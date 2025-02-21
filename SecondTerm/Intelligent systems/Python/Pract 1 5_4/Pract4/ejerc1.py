import matplotlib.pyplot as plt
import pandas as pd
import numpy as np

df = pd.read_csv(r'Pract4\bmw.csv')

x = np.linspace(0, 2*(np.pi),100)
plt.figure(figsize=(8,6), layout='constrained')
plt.plot(x,np.sin(x) + np.cos(x),label= "f(x)")

plt.xlabel("x")
plt.ylabel("y")
plt.title("Ejercicio 1")
plt.grid()
plt.legend()




