import pandas as pd

df = pd.read_csv(r'C:\Users\mpord\Documents\Segundo Cuatrimestre\Sistemas_Inteligentes\Python\Pract 1 5_4\Pract3\bmw.csv')

df2 = df[(df['mileage']<10000 )& (df['mpg']>40)]

print(df2)