'''
Escribe un programa que cree un array unidimensional con los enteros positivos múltiplos de 4 menores 
que 100.
'''

import numpy as np
tope = 100/4
a= np.arange(1,tope, dtype=int)

c= np.arange(0,100)[::4]
print(a *4)
print(c)