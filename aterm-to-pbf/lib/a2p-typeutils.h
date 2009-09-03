#ifndef TYPEUTILS_H_
#define TYPEUTILS_H_

#include <pdbtypes.h>

#ifdef __cplusplus
extern "C"
{
#endif /* __cplusplus */

int typeIsEqual(A2PType type1, A2PType type2);

unsigned int hashType(A2PType type);

#ifdef __cplusplus
}
#endif /* __cplusplus */

#endif /* TYPEUTILS_H_ */

