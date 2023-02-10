#include <stdio.h>

int main(void) {

    struct pessoa{
        char nome[50];
        int idade;
    };

    struct pessoa pessoas[3];
	struct pessoa *pPessoas = NULL;	
	int i = 0;
	pPessoas = pessoas;

	
	for (i = 0; i < 3; i++) {
		printf("Digite o nome: ");
		scanf("%s", pPessoas -> nome); 
		printf("Digite a idade: ");
		scanf("%d", &pPessoas -> idade); 
		pPessoas++;
	}
  	
	pPessoas = pessoas;

	
	for (i = 0; i < 3; i++) {
	    printf("Nome: %s\n", pPessoas -> nome);
	    printf("Idade: %d\n", pPessoas -> idade);
		pPessoas++;
	}
	
    return 0;
}
