import pandas as pd

df = pd.read_csv(r'Pract3\bmw.csv')

df2 = df[['mileage','price','mpg']]
long = int(len(df2) *0.2)
print(df2.sample(long))