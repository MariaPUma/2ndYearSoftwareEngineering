/*Dise�a un procedimiento recursivo que lea por teclado una secuencia de caracteres de
longitud arbitraria terminada en un punto, y la imprima en orden inverso (el car�cter punto no
se escribe, s�lo es un car�cter terminador). El procedimiento no tiene par�metros. Dise�a
tambi�n un algoritmo principal (main) para probar el procedimiento. Por ejemplo, si se
introduce por teclado la secuencia hola, la salida por pantalla ser� aloh
*/
#include <iostream>
#include <array>
using namespace std;

void Leer (int& P,int& Q)
{
    do
    {
        cout << "Por favor introduzca el valor de P : ";
        cin >> P;


        cout << "Por favor introduzca el valor de Q : ";
        cin >> Q;

    }
    while (P<=0 || Q<=0);

}


int main (){





return 0 ;
}
