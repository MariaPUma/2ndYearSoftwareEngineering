import pandas as pd

df = pd.read_csv(r'Pract3\bmw.csv')
#model=" 3 Series", year=2023, price = 22572, transmission = "Automatic", mileage = 74120, fuelType = "Diesel", tax = 160, mpg = 58.4, engineSize = 2.0


d= pd.Series({'model': ' 3 Series', 'year': 2023, 'price': 22572, 'transmission': 'Automatic','mileage': 74120, 'fuelType': 'Diesel', 'tax': 160, 'mpg': 58.4, 'engineSize': 2.0})


print(df._append(d,ignore_index=True))