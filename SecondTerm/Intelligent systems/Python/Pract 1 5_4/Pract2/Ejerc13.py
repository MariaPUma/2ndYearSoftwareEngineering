import numpy as np
arr = np.array([[4, 2, 1, 3],
                [9, 10, 7, 5],
                [11, 12, 8, 6],
                [1, 5, 3, 2]])
print("Array bidimensional ordenado: ")
print( arr[arr[:, 0].argsort()])
print("Orden de los elementos de la primera columna=",arr[:, 0].argsort())