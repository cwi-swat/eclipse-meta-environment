#include <hashtable.h>

#include <stdlib.h>
#include <stdio.h>

#define DEFAULTTABLEBITSIZE 8 /* Default table size is 256 entries */

#define PREALLOCATEDENTRYBLOCKSINCREMENT 16
#define PREALLOCATEDENTRYBLOCKSINCREMENTMASK 0x0000000fU
#define PREALLOCATEDENTRYBLOCKSIZE 256

static HTEntryCache createEntryCache(){
	HTEntry *block;
	
	HTEntryCache entryCache = (HTEntryCache) malloc(sizeof(struct _HTEntryCache));
	if(entryCache == NULL){
		fprintf(stderr, "Failed to allocate memory for entry cache.\n");
		exit(1);
	}
	
	entryCache->blocks = (HTEntry**) malloc(PREALLOCATEDENTRYBLOCKSINCREMENT * sizeof(HTEntry*));
	if(entryCache->blocks == NULL){
		fprintf(stderr, "Failed to allocate array for storing references to pre-allocated entries.\n");
		exit(1);
	}
	entryCache->nrOfBlocks = 1;
	
	block = (HTEntry*) malloc(PREALLOCATEDENTRYBLOCKSIZE * sizeof(struct HTEntry));
	if(block == NULL){
		fprintf(stderr, "Failed to allocate block of memory for pre-allocated entries.\n");
		exit(1);
	}
	entryCache->nextEntry = block;
	entryCache->spaceLeft = PREALLOCATEDENTRYBLOCKSIZE;
	
	entryCache->blocks[0] = block;
	
	entryCache->freeList = NULL;
	
	return entryCache;
}

static void destroyEntryCache(HTEntryCache entryCache){
	int i = entryCache->nrOfBlocks;
	do{
		free(entryCache->blocks[--i]);
	}while(i > 0);
	free(entryCache->blocks);
	
	free(entryCache);
}

static void expandEntryCache(HTEntryCache entryCache){
	HTEntry *block = (HTEntry*) malloc(PREALLOCATEDENTRYBLOCKSIZE * sizeof(struct HTEntry));
	if(block == NULL){
		fprintf(stderr, "Failed to allocate block of memory for pre-allocated entries.\n");
		exit(1);
	}
	
	if((entryCache->nrOfBlocks & PREALLOCATEDENTRYBLOCKSINCREMENTMASK) == 0){
		entryCache->blocks = (HTEntry**) realloc(entryCache->blocks, (entryCache->nrOfBlocks + PREALLOCATEDENTRYBLOCKSINCREMENT) * sizeof(HTEntry*));
		if(entryCache->blocks == NULL){
			fprintf(stderr, "Failed to allocate array for storing references to pre-allocated entries.\n");
			exit(1);
		}
	}
	
	entryCache->blocks[entryCache->nrOfBlocks++] = block;
	
	entryCache->spaceLeft = PREALLOCATEDENTRYBLOCKSIZE;
	entryCache->nextEntry = block;
}

static HTEntry* getEntry(HTEntryCache entryCache){
	HTEntry *entry = entryCache->freeList;
	
	if(entry != NULL){
		entryCache->freeList = entry->next;
	}else{
		if(entryCache->spaceLeft == 0) expandEntryCache(entryCache);
		
		entryCache->spaceLeft--;
		entry = entryCache->nextEntry++;
	}
	
	return entry;
}

static void releaseEntry(HTEntryCache entryCache, HTEntry *entry){
	entry->next = entryCache->freeList;
	entryCache->freeList = entry;
}

static unsigned int supplementalHash(unsigned int h){
	return ((h << 7) - h + (h >> 9) + (h >> 17));
}

static void ensureTableCapacity(HThashtable hashtable){
	HTEntry **oldTable = hashtable->table;
	
	unsigned int currentTableSize = hashtable->tableSize;
	if(hashtable->load >= hashtable->threshold){
		unsigned int hashMask;
		int i = currentTableSize - 1;
		
		unsigned int newTableSize = currentTableSize << 1;
		HTEntry **table = (HTEntry**) calloc(newTableSize, sizeof(HTEntry*));
		if(table == NULL){
			fprintf(stderr, "The hashtable was unable to allocate memory for extending the entry table.\n");
			exit(1);
		}
		hashtable->table = table;
		hashtable->tableSize = newTableSize;
		
		hashMask = newTableSize - 1;
		hashtable->hashMask = hashMask;
		hashtable->threshold <<= 1;
		
		do{
			HTEntry *e = oldTable[i];
			while(e != NULL){
				unsigned int bucketPos = e->hash & hashMask;
				
				HTEntry *currentEntry = table[bucketPos];
				
				/* Find the next entry in the old table. */
				HTEntry *nextEntry = e->next;
				
				/* Add the entry in the new table. */
				e->next = currentEntry;
				table[bucketPos] = e;
				
				e = nextEntry;
			}
		}while(--i >= 0);
		
		free(oldTable);
	}
}

