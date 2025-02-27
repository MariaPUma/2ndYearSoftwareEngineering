/*Escribe un programa que acepte como entrada desde teclado un número natural mayor que uno
(> 1) y dé como salida el resultado de realizar la descomposición en factores primos de dicho
número.
Para realizar la descomposición en factores primos se procede de la siguiente forma:
 Paso 1: Se toma como primer primo el 2 (p = 2).
 Paso 2: Se va dividiendo n por p mientras que el resto de la división sea 0. Cada vez que
se hace esto, se muestra por pantalla el valor de p y se actualiza el valor de n al valor del
cociente de la división entera de n entre p.
 Paso 3: Cuando deja de cumplirse que el resto de la división de n entre p sea 0, se pasa
al siguiente primo.
 Se repiten los pasos 2 y 3 mientras que n sea mayor o igual que p.

*/


#include <iostream>
#include <cmath>
using namespace std;

void Leer (int& n)
{
    do
    {

        cout << "Por favor introduzca un numero (mayoresque cero): ";
        cin >> n;


    }
    while (n<1);

}

bool esPrimo (int p)
{
    bool res=false;
    int tope,divisor;
    if (p>=2)
    {
        tope =sqrt(p);
        divisor=2 ;

        while((divisor<=tope)&& (p%divisor!=0))
            divisor++;

    }

    res= divisor > tope;

    return res;

}
void SigPrimo (int&p)
{
    p++;
    while (!esPrimo)
    {

        p++;
    }

}
void Factorizacion (int n)
{
    int p;
    p=2;

      while(n>=p)
    {
        while (n%p==0)
        {
            cout << p<< " ";

            n/=p;
        }
        SigPrimo(p);
    }

}

int main ()
{
    int n;
    Leer(n);

    Factorizacion (n);

    return 0 ;
}
