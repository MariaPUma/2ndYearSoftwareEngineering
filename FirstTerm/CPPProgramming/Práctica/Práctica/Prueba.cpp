#include <iostream>
#include <cmath>
using namespace std;
bool esPrimo(int num)
{
    bool res = false;
    int divisor, tope;
    if (num >= 2)
    {
        tope = sqrt(num);
        divisor = 2;
        while ((divisor <= tope) && (num % divisor != 0))
        {
            divisor++;
        }
        res = divisor > tope;
    }
    return res;
}
bool conjeturaGoldbach(int num)
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
void datosEntrada(int& inf, int& sup, int& primerPar)
{
    do
    {
        cout << "Introduzca limite inferior y superior: ";
        cin >> inf >> sup;
    }
    while ((inf > sup) || (inf <= 2));
    if (inf % 2 == 0)
    {
        primerPar = inf;
    }
    else
    {
        primerPar = inf+1;
    }
}
int main()
{
    int inf,sup,par;

    datosEntrada(inf,sup,par);
    while ((par <= sup) && conjeturaGoldbach(par))
    {
        par += 2;
    }
    if (par > sup)
    {
        cout << "Todos los pares en el rango elegido cumplen la conjetura"<< endl;
    }
    else
    {
        cout << "El par " << par << " no cumple la conjetura"<< endl;
    }
    return 0;
}
