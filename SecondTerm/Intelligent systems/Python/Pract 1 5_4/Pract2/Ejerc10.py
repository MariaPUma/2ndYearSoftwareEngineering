# import numpy as np
# def normaliza_array_bidimensional (arr):
#     media= np.mean(arr,axis=0)
#     desv_tipica = np.std(arr,axis=0)

#     norm= (arr-media)/desv_tipica
#     return norm

# array = np.array([[10, 20, 30],
#                   [15, 25, 35],
#                   [12, 22, 32],
#                   [18, 28, 38]])

# res= normaliza_array_bidimensional(array)

# print(res)
import numpy as np
arr = np.array([[10, 20, 30],
                  [15, 25, 35],
                  [12, 22, 32],
                  [18, 28, 38]])
media= np.mean(arr,axis=0)
desv_tipica = np.std(arr,axis=0)

norm= (arr-media)/desv_tipica


print(norm)
