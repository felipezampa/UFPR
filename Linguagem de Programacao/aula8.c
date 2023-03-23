#include <stdio.h>
#include <stdlib.h>

int main() {
	int a;
	do{

		scanf ("%d",&a);
		if (a != 0){
		printf("%d",a);
			if (a % 2 == 0){
				printf(" par\n");
			}
			else{
				printf(" impar\n");
			}
		}
	}while (a != 0);
return 0;
}

