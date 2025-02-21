#include <stdio.h>
#include <stdlib.h>
#include "arbolbb.h"

/*
void Crear(T_Arbol *arbol)
{
    (*arbol)=NULL; //Inicializamos el puntero a NULL
}

void Destruir(T_Arbol *arbol) //Para destruir el árbol aplicaremos recursividad
{
    if (*arbol!=NULL){
        Destruir(&((*arbol)->izq));
        Destruir(&((*arbol)->der));
        free(*arbol);
        *arbol=NULL;
    }
}

void Insertar(T_Arbol *arbol, unsigned num)
{
    if(*arbol == NULL){//1ºCase: No hay nodos en el árbol
        T_Arbol nuevo = (T_Arbol) malloc(sizeof(struct T_Nodo));
        nuevo->dato = num;
        nuevo->der=NULL;
        nuevo->izq=NULL;

        *arbol = nuevo;

    }else{
        unsigned datoA=(*arbol)->dato;

        if(datoA < num){
            Insertar(&((*arbol)->izq),num);
        }else if (datoA > num){
            Insertar(&((*arbol)->der),num);
        }
    }
}

void Mostrar(T_Arbol arbol)
{
    if(arbol != NULL){
        Mostrar(arbol->izq);
        printf("%d ",arbol->dato);
        Mostrar(arbol->der);
    }
}

void Salvar(T_Arbol arbol, FILE *fichero)
{
    if (arbol!=NULL){
        unsigned elem= (arbol->dato);

        Salvar(arbol->izq,fichero);
        fwrite(&elem,sizeof(int),1,fichero);
        Salvar(arbol->der,fichero);
    }
}
*/