/*La conjetura de Goldbach dice que todo número par mayor que 2 tiene la propiedad de que es
la suma de dos números primos. Diseña un algoritmo que compruebe si dicha conjetura es
cierta para todos los números pares comprendidos entre dos números leídos por teclado.
Ejemplo. Para los números pares comprendidos entre 3 y 12, se cumple la conjetura:
4 = 2 + 2
6 = 3 + 3
8 = 3 + 5
10 = 3 + 7
12 = 5 + 7
Por lo que la ejecución del programa mostraría por pantalla :
Introduzca límite inferior: 3
Introduzca límite superior: 12
Todos los pares en el rango elegido cumplen la conjetura
*/


#include <iostream>
#include <array>
using namespace std;
void Leer (int& x,int& y){
do{
    cout << "Introduzca límite inferior: ";
    cin >>x;

}while(x<=2)
    cout<< endl;


do{
    cout << "Introduzca límite superior: ";
    cin >>y;

}while(y<x)

}
int main (){



Leer (x,y);

return 0 ;
}
