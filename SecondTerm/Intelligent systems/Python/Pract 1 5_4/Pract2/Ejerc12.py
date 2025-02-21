import numpy as np
arr = np.array([[7, 0, 4],
                [9, 1, 1],
                [2, 9, 0],
                [7, 7, 5]])
print("Indices:")
ind_maxC= np.argmax(arr,axis=0)
ind_maxF= np.argmax(arr,axis=1)

ind_minC= np.argmin(arr,axis=0)
ind_minF= np.argmin(arr,axis=1)

print("Min Filas= ",ind_minF," Max Filas= ",ind_maxF)
print("Min Columnas= ",ind_minC," Max Columnas= ",ind_maxC)

# Si buscamos el min y el máx de todo el array
ind_min= np.unravel_index(np.argmin(arr), arr.shape)
ind_max= np.unravel_index(np.argmax(arr),arr.shape)
print("Min array bidimimensional=",ind_min,"Max array bidimimensional=",ind_max)