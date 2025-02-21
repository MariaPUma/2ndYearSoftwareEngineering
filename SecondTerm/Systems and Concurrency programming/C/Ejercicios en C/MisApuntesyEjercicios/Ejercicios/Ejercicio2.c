#include <stdio.h>

void intercambiar (int **pt1,int **pt2 ){
    int *aux= *pt1;
    *pt1= *pt2;
    *pt2= aux;


}
int main (void){
    int *ptr1;
    int *ptr2;

    int x=1;
    int y=2;

    ptr1= &x;
    ptr2= &y;

    intercambiar(&ptr1,&ptr2);

    printf("El valor del puntero 1: %d y el valor del puntero 2: %d",*ptr1,*ptr2);

}