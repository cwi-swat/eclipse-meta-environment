#ifndef _PARSEDRSTORE_H
#define _PARSEDRSTORE_H

#include <stdlib.h>
#include <string.h>
#include <aterm1.h>
#include "ParsedRStore_dict.h"

typedef struct _PRS_OptLayout *PRS_OptLayout;
typedef struct _PRS_Layout *PRS_Layout;
typedef struct _PRS_LexLayoutList *PRS_LexLayoutList;
typedef struct _PRS_RElem *PRS_RElem;
typedef struct _PRS_RType *PRS_RType;
typedef struct _PRS_RTuple *PRS_RTuple;
typedef struct _PRS_RStore *PRS_RStore;
typedef struct _PRS_Start *PRS_Start;
typedef struct _PRS_RElemElements *PRS_RElemElements;
typedef struct _PRS_RTypeColumnTypes *PRS_RTypeColumnTypes;
typedef struct _PRS_RTupleRtuples *PRS_RTupleRtuples;
typedef struct _PRS_LexStrChar *PRS_LexStrChar;
typedef struct _PRS_StrChar *PRS_StrChar;
typedef struct _PRS_LexStrCon *PRS_LexStrCon;
typedef struct _PRS_StrCon *PRS_StrCon;
typedef struct _PRS_LexStrCharChars *PRS_LexStrCharChars;
typedef struct _PRS_BoolCon *PRS_BoolCon;
typedef struct _PRS_LexNatCon *PRS_LexNatCon;
typedef struct _PRS_NatCon *PRS_NatCon;
typedef struct _PRS_LexIdCon *PRS_LexIdCon;
typedef struct _PRS_IdCon *PRS_IdCon;
typedef struct _PRS_Integer *PRS_Integer;
typedef struct _PRS_LexLayout *PRS_LexLayout;
typedef struct _PRS_Location *PRS_Location;
typedef struct _PRS_Area *PRS_Area;

#ifdef FAST_API
#define PRS_initParsedRStoreApi() (init_ParsedRStore_dict())
#else
void _PRS_initParsedRStoreApi (void);
#define PRS_initParsedRStoreApi() (_PRS_initParsedRStoreApi())
#endif

