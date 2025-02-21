#include "gestion_memoria.h"
#include <stdio.h>
#include <stdlib.h>

#define MAX 1000

void crear(T_Manejador *manejador)
{
    *manejador = (T_Manejador)malloc(sizeof(struct T_Nodo));
    (*manejador)->inicio=0;
    (*manejador)->fin= MAX-1;
    (*manejador)->sig=NULL;
}
void eliminarPrimPos (T_Manejador *man){
    if(*man != NULL){
        T_Manejador ptr= *man;
        
        *man= (*man)->sig;
        free(ptr);
    }
}

void destruir(T_Manejador *manejador)
{
    while (*manejador!=NULL){
        eliminarPrimPos(manejador);
    }
}

void obtener(T_Manejador *manejador, unsigned tam, unsigned *dir, unsigned *ok)
{
    //Debemos de saber si el nodo siguiente tiene espacio suficiente para almacenar 
       T_Manejador ant, ptr;
    *ok = 0;
    ant= NULL;
    ptr= *manejador;

    while (ptr!=NULL && !(*ok)){
        //Iteramos en la lista hasta que necontremos tamaño suficiente para meter el archivo
        unsigned tam_actual= (ptr->fin)-(ptr->inicio) +1;

        if (tam <= tam_actual){
            (*ok)= 1; //hemos encontrado espacio
        }else{
            //seguimos en la lista
            ant= ptr;
            ptr= ptr->sig;
        }

    } 

    if((*ok)){
        //hemos encontrado un espacio
        *dir = ptr->inicio;
        //Tomamos el tamaño disponible 
        unsigned tam_actual= (ptr->fin)-(ptr->inicio) +1;
         
        if (tam == tam_actual){
            //Ocupa todo el espacio
            if (ant ==NULL){
                //Estamos al inicio de la lista
                ant =  (*manejador);
                (*manejador) = (*manejador)->sig ;
            }
                //nodo intermedio o final
                ant->sig= ptr->sig;
        }else{//toma una parte del almacenamiento del nodo
            ptr->inicio= ptr->inicio + tam;
        }

    }
    

}

void mostrar(T_Manejador manejador)
{
    printf ("----------------- \n");

    while (manejador!=NULL){
        printf("Desde %d a %d : Libre\n",manejador->inicio,manejador->fin);
        manejador= manejador->sig;
    }

    fflush(stdout);

}

void devolver(T_Manejador *manejador, unsigned tam, unsigned dir)
{
    T_Manejador ptr, ant;
    ptr=*manejador;
    ant = NULL;

    while(ptr!=NULL && (ptr->inicio)<dir){
        ant = ptr;
        ptr=ptr->sig;
    }

    T_Manejador nuevo;
    nuevo = (T_Manejador) malloc (sizeof(struct T_Nodo));

    nuevo->inicio= dir;
    nuevo->fin= dir + tam - 1;
    nuevo->sig = ptr;

    if(ant== NULL){
        (*manejador)= nuevo;

    }else{
        ant->sig = nuevo;
    }

}
