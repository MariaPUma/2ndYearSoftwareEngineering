#include <stdio.h>
#include <stdlib.h>
#include "Bloques.h"
#define TAM 512

void crearNodo (ListaBloques *nuevo,unsigned int dirini){
    *nuevo = (ListaBloques)malloc(sizeof(struct Nodo));
    
    if(*nuevo!=NULL){
    (*nuevo)->dirInicio= dirini;
    (*nuevo)->sig= NULL;
    }
    

}
void crear(ListaBloques *lb, unsigned int tamMemoria)
{
    *lb= NULL;
    int size =(tamMemoria==0)?-1:( (int) tamMemoria )/512;
    ListaBloques nuevo;

    for(int i=0;i<size;i++){
        crearNodo(&nuevo,TAM*i);
        insertarBloque(lb,nuevo);
    }
}

void obtenerBloque(ListaBloques *lb, ListaBloques *bloque)
{
    *bloque = *lb;
    if(*lb!=NULL){
        *lb = (*lb)->sig;
        (*bloque)->sig= NULL;
    }
}

void insertarBloque(ListaBloques *lb, ListaBloques bloque)
{
    ListaBloques ptr, ant ;
    ptr= *lb;
    ant =NULL;

    while (ptr!=NULL && (ptr->dirInicio)<(bloque->dirInicio)){
        ant= ptr;
        ptr= ptr->sig;
    }

    if(ant == NULL){
       bloque ->sig= ptr;
       *lb =bloque;
    }else{
        bloque->sig = ptr;
        ant->sig= bloque;
    }
}

void imprimir(ListaBloques lb)
{
    printf("(");

    while(lb!=NULL){
        printf("%d ",lb->dirInicio);
        lb= lb->sig;
    }

    printf(") \n");
    
}

void borrar(ListaBloques *lb)
{
    ListaBloques ptr= *lb;

    while (ptr!= NULL){
        ptr= *lb;
        *lb= (*lb)->sig;
        free(ptr);
    }
}