#ifdef FAST_API
#define PRS_protectOptLayout(arg) (ATprotect((ATerm*)((void*) (arg))))
#else
void _PRS_protectOptLayout (PRS_OptLayout * arg);
#define PRS_protectOptLayout(arg) (_PRS_protectOptLayout(arg))
#endif
#ifdef FAST_API
#define PRS_unprotectOptLayout(arg) (ATunprotect((ATerm*)((void*) (arg))))
#else
void _PRS_unprotectOptLayout (PRS_OptLayout * arg);
#define PRS_unprotectOptLayout(arg) (_PRS_unprotectOptLayout(arg))
#endif
#ifdef FAST_API
#define PRS_protectLayout(arg) (ATprotect((ATerm*)((void*) (arg))))
#else
void _PRS_protectLayout (PRS_Layout * arg);
#define PRS_protectLayout(arg) (_PRS_protectLayout(arg))
#endif
#ifdef FAST_API
#define PRS_unprotectLayout(arg) (ATunprotect((ATerm*)((void*) (arg))))
#else
void _PRS_unprotectLayout (PRS_Layout * arg);
#define PRS_unprotectLayout(arg) (_PRS_unprotectLayout(arg))
#endif
#ifdef FAST_API
#define PRS_protectLexLayoutList(arg) (ATprotect((ATerm*)((void*) (arg))))
#else
void _PRS_protectLexLayoutList (PRS_LexLayoutList * arg);
#define PRS_protectLexLayoutList(arg) (_PRS_protectLexLayoutList(arg))
#endif
#ifdef FAST_API
#define PRS_unprotectLexLayoutList(arg) (ATunprotect((ATerm*)((void*) (arg))))
#else
void _PRS_unprotectLexLayoutList (PRS_LexLayoutList * arg);
#define PRS_unprotectLexLayoutList(arg) (_PRS_unprotectLexLayoutList(arg))
#endif
#ifdef FAST_API
#define PRS_protectRElem(arg) (ATprotect((ATerm*)((void*) (arg))))
#else
void _PRS_protectRElem (PRS_RElem * arg);
#define PRS_protectRElem(arg) (_PRS_protectRElem(arg))
#endif
#ifdef FAST_API
#define PRS_unprotectRElem(arg) (ATunprotect((ATerm*)((void*) (arg))))
#else
void _PRS_unprotectRElem (PRS_RElem * arg);
#define PRS_unprotectRElem(arg) (_PRS_unprotectRElem(arg))
#endif
#ifdef FAST_API
#define PRS_protectRType(arg) (ATprotect((ATerm*)((void*) (arg))))
#else
void _PRS_protectRType (PRS_RType * arg);
#define PRS_protectRType(arg) (_PRS_protectRType(arg))
#endif
#ifdef FAST_API
#define PRS_unprotectRType(arg) (ATunprotect((ATerm*)((void*) (arg))))
#else
void _PRS_unprotectRType (PRS_RType * arg);
#define PRS_unprotectRType(arg) (_PRS_unprotectRType(arg))
#endif
#ifdef FAST_API
#define PRS_protectRTuple(arg) (ATprotect((ATerm*)((void*) (arg))))
#else
void _PRS_protectRTuple (PRS_RTuple * arg);
#define PRS_protectRTuple(arg) (_PRS_protectRTuple(arg))
#endif
#ifdef FAST_API
#define PRS_unprotectRTuple(arg) (ATunprotect((ATerm*)((void*) (arg))))
#else
void _PRS_unprotectRTuple (PRS_RTuple * arg);
#define PRS_unprotectRTuple(arg) (_PRS_unprotectRTuple(arg))
#endif
#ifdef FAST_API
#define PRS_protectRStore(arg) (ATprotect((ATerm*)((void*) (arg))))
#else
void _PRS_protectRStore (PRS_RStore * arg);
#define PRS_protectRStore(arg) (_PRS_protectRStore(arg))
#endif
#ifdef FAST_API
#define PRS_unprotectRStore(arg) (ATunprotect((ATerm*)((void*) (arg))))
#else
void _PRS_unprotectRStore (PRS_RStore * arg);
#define PRS_unprotectRStore(arg) (_PRS_unprotectRStore(arg))
#endif
#ifdef FAST_API
#define PRS_protectStart(arg) (ATprotect((ATerm*)((void*) (arg))))
#else
void _PRS_protectStart (PRS_Start * arg);
#define PRS_protectStart(arg) (_PRS_protectStart(arg))
#endif
#ifdef FAST_API
#define PRS_unprotectStart(arg) (ATunprotect((ATerm*)((void*) (arg))))
#else
void _PRS_unprotectStart (PRS_Start * arg);
#define PRS_unprotectStart(arg) (_PRS_unprotectStart(arg))
#endif
#ifdef FAST_API
#define PRS_protectRElemElements(arg) (ATprotect((ATerm*)((void*) (arg))))
#else
void _PRS_protectRElemElements (PRS_RElemElements * arg);
#define PRS_protectRElemElements(arg) (_PRS_protectRElemElements(arg))
#endif
#ifdef FAST_API
#define PRS_unprotectRElemElements(arg) (ATunprotect((ATerm*)((void*) (arg))))
#else
void _PRS_unprotectRElemElements (PRS_RElemElements * arg);
#define PRS_unprotectRElemElements(arg) (_PRS_unprotectRElemElements(arg))
#endif
#ifdef FAST_API
#define PRS_protectRTypeColumnTypes(arg) (ATprotect((ATerm*)((void*) (arg))))
#else
void _PRS_protectRTypeColumnTypes (PRS_RTypeColumnTypes * arg);
#define PRS_protectRTypeColumnTypes(arg) (_PRS_protectRTypeColumnTypes(arg))
#endif
#ifdef FAST_API
#define PRS_unprotectRTypeColumnTypes(arg) (ATunprotect((ATerm*)((void*) (arg))))
#else
void _PRS_unprotectRTypeColumnTypes (PRS_RTypeColumnTypes * arg);
#define PRS_unprotectRTypeColumnTypes(arg) (_PRS_unprotectRTypeColumnTypes(arg))
#endif
#ifdef FAST_API
#define PRS_protectRTupleRtuples(arg) (ATprotect((ATerm*)((void*) (arg))))
#else
void _PRS_protectRTupleRtuples (PRS_RTupleRtuples * arg);
#define PRS_protectRTupleRtuples(arg) (_PRS_protectRTupleRtuples(arg))
#endif
#ifdef FAST_API
#define PRS_unprotectRTupleRtuples(arg) (ATunprotect((ATerm*)((void*) (arg))))
#else
void _PRS_unprotectRTupleRtuples (PRS_RTupleRtuples * arg);
#define PRS_unprotectRTupleRtuples(arg) (_PRS_unprotectRTupleRtuples(arg))
#endif
#ifdef FAST_API
#define PRS_protectLexStrChar(arg) (ATprotect((ATerm*)((void*) (arg))))
#else
void _PRS_protectLexStrChar (PRS_LexStrChar * arg);
#define PRS_protectLexStrChar(arg) (_PRS_protectLexStrChar(arg))
#endif
#ifdef FAST_API
#define PRS_unprotectLexStrChar(arg) (ATunprotect((ATerm*)((void*) (arg))))
#else
void _PRS_unprotectLexStrChar (PRS_LexStrChar * arg);
#define PRS_unprotectLexStrChar(arg) (_PRS_unprotectLexStrChar(arg))
#endif
#ifdef FAST_API
#define PRS_protectStrChar(arg) (ATprotect((ATerm*)((void*) (arg))))
#else
void _PRS_protectStrChar (PRS_StrChar * arg);
#define PRS_protectStrChar(arg) (_PRS_protectStrChar(arg))
#endif
#ifdef FAST_API
#define PRS_unprotectStrChar(arg) (ATunprotect((ATerm*)((void*) (arg))))
#else
void _PRS_unprotectStrChar (PRS_StrChar * arg);
#define PRS_unprotectStrChar(arg) (_PRS_unprotectStrChar(arg))
#endif
#ifdef FAST_API
#define PRS_protectLexStrCon(arg) (ATprotect((ATerm*)((void*) (arg))))
#else
void _PRS_protectLexStrCon (PRS_LexStrCon * arg);
#define PRS_protectLexStrCon(arg) (_PRS_protectLexStrCon(arg))
#endif
#ifdef FAST_API
#define PRS_unprotectLexStrCon(arg) (ATunprotect((ATerm*)((void*) (arg))))
#else
void _PRS_unprotectLexStrCon (PRS_LexStrCon * arg);
#define PRS_unprotectLexStrCon(arg) (_PRS_unprotectLexStrCon(arg))
#endif
#ifdef FAST_API
#define PRS_protectStrCon(arg) (ATprotect((ATerm*)((void*) (arg))))
#else
void _PRS_protectStrCon (PRS_StrCon * arg);
#define PRS_protectStrCon(arg) (_PRS_protectStrCon(arg))
#endif
#ifdef FAST_API
#define PRS_unprotectStrCon(arg) (ATunprotect((ATerm*)((void*) (arg))))
#else
void _PRS_unprotectStrCon (PRS_StrCon * arg);
#define PRS_unprotectStrCon(arg) (_PRS_unprotectStrCon(arg))
#endif
#ifdef FAST_API
#define PRS_protectLexStrCharChars(arg) (ATprotect((ATerm*)((void*) (arg))))
#else
void _PRS_protectLexStrCharChars (PRS_LexStrCharChars * arg);
#define PRS_protectLexStrCharChars(arg) (_PRS_protectLexStrCharChars(arg))
#endif
#ifdef FAST_API
#define PRS_unprotectLexStrCharChars(arg) (ATunprotect((ATerm*)((void*) (arg))))
#else
void _PRS_unprotectLexStrCharChars (PRS_LexStrCharChars * arg);
#define PRS_unprotectLexStrCharChars(arg) (_PRS_unprotectLexStrCharChars(arg))
#endif
#ifdef FAST_API
#define PRS_protectBoolCon(arg) (ATprotect((ATerm*)((void*) (arg))))
#else
void _PRS_protectBoolCon (PRS_BoolCon * arg);
#define PRS_protectBoolCon(arg) (_PRS_protectBoolCon(arg))
#endif
#ifdef FAST_API
#define PRS_unprotectBoolCon(arg) (ATunprotect((ATerm*)((void*) (arg))))
#else
void _PRS_unprotectBoolCon (PRS_BoolCon * arg);
#define PRS_unprotectBoolCon(arg) (_PRS_unprotectBoolCon(arg))
#endif
#ifdef FAST_API
#define PRS_protectLexNatCon(arg) (ATprotect((ATerm*)((void*) (arg))))
#else
void _PRS_protectLexNatCon (PRS_LexNatCon * arg);
#define PRS_protectLexNatCon(arg) (_PRS_protectLexNatCon(arg))
#endif
#ifdef FAST_API
#define PRS_unprotectLexNatCon(arg) (ATunprotect((ATerm*)((void*) (arg))))
#else
void _PRS_unprotectLexNatCon (PRS_LexNatCon * arg);
#define PRS_unprotectLexNatCon(arg) (_PRS_unprotectLexNatCon(arg))
#endif
#ifdef FAST_API
#define PRS_protectNatCon(arg) (ATprotect((ATerm*)((void*) (arg))))
#else
void _PRS_protectNatCon (PRS_NatCon * arg);
#define PRS_protectNatCon(arg) (_PRS_protectNatCon(arg))
#endif
#ifdef FAST_API
#define PRS_unprotectNatCon(arg) (ATunprotect((ATerm*)((void*) (arg))))
#else
void _PRS_unprotectNatCon (PRS_NatCon * arg);
#define PRS_unprotectNatCon(arg) (_PRS_unprotectNatCon(arg))
#endif
#ifdef FAST_API
#define PRS_protectLexIdCon(arg) (ATprotect((ATerm*)((void*) (arg))))
#else
void _PRS_protectLexIdCon (PRS_LexIdCon * arg);
#define PRS_protectLexIdCon(arg) (_PRS_protectLexIdCon(arg))
#endif
#ifdef FAST_API
#define PRS_unprotectLexIdCon(arg) (ATunprotect((ATerm*)((void*) (arg))))
#else
void _PRS_unprotectLexIdCon (PRS_LexIdCon * arg);
#define PRS_unprotectLexIdCon(arg) (_PRS_unprotectLexIdCon(arg))
#endif
#ifdef FAST_API
#define PRS_protectIdCon(arg) (ATprotect((ATerm*)((void*) (arg))))
#else
void _PRS_protectIdCon (PRS_IdCon * arg);
#define PRS_protectIdCon(arg) (_PRS_protectIdCon(arg))
#endif
#ifdef FAST_API
#define PRS_unprotectIdCon(arg) (ATunprotect((ATerm*)((void*) (arg))))
#else
void _PRS_unprotectIdCon (PRS_IdCon * arg);
#define PRS_unprotectIdCon(arg) (_PRS_unprotectIdCon(arg))
#endif
#ifdef FAST_API
#define PRS_protectInteger(arg) (ATprotect((ATerm*)((void*) (arg))))
#else
void _PRS_protectInteger (PRS_Integer * arg);
#define PRS_protectInteger(arg) (_PRS_protectInteger(arg))
#endif
#ifdef FAST_API
#define PRS_unprotectInteger(arg) (ATunprotect((ATerm*)((void*) (arg))))
#else
void _PRS_unprotectInteger (PRS_Integer * arg);
#define PRS_unprotectInteger(arg) (_PRS_unprotectInteger(arg))
#endif
#ifdef FAST_API
#define PRS_protectLexLayout(arg) (ATprotect((ATerm*)((void*) (arg))))
#else
void _PRS_protectLexLayout (PRS_LexLayout * arg);
#define PRS_protectLexLayout(arg) (_PRS_protectLexLayout(arg))
#endif
#ifdef FAST_API
#define PRS_unprotectLexLayout(arg) (ATunprotect((ATerm*)((void*) (arg))))
#else
void _PRS_unprotectLexLayout (PRS_LexLayout * arg);
#define PRS_unprotectLexLayout(arg) (_PRS_unprotectLexLayout(arg))
#endif
#ifdef FAST_API
#define PRS_protectLocation(arg) (ATprotect((ATerm*)((void*) (arg))))
#else
void _PRS_protectLocation (PRS_Location * arg);
#define PRS_protectLocation(arg) (_PRS_protectLocation(arg))
#endif
#ifdef FAST_API
#define PRS_unprotectLocation(arg) (ATunprotect((ATerm*)((void*) (arg))))
#else
void _PRS_unprotectLocation (PRS_Location * arg);
#define PRS_unprotectLocation(arg) (_PRS_unprotectLocation(arg))
#endif
#ifdef FAST_API
#define PRS_protectArea(arg) (ATprotect((ATerm*)((void*) (arg))))
#else
void _PRS_protectArea (PRS_Area * arg);
#define PRS_protectArea(arg) (_PRS_protectArea(arg))
#endif
#ifdef FAST_API
#define PRS_unprotectArea(arg) (ATunprotect((ATerm*)((void*) (arg))))
#else
void _PRS_unprotectArea (PRS_Area * arg);
#define PRS_unprotectArea(arg) (_PRS_unprotectArea(arg))
#endif
#ifdef FAST_API
#define PRS_OptLayoutFromTerm(t) ((PRS_OptLayout)(t))
#else
PRS_OptLayout _PRS_OptLayoutFromTerm (ATerm t);
#define PRS_OptLayoutFromTerm(t) (_PRS_OptLayoutFromTerm(t))
#endif
#ifdef FAST_API
#define PRS_OptLayoutToTerm(arg) ((ATerm)(arg))
#else
ATerm _PRS_OptLayoutToTerm (PRS_OptLayout arg);
#define PRS_OptLayoutToTerm(arg) (_PRS_OptLayoutToTerm(arg))
#endif
#ifdef FAST_API
#define PRS_LayoutFromTerm(t) ((PRS_Layout)(t))
#else
PRS_Layout _PRS_LayoutFromTerm (ATerm t);
#define PRS_LayoutFromTerm(t) (_PRS_LayoutFromTerm(t))
#endif
#ifdef FAST_API
#define PRS_LayoutToTerm(arg) ((ATerm)(arg))
#else
ATerm _PRS_LayoutToTerm (PRS_Layout arg);
#define PRS_LayoutToTerm(arg) (_PRS_LayoutToTerm(arg))
#endif
#ifdef FAST_API
#define PRS_LexLayoutListFromTerm(t) ((PRS_LexLayoutList)(t))
#else
PRS_LexLayoutList _PRS_LexLayoutListFromTerm (ATerm t);
#define PRS_LexLayoutListFromTerm(t) (_PRS_LexLayoutListFromTerm(t))
#endif
#ifdef FAST_API
#define PRS_LexLayoutListToTerm(arg) ((ATerm)(arg))
#else
ATerm _PRS_LexLayoutListToTerm (PRS_LexLayoutList arg);
#define PRS_LexLayoutListToTerm(arg) (_PRS_LexLayoutListToTerm(arg))
#endif
#ifdef FAST_API
#define PRS_RElemFromTerm(t) ((PRS_RElem)(t))
#else
PRS_RElem _PRS_RElemFromTerm (ATerm t);
#define PRS_RElemFromTerm(t) (_PRS_RElemFromTerm(t))
#endif
#ifdef FAST_API
#define PRS_RElemToTerm(arg) ((ATerm)(arg))
#else
ATerm _PRS_RElemToTerm (PRS_RElem arg);
#define PRS_RElemToTerm(arg) (_PRS_RElemToTerm(arg))
#endif
#ifdef FAST_API
#define PRS_RTypeFromTerm(t) ((PRS_RType)(t))
#else
PRS_RType _PRS_RTypeFromTerm (ATerm t);
#define PRS_RTypeFromTerm(t) (_PRS_RTypeFromTerm(t))
#endif
#ifdef FAST_API
#define PRS_RTypeToTerm(arg) ((ATerm)(arg))
#else
ATerm _PRS_RTypeToTerm (PRS_RType arg);
#define PRS_RTypeToTerm(arg) (_PRS_RTypeToTerm(arg))
#endif
#ifdef FAST_API
#define PRS_RTupleFromTerm(t) ((PRS_RTuple)(t))
#else
PRS_RTuple _PRS_RTupleFromTerm (ATerm t);
#define PRS_RTupleFromTerm(t) (_PRS_RTupleFromTerm(t))
#endif
#ifdef FAST_API
#define PRS_RTupleToTerm(arg) ((ATerm)(arg))
#else
ATerm _PRS_RTupleToTerm (PRS_RTuple arg);
#define PRS_RTupleToTerm(arg) (_PRS_RTupleToTerm(arg))
#endif
#ifdef FAST_API
#define PRS_RStoreFromTerm(t) ((PRS_RStore)(t))
#else
PRS_RStore _PRS_RStoreFromTerm (ATerm t);
#define PRS_RStoreFromTerm(t) (_PRS_RStoreFromTerm(t))
#endif
#ifdef FAST_API
#define PRS_RStoreToTerm(arg) ((ATerm)(arg))
#else
ATerm _PRS_RStoreToTerm (PRS_RStore arg);
#define PRS_RStoreToTerm(arg) (_PRS_RStoreToTerm(arg))
#endif
#ifdef FAST_API
#define PRS_StartFromTerm(t) ((PRS_Start)(t))
#else
PRS_Start _PRS_StartFromTerm (ATerm t);
#define PRS_StartFromTerm(t) (_PRS_StartFromTerm(t))
#endif
#ifdef FAST_API
#define PRS_StartToTerm(arg) ((ATerm)(arg))
#else
ATerm _PRS_StartToTerm (PRS_Start arg);
#define PRS_StartToTerm(arg) (_PRS_StartToTerm(arg))
#endif
#ifdef FAST_API
#define PRS_RElemElementsFromTerm(t) ((PRS_RElemElements)(t))
#else
PRS_RElemElements _PRS_RElemElementsFromTerm (ATerm t);
#define PRS_RElemElementsFromTerm(t) (_PRS_RElemElementsFromTerm(t))
#endif
#ifdef FAST_API
#define PRS_RElemElementsToTerm(arg) ((ATerm)(arg))
#else
ATerm _PRS_RElemElementsToTerm (PRS_RElemElements arg);
#define PRS_RElemElementsToTerm(arg) (_PRS_RElemElementsToTerm(arg))
#endif
#ifdef FAST_API
#define PRS_RTypeColumnTypesFromTerm(t) ((PRS_RTypeColumnTypes)(t))
#else
PRS_RTypeColumnTypes _PRS_RTypeColumnTypesFromTerm (ATerm t);
#define PRS_RTypeColumnTypesFromTerm(t) (_PRS_RTypeColumnTypesFromTerm(t))
#endif
#ifdef FAST_API
#define PRS_RTypeColumnTypesToTerm(arg) ((ATerm)(arg))
#else
ATerm _PRS_RTypeColumnTypesToTerm (PRS_RTypeColumnTypes arg);
#define PRS_RTypeColumnTypesToTerm(arg) (_PRS_RTypeColumnTypesToTerm(arg))
#endif
#ifdef FAST_API
#define PRS_RTupleRtuplesFromTerm(t) ((PRS_RTupleRtuples)(t))
#else
PRS_RTupleRtuples _PRS_RTupleRtuplesFromTerm (ATerm t);
#define PRS_RTupleRtuplesFromTerm(t) (_PRS_RTupleRtuplesFromTerm(t))
#endif
#ifdef FAST_API
#define PRS_RTupleRtuplesToTerm(arg) ((ATerm)(arg))
#else
ATerm _PRS_RTupleRtuplesToTerm (PRS_RTupleRtuples arg);
#define PRS_RTupleRtuplesToTerm(arg) (_PRS_RTupleRtuplesToTerm(arg))
#endif
#ifdef FAST_API
#define PRS_LexStrCharFromTerm(t) ((PRS_LexStrChar)(t))
#else
PRS_LexStrChar _PRS_LexStrCharFromTerm (ATerm t);
#define PRS_LexStrCharFromTerm(t) (_PRS_LexStrCharFromTerm(t))
#endif
#ifdef FAST_API
#define PRS_LexStrCharToTerm(arg) ((ATerm)(arg))
#else
ATerm _PRS_LexStrCharToTerm (PRS_LexStrChar arg);
#define PRS_LexStrCharToTerm(arg) (_PRS_LexStrCharToTerm(arg))
#endif
#ifdef FAST_API
#define PRS_StrCharFromTerm(t) ((PRS_StrChar)(t))
#else
PRS_StrChar _PRS_StrCharFromTerm (ATerm t);
#define PRS_StrCharFromTerm(t) (_PRS_StrCharFromTerm(t))
#endif
#ifdef FAST_API
#define PRS_StrCharToTerm(arg) ((ATerm)(arg))
#else
ATerm _PRS_StrCharToTerm (PRS_StrChar arg);
#define PRS_StrCharToTerm(arg) (_PRS_StrCharToTerm(arg))
#endif
#ifdef FAST_API
#define PRS_LexStrConFromTerm(t) ((PRS_LexStrCon)(t))
#else
PRS_LexStrCon _PRS_LexStrConFromTerm (ATerm t);
#define PRS_LexStrConFromTerm(t) (_PRS_LexStrConFromTerm(t))
#endif
#ifdef FAST_API
#define PRS_LexStrConToTerm(arg) ((ATerm)(arg))
#else
ATerm _PRS_LexStrConToTerm (PRS_LexStrCon arg);
#define PRS_LexStrConToTerm(arg) (_PRS_LexStrConToTerm(arg))
#endif
#ifdef FAST_API
#define PRS_StrConFromTerm(t) ((PRS_StrCon)(t))
#else
PRS_StrCon _PRS_StrConFromTerm (ATerm t);
#define PRS_StrConFromTerm(t) (_PRS_StrConFromTerm(t))
#endif
#ifdef FAST_API
#define PRS_StrConToTerm(arg) ((ATerm)(arg))
#else
ATerm _PRS_StrConToTerm (PRS_StrCon arg);
#define PRS_StrConToTerm(arg) (_PRS_StrConToTerm(arg))
#endif
#ifdef FAST_API
#define PRS_LexStrCharCharsFromTerm(t) ((PRS_LexStrCharChars)(t))
#else
PRS_LexStrCharChars _PRS_LexStrCharCharsFromTerm (ATerm t);
#define PRS_LexStrCharCharsFromTerm(t) (_PRS_LexStrCharCharsFromTerm(t))
#endif
#ifdef FAST_API
#define PRS_LexStrCharCharsToTerm(arg) ((ATerm)(arg))
#else
ATerm _PRS_LexStrCharCharsToTerm (PRS_LexStrCharChars arg);
#define PRS_LexStrCharCharsToTerm(arg) (_PRS_LexStrCharCharsToTerm(arg))
#endif
#ifdef FAST_API
#define PRS_BoolConFromTerm(t) ((PRS_BoolCon)(t))
#else
PRS_BoolCon _PRS_BoolConFromTerm (ATerm t);
#define PRS_BoolConFromTerm(t) (_PRS_BoolConFromTerm(t))
#endif
#ifdef FAST_API
#define PRS_BoolConToTerm(arg) ((ATerm)(arg))
#else
ATerm _PRS_BoolConToTerm (PRS_BoolCon arg);
#define PRS_BoolConToTerm(arg) (_PRS_BoolConToTerm(arg))
#endif
#ifdef FAST_API
#define PRS_LexNatConFromTerm(t) ((PRS_LexNatCon)(t))
#else
PRS_LexNatCon _PRS_LexNatConFromTerm (ATerm t);
#define PRS_LexNatConFromTerm(t) (_PRS_LexNatConFromTerm(t))
#endif
#ifdef FAST_API
#define PRS_LexNatConToTerm(arg) ((ATerm)(arg))
#else
ATerm _PRS_LexNatConToTerm (PRS_LexNatCon arg);
#define PRS_LexNatConToTerm(arg) (_PRS_LexNatConToTerm(arg))
#endif
#ifdef FAST_API
#define PRS_NatConFromTerm(t) ((PRS_NatCon)(t))
#else
PRS_NatCon _PRS_NatConFromTerm (ATerm t);
#define PRS_NatConFromTerm(t) (_PRS_NatConFromTerm(t))
#endif
#ifdef FAST_API
#define PRS_NatConToTerm(arg) ((ATerm)(arg))
#else
ATerm _PRS_NatConToTerm (PRS_NatCon arg);
#define PRS_NatConToTerm(arg) (_PRS_NatConToTerm(arg))
#endif
#ifdef FAST_API
#define PRS_LexIdConFromTerm(t) ((PRS_LexIdCon)(t))
#else
PRS_LexIdCon _PRS_LexIdConFromTerm (ATerm t);
#define PRS_LexIdConFromTerm(t) (_PRS_LexIdConFromTerm(t))
#endif
#ifdef FAST_API
#define PRS_LexIdConToTerm(arg) ((ATerm)(arg))
#else
ATerm _PRS_LexIdConToTerm (PRS_LexIdCon arg);
#define PRS_LexIdConToTerm(arg) (_PRS_LexIdConToTerm(arg))
#endif
#ifdef FAST_API
#define PRS_IdConFromTerm(t) ((PRS_IdCon)(t))
#else
PRS_IdCon _PRS_IdConFromTerm (ATerm t);
#define PRS_IdConFromTerm(t) (_PRS_IdConFromTerm(t))
#endif
#ifdef FAST_API
#define PRS_IdConToTerm(arg) ((ATerm)(arg))
#else
ATerm _PRS_IdConToTerm (PRS_IdCon arg);
#define PRS_IdConToTerm(arg) (_PRS_IdConToTerm(arg))
#endif
#ifdef FAST_API
#define PRS_IntegerFromTerm(t) ((PRS_Integer)(t))
#else
PRS_Integer _PRS_IntegerFromTerm (ATerm t);
#define PRS_IntegerFromTerm(t) (_PRS_IntegerFromTerm(t))
#endif
#ifdef FAST_API
#define PRS_IntegerToTerm(arg) ((ATerm)(arg))
#else
ATerm _PRS_IntegerToTerm (PRS_Integer arg);
#define PRS_IntegerToTerm(arg) (_PRS_IntegerToTerm(arg))
#endif
#ifdef FAST_API
#define PRS_LexLayoutFromTerm(t) ((PRS_LexLayout)(t))
#else
PRS_LexLayout _PRS_LexLayoutFromTerm (ATerm t);
#define PRS_LexLayoutFromTerm(t) (_PRS_LexLayoutFromTerm(t))
#endif
#ifdef FAST_API
#define PRS_LexLayoutToTerm(arg) ((ATerm)(arg))
#else
ATerm _PRS_LexLayoutToTerm (PRS_LexLayout arg);
#define PRS_LexLayoutToTerm(arg) (_PRS_LexLayoutToTerm(arg))
#endif
#ifdef FAST_API
#define PRS_LocationFromTerm(t) ((PRS_Location)(t))
#else
PRS_Location _PRS_LocationFromTerm (ATerm t);
#define PRS_LocationFromTerm(t) (_PRS_LocationFromTerm(t))
#endif
#ifdef FAST_API
#define PRS_LocationToTerm(arg) ((ATerm)(arg))
#else
ATerm _PRS_LocationToTerm (PRS_Location arg);
#define PRS_LocationToTerm(arg) (_PRS_LocationToTerm(arg))
#endif
#ifdef FAST_API
#define PRS_AreaFromTerm(t) ((PRS_Area)(t))
#else
PRS_Area _PRS_AreaFromTerm (ATerm t);
#define PRS_AreaFromTerm(t) (_PRS_AreaFromTerm(t))
#endif
#ifdef FAST_API
#define PRS_AreaToTerm(arg) ((ATerm)(arg))
#else
ATerm _PRS_AreaToTerm (PRS_Area arg);
#define PRS_AreaToTerm(arg) (_PRS_AreaToTerm(arg))
#endif
#ifdef FAST_API
#define PRS_getLexLayoutListLength(arg) (ATgetLength((ATermList) (arg)))
#else
int _PRS_getLexLayoutListLength (PRS_LexLayoutList arg);
#define PRS_getLexLayoutListLength(arg) (_PRS_getLexLayoutListLength(arg))
#endif
#ifdef FAST_API
#define PRS_reverseLexLayoutList(arg) ((PRS_LexLayoutList) ATreverse((ATermList) (arg)))
#else
PRS_LexLayoutList _PRS_reverseLexLayoutList (PRS_LexLayoutList arg);
#define PRS_reverseLexLayoutList(arg) (_PRS_reverseLexLayoutList(arg))
#endif
#ifdef FAST_API
#define PRS_appendLexLayoutList(arg, elem) ((PRS_LexLayoutList) ATappend((ATermList) (arg), (ATerm) (((ATerm) elem))))
#else
PRS_LexLayoutList _PRS_appendLexLayoutList (PRS_LexLayoutList arg,
					    PRS_LexLayout elem);
