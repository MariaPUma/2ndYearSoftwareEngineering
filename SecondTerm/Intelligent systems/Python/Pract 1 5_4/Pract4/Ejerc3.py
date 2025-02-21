import matplotlib.pyplot as plt
import pandas as pd
import numpy as np

df = pd.read_csv(r'C:\Users\mpord\Documents\Segundo Cuatrimestre\Sistemas_Inteligentes\Python\Pract 1 5_4\Pract4\bmw.csv')


tabla = df['model'].value_counts()

plt.figure(figsize=(10,6))
plt.bar(tabla.index,tabla.values, color= 'red')
plt.xlabel('Modelos')
plt.ylabel('Frecuencia')
plt.title("Ejerc 3")

plt.xticks(rotation= 45)
plt.legend()
