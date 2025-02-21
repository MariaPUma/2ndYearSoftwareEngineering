#TEORÍA NUMPY
'''
Biblioteca que nos ayudará con los calculos numéricos

CLASE:ndarray -> alias : array 
    Creación:
        variable= np.arrray([])
        variable= np.arrray([], tipo)
            tipo  = dtype= np.tipoTamaño

'''

import numpy


f= numpy.empty([])

b= numpy.arange(12).reshape(3,4)
print(b)

c = numpy.diagonal(b)
print(b)
