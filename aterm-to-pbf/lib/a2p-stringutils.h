#ifndef STRINGUTILS_H_
#define STRINGUTILS_H_

#ifdef __cplusplus
extern "C"
{
#endif /* __cplusplus */

unsigned int hashString(char *str);

int stringIsEqual(char *str1, char *str2);

#ifdef __cplusplus
}
#endif /* __cplusplus */

#endif /* STRINGUTILS_H_ */

