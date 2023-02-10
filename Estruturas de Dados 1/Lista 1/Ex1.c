#include <stdio.h>
// Exercicio 1
int main(void) {

    int a = 5,*pa;
    char b = 'A',*pb;

    pa = &a;
    pb = &b;

    printf("Antes: %d %c\n", a, b);

    *pa = 10;
    *pb = 'B';
    printf("Depois: %d %c\n", a, b);

    return 0;
}
