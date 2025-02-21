import numpy as np

def MatrizCuadrada (array):
   tam= np.sqrt(len(array)) 
   
   if( tam.is_integer()):
      matriz= array.reshape(int(tam),int(tam))
      print (matriz)
   else :
      print("No se pudo redimensionar el array pasado")
      


a= np.arange(0,9)
b= np.arange(0,13)

MatrizCuadrada(a)
MatrizCuadrada(b)