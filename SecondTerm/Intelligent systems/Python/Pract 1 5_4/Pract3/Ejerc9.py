import pandas as pd

df = pd.read_csv(r'C:\Users\mpord\Documents\Segundo Cuatrimestre\Sistemas_Inteligentes\Python\Pract 1 5_4\Pract3\bmw.csv')

df2 = df[['mileage','price','mpg']]
long = int(len(df2) *0.2)
print(df2.sample(long))