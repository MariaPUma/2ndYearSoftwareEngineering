#include "lista.h"
#include <stdlib.h>
#include <stdio.h>
#include <string.h>


void crear(TLista * lista)
{
    *lista= NULL;
}

int estaVacia(TLista lista)
{
    return (lista == NULL);
}

TLista crearNodo (unsigned int pos, char *nombre){
    TLista nuevo = (TLista)malloc(sizeof(struct TNodo));
    if(nuevo!=NULL){
        nuevo->nFrame= pos;
        strcpy(nuevo->nombre,nombre);
        nuevo->sig= NULL;
    }else{
        perror("No se pudo reservar almacenamiento");
        exit(-1);
    }
    return nuevo;
}

void insertar(TLista *lista, unsigned int pos, char *nombre)
{
    
    TLista nuevo = crearNodo(pos,nombre);

    if(nuevo!=NULL){
        if(*lista ==NULL){
            *lista= nuevo;
        }else{

            TLista ptr, ant;
            ptr= *lista;
            ant = NULL;

            while (ptr!=NULL && (ptr->nFrame)<pos){
                ant= ptr;
                ptr= ptr->sig;
            }

            if(ant ==NULL && (ptr->nFrame != pos)){
                *lista= nuevo;
                nuevo->sig= ptr;
            }else{
                if(ptr==NULL){
                    ant->sig = nuevo;
                }else if ((ptr->nFrame)!=pos){
                    ant->sig = nuevo;
                    nuevo->sig= ptr;
                }
            }
        }
    }
    
}

void crearDesdeFichero(TLista *lista, FILE *fich)
{
    unsigned int frame;
    char nombre[30];
    crear(lista);
    while(!feof(fich)){
        fscanf(fich,"%d %s ",&frame,&nombre);
        insertar(lista,frame,nombre);
    }

}

void mostrar(TLista lista)
{
    TLista ptr;
    ptr= lista;
    while (ptr!= NULL){
        printf("%d %s \n",ptr->nFrame,ptr->nombre);
        ptr= ptr->sig;
    }
    printf("\n");
}

void copiar_a_fichero (TLista * l, FILE *fd,unsigned int *numFrames){
    *numFrames= 0;
    if(*l !=NULL){
        TLista ptr=*l;

        while(ptr!=NULL){
            ptr=*l;
            fprintf(fd,"%d %s \n",ptr->nFrame,ptr->nombre);
            numFrames++;
            *l= (*l)->sig;
            free(ptr);
        }
    }
}

void sacar(TLista *lista, unsigned int salto, unsigned int *numFrames, FILE *eliminados)
{
    //Si el salto es de 0 el método sacar está haciendo el método destruir pero almacenando toda la lista en 
    //el fichero de eliminados

    if(salto < 0){ //Se ha introducido un valor negativo
        perror("Valor de salto erróneo");
        exit(-1);
    }else if (salto == 0){ // Se ha introducido como salto el 0
        copiar_a_fichero(lista,eliminados,numFrames);
    }else{ // Salto Es un valor aceptable
        TLista ptr,ant;
        int i=0;

        while(ptr!=NULL){
            if(salto == i){ //Cuanto el contador de salto haya completado la cantidad de salto
                i=0;
                TLista borrar= ptr; //variable que borra el nodo

                fprintf(eliminados,"%d %s \n",ptr->nFrame,ptr->nombre); //almacenamos en el fichero
                ant->sig= ptr->sig;
                ptr= ptr->sig;
                free(borrar);
                (*numFrames)++;

            }else{ //Caso: No se ha comppletado la cantidad de salto
                ant= ptr;
                ptr=ptr->sig;
                i++; // sumamos cada salto que da el algoritmo
            }
        }

    }
}
    

int eliminar(TLista *lista, unsigned int *numFrame, char *nombre)
{
    if (*lista==NULL)
    {
        return 0;
    }else
    {
        TLista ptr= *lista;
        *numFrame= ptr->nFrame;
        strcpy(nombre,ptr->nombre);
        *lista= (*lista)->sig;
        free(ptr);
    }
    
    return 1;
}

void destruir(TLista *lista)
{
    TLista borrar= *lista;

    while (*lista!=NULL){
        *lista= (*lista)->sig;
        free(borrar);
        borrar=*lista;
    }
}
