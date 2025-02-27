#include <iostream>
#include <array>
using namespace std;
const int MAX=10;


typedef array<int, MAX>TArray;

void Leer (TArray& a,int& num)
{
    cout << "Por favor introduzca "<<MAX<< "numeros enteros:";

    for (int i=0; i<MAX; i++)
    {

        cin>> a[i];
    }

    cout << "Introduzca el numero a buscar: ";
    cin >> num;



}

bool Esta (const TArray& a, int n)
{
    bool encontrado=false;
    int i=0;
    while ((i<MAX)&& (!encontrado))
    {
        if (a[i]== n)
        {
            encontrado=true;
        }
        else
        {
            i++;
        }


    }
    return encontrado;
}

void Escribir (const TArray& a, int num)
{

    if (Esta(a,num))
    {
        cout << "El numero "<< num<< " SI esta en la secuencia.";
    }
    else
    {
        cout << "El numero "<< num<< " NO esta en la secuencia.";
    }

    cout << endl;
    cout <<"La array de enteros es la siguiente: "<< endl;
    for (int i=0; i<MAX; i++)
    {

        cout <<a[i]<<" ";
    }

}
int main ()
{

    TArray n;
    int num;
    Leer (n,num);
    Escribir (n,num);
    return 0 ;
}
