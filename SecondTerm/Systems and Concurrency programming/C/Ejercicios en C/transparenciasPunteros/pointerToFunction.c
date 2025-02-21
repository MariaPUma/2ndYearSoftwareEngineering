/*
 * pointerToFunction.c
 */

#include <stdio.h>

/* Es necesario que los par�metros sean punteros
 * para luego poder definir esMayor de forma gen�rica
 */
int esMayorInt(int *a, int *b) {
	return *a > *b;
}

/* Es necesario que los par�metros sean punteros
 * para luego poder definir esMayor de forma gen�rica
 */
int esMayorDouble(double *a, double *b) {
	return *a > *b;
}

/* Los dos primeros par�metros son punteros a cualquier tipo de datos
 * El tercer par�metro permite pasar como par�metro un puntero a cualquier
 * funci�n que reciba como par�metros dos punteros y devuelva un valor de tipo int
 */
int esMayor(void *a, void *b, int (*f)(void *, void *)) {
	return (*f)(a, b);
}

int main(void) {
	int a = 4, b = 5;
	double e = 6.0, f = 5.0;
	int result;

	//Llamada a esMayor para comparar valores de tipo int
	result = esMayor(&a, &b, (int (*)(void *, void *)) esMayorInt); //El casting es para evitar un warning
	if (result == 1)
		printf("%d es mayor que %d\n", a, b);
	else
		printf("%d no es mayor que %d\n", a, b);

	//Llamada a esMayor para comparar valores de tipo double
	result = esMayor(&e, &f, (int (*)(void*, void*)) esMayorDouble);
	if (result == 1)
		printf("%f es mayor que %f\n", e, f);
	else
		printf("%f no es mayor que %f\n", e, f);

	return 0;
} // main
