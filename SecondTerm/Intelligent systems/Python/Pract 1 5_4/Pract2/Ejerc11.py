import numpy as np
import numpy as np
arr = np.array([[10, 20, 30],
                  [15, 25, 35],
                  [12, 22, 32],
                  [18, 28, 38]])
media= np.mean(arr,axis=1,keepdims=True)
desv_tipica = np.std(arr,axis=1,keepdims=True)

norm= (arr-media)/desv_tipica


print(norm)
