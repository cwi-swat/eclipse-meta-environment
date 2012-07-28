#ifndef _RSTORE_H
#define _RSTORE_H

#include <stdlib.h>
#include <string.h>
#include <aterm1.h>
#include "RStore_dict.h"

typedef struct _RS_RElem *RS_RElem;
typedef struct _RS_RType *RS_RType;
typedef struct _RS_RTuple *RS_RTuple;
typedef struct _RS_RStore *RS_RStore;
typedef struct _RS_RElemElements *RS_RElemElements;
typedef struct _RS_RTypeColumnTypes *RS_RTypeColumnTypes;
typedef struct _RS_RTupleRtuples *RS_RTupleRtuples;
typedef struct _RS_StrChar *RS_StrChar;
typedef struct _RS_StrCon *RS_StrCon;
typedef struct _RS_BoolCon *RS_BoolCon;
typedef struct _RS_NatCon *RS_NatCon;
typedef struct _RS_IdCon *RS_IdCon;
typedef struct _RS_Integer *RS_Integer;
typedef struct _RS_Location *RS_Location;
typedef struct _RS_Area *RS_Area;

#ifdef FAST_API
#define RS_initRStoreApi() (init_RStore_dict())
#else
void _RS_initRStoreApi (void);
#define RS_initRStoreApi() (_RS_initRStoreApi())
#endif

#ifdef FAST_API
#define RS_protectRElem(arg) (ATprotect((ATerm*)((void*) (arg))))
#else
void _RS_protectRElem (RS_RElem * arg);
#define RS_protectRElem(arg) (_RS_protectRElem(arg))
#endif
#ifdef FAST_API
#define RS_unprotectRElem(arg) (ATunprotect((ATerm*)((void*) (arg))))
#else
void _RS_unprotectRElem (RS_RElem * arg);
#define RS_unprotectRElem(arg) (_RS_unprotectRElem(arg))
#endif
#ifdef FAST_API
#define RS_protectRType(arg) (ATprotect((ATerm*)((void*) (arg))))
#else
void _RS_protectRType (RS_RType * arg);
#define RS_protectRType(arg) (_RS_protectRType(arg))
#endif
#ifdef FAST_API
#define RS_unprotectRType(arg) (ATunprotect((ATerm*)((void*) (arg))))
#else
void _RS_unprotectRType (RS_RType * arg);
#define RS_unprotectRType(arg) (_RS_unprotectRType(arg))
#endif
#ifdef FAST_API
#define RS_protectRTuple(arg) (ATprotect((ATerm*)((void*) (arg))))
#else
void _RS_protectRTuple (RS_RTuple * arg);
#define RS_protectRTuple(arg) (_RS_protectRTuple(arg))
#endif
#ifdef FAST_API
#define RS_unprotectRTuple(arg) (ATunprotect((ATerm*)((void*) (arg))))
#else
void _RS_unprotectRTuple (RS_RTuple * arg);
#define RS_unprotectRTuple(arg) (_RS_unprotectRTuple(arg))
#endif
#ifdef FAST_API
#define RS_protectRStore(arg) (ATprotect((ATerm*)((void*) (arg))))
#else
void _RS_protectRStore (RS_RStore * arg);
#define RS_protectRStore(arg) (_RS_protectRStore(arg))
#endif
#ifdef FAST_API
#define RS_unprotectRStore(arg) (ATunprotect((ATerm*)((void*) (arg))))
#else
void _RS_unprotectRStore (RS_RStore * arg);
#define RS_unprotectRStore(arg) (_RS_unprotectRStore(arg))
#endif
#ifdef FAST_API
#define RS_protectRElemElements(arg) (ATprotect((ATerm*)((void*) (arg))))
#else
void _RS_protectRElemElements (RS_RElemElements * arg);
#define RS_protectRElemElements(arg) (_RS_protectRElemElements(arg))
#endif
#ifdef FAST_API
#define RS_unprotectRElemElements(arg) (ATunprotect((ATerm*)((void*) (arg))))
#else
void _RS_unprotectRElemElements (RS_RElemElements * arg);
#define RS_unprotectRElemElements(arg) (_RS_unprotectRElemElements(arg))
#endif
#ifdef FAST_API
#define RS_protectRTypeColumnTypes(arg) (ATprotect((ATerm*)((void*) (arg))))
#else
void _RS_protectRTypeColumnTypes (RS_RTypeColumnTypes * arg);
#define RS_protectRTypeColumnTypes(arg) (_RS_protectRTypeColumnTypes(arg))
#endif
#ifdef FAST_API
#define RS_unprotectRTypeColumnTypes(arg) (ATunprotect((ATerm*)((void*) (arg))))
#else
void _RS_unprotectRTypeColumnTypes (RS_RTypeColumnTypes * arg);
#define RS_unprotectRTypeColumnTypes(arg) (_RS_unprotectRTypeColumnTypes(arg))
#endif
#ifdef FAST_API
#define RS_protectRTupleRtuples(arg) (ATprotect((ATerm*)((void*) (arg))))
#else
void _RS_protectRTupleRtuples (RS_RTupleRtuples * arg);
#define RS_protectRTupleRtuples(arg) (_RS_protectRTupleRtuples(arg))
#endif
#ifdef FAST_API
#define RS_unprotectRTupleRtuples(arg) (ATunprotect((ATerm*)((void*) (arg))))
#else
void _RS_unprotectRTupleRtuples (RS_RTupleRtuples * arg);
#define RS_unprotectRTupleRtuples(arg) (_RS_unprotectRTupleRtuples(arg))
#endif
#ifdef FAST_API
#define RS_protectStrChar(arg) (ATprotect((ATerm*)((void*) (arg))))
#else
void _RS_protectStrChar (RS_StrChar * arg);
#define RS_protectStrChar(arg) (_RS_protectStrChar(arg))
#endif
#ifdef FAST_API
#define RS_unprotectStrChar(arg) (ATunprotect((ATerm*)((void*) (arg))))
#else
void _RS_unprotectStrChar (RS_StrChar * arg);
#define RS_unprotectStrChar(arg) (_RS_unprotectStrChar(arg))
#endif
#ifdef FAST_API
#define RS_protectStrCon(arg) (ATprotect((ATerm*)((void*) (arg))))
#else
void _RS_protectStrCon (RS_StrCon * arg);
#define RS_protectStrCon(arg) (_RS_protectStrCon(arg))
#endif
#ifdef FAST_API
#define RS_unprotectStrCon(arg) (ATunprotect((ATerm*)((void*) (arg))))
#else
void _RS_unprotectStrCon (RS_StrCon * arg);
#define RS_unprotectStrCon(arg) (_RS_unprotectStrCon(arg))
#endif
#ifdef FAST_API
#define RS_protectBoolCon(arg) (ATprotect((ATerm*)((void*) (arg))))
#else
void _RS_protectBoolCon (RS_BoolCon * arg);
#define RS_protectBoolCon(arg) (_RS_protectBoolCon(arg))
#endif
#ifdef FAST_API
#define RS_unprotectBoolCon(arg) (ATunprotect((ATerm*)((void*) (arg))))
#else
void _RS_unprotectBoolCon (RS_BoolCon * arg);
#define RS_unprotectBoolCon(arg) (_RS_unprotectBoolCon(arg))
#endif
#ifdef FAST_API
#define RS_protectNatCon(arg) (ATprotect((ATerm*)((void*) (arg))))
#else
void _RS_protectNatCon (RS_NatCon * arg);
#define RS_protectNatCon(arg) (_RS_protectNatCon(arg))
#endif
#ifdef FAST_API
#define RS_unprotectNatCon(arg) (ATunprotect((ATerm*)((void*) (arg))))
#else
void _RS_unprotectNatCon (RS_NatCon * arg);
#define RS_unprotectNatCon(arg) (_RS_unprotectNatCon(arg))
#endif
#ifdef FAST_API
#define RS_protectIdCon(arg) (ATprotect((ATerm*)((void*) (arg))))
#else
void _RS_protectIdCon (RS_IdCon * arg);
#define RS_protectIdCon(arg) (_RS_protectIdCon(arg))
#endif
#ifdef FAST_API
#define RS_unprotectIdCon(arg) (ATunprotect((ATerm*)((void*) (arg))))
#else
void _RS_unprotectIdCon (RS_IdCon * arg);
#define RS_unprotectIdCon(arg) (_RS_unprotectIdCon(arg))
#endif
#ifdef FAST_API
#define RS_protectInteger(arg) (ATprotect((ATerm*)((void*) (arg))))
#else
void _RS_protectInteger (RS_Integer * arg);
#define RS_protectInteger(arg) (_RS_protectInteger(arg))
#endif
#ifdef FAST_API
#define RS_unprotectInteger(arg) (ATunprotect((ATerm*)((void*) (arg))))
#else
void _RS_unprotectInteger (RS_Integer * arg);
#define RS_unprotectInteger(arg) (_RS_unprotectInteger(arg))
#endif
#ifdef FAST_API
#define RS_protectLocation(arg) (ATprotect((ATerm*)((void*) (arg))))
#else
void _RS_protectLocation (RS_Location * arg);
#define RS_protectLocation(arg) (_RS_protectLocation(arg))
#endif
#ifdef FAST_API
#define RS_unprotectLocation(arg) (ATunprotect((ATerm*)((void*) (arg))))
#else
void _RS_unprotectLocation (RS_Location * arg);
#define RS_unprotectLocation(arg) (_RS_unprotectLocation(arg))
#endif
#ifdef FAST_API
#define RS_protectArea(arg) (ATprotect((ATerm*)((void*) (arg))))
#else
void _RS_protectArea (RS_Area * arg);
#define RS_protectArea(arg) (_RS_protectArea(arg))
#endif
#ifdef FAST_API
#define RS_unprotectArea(arg) (ATunprotect((ATerm*)((void*) (arg))))
#else
void _RS_unprotectArea (RS_Area * arg);
#define RS_unprotectArea(arg) (_RS_unprotectArea(arg))
#endif
#ifdef FAST_API
#define RS_RElemFromTerm(t) ((RS_RElem)(t))
#else
RS_RElem _RS_RElemFromTerm (ATerm t);
#define RS_RElemFromTerm(t) (_RS_RElemFromTerm(t))
#endif
#ifdef FAST_API
#define RS_RElemToTerm(arg) ((ATerm)(arg))
#else
ATerm _RS_RElemToTerm (RS_RElem arg);
#define RS_RElemToTerm(arg) (_RS_RElemToTerm(arg))
#endif
#ifdef FAST_API
#define RS_RTypeFromTerm(t) ((RS_RType)(t))
#else
RS_RType _RS_RTypeFromTerm (ATerm t);
#define RS_RTypeFromTerm(t) (_RS_RTypeFromTerm(t))
#endif
#ifdef FAST_API
#define RS_RTypeToTerm(arg) ((ATerm)(arg))
#else
ATerm _RS_RTypeToTerm (RS_RType arg);
#define RS_RTypeToTerm(arg) (_RS_RTypeToTerm(arg))
#endif
#ifdef FAST_API
#define RS_RTupleFromTerm(t) ((RS_RTuple)(t))
#else
RS_RTuple _RS_RTupleFromTerm (ATerm t);
#define RS_RTupleFromTerm(t) (_RS_RTupleFromTerm(t))
#endif
#ifdef FAST_API
#define RS_RTupleToTerm(arg) ((ATerm)(arg))
#else
ATerm _RS_RTupleToTerm (RS_RTuple arg);
#define RS_RTupleToTerm(arg) (_RS_RTupleToTerm(arg))
#endif
#ifdef FAST_API
#define RS_RStoreFromTerm(t) ((RS_RStore)(t))
#else
RS_RStore _RS_RStoreFromTerm (ATerm t);
#define RS_RStoreFromTerm(t) (_RS_RStoreFromTerm(t))
#endif
#ifdef FAST_API
#define RS_RStoreToTerm(arg) ((ATerm)(arg))
#else
ATerm _RS_RStoreToTerm (RS_RStore arg);
#define RS_RStoreToTerm(arg) (_RS_RStoreToTerm(arg))
#endif
#ifdef FAST_API
#define RS_RElemElementsFromTerm(t) ((RS_RElemElements)(t))
#else
RS_RElemElements _RS_RElemElementsFromTerm (ATerm t);
#define RS_RElemElementsFromTerm(t) (_RS_RElemElementsFromTerm(t))
#endif
#ifdef FAST_API
#define RS_RElemElementsToTerm(arg) ((ATerm)(arg))
#else
ATerm _RS_RElemElementsToTerm (RS_RElemElements arg);
#define RS_RElemElementsToTerm(arg) (_RS_RElemElementsToTerm(arg))
#endif
#ifdef FAST_API
#define RS_RTypeColumnTypesFromTerm(t) ((RS_RTypeColumnTypes)(t))
#else
RS_RTypeColumnTypes _RS_RTypeColumnTypesFromTerm (ATerm t);
#define RS_RTypeColumnTypesFromTerm(t) (_RS_RTypeColumnTypesFromTerm(t))
#endif
#ifdef FAST_API
#define RS_RTypeColumnTypesToTerm(arg) ((ATerm)(arg))
#else
ATerm _RS_RTypeColumnTypesToTerm (RS_RTypeColumnTypes arg);
#define RS_RTypeColumnTypesToTerm(arg) (_RS_RTypeColumnTypesToTerm(arg))
#endif
#ifdef FAST_API
#define RS_RTupleRtuplesFromTerm(t) ((RS_RTupleRtuples)(t))
#else
RS_RTupleRtuples _RS_RTupleRtuplesFromTerm (ATerm t);
#define RS_RTupleRtuplesFromTerm(t) (_RS_RTupleRtuplesFromTerm(t))
#endif
#ifdef FAST_API
#define RS_RTupleRtuplesToTerm(arg) ((ATerm)(arg))
#else
ATerm _RS_RTupleRtuplesToTerm (RS_RTupleRtuples arg);
#define RS_RTupleRtuplesToTerm(arg) (_RS_RTupleRtuplesToTerm(arg))
#endif
#ifdef FAST_API
#define RS_StrCharFromTerm(t) ((RS_StrChar)(t))
#else
RS_StrChar _RS_StrCharFromTerm (ATerm t);
#define RS_StrCharFromTerm(t) (_RS_StrCharFromTerm(t))
#endif
#ifdef FAST_API
#define RS_StrCharToTerm(arg) ((ATerm)(arg))
#else
ATerm _RS_StrCharToTerm (RS_StrChar arg);
#define RS_StrCharToTerm(arg) (_RS_StrCharToTerm(arg))
#endif
#ifdef FAST_API
#define RS_StrConFromTerm(t) ((RS_StrCon)(t))
#else
RS_StrCon _RS_StrConFromTerm (ATerm t);
#define RS_StrConFromTerm(t) (_RS_StrConFromTerm(t))
#endif
#ifdef FAST_API
#define RS_StrConToTerm(arg) ((ATerm)(arg))
#else
ATerm _RS_StrConToTerm (RS_StrCon arg);
#define RS_StrConToTerm(arg) (_RS_StrConToTerm(arg))
#endif
#ifdef FAST_API
#define RS_BoolConFromTerm(t) ((RS_BoolCon)(t))
#else
RS_BoolCon _RS_BoolConFromTerm (ATerm t);
#define RS_BoolConFromTerm(t) (_RS_BoolConFromTerm(t))
#endif
#ifdef FAST_API
#define RS_BoolConToTerm(arg) ((ATerm)(arg))
#else
ATerm _RS_BoolConToTerm (RS_BoolCon arg);
#define RS_BoolConToTerm(arg) (_RS_BoolConToTerm(arg))
#endif
#ifdef FAST_API
#define RS_NatConFromTerm(t) ((RS_NatCon)(t))
#else
RS_NatCon _RS_NatConFromTerm (ATerm t);
#define RS_NatConFromTerm(t) (_RS_NatConFromTerm(t))
#endif
#ifdef FAST_API
#define RS_NatConToTerm(arg) ((ATerm)(arg))
#else
ATerm _RS_NatConToTerm (RS_NatCon arg);
#define RS_NatConToTerm(arg) (_RS_NatConToTerm(arg))
#endif
#ifdef FAST_API
#define RS_IdConFromTerm(t) ((RS_IdCon)(t))
#else
RS_IdCon _RS_IdConFromTerm (ATerm t);
#define RS_IdConFromTerm(t) (_RS_IdConFromTerm(t))
#endif
#ifdef FAST_API
#define RS_IdConToTerm(arg) ((ATerm)(arg))
#else
ATerm _RS_IdConToTerm (RS_IdCon arg);
#define RS_IdConToTerm(arg) (_RS_IdConToTerm(arg))
#endif
#ifdef FAST_API
#define RS_IntegerFromTerm(t) ((RS_Integer)(t))
#else
RS_Integer _RS_IntegerFromTerm (ATerm t);
#define RS_IntegerFromTerm(t) (_RS_IntegerFromTerm(t))
#endif
#ifdef FAST_API
#define RS_IntegerToTerm(arg) ((ATerm)(arg))
#else
ATerm _RS_IntegerToTerm (RS_Integer arg);
#define RS_IntegerToTerm(arg) (_RS_IntegerToTerm(arg))
#endif
#ifdef FAST_API
#define RS_LocationFromTerm(t) ((RS_Location)(t))
#else
RS_Location _RS_LocationFromTerm (ATerm t);
#define RS_LocationFromTerm(t) (_RS_LocationFromTerm(t))
#endif
#ifdef FAST_API
#define RS_LocationToTerm(arg) ((ATerm)(arg))
#else
ATerm _RS_LocationToTerm (RS_Location arg);
#define RS_LocationToTerm(arg) (_RS_LocationToTerm(arg))
#endif
#ifdef FAST_API
#define RS_AreaFromTerm(t) ((RS_Area)(t))
#else
RS_Area _RS_AreaFromTerm (ATerm t);
#define RS_AreaFromTerm(t) (_RS_AreaFromTerm(t))
#endif
#ifdef FAST_API
#define RS_AreaToTerm(arg) ((ATerm)(arg))
#else
ATerm _RS_AreaToTerm (RS_Area arg);
#define RS_AreaToTerm(arg) (_RS_AreaToTerm(arg))
#endif
#ifdef FAST_API
#define RS_getRElemElementsLength(arg) (ATgetLength((ATermList) (arg)))
#else
int _RS_getRElemElementsLength (RS_RElemElements arg);
#define RS_getRElemElementsLength(arg) (_RS_getRElemElementsLength(arg))
#endif
#ifdef FAST_API
#define RS_reverseRElemElements(arg) ((RS_RElemElements) ATreverse((ATermList) (arg)))
#else
RS_RElemElements _RS_reverseRElemElements (RS_RElemElements arg);
#define RS_reverseRElemElements(arg) (_RS_reverseRElemElements(arg))
#endif
#ifdef FAST_API
#define RS_appendRElemElements(arg, elem) ((RS_RElemElements) ATappend((ATermList) (arg), (ATerm) (((ATerm) elem))))
#else
RS_RElemElements _RS_appendRElemElements (RS_RElemElements arg,
					  RS_RElem elem);
#define RS_appendRElemElements(arg, elem) (_RS_appendRElemElements(arg, elem))
#endif
#ifdef FAST_API
#define RS_concatRElemElements(arg0, arg1) ((RS_RElemElements) ATconcat((ATermList) (arg0), (ATermList) (arg1)))
#else
RS_RElemElements _RS_concatRElemElements (RS_RElemElements arg0,
					  RS_RElemElements arg1);
#define RS_concatRElemElements(arg0, arg1) (_RS_concatRElemElements(arg0, arg1))
#endif
#ifdef FAST_API
#define RS_sliceRElemElements(arg, start, end) ((RS_RElemElements) ATgetSlice((ATermList) (arg), (start), (end)))
#else
RS_RElemElements _RS_sliceRElemElements (RS_RElemElements arg, int start,
					 int end);
#define RS_sliceRElemElements(arg, start, end) (_RS_sliceRElemElements(arg, start, end))
#endif
#ifdef FAST_API
#define RS_getRElemElementsRElemAt(arg, index) ((RS_RElem) (ATelementAt((ATermList) arg,index)))
#else
RS_RElem _RS_getRElemElementsRElemAt (RS_RElemElements arg, int index);
#define RS_getRElemElementsRElemAt(arg, index) (_RS_getRElemElementsRElemAt(arg, index))
#endif
#ifdef FAST_API
#define RS_replaceRElemElementsRElemAt(arg, elem, index) ((RS_RElemElements) ATreplace((ATermList) (arg), (ATerm) (((ATerm) elem)), (index)))
#else
RS_RElemElements _RS_replaceRElemElementsRElemAt (RS_RElemElements arg,
						  RS_RElem elem, int index);
#define RS_replaceRElemElementsRElemAt(arg, elem, index) (_RS_replaceRElemElementsRElemAt(arg, elem, index))
#endif
#ifdef FAST_API
#define RS_makeRElemElements2(elem1,  elem2) ((RS_RElemElements) ATmakeList2((ATerm) ((ATerm) elem1), (ATerm) (((ATerm) elem2))))
#else
RS_RElemElements _RS_makeRElemElements2 (RS_RElem elem1, RS_RElem elem2);
#define RS_makeRElemElements2(elem1,  elem2) (_RS_makeRElemElements2(elem1,  elem2))
#endif
#ifdef FAST_API
#define RS_makeRElemElements3(elem1, elem2,  elem3) ((RS_RElemElements) ATmakeList3((ATerm) ((ATerm) elem1), (ATerm) ((ATerm) elem2), (ATerm) (((ATerm) elem3))))
#else
RS_RElemElements _RS_makeRElemElements3 (RS_RElem elem1, RS_RElem elem2,
					 RS_RElem elem3);
#define RS_makeRElemElements3(elem1, elem2,  elem3) (_RS_makeRElemElements3(elem1, elem2,  elem3))
#endif
#ifdef FAST_API
#define RS_makeRElemElements4(elem1, elem2, elem3,  elem4) ((RS_RElemElements) ATmakeList4((ATerm) ((ATerm) elem1), (ATerm) ((ATerm) elem2), (ATerm) ((ATerm) elem3), (ATerm) (((ATerm) elem4))))
#else
RS_RElemElements _RS_makeRElemElements4 (RS_RElem elem1, RS_RElem elem2,
					 RS_RElem elem3, RS_RElem elem4);
#define RS_makeRElemElements4(elem1, elem2, elem3,  elem4) (_RS_makeRElemElements4(elem1, elem2, elem3,  elem4))
#endif
#ifdef FAST_API
#define RS_makeRElemElements5(elem1, elem2, elem3, elem4,  elem5) ((RS_RElemElements) ATmakeList5((ATerm) ((ATerm) elem1), (ATerm) ((ATerm) elem2), (ATerm) ((ATerm) elem3), (ATerm) ((ATerm) elem4), (ATerm) (((ATerm) elem5))))
#else
RS_RElemElements _RS_makeRElemElements5 (RS_RElem elem1, RS_RElem elem2,
					 RS_RElem elem3, RS_RElem elem4,
					 RS_RElem elem5);
