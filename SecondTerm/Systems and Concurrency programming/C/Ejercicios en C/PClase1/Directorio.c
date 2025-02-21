#include <stdio.h>
#include <stdlib.h>
#include "Directorio.h"

void crearDirectorio(ListaFicheros *lf)
{
    *lf = NULL;
}

void nuevoFichero(ListaFicheros *lf, char *nombre, unsigned int tam, ListaBloques *lb)
{
    crearNodo()
}

void crearDesdeFicheroTexto(char *nombre, ListaFicheros *lf, ListaBloques *lb)
{
    FILE *fd;
    fd=fopen(*nombre,"rt");

    if(fd==NULL){
        perror("No se ha podido abrir el fichero");
    }

    while (!feof(fd)){
        fscanf(fd,"%s %d",)
    }
}

void imprimirDirectorio(ListaFicheros lf)
{
}

void borrarDirectorio(ListaFicheros *lf, ListaBloques *lb)
{
}
