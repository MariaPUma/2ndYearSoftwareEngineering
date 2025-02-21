/*
 ============================================================================
 Name        : prPalabras3_13.c
 Author      : PONGA SU NOMBRE AQUI <<<<<<<<<<<<<<<<<<<<<
 Version     : 1
 Copyright   : Examen parcial abril 2017
 Description : Programa principal, Ansi-style
 ============================================================================
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "lista.h"
#define MIN_LETRA (3)
#define MAX_LETRA (13)
#define NUM_TAMANO (MAX_LETRA - MIN_LETRA +1)


void escribir_salida(FILE * fp, Lista* lp){
	for(int i=0;i<NUM_TAMANO;i++){
		fprintf(fp,"Palabras de %d letras \n", i+3);
		if(lista_vacia(lp[i])){
			fprintf(fp,"No se han encontrado palabras de %d letras \n",i+3);
		}else{
			escribir_fichero(fp,lp[i]);
		}
	}
}


int main(void) {

	setvbuf(stdout, NULL, _IONBF, 0);
	setvbuf(stderr, NULL, _IONBF, 0);
	char *inputFileName = "Lorem_Ipsum.txt";
	char *outputFileName = "Palabras3_13.txt";


	/* Crear Array de Palabras de tamaño NUM_TAMANO */
	Lista arrayPalabra[NUM_TAMANO];

	/* Incializar lista palabras */
	for(int i= 0;i<NUM_TAMANO;i++){
		crear(&arrayPalabra[i]);
	}

	/* Abrir Fichero de Entrada */
	FILE *fen= fopen(inputFileName,"rt");

	if(fen==NULL){
		perror("No se puede abrir el archivo");
		exit(-1);
	}

	/* Leer palabras del fichero de entrada */
	char palabra[30];
	while (fscanf(fen,"%s",palabra)>0){
		int longitud = strlen(palabra);
		if(longitud>=3 && longitud<= 13 && !buscar_palabra(palabra,arrayPalabra[longitud-3])){
			insertar(palabra,&arrayPalabra[longitud-3]);
		}

	}

	/* Escribir en consola */
	for(int i=0;i<NUM_TAMANO;i++){
		printf("Palabras de %d letras \n", i+3);
		if(lista_vacia(arrayPalabra[i])){
			printf("No se han encontrado palabras de %d  \n",i+3);
		}else{
			escribir(arrayPalabra[i]);
		}
		printf("\n");
		
	}

	/* Escribir archivo (para el apartado B) */
	FILE *fsal= fopen(outputFileName,"wt");
	escribir_salida(fsal,arrayPalabra);
	fclose(fsal);
	fclose(fen);

	/* Destruir las listas creadas */
	for(int i= 0;i<NUM_TAMANO;i++){
		destruir(&arrayPalabra[i]);
	}


	return EXIT_SUCCESS;
}
