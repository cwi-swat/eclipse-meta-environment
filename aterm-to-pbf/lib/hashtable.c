#include "hashtable.h"
#include <stdlib.h>
#include <stdio.h>

#define DEFAULTTABLEBITSIZE 8 /* Default table size is 256 entries */

#define PREALLOCATEDENTRYBLOCKSINCREMENT 16
#define PREALLOCATEDENTRYBLOCKSINCREMENTMASK 0x0000000fU
#define PREALLOCATEDENTRYBLOCKSIZE 256

struct Entry{
	void *key;
	unsigned int hash;
	
	void *value;
	
	Entry *next;
};

static EntryCache createEntryCache(){
	Entry *block;
	
	EntryCache entryCache = (EntryCache) malloc(sizeof(struct _EntryCache));
	if(entryCache == NULL){
		printf("Failed to allocate memory for entry cache.\n");
		exit(1);
	}
	
	entryCache->blocks = (Entry**) malloc(PREALLOCATEDENTRYBLOCKSINCREMENT * sizeof(Entry*));
	if(entryCache->blocks == NULL){
		printf("Failed to allocate array for storing references to pre-allocated entries.\n");
		exit(1);
	}
	entryCache->nrOfBlocks = 1;
	
	block = (Entry*) malloc(PREALLOCATEDENTRYBLOCKSIZE * sizeof(struct Entry));
	if(block == NULL){
		printf("Failed to allocate block of memory for pre-allocated entries.\n");
		exit(1);
	}
	entryCache->nextEntry = block;
	entryCache->spaceLeft = PREALLOCATEDENTRYBLOCKSIZE;
	
	entryCache->blocks[0] = block;
	
	entryCache->freeList = NULL;
	
	return entryCache;
}

static void destroyEntryCache(EntryCache entryCache){
	int i = entryCache->nrOfBlocks;
	do{
		free(entryCache->blocks[--i]);
	}while(i > 0);
	free(entryCache->blocks);
	
	free(entryCache);
}

static void expandEntryCache(EntryCache entryCache){
	Entry *block = (Entry*) malloc(PREALLOCATEDENTRYBLOCKSIZE * sizeof(struct Entry));
	if(block == NULL){
		printf("Failed to allocate block of memory for pre-allocated entries.\n");
		exit(1);
	}
	
	if((entryCache->nrOfBlocks & PREALLOCATEDENTRYBLOCKSINCREMENTMASK) == 0){
		entryCache->blocks = (Entry**) realloc(entryCache->blocks, (entryCache->nrOfBlocks + PREALLOCATEDENTRYBLOCKSINCREMENT) * sizeof(Entry*));
		if(entryCache->blocks == NULL){
			printf("Failed to allocate array for storing references to pre-allocated entries.\n");
			exit(1);
		}
	}
	
	entryCache->blocks[entryCache->nrOfBlocks++] = block;
	
	entryCache->spaceLeft = PREALLOCATEDENTRYBLOCKSIZE;
	entryCache->nextEntry = block;
}

static Entry* getEntry(EntryCache entryCache){
	Entry *entry = entryCache->freeList;
	
	if(entry != NULL){
		entryCache->freeList = entry->next;
	}else{
		if(entryCache->spaceLeft == 0) expandEntryCache(entryCache);
		
		entryCache->spaceLeft--;
		entry = entryCache->nextEntry++;
	}
	
	return entry;
}

static void releaseEntry(EntryCache entryCache, Entry *entry){
	entry->next = entryCache->freeList;
	entryCache->freeList = entry;
}

static unsigned int supplementalHash(unsigned int h){
	return ((h << 7) - h + (h >> 9) + (h >> 17));
}

static void ensureTableCapacity(Hashtable hashtable){
	Entry **oldTable = hashtable->table;
	
	unsigned int currentTableSize = hashtable->tableSize;
	if(hashtable->load >= hashtable->threshold){
		unsigned int hashMask;
		int i = currentTableSize - 1;
		
		unsigned int newTableSize = currentTableSize << 1;
		Entry **table = (Entry**) calloc(newTableSize, sizeof(Entry*));
		if(table == NULL){
			printf("The hashtable was unable to allocate memory for extending the entry table.\n");
			exit(1);
		}
		hashtable->table = table;
		hashtable->tableSize = newTableSize;
		
		hashMask = newTableSize - 1;
		hashtable->hashMask = hashMask;
		hashtable->threshold <<= 1;
		
		do{
			Entry *e = oldTable[i];
			while(e != NULL){
				unsigned int bucketPos = e->hash & hashMask;
				
				Entry *currentEntry = table[bucketPos];
				
				/* Find the next entry in the old table. */
				Entry *nextEntry = e->next;
				
				/* Add the entry in the new table. */
				e->next = currentEntry;
				table[bucketPos] = e;
				
				e = nextEntry;
			}
			i--;
		}while(i >= 0);
		
		free(oldTable);
	}
}

Hashtable HTcreate(int (*equals)(void*, void*), float loadPercentage){
	unsigned int tableSize = 1 << DEFAULTTABLEBITSIZE;
	
	Hashtable hashtable = (Hashtable) malloc(sizeof(struct _Hashtable));
	if(hashtable == NULL){
		printf("Unable to allocate memory for creating a hashtable.\n");
		exit(1);
	}
	
	hashtable->equals = equals;
	
	hashtable->entryCache = createEntryCache();
	
	hashtable->table = (Entry**) calloc(tableSize, sizeof(Entry*));
	if(hashtable->table == NULL){
		printf("The hashtable was unable to allocate memory for the entry table.\n");
		exit(1);
	}
	hashtable->tableSize = tableSize;
	
	hashtable->hashMask = tableSize - 1;
	hashtable->threshold = tableSize * loadPercentage;
	
	hashtable->load = 0;
	
	return hashtable;
}

void *HTput(Hashtable hashtable, void *key, unsigned int h, void *value){
	unsigned int bucketPos;
	Entry **table;
	Entry *currentEntry, *entry;
	
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

void *HTget(Hashtable hashtable, void *key, unsigned int h){
	unsigned int hash = supplementalHash(h);
	
	unsigned int bucketPos = hash & hashtable->hashMask;
	Entry *entry = hashtable->table[bucketPos];
	while(entry != NULL && entry->key != key && !(entry->hash == hash && (*hashtable->equals)(entry->key, key))){
		entry = entry->next;
	}
	
	if(entry == NULL) return NULL;
	return entry->value;
}

void *HTremove(Hashtable hashtable, void *key, unsigned int h){
	unsigned int hash = supplementalHash(h);
	
	unsigned int bucketPos = hash & hashtable->hashMask;
	Entry **table = hashtable->table;
	Entry *entry = table[bucketPos];
	
	Entry *previousEntry = NULL;
	while(entry != NULL){
		if(entry->key == key || (entry->hash == hash && (*hashtable->equals)(entry->key, key))){
			void *oldValue = entry->value;
			
			Entry *nextEntry = entry->next;
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

unsigned int HTsize(Hashtable hashtable){
	return hashtable->load;
}

void HTdestroy(Hashtable hashtable){
	Entry **table = hashtable->table;
	
	destroyEntryCache(hashtable->entryCache);
	
	free(table);
	
	free(hashtable);
}

int defaultEquals(void* left, void* right){
	return (left == right);
}