HThashtable HTcreate(int (*equals)(void*, void*), float loadPercentage){
	unsigned int tableSize = 1 << DEFAULTTABLEBITSIZE;
	
	HThashtable hashtable = (HThashtable) malloc(sizeof(struct _HThashtable));
	if(hashtable == NULL){
		fprintf(stderr, "Unable to allocate memory for creating a hashtable.\n");
		exit(1);
	}
	
	hashtable->equals = equals;
	
	hashtable->entryCache = createEntryCache();
	
	hashtable->table = (HTEntry**) calloc(tableSize, sizeof(HTEntry*));
	if(hashtable->table == NULL){
		fprintf(stderr, "The hashtable was unable to allocate memory for the entry table.\n");
		exit(1);
	}
	hashtable->tableSize = tableSize;
	
	hashtable->hashMask = tableSize - 1;
	hashtable->threshold = tableSize * loadPercentage;
	
	hashtable->load = 0;
	
	return hashtable;
}

void *HTput(HThashtable hashtable, void *key, unsigned int h, void *value){
	unsigned int bucketPos;
	HTEntry **table;
	HTEntry *currentEntry, *entry;
	
	unsigned int hash = supplementalHash(h);
	
	ensureTableCapacity(hashtable);
	
	bucketPos = hash & hashtable->hashMask;
	table = hashtable->table;
	currentEntry = table[bucketPos];
	
	entry = currentEntry;
	while(entry != NULL){
		if(entry->key == key || (entry->hash == hash && (*hashtable->equals)(entry->key, key))){
			void *oldValue = entry->value;
			
			entry->value = value;
			
			return oldValue;
		}
		
		entry = entry->next;
	}
	
	/* Insert the new entry at the start of the bucket and link it with the colliding entries (if present). */
	entry = getEntry(hashtable->entryCache);
	entry->hash = hash;
	entry->key = key;
	entry->value = value;
	entry->next = currentEntry;
	
	table[bucketPos] = entry;
	hashtable->load++;
	
	return NULL;
}

void *HTget(HThashtable hashtable, void *key, unsigned int h){
	unsigned int hash = supplementalHash(h);
	
	unsigned int bucketPos = hash & hashtable->hashMask;
	HTEntry *entry = hashtable->table[bucketPos];
	while(entry != NULL && entry->key != key && !(entry->hash == hash && (*hashtable->equals)(entry->key, key))){
		entry = entry->next;
	}
	
	if(entry == NULL) return NULL;
	return entry->value;
}

void *HTremove(HThashtable hashtable, void *key, unsigned int h){
	unsigned int hash = supplementalHash(h);
	
	unsigned int bucketPos = hash & hashtable->hashMask;
	HTEntry **table = hashtable->table;
	HTEntry *entry = table[bucketPos];
	
	HTEntry *previousEntry = NULL;
	while(entry != NULL){
		if(entry->key == key || (entry->hash == hash && (*hashtable->equals)(entry->key, key))){
			void *oldValue = entry->value;
			
			HTEntry *nextEntry = entry->next;
			if(previousEntry == NULL) table[bucketPos] = nextEntry;
			else previousEntry->next = nextEntry;
			
			hashtable->load--;
			
			releaseEntry(hashtable->entryCache, entry);
			
			return oldValue;
		}
		previousEntry = entry;
		entry = entry->next;
	}
	return NULL;
}

unsigned int HTsize(HThashtable hashtable){
	return hashtable->load;
}

void HTdestroy(HThashtable hashtable){
	HTEntry **table = hashtable->table;
	
	destroyEntryCache(hashtable->entryCache);
	
	free(table);
	
	free(hashtable);
}

HTiterator HTcreateIterator(HThashtable hashtable){
	HTiterator iterator = (HTiterator) malloc(sizeof(struct _HTiterator));
	if(iterator == NULL){
		fprintf(stderr, "The hashtable was unable to allocate memory for an iterator.");
		exit(1);
	}
	
	iterator->hashtable = hashtable;
	iterator->position = -1;
	iterator->currentEntry = NULL;
	
	return iterator;
}

HTEntry *HTgetNext(HTiterator iterator){
	HTEntry *currentEntry = iterator->currentEntry;
	
	HTEntry *nextEntry = NULL;
        if(currentEntry != NULL){
                nextEntry = currentEntry->next;
        }
	
        if(nextEntry == NULL){
                HThashtable hashtable = iterator->hashtable;
                do{
                        nextEntry = hashtable->table[++iterator->position];
                }while(nextEntry == NULL && iterator->position < hashtable->tableSize);

                if(nextEntry == NULL) return NULL; /* End of hashtable. */
        }
	
	iterator->currentEntry = nextEntry;
	
	return nextEntry;
}

