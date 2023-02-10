#include <stdio.h>

int main(void){
	int x = 10, y = 10;
	int *px, *py;

	px = &x;
	py = &y;

	//Acesso direto e indireto a x e y
	printf("x = %d, y = %d, px = %d, py = %d\n", 
		x, y, *px, *py);

	return 0;
}