#define PRS_appendLexLayoutList(arg, elem) (_PRS_appendLexLayoutList(arg, elem))
#endif
#ifdef FAST_API
#define PRS_concatLexLayoutList(arg0, arg1) ((PRS_LexLayoutList) ATconcat((ATermList) (arg0), (ATermList) (arg1)))
#else
PRS_LexLayoutList _PRS_concatLexLayoutList (PRS_LexLayoutList arg0,
					    PRS_LexLayoutList arg1);
#define PRS_concatLexLayoutList(arg0, arg1) (_PRS_concatLexLayoutList(arg0, arg1))
#endif
#ifdef FAST_API
#define PRS_sliceLexLayoutList(arg, start, end) ((PRS_LexLayoutList) ATgetSlice((ATermList) (arg), (start), (end)))
#else
PRS_LexLayoutList _PRS_sliceLexLayoutList (PRS_LexLayoutList arg, int start,
					   int end);
#define PRS_sliceLexLayoutList(arg, start, end) (_PRS_sliceLexLayoutList(arg, start, end))
#endif
#ifdef FAST_API
#define PRS_getLexLayoutListLexLayoutAt(arg, index) ((PRS_LexLayout) (ATelementAt((ATermList) arg,index)))
#else
PRS_LexLayout _PRS_getLexLayoutListLexLayoutAt (PRS_LexLayoutList arg,
						int index);
#define PRS_getLexLayoutListLexLayoutAt(arg, index) (_PRS_getLexLayoutListLexLayoutAt(arg, index))
#endif
#ifdef FAST_API
#define PRS_replaceLexLayoutListLexLayoutAt(arg, elem, index) ((PRS_LexLayoutList) ATreplace((ATermList) (arg), (ATerm) (((ATerm) elem)), (index)))
#else
PRS_LexLayoutList _PRS_replaceLexLayoutListLexLayoutAt (PRS_LexLayoutList arg,
							PRS_LexLayout elem,
							int index);
#define PRS_replaceLexLayoutListLexLayoutAt(arg, elem, index) (_PRS_replaceLexLayoutListLexLayoutAt(arg, elem, index))
#endif
#ifdef FAST_API
#define PRS_makeLexLayoutList2(elem1,  elem2) ((PRS_LexLayoutList) ATmakeList2((ATerm) ((ATerm) elem1), (ATerm) (((ATerm) elem2))))
#else
PRS_LexLayoutList _PRS_makeLexLayoutList2 (PRS_LexLayout elem1,
					   PRS_LexLayout elem2);
#define PRS_makeLexLayoutList2(elem1,  elem2) (_PRS_makeLexLayoutList2(elem1,  elem2))
#endif
#ifdef FAST_API
#define PRS_makeLexLayoutList3(elem1, elem2,  elem3) ((PRS_LexLayoutList) ATmakeList3((ATerm) ((ATerm) elem1), (ATerm) ((ATerm) elem2), (ATerm) (((ATerm) elem3))))
#else
PRS_LexLayoutList _PRS_makeLexLayoutList3 (PRS_LexLayout elem1,
					   PRS_LexLayout elem2,
					   PRS_LexLayout elem3);
#define PRS_makeLexLayoutList3(elem1, elem2,  elem3) (_PRS_makeLexLayoutList3(elem1, elem2,  elem3))
#endif
#ifdef FAST_API
#define PRS_makeLexLayoutList4(elem1, elem2, elem3,  elem4) ((PRS_LexLayoutList) ATmakeList4((ATerm) ((ATerm) elem1), (ATerm) ((ATerm) elem2), (ATerm) ((ATerm) elem3), (ATerm) (((ATerm) elem4))))
#else
PRS_LexLayoutList _PRS_makeLexLayoutList4 (PRS_LexLayout elem1,
					   PRS_LexLayout elem2,
					   PRS_LexLayout elem3,
					   PRS_LexLayout elem4);
#define PRS_makeLexLayoutList4(elem1, elem2, elem3,  elem4) (_PRS_makeLexLayoutList4(elem1, elem2, elem3,  elem4))
#endif
#ifdef FAST_API
#define PRS_makeLexLayoutList5(elem1, elem2, elem3, elem4,  elem5) ((PRS_LexLayoutList) ATmakeList5((ATerm) ((ATerm) elem1), (ATerm) ((ATerm) elem2), (ATerm) ((ATerm) elem3), (ATerm) ((ATerm) elem4), (ATerm) (((ATerm) elem5))))
#else
PRS_LexLayoutList _PRS_makeLexLayoutList5 (PRS_LexLayout elem1,
					   PRS_LexLayout elem2,
					   PRS_LexLayout elem3,
					   PRS_LexLayout elem4,
					   PRS_LexLayout elem5);
#define PRS_makeLexLayoutList5(elem1, elem2, elem3, elem4,  elem5) (_PRS_makeLexLayoutList5(elem1, elem2, elem3, elem4,  elem5))
#endif
#ifdef FAST_API
#define PRS_makeLexLayoutList6(elem1, elem2, elem3, elem4, elem5,  elem6) ((PRS_LexLayoutList) ATmakeList6((ATerm) ((ATerm) elem1), (ATerm) ((ATerm) elem2), (ATerm) ((ATerm) elem3), (ATerm) ((ATerm) elem4), (ATerm) ((ATerm) elem5), (ATerm) (((ATerm) elem6))))
#else
PRS_LexLayoutList _PRS_makeLexLayoutList6 (PRS_LexLayout elem1,
					   PRS_LexLayout elem2,
					   PRS_LexLayout elem3,
					   PRS_LexLayout elem4,
					   PRS_LexLayout elem5,
					   PRS_LexLayout elem6);
#define PRS_makeLexLayoutList6(elem1, elem2, elem3, elem4, elem5,  elem6) (_PRS_makeLexLayoutList6(elem1, elem2, elem3, elem4, elem5,  elem6))
#endif
#ifdef FAST_API
#define PRS_getRElemElementsLength(arg) ((ATisEmpty((ATermList) (arg)) ? 0 : (ATgetLength((ATermList) (arg)) / 4) + 1))
#else
int _PRS_getRElemElementsLength (PRS_RElemElements arg);
#define PRS_getRElemElementsLength(arg) (_PRS_getRElemElementsLength(arg))
#endif
PRS_RElemElements PRS_reverseRElemElements (PRS_RElemElements arg);
PRS_RElemElements PRS_appendRElemElements (PRS_RElemElements arg0,
					   PRS_OptLayout wsAfterHead,
					   PRS_OptLayout wsAfterSep,
					   PRS_RElem arg1);
PRS_RElemElements PRS_concatRElemElements (PRS_RElemElements arg0,
					   PRS_OptLayout wsAfterHead,
					   PRS_OptLayout wsAfterSep,
					   PRS_RElemElements arg1);
#ifdef FAST_API
#define PRS_sliceRElemElements(arg, start, end) ((PRS_RElemElements) ATgetSlice((ATermList) (arg), (start * 4), (end * 4)))
#else
PRS_RElemElements _PRS_sliceRElemElements (PRS_RElemElements arg, int start,
					   int end);
#define PRS_sliceRElemElements(arg, start, end) (_PRS_sliceRElemElements(arg, start, end))
#endif
#ifdef FAST_API
#define PRS_getRElemElementsRElemAt(arg, index) ((PRS_RElem) (ATelementAt((ATermList) arg,index * 4)))
#else
PRS_RElem _PRS_getRElemElementsRElemAt (PRS_RElemElements arg, int index);
#define PRS_getRElemElementsRElemAt(arg, index) (_PRS_getRElemElementsRElemAt(arg, index))
#endif
#ifdef FAST_API
#define PRS_replaceRElemElementsRElemAt(arg, elem, index) ((PRS_RElemElements) ATreplace((ATermList) (arg), (ATerm) (((ATerm) elem)), (index * 4)))
#else
PRS_RElemElements _PRS_replaceRElemElementsRElemAt (PRS_RElemElements arg,
						    PRS_RElem elem,
						    int index);
#define PRS_replaceRElemElementsRElemAt(arg, elem, index) (_PRS_replaceRElemElementsRElemAt(arg, elem, index))
#endif
PRS_RElemElements PRS_makeRElemElements2 (PRS_OptLayout wsAfterHead,
					  PRS_OptLayout wsAfterSep,
					  PRS_RElem elem1, PRS_RElem elem2);
PRS_RElemElements PRS_makeRElemElements3 (PRS_OptLayout wsAfterHead,
					  PRS_OptLayout wsAfterSep,
					  PRS_RElem elem1, PRS_RElem elem2,
					  PRS_RElem elem3);
PRS_RElemElements PRS_makeRElemElements4 (PRS_OptLayout wsAfterHead,
					  PRS_OptLayout wsAfterSep,
					  PRS_RElem elem1, PRS_RElem elem2,
					  PRS_RElem elem3, PRS_RElem elem4);
PRS_RElemElements PRS_makeRElemElements5 (PRS_OptLayout wsAfterHead,
					  PRS_OptLayout wsAfterSep,
					  PRS_RElem elem1, PRS_RElem elem2,
					  PRS_RElem elem3, PRS_RElem elem4,
					  PRS_RElem elem5);
PRS_RElemElements PRS_makeRElemElements6 (PRS_OptLayout wsAfterHead,
					  PRS_OptLayout wsAfterSep,
					  PRS_RElem elem1, PRS_RElem elem2,
					  PRS_RElem elem3, PRS_RElem elem4,
					  PRS_RElem elem5, PRS_RElem elem6);
#ifdef FAST_API
#define PRS_getRTypeColumnTypesLength(arg) ((ATisEmpty((ATermList) (arg)) ? 0 : (ATgetLength((ATermList) (arg)) / 4) + 1))
#else
int _PRS_getRTypeColumnTypesLength (PRS_RTypeColumnTypes arg);
#define PRS_getRTypeColumnTypesLength(arg) (_PRS_getRTypeColumnTypesLength(arg))
#endif
PRS_RTypeColumnTypes PRS_reverseRTypeColumnTypes (PRS_RTypeColumnTypes arg);
PRS_RTypeColumnTypes PRS_appendRTypeColumnTypes (PRS_RTypeColumnTypes arg0,
						 PRS_OptLayout wsAfterHead,
						 PRS_OptLayout wsAfterSep,
						 PRS_RType arg1);
PRS_RTypeColumnTypes PRS_concatRTypeColumnTypes (PRS_RTypeColumnTypes arg0,
						 PRS_OptLayout wsAfterHead,
						 PRS_OptLayout wsAfterSep,
						 PRS_RTypeColumnTypes arg1);
#ifdef FAST_API
#define PRS_sliceRTypeColumnTypes(arg, start, end) ((PRS_RTypeColumnTypes) ATgetSlice((ATermList) (arg), (start * 4), (end * 4)))
#else
PRS_RTypeColumnTypes _PRS_sliceRTypeColumnTypes (PRS_RTypeColumnTypes arg,
						 int start, int end);
#define PRS_sliceRTypeColumnTypes(arg, start, end) (_PRS_sliceRTypeColumnTypes(arg, start, end))
#endif
#ifdef FAST_API
#define PRS_getRTypeColumnTypesRTypeAt(arg, index) ((PRS_RType) (ATelementAt((ATermList) arg,index * 4)))
#else
PRS_RType _PRS_getRTypeColumnTypesRTypeAt (PRS_RTypeColumnTypes arg,
					   int index);
#define PRS_getRTypeColumnTypesRTypeAt(arg, index) (_PRS_getRTypeColumnTypesRTypeAt(arg, index))
#endif
#ifdef FAST_API
#define PRS_replaceRTypeColumnTypesRTypeAt(arg, elem, index) ((PRS_RTypeColumnTypes) ATreplace((ATermList) (arg), (ATerm) (((ATerm) elem)), (index * 4)))
#else
PRS_RTypeColumnTypes _PRS_replaceRTypeColumnTypesRTypeAt (PRS_RTypeColumnTypes
							  arg, PRS_RType elem,
							  int index);
#define PRS_replaceRTypeColumnTypesRTypeAt(arg, elem, index) (_PRS_replaceRTypeColumnTypesRTypeAt(arg, elem, index))
#endif
PRS_RTypeColumnTypes PRS_makeRTypeColumnTypes2 (PRS_OptLayout wsAfterHead,
						PRS_OptLayout wsAfterSep,
						PRS_RType elem1,
						PRS_RType elem2);
PRS_RTypeColumnTypes PRS_makeRTypeColumnTypes3 (PRS_OptLayout wsAfterHead,
						PRS_OptLayout wsAfterSep,
						PRS_RType elem1,
						PRS_RType elem2,
						PRS_RType elem3);
PRS_RTypeColumnTypes PRS_makeRTypeColumnTypes4 (PRS_OptLayout wsAfterHead,
						PRS_OptLayout wsAfterSep,
						PRS_RType elem1,
						PRS_RType elem2,
						PRS_RType elem3,
						PRS_RType elem4);
PRS_RTypeColumnTypes PRS_makeRTypeColumnTypes5 (PRS_OptLayout wsAfterHead,
						PRS_OptLayout wsAfterSep,
						PRS_RType elem1,
						PRS_RType elem2,
						PRS_RType elem3,
						PRS_RType elem4,
						PRS_RType elem5);
PRS_RTypeColumnTypes PRS_makeRTypeColumnTypes6 (PRS_OptLayout wsAfterHead,
						PRS_OptLayout wsAfterSep,
						PRS_RType elem1,
						PRS_RType elem2,
						PRS_RType elem3,
						PRS_RType elem4,
						PRS_RType elem5,
						PRS_RType elem6);
