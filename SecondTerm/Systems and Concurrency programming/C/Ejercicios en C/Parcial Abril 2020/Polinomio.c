#include "Polinomio.h"
#include <stdlib.h>
#include <stdio.h>

void polinomioCero(TPolinomio *p)
{
    *p=NULL;
}

unsigned int grado(TPolinomio p)
{
    return p->exp;
}

unsigned int coeficiente(TPolinomio p, unsigned int exp)
{
    TPolinomio ptr=p;
    unsigned int cof=0;

    while(ptr!=NULL && (ptr->exp)>exp){
        ptr=ptr->sig;
    }

    if(ptr!=NULL && ptr->exp == exp){
        cof= ptr->coef;
    }
    return cof;
}
void crearMonomio(TPolinomio *nuevo, unsigned int coef, unsigned int exp)
{
    *nuevo= (TPolinomio)malloc(sizeof(struct TMonomio));
    
        (*nuevo)->coef=coef;
        (*nuevo)->exp= exp;
        (*nuevo)->sig= NULL;
    
}
void insertar(TPolinomio *p, unsigned int coef, unsigned int exp)
{
    if(coef!=0){
        TPolinomio nuevo;
        crearMonomio(&nuevo,coef,exp);
        
            
            if(*p==NULL){
                *p=nuevo;
            }else{
                TPolinomio ptr,ant;
                ptr=*p;
                ant= NULL;

                while(ptr!=NULL && ptr->exp > exp)
                {
                    ant= ptr;
                    ptr= ptr->sig;
                }

                if(ptr==NULL){
                    //Tenemos que insertar al final de la lista
                    ant->sig= nuevo;
                    
                }else{
                    if(ptr->exp == exp){ //Si hemos encontrado un monomio con el mismo exponente
                        ptr->coef =ptr->coef + coef;
                    }else{
                        if(ant==NULL){
                            *p= nuevo;
                            nuevo->sig= ptr;
                        }else{
                            ant->sig= nuevo;
                            nuevo->sig= ptr;
                        }

                    }

                }


            }
    }
    
}

void imprimir(TPolinomio p)
{
    TPolinomio ptr=p;
    printf("[");
    while (ptr!=NULL){
        printf("(%d,%d)",ptr->coef,ptr->exp);
        ptr=ptr->sig;
    }
    printf("]\n");
}

void destruir(TPolinomio *p)
{
    TPolinomio borrar=*p;
    while(borrar!=NULL){
        *p=(*p)->sig;
        free(borrar);
        borrar=*p;
    }
}

//nombre= nombre del fichero
void crearDeFichero(TPolinomio *p, char *nombre){
    FILE *fd;
    char c1,c2;
    int cof, ex;
    int nleidos;

    fd= fopen(nombre,"rt");

    if(fd==NULL){
        perror("No se pudo abrir el fichero ");
    }else{
        polinomioCero(p);
        while ((nleidos= fscanf(fd,"%c\n%c\n",&c1,&c2))>0){
            cof= c1- '0';
            ex = c2 - '0';
            insertar(p,cof,ex);
        }

        fclose(fd);
    }
    

}

int evaluar(TPolinomio p, int x)
{
    return 0;
}
