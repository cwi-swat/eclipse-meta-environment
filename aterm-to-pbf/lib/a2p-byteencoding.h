#ifndef BYTEENCODING_H_
#define BYTEENCODING_H_

#ifdef __cplusplus
extern "C"
{
#endif /* __cplusplus */

int A2PserializeMultiByteInt(int i, char *c);

void A2PserializeDouble(double d, char *c);


int A2PdeserializeMultiByteInt(char *c, unsigned int *i);

double A2PdeserializeDouble(char *c);

#ifdef __cplusplus
}
#endif /* __cplusplus */ 

#endif /*BYTEENCODING_H_*/
