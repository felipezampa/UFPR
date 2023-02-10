#include <stdio.h>
#include <stdlib.h>

int mystrchr(const char *s, const char ch) {
   char *p=s;
   while( *p ) {
      if( *p == ch ) {
        return (int)(p-s);
      }
      p++;
   }
   return -1;
}

int main()
{	
    char string[101],simbolo;
	scanf("%s",&string);
	scanf("%d",&simbolo);
    printf("%d\n",mystrchr(string,'simbolo'));


    return 0;
}
