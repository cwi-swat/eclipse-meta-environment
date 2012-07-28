#include <a2p-stringutils.h>

#include <string.h>

unsigned int hashString(char *string){
	unsigned int h = 0;
	
	int i = 0;
	char c;
	while((c = string[i++]) != '\0'){
		h *= -127;
		h += c;
	}
	
	return h;
}

int stringIsEqual(char *str1, char *str2){
        int length1 = strlen(str1);
        int length2 = strlen(str2);
        
        if(length1 != length2) return 0;
        
        return (strncmp(str1, str2, length1) == 0) ? 1 : 0;
}

