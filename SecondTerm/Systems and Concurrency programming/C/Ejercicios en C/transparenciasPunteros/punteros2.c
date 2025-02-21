#include <stdio.h>
#include <stdlib.h>

/* punteros y arrays. Cuidado. Este código tal como está no es correcto*/
int main(){
    int x = 3;
    char nombre[15] ="Maria"; //array de char / cadena caracteres 
    
    int *ptr_i;
    char *ptr_c;

    ptr_i = &x;
    ptr_c = nombre; //¿Es necesario el &? ¿Funciona o da error? ¿Por qué?

    printf("%d %s %s\n",*ptr_i, nombre, *ptr_c); //¿Es necesario el * en el tercer parámetro? ¿Funciona o da error? ¿Por qué?

    return 0;
}
