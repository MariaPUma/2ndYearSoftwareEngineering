/*
 * Directorio.h
 *
 *  Created on: 16 abr. 2021
 *      Author: Monica
 */

#ifndef DIRECTORIO_H_
#define DIRECTORIO_H_

#include "Bloques.h"

typedef struct NodoFichero *ListaFicheros;
struct NodoFichero {
	char nombre[30];		//Nombre del fichero
	unsigned int tam;		//Tama�o del fichero
	ListaBloques bloques;	//Puntero a la lista de bloques que ocupa el fichero en memoria
	ListaFicheros sig;		//Puntero al siguiente nodo en la lista (siguiente fichero)
};

/**
 * crea un directorio vac�o
 */
void crearDirectorio(ListaFicheros* lf);

/**
* A�ade un fichero al directorio, con nombre el indicado como segundo par�metro y tama�o del fichero
* el indicado en el tercer par�metro. Cada fichero nuevo se insertar� el comienzo de la lista y no es
* necesario que los nombres de los ficheros est�n ordenados.
*
* El tama�o del fichero podr� ser o no m�ltiplo de 512 bytes,
* aunque siempre se reservar�n bloques de 512 bytes, aunque el �ltimo no est� completo.
*
* Los bloques necesarios para guardar el fichero se obtendr�n de la lista de bloques libres que se
* proporciona como tercer par�metro.
*
* Si durante la creaci�n de un fichero nos quedamos sin espacio en la lista de bloques libres,
* entonces los bloques que ya hayamos reservado para el fichero se devolver�n a la lista de bloques libres y el puntero
* a los bloques de memoria del fichero tendr� un valor NULL. El tama�o del fichero tambi�n se cambiar� para que sea 0.
*/
void nuevoFichero(ListaFicheros *lf, char *nombre, unsigned int tam, ListaBloques *lb);

/*
* Se leen todos los datos del directorio desde el fichero de texto cuyo nombre se indica como primer par�metro y se
* crear� la lista enlazada con todos los ficheros (segundo par�metro (lf)). El tercer par�metro es la lista de
* bloques libres.
* El fichero de entrada contendr� una l�nea por cada fichero en el directorio, con el siguiente formato:
*     <nombre_fichero> <tama�o en memoria>
*/
void crearDesdeFicheroTexto(char * nombre, ListaFicheros *lf, ListaBloques *lb);

/**
* Se muestra por pantalla el contenido del directorio, con los datos de cada
* fichero en una l�nea distinta. Cada l�nea tiene el siguiente formato:
*
*      <nombre_fichero> <tam> ( <dir1> <dir2> <dir3> � <dirN>)
*/
void imprimirDirectorio(ListaFicheros lf);
/**
 * Se borran todos los ficheros del directorio y los bloques de memoria que ocupan los ficheros borrados se devuelven al bloque
* de memoria libre (tercer par�metro)
 */
void borrarDirectorio(ListaFicheros *lf, ListaBloques *lb);

#endif /* DIRECTORIO_H_ */
