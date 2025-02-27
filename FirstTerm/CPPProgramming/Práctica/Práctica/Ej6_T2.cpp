/*La conjetura de Goldbach dice que todo n�mero par mayor que 2 tiene la propiedad de que es
la suma de dos n�meros primos. Dise�a un algoritmo que compruebe si dicha conjetura es
cierta para todos los n�meros pares comprendidos entre dos n�meros le�dos por teclado.
Ejemplo. Para los n�meros pares comprendidos entre 3 y 12, se cumple la conjetura:
4 = 2 + 2
6 = 3 + 3
8 = 3 + 5
10 = 3 + 7
12 = 5 + 7
*/
#include <iostream>
#include <cmath>
using namespace std;

bool esPrimo (int p)
{
    bool res=false;
    int tope,divisor;
    if (p>=2)
    {
        tope =sqrt(p);
        divisor=2 ;

        while((divisor <=tope)&& (p%divisor!=0))
            divisor++;

    }

    res= divisor > tope;

    return res;

}


void Leer (int& inf,int& sup)
{

    cout << "Por favor introduzca l�mite inferior : ";
    cin >> inf;


    cout << "Por favor introduzca l�mite superior : ";
    cin >> sup;



}
bool esPar(int i)
{
    bool res=false;

    if (i%2==0)
    {

        res=true;
    }

    return res;
}


bool SumarPrimos (int num)
{
    bool encontrado = false;
    int cont = 2;
    while ((cont <= num/2) && !encontrado)
    {
        if (esPrimo(cont) && esPrimo(num-cont))
        {

            encontrado = true;
         cout << num << " = " << cont << " + " << num-cont << endl;
        }
        else
        {
            cont ++;
        }
    }
    return encontrado;


}


bool Conjetura (int inf,int sup)
{
    bool res=false;
    for (int i=inf ; i<=sup ; i++ )
    {
        if (esPar(i))
        {
            res=SumarPrimos(i);

        }

    }
    return res;
}

int main ()
{
    int inf,sup;

    Leer(inf,sup);

    Conjetura (inf, sup);

    if (Conjetura)
    {
        cout<< "Todos los pares en el rango elegido cumplen la conjetura" ;

    }
    else
    {
        cout << "Los pares en el rango elegido no cumplen la conjetura";

    }

    return 0 ;
}
