import pandas as pd

df = pd.read_csv(r'C:\Users\mpord\Documents\Segundo Cuatrimestre\Sistemas_Inteligentes\Python\Pract 1 5_4\Pract3\bmw.csv')
millas = df.groupby([ 'year'])['mileage'].mean()

df["media"]=df["mileage"]/(2024-df["year"])
print("DataFrame con columna media:\n",df)


