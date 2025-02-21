import pandas as pd

df = pd.read_csv(r'C:\Users\mpord\Documents\Segundo Cuatrimestre\Sistemas_Inteligentes\Python\Pract 1 5_4\Pract3\bmw.csv')

print("Número de filas")
print(df.shape[0])
print("Número de columnas")
print(df.shape[1])


print("Antepenúltimo")
print(df.iloc[-3])