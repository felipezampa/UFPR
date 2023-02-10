#include<stdio.h>

int main(void)
{
	struct student
	{
		int mat;
		float n1, n2;		
	};
	
	struct student aStudent, *pStudent;
	float mFinal;

	pStudent = &aStudent;
	printf("Digite a matricula, nota 1 e nota 2\n");
	scanf("%d", &pStudent -> mat);
	scanf("%f", &pStudent -> n1);
	scanf("%f", &pStudent -> n2);
	mFinal = (pStudent -> n1 + pStudent -> n2) / 2;
	printf("Aluno = %d e média = %.1f \n", pStudent -> mat, mFinal);
 		
 	return 0;
} 

