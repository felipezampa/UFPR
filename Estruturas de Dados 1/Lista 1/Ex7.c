#include <stdio.h>

int main() {

    struct pessoa{
        char nome[50];
        int idade;
    };

    struct pessoa umaPessoa, *pPessoa;
		
    pPessoa = &umaPessoa;
    fflush(stdin);

    printf("Digite o nome:");
    fgets(umaPessoa.nome,50,stdin);

    printf("Digite a idade:");
    scanf("%d",&pPessoa -> idade);

    printf("Nome:%sIdade:%d", pPessoa -> nome,pPessoa -> idade);

    return 0;
}
