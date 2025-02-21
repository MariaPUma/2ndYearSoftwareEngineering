#include <stdio.h>
#include <stdlib.h>
#include <string.h>

const int delta = 0x9e3779b9;
/* Parte 1: algoritmo de descifrado
 * 	v: puntero a un bloque de 64 bits.
 * 	k: puntero a la clave para descifrar.
 * 	Sabiendo que "unsigned int" equivale a 4 bytes (32 bits)
 * 	Podemos usar la notaci�n de array con v y k
 * 	v[0] v[1] --- k[0] ... k[3]
 */
void decrypt(unsigned int *v, unsigned int *k)
{
	//Definir variables e inicializar los valores de delta y sum
	
	unsigned int sum= 0xc6ef3720;

	//Repetir 32  veces (usar un bucle) la siguiente secuencia de operaciones de bajo nivel

			//Restar a v[1] el resultado de la operacion :
				// (v[0] desplazado a la izquierda 4 bits +k[2]) XOR (v[0] + sum)  XOR (v[0] desplazado a la derecha 5 bits)+k[3]

			//Restar a v[0] el resultado de la operacion:
				// (v[1] desplazado a la izquierda 4 bits + k[0]) XOR (v[1]+ sum)  XOR (v[1] desplazado a la derecha 5 bits)+k[1]

			// Restar a sum el valor de delta
	for(int i = 0; i<32;i++){
		v[1]= v[1]-(((v[0]<<4)+k[2])^ (v[0]+sum) ^ ((v[0]>>5)+ k[3]));
		v[0]= v[0]-(((v[1]<<4)+k[0])^ (v[1]+sum) ^ ((v[1]>>5)+ k[1]));
	}
}

/* Parte 2: Metodo main. Tenemos diferentes opciones para obtener el nombre del fichero cifrado y el descifrado
 * 1. Usar los argumentos de entrada (argv)
 * 2. Pedir que el usuario introduzca los nombres por teclado
 * 3. Definir arrays de caracteres con los nombres
 */
int main()
{
	/*Declaraci�n de las variables necesarias, por ejemplo:
	* variables para los descriptores de los ficheros ( FILE * fent, *fsal)
	* la constante k inicializada con los valores de la clave
	* buffer para almacenar los datos (puntero a unsigned int, m�s adelante se reserva memoria din�mica */
	FILE * fent, *fsal;
	unsigned int v[2],k[4]= {128, 129, 130,131};
	char *buffer;

	char nombrefichENC[50],nombrefichSAL[50];
	printf("Introduzca el nombre del ficherp encriptado \n");
	scanf ("%s",nombrefichENC);

	printf("Introduzca el nombre del fichero de salida \n");
	scanf ("%s",nombrefichSAL);


	/*Abrir fichero encriptado fent en modo lectura binario
	 * nota: comprobar que se ha abierto correctamente*/
    fent= fopen(nombrefichENC,"rb");
	if (fent==NULL)
	{
		perror("El fichero encriptado no se ha podido abrir para su lectura");
	}
	

	/*Abrir/crear fichero fsal en modo escritura binario
	 * nota: comprobar que se ha abierto correctamente*/
	fsal= fopen(nombrefichSAL,"wb");
	if(fsal==NULL)
	{
		perror("El fichero de salida no se ha podido abrir para su lectura");
	}

   /*Al comienzo del fichero cifrado esta almacenado el tama�o en bytes que tendr� el fichero descifrado.
    * Leer este valor (imgSize)*/
   int imgSize, imgSize2;
   fread(&imgSize,sizeof(int),1,fent);

	/*Reservar memoria din�mica para el buffer que almacenara el contenido del fichero cifrado
	 * nota1: si el tama�o del fichero descifrado (imgSize) no es m�ltiplo de 8 bytes,
	 * el fichero cifrado tiene adem�s un bloque de 8 bytes incompleto, por lo que puede que no coincida con imgSize
	 * nota2: al reservar memoria din�mica comprobar que se realiz� de forma correcta */
	if(imgSize % 8 ==0){
		imgSize2= imgSize;
	}else{
		imgSize2= imgSize + (8- (imgSize %8));
	}

	buffer = (char*)malloc(sizeof(char) * imgSize2);
	if(buffer ==NULL)
	{
		perror("No se reservo memoria correctamente en el buffer");
	}
	char *buff_ad;

	buff_ad= buffer;
	/*Leer la informaci�n del fichero cifrado, almacenado el contenido en el buffer*/
	fread(buffer,sizeof(char),imgSize2,fent);
	/*Para cada bloque de 64 bits (8 bytes o dos unsigned int) del buffer, ejecutar el algoritmo de desencriptado*/
	int cont = imgSize/8;

	for (int i=0;i<cont; i++){
		memcpy((void *) v,(void *) buff_ad,8);
		decrypt(v,k);
		buff_ad+=8;
		fwrite((void *) v, sizeof(char),8,fsal);
	}
	if(imgSize % 8 !=0){
		memcpy((void *) v,(void *) buff_ad,8);
		decrypt(v,k);
		fwrite((void *) v,sizeof(char),imgSize%8,fsal);
	}


    /*Guardar el contenido del buffer en el fichero fsal
     * nota: en fsal solo se almacenan tantos bytes como diga imgSize */

	/*Cerrar los ficheros*/
	free(buffer);
	fclose(fsal);
	fclose(fent);
}


