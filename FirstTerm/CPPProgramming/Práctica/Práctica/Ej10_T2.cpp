/*La conjetura de Goldbach dice que todo n�mero par mayor que 2 tiene la propiedad de que es
la suma de dos n�meros primos. Dise�a un algoritmo que compruebe si dicha conjetura es
cierta para todos los n�meros pares comprendidos entre dos n�meros le�dos por teclado.
Ejemplo. Para los n�meros pares comprendidos entre 3 y 12, se cumple la conjetura:
4 = 2 + 2
6 = 3 + 3
8 = 3 + 5
10 = 3 + 7
12 = 5 + 7
Por lo que la ejecuci�n del programa mostrar�a por pantalla :
Introduzca l�mite inferior: 3
Introduzca l�mite superior: 12
Todos los pares en el rango elegido cumplen la conjetura
*/


#include <iostream>
#include <array>
using namespace std;
void Leer (int& x,int& y){
do{
    cout << "Introduzca l�mite inferior: ";
    cin >>x;

}while(x<=2)
    cout<< endl;


do{
    cout << "Introduzca l�mite superior: ";
    cin >>y;

}while(y<x)

}
int main (){



Leer (x,y);

return 0 ;
}
