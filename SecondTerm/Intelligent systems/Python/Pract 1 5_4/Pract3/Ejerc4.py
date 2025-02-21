import pandas as pd

df = pd.read_csv(r'Pract3\bmw.csv')
num_elem= int(len(df) * 0.4)

print(df['mileage'].sample(num_elem))
