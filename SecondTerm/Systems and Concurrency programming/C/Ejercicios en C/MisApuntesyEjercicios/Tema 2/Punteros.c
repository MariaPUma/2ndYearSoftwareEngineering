#include <stdio.h>
//PUNTEROS
//
/*
variable que contiene una dirección de memoria de otra zona de la memoria.
Como se inicializa-> int *ptr; (El puntero apunta a una posición de memoria que contiene un puntero)

       * la variable ptr tiene tamaño que corresponde al tamaño en bytes del almacenamiento (dirección de memoria)
       * ptr no sabemos a que apunta ya que al inicializarla sin ningún valor toma un valor cualquiera (valor basura)
         .Accede a una posición de memoria que nosotros desconocemos su contenido (altas posibilidades de fallo de segmentación)
       * Se puede inicializar con un valor null 

Operacdor "&" (Operador de dirección):
    &v -> devuelve la dirección de memoria en la que está almacenada esa variable
            Ejemplo:
            ----------------------------------
            int *ptr; //contiene una dirección cualquiera
            int v=10; //variable 
            ptr= &v; //se asocia la dirección de memoria de "v" a ptr 
            ----------------------------------
Operador "*" (Operador de indirección):
    *  -> Obtener el valor que tiene la posición de memoria almacenada en el puntero
            Ejemplo:
            ----------------------------------
            printf("valor = %d %d",x, *ptr)

El puntero puede apuntar a cualquier objeto --- void *ptr; para luego asociarlo a otra variable
Y ahí ya se especifica el tipo

PUNTEROS EN ARRAY
          _____________________________
         |    |    |    |    |    |    |        
         |____|____|____|____|____|____| 
           ^
           |
            _
           |_| PUNTERO (apunta a la primera posición del array)

        ptr = &a[0]
        a[0] = *ptr

Para iterar sobre los arrays sin for each se puede incrementar el puntero (sin *)
en 1 para pasar a la "siguiente casilla"
/Ejemplo t54/

PUNTEROS A ESTRUCTURAS
    struct Punto *ppunto;
    ó
    typedef struct Punto *Ppunto;
    Ppunto p; ->  es un puntero a un tipo struct 

Para poder acceder a las componentes de struct con el operador ".":
    (*p).componente   ó     p->componente
    Ambas dos dan el valor almacenado en el componente 

PUNTEROS A FUNCIONES
    int (*comp)(void *, void *)
El puntero hace referencia a una función:

      int esMayorInt (int *a, int *b) { return *a > *b ; }

      int esMayorDouble (double *a, double *b) { return *a > *b ; }

      int esMayor(void *a, void *b, int (*f)(void *, void *)) {    //Toma dos punteros que toman un valor cualquiera y también toma una referencia a una función del tipo int
      return (*f)(a, b) ;
      }
    /Mirar el archivo pointerToFunction.c
*/
int main (void){
    // int *ptr;
    // int v=10;
    // int x=2;

    // ptr= &v;


    // printf("valor = %d %d",x, *ptr); //valor = 2 10 (Salida de la consola)

    

}