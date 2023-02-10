#include<stdio.h>

int main(void)
{
	struct date
	{
		int day;
		int month;		
	};
	
	struct date aDate, *pDate;

	pDate = &aDate;
	pDate -> day = 21;
	pDate -> month = 10;

	printf("Data = %d/%d \n", pDate -> day, pDate -> month);
 		
 	return 0;
} 