#define RS_makeRElemElements5(elem1, elem2, elem3, elem4,  elem5) (_RS_makeRElemElements5(elem1, elem2, elem3, elem4,  elem5))
#endif
#ifdef FAST_API
#define RS_makeRElemElements6(elem1, elem2, elem3, elem4, elem5,  elem6) ((RS_RElemElements) ATmakeList6((ATerm) ((ATerm) elem1), (ATerm) ((ATerm) elem2), (ATerm) ((ATerm) elem3), (ATerm) ((ATerm) elem4), (ATerm) ((ATerm) elem5), (ATerm) (((ATerm) elem6))))
#else
RS_RElemElements _RS_makeRElemElements6 (RS_RElem elem1, RS_RElem elem2,
					 RS_RElem elem3, RS_RElem elem4,
					 RS_RElem elem5, RS_RElem elem6);
#define RS_makeRElemElements6(elem1, elem2, elem3, elem4, elem5,  elem6) (_RS_makeRElemElements6(elem1, elem2, elem3, elem4, elem5,  elem6))
#endif
#ifdef FAST_API
#define RS_getRTypeColumnTypesLength(arg) (ATgetLength((ATermList) (arg)))
#else
int _RS_getRTypeColumnTypesLength (RS_RTypeColumnTypes arg);
#define RS_getRTypeColumnTypesLength(arg) (_RS_getRTypeColumnTypesLength(arg))
#endif
#ifdef FAST_API
#define RS_reverseRTypeColumnTypes(arg) ((RS_RTypeColumnTypes) ATreverse((ATermList) (arg)))
#else
RS_RTypeColumnTypes _RS_reverseRTypeColumnTypes (RS_RTypeColumnTypes arg);
#define RS_reverseRTypeColumnTypes(arg) (_RS_reverseRTypeColumnTypes(arg))
#endif
#ifdef FAST_API
#define RS_appendRTypeColumnTypes(arg, elem) ((RS_RTypeColumnTypes) ATappend((ATermList) (arg), (ATerm) (((ATerm) elem))))
#else
RS_RTypeColumnTypes _RS_appendRTypeColumnTypes (RS_RTypeColumnTypes arg,
						RS_RType elem);
#define RS_appendRTypeColumnTypes(arg, elem) (_RS_appendRTypeColumnTypes(arg, elem))
#endif
#ifdef FAST_API
#define RS_concatRTypeColumnTypes(arg0, arg1) ((RS_RTypeColumnTypes) ATconcat((ATermList) (arg0), (ATermList) (arg1)))
#else
RS_RTypeColumnTypes _RS_concatRTypeColumnTypes (RS_RTypeColumnTypes arg0,
						RS_RTypeColumnTypes arg1);
#define RS_concatRTypeColumnTypes(arg0, arg1) (_RS_concatRTypeColumnTypes(arg0, arg1))
#endif
#ifdef FAST_API
#define RS_sliceRTypeColumnTypes(arg, start, end) ((RS_RTypeColumnTypes) ATgetSlice((ATermList) (arg), (start), (end)))
#else
RS_RTypeColumnTypes _RS_sliceRTypeColumnTypes (RS_RTypeColumnTypes arg,
					       int start, int end);
#define RS_sliceRTypeColumnTypes(arg, start, end) (_RS_sliceRTypeColumnTypes(arg, start, end))
#endif
#ifdef FAST_API
#define RS_getRTypeColumnTypesRTypeAt(arg, index) ((RS_RType) (ATelementAt((ATermList) arg,index)))
#else
RS_RType _RS_getRTypeColumnTypesRTypeAt (RS_RTypeColumnTypes arg, int index);
#define RS_getRTypeColumnTypesRTypeAt(arg, index) (_RS_getRTypeColumnTypesRTypeAt(arg, index))
#endif
#ifdef FAST_API
#define RS_replaceRTypeColumnTypesRTypeAt(arg, elem, index) ((RS_RTypeColumnTypes) ATreplace((ATermList) (arg), (ATerm) (((ATerm) elem)), (index)))
#else
RS_RTypeColumnTypes _RS_replaceRTypeColumnTypesRTypeAt (RS_RTypeColumnTypes
							arg, RS_RType elem,
							int index);
#define RS_replaceRTypeColumnTypesRTypeAt(arg, elem, index) (_RS_replaceRTypeColumnTypesRTypeAt(arg, elem, index))
#endif
#ifdef FAST_API
#define RS_makeRTypeColumnTypes2(elem1,  elem2) ((RS_RTypeColumnTypes) ATmakeList2((ATerm) ((ATerm) elem1), (ATerm) (((ATerm) elem2))))
#else
RS_RTypeColumnTypes _RS_makeRTypeColumnTypes2 (RS_RType elem1,
					       RS_RType elem2);
#define RS_makeRTypeColumnTypes2(elem1,  elem2) (_RS_makeRTypeColumnTypes2(elem1,  elem2))
#endif
#ifdef FAST_API
#define RS_makeRTypeColumnTypes3(elem1, elem2,  elem3) ((RS_RTypeColumnTypes) ATmakeList3((ATerm) ((ATerm) elem1), (ATerm) ((ATerm) elem2), (ATerm) (((ATerm) elem3))))
#else
RS_RTypeColumnTypes _RS_makeRTypeColumnTypes3 (RS_RType elem1, RS_RType elem2,
					       RS_RType elem3);
#define RS_makeRTypeColumnTypes3(elem1, elem2,  elem3) (_RS_makeRTypeColumnTypes3(elem1, elem2,  elem3))
#endif
#ifdef FAST_API
#define RS_makeRTypeColumnTypes4(elem1, elem2, elem3,  elem4) ((RS_RTypeColumnTypes) ATmakeList4((ATerm) ((ATerm) elem1), (ATerm) ((ATerm) elem2), (ATerm) ((ATerm) elem3), (ATerm) (((ATerm) elem4))))
#else
RS_RTypeColumnTypes _RS_makeRTypeColumnTypes4 (RS_RType elem1, RS_RType elem2,
					       RS_RType elem3,
					       RS_RType elem4);
#define RS_makeRTypeColumnTypes4(elem1, elem2, elem3,  elem4) (_RS_makeRTypeColumnTypes4(elem1, elem2, elem3,  elem4))
#endif
#ifdef FAST_API
#define RS_makeRTypeColumnTypes5(elem1, elem2, elem3, elem4,  elem5) ((RS_RTypeColumnTypes) ATmakeList5((ATerm) ((ATerm) elem1), (ATerm) ((ATerm) elem2), (ATerm) ((ATerm) elem3), (ATerm) ((ATerm) elem4), (ATerm) (((ATerm) elem5))))
#else
RS_RTypeColumnTypes _RS_makeRTypeColumnTypes5 (RS_RType elem1, RS_RType elem2,
					       RS_RType elem3, RS_RType elem4,
					       RS_RType elem5);
#define RS_makeRTypeColumnTypes5(elem1, elem2, elem3, elem4,  elem5) (_RS_makeRTypeColumnTypes5(elem1, elem2, elem3, elem4,  elem5))
#endif
#ifdef FAST_API
#define RS_makeRTypeColumnTypes6(elem1, elem2, elem3, elem4, elem5,  elem6) ((RS_RTypeColumnTypes) ATmakeList6((ATerm) ((ATerm) elem1), (ATerm) ((ATerm) elem2), (ATerm) ((ATerm) elem3), (ATerm) ((ATerm) elem4), (ATerm) ((ATerm) elem5), (ATerm) (((ATerm) elem6))))
#else
RS_RTypeColumnTypes _RS_makeRTypeColumnTypes6 (RS_RType elem1, RS_RType elem2,
					       RS_RType elem3, RS_RType elem4,
					       RS_RType elem5,
					       RS_RType elem6);
