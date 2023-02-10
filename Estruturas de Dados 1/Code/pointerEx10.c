#include<stdio.h>

int main(void)
{
	struct student
	{
		int mat;
	};
	
	struct student aStudent, *pStudent;

	pStudent = &aStudent;
	printf("Digite a matricula\n");
	scanf("%d", &pStudent -> mat);
	printf("Aluno = %d \n", (*pStudent).mat);
	printf("Aluno = %d \n", pStudent -> mat);
 		
 	return 0;
} 

