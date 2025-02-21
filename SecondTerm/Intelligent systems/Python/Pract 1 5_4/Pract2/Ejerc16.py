import numpy as np

matriz = np.random.rand(3,4)

print("Matriz original:")
print(matriz)

matriz[:,:2]= 0
matriz[:, -3:]=1
print("\nMatriz:")
print(matriz)