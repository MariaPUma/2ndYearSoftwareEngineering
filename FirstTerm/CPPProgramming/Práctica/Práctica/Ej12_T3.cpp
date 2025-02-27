/*Diseña un procedimiento borrar, que recibe como parámetros un registro de tipo Vector y
un número entero, y elimina dicho número del array del registro (si hay varias ocurrencias del
número, se elimina sólo una de ellas; si el número no está, no se hace nada). Diseña otro
procedimiento insertar, que recibe como parámetros un registro de tipo Vector y un
número entero, y añade dicho número al array del registro (si el array está lleno no se hace
nada)

*/
#include <iostream>
#include <array>
using namespace std;
const int MAX=10;

typedef array<int, MAX>TSecuencia;

struct Vector
{
    TSecuencia numeros;
    int tam;

};

void Leer (Vector& v)
{
    int num;

    cout << "Por favor introduzca una secuencia de numeros de no más de "<< MAX <<" numeros.";

    v.tam=0;
    cin>> num;
    while (num!=0)
    {
        if (v.tam< MAX)
        {
            v.numeros[v.tam]=num;
            v.tam++;
        }
        cin>>num;
    }
}

int posicion(int num, const Vector& v)
{
    int cont = 0;

    while ((cont < v.tam) && (num > v.numeros[cont]))
    {
        cont++;
    }

    return cont;
}

void Escribir (const Vector& v)
{

    for (int cont=0; cont < v.tam ; cont++ )
    {
        cout<< v.numeros[cont]<<" ";

    }


}
int Buscar (const Vector& v,int num)
{
    int i=0;
    while ((v.numeros[i]!=num)&& (i<v.tam))
    {

        i++;
    }
    return i;
}

void Borrar ( Vector& v, int num)
{

    int pos=Buscar(v,num);

    if (pos < v.tam)
    {
        for (int cont = pos; cont < v.tam - 1; cont++)
        {
            v.numeros[cont] = v.numeros[cont+1];
        }
        v.tam--;
    }


}


void Insertar ( Vector& v, int num)
{
int pos=0;
    if (v.tam < MAX)
    {
        pos = posicion(num,v);
        for (int cont = v.tam; cont > pos; cont--)
        {
            v.numeros[cont] = v.numeros[cont-1];
        }
        v.numeros[pos] = num;
        v.tam++;

    }
}


int main ()
{
    Vector v;
    int bor,ins;

    Leer (v);

    cout<< "Por favor introduzca un numero de la secuencia a borrar: "<< endl;
    cin >> bor;

    Borrar (v,bor);
    cout << "El vector despues de borrar es: "<< endl;
    Escribir(v);
    cout<< endl;
    cout << "Por favor introduzca un numero a insertar en la secuencia: "<< endl;
    cin >> ins;


    Insertar(v,ins);
    cout << "El vector despues de insertar es: "<< endl;
    Escribir(v);
    cout<< endl;
    Escribir (v);


    return 0 ;
}
