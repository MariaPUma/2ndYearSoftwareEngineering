import matplotlib.pyplot as plt
import pandas as pd
import numpy as np

df = pd.read_csv(r'C:\Users\mpord\Documents\Segundo Cuatrimestre\Sistemas_Inteligentes\Python\Pract 1 5_4\Pract4\bmw.csv')

x= np.linspace(0, 2* np.pi,100)
plt.figure(figsize=(10,6), layout='constrained')
plt.plot(x,np.sin(x), label= "f(x)")


plt.plot(x,np.log(x+1), label="g(x)")

plt.xlabel("x")
plt.ylabel("y")
plt.title("Ejercicio 2")
plt.grid()
plt.legend()
