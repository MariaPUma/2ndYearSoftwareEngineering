import matplotlib.pyplot as plt
import pandas as pd
import numpy as np

df = pd.read_csv(r'Pract4\bmw.csv')

plt.figure(figsize=(20,6))
scatter = plt.scatter(df['price'], df['mileage'], c=df['mpg'], cmap='viridis', alpha=0.8)
plt.colorbar(scatter, label='Eficiencia (mpg)')

#Otra forma:
#
#plt.scatter(df['price'], df['mileage'], c=df['mpg'])
#plt.colorbar(label='Eficiencia (mpg)')

plt.xlabel('Precio')
plt.ylabel('Kilometraje')
plt.title('Ejerc4')
plt.grid()
plt.legend()