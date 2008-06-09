/* $Id: logging.h 18213 2006-03-17 14:05:00Z economop $ */

#ifndef SGLR_FILES_H
#define SGLR_FILES_H

#include <stdio.h>

FILE  *LOG_OpenLog(const char *prg, const char *fnam);
void LOG_CloseFile(FILE *fd);
char *LOG_ReadFile(const char *prg, 
			 const char *err, 
			 const char *fnam, 
			 size_t *fsize);
FILE *LOG_OpenFile(const char *std_error, const char *FN);
size_t LOG_FileSize(const char *FN);
FILE *LOG_log();

#endif