#ifdef FAST_API
#define PRS_getRTupleRtuplesLength(arg) ((ATisEmpty((ATermList) (arg)) ? 0 : (ATgetLength((ATermList) (arg)) / 4) + 1))
#else
int _PRS_getRTupleRtuplesLength (PRS_RTupleRtuples arg);
#define PRS_getRTupleRtuplesLength(arg) (_PRS_getRTupleRtuplesLength(arg))
#endif
PRS_RTupleRtuples PRS_reverseRTupleRtuples (PRS_RTupleRtuples arg);
PRS_RTupleRtuples PRS_appendRTupleRtuples (PRS_RTupleRtuples arg0,
					   PRS_OptLayout wsAfterHead,
					   PRS_OptLayout wsAfterSep,
					   PRS_RTuple arg1);
PRS_RTupleRtuples PRS_concatRTupleRtuples (PRS_RTupleRtuples arg0,
					   PRS_OptLayout wsAfterHead,
					   PRS_OptLayout wsAfterSep,
					   PRS_RTupleRtuples arg1);
#ifdef FAST_API
#define PRS_sliceRTupleRtuples(arg, start, end) ((PRS_RTupleRtuples) ATgetSlice((ATermList) (arg), (start * 4), (end * 4)))
#else
PRS_RTupleRtuples _PRS_sliceRTupleRtuples (PRS_RTupleRtuples arg, int start,
					   int end);
#define PRS_sliceRTupleRtuples(arg, start, end) (_PRS_sliceRTupleRtuples(arg, start, end))
#endif
#ifdef FAST_API
#define PRS_getRTupleRtuplesRTupleAt(arg, index) ((PRS_RTuple) (ATelementAt((ATermList) arg,index * 4)))
#else
PRS_RTuple _PRS_getRTupleRtuplesRTupleAt (PRS_RTupleRtuples arg, int index);
#define PRS_getRTupleRtuplesRTupleAt(arg, index) (_PRS_getRTupleRtuplesRTupleAt(arg, index))
#endif
#ifdef FAST_API
#define PRS_replaceRTupleRtuplesRTupleAt(arg, elem, index) ((PRS_RTupleRtuples) ATreplace((ATermList) (arg), (ATerm) (((ATerm) elem)), (index * 4)))
#else
PRS_RTupleRtuples _PRS_replaceRTupleRtuplesRTupleAt (PRS_RTupleRtuples arg,
						     PRS_RTuple elem,
						     int index);
#define PRS_replaceRTupleRtuplesRTupleAt(arg, elem, index) (_PRS_replaceRTupleRtuplesRTupleAt(arg, elem, index))
#endif
PRS_RTupleRtuples PRS_makeRTupleRtuples2 (PRS_OptLayout wsAfterHead,
					  PRS_OptLayout wsAfterSep,
					  PRS_RTuple elem1, PRS_RTuple elem2);
PRS_RTupleRtuples PRS_makeRTupleRtuples3 (PRS_OptLayout wsAfterHead,
					  PRS_OptLayout wsAfterSep,
					  PRS_RTuple elem1, PRS_RTuple elem2,
					  PRS_RTuple elem3);
PRS_RTupleRtuples PRS_makeRTupleRtuples4 (PRS_OptLayout wsAfterHead,
					  PRS_OptLayout wsAfterSep,
					  PRS_RTuple elem1, PRS_RTuple elem2,
					  PRS_RTuple elem3, PRS_RTuple elem4);
PRS_RTupleRtuples PRS_makeRTupleRtuples5 (PRS_OptLayout wsAfterHead,
					  PRS_OptLayout wsAfterSep,
					  PRS_RTuple elem1, PRS_RTuple elem2,
					  PRS_RTuple elem3, PRS_RTuple elem4,
					  PRS_RTuple elem5);
PRS_RTupleRtuples PRS_makeRTupleRtuples6 (PRS_OptLayout wsAfterHead,
					  PRS_OptLayout wsAfterSep,
					  PRS_RTuple elem1, PRS_RTuple elem2,
					  PRS_RTuple elem3, PRS_RTuple elem4,
					  PRS_RTuple elem5, PRS_RTuple elem6);
#ifdef FAST_API
#define PRS_getLexStrCharCharsLength(arg) (ATgetLength((ATermList) (arg)))
#else
int _PRS_getLexStrCharCharsLength (PRS_LexStrCharChars arg);
#define PRS_getLexStrCharCharsLength(arg) (_PRS_getLexStrCharCharsLength(arg))
#endif
#ifdef FAST_API
#define PRS_reverseLexStrCharChars(arg) ((PRS_LexStrCharChars) ATreverse((ATermList) (arg)))
#else
PRS_LexStrCharChars _PRS_reverseLexStrCharChars (PRS_LexStrCharChars arg);
#define PRS_reverseLexStrCharChars(arg) (_PRS_reverseLexStrCharChars(arg))
#endif
#ifdef FAST_API
#define PRS_appendLexStrCharChars(arg, elem) ((PRS_LexStrCharChars) ATappend((ATermList) (arg), (ATerm) (((ATerm) elem))))
#else
PRS_LexStrCharChars _PRS_appendLexStrCharChars (PRS_LexStrCharChars arg,
						PRS_LexStrChar elem);
#define PRS_appendLexStrCharChars(arg, elem) (_PRS_appendLexStrCharChars(arg, elem))
#endif
#ifdef FAST_API
#define PRS_concatLexStrCharChars(arg0, arg1) ((PRS_LexStrCharChars) ATconcat((ATermList) (arg0), (ATermList) (arg1)))
#else
PRS_LexStrCharChars _PRS_concatLexStrCharChars (PRS_LexStrCharChars arg0,
						PRS_LexStrCharChars arg1);
#define PRS_concatLexStrCharChars(arg0, arg1) (_PRS_concatLexStrCharChars(arg0, arg1))
#endif
#ifdef FAST_API
#define PRS_sliceLexStrCharChars(arg, start, end) ((PRS_LexStrCharChars) ATgetSlice((ATermList) (arg), (start), (end)))
#else
PRS_LexStrCharChars _PRS_sliceLexStrCharChars (PRS_LexStrCharChars arg,
					       int start, int end);
#define PRS_sliceLexStrCharChars(arg, start, end) (_PRS_sliceLexStrCharChars(arg, start, end))
#endif
#ifdef FAST_API
#define PRS_getLexStrCharCharsLexStrCharAt(arg, index) ((PRS_LexStrChar) (ATelementAt((ATermList) arg,index)))
#else
PRS_LexStrChar _PRS_getLexStrCharCharsLexStrCharAt (PRS_LexStrCharChars arg,
						    int index);
#define PRS_getLexStrCharCharsLexStrCharAt(arg, index) (_PRS_getLexStrCharCharsLexStrCharAt(arg, index))
#endif
#ifdef FAST_API
#define PRS_replaceLexStrCharCharsLexStrCharAt(arg, elem, index) ((PRS_LexStrCharChars) ATreplace((ATermList) (arg), (ATerm) (((ATerm) elem)), (index)))
#else
PRS_LexStrCharChars
_PRS_replaceLexStrCharCharsLexStrCharAt (PRS_LexStrCharChars arg,
					 PRS_LexStrChar elem, int index);
#define PRS_replaceLexStrCharCharsLexStrCharAt(arg, elem, index) (_PRS_replaceLexStrCharCharsLexStrCharAt(arg, elem, index))
#endif
#ifdef FAST_API
#define PRS_makeLexStrCharChars2(elem1,  elem2) ((PRS_LexStrCharChars) ATmakeList2((ATerm) ((ATerm) elem1), (ATerm) (((ATerm) elem2))))
#else
PRS_LexStrCharChars _PRS_makeLexStrCharChars2 (PRS_LexStrChar elem1,
					       PRS_LexStrChar elem2);
#define PRS_makeLexStrCharChars2(elem1,  elem2) (_PRS_makeLexStrCharChars2(elem1,  elem2))
#endif
#ifdef FAST_API
#define PRS_makeLexStrCharChars3(elem1, elem2,  elem3) ((PRS_LexStrCharChars) ATmakeList3((ATerm) ((ATerm) elem1), (ATerm) ((ATerm) elem2), (ATerm) (((ATerm) elem3))))
#else
PRS_LexStrCharChars _PRS_makeLexStrCharChars3 (PRS_LexStrChar elem1,
					       PRS_LexStrChar elem2,
					       PRS_LexStrChar elem3);
#define PRS_makeLexStrCharChars3(elem1, elem2,  elem3) (_PRS_makeLexStrCharChars3(elem1, elem2,  elem3))
#endif
#ifdef FAST_API
#define PRS_makeLexStrCharChars4(elem1, elem2, elem3,  elem4) ((PRS_LexStrCharChars) ATmakeList4((ATerm) ((ATerm) elem1), (ATerm) ((ATerm) elem2), (ATerm) ((ATerm) elem3), (ATerm) (((ATerm) elem4))))
#else
PRS_LexStrCharChars _PRS_makeLexStrCharChars4 (PRS_LexStrChar elem1,
					       PRS_LexStrChar elem2,
					       PRS_LexStrChar elem3,
					       PRS_LexStrChar elem4);
#define PRS_makeLexStrCharChars4(elem1, elem2, elem3,  elem4) (_PRS_makeLexStrCharChars4(elem1, elem2, elem3,  elem4))
#endif
#ifdef FAST_API
#define PRS_makeLexStrCharChars5(elem1, elem2, elem3, elem4,  elem5) ((PRS_LexStrCharChars) ATmakeList5((ATerm) ((ATerm) elem1), (ATerm) ((ATerm) elem2), (ATerm) ((ATerm) elem3), (ATerm) ((ATerm) elem4), (ATerm) (((ATerm) elem5))))
#else
PRS_LexStrCharChars _PRS_makeLexStrCharChars5 (PRS_LexStrChar elem1,
					       PRS_LexStrChar elem2,
					       PRS_LexStrChar elem3,
					       PRS_LexStrChar elem4,
					       PRS_LexStrChar elem5);
#define PRS_makeLexStrCharChars5(elem1, elem2, elem3, elem4,  elem5) (_PRS_makeLexStrCharChars5(elem1, elem2, elem3, elem4,  elem5))
#endif
#ifdef FAST_API
#define PRS_makeLexStrCharChars6(elem1, elem2, elem3, elem4, elem5,  elem6) ((PRS_LexStrCharChars) ATmakeList6((ATerm) ((ATerm) elem1), (ATerm) ((ATerm) elem2), (ATerm) ((ATerm) elem3), (ATerm) ((ATerm) elem4), (ATerm) ((ATerm) elem5), (ATerm) (((ATerm) elem6))))
#else
PRS_LexStrCharChars _PRS_makeLexStrCharChars6 (PRS_LexStrChar elem1,
					       PRS_LexStrChar elem2,
					       PRS_LexStrChar elem3,
					       PRS_LexStrChar elem4,
					       PRS_LexStrChar elem5,
					       PRS_LexStrChar elem6);
#define PRS_makeLexStrCharChars6(elem1, elem2, elem3, elem4, elem5,  elem6) (_PRS_makeLexStrCharChars6(elem1, elem2, elem3, elem4, elem5,  elem6))
#endif
PRS_OptLayout PRS_makeOptLayoutAbsent (void);
PRS_OptLayout PRS_makeOptLayoutPresent (PRS_Layout layout);
PRS_Layout PRS_makeLayoutLexToCf (PRS_LexLayoutList list);
PRS_LexLayoutList PRS_makeLexLayoutListEmpty (void);
PRS_LexLayoutList PRS_makeLexLayoutListSingle (PRS_LexLayout head);
PRS_LexLayoutList PRS_makeLexLayoutListMany (PRS_LexLayout head,
					     PRS_LexLayoutList tail);
PRS_RElem PRS_makeRElemInt (PRS_Integer Integer);
PRS_RElem PRS_makeRElemStr (PRS_StrCon StrCon);
PRS_RElem PRS_makeRElemBool (PRS_BoolCon BoolCon);
PRS_RElem PRS_makeRElemLoc (PRS_Location Location);
PRS_RElem PRS_makeRElemSet (PRS_OptLayout wsAfterBraceOpen,
			    PRS_RElemElements elements,
			    PRS_OptLayout wsAfterElements);
PRS_RElem PRS_makeRElemBag (PRS_OptLayout wsAfterBraceOpenBar,
			    PRS_RElemElements elements,
			    PRS_OptLayout wsAfterElements);
PRS_RElem PRS_makeRElemTuple (PRS_OptLayout wsAfterLessThan,
			      PRS_RElemElements elements,
			      PRS_OptLayout wsAfterElements);
PRS_RType PRS_makeRTypeInt (void);
PRS_RType PRS_makeRTypeBool (void);
PRS_RType PRS_makeRTypeStr (void);
PRS_RType PRS_makeRTypeLoc (void);
PRS_RType PRS_makeRTypeTuple (PRS_OptLayout wsAfterLessThan,
			      PRS_RTypeColumnTypes columnTypes,
			      PRS_OptLayout wsAfterColumnTypes);
PRS_RType PRS_makeRTypeSet (PRS_OptLayout wsAfterSet,
			    PRS_OptLayout wsAfterBracketOpen,
			    PRS_RType elementType,
			    PRS_OptLayout wsAfterElementType);
PRS_RType PRS_makeRTypeBag (PRS_OptLayout wsAfterBag,
			    PRS_OptLayout wsAfterBracketOpen,
			    PRS_RType elementType,
			    PRS_OptLayout wsAfterElementType);
PRS_RType PRS_makeRTypeRelation (PRS_OptLayout wsAfterRel,
				 PRS_OptLayout wsAfterBracketOpen,
				 PRS_RTypeColumnTypes columnTypes,
				 PRS_OptLayout wsAfterColumnTypes);
PRS_RType PRS_makeRTypeUserDefined (PRS_IdCon typeName);
PRS_RType PRS_makeRTypeParameter (PRS_OptLayout wsAfterAmp,
				  PRS_IdCon parameterName);
PRS_RTuple PRS_makeRTupleRtuple (PRS_OptLayout wsAfterLessThan,
				 PRS_IdCon variable,
				 PRS_OptLayout wsAfterVariable,
				 PRS_OptLayout wsAfterComma, PRS_RType rtype,
				 PRS_OptLayout wsAfterRtype,
				 PRS_OptLayout wsAfterComma1, PRS_RElem value,
				 PRS_OptLayout wsAfterValue);
PRS_RStore PRS_makeRStoreRstore (PRS_OptLayout wsAfterRstore,
				 PRS_OptLayout wsAfterParenOpen,
				 PRS_OptLayout wsAfterBracketOpen,
				 PRS_RTupleRtuples rtuples,
				 PRS_OptLayout wsAfterRtuples,
				 PRS_OptLayout wsAfterBracketClose);
PRS_Start PRS_makeStartRStore (PRS_OptLayout wsBefore, PRS_RStore topRStore,
			       PRS_OptLayout wsAfter, int ambCnt);
PRS_Start PRS_makeStartBoolCon (PRS_OptLayout wsBefore,
				PRS_BoolCon topBoolCon, PRS_OptLayout wsAfter,
				int ambCnt);
PRS_RElemElements PRS_makeRElemElementsEmpty (void);
PRS_RElemElements PRS_makeRElemElementsSingle (PRS_RElem head);
PRS_RElemElements PRS_makeRElemElementsMany (PRS_RElem head,
					     PRS_OptLayout wsAfterHead,
					     PRS_OptLayout wsAfterSep,
					     PRS_RElemElements tail);
PRS_RTypeColumnTypes PRS_makeRTypeColumnTypesEmpty (void);
PRS_RTypeColumnTypes PRS_makeRTypeColumnTypesSingle (PRS_RType head);
PRS_RTypeColumnTypes PRS_makeRTypeColumnTypesMany (PRS_RType head,
						   PRS_OptLayout wsAfterHead,
						   PRS_OptLayout wsAfterSep,
						   PRS_RTypeColumnTypes tail);
PRS_RTupleRtuples PRS_makeRTupleRtuplesEmpty (void);
PRS_RTupleRtuples PRS_makeRTupleRtuplesSingle (PRS_RTuple head);
PRS_RTupleRtuples PRS_makeRTupleRtuplesMany (PRS_RTuple head,
					     PRS_OptLayout wsAfterHead,
					     PRS_OptLayout wsAfterSep,
					     PRS_RTupleRtuples tail);
PRS_LexStrChar PRS_makeLexStrCharNewline (void);
PRS_LexStrChar PRS_makeLexStrCharTab (void);
PRS_LexStrChar PRS_makeLexStrCharQuote (void);
PRS_LexStrChar PRS_makeLexStrCharBackslash (void);
PRS_LexStrChar PRS_makeLexStrCharDecimal (char a, char b, char c);
PRS_LexStrChar PRS_makeLexStrCharNormal (char ch);
PRS_StrChar PRS_makeStrCharLexToCf (PRS_LexStrChar StrChar);
PRS_LexStrCon PRS_makeLexStrConDefault (PRS_LexStrCharChars chars);
PRS_StrCon PRS_makeStrConLexToCf (PRS_LexStrCon StrCon);
PRS_LexStrCharChars PRS_makeLexStrCharCharsEmpty (void);
PRS_LexStrCharChars PRS_makeLexStrCharCharsSingle (PRS_LexStrChar head);
PRS_LexStrCharChars PRS_makeLexStrCharCharsMany (PRS_LexStrChar head,
						 PRS_LexStrCharChars tail);
PRS_BoolCon PRS_makeBoolConTrue (void);
PRS_BoolCon PRS_makeBoolConFalse (void);
PRS_LexNatCon PRS_makeLexNatConDigits (const char *list);
PRS_NatCon PRS_makeNatConLexToCf (PRS_LexNatCon NatCon);
PRS_LexIdCon PRS_makeLexIdConDefault (char head, const char *tail);
PRS_IdCon PRS_makeIdConLexToCf (PRS_LexIdCon IdCon);
PRS_Integer PRS_makeIntegerNatCon (PRS_NatCon NatCon);
PRS_Integer PRS_makeIntegerPositive (PRS_OptLayout wsAfterPlus,
				     PRS_Integer integer);
PRS_Integer PRS_makeIntegerNegative (PRS_OptLayout wsAfter,
				     PRS_Integer integer);
PRS_LexLayout PRS_makeLexLayoutWhitespace (char ch);
PRS_Location PRS_makeLocationFile (PRS_OptLayout wsAfterFile,
				   PRS_OptLayout wsAfterParenOpen,
				   PRS_StrCon filename,
				   PRS_OptLayout wsAfterFilename);
PRS_Location PRS_makeLocationArea (PRS_OptLayout wsAfterA,
				   PRS_OptLayout wsAfterParenOpen,
				   PRS_Area Area, PRS_OptLayout wsAfterArea);
PRS_Location PRS_makeLocationAreaInFile (PRS_OptLayout wsAfterAreaInFile,
					 PRS_OptLayout wsAfterParenOpen,
					 PRS_StrCon filename,
					 PRS_OptLayout wsAfterFilename,
					 PRS_OptLayout wsAfterComma,
					 PRS_Area Area,
					 PRS_OptLayout wsAfterArea);
PRS_Area PRS_makeAreaArea (PRS_OptLayout wsAfterArea,
			   PRS_OptLayout wsAfterParenOpen,
			   PRS_NatCon beginLine,
			   PRS_OptLayout wsAfterBeginLine,
			   PRS_OptLayout wsAfterComma, PRS_NatCon beginColumn,
			   PRS_OptLayout wsAfterBeginColumn,
			   PRS_OptLayout wsAfterComma1, PRS_NatCon endLine,
			   PRS_OptLayout wsAfterEndLine,
			   PRS_OptLayout wsAfterComma2, PRS_NatCon endColumn,
			   PRS_OptLayout wsAfterEndColumn,
			   PRS_OptLayout wsAfterComma3, PRS_NatCon offset,
			   PRS_OptLayout wsAfterOffset,
			   PRS_OptLayout wsAfterComma4, PRS_NatCon length,
			   PRS_OptLayout wsAfterLength);
#ifdef FAST_API
#define PRS_isEqualOptLayout(arg0, arg1) (ATisEqual((ATerm)(arg0), (ATerm)(arg1)))
#else
ATbool _PRS_isEqualOptLayout (PRS_OptLayout arg0, PRS_OptLayout arg1);
#define PRS_isEqualOptLayout(arg0, arg1) (_PRS_isEqualOptLayout(arg0, arg1))
#endif
#ifdef FAST_API
#define PRS_isEqualLayout(arg0, arg1) (ATisEqual((ATerm)(arg0), (ATerm)(arg1)))
#else
ATbool _PRS_isEqualLayout (PRS_Layout arg0, PRS_Layout arg1);
#define PRS_isEqualLayout(arg0, arg1) (_PRS_isEqualLayout(arg0, arg1))
#endif
#ifdef FAST_API
#define PRS_isEqualLexLayoutList(arg0, arg1) (ATisEqual((ATerm)(arg0), (ATerm)(arg1)))
#else
ATbool _PRS_isEqualLexLayoutList (PRS_LexLayoutList arg0,
				  PRS_LexLayoutList arg1);
#define PRS_isEqualLexLayoutList(arg0, arg1) (_PRS_isEqualLexLayoutList(arg0, arg1))
#endif
#ifdef FAST_API
#define PRS_isEqualRElem(arg0, arg1) (ATisEqual((ATerm)(arg0), (ATerm)(arg1)))
#else
ATbool _PRS_isEqualRElem (PRS_RElem arg0, PRS_RElem arg1);
#define PRS_isEqualRElem(arg0, arg1) (_PRS_isEqualRElem(arg0, arg1))
#endif
#ifdef FAST_API
#define PRS_isEqualRType(arg0, arg1) (ATisEqual((ATerm)(arg0), (ATerm)(arg1)))
#else
ATbool _PRS_isEqualRType (PRS_RType arg0, PRS_RType arg1);
#define PRS_isEqualRType(arg0, arg1) (_PRS_isEqualRType(arg0, arg1))
#endif
#ifdef FAST_API
#define PRS_isEqualRTuple(arg0, arg1) (ATisEqual((ATerm)(arg0), (ATerm)(arg1)))
#else
ATbool _PRS_isEqualRTuple (PRS_RTuple arg0, PRS_RTuple arg1);
#define PRS_isEqualRTuple(arg0, arg1) (_PRS_isEqualRTuple(arg0, arg1))
#endif
#ifdef FAST_API
#define PRS_isEqualRStore(arg0, arg1) (ATisEqual((ATerm)(arg0), (ATerm)(arg1)))
#else
ATbool _PRS_isEqualRStore (PRS_RStore arg0, PRS_RStore arg1);
#define PRS_isEqualRStore(arg0, arg1) (_PRS_isEqualRStore(arg0, arg1))
#endif
#ifdef FAST_API
#define PRS_isEqualStart(arg0, arg1) (ATisEqual((ATerm)(arg0), (ATerm)(arg1)))
#else
ATbool _PRS_isEqualStart (PRS_Start arg0, PRS_Start arg1);
#define PRS_isEqualStart(arg0, arg1) (_PRS_isEqualStart(arg0, arg1))
#endif
#ifdef FAST_API
#define PRS_isEqualRElemElements(arg0, arg1) (ATisEqual((ATerm)(arg0), (ATerm)(arg1)))
#else
ATbool _PRS_isEqualRElemElements (PRS_RElemElements arg0,
				  PRS_RElemElements arg1);
#define PRS_isEqualRElemElements(arg0, arg1) (_PRS_isEqualRElemElements(arg0, arg1))
#endif
#ifdef FAST_API
#define PRS_isEqualRTypeColumnTypes(arg0, arg1) (ATisEqual((ATerm)(arg0), (ATerm)(arg1)))
#else
ATbool _PRS_isEqualRTypeColumnTypes (PRS_RTypeColumnTypes arg0,
				     PRS_RTypeColumnTypes arg1);
#define PRS_isEqualRTypeColumnTypes(arg0, arg1) (_PRS_isEqualRTypeColumnTypes(arg0, arg1))
#endif
#ifdef FAST_API
#define PRS_isEqualRTupleRtuples(arg0, arg1) (ATisEqual((ATerm)(arg0), (ATerm)(arg1)))
#else
ATbool _PRS_isEqualRTupleRtuples (PRS_RTupleRtuples arg0,
				  PRS_RTupleRtuples arg1);
#define PRS_isEqualRTupleRtuples(arg0, arg1) (_PRS_isEqualRTupleRtuples(arg0, arg1))
#endif
#ifdef FAST_API
#define PRS_isEqualLexStrChar(arg0, arg1) (ATisEqual((ATerm)(arg0), (ATerm)(arg1)))
#else
ATbool _PRS_isEqualLexStrChar (PRS_LexStrChar arg0, PRS_LexStrChar arg1);
#define PRS_isEqualLexStrChar(arg0, arg1) (_PRS_isEqualLexStrChar(arg0, arg1))
#endif
#ifdef FAST_API
#define PRS_isEqualStrChar(arg0, arg1) (ATisEqual((ATerm)(arg0), (ATerm)(arg1)))
#else
ATbool _PRS_isEqualStrChar (PRS_StrChar arg0, PRS_StrChar arg1);
#define PRS_isEqualStrChar(arg0, arg1) (_PRS_isEqualStrChar(arg0, arg1))
#endif
#ifdef FAST_API
#define PRS_isEqualLexStrCon(arg0, arg1) (ATisEqual((ATerm)(arg0), (ATerm)(arg1)))
#else
ATbool _PRS_isEqualLexStrCon (PRS_LexStrCon arg0, PRS_LexStrCon arg1);
#define PRS_isEqualLexStrCon(arg0, arg1) (_PRS_isEqualLexStrCon(arg0, arg1))
#endif
#ifdef FAST_API
#define PRS_isEqualStrCon(arg0, arg1) (ATisEqual((ATerm)(arg0), (ATerm)(arg1)))
#else
ATbool _PRS_isEqualStrCon (PRS_StrCon arg0, PRS_StrCon arg1);
#define PRS_isEqualStrCon(arg0, arg1) (_PRS_isEqualStrCon(arg0, arg1))
#endif
#ifdef FAST_API
#define PRS_isEqualLexStrCharChars(arg0, arg1) (ATisEqual((ATerm)(arg0), (ATerm)(arg1)))
#else
ATbool _PRS_isEqualLexStrCharChars (PRS_LexStrCharChars arg0,
				    PRS_LexStrCharChars arg1);
#define PRS_isEqualLexStrCharChars(arg0, arg1) (_PRS_isEqualLexStrCharChars(arg0, arg1))
#endif
#ifdef FAST_API
#define PRS_isEqualBoolCon(arg0, arg1) (ATisEqual((ATerm)(arg0), (ATerm)(arg1)))
#else
ATbool _PRS_isEqualBoolCon (PRS_BoolCon arg0, PRS_BoolCon arg1);
#define PRS_isEqualBoolCon(arg0, arg1) (_PRS_isEqualBoolCon(arg0, arg1))
#endif
#ifdef FAST_API
#define PRS_isEqualLexNatCon(arg0, arg1) (ATisEqual((ATerm)(arg0), (ATerm)(arg1)))
#else
ATbool _PRS_isEqualLexNatCon (PRS_LexNatCon arg0, PRS_LexNatCon arg1);
#define PRS_isEqualLexNatCon(arg0, arg1) (_PRS_isEqualLexNatCon(arg0, arg1))
#endif
#ifdef FAST_API
#define PRS_isEqualNatCon(arg0, arg1) (ATisEqual((ATerm)(arg0), (ATerm)(arg1)))
#else
ATbool _PRS_isEqualNatCon (PRS_NatCon arg0, PRS_NatCon arg1);
#define PRS_isEqualNatCon(arg0, arg1) (_PRS_isEqualNatCon(arg0, arg1))
#endif
#ifdef FAST_API
#define PRS_isEqualLexIdCon(arg0, arg1) (ATisEqual((ATerm)(arg0), (ATerm)(arg1)))
#else
ATbool _PRS_isEqualLexIdCon (PRS_LexIdCon arg0, PRS_LexIdCon arg1);
#define PRS_isEqualLexIdCon(arg0, arg1) (_PRS_isEqualLexIdCon(arg0, arg1))
#endif
#ifdef FAST_API
#define PRS_isEqualIdCon(arg0, arg1) (ATisEqual((ATerm)(arg0), (ATerm)(arg1)))
#else
ATbool _PRS_isEqualIdCon (PRS_IdCon arg0, PRS_IdCon arg1);
#define PRS_isEqualIdCon(arg0, arg1) (_PRS_isEqualIdCon(arg0, arg1))
#endif
#ifdef FAST_API
#define PRS_isEqualInteger(arg0, arg1) (ATisEqual((ATerm)(arg0), (ATerm)(arg1)))
#else
ATbool _PRS_isEqualInteger (PRS_Integer arg0, PRS_Integer arg1);
#define PRS_isEqualInteger(arg0, arg1) (_PRS_isEqualInteger(arg0, arg1))
#endif
#ifdef FAST_API
#define PRS_isEqualLexLayout(arg0, arg1) (ATisEqual((ATerm)(arg0), (ATerm)(arg1)))
#else
ATbool _PRS_isEqualLexLayout (PRS_LexLayout arg0, PRS_LexLayout arg1);
#define PRS_isEqualLexLayout(arg0, arg1) (_PRS_isEqualLexLayout(arg0, arg1))
#endif
#ifdef FAST_API
#define PRS_isEqualLocation(arg0, arg1) (ATisEqual((ATerm)(arg0), (ATerm)(arg1)))
#else
ATbool _PRS_isEqualLocation (PRS_Location arg0, PRS_Location arg1);
#define PRS_isEqualLocation(arg0, arg1) (_PRS_isEqualLocation(arg0, arg1))
#endif
#ifdef FAST_API
#define PRS_isEqualArea(arg0, arg1) (ATisEqual((ATerm)(arg0), (ATerm)(arg1)))
#else
ATbool _PRS_isEqualArea (PRS_Area arg0, PRS_Area arg1);
#define PRS_isEqualArea(arg0, arg1) (_PRS_isEqualArea(arg0, arg1))
#endif
ATbool PRS_isValidOptLayout (PRS_OptLayout arg);
inline ATbool PRS_isOptLayoutAbsent (PRS_OptLayout arg);
inline ATbool PRS_isOptLayoutPresent (PRS_OptLayout arg);
ATbool PRS_hasOptLayoutLayout (PRS_OptLayout arg);
PRS_Layout PRS_getOptLayoutLayout (PRS_OptLayout arg);
PRS_OptLayout PRS_setOptLayoutLayout (PRS_OptLayout arg, PRS_Layout layout);
ATbool PRS_isValidLayout (PRS_Layout arg);
inline ATbool PRS_isLayoutLexToCf (PRS_Layout arg);
ATbool PRS_hasLayoutList (PRS_Layout arg);
PRS_LexLayoutList PRS_getLayoutList (PRS_Layout arg);
PRS_Layout PRS_setLayoutList (PRS_Layout arg, PRS_LexLayoutList list);
ATbool PRS_isValidLexLayoutList (PRS_LexLayoutList arg);
inline ATbool PRS_isLexLayoutListEmpty (PRS_LexLayoutList arg);
inline ATbool PRS_isLexLayoutListSingle (PRS_LexLayoutList arg);
inline ATbool PRS_isLexLayoutListMany (PRS_LexLayoutList arg);
ATbool PRS_hasLexLayoutListHead (PRS_LexLayoutList arg);
ATbool PRS_hasLexLayoutListTail (PRS_LexLayoutList arg);
PRS_LexLayout PRS_getLexLayoutListHead (PRS_LexLayoutList arg);
PRS_LexLayoutList PRS_getLexLayoutListTail (PRS_LexLayoutList arg);
PRS_LexLayoutList PRS_setLexLayoutListHead (PRS_LexLayoutList arg,
					    PRS_LexLayout head);
PRS_LexLayoutList PRS_setLexLayoutListTail (PRS_LexLayoutList arg,
					    PRS_LexLayoutList tail);
ATbool PRS_isValidRElem (PRS_RElem arg);
inline ATbool PRS_isRElemInt (PRS_RElem arg);
inline ATbool PRS_isRElemStr (PRS_RElem arg);
inline ATbool PRS_isRElemBool (PRS_RElem arg);
inline ATbool PRS_isRElemLoc (PRS_RElem arg);
inline ATbool PRS_isRElemSet (PRS_RElem arg);
inline ATbool PRS_isRElemBag (PRS_RElem arg);
inline ATbool PRS_isRElemTuple (PRS_RElem arg);
ATbool PRS_hasRElemInteger (PRS_RElem arg);
ATbool PRS_hasRElemStrCon (PRS_RElem arg);
ATbool PRS_hasRElemBoolCon (PRS_RElem arg);
ATbool PRS_hasRElemLocation (PRS_RElem arg);
ATbool PRS_hasRElemWsAfterBraceOpen (PRS_RElem arg);
ATbool PRS_hasRElemElements (PRS_RElem arg);
ATbool PRS_hasRElemWsAfterElements (PRS_RElem arg);
ATbool PRS_hasRElemWsAfterBraceOpenBar (PRS_RElem arg);
ATbool PRS_hasRElemWsAfterLessThan (PRS_RElem arg);
PRS_Integer PRS_getRElemInteger (PRS_RElem arg);
PRS_StrCon PRS_getRElemStrCon (PRS_RElem arg);
PRS_BoolCon PRS_getRElemBoolCon (PRS_RElem arg);
PRS_Location PRS_getRElemLocation (PRS_RElem arg);
PRS_OptLayout PRS_getRElemWsAfterBraceOpen (PRS_RElem arg);
PRS_RElemElements PRS_getRElemElements (PRS_RElem arg);
PRS_OptLayout PRS_getRElemWsAfterElements (PRS_RElem arg);
PRS_OptLayout PRS_getRElemWsAfterBraceOpenBar (PRS_RElem arg);
PRS_OptLayout PRS_getRElemWsAfterLessThan (PRS_RElem arg);
PRS_RElem PRS_setRElemInteger (PRS_RElem arg, PRS_Integer Integer);
PRS_RElem PRS_setRElemStrCon (PRS_RElem arg, PRS_StrCon StrCon);
PRS_RElem PRS_setRElemBoolCon (PRS_RElem arg, PRS_BoolCon BoolCon);
PRS_RElem PRS_setRElemLocation (PRS_RElem arg, PRS_Location Location);
PRS_RElem PRS_setRElemWsAfterBraceOpen (PRS_RElem arg,
					PRS_OptLayout wsAfterBraceOpen);
PRS_RElem PRS_setRElemElements (PRS_RElem arg, PRS_RElemElements elements);
PRS_RElem PRS_setRElemWsAfterElements (PRS_RElem arg,
				       PRS_OptLayout wsAfterElements);
PRS_RElem PRS_setRElemWsAfterBraceOpenBar (PRS_RElem arg,
					   PRS_OptLayout wsAfterBraceOpenBar);
PRS_RElem PRS_setRElemWsAfterLessThan (PRS_RElem arg,
				       PRS_OptLayout wsAfterLessThan);
ATbool PRS_isValidRType (PRS_RType arg);
inline ATbool PRS_isRTypeInt (PRS_RType arg);
inline ATbool PRS_isRTypeBool (PRS_RType arg);
inline ATbool PRS_isRTypeStr (PRS_RType arg);
inline ATbool PRS_isRTypeLoc (PRS_RType arg);
inline ATbool PRS_isRTypeTuple (PRS_RType arg);
inline ATbool PRS_isRTypeSet (PRS_RType arg);
inline ATbool PRS_isRTypeBag (PRS_RType arg);
inline ATbool PRS_isRTypeRelation (PRS_RType arg);
inline ATbool PRS_isRTypeUserDefined (PRS_RType arg);
inline ATbool PRS_isRTypeParameter (PRS_RType arg);
ATbool PRS_hasRTypeWsAfterLessThan (PRS_RType arg);
ATbool PRS_hasRTypeColumnTypes (PRS_RType arg);
ATbool PRS_hasRTypeWsAfterColumnTypes (PRS_RType arg);
ATbool PRS_hasRTypeWsAfterSet (PRS_RType arg);
ATbool PRS_hasRTypeWsAfterBracketOpen (PRS_RType arg);
ATbool PRS_hasRTypeElementType (PRS_RType arg);
ATbool PRS_hasRTypeWsAfterElementType (PRS_RType arg);
ATbool PRS_hasRTypeWsAfterBag (PRS_RType arg);
ATbool PRS_hasRTypeWsAfterRel (PRS_RType arg);
ATbool PRS_hasRTypeTypeName (PRS_RType arg);
ATbool PRS_hasRTypeWsAfterAmp (PRS_RType arg);
ATbool PRS_hasRTypeParameterName (PRS_RType arg);
PRS_OptLayout PRS_getRTypeWsAfterLessThan (PRS_RType arg);
PRS_RTypeColumnTypes PRS_getRTypeColumnTypes (PRS_RType arg);
PRS_OptLayout PRS_getRTypeWsAfterColumnTypes (PRS_RType arg);
PRS_OptLayout PRS_getRTypeWsAfterSet (PRS_RType arg);
PRS_OptLayout PRS_getRTypeWsAfterBracketOpen (PRS_RType arg);
PRS_RType PRS_getRTypeElementType (PRS_RType arg);
PRS_OptLayout PRS_getRTypeWsAfterElementType (PRS_RType arg);
PRS_OptLayout PRS_getRTypeWsAfterBag (PRS_RType arg);
PRS_OptLayout PRS_getRTypeWsAfterRel (PRS_RType arg);
PRS_IdCon PRS_getRTypeTypeName (PRS_RType arg);
PRS_OptLayout PRS_getRTypeWsAfterAmp (PRS_RType arg);
PRS_IdCon PRS_getRTypeParameterName (PRS_RType arg);
PRS_RType PRS_setRTypeWsAfterLessThan (PRS_RType arg,
				       PRS_OptLayout wsAfterLessThan);
PRS_RType PRS_setRTypeColumnTypes (PRS_RType arg,
				   PRS_RTypeColumnTypes columnTypes);
PRS_RType PRS_setRTypeWsAfterColumnTypes (PRS_RType arg,
					  PRS_OptLayout wsAfterColumnTypes);
PRS_RType PRS_setRTypeWsAfterSet (PRS_RType arg, PRS_OptLayout wsAfterSet);
PRS_RType PRS_setRTypeWsAfterBracketOpen (PRS_RType arg,
					  PRS_OptLayout wsAfterBracketOpen);
PRS_RType PRS_setRTypeElementType (PRS_RType arg, PRS_RType elementType);
PRS_RType PRS_setRTypeWsAfterElementType (PRS_RType arg,
					  PRS_OptLayout wsAfterElementType);
PRS_RType PRS_setRTypeWsAfterBag (PRS_RType arg, PRS_OptLayout wsAfterBag);
PRS_RType PRS_setRTypeWsAfterRel (PRS_RType arg, PRS_OptLayout wsAfterRel);
PRS_RType PRS_setRTypeTypeName (PRS_RType arg, PRS_IdCon typeName);
PRS_RType PRS_setRTypeWsAfterAmp (PRS_RType arg, PRS_OptLayout wsAfterAmp);
PRS_RType PRS_setRTypeParameterName (PRS_RType arg, PRS_IdCon parameterName);
ATbool PRS_isValidRTuple (PRS_RTuple arg);
inline ATbool PRS_isRTupleRtuple (PRS_RTuple arg);
ATbool PRS_hasRTupleWsAfterLessThan (PRS_RTuple arg);
ATbool PRS_hasRTupleVariable (PRS_RTuple arg);
ATbool PRS_hasRTupleWsAfterVariable (PRS_RTuple arg);
ATbool PRS_hasRTupleWsAfterComma (PRS_RTuple arg);
ATbool PRS_hasRTupleRtype (PRS_RTuple arg);
ATbool PRS_hasRTupleWsAfterRtype (PRS_RTuple arg);
ATbool PRS_hasRTupleWsAfterComma1 (PRS_RTuple arg);
ATbool PRS_hasRTupleValue (PRS_RTuple arg);
ATbool PRS_hasRTupleWsAfterValue (PRS_RTuple arg);
PRS_OptLayout PRS_getRTupleWsAfterLessThan (PRS_RTuple arg);
PRS_IdCon PRS_getRTupleVariable (PRS_RTuple arg);
PRS_OptLayout PRS_getRTupleWsAfterVariable (PRS_RTuple arg);
PRS_OptLayout PRS_getRTupleWsAfterComma (PRS_RTuple arg);
PRS_RType PRS_getRTupleRtype (PRS_RTuple arg);
PRS_OptLayout PRS_getRTupleWsAfterRtype (PRS_RTuple arg);
PRS_OptLayout PRS_getRTupleWsAfterComma1 (PRS_RTuple arg);
PRS_RElem PRS_getRTupleValue (PRS_RTuple arg);
PRS_OptLayout PRS_getRTupleWsAfterValue (PRS_RTuple arg);
PRS_RTuple PRS_setRTupleWsAfterLessThan (PRS_RTuple arg,
					 PRS_OptLayout wsAfterLessThan);
PRS_RTuple PRS_setRTupleVariable (PRS_RTuple arg, PRS_IdCon variable);
PRS_RTuple PRS_setRTupleWsAfterVariable (PRS_RTuple arg,
					 PRS_OptLayout wsAfterVariable);
PRS_RTuple PRS_setRTupleWsAfterComma (PRS_RTuple arg,
				      PRS_OptLayout wsAfterComma);
PRS_RTuple PRS_setRTupleRtype (PRS_RTuple arg, PRS_RType rtype);
PRS_RTuple PRS_setRTupleWsAfterRtype (PRS_RTuple arg,
				      PRS_OptLayout wsAfterRtype);
PRS_RTuple PRS_setRTupleWsAfterComma1 (PRS_RTuple arg,
				       PRS_OptLayout wsAfterComma1);
PRS_RTuple PRS_setRTupleValue (PRS_RTuple arg, PRS_RElem value);
PRS_RTuple PRS_setRTupleWsAfterValue (PRS_RTuple arg,
				      PRS_OptLayout wsAfterValue);
ATbool PRS_isValidRStore (PRS_RStore arg);
inline ATbool PRS_isRStoreRstore (PRS_RStore arg);
ATbool PRS_hasRStoreWsAfterRstore (PRS_RStore arg);
ATbool PRS_hasRStoreWsAfterParenOpen (PRS_RStore arg);
ATbool PRS_hasRStoreWsAfterBracketOpen (PRS_RStore arg);
ATbool PRS_hasRStoreRtuples (PRS_RStore arg);
ATbool PRS_hasRStoreWsAfterRtuples (PRS_RStore arg);
ATbool PRS_hasRStoreWsAfterBracketClose (PRS_RStore arg);
PRS_OptLayout PRS_getRStoreWsAfterRstore (PRS_RStore arg);
PRS_OptLayout PRS_getRStoreWsAfterParenOpen (PRS_RStore arg);
PRS_OptLayout PRS_getRStoreWsAfterBracketOpen (PRS_RStore arg);
PRS_RTupleRtuples PRS_getRStoreRtuples (PRS_RStore arg);
PRS_OptLayout PRS_getRStoreWsAfterRtuples (PRS_RStore arg);
PRS_OptLayout PRS_getRStoreWsAfterBracketClose (PRS_RStore arg);
PRS_RStore PRS_setRStoreWsAfterRstore (PRS_RStore arg,
				       PRS_OptLayout wsAfterRstore);
PRS_RStore PRS_setRStoreWsAfterParenOpen (PRS_RStore arg,
					  PRS_OptLayout wsAfterParenOpen);
PRS_RStore PRS_setRStoreWsAfterBracketOpen (PRS_RStore arg,
					    PRS_OptLayout wsAfterBracketOpen);
PRS_RStore PRS_setRStoreRtuples (PRS_RStore arg, PRS_RTupleRtuples rtuples);
PRS_RStore PRS_setRStoreWsAfterRtuples (PRS_RStore arg,
					PRS_OptLayout wsAfterRtuples);
PRS_RStore PRS_setRStoreWsAfterBracketClose (PRS_RStore arg,
					     PRS_OptLayout
					     wsAfterBracketClose);
ATbool PRS_isValidStart (PRS_Start arg);
inline ATbool PRS_isStartRStore (PRS_Start arg);
inline ATbool PRS_isStartBoolCon (PRS_Start arg);
ATbool PRS_hasStartWsBefore (PRS_Start arg);
ATbool PRS_hasStartTopRStore (PRS_Start arg);
ATbool PRS_hasStartWsAfter (PRS_Start arg);
ATbool PRS_hasStartAmbCnt (PRS_Start arg);
ATbool PRS_hasStartTopBoolCon (PRS_Start arg);
PRS_OptLayout PRS_getStartWsBefore (PRS_Start arg);
PRS_RStore PRS_getStartTopRStore (PRS_Start arg);
PRS_OptLayout PRS_getStartWsAfter (PRS_Start arg);
int PRS_getStartAmbCnt (PRS_Start arg);
PRS_BoolCon PRS_getStartTopBoolCon (PRS_Start arg);
PRS_Start PRS_setStartWsBefore (PRS_Start arg, PRS_OptLayout wsBefore);
PRS_Start PRS_setStartTopRStore (PRS_Start arg, PRS_RStore topRStore);
PRS_Start PRS_setStartWsAfter (PRS_Start arg, PRS_OptLayout wsAfter);
PRS_Start PRS_setStartAmbCnt (PRS_Start arg, int ambCnt);
PRS_Start PRS_setStartTopBoolCon (PRS_Start arg, PRS_BoolCon topBoolCon);
ATbool PRS_isValidRElemElements (PRS_RElemElements arg);
inline ATbool PRS_isRElemElementsEmpty (PRS_RElemElements arg);
inline ATbool PRS_isRElemElementsSingle (PRS_RElemElements arg);
inline ATbool PRS_isRElemElementsMany (PRS_RElemElements arg);
ATbool PRS_hasRElemElementsHead (PRS_RElemElements arg);
ATbool PRS_hasRElemElementsWsAfterHead (PRS_RElemElements arg);
ATbool PRS_hasRElemElementsWsAfterSep (PRS_RElemElements arg);
ATbool PRS_hasRElemElementsTail (PRS_RElemElements arg);
PRS_RElemElements PRS_getRElemElementsTail (PRS_RElemElements arg);
PRS_RElem PRS_getRElemElementsHead (PRS_RElemElements arg);
PRS_OptLayout PRS_getRElemElementsWsAfterHead (PRS_RElemElements arg);
PRS_OptLayout PRS_getRElemElementsWsAfterSep (PRS_RElemElements arg);
PRS_RElemElements PRS_setRElemElementsHead (PRS_RElemElements arg,
					    PRS_RElem head);
