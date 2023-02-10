#include <stdio.h>
#include <stdlib.h>

typedef struct no {
	int valor;
	struct no *proximo;
}No;


void copia_fila_para_pilha(No **fila, No **pilha) {
	int i = 0;
	for(i = 0; i < 6; i++) {
		*pilha = *fila; 
	}	
}
No* inserir_na_pilha(No *pilha, int num) {
	No *novo = malloc(sizeof(No));
	if (novo) {
		novo->valor = num;
		novo->proximo = pilha;
		return novo;
	}
	else 
		printf("Erro. ");
		return NULL;
}
No* remover_da_pilha(No **pilha) {
	if (*pilha) {
		No *remover = *pilha;
		*pilha = remover->proximo;
		return remover;
		
	}
	else
		printf("Pilha vazia. ");
		return NULL;
}

void inserir_na_fila(No **fila, int num) {
	No *aux, *novo = malloc(sizeof(No));
	if (novo) {
		novo->valor = num;
		novo->proximo = NULL;
		if(*fila == NULL) {
			*fila = novo;
			
		}
		else {
			aux = *fila;
			while (aux->proximo)
				aux = aux->proximo;
			aux->proximo = novo;
		}
	}
	else 
		printf("\n Error ao alocar memoria. \n");
		
}
No* remover_da_fila(No **fila) {
	No *remover = NULL;
	if (*fila) {
		remover = *fila;
		*fila = remover->proximo;
		
	}
	else 
		printf("Fila vazia");
	return remover;
	
}
void imprimir(No *pilha) {
	while (pilha) {
		printf("%d\n", pilha->valor);
		pilha = pilha->proximo;
	}
}
int main() {
	int i = 0, num;
	No *fila = NULL;
	No *pilha = NULL;
	// Preenche valores
	for(i = 0; i < 6; i++) {
		printf("Digite um valor para a fila: ");
		scanf("%d", &num);
		inserir_na_fila(&fila, num);
	}
	// Copia para Pilha
	for (i = 0; i < 6; i++) {
		copia_fila_para_pilha(&fila, &pilha);
	}
	// Zera fila.
	for (i = 0; i < 6; i++) {
		remover_da_fila(&fila);
	}
		
	imprimir(pilha);
	
}
