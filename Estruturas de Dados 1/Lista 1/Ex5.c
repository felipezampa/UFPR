#include <stdio.h>
#include <stdlib.h>
int main(void) {
	
	int r = 3, p = 3, i, j;

	float* vet = malloc((r * p) * sizeof(int));
	
	for (i = 0; i < r * p; i++) {
	 vet[i] = i + 1;
	}
			
 	for (i = 0; i < r; i++) {
        for (j = 0; j < p; j++)
            printf("%f ", vet[i * p + j]);
           	
        printf("\n");
    }


		free(vet);
		return 0;	
			
	}


	



