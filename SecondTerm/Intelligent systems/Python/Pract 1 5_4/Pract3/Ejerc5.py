import pandas as pd

df = pd.read_csv(r'Pract3\bmw.csv')
num= 20000
serie= df['mileage']
print(serie[serie<20000])