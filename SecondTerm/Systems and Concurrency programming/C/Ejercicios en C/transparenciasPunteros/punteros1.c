#include <stdio.h>
/*
 * punteros.c. Ejemplo de las transparencias
 */
int main(){
	//Declaramos dos variables
	int x = 3;
	int *ptr;

	printf("Valor: %d\n", *ptr);

	//ptr almacena la dirección de memoria de x
	ptr = &x;

	//mostramos por pantalla x y el valor al que apunta ptr
	printf("valor = %d %d", x, *ptr);

	return 0;
}


