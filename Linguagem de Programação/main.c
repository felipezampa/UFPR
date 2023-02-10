#include <stdio.h>
#include <stdlib.h>

int main()
{	
	int i,j,simb;
	 n
	for (i=0; i<16 ; i++){
		for (j=0; j<16 ;j++){
			simb = i*16 + j;
			if( simb < 32 ) {
				simb = 32;
			}
			printf("%c ",simb);
		}
		printf("\n");
	}
}