#define RS_makeRTypeColumnTypes6(elem1, elem2, elem3, elem4, elem5,  elem6) (_RS_makeRTypeColumnTypes6(elem1, elem2, elem3, elem4, elem5,  elem6))
#endif
#ifdef FAST_API
#define RS_getRTupleRtuplesLength(arg) (ATgetLength((ATermList) (arg)))
#else
int _RS_getRTupleRtuplesLength (RS_RTupleRtuples arg);
#define RS_getRTupleRtuplesLength(arg) (_RS_getRTupleRtuplesLength(arg))
#endif
#ifdef FAST_API
#define RS_reverseRTupleRtuples(arg) ((RS_RTupleRtuples) ATreverse((ATermList) (arg)))
#else
RS_RTupleRtuples _RS_reverseRTupleRtuples (RS_RTupleRtuples arg);
#define RS_reverseRTupleRtuples(arg) (_RS_reverseRTupleRtuples(arg))
#endif
#ifdef FAST_API
#define RS_appendRTupleRtuples(arg, elem) ((RS_RTupleRtuples) ATappend((ATermList) (arg), (ATerm) (((ATerm) elem))))
#else
RS_RTupleRtuples _RS_appendRTupleRtuples (RS_RTupleRtuples arg,
					  RS_RTuple elem);
#define RS_appendRTupleRtuples(arg, elem) (_RS_appendRTupleRtuples(arg, elem))
#endif
#ifdef FAST_API
#define RS_concatRTupleRtuples(arg0, arg1) ((RS_RTupleRtuples) ATconcat((ATermList) (arg0), (ATermList) (arg1)))
#else
RS_RTupleRtuples _RS_concatRTupleRtuples (RS_RTupleRtuples arg0,
					  RS_RTupleRtuples arg1);
#define RS_concatRTupleRtuples(arg0, arg1) (_RS_concatRTupleRtuples(arg0, arg1))
#endif
#ifdef FAST_API
#define RS_sliceRTupleRtuples(arg, start, end) ((RS_RTupleRtuples) ATgetSlice((ATermList) (arg), (start), (end)))
#else
RS_RTupleRtuples _RS_sliceRTupleRtuples (RS_RTupleRtuples arg, int start,
					 int end);
#define RS_sliceRTupleRtuples(arg, start, end) (_RS_sliceRTupleRtuples(arg, start, end))
#endif
#ifdef FAST_API
#define RS_getRTupleRtuplesRTupleAt(arg, index) ((RS_RTuple) (ATelementAt((ATermList) arg,index)))
#else
RS_RTuple _RS_getRTupleRtuplesRTupleAt (RS_RTupleRtuples arg, int index);
#define RS_getRTupleRtuplesRTupleAt(arg, index) (_RS_getRTupleRtuplesRTupleAt(arg, index))
#endif
#ifdef FAST_API
#define RS_replaceRTupleRtuplesRTupleAt(arg, elem, index) ((RS_RTupleRtuples) ATreplace((ATermList) (arg), (ATerm) (((ATerm) elem)), (index)))
#else
RS_RTupleRtuples _RS_replaceRTupleRtuplesRTupleAt (RS_RTupleRtuples arg,
						   RS_RTuple elem, int index);
#define RS_replaceRTupleRtuplesRTupleAt(arg, elem, index) (_RS_replaceRTupleRtuplesRTupleAt(arg, elem, index))
#endif
#ifdef FAST_API
#define RS_makeRTupleRtuples2(elem1,  elem2) ((RS_RTupleRtuples) ATmakeList2((ATerm) ((ATerm) elem1), (ATerm) (((ATerm) elem2))))
#else
RS_RTupleRtuples _RS_makeRTupleRtuples2 (RS_RTuple elem1, RS_RTuple elem2);
#define RS_makeRTupleRtuples2(elem1,  elem2) (_RS_makeRTupleRtuples2(elem1,  elem2))
#endif
#ifdef FAST_API
#define RS_makeRTupleRtuples3(elem1, elem2,  elem3) ((RS_RTupleRtuples) ATmakeList3((ATerm) ((ATerm) elem1), (ATerm) ((ATerm) elem2), (ATerm) (((ATerm) elem3))))
#else
RS_RTupleRtuples _RS_makeRTupleRtuples3 (RS_RTuple elem1, RS_RTuple elem2,
					 RS_RTuple elem3);
#define RS_makeRTupleRtuples3(elem1, elem2,  elem3) (_RS_makeRTupleRtuples3(elem1, elem2,  elem3))
#endif
#ifdef FAST_API
#define RS_makeRTupleRtuples4(elem1, elem2, elem3,  elem4) ((RS_RTupleRtuples) ATmakeList4((ATerm) ((ATerm) elem1), (ATerm) ((ATerm) elem2), (ATerm) ((ATerm) elem3), (ATerm) (((ATerm) elem4))))
#else
RS_RTupleRtuples _RS_makeRTupleRtuples4 (RS_RTuple elem1, RS_RTuple elem2,
					 RS_RTuple elem3, RS_RTuple elem4);
#define RS_makeRTupleRtuples4(elem1, elem2, elem3,  elem4) (_RS_makeRTupleRtuples4(elem1, elem2, elem3,  elem4))
#endif
#ifdef FAST_API
#define RS_makeRTupleRtuples5(elem1, elem2, elem3, elem4,  elem5) ((RS_RTupleRtuples) ATmakeList5((ATerm) ((ATerm) elem1), (ATerm) ((ATerm) elem2), (ATerm) ((ATerm) elem3), (ATerm) ((ATerm) elem4), (ATerm) (((ATerm) elem5))))
#else
RS_RTupleRtuples _RS_makeRTupleRtuples5 (RS_RTuple elem1, RS_RTuple elem2,
					 RS_RTuple elem3, RS_RTuple elem4,
					 RS_RTuple elem5);
#define RS_makeRTupleRtuples5(elem1, elem2, elem3, elem4,  elem5) (_RS_makeRTupleRtuples5(elem1, elem2, elem3, elem4,  elem5))
#endif
#ifdef FAST_API
#define RS_makeRTupleRtuples6(elem1, elem2, elem3, elem4, elem5,  elem6) ((RS_RTupleRtuples) ATmakeList6((ATerm) ((ATerm) elem1), (ATerm) ((ATerm) elem2), (ATerm) ((ATerm) elem3), (ATerm) ((ATerm) elem4), (ATerm) ((ATerm) elem5), (ATerm) (((ATerm) elem6))))
#else
RS_RTupleRtuples _RS_makeRTupleRtuples6 (RS_RTuple elem1, RS_RTuple elem2,
					 RS_RTuple elem3, RS_RTuple elem4,
					 RS_RTuple elem5, RS_RTuple elem6);
#define RS_makeRTupleRtuples6(elem1, elem2, elem3, elem4, elem5,  elem6) (_RS_makeRTupleRtuples6(elem1, elem2, elem3, elem4, elem5,  elem6))
#endif
RS_RElem RS_makeRElemInt (RS_Integer Integer);
RS_RElem RS_makeRElemStr (const char *StrCon);
RS_RElem RS_makeRElemBool (RS_BoolCon BoolCon);
RS_RElem RS_makeRElemLoc (RS_Location Location);
RS_RElem RS_makeRElemSet (RS_RElemElements elements);
RS_RElem RS_makeRElemBag (RS_RElemElements elements);
RS_RElem RS_makeRElemTuple (RS_RElemElements elements);
RS_RType RS_makeRTypeInt (void);
RS_RType RS_makeRTypeBool (void);
RS_RType RS_makeRTypeStr (void);
RS_RType RS_makeRTypeLoc (void);
RS_RType RS_makeRTypeTuple (RS_RTypeColumnTypes columnTypes);
RS_RType RS_makeRTypeSet (RS_RType elementType);
RS_RType RS_makeRTypeBag (RS_RType elementType);
RS_RType RS_makeRTypeRelation (RS_RTypeColumnTypes columnTypes);
RS_RType RS_makeRTypeUserDefined (RS_IdCon typeName);
RS_RType RS_makeRTypeParameter (RS_IdCon parameterName);
RS_RTuple RS_makeRTupleRtuple (RS_IdCon variable, RS_RType rtype,
			       RS_RElem value);
RS_RStore RS_makeRStoreRstore (RS_RTupleRtuples rtuples);
RS_RElemElements RS_makeRElemElementsEmpty (void);
RS_RElemElements RS_makeRElemElementsSingle (RS_RElem head);
RS_RElemElements RS_makeRElemElementsMany (RS_RElem head,
					   RS_RElemElements tail);
RS_RTypeColumnTypes RS_makeRTypeColumnTypesEmpty (void);
RS_RTypeColumnTypes RS_makeRTypeColumnTypesSingle (RS_RType head);
RS_RTypeColumnTypes RS_makeRTypeColumnTypesMany (RS_RType head,
						 RS_RTypeColumnTypes tail);
RS_RTupleRtuples RS_makeRTupleRtuplesEmpty (void);
RS_RTupleRtuples RS_makeRTupleRtuplesSingle (RS_RTuple head);
RS_RTupleRtuples RS_makeRTupleRtuplesMany (RS_RTuple head,
					   RS_RTupleRtuples tail);
RS_StrChar RS_makeStrCharStrChar (const char *string);
RS_StrCon RS_makeStrConStrCon (const char *string);
RS_BoolCon RS_makeBoolConTrue (void);
RS_BoolCon RS_makeBoolConFalse (void);
RS_NatCon RS_makeNatConNatCon (const char *string);
RS_IdCon RS_makeIdConIdCon (const char *string);
RS_Integer RS_makeIntegerNatCon (int NatCon);
RS_Integer RS_makeIntegerPositive (RS_Integer integer);
RS_Integer RS_makeIntegerNegative (RS_Integer integer);
RS_Location RS_makeLocationFile (const char *filename);
RS_Location RS_makeLocationArea (RS_Area Area);
RS_Location RS_makeLocationAreaInFile (const char *filename, RS_Area Area);
RS_Area RS_makeAreaArea (int beginLine, int beginColumn, int endLine,
			 int endColumn, int offset, int length);
#ifdef FAST_API
#define RS_isEqualRElem(arg0, arg1) (ATisEqual((ATerm)(arg0), (ATerm)(arg1)))
#else
ATbool _RS_isEqualRElem (RS_RElem arg0, RS_RElem arg1);
#define RS_isEqualRElem(arg0, arg1) (_RS_isEqualRElem(arg0, arg1))
#endif
#ifdef FAST_API
#define RS_isEqualRType(arg0, arg1) (ATisEqual((ATerm)(arg0), (ATerm)(arg1)))
#else
ATbool _RS_isEqualRType (RS_RType arg0, RS_RType arg1);
#define RS_isEqualRType(arg0, arg1) (_RS_isEqualRType(arg0, arg1))
#endif
#ifdef FAST_API
#define RS_isEqualRTuple(arg0, arg1) (ATisEqual((ATerm)(arg0), (ATerm)(arg1)))
#else
ATbool _RS_isEqualRTuple (RS_RTuple arg0, RS_RTuple arg1);
#define RS_isEqualRTuple(arg0, arg1) (_RS_isEqualRTuple(arg0, arg1))
#endif
#ifdef FAST_API
#define RS_isEqualRStore(arg0, arg1) (ATisEqual((ATerm)(arg0), (ATerm)(arg1)))
#else
ATbool _RS_isEqualRStore (RS_RStore arg0, RS_RStore arg1);
#define RS_isEqualRStore(arg0, arg1) (_RS_isEqualRStore(arg0, arg1))
#endif
#ifdef FAST_API
#define RS_isEqualRElemElements(arg0, arg1) (ATisEqual((ATerm)(arg0), (ATerm)(arg1)))
#else
ATbool _RS_isEqualRElemElements (RS_RElemElements arg0,
				 RS_RElemElements arg1);
#define RS_isEqualRElemElements(arg0, arg1) (_RS_isEqualRElemElements(arg0, arg1))
#endif
#ifdef FAST_API
#define RS_isEqualRTypeColumnTypes(arg0, arg1) (ATisEqual((ATerm)(arg0), (ATerm)(arg1)))
#else
ATbool _RS_isEqualRTypeColumnTypes (RS_RTypeColumnTypes arg0,
				    RS_RTypeColumnTypes arg1);
#define RS_isEqualRTypeColumnTypes(arg0, arg1) (_RS_isEqualRTypeColumnTypes(arg0, arg1))
#endif
#ifdef FAST_API
#define RS_isEqualRTupleRtuples(arg0, arg1) (ATisEqual((ATerm)(arg0), (ATerm)(arg1)))
#else
ATbool _RS_isEqualRTupleRtuples (RS_RTupleRtuples arg0,
				 RS_RTupleRtuples arg1);
