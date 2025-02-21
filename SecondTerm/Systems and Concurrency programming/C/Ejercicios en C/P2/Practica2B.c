/*
 ============================================================================
 Name        : Practica2B.c
 Author      : esc
 Version     :
 Copyright   : Your copyright notice
 Description : Hello World in C, Ansi-style
 ============================================================================
 */

#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include "arbolbb.h"


/**
 * Pide un n�mero "tam" al usuario, y
 * crea un fichero binario para escritura con el nombre "nfichero"
 * en que escribe "tam" numeros (unsigned int) aleatorios
 * Se utiliza srand(time(NULL)) para establecer la semilla (de la libreria time.h)
 * y rand()%100 para crear un n�mero aleatorio entre 0 y 99.
 */
void creafichero(char* nfichero){
	int TAM;
	unsigned n;
	

	FILE *fed;
	fed = fopen(nfichero, "wb");	//Creamos un fichero binario vació

	if (fed == NULL){
		perror("Error creando el fichero"); 	//Comprobamos si se ha creado correctamente
	}else{
		
		printf("Ingrese un tam:");

		scanf("%d",&TAM);

		srand(time(NULL));

		for (int i = 0; i < TAM; i++)
		{
			/* code */
			n= rand() % TAM;
			fwrite(&n,sizeof(int),1,fed);
		}
		fclose(fed);
	}

}
/**
 * Muestra por pantalla la lista de n�meros (unsigned int) almacenada
 * en el fichero binario "nfichero"
 */
void muestrafichero(char* nfichero){

	FILE *f; 

	if ((f = fopen(nfichero,"rb"))==NULL){
		perror("Error abriendo el fichero");
	}

	int num,leidos; //elementos del fichero
 
    printf("[ ");

	while ((leidos= fread(&num,sizeof(int),1,f))>0){	//Hacemos un bucle para poder ir leyendo hasta que fread devuelva -1 (ya no hay más que leer)
		printf("%d ",num);
	}
	printf("]");
	fclose(f);

}

/**
 * Guarda en el arbol "*miarbol" los n�meros almacenados en el fichero binario "nfichero"
 */

void cargaFichero(char* nfichero, T_Arbol* miarbol){
	int valor,leidos;
	
	FILE *f; 

	if ((f = fopen(nfichero,"rb"))==NULL){
		perror("Error abriendo el fichero");
	}

	while ((leidos = fread(&valor,sizeof(int),1,f)) > 0){
		Insertar(miarbol,valor);
	}

	fclose(f);


}

int main(void) {
	setvbuf(stdout,NULL,_IONBF,0);

	char nfichero[50];
	printf ("Introduce el nombre del fichero binario:\n");
	fflush(stdout);
	scanf ("%s",nfichero);
	fflush(stdin);
	creafichero(nfichero);
	printf("\nAhora lo leemos y mostramos:\n");
	muestrafichero(nfichero);
	fflush(stdout);

	printf ("\nAhora lo cargamos en el arbol\n");
	T_Arbol miarbol;
	Crear (&miarbol);
	cargaFichero(nfichero,&miarbol);
	printf ("\nY lo mostramos ordenado\n");
	Mostrar(miarbol);
	fflush(stdout);
	printf("\nAhora lo guardamos ordenado\n");
	FILE * fich;
	fich = fopen (nfichero, "wb");
	Salvar (miarbol, fich);
	fclose (fich);
	printf("\nY lo mostramos ordenado\n");
	muestrafichero(nfichero);
	Destruir (&miarbol);

	

	return EXIT_SUCCESS;
}
