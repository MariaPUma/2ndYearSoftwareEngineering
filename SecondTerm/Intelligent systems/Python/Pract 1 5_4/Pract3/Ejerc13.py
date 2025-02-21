import pandas as pd

df = pd.read_csv(r'Pract3\bmw.csv')

ndarray = df.to_numpy()

print((ndarray))
print(type(ndarray))