/*Escribe un programa que acepte como entrada desde teclado un número natural mayor que cero
y dé como salida el resultado de sumar dos a dos los dígitos que aparecen en posiciones
simétricas respecto al dígito central dentro del número dado como entrada. Por ejemplo:
*/
#include <iostream>
#include <array>
using namespace std;

void Leer (int& n)
{
    do
    {

        cout << "Por favor introduzca un numero (mayoresque cero): ";
        cin >> n;


    }
    while (n<=0);

}

int dig(int n, int i)
{

    int suma=0,resto=0,resultado =0;
    do
    {
        resto=n%10;
        n=n/10;
        suma++;
    }
    while (n>0&&suma<i);


    if (suma<i)
    {
        resultado =-1;

    }
    else
    {
        resultado =resto;
    }
    return resultado;
}

int contarDigitos (int n)
{
    int suma=0;
    do
    {
        n=n/10;
        suma++;
    }
    while (n>0);

    return suma;

}


void escribirResultado (int n){
int numerodigito,d1,d2;

numerodigito= contarDigitos (n);

for (int i=1;i<=numerodigito/2;i++){
    d1= dig (n,numerodigito-i+1);
    d2= dig (n,i);
    cout<< d1 <<" + "<< d2<< " = "<<d1+d2 << endl;
}
if (n%2!=0){
    cout << dig(n,numerodigito / 2 + 1) << endl;

}


}

int main ()
{
    int n;

    Leer(n);

    escribirResultado(n);


    return 0 ;
}
