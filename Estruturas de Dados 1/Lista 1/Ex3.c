#include <stdio.h>

int main() {
    int a[4], b[4],*pA,*pB;

    for (int i = 0; i < 5; i++) {
        printf("Digite o %d numero:", (i+1));
        scanf("%d", &(*(a+i)));
    }

    printf("primeiro array\n");
    for (int i = 0; i < 5; i++) {
        printf("Numero: %d\n", *(a+i));
    }

    for (int i = 0; i < 5; i++) {
        *(b+i) = *(a+i);
    }

    printf("segundo array\n");
    for (int i = 0; i < 5; i++) {
        printf("Numero: %d\n", *(b+i));
    }

    return 0;
}