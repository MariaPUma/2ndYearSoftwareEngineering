/*
 * t46.c
 *  
 *   Uso de * y & con punteros en operaciones de E/S
 *   ¿Cuándo usarlos y cuando no?
 */

#include <stdio.h>
int main() {
	int *ptr; //puntero a una zona de memoria que almacena un valor de tipo int
	int x = 10;

	ptr = &x; //ptr almacena la direcci�n de memoria de x

	printf("La variable ptr contiene: %p y apunta a una zona de memoria con valor: %d\n",ptr, *ptr);
	printf("La direccion de memoria de la variable x es: %p\n",&x);
	printf("La direccion de memoria de la variable ptr es: %p\n",&ptr);

	printf("\nIntroduce un valor entero y lo guarda en x: ");
	scanf("%d",&x);
	printf("\nIntroduce otro valor entero y lo guarda en la zona de memoria a la que apunta ptr: ");
	scanf("%d",ptr); //No necesito poner el & porque ptr ya es un puntero a una zona de memoria.

	printf("\nLa variable x contiene: %d y la variable ptr apunta a una zona de memoria con valor: %d\n",x,*ptr);
	//Necesitamos poner el * a ptr, porque queremos mostrar el contenido de la zona de memoria a la que apunta, no el valor de ptr

	return 0;
}

