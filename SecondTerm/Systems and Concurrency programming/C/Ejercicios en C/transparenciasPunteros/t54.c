/*
 * t54.c
 */

#include <stdio.h>
int main() {
	int a[8]={1,2,3,4,5,6,7,8};
	int *ptr;

	printf("Tamanio int: %d\n",(int)sizeof(int));

	ptr = &a[0];
	//ptr = a; //funcionaria tambien asi?
	for (int i=0; i<8; i++){
		printf("Valor: %d ",*ptr);
		printf("Direccion en ptr: %p\n",ptr);
		ptr = ptr + 1;
	}

	return 0;
}