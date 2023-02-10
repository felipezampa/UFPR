#include<stdio.h>

int main(void)
{
	int v[] = {1, 2, 3, 4, 5}, i, *p;
	//p = v;
	p = &v[0];
	
 	for(i=0; i<5; i++) {
 		printf("%d ", *p);
 		p++;
 	}
 	printf("\n");
 	
 	return 0;
} 

