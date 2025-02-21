#include <stdio.h>
void mostrarEnteros (int* ptr,int cant){
    for(int i =0;i<cant;i++){
        printf("%d ",*ptr+i);
    }
}

int main (){
    int array[5]= {3,4,6,7,8};

    mostrarEnteros(array,5);
}