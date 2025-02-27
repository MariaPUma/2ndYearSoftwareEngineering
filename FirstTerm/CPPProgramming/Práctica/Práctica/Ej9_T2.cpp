/*
9.- El máximo común divisor (mcd) de dos números naturales p y q es el mayor entero d que
divide a ambos. Un algoritmo muy conocido para calcularlo es el de Euclides. Éste utiliza dos
variables, que contienen inicialmente a cada uno de los números, y trata de hacer que su
contenido sea el mismo. Para ello, irá restando la menor a la mayor hasta que ambas
contengan el mismo valor. En dicho momento, el valor obtenido en cualquiera de ellas es el
máximo común divisor de los dos números iniciales. Por ejemplo, si P = 18 y Q = 12, el
algoritmo hará que P y Q vayan tomando los siguientes valores:
Inicialmente P == 18 y Q == 12 (P > Q => P = P - Q)
Después P == 6 y Q == 12 (Q > P => Q = Q - P)
Después P == 6 y Q == 6 (P == Q => El mcd es 6)
Diseña una función con la siguiente cabecera para el algoritmo anterior siguiendo un
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
cout<<"El máximo común divisor es "<< mcd(x,y);


return 0 ;
}
