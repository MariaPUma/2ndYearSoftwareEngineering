#include "lista.h"
#include <stdlib.h>
#include <stdio.h>
#include <string.h>

void crear (Lista *l){
    *l= NULL;
}

int lista_vacia (Lista l){
    return (l==NULL);
}

void escribir (Lista l){
    Lista ptr= l;
    while (ptr!=NULL){
        printf("%s, ",ptr->palabra);
        ptr= ptr->sig;
    }
    printf("\n");
}

void escribir_fichero(FILE *fp, Lista l)
{
    Lista ptr= l;

    while (ptr!=NULL){
        fprintf(fp, "%s, ",ptr->palabra);
        ptr= ptr->sig;
    }
    fprintf(fp,"\n");

}

/*
 * Inserta una palabra al final de una lista enlazada.
 * No comprueba si la palabra existe, si se desea no repetir palabras
 * se debe utilizar buscar_palabra() y comprobar antes de invocar esta función
 * palabra: la palabra que se desea insertar
 * l: lista enlazada de palabras
 */

void crearNodo(Lista *nuevo,char* nombre){
    *nuevo = (Lista)malloc(sizeof(struct item));
    (*nuevo)->sig=NULL;
    strcpy((*nuevo)->palabra,nombre);
}
void insertar(char *palabra, Lista *l)
{
   Lista nuevo,ptr=*l;
   crearNodo(&nuevo, palabra);
    if(ptr==NULL){
        *l=nuevo;
   }else{
        while((ptr->sig)!=NULL){
                ptr =ptr->sig;
        }
            ptr->sig= nuevo;
   
   }
}


void destruir(Lista* l){
    Lista ptr= *l;
    while (ptr!=NULL){
        *l= (*l)->sig;
        free(ptr);
        ptr= *l;
    }
}

int buscar_palabra(char* palabra, Lista l){
    Lista ptr;
    ptr= l;
    int esta=0;

    while (ptr!=NULL && !(esta)){
        
        if(strcmp(palabra,ptr->palabra)==0){
            esta= 1;
        }
        ptr= ptr->sig;
    }

   return esta;
}