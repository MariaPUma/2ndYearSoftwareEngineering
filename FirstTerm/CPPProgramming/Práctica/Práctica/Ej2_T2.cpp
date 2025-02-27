#include <iostream>
#include <array>
using namespace std;
//Escribe un programa que lea un número natural N por teclado y dibuje un triángulo de
//asteriscos con base y altura N. Por ejemplo, si N=5 debería dibujarse:

void Leer (int& n)
{

    cout << "Por favor introduzca un numero: ";
    cin >> n;

}
void EscribirBlancos (int blancos)
{

    for (int i=1; i<=blancos; i++)
    {
        cout << " ";
    }

}
void EscribirX (int x)
{

    for (int i=1; i<=x; i++)
    {
        cout << 'x'<< " ";
    }

}
void EscribirPiramide (int n)
{

    for (int i=1; i<=n; i++)
    {
        EscribirBlancos (n-i);
        EscribirX (i);
        cout << endl;
    }
}

int main ()
{
    int n;
    Leer (n);
    EscribirPiramide (n);


    return 0 ;
}
