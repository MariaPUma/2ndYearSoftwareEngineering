/*
 * t55.c
 */

#include <stdio.h>

//Declaraci�n de tipos
typedef struct Punto *Ppunto;
struct Punto{
	int x,y;
};

int main() {
	//struct Punto *ptr_punto; // no se usa el tipo Ppunto definido
	Ppunto ptr_punto; //ptr_punto es un puntero a un registro

	//Si comentamos las dos l�neas siguientes el programa no compila porque el puntero
	//no apunta a ninguna zona de memoria v�lida
	struct Punto p; //Necesitamos una variable de tipo registro a la que apunte el puntero
	ptr_punto = &p; //El puntero apunta ahora a una zona de memoria donde est� el registro

	ptr_punto->x=10;
	ptr_punto->y=5;

	printf("(%d,%d)\n",ptr_punto->x,ptr_punto->y);

	return 0;
}

