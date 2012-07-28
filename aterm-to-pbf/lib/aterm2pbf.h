#ifndef ATERM2PBF_H
#define ATERM2PBF_H

#include <pdbtypes.h>

#include <aterm2.h>

#ifdef __cplusplus
extern "C"
{
#endif/* __cplusplus */

char *A2Pserialize(ATerm term, A2PType topType, int *length);

#ifdef __cplusplus
}
#endif/* __cplusplus */

#endif/* ATERM2PBF_H*/
