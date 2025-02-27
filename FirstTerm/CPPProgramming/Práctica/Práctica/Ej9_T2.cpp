/*
9.- El m�ximo com�n divisor (mcd) de dos n�meros naturales p y q es el mayor entero d que
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
*/

#include <iostream>
#include <array>
using namespace std;
void leer (int& x, int& y){
cout<< "Por favor introduzca dos numeros: "<<endl;
cin >>x>>y;

}

int mcd (int P,int Q){


while (P!=Q){
    if(P>Q){
      P = P - Q;
    }else if(Q>P){
      Q = Q - P;

    }
}
return P;
}
int main (){
int x, y;
leer(x,y);
cout<<"El m�ximo com�n divisor es "<< mcd(x,y);


return 0 ;
}
