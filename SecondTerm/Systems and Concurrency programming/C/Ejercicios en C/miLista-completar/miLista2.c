#include "miLista.h"
//Implementación de las funciones de manejo de listas enlazadas
//error típico: no debemos de reservar memoria si no que añadir ptr= null;

void crear (Lista *l){
    //Paso de parámetros por referencia en c
    /*
    En la tablet (documento) A0
    si no ponemos el puntero modificamos (struct TNodo *)
    */
   *l =NULL;
}

Lista crearV2()
{
    return NULL;
}

int estaVacia(Lista l)
{
    return (l==NULL);
}

Lista crearNodo (int elem){
    Lista nuevo= (Lista) malloc (sizeof(struct TNodo));

    if(nuevo!=NULL){
        nuevo->dato=elem;
        nuevo->sig=NULL;
    }
    return nuevo;
}

void crearNodo2 (Lista *nuevo, int elem){
    *nuevo= (Lista) malloc(sizeof(struct TNodo))

    if(*nuevo !=NULL){
        (*nuevo)->dato = elem;
        (*nuevo)->sig = NULL;
    }
}

void insertar(Lista *l, int elem)
{
    Lista nuevo = crearNodo(elem);

    if(nuevo != NULL){
        if(*l !=NULL){
            nuevo->sig= *l;
        }
        *l = nuevo;
    }
}

void insertarFinal(Lista *l, int elem)
{
    Lista ant,nuevo;
    nuevo = crearNodo(elem);

    if(nuevo != NULL){
        if(*l==NULL){
            *l= nuevo;
        }else{
            while(ant->sig!=NULL){
                ant = ant->sig;
            }

            ant->sig= nuevo;
        }
    }
}

void insertarOrdenado(Lista *l, int elem)
{
    Lista ptr, ant, nuevo;
    ptr= (*l);
    ant= NULL;

    if(nuevo !=NULL){
        if(*l ==NULL){
            *l = nuevo;
        }else{
            while (ptr != NULL && (ptr->dato) < elem){
                ant = ptr;
                ptr = ptr->sig;
            }

            if(ant==NULL){
                insertar(l,elem);
            }else{
                nuevo = crearNodo(elem);

                if(ptr == NULL){
                    ant->sig= nuevo;
                }else{
                    ant->sig= nuevo;
                    nuevo->sig= ptr;
                }
            }
        }
    }
}

void insertarOrdenadoSinRepeticiones(Lista *l, int elem)
{
    Lista ptr, ant, nuevo;
    ptr= (*l);
    ant= NULL;

    if(nuevo !=NULL){
        if(*l ==NULL){
            *l = nuevo;
        }else{
            while (ptr != NULL && (ptr->dato) < elem){
                ant = ptr;
                ptr = ptr->sig;
            }

            if(ptr->dato != elem){
                if(ant==NULL){
                    insertar(l,elem);
                }else{
                    nuevo = crearNodo(elem);

                    if(ptr == NULL){
                        ant->sig= nuevo;
                    }else{
                        ant->sig= nuevo;
                        nuevo->sig= ptr;
                    }
                }
            }

        }
    }
}

void eliminar(Lista *l)
{
    if(*l != NULL){
        Lista ptr= *l;
        *l=(*l)->sig;
        free(ptr);
    }
}

Lista sacar(Lista *l)
{
    Lista ptr= l*;
    if(){
        *l== (*l)->sig;
        ptr->sig=NULL;
    }
    return ptr;
}

void eliminarElem(Lista *l, int elem)
{
    if(*l !=NULL){
        Lista ptr, ant;

        ptr = *l;
        ant= NULL;

        while (ptr!=NULL && ptr->dato != elem ){
            ant = ptr;
            ptr = ptr->sig;
        }

        if(ptr!=NULL){
            if(ant == NULL) eliminar(l);
            else{
                ant->sig= ptr->sig;
                free(ptr);
            }
        }
    }
}

Lista sacarElem(Lista *l, int elem)
{
    Lista dato=NULL;
    if(*l !=NULL){
        Lista ptr, ant;
        while (ptr!= NULL && (ptr->dato) != elem){
            ant = ptr;
            ptr= ptr->sig;
        }

        if(ptr!=NULL){
            if(ant ==NULL){
                dato =sacar(l);
            }else{
                ant->sig = ptr->sig;
                dato = ptr;
            }
        }
    }
    return ptr;
}

void eliminarMultiplesElem(Lista *l, int elem)
{
    if(*l !=NULL){
        Lista ant, ptr;

        ant =NULL;
        ptr= *l;

        while(ptr!=NULL){
            if(ptr->dato == elem){
               if(ant == NULL){
                eliminar(*l);
                ptr=*l;
               }else{
                ant = ptr->sig;
                free(ptr);
                ptr= ant->sig;
               }
            }
        }
    }
}

void eliminarOrdenado(Lista *l, int elem)
{
    if (*l !=NULL){
        Lista ptr, ant,
        ptr = *l;
        ant = NULL;

        while (ptr!=NULL && elem>(ptr->dato)){
            ant= ptr;
            ptr= ptr->sig;
        }

        if(ptr!=NULL && ptr->dato == elem){
            if(ant==NULL){
                eliminar(*l);
            }else{
                ant->sig= ptr->sig;
                free(ptr);
            }

        }
    }
}

Lista sacarOrdenado(Lista *l, int elem)
{
    Lista dato= NULL;
    if(*l!=NULL){
        Lista ant, ptr;
        ant =NULL;
        ptr= *l;

        while (ptr!=NULL && ptr->dato < elem){
            ant = ptr;
            ptr= ptr->sig;
        }

        if(ptr!=NULL && ptr->dato == elem){
            if(ant == NULL){
                dato= sacar(elem);
            }else{
                ant->sig= ptr->sig;
                dato= ptr;
            }
        }
    }
    return dato;
}

void eliminarOrdenadoMultiplesElem(Lista *l, int elem)
{
    if(*l != NULL){
        Lista ant, ptr;

        ant=NULL;
        ptr= *l;

        while (ptr !=NULL && elem> ptr->dato){
            if(ptr->dato ==elem){
                if(ant ==NULL){
                    eliminar(*l);
                    ptr=*l;
                }else{
                    ant->sig= ptr->sig;
                    free(ptr);
                    ptr= ant->sig;
                }
            }
        }
    }
}

void mostrar(Lista l)
{
    Lista ptr=*l;
    printf("[ ");
    while (ptr!=NULL){
        printf("%d ", ptr->dato);
        ptr= ptr->sig;
    }
    printf("] \n");
}

int buscarElementoV1(Lista l, int elem)
{
    Lista ptr;
    while (ptr !=NULL && ptr->dato != elem){
        ptr= ptr->sig;
    }
    return (ptr !=NULL);
}

Lista buscarElementoV2(Lista l, int elem)
{
    Lista dato = l;
    while (dato != NULL && dato->dato != elem){
        dato= dato->sig;
    }
    return dato;
}

void destruir(Lista *l)
{
    while (l*!=NULL){
        eliminar(*l);
    }
}
