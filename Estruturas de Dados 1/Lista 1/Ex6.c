#include <stdio.h>
#include <stdlib.h>

int main (void) {
	int numMax, numMin;
	int vInteiros[10];
	menorMaior(10, numMax, numMin, vInteiros);

}

void menorMaior(int n, int numMax, int numMin, int *vInteiros) {
	int i;
	for (i = 0; i < n; i++) {
		printf("Digite os valores do Vetor:");
		scanf("%d", &vInteiros[i]);
	}
	for (i = 0; i< n; i++) {
		if (numMin > vInteiros[i] ) {
			numMin = vInteiros[i];
		}
	}
	for (i = 0; i< n; i++) {
		if (numMax < vInteiros[i] ) {
			numMax = vInteiros[i];
		}
	}	
	printf("O menor valor e: %d \nO maior valor e: %d",  numMin, numMax);
}
