#include <jni.h>
#include <stdlib.h>

#include "aterm2.h"
#include "safio.h"

#include "sglrInterface.h"
#include "inputStringBuilder.h"
#include "filters.h"
#include "parseTableDB.h"

#include "Error-manager.h"

#include <sglr_SGLRInvoker.h>


static int initialized = 0;

JNIEXPORT jint JNICALL Java_sglr_SGLRInvoker_initialize(JNIEnv* env, jobject method){
	if(!initialized){
		ATerm x;
		ATinit(0, NULL, &x);
		SGLR_initialize();
		initialized = 1;
	}
}

static ATerm result = NULL;

static ATerm parse(const unsigned char *inputString, const unsigned char *parseTableName){
	PT_ParseTree parseTree;
	
	InputString sglrInputString = IS_createInputStringFromString(inputString);
	
	if(inputString == NULL){
		ERR_displaySummary(ERR_getManagerSummary());
		return ERR_SummaryToTerm(ERR_getManagerSummary());
	}
	
	if(!SGLR_isParseTableLoaded(parseTableName)){
		if(SG_AddParseTable(parseTableName) == NULL){
			ERR_displaySummary(ERR_getManagerSummary());
			return ERR_SummaryToTerm(ERR_getManagerSummary());
		}
	}
	
	parseTree = SGLR_parse(sglrInputString, parseTableName);
	
	if(parseTree == NULL){
		ERR_displaySummary(ERR_getManagerSummary());
		return ERR_SummaryToTerm(ERR_getManagerSummary());
	}
	
	return PT_ParseTreeToTerm(parseTree);
}

JNIEXPORT void JNICALL Java_sglr_SGLRInvoker_parse(JNIEnv* env, jobject method){
	jclass clazz = (*env)->GetObjectClass(env, method);
	
	/* Get source data. */
	jmethodID getInputString = (*env)->GetMethodID(env, clazz, "getInputString", "()Ljava/nio/ByteBuffer;");
	jobject inputStringBuffer = (*env)->CallNonvirtualObjectMethod(env, method, clazz, getInputString);
	const unsigned char *inputString = (unsigned char*) ((*env)->GetDirectBufferAddress(env, inputStringBuffer));
	
	/* Get parse table. */
	jmethodID getParseTableName = (*env)->GetMethodID(env, clazz, "getParseTableName", "()Ljava/lang/String;");
	jstring parseTableNameString = (*env)->CallNonvirtualObjectMethod(env, method, clazz, getParseTableName);
	const unsigned char *parseTableName = (*env)->GetStringUTFChars(env, parseTableNameString, NULL);
	
	/* Call parser. */
	result = parse(inputString, parseTableName); /* No need to protect the result, since no GC will occur until the next invocation of the parser (in which case we want the old result to be collectable). */
	
	/* Release stuff. */
	(*env)->ReleaseStringUTFChars(env, parseTableNameString, parseTableName);
}

static BinaryWriter resultWriter = NULL;

JNIEXPORT jobject JNICALL Java_sglr_SGLRInvoker_getResultData(JNIEnv* env, jobject method){
	char *resultData = ATwriteToString(result); /* ResultData doesn't need to be freed, since it's managed by the aterm library. */
	
	return (*env)->NewDirectByteBuffer(env, resultData, strlen(resultData));
}
