#include <stdio.h>

int main (void){
    int *ptr= NULL;
    //int x=10;
    //ptr =&x;
    printf("El puntero apunta a la posicion de memoria: %p\n",ptr);
    printf("El puntero apunta a la variable: %d\n",*ptr);
}