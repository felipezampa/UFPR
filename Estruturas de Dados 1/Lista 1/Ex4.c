#include <stdio.h>

int main() {
    int a[4];

    for (int i = 0; i < 5; i++) {
        printf("Digite o %d numero:", (i+1));
        scanf("%d", &(*(a+i)));
    }

    for (int i = 0; i < 5; ++i) {
        if(a[i] % 2 == 0){
            printf("Endereco de um int par: %d\n",*(a+i));
        }
    }

    return 0;
}