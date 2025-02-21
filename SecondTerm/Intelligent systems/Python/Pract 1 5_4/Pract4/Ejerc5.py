import matplotlib.pyplot as plt
import pandas as pd
import numpy as np

df = pd.read_csv(r'C:\Users\mpord\Documents\Segundo Cuatrimestre\Sistemas_Inteligentes\Python\Pract 1 5_4\Pract4\bmw.csv')
plt.figure(figsize=(10,6))
plt.hist(df['price'], edgecolor='black', color='pink')
plt.xlabel('Precio')
plt.ylabel('Frecuencia')
plt.legend()