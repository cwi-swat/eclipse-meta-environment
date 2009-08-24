#include <stringutils.h>

unsigned int hashString(char *string){
        /* TODO Implement. */
        return 0; /* Temp. */
}

int stringIsEqual(char *str1, char *str2){
        int length1 = strlen(str1);
        int length2 = strlen(str2);
        
        if(length1 != length2) return 0;
        
        return (strncmp(str1, str2, length1) == 0) ? 1 : 0;
}

