/*Escribe un programa que calcule e imprima por pantalla los N primeros números primos,
siendo N un número natural que se introduce por teclado. Por ejemplo, si N = 8, los primos
que se mostrarán por pantalla son 2, 3, 5, 7, 11, 13, 17, 19.
*/

#include <iostream>
#include <cmath>
using namespace std;

void Leer (int& n)
{

    do
    {

        cout <<"Por favor introduzca un numero (mayor de 0)";
        cin>> n;
    }
    while (n<0);

}

bool EsPrimo (int num)
{
    int tope,divisor;
    bool res=false;
    if (num >=2)
    {
        tope=int(sqrt (num));
        divisor =2;
        while (divisor<=tope && num%divisor!=0)
        {

            divisor++;
        }
        res =divisor > tope;
    }
    return res;
}

void ImprimirPrimos (int N)
{
    int primo=2,cont=0;

    while(cont<N)
    {

        if (EsPrimo(primo ))
        {
            cout << primo<< " ";
            cont++;
        }
        primo++;
    }

}

int main ()
{
    int N;
    Leer(N);

    ImprimirPrimos (N);

    return 0 ;
}
