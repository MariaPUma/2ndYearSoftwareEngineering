#include "Directorio.h"
#include <stdlib.h>
#include <stdio.h>
#include <string.h>

#define TAMBYTE 512


void crearDirectorio(ListaFicheros* lf)
{
    *lf= NULL;
}

/*funcion privada para devolver los bloques de la lista lb2 a la lista lb1*/
void devolverBloques(ListaBloques *lb1, ListaBloques *lb2){
	ListaBloques bloque;
	int hayBloque;

	do {
		obtenerBloque(lb2,&bloque);
		hayBloque = (bloque!=NULL);
		if (hayBloque) insertarBloque(lb1,bloque);
	}while(hayBloque);
}

void crearNodoFich (ListaFicheros *f, char *nombre, unsigned int tam)
{
    *f= (ListaFicheros)malloc(sizeof(struct NodoFichero));
    crear(&((*f)->bloques),0);
    (*f)->tam= tam;
    strcpy((*f)->nombre,nombre);
    (*f)->sig= NULL;
}

void nuevoFichero(ListaFicheros *lf, char *nombre, unsigned int tam, ListaBloques *lb)
{
    ListaFicheros nuevo;
    ListaBloques lista,bloq;

    crearNodoFich(&nuevo,nombre,tam);

    if(*lf==NULL){
        *lf= nuevo;
    }else{
        nuevo->sig= *lf;
        *lf= nuevo;
    }
    int hayEspacio=1,i=0;
    
    int numBloq = (tam% TAMBYTE !=0)?(((int)tam)/TAMBYTE)+1:((int)tam)/TAMBYTE;

    while(hayEspacio && i<numBloq ){
        obtenerBloque(lf,bloq);
        if(bloq!=NULL){
            insertarBloque(&(nuevo->bloques),bloq);
            i++;
        }else{
            hayEspacio=0;
        }
    }
    if(!hayEspacio){
        devolverbloq(lf,&(nuevo->bloques)); // junta ambas lista en la lista del primer argumento
        nuevo->tam=0;
    }
   
}

void crearDesdeFicheroTexto(char * nombre, ListaFicheros *lf, ListaBloques *lb)
{
    FILE * fd;
    int nleidos;
    char nom[30];
    unsigned int tam;

    if((fd= fopen(nombre,"rt"))==NULL){
        perror("No se pudo abrir el fichero");
    }else{
        crearDirectorio(lf);
        while((nleidos= fscanf(fd,"%s %d",nom,&tam))==2){
            nuevoFichero(lf,nom,tam,lb);
        }
        fclose(fd);
    }

}

void imprimirDirectorio(ListaFicheros lf)
{
    ListaFicheros ptr= lf;
    if(lf!=NULL){
    while (ptr!=NULL){
        printf("%s %6d ",ptr->nombre,ptr->tam);
        imprimir(ptr->bloques);
        ptr=ptr->sig;

    }
    }else{
        printf("El directorio esta vacío");
    }

}

void borrarDirectorio(ListaFicheros *lf, ListaBloques *lb){
    ListaFicheros borrar;

    while (*lf!=NULL){
        borrar=*lf;
        *lf= (*lf)->sig;
        devolverBloques(lb,&(borrar->bloques));
        free(borrar);
    }
    *lf=NULL;
}