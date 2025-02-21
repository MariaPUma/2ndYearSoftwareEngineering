#include <stdio.h>

int main(){
	void *ptr;
    int valor = 10;
    char car = 'a';

    ptr = &valor;
    printf("Entero: %d\n", *(int *)ptr);

    ptr = &car;
    printf("Caracter: %c\n", *(char *)ptr);
    
	return 0;
}