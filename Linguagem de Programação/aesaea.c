#include <stdio.h>
#include <stdlib.h>

int funcao(int a, int b){
while(b != 0){
    int aux = a % b;
    a = b;
    b = aux;
}
return a;
}

int main() {
	int num1,num2,mmc,aux,a;
		
	printf("Digite os 2 numeros: \n");
	scanf("%d",&num1);
	scanf("%d",&num2);
	
	aux = funcao(num1,num2);
	mmc = num1 * (num2 / aux);
    printf("o minimo multiplo comum deles= %d\n", mmc);
    return 0;
}
