import pandas as pd

df = pd.read_csv(r'Pract3\bmw.csv')

print("Número de filas")
print(df.shape[0])
print("Número de columnas")
print(df.shape[1])


print("Antepenúltimo")
print(df.iloc[-3])