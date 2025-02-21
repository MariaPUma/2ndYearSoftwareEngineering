import numpy as np

a= np.arange(0,20).reshape(5,4)

media = np.mean(a,axis=0)

print(media)