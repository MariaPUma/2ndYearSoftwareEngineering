#include <stdio.h>

void leerEnteros (int* ptr, int cant){
    int array[cant];
    ptr = array;

    for(int i=0;i<cant;i++){
        printf("Introduzca el siguiente valor: ");
        scanf("%d",ptr+i);
    }

    printf("Valores introducidos:\n");
    for (int i = 0; i < cant; i++) {
        printf("%d ", *(ptr + i)); // Acceso a los valores a través del puntero
    }
    
}

int main (){
    int cantidad ;
    int *ptr= NULL;

    scanf("%d",&cantidad);

    leerEnteros(ptr,cantidad);

    printf("\n%d ",cantidad);
}
/*
#include <stdio.h>

void leerEnteros(int *puntero, int cantidad) {
    for (int i = 0; i < cantidad; i++) {
        printf("Introduce el valor %d: ", i + 1);
        scanf("%d", puntero + i);
    }
}

int main() {
    int valores[5]; // Definición de un array de enteros
    int *ptr = valores; // Puntero que apunta al primer elemento del array

    leerEnteros(ptr, 5); // Llamada al procedimiento leerEnteros

    // Mostrar los valores almacenados en el array
    printf("Valores introducidos:\n");
    for (int i = 0; i < 5; i++) {
        printf("%d ", *(ptr + i)); // Acceso a los valores a través del puntero
    }

    return 0;
}
*/