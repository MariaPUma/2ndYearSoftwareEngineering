#include "Bloques.h"
#include <stdlib.h>
#include <stdio.h>
#define TAMBYTE 512

void crearNodo (ListaBloques *novo, unsigned int dirini){
    *novo  =(ListaBloques)malloc(sizeof(struct Nodo));
    (*novo)->dirInicio= dirini;
    (*novo)->sig= NULL;
}

void crear(ListaBloques *lb, unsigned int tamMemoria)
{
    *lb=NULL;
    ListaBloques nuevo;
    int size =(tamMemoria==0)?-1:((int)tamMemoria)/512;
    for(int i = 0;i<size;i++){
        crearNodo(&nuevo,i*TAMBYTE);
        insertarBloque(lb,nuevo);
    }
}

void obtenerBloque(ListaBloques *lb, ListaBloques *bloque)
{
    *bloque=NULL;
    if(*lb!=NULL)
    {
        ListaBloques ptr=*lb;
        *lb=ptr->sig;
        ptr->sig=NULL;
        *bloque= ptr;  
    }
}

void insertarBloque(ListaBloques *lb, ListaBloques bloque)
{    
    if(*lb==NULL)
    {
        *lb= bloque;
    }else
    {
        ListaBloques ptr,ant;
        ptr= *lb;
        ant= NULL;

        while (ptr!=NULL && (ptr->dirInicio)<(bloque->dirInicio))
        {
            ant= ptr;
            ptr= ptr->sig;
        }

        if(ant==NULL){
            *lb= bloque;
            bloque->sig= ptr;
        }else{
            if(ptr==NULL)
            {
                ant->sig= bloque;
            }else
            {
                ant->sig= bloque;
                bloque->sig= ptr;
            }
        }
    }

}

void imprimir(ListaBloques lb)
{
    ListaBloques ptr=lb;
    printf("(");

    while(ptr!=NULL){
        printf("%d ",ptr->dirInicio);
        ptr=ptr->sig;
    }
    printf(")\n");

}

void borrar(ListaBloques *lb)
{
    ListaBloques borrar=*lb;
    while (borrar!=NULL)
    {
        *lb= (*lb)->sig;
        free(borrar);
        borrar=*lb;
    }
    
}