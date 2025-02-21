import pandas as pd

df = pd.read_csv(r'Pract3\bmw.csv')
millas = df.groupby([ 'year'])['mileage'].mean()

df["media"]=df["mileage"]/(2024-df["year"])
print("DataFrame con columna media:\n",df)


