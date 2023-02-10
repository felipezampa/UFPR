#include <stdio.h>
#include <stdlib.h>
struct _COR_WEB {
	unsigned b:8;
	unsigned g:8;
	unsigned r:8;
	unsigned alfa;
};

int main()
{
	unsigned long cor1 = 0xFF09a0;
	struct _COR_WEB cor;
	cor = *((struct _COR_WEB *)(&cor1));
	printf("Cor (long): %lx\n",cor1);
	printf("Cor: r=%x (%d) g=%x (%d) b=%x (%d)\n",cor.r,cor.r,cor.g,cor.g,cor.b,cor.b);
	return 0;
}
