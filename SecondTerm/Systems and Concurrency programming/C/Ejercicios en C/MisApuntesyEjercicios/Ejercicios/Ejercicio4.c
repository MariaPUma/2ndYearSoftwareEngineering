#include <stdio.h>
#include <stdlib.h>

int main (){
    int cant; //cantidad de elementos del array

    scanf("%d",&cant); //solicitamos al usuario la cantidad

    //printf("%d",cant);

    int *ptr= (int *) malloc(cant* sizeof(int)); //reserva cant posiciones de memoria

    if(ptr==NULL){
        perror("ERROR: El puntero no apunta a ninguna posicion de memoria");
    }

   for(int i=0;i<cant;i++){
        printf("Introduzca el valor %d º: ",i+1);
        scanf("%d",ptr+i);
    }

    printf ("Los valores almacenados son: \n");

    for(int i =0;i<cant;i++){
        printf("%d ",*(ptr+i));
    }

    free(ptr);


}