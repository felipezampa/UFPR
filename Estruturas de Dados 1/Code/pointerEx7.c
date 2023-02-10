#include <stdio.h>
#include <stdlib.h>

void Troca(int **px, int **py)
{
	printf("Valor de px: %d\n", **px);
	printf("Valor de py: %d\n", **py);
	int *temp;
 	temp = *px;
 	*px = *py;
 	*py = temp;
}

int main( )
{
	 int *x, *y;
	 x = malloc(sizeof(int));
	 y = malloc(sizeof(int));
	 *x =10;
	 *y =20;
	 Troca(&x, &y);
	 printf("Valor de x: %d\n", *x);
	 printf("Valor de y: %d\n", *y);

	 return 0;
} 