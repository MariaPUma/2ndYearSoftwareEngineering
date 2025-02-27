/*Escribe un programa lea de teclado un n�mero natural (debe ser mayor de 0 y menor de 10)
que representa el n�mero de filas de una determinada pir�mide de d�gitos y que muestre por
pantalla dicha pir�mide. El formato de la misma ser� como la que se muestra a continuaci�n,
para una entrada de 5 filas:

*/

#include <iostream>
#include <array>
using namespace std;

void Leer (int& n){

do{

    cout <<"Por favor introduzca un numero (mayor de 0 y menor de 10)";
    cin>> n;
}while ((n<0)||(n>10));

}


void EscribirBlancos (int blancos)
{

    for (int i=1; i<=blancos; i++)
    {
        cout << " ";
    }

}
void EscribirnumAsc (int blancos)
{

    for (int i=1; i<=blancos; i++)
    {
        cout << i;
    }

}
void EscribirnumDes (int blancos)
{

    for (int i=blancos-1; i>=1; i--)
    {
        cout << i;
    }

}


void escribirPiramide (int n){

for (int cont= 1; cont <=n;cont++){
    EscribirBlancos (n-cont);
    EscribirnumAsc (cont);
    EscribirnumDes (cont);
    cout<<endl;
}

}
int main (){
int n;
Leer (n);
escribirPiramide (n);


return 0 ;
}
