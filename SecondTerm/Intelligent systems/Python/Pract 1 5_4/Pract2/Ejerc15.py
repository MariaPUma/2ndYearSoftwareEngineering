import numpy as np
arr = np.array([10, 5, 8, 12, 3, 20, 15, 7])

k= 2

#En la siguiente función ordena los números de mayor a menor pero solo mostrando los índices
elementos_may = np.argsort(-arr)
print (elementos_may)
print(np.sort(elementos_may[:k]))

