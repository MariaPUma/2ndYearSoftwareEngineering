import pandas as pd

df = pd.read_csv(r'Pract3\bmw.csv')

df2 = df[(df['mileage']<10000 )& (df['mpg']>40)]

print(df2)