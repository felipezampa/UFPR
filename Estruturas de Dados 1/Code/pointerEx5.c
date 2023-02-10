#include<stdlib.h>
#include<stdio.h>

int main(void)
{
	int *vet, i;
	
	vet = (int *) malloc(10 * sizeof(int)); 
 	
 	for(i=0; i<10; i++)
 	{
 		printf("Digite um número para a posição %d ->", i);
 		scanf("%d", &vet[i]);
 	}
 
 	for(i=0; i<10; i++) 
 		printf("O número do índice %d eh %d \n", i, vet[i]);

 	return 0;
} 

 