#define RS_isEqualRTupleRtuples(arg0, arg1) (_RS_isEqualRTupleRtuples(arg0, arg1))
#endif
#ifdef FAST_API
#define RS_isEqualStrChar(arg0, arg1) (ATisEqual((ATerm)(arg0), (ATerm)(arg1)))
#else
ATbool _RS_isEqualStrChar (RS_StrChar arg0, RS_StrChar arg1);
#define RS_isEqualStrChar(arg0, arg1) (_RS_isEqualStrChar(arg0, arg1))
#endif
#ifdef FAST_API
#define RS_isEqualStrCon(arg0, arg1) (ATisEqual((ATerm)(arg0), (ATerm)(arg1)))
#else
ATbool _RS_isEqualStrCon (RS_StrCon arg0, RS_StrCon arg1);
#define RS_isEqualStrCon(arg0, arg1) (_RS_isEqualStrCon(arg0, arg1))
#endif
#ifdef FAST_API
#define RS_isEqualBoolCon(arg0, arg1) (ATisEqual((ATerm)(arg0), (ATerm)(arg1)))
#else
ATbool _RS_isEqualBoolCon (RS_BoolCon arg0, RS_BoolCon arg1);
#define RS_isEqualBoolCon(arg0, arg1) (_RS_isEqualBoolCon(arg0, arg1))
#endif
#ifdef FAST_API
#define RS_isEqualNatCon(arg0, arg1) (ATisEqual((ATerm)(arg0), (ATerm)(arg1)))
#else
ATbool _RS_isEqualNatCon (RS_NatCon arg0, RS_NatCon arg1);
#define RS_isEqualNatCon(arg0, arg1) (_RS_isEqualNatCon(arg0, arg1))
#endif
#ifdef FAST_API
#define RS_isEqualIdCon(arg0, arg1) (ATisEqual((ATerm)(arg0), (ATerm)(arg1)))
#else
ATbool _RS_isEqualIdCon (RS_IdCon arg0, RS_IdCon arg1);
#define RS_isEqualIdCon(arg0, arg1) (_RS_isEqualIdCon(arg0, arg1))
#endif
#ifdef FAST_API
#define RS_isEqualInteger(arg0, arg1) (ATisEqual((ATerm)(arg0), (ATerm)(arg1)))
#else
ATbool _RS_isEqualInteger (RS_Integer arg0, RS_Integer arg1);
#define RS_isEqualInteger(arg0, arg1) (_RS_isEqualInteger(arg0, arg1))
#endif
#ifdef FAST_API
#define RS_isEqualLocation(arg0, arg1) (ATisEqual((ATerm)(arg0), (ATerm)(arg1)))
#else
ATbool _RS_isEqualLocation (RS_Location arg0, RS_Location arg1);
#define RS_isEqualLocation(arg0, arg1) (_RS_isEqualLocation(arg0, arg1))
#endif
#ifdef FAST_API
#define RS_isEqualArea(arg0, arg1) (ATisEqual((ATerm)(arg0), (ATerm)(arg1)))
#else
ATbool _RS_isEqualArea (RS_Area arg0, RS_Area arg1);
#define RS_isEqualArea(arg0, arg1) (_RS_isEqualArea(arg0, arg1))
#endif
ATbool RS_isValidRElem (RS_RElem arg);
inline ATbool RS_isRElemInt (RS_RElem arg);
inline ATbool RS_isRElemStr (RS_RElem arg);
inline ATbool RS_isRElemBool (RS_RElem arg);
inline ATbool RS_isRElemLoc (RS_RElem arg);
inline ATbool RS_isRElemSet (RS_RElem arg);
inline ATbool RS_isRElemBag (RS_RElem arg);
inline ATbool RS_isRElemTuple (RS_RElem arg);
ATbool RS_hasRElemInteger (RS_RElem arg);
ATbool RS_hasRElemStrCon (RS_RElem arg);
ATbool RS_hasRElemBoolCon (RS_RElem arg);
ATbool RS_hasRElemLocation (RS_RElem arg);
ATbool RS_hasRElemElements (RS_RElem arg);
RS_Integer RS_getRElemInteger (RS_RElem arg);
char *RS_getRElemStrCon (RS_RElem arg);
RS_BoolCon RS_getRElemBoolCon (RS_RElem arg);
RS_Location RS_getRElemLocation (RS_RElem arg);
RS_RElemElements RS_getRElemElements (RS_RElem arg);
RS_RElem RS_setRElemInteger (RS_RElem arg, RS_Integer Integer);
RS_RElem RS_setRElemStrCon (RS_RElem arg, const char *StrCon);
RS_RElem RS_setRElemBoolCon (RS_RElem arg, RS_BoolCon BoolCon);
RS_RElem RS_setRElemLocation (RS_RElem arg, RS_Location Location);
RS_RElem RS_setRElemElements (RS_RElem arg, RS_RElemElements elements);
ATbool RS_isValidRType (RS_RType arg);
inline ATbool RS_isRTypeInt (RS_RType arg);
inline ATbool RS_isRTypeBool (RS_RType arg);
inline ATbool RS_isRTypeStr (RS_RType arg);
inline ATbool RS_isRTypeLoc (RS_RType arg);
inline ATbool RS_isRTypeTuple (RS_RType arg);
inline ATbool RS_isRTypeSet (RS_RType arg);
inline ATbool RS_isRTypeBag (RS_RType arg);
inline ATbool RS_isRTypeRelation (RS_RType arg);
inline ATbool RS_isRTypeUserDefined (RS_RType arg);
inline ATbool RS_isRTypeParameter (RS_RType arg);
ATbool RS_hasRTypeColumnTypes (RS_RType arg);
ATbool RS_hasRTypeElementType (RS_RType arg);
ATbool RS_hasRTypeTypeName (RS_RType arg);
ATbool RS_hasRTypeParameterName (RS_RType arg);
RS_RTypeColumnTypes RS_getRTypeColumnTypes (RS_RType arg);
RS_RType RS_getRTypeElementType (RS_RType arg);
RS_IdCon RS_getRTypeTypeName (RS_RType arg);
RS_IdCon RS_getRTypeParameterName (RS_RType arg);
RS_RType RS_setRTypeColumnTypes (RS_RType arg,
				 RS_RTypeColumnTypes columnTypes);
RS_RType RS_setRTypeElementType (RS_RType arg, RS_RType elementType);
RS_RType RS_setRTypeTypeName (RS_RType arg, RS_IdCon typeName);
RS_RType RS_setRTypeParameterName (RS_RType arg, RS_IdCon parameterName);
ATbool RS_isValidRTuple (RS_RTuple arg);
inline ATbool RS_isRTupleRtuple (RS_RTuple arg);
ATbool RS_hasRTupleVariable (RS_RTuple arg);
ATbool RS_hasRTupleRtype (RS_RTuple arg);
ATbool RS_hasRTupleValue (RS_RTuple arg);
RS_IdCon RS_getRTupleVariable (RS_RTuple arg);
RS_RType RS_getRTupleRtype (RS_RTuple arg);
RS_RElem RS_getRTupleValue (RS_RTuple arg);
RS_RTuple RS_setRTupleVariable (RS_RTuple arg, RS_IdCon variable);
RS_RTuple RS_setRTupleRtype (RS_RTuple arg, RS_RType rtype);
RS_RTuple RS_setRTupleValue (RS_RTuple arg, RS_RElem value);
ATbool RS_isValidRStore (RS_RStore arg);
inline ATbool RS_isRStoreRstore (RS_RStore arg);
ATbool RS_hasRStoreRtuples (RS_RStore arg);
RS_RTupleRtuples RS_getRStoreRtuples (RS_RStore arg);
RS_RStore RS_setRStoreRtuples (RS_RStore arg, RS_RTupleRtuples rtuples);
ATbool RS_isValidRElemElements (RS_RElemElements arg);
inline ATbool RS_isRElemElementsEmpty (RS_RElemElements arg);
inline ATbool RS_isRElemElementsSingle (RS_RElemElements arg);
inline ATbool RS_isRElemElementsMany (RS_RElemElements arg);
ATbool RS_hasRElemElementsHead (RS_RElemElements arg);
ATbool RS_hasRElemElementsTail (RS_RElemElements arg);
RS_RElem RS_getRElemElementsHead (RS_RElemElements arg);
RS_RElemElements RS_getRElemElementsTail (RS_RElemElements arg);
RS_RElemElements RS_setRElemElementsHead (RS_RElemElements arg,
					  RS_RElem head);
RS_RElemElements RS_setRElemElementsTail (RS_RElemElements arg,
					  RS_RElemElements tail);
ATbool RS_isValidRTypeColumnTypes (RS_RTypeColumnTypes arg);
inline ATbool RS_isRTypeColumnTypesEmpty (RS_RTypeColumnTypes arg);
inline ATbool RS_isRTypeColumnTypesSingle (RS_RTypeColumnTypes arg);
inline ATbool RS_isRTypeColumnTypesMany (RS_RTypeColumnTypes arg);
ATbool RS_hasRTypeColumnTypesHead (RS_RTypeColumnTypes arg);
ATbool RS_hasRTypeColumnTypesTail (RS_RTypeColumnTypes arg);
RS_RType RS_getRTypeColumnTypesHead (RS_RTypeColumnTypes arg);
RS_RTypeColumnTypes RS_getRTypeColumnTypesTail (RS_RTypeColumnTypes arg);
RS_RTypeColumnTypes RS_setRTypeColumnTypesHead (RS_RTypeColumnTypes arg,
						RS_RType head);
RS_RTypeColumnTypes RS_setRTypeColumnTypesTail (RS_RTypeColumnTypes arg,
						RS_RTypeColumnTypes tail);
