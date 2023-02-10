#include <stdio.h>
#include <stdlib.h>
#include <math.h>
//Programa que valida um cpf

int main(){
	int i,cpf[12],cpfaux;
	printf("Digite o seu CPF (com apenas numeros): ");
	scanf("%d", &cpfaux);
		for (i=11 ; i=0 ; i--){
		cpf[i]= cpfaux / 10;
		cpfaux = cpfaux / 10;
		}
	printf("%d\n",cpf[3]);
		//for (i=0 ; i<11 ; i++){	
	//}
	return 0;
}
