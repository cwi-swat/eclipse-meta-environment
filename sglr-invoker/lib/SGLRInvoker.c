#include <jni.h>
#include <stdlib.h>

#include <aterm2.h>
#include <safio.h>

#include <sglrInterface.h>
#include <inputStringBuilder.h>
#include <inputString-api.h>
#include <filters.h>
#include <filterOptions.h>
#include <parseTableDB.h>

#include <Error-manager.h>

#include <sglr_SGLRInvoker.h>

#define FILTERS_DEFAULT 0
#define FILTERS_REMOVE_CYCLES 1
#define FILTERS_DIRECT_PREFERENCE (1 << 1)
#define FILTERS_INDIRECT_PREFERENCE (1 << 2)
#define FILTERS_PREFERENCE_COUNT (1 << 3)
#define FILTERS_INJECTION_COUNT (1 << 4)
#define FILTERS_PRIORITY (1 << 5)
#define FILTERS_REJECT (1 << 6)

static int initialized = 0;

JNIEXPORT void JNICALL Java_sglr_SGLRInvoker_initialize(JNIEnv* env, jobject method){
	if(!initialized){
		ATerm x;
		ATinit(0, NULL, &x);
		SGLR_initialize();
		FLT_initalize();
		initialized = 1;
	}
}

static ATerm result = NULL;

static ATerm parse(const unsigned char *inputString, unsigned int inputStringLength, const char *inputPath, const char *parseTableName){
	PT_ParseTree parseTree;
	InputString sglrInputString;
	
	ERR_resetErrorManager();
	
	sglrInputString = IS_allocateString(inputPath, inputString, inputStringLength);
	
	if(inputString == NULL){
		return ERR_SummaryToTerm(ERR_getManagerSummary());
	}
	
	if(!SGLR_isParseTableLoaded(parseTableName)){
		if(SG_AddParseTable(parseTableName) == NULL){
			return ERR_SummaryToTerm(ERR_getManagerSummary());
		}
	}
	
	parseTree = SGLR_parse(sglrInputString, parseTableName);
	
	if(parseTree == NULL){
		return ERR_SummaryToTerm(ERR_getManagerSummary());
	}
	
	return PT_ParseTreeToTerm(parseTree);
}

static void setFilters(unsigned int filterFlags){
	FLT_initializeDefaultOptions();
	
	if(filterFlags == FILTERS_DEFAULT){
		return;
	}
	if((filterFlags & FILTERS_REMOVE_CYCLES) == FILTERS_REMOVE_CYCLES){
		ATbool removeCycles = FLT_getRemoveCyclesFlag();
		FLT_setRemoveCyclesFlag((removeCycles == ATtrue) ? ATfalse : ATtrue);
	}
	if((filterFlags & FILTERS_DIRECT_PREFERENCE) == FILTERS_DIRECT_PREFERENCE){
		ATbool directPreference = FLT_getDirectPreferenceFlag();
		FLT_setDirectPreferenceFlag((directPreference == ATtrue) ? ATfalse : ATtrue);
	}
	if((filterFlags & FILTERS_INDIRECT_PREFERENCE) == FILTERS_INDIRECT_PREFERENCE){
		ATbool indirectPreference = FLT_getIndirectPreferenceFlag();
		FLT_setIndirectPreferenceFlag((indirectPreference == ATtrue) ? ATfalse : ATtrue);
	}
	if((filterFlags & FILTERS_PREFERENCE_COUNT) == FILTERS_PREFERENCE_COUNT){
		ATbool preferenceCount = FLT_getPreferenceCountFlag();
		FLT_setPreferenceCountFlag((preferenceCount == ATtrue) ? ATfalse : ATtrue);
	}
	if((filterFlags & FILTERS_INJECTION_COUNT) == FILTERS_INJECTION_COUNT){
		ATbool injectionCount = FLT_getInjectionCountFlag();
		FLT_setInjectionCountFlag((injectionCount == ATtrue) ? ATfalse : ATtrue);
	}
	if((filterFlags & FILTERS_PRIORITY) == FILTERS_PRIORITY){
		ATbool priority = FLT_getPriorityFlag();
		FLT_setPriorityFlag((priority == ATtrue) ? ATfalse : ATtrue);
	}
	if((filterFlags & FILTERS_REJECT) == FILTERS_REJECT){
		ATbool reject = FLT_getRejectFlag();
		FLT_setRejectFlag((reject == ATtrue) ? ATfalse : ATtrue);
	}
}

JNIEXPORT jobject JNICALL Java_sglr_SGLRInvoker_parse(JNIEnv* env, jobject method){
	char *resultData;
	
	jclass clazz = (*env)->GetObjectClass(env, method);
	
	/* Get source data. */
	jmethodID getInputString = (*env)->GetMethodID(env, clazz, "getInputString", "()Ljava/nio/ByteBuffer;");
	jobject inputStringBuffer = (*env)->CallNonvirtualObjectMethod(env, method, clazz, getInputString);
	const unsigned char *inputString = (unsigned char*) ((*env)->GetDirectBufferAddress(env, inputStringBuffer));
	
	/* Get the input string length. */
	jmethodID getInputStringLength = (*env)->GetMethodID(env, clazz, "getInputStringLength", "()I");
	unsigned int inputStringLength = (unsigned int) (*env)->CallNonvirtualObjectMethod(env, method, clazz, getInputStringLength);
	
	/* Get the input path. */
	jmethodID getInputPath = (*env)->GetMethodID(env, clazz, "getInputPath", "()Ljava/lang/String;");
	jstring inputPathString = (*env)->CallNonvirtualObjectMethod(env, method, clazz, getInputPath);
	const char *inputPath = (*env)->GetStringUTFChars(env, inputPathString, NULL);
	
	/* Get parse table. */
	jmethodID getParseTableName = (*env)->GetMethodID(env, clazz, "getParseTableName", "()Ljava/lang/String;");
	jstring parseTableNameString = (*env)->CallNonvirtualObjectMethod(env, method, clazz, getParseTableName);
	const char *parseTableName = (*env)->GetStringUTFChars(env, parseTableNameString, NULL);
	
	/* Get filter flags */
	jmethodID getFilterFlags = (*env)->GetMethodID(env, clazz, "getFilterFlags", "()I");
	unsigned int filterFlags = (unsigned int) (*env)->CallNonvirtualObjectMethod(env, method, clazz, getFilterFlags);
	
	setFilters(filterFlags);
	
	/* Call parser. */
	result = parse(inputString, inputStringLength, inputPath, parseTableName); /* No need to protect the result, since no GC will occur until the next invocation of the parser (in which case we want the old result to be collectable). */
	
	/* Release stuff. */
	(*env)->ReleaseStringUTFChars(env, parseTableNameString, parseTableName);
	(*env)->ReleaseStringUTFChars(env, inputPathString, inputPath);
	
	resultData = ATwriteToString(result); /* ResultData doesn't need to be freed, since it's managed by the aterm library. */
	
	return (*env)->NewDirectByteBuffer(env, resultData, strlen(resultData));
}