ATbool RS_isValidRTupleRtuples (RS_RTupleRtuples arg);
inline ATbool RS_isRTupleRtuplesEmpty (RS_RTupleRtuples arg);
inline ATbool RS_isRTupleRtuplesSingle (RS_RTupleRtuples arg);
inline ATbool RS_isRTupleRtuplesMany (RS_RTupleRtuples arg);
ATbool RS_hasRTupleRtuplesHead (RS_RTupleRtuples arg);
ATbool RS_hasRTupleRtuplesTail (RS_RTupleRtuples arg);
RS_RTuple RS_getRTupleRtuplesHead (RS_RTupleRtuples arg);
RS_RTupleRtuples RS_getRTupleRtuplesTail (RS_RTupleRtuples arg);
RS_RTupleRtuples RS_setRTupleRtuplesHead (RS_RTupleRtuples arg,
					  RS_RTuple head);
RS_RTupleRtuples RS_setRTupleRtuplesTail (RS_RTupleRtuples arg,
					  RS_RTupleRtuples tail);
ATbool RS_isValidStrChar (RS_StrChar arg);
inline ATbool RS_isStrCharStrChar (RS_StrChar arg);
ATbool RS_hasStrCharString (RS_StrChar arg);
char *RS_getStrCharString (RS_StrChar arg);
RS_StrChar RS_setStrCharString (RS_StrChar arg, const char *string);
ATbool RS_isValidStrCon (RS_StrCon arg);
inline ATbool RS_isStrConStrCon (RS_StrCon arg);
ATbool RS_hasStrConString (RS_StrCon arg);
char *RS_getStrConString (RS_StrCon arg);
RS_StrCon RS_setStrConString (RS_StrCon arg, const char *string);
ATbool RS_isValidBoolCon (RS_BoolCon arg);
inline ATbool RS_isBoolConTrue (RS_BoolCon arg);
inline ATbool RS_isBoolConFalse (RS_BoolCon arg);
ATbool RS_isValidNatCon (RS_NatCon arg);
inline ATbool RS_isNatConNatCon (RS_NatCon arg);
ATbool RS_hasNatConString (RS_NatCon arg);
char *RS_getNatConString (RS_NatCon arg);
RS_NatCon RS_setNatConString (RS_NatCon arg, const char *string);
ATbool RS_isValidIdCon (RS_IdCon arg);
inline ATbool RS_isIdConIdCon (RS_IdCon arg);
ATbool RS_hasIdConString (RS_IdCon arg);
char *RS_getIdConString (RS_IdCon arg);
RS_IdCon RS_setIdConString (RS_IdCon arg, const char *string);
ATbool RS_isValidInteger (RS_Integer arg);
inline ATbool RS_isIntegerNatCon (RS_Integer arg);
inline ATbool RS_isIntegerPositive (RS_Integer arg);
inline ATbool RS_isIntegerNegative (RS_Integer arg);
ATbool RS_hasIntegerNatCon (RS_Integer arg);
ATbool RS_hasIntegerInteger (RS_Integer arg);
int RS_getIntegerNatCon (RS_Integer arg);
RS_Integer RS_getIntegerInteger (RS_Integer arg);
RS_Integer RS_setIntegerNatCon (RS_Integer arg, int NatCon);
RS_Integer RS_setIntegerInteger (RS_Integer arg, RS_Integer integer);
ATbool RS_isValidLocation (RS_Location arg);
inline ATbool RS_isLocationFile (RS_Location arg);
inline ATbool RS_isLocationArea (RS_Location arg);
inline ATbool RS_isLocationAreaInFile (RS_Location arg);
ATbool RS_hasLocationFilename (RS_Location arg);
ATbool RS_hasLocationArea (RS_Location arg);
char *RS_getLocationFilename (RS_Location arg);
RS_Area RS_getLocationArea (RS_Location arg);
RS_Location RS_setLocationFilename (RS_Location arg, const char *filename);
RS_Location RS_setLocationArea (RS_Location arg, RS_Area Area);
ATbool RS_isValidArea (RS_Area arg);
inline ATbool RS_isAreaArea (RS_Area arg);
ATbool RS_hasAreaBeginLine (RS_Area arg);
ATbool RS_hasAreaBeginColumn (RS_Area arg);
ATbool RS_hasAreaEndLine (RS_Area arg);
ATbool RS_hasAreaEndColumn (RS_Area arg);
ATbool RS_hasAreaOffset (RS_Area arg);
ATbool RS_hasAreaLength (RS_Area arg);
int RS_getAreaBeginLine (RS_Area arg);
int RS_getAreaBeginColumn (RS_Area arg);
int RS_getAreaEndLine (RS_Area arg);
int RS_getAreaEndColumn (RS_Area arg);
int RS_getAreaOffset (RS_Area arg);
int RS_getAreaLength (RS_Area arg);
RS_Area RS_setAreaBeginLine (RS_Area arg, int beginLine);
RS_Area RS_setAreaBeginColumn (RS_Area arg, int beginColumn);
RS_Area RS_setAreaEndLine (RS_Area arg, int endLine);
RS_Area RS_setAreaEndColumn (RS_Area arg, int endColumn);
RS_Area RS_setAreaOffset (RS_Area arg, int offset);
RS_Area RS_setAreaLength (RS_Area arg, int length);
RS_RElem RS_visitRElem (RS_RElem arg,
			RS_Integer (*acceptInteger) (RS_Integer),
			char *(*acceptStrCon) (char *),
			RS_BoolCon (*acceptBoolCon) (RS_BoolCon),
			RS_Location (*acceptLocation) (RS_Location),
			RS_RElemElements (*acceptElements)
			(RS_RElemElements));
RS_RType RS_visitRType (RS_RType arg,
			RS_RTypeColumnTypes (*acceptColumnTypes)
			(RS_RTypeColumnTypes),
			RS_IdCon (*acceptTypeName) (RS_IdCon),
			RS_IdCon (*acceptParameterName) (RS_IdCon));
RS_RTuple RS_visitRTuple (RS_RTuple arg,
			  RS_IdCon (*acceptVariable) (RS_IdCon),
			  RS_RType (*acceptRtype) (RS_RType),
			  RS_RElem (*acceptValue) (RS_RElem));
RS_RStore RS_visitRStore (RS_RStore arg,
			  RS_RTupleRtuples (*acceptRtuples)
			  (RS_RTupleRtuples));
RS_RElemElements RS_visitRElemElements (RS_RElemElements arg,
					RS_RElem (*acceptHead) (RS_RElem));
RS_RTypeColumnTypes RS_visitRTypeColumnTypes (RS_RTypeColumnTypes arg,
					      RS_RType (*acceptHead)
					      (RS_RType));
RS_RTupleRtuples RS_visitRTupleRtuples (RS_RTupleRtuples arg,
					RS_RTuple (*acceptHead) (RS_RTuple));
RS_StrChar RS_visitStrChar (RS_StrChar arg, char *(*acceptString) (char *));
RS_StrCon RS_visitStrCon (RS_StrCon arg, char *(*acceptString) (char *));
RS_BoolCon RS_visitBoolCon (RS_BoolCon arg);
RS_NatCon RS_visitNatCon (RS_NatCon arg, char *(*acceptString) (char *));
RS_IdCon RS_visitIdCon (RS_IdCon arg, char *(*acceptString) (char *));
RS_Integer RS_visitInteger (RS_Integer arg, int (*acceptNatCon) (int));
RS_Location RS_visitLocation (RS_Location arg,
			      char *(*acceptFilename) (char *),
			      RS_Area (*acceptArea) (RS_Area));
RS_Area RS_visitArea (RS_Area arg, int (*acceptBeginLine) (int),
		      int (*acceptBeginColumn) (int),
		      int (*acceptEndLine) (int),
		      int (*acceptEndColumn) (int), int (*acceptOffset) (int),
		      int (*acceptLength) (int));

#endif /* _RSTORE_H */
