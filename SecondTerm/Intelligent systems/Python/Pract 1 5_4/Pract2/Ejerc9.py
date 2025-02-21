import numpy as np
array = np.array([1, 2, 1, 3, 2, 1, 4, 3, 4, 4])
elem,veces= np.unique(array,return_counts=True)

print(elem)
print(veces)

#Para que se visualize de una mejor manera podemos utilizar un diccionario

print (dict(zip(elem,veces)))