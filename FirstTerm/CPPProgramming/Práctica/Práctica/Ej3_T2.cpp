/*Escribe un programa que tome como entrada desde teclado dos n�meros naturales (mayores
que cero) "N" e "i", e imprima en pantalla el d�gito que ocupa la posici�n i-�sima del n�mero
N. Si i es mayor que el n�mero de d�gitos de N, se escribir� en pantalla -1. Por ejemplo, para N
= 25064 e i = 2, el resultado es el d�gito 6, y para i = 7, el resultado es -1.
*/
#include <iostream>
#include <array>
using namespace std;

void Leer (int& n, int& i)
{
    do
    {

        cout << "Por favor introduzca un numero (mayoresque cero): ";
        cin >> n;

        cout << "Por favor introduzca la posicion i-esima: ";
    cin >> i;
    }
    while (n<=0||i<=0);

}

   int dig(int n, int i) {

int suma=0,resto,resultado =0;
    do
    {
        resto=n%10;
        n=n/10;
        suma++;
    }
    while (n>0&&suma<i);


    if (suma<i){
        resultado =-1;

    }else{
    resultado =resto;
    }
    return resultado;
   }

void Posicion (int n, int i, int digitos)
{
    cout << "El digito que ocupa la posicion " << i
<< " del numero " << n<< " es: "
<< digitos << endl;

}

int main ()
{
    int n,i;
    Leer(n,i);
    Posicion (n,i,dig(n,i)) ;


    return 0 ;
}