PRS_RElemElements PRS_setRElemElementsWsAfterHead (PRS_RElemElements arg,
						   PRS_OptLayout wsAfterHead);
PRS_RElemElements PRS_setRElemElementsWsAfterSep (PRS_RElemElements arg,
						  PRS_OptLayout wsAfterSep);
PRS_RElemElements PRS_setRElemElementsTail (PRS_RElemElements arg,
					    PRS_RElemElements tail);
ATbool PRS_isValidRTypeColumnTypes (PRS_RTypeColumnTypes arg);
inline ATbool PRS_isRTypeColumnTypesEmpty (PRS_RTypeColumnTypes arg);
inline ATbool PRS_isRTypeColumnTypesSingle (PRS_RTypeColumnTypes arg);
inline ATbool PRS_isRTypeColumnTypesMany (PRS_RTypeColumnTypes arg);
ATbool PRS_hasRTypeColumnTypesHead (PRS_RTypeColumnTypes arg);
ATbool PRS_hasRTypeColumnTypesWsAfterHead (PRS_RTypeColumnTypes arg);
ATbool PRS_hasRTypeColumnTypesWsAfterSep (PRS_RTypeColumnTypes arg);
ATbool PRS_hasRTypeColumnTypesTail (PRS_RTypeColumnTypes arg);
PRS_RTypeColumnTypes PRS_getRTypeColumnTypesTail (PRS_RTypeColumnTypes arg);
PRS_RType PRS_getRTypeColumnTypesHead (PRS_RTypeColumnTypes arg);
PRS_OptLayout PRS_getRTypeColumnTypesWsAfterHead (PRS_RTypeColumnTypes arg);
PRS_OptLayout PRS_getRTypeColumnTypesWsAfterSep (PRS_RTypeColumnTypes arg);
PRS_RTypeColumnTypes PRS_setRTypeColumnTypesHead (PRS_RTypeColumnTypes arg,
						  PRS_RType head);
PRS_RTypeColumnTypes PRS_setRTypeColumnTypesWsAfterHead (PRS_RTypeColumnTypes
							 arg,
							 PRS_OptLayout
							 wsAfterHead);
PRS_RTypeColumnTypes PRS_setRTypeColumnTypesWsAfterSep (PRS_RTypeColumnTypes
							arg,
							PRS_OptLayout
							wsAfterSep);
PRS_RTypeColumnTypes PRS_setRTypeColumnTypesTail (PRS_RTypeColumnTypes arg,
						  PRS_RTypeColumnTypes tail);
ATbool PRS_isValidRTupleRtuples (PRS_RTupleRtuples arg);
inline ATbool PRS_isRTupleRtuplesEmpty (PRS_RTupleRtuples arg);
inline ATbool PRS_isRTupleRtuplesSingle (PRS_RTupleRtuples arg);
inline ATbool PRS_isRTupleRtuplesMany (PRS_RTupleRtuples arg);
ATbool PRS_hasRTupleRtuplesHead (PRS_RTupleRtuples arg);
ATbool PRS_hasRTupleRtuplesWsAfterHead (PRS_RTupleRtuples arg);
ATbool PRS_hasRTupleRtuplesWsAfterSep (PRS_RTupleRtuples arg);
ATbool PRS_hasRTupleRtuplesTail (PRS_RTupleRtuples arg);
PRS_RTupleRtuples PRS_getRTupleRtuplesTail (PRS_RTupleRtuples arg);
PRS_RTuple PRS_getRTupleRtuplesHead (PRS_RTupleRtuples arg);
PRS_OptLayout PRS_getRTupleRtuplesWsAfterHead (PRS_RTupleRtuples arg);
PRS_OptLayout PRS_getRTupleRtuplesWsAfterSep (PRS_RTupleRtuples arg);
PRS_RTupleRtuples PRS_setRTupleRtuplesHead (PRS_RTupleRtuples arg,
					    PRS_RTuple head);
PRS_RTupleRtuples PRS_setRTupleRtuplesWsAfterHead (PRS_RTupleRtuples arg,
						   PRS_OptLayout wsAfterHead);
PRS_RTupleRtuples PRS_setRTupleRtuplesWsAfterSep (PRS_RTupleRtuples arg,
						  PRS_OptLayout wsAfterSep);
PRS_RTupleRtuples PRS_setRTupleRtuplesTail (PRS_RTupleRtuples arg,
					    PRS_RTupleRtuples tail);
ATbool PRS_isValidLexStrChar (PRS_LexStrChar arg);
inline ATbool PRS_isLexStrCharNewline (PRS_LexStrChar arg);
inline ATbool PRS_isLexStrCharTab (PRS_LexStrChar arg);
inline ATbool PRS_isLexStrCharQuote (PRS_LexStrChar arg);
inline ATbool PRS_isLexStrCharBackslash (PRS_LexStrChar arg);
inline ATbool PRS_isLexStrCharDecimal (PRS_LexStrChar arg);
inline ATbool PRS_isLexStrCharNormal (PRS_LexStrChar arg);
ATbool PRS_hasLexStrCharA (PRS_LexStrChar arg);
ATbool PRS_hasLexStrCharB (PRS_LexStrChar arg);
ATbool PRS_hasLexStrCharC (PRS_LexStrChar arg);
ATbool PRS_hasLexStrCharCh (PRS_LexStrChar arg);
char PRS_getLexStrCharA (PRS_LexStrChar arg);
char PRS_getLexStrCharB (PRS_LexStrChar arg);
char PRS_getLexStrCharC (PRS_LexStrChar arg);
char PRS_getLexStrCharCh (PRS_LexStrChar arg);
PRS_LexStrChar PRS_setLexStrCharA (PRS_LexStrChar arg, char a);
PRS_LexStrChar PRS_setLexStrCharB (PRS_LexStrChar arg, char b);
PRS_LexStrChar PRS_setLexStrCharC (PRS_LexStrChar arg, char c);
PRS_LexStrChar PRS_setLexStrCharCh (PRS_LexStrChar arg, char ch);
ATbool PRS_isValidStrChar (PRS_StrChar arg);
inline ATbool PRS_isStrCharLexToCf (PRS_StrChar arg);
ATbool PRS_hasStrCharStrChar (PRS_StrChar arg);
PRS_LexStrChar PRS_getStrCharStrChar (PRS_StrChar arg);
PRS_StrChar PRS_setStrCharStrChar (PRS_StrChar arg, PRS_LexStrChar StrChar);
ATbool PRS_isValidLexStrCon (PRS_LexStrCon arg);
inline ATbool PRS_isLexStrConDefault (PRS_LexStrCon arg);
ATbool PRS_hasLexStrConChars (PRS_LexStrCon arg);
PRS_LexStrCharChars PRS_getLexStrConChars (PRS_LexStrCon arg);
PRS_LexStrCon PRS_setLexStrConChars (PRS_LexStrCon arg,
				     PRS_LexStrCharChars chars);
ATbool PRS_isValidStrCon (PRS_StrCon arg);
inline ATbool PRS_isStrConLexToCf (PRS_StrCon arg);
ATbool PRS_hasStrConStrCon (PRS_StrCon arg);
PRS_LexStrCon PRS_getStrConStrCon (PRS_StrCon arg);
PRS_StrCon PRS_setStrConStrCon (PRS_StrCon arg, PRS_LexStrCon StrCon);
ATbool PRS_isValidLexStrCharChars (PRS_LexStrCharChars arg);
inline ATbool PRS_isLexStrCharCharsEmpty (PRS_LexStrCharChars arg);
inline ATbool PRS_isLexStrCharCharsSingle (PRS_LexStrCharChars arg);
inline ATbool PRS_isLexStrCharCharsMany (PRS_LexStrCharChars arg);
ATbool PRS_hasLexStrCharCharsHead (PRS_LexStrCharChars arg);
ATbool PRS_hasLexStrCharCharsTail (PRS_LexStrCharChars arg);
PRS_LexStrChar PRS_getLexStrCharCharsHead (PRS_LexStrCharChars arg);
PRS_LexStrCharChars PRS_getLexStrCharCharsTail (PRS_LexStrCharChars arg);
PRS_LexStrCharChars PRS_setLexStrCharCharsHead (PRS_LexStrCharChars arg,
						PRS_LexStrChar head);
PRS_LexStrCharChars PRS_setLexStrCharCharsTail (PRS_LexStrCharChars arg,
						PRS_LexStrCharChars tail);
ATbool PRS_isValidBoolCon (PRS_BoolCon arg);
inline ATbool PRS_isBoolConTrue (PRS_BoolCon arg);
inline ATbool PRS_isBoolConFalse (PRS_BoolCon arg);
ATbool PRS_isValidLexNatCon (PRS_LexNatCon arg);
inline ATbool PRS_isLexNatConDigits (PRS_LexNatCon arg);
ATbool PRS_hasLexNatConList (PRS_LexNatCon arg);
char *PRS_getLexNatConList (PRS_LexNatCon arg);
PRS_LexNatCon PRS_setLexNatConList (PRS_LexNatCon arg, const char *list);
ATbool PRS_isValidNatCon (PRS_NatCon arg);
inline ATbool PRS_isNatConLexToCf (PRS_NatCon arg);
ATbool PRS_hasNatConNatCon (PRS_NatCon arg);
PRS_LexNatCon PRS_getNatConNatCon (PRS_NatCon arg);
PRS_NatCon PRS_setNatConNatCon (PRS_NatCon arg, PRS_LexNatCon NatCon);
ATbool PRS_isValidLexIdCon (PRS_LexIdCon arg);
inline ATbool PRS_isLexIdConDefault (PRS_LexIdCon arg);
ATbool PRS_hasLexIdConHead (PRS_LexIdCon arg);
ATbool PRS_hasLexIdConTail (PRS_LexIdCon arg);
char PRS_getLexIdConHead (PRS_LexIdCon arg);
char *PRS_getLexIdConTail (PRS_LexIdCon arg);
PRS_LexIdCon PRS_setLexIdConHead (PRS_LexIdCon arg, char head);
PRS_LexIdCon PRS_setLexIdConTail (PRS_LexIdCon arg, const char *tail);
ATbool PRS_isValidIdCon (PRS_IdCon arg);
inline ATbool PRS_isIdConLexToCf (PRS_IdCon arg);
ATbool PRS_hasIdConIdCon (PRS_IdCon arg);
PRS_LexIdCon PRS_getIdConIdCon (PRS_IdCon arg);
PRS_IdCon PRS_setIdConIdCon (PRS_IdCon arg, PRS_LexIdCon IdCon);
ATbool PRS_isValidInteger (PRS_Integer arg);
inline ATbool PRS_isIntegerNatCon (PRS_Integer arg);
inline ATbool PRS_isIntegerPositive (PRS_Integer arg);
inline ATbool PRS_isIntegerNegative (PRS_Integer arg);
ATbool PRS_hasIntegerNatCon (PRS_Integer arg);
ATbool PRS_hasIntegerWsAfterPlus (PRS_Integer arg);
ATbool PRS_hasIntegerInteger (PRS_Integer arg);
ATbool PRS_hasIntegerWsAfter (PRS_Integer arg);
PRS_NatCon PRS_getIntegerNatCon (PRS_Integer arg);
PRS_OptLayout PRS_getIntegerWsAfterPlus (PRS_Integer arg);
PRS_Integer PRS_getIntegerInteger (PRS_Integer arg);
PRS_OptLayout PRS_getIntegerWsAfter (PRS_Integer arg);
PRS_Integer PRS_setIntegerNatCon (PRS_Integer arg, PRS_NatCon NatCon);
PRS_Integer PRS_setIntegerWsAfterPlus (PRS_Integer arg,
				       PRS_OptLayout wsAfterPlus);
PRS_Integer PRS_setIntegerInteger (PRS_Integer arg, PRS_Integer integer);
PRS_Integer PRS_setIntegerWsAfter (PRS_Integer arg, PRS_OptLayout wsAfter);
ATbool PRS_isValidLexLayout (PRS_LexLayout arg);
inline ATbool PRS_isLexLayoutWhitespace (PRS_LexLayout arg);
ATbool PRS_hasLexLayoutCh (PRS_LexLayout arg);
char PRS_getLexLayoutCh (PRS_LexLayout arg);
PRS_LexLayout PRS_setLexLayoutCh (PRS_LexLayout arg, char ch);
ATbool PRS_isValidLocation (PRS_Location arg);
inline ATbool PRS_isLocationFile (PRS_Location arg);
inline ATbool PRS_isLocationArea (PRS_Location arg);
inline ATbool PRS_isLocationAreaInFile (PRS_Location arg);
ATbool PRS_hasLocationWsAfterFile (PRS_Location arg);
ATbool PRS_hasLocationWsAfterParenOpen (PRS_Location arg);
ATbool PRS_hasLocationFilename (PRS_Location arg);
ATbool PRS_hasLocationWsAfterFilename (PRS_Location arg);
ATbool PRS_hasLocationWsAfterA (PRS_Location arg);
ATbool PRS_hasLocationArea (PRS_Location arg);
ATbool PRS_hasLocationWsAfterArea (PRS_Location arg);
ATbool PRS_hasLocationWsAfterAreaInFile (PRS_Location arg);
ATbool PRS_hasLocationWsAfterComma (PRS_Location arg);
PRS_OptLayout PRS_getLocationWsAfterFile (PRS_Location arg);
PRS_OptLayout PRS_getLocationWsAfterParenOpen (PRS_Location arg);
PRS_StrCon PRS_getLocationFilename (PRS_Location arg);
PRS_OptLayout PRS_getLocationWsAfterFilename (PRS_Location arg);
PRS_OptLayout PRS_getLocationWsAfterA (PRS_Location arg);
PRS_Area PRS_getLocationArea (PRS_Location arg);
PRS_OptLayout PRS_getLocationWsAfterArea (PRS_Location arg);
PRS_OptLayout PRS_getLocationWsAfterAreaInFile (PRS_Location arg);
PRS_OptLayout PRS_getLocationWsAfterComma (PRS_Location arg);
PRS_Location PRS_setLocationWsAfterFile (PRS_Location arg,
					 PRS_OptLayout wsAfterFile);
PRS_Location PRS_setLocationWsAfterParenOpen (PRS_Location arg,
					      PRS_OptLayout wsAfterParenOpen);
PRS_Location PRS_setLocationFilename (PRS_Location arg, PRS_StrCon filename);
PRS_Location PRS_setLocationWsAfterFilename (PRS_Location arg,
					     PRS_OptLayout wsAfterFilename);
PRS_Location PRS_setLocationWsAfterA (PRS_Location arg,
				      PRS_OptLayout wsAfterA);
PRS_Location PRS_setLocationArea (PRS_Location arg, PRS_Area Area);
PRS_Location PRS_setLocationWsAfterArea (PRS_Location arg,
					 PRS_OptLayout wsAfterArea);
PRS_Location PRS_setLocationWsAfterAreaInFile (PRS_Location arg,
					       PRS_OptLayout
					       wsAfterAreaInFile);
PRS_Location PRS_setLocationWsAfterComma (PRS_Location arg,
					  PRS_OptLayout wsAfterComma);
