#ifndef RSTORE_UITLS_H
#define RSTORE_UITLS_H
#include <RStore.h>
#include <ParsedRStore.h>
RS_RStore RS_lowerRStore(PRS_RStore store);
PRS_RStore RS_liftRStore(RS_RStore store);
PRS_RTuple RS_liftRTuple(RS_RTuple tuple);
#endif
