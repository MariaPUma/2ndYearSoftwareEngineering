/*El m�ximo com�n divisor (mcd) de dos n�meros naturales p y q es el mayor entero d que
divide a ambos. Un algoritmo muy conocido para calcularlo es el de Euclides. �ste utiliza dos
variables, que contienen inicialmente a cada uno de los n�meros, y trata de hacer que su
contenido sea el mismo. Para ello, ir� restando la menor a la mayor hasta que ambas
contengan el mismo valor. En dicho momento, el valor obtenido en cualquiera de ellas es el
m�ximo com�n divisor de los dos n�meros iniciales. Por ejemplo, si P = 18 y Q = 12, el
algoritmo har� que P y Q vayan tomando los siguientes valores:
Inicialmente P == 18 y Q == 12 (P > Q => P = P - Q)
Despu�s P == 6 y Q == 12 (Q > P => Q = Q - P)
Despu�s P == 6 y Q == 6 (P == Q => El mcd es 6)
Dise�a una funci�n con la siguiente cabecera para el algoritmo anterior siguiendo un
enfoque recursivo:
int mcd(int P, int Q)
Dise�a tambi�n un algoritmo principal (main) para probar la funci�n. Para ello, se leer�n
de teclado los valores P y Q (asegur�ndose que son n�meros > 0), se invocar� a la funci�n
implementada y se mostrar� por pantalla el valor devuelto por la misma.

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

int mcd(int P, int Q)
{
    int cambio;

    while ((P!=Q))
    {
        if (P<Q)
        {
            cambio=P;
            P=Q;
            Q=cambio;
        }
        else
        {

            P-=Q;
        }

    }

    return P;
}


int main ()
{
    int P,Q;
    Leer (P, Q);

    cout << "El mcd es "<< mcd( P,  Q);

    return 0 ;
}
