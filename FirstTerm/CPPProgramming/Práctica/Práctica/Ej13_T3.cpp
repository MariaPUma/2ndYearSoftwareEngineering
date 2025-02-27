#include <iostream>
#include <array>
using namespace std;
const int MAY=26;
struct TDatos
{
    int ultimaPos;
    int mayorDis;
    bool repet;

};
typedef array<TDatos,MAY> TArray;

void Inicializar (TArray& v)
{

    for (int i=0; i<MAY; i++)
    {
        v[i].ultimaPos=0;
        v[i].mayorDis=0;
        v[i].repet=false;
    }

}
void escribirMayDis (const TArray& v){


}
void CalcularMayorDis(TArray& v)
{
    char c;
    cout << "Por favor introduzca una serie de letras mayusculas terminadas en punto :";
    cin.get(c);
    pos=1;
    while (c!='.')
    {
        if (('A'<=c)&& (c<='Z'))
        {

            if (v[c-'A'].ultimaPos==0)

                v[c-'A'].ultimaPos=pos;

        }
        else
        {
            v[c-'A'].repet = true;
            dis=pos -v[c-'A'].ultimaPos  -1;

            letras[c-'A'].ultimaPos = pos;
            if (dis > letras[c-'A'].mayorDis)
            {
                letras[c-'A'].mayorDis = dis;
            }


        }

        cin.get(c);
        pos++;
    }
}
int main ()
{

    TArray v= {{}};



    return 0 ;
}