ATbool PRS_isValidArea (PRS_Area arg);
inline ATbool PRS_isAreaArea (PRS_Area arg);
ATbool PRS_hasAreaWsAfterArea (PRS_Area arg);
ATbool PRS_hasAreaWsAfterParenOpen (PRS_Area arg);
ATbool PRS_hasAreaBeginLine (PRS_Area arg);
ATbool PRS_hasAreaWsAfterBeginLine (PRS_Area arg);
ATbool PRS_hasAreaWsAfterComma (PRS_Area arg);
ATbool PRS_hasAreaBeginColumn (PRS_Area arg);
ATbool PRS_hasAreaWsAfterBeginColumn (PRS_Area arg);
ATbool PRS_hasAreaWsAfterComma1 (PRS_Area arg);
ATbool PRS_hasAreaEndLine (PRS_Area arg);
ATbool PRS_hasAreaWsAfterEndLine (PRS_Area arg);
ATbool PRS_hasAreaWsAfterComma2 (PRS_Area arg);
ATbool PRS_hasAreaEndColumn (PRS_Area arg);
ATbool PRS_hasAreaWsAfterEndColumn (PRS_Area arg);
ATbool PRS_hasAreaWsAfterComma3 (PRS_Area arg);
ATbool PRS_hasAreaOffset (PRS_Area arg);
ATbool PRS_hasAreaWsAfterOffset (PRS_Area arg);
ATbool PRS_hasAreaWsAfterComma4 (PRS_Area arg);
ATbool PRS_hasAreaLength (PRS_Area arg);
ATbool PRS_hasAreaWsAfterLength (PRS_Area arg);
PRS_OptLayout PRS_getAreaWsAfterArea (PRS_Area arg);
PRS_OptLayout PRS_getAreaWsAfterParenOpen (PRS_Area arg);
PRS_NatCon PRS_getAreaBeginLine (PRS_Area arg);
PRS_OptLayout PRS_getAreaWsAfterBeginLine (PRS_Area arg);
PRS_OptLayout PRS_getAreaWsAfterComma (PRS_Area arg);
PRS_NatCon PRS_getAreaBeginColumn (PRS_Area arg);
PRS_OptLayout PRS_getAreaWsAfterBeginColumn (PRS_Area arg);
PRS_OptLayout PRS_getAreaWsAfterComma1 (PRS_Area arg);
PRS_NatCon PRS_getAreaEndLine (PRS_Area arg);
PRS_OptLayout PRS_getAreaWsAfterEndLine (PRS_Area arg);
PRS_OptLayout PRS_getAreaWsAfterComma2 (PRS_Area arg);
PRS_NatCon PRS_getAreaEndColumn (PRS_Area arg);
PRS_OptLayout PRS_getAreaWsAfterEndColumn (PRS_Area arg);
PRS_OptLayout PRS_getAreaWsAfterComma3 (PRS_Area arg);
PRS_NatCon PRS_getAreaOffset (PRS_Area arg);
PRS_OptLayout PRS_getAreaWsAfterOffset (PRS_Area arg);
PRS_OptLayout PRS_getAreaWsAfterComma4 (PRS_Area arg);
PRS_NatCon PRS_getAreaLength (PRS_Area arg);
PRS_OptLayout PRS_getAreaWsAfterLength (PRS_Area arg);
PRS_Area PRS_setAreaWsAfterArea (PRS_Area arg, PRS_OptLayout wsAfterArea);
PRS_Area PRS_setAreaWsAfterParenOpen (PRS_Area arg,
				      PRS_OptLayout wsAfterParenOpen);
PRS_Area PRS_setAreaBeginLine (PRS_Area arg, PRS_NatCon beginLine);
PRS_Area PRS_setAreaWsAfterBeginLine (PRS_Area arg,
				      PRS_OptLayout wsAfterBeginLine);
PRS_Area PRS_setAreaWsAfterComma (PRS_Area arg, PRS_OptLayout wsAfterComma);
PRS_Area PRS_setAreaBeginColumn (PRS_Area arg, PRS_NatCon beginColumn);
PRS_Area PRS_setAreaWsAfterBeginColumn (PRS_Area arg,
					PRS_OptLayout wsAfterBeginColumn);
PRS_Area PRS_setAreaWsAfterComma1 (PRS_Area arg, PRS_OptLayout wsAfterComma1);
PRS_Area PRS_setAreaEndLine (PRS_Area arg, PRS_NatCon endLine);
PRS_Area PRS_setAreaWsAfterEndLine (PRS_Area arg,
				    PRS_OptLayout wsAfterEndLine);
PRS_Area PRS_setAreaWsAfterComma2 (PRS_Area arg, PRS_OptLayout wsAfterComma2);
PRS_Area PRS_setAreaEndColumn (PRS_Area arg, PRS_NatCon endColumn);
PRS_Area PRS_setAreaWsAfterEndColumn (PRS_Area arg,
				      PRS_OptLayout wsAfterEndColumn);
PRS_Area PRS_setAreaWsAfterComma3 (PRS_Area arg, PRS_OptLayout wsAfterComma3);
PRS_Area PRS_setAreaOffset (PRS_Area arg, PRS_NatCon offset);
PRS_Area PRS_setAreaWsAfterOffset (PRS_Area arg, PRS_OptLayout wsAfterOffset);
PRS_Area PRS_setAreaWsAfterComma4 (PRS_Area arg, PRS_OptLayout wsAfterComma4);
PRS_Area PRS_setAreaLength (PRS_Area arg, PRS_NatCon length);
PRS_Area PRS_setAreaWsAfterLength (PRS_Area arg, PRS_OptLayout wsAfterLength);
PRS_OptLayout PRS_visitOptLayout (PRS_OptLayout arg,
				  PRS_Layout (*acceptLayout) (PRS_Layout));
PRS_Layout PRS_visitLayout (PRS_Layout arg,
			    PRS_LexLayoutList (*acceptList)
			    (PRS_LexLayoutList));
PRS_LexLayoutList PRS_visitLexLayoutList (PRS_LexLayoutList arg,
					  PRS_LexLayout (*acceptHead)
					  (PRS_LexLayout));
PRS_RElem PRS_visitRElem (PRS_RElem arg,
			  PRS_Integer (*acceptInteger) (PRS_Integer),
			  PRS_StrCon (*acceptStrCon) (PRS_StrCon),
			  PRS_BoolCon (*acceptBoolCon) (PRS_BoolCon),
			  PRS_Location (*acceptLocation) (PRS_Location),
			  PRS_OptLayout (*acceptWsAfterBraceOpen)
			  (PRS_OptLayout),
			  PRS_RElemElements (*acceptElements)
			  (PRS_RElemElements),
			  PRS_OptLayout (*acceptWsAfterElements)
			  (PRS_OptLayout),
			  PRS_OptLayout (*acceptWsAfterBraceOpenBar)
			  (PRS_OptLayout),
			  PRS_OptLayout (*acceptWsAfterLessThan)
			  (PRS_OptLayout));
PRS_RType PRS_visitRType (PRS_RType arg,
			  PRS_OptLayout (*acceptWsAfterLessThan)
			  (PRS_OptLayout),
			  PRS_RTypeColumnTypes (*acceptColumnTypes)
			  (PRS_RTypeColumnTypes),
			  PRS_OptLayout (*acceptWsAfterColumnTypes)
			  (PRS_OptLayout),
			  PRS_OptLayout (*acceptWsAfterSet) (PRS_OptLayout),
			  PRS_OptLayout (*acceptWsAfterBracketOpen)
			  (PRS_OptLayout),
			  PRS_OptLayout (*acceptWsAfterElementType)
			  (PRS_OptLayout),
			  PRS_OptLayout (*acceptWsAfterBag) (PRS_OptLayout),
			  PRS_OptLayout (*acceptWsAfterRel) (PRS_OptLayout),
			  PRS_IdCon (*acceptTypeName) (PRS_IdCon),
			  PRS_OptLayout (*acceptWsAfterAmp) (PRS_OptLayout),
			  PRS_IdCon (*acceptParameterName) (PRS_IdCon));
PRS_RTuple PRS_visitRTuple (PRS_RTuple arg,
			    PRS_OptLayout (*acceptWsAfterLessThan)
			    (PRS_OptLayout),
			    PRS_IdCon (*acceptVariable) (PRS_IdCon),
			    PRS_OptLayout (*acceptWsAfterVariable)
			    (PRS_OptLayout),
			    PRS_OptLayout (*acceptWsAfterComma)
			    (PRS_OptLayout),
			    PRS_RType (*acceptRtype) (PRS_RType),
			    PRS_OptLayout (*acceptWsAfterRtype)
			    (PRS_OptLayout),
			    PRS_OptLayout (*acceptWsAfterComma1)
			    (PRS_OptLayout),
			    PRS_RElem (*acceptValue) (PRS_RElem),
			    PRS_OptLayout (*acceptWsAfterValue)
			    (PRS_OptLayout));
PRS_RStore PRS_visitRStore (PRS_RStore arg,
			    PRS_OptLayout (*acceptWsAfterRstore)
			    (PRS_OptLayout),
			    PRS_OptLayout (*acceptWsAfterParenOpen)
			    (PRS_OptLayout),
			    PRS_OptLayout (*acceptWsAfterBracketOpen)
			    (PRS_OptLayout),
			    PRS_RTupleRtuples (*acceptRtuples)
			    (PRS_RTupleRtuples),
			    PRS_OptLayout (*acceptWsAfterRtuples)
			    (PRS_OptLayout),
			    PRS_OptLayout (*acceptWsAfterBracketClose)
			    (PRS_OptLayout));
PRS_Start PRS_visitStart (PRS_Start arg,
			  PRS_OptLayout (*acceptWsBefore) (PRS_OptLayout),
			  PRS_RStore (*acceptTopRStore) (PRS_RStore),
			  PRS_OptLayout (*acceptWsAfter) (PRS_OptLayout),
			  int (*acceptAmbCnt) (int),
			  PRS_BoolCon (*acceptTopBoolCon) (PRS_BoolCon));
PRS_RElemElements PRS_visitRElemElements (PRS_RElemElements arg,
					  PRS_RElem (*acceptHead) (PRS_RElem),
					  PRS_OptLayout (*acceptWsAfterHead)
					  (PRS_OptLayout),
					  PRS_OptLayout (*acceptWsAfterSep)
					  (PRS_OptLayout));
PRS_RTypeColumnTypes PRS_visitRTypeColumnTypes (PRS_RTypeColumnTypes arg,
						PRS_RType (*acceptHead)
						(PRS_RType),
						PRS_OptLayout
						(*acceptWsAfterHead)
						(PRS_OptLayout),
						PRS_OptLayout
						(*acceptWsAfterSep)
						(PRS_OptLayout));
PRS_RTupleRtuples PRS_visitRTupleRtuples (PRS_RTupleRtuples arg,
					  PRS_RTuple (*acceptHead)
					  (PRS_RTuple),
					  PRS_OptLayout (*acceptWsAfterHead)
					  (PRS_OptLayout),
					  PRS_OptLayout (*acceptWsAfterSep)
					  (PRS_OptLayout));
PRS_LexStrChar PRS_visitLexStrChar (PRS_LexStrChar arg,
				    char (*acceptA) (char),
				    char (*acceptB) (char),
				    char (*acceptC) (char),
				    char (*acceptCh) (char));
PRS_StrChar PRS_visitStrChar (PRS_StrChar arg,
			      PRS_LexStrChar (*acceptStrChar)
			      (PRS_LexStrChar));
PRS_LexStrCon PRS_visitLexStrCon (PRS_LexStrCon arg,
				  PRS_LexStrCharChars (*acceptChars)
				  (PRS_LexStrCharChars));
PRS_StrCon PRS_visitStrCon (PRS_StrCon arg,
			    PRS_LexStrCon (*acceptStrCon) (PRS_LexStrCon));
PRS_LexStrCharChars PRS_visitLexStrCharChars (PRS_LexStrCharChars arg,
					      PRS_LexStrChar (*acceptHead)
					      (PRS_LexStrChar));
PRS_BoolCon PRS_visitBoolCon (PRS_BoolCon arg);
PRS_LexNatCon PRS_visitLexNatCon (PRS_LexNatCon arg,
				  char *(*acceptList) (char *));
PRS_NatCon PRS_visitNatCon (PRS_NatCon arg,
			    PRS_LexNatCon (*acceptNatCon) (PRS_LexNatCon));
PRS_LexIdCon PRS_visitLexIdCon (PRS_LexIdCon arg, char (*acceptHead) (char),
				char *(*acceptTail) (char *));
PRS_IdCon PRS_visitIdCon (PRS_IdCon arg,
			  PRS_LexIdCon (*acceptIdCon) (PRS_LexIdCon));
PRS_Integer PRS_visitInteger (PRS_Integer arg,
			      PRS_NatCon (*acceptNatCon) (PRS_NatCon),
			      PRS_OptLayout (*acceptWsAfterPlus)
			      (PRS_OptLayout),
			      PRS_OptLayout (*acceptWsAfter) (PRS_OptLayout));
PRS_LexLayout PRS_visitLexLayout (PRS_LexLayout arg, char (*acceptCh) (char));
PRS_Location PRS_visitLocation (PRS_Location arg,
				PRS_OptLayout (*acceptWsAfterFile)
				(PRS_OptLayout),
				PRS_OptLayout (*acceptWsAfterParenOpen)
				(PRS_OptLayout),
				PRS_StrCon (*acceptFilename) (PRS_StrCon),
				PRS_OptLayout (*acceptWsAfterFilename)
				(PRS_OptLayout),
				PRS_OptLayout (*acceptWsAfterA)
				(PRS_OptLayout),
				PRS_Area (*acceptArea) (PRS_Area),
				PRS_OptLayout (*acceptWsAfterArea)
				(PRS_OptLayout),
				PRS_OptLayout (*acceptWsAfterAreaInFile)
				(PRS_OptLayout),
				PRS_OptLayout (*acceptWsAfterComma)
				(PRS_OptLayout));
PRS_Area PRS_visitArea (PRS_Area arg,
			PRS_OptLayout (*acceptWsAfterArea) (PRS_OptLayout),
			PRS_OptLayout (*acceptWsAfterParenOpen)
			(PRS_OptLayout),
			PRS_NatCon (*acceptBeginLine) (PRS_NatCon),
			PRS_OptLayout (*acceptWsAfterBeginLine)
			(PRS_OptLayout),
			PRS_OptLayout (*acceptWsAfterComma) (PRS_OptLayout),
			PRS_NatCon (*acceptBeginColumn) (PRS_NatCon),
			PRS_OptLayout (*acceptWsAfterBeginColumn)
			(PRS_OptLayout),
			PRS_OptLayout (*acceptWsAfterComma1) (PRS_OptLayout),
			PRS_NatCon (*acceptEndLine) (PRS_NatCon),
			PRS_OptLayout (*acceptWsAfterEndLine) (PRS_OptLayout),
			PRS_OptLayout (*acceptWsAfterComma2) (PRS_OptLayout),
			PRS_NatCon (*acceptEndColumn) (PRS_NatCon),
			PRS_OptLayout (*acceptWsAfterEndColumn)
			(PRS_OptLayout),
			PRS_OptLayout (*acceptWsAfterComma3) (PRS_OptLayout),
			PRS_NatCon (*acceptOffset) (PRS_NatCon),
			PRS_OptLayout (*acceptWsAfterOffset) (PRS_OptLayout),
			PRS_OptLayout (*acceptWsAfterComma4) (PRS_OptLayout),
			PRS_NatCon (*acceptLength) (PRS_NatCon),
			PRS_OptLayout (*acceptWsAfterLength) (PRS_OptLayout));

#endif /* _PARSEDRSTORE_H */
