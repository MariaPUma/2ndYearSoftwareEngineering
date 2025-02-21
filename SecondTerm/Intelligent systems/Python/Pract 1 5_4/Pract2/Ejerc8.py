import numpy as np

array_bi= np.arange(0,12).reshape(3,4)
#print(array_bi)
print(np.amax(array_bi,axis=1))