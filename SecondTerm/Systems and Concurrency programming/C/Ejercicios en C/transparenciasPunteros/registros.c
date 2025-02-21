#include <stdio.h>

/* registros y punteros:
    - Diferencia entre (*ptr_reg).x y *ptr_reg.x (el punto tiene prioridad frente a *)
    - Uso de notación -> para acceder a los campos del registro desde un puntero
*/
struct Punto{
    int x,y;
};

int main(){
    struct Punto reg={1,2}; //Registro de dos campos
    struct Punto *ptr_reg;  //Puntero a un registro de tipo struct Punto

    ptr_reg=&reg;   

    printf("Registro: %d %d\n",reg.x, reg.y);
    printf("Registro: %d %d\n",(*ptr_reg).x, (*ptr_reg).y); //¿Son necesarios los paréntesis?
    printf("Registro: %d %d\n",ptr_reg->x, ptr_reg->y);

}