#include "ListaCircular.h"
#include <stdlib.h>
#include <stdio.h>
#include <string.h>

void crear(TListaCircular *lc)
{
    *lc=NULL,
}

void crearNodo(TListaCircular *nuevo, char *nombre){
    *nuevo= (TListaCircular)malloc(sizeof(struct TNodo));
    strcpy((*nuevo)->nombre,nombre);
    (*nuevo)->sig= NULL;
}

void insertar(TListaCircular *lc, char *nombre)
{
    TListaCircular nuevo;
    crearNodo(&nuevo,nombre);

    if(*lc == NULL){
        *lc =nuevo;
        (*lc)->sig= nuevo;
    }else{
        char *nombreprimero = (*lc)->nombre;
        TListaCircular ptr=(*lc)->sig, ant=*lc;

        while ((strcmp(ptr->nombre,nombreprimero)!=0)){
            ant= ptr;
            ptr= ptr->sig,
        }

        
    }
}

void recorrer(TListaCircular lc)
{
}

int longitud(TListaCircular lc)
{
    return 0;
}

void mover(TListaCircular *lc, int n)
{
}

void extraer(TListaCircular *lc, char *nombre)
{
}
