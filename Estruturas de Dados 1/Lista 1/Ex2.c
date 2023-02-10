#include <stdio.h>

int main() {
    int a,b,c;
    int *pa, *pb;

    printf("Digite o primeiro numero:");
    scanf("%d", &a);
    printf("Digite o segundo numero:");
    scanf("%d", &b);

    pa = &a;
    pb = &b;

    c = (pa > pb) ? *pa : *pb;
    printf("O numero com maior endereco de memoria eh o: %d",c);

    return 0;
}
