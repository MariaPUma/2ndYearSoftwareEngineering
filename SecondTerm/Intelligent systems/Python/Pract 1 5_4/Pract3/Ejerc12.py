import pandas as pd

df = pd.read_csv(r'C:\Users\mpord\Documents\Segundo Cuatrimestre\Sistemas_Inteligentes\Python\Pract 1 5_4\Pract3\bmw.csv')
#model=" 3 Series", year=2023, price = 22572, transmission = "Automatic", mileage = 74120, fuelType = "Diesel", tax = 160, mpg = 58.4, engineSize = 2.0


d= pd.Series({'model': ' 3 Series', 'year': 2023, 'price': 22572, 'transmission': 'Automatic','mileage': 74120, 'fuelType': 'Diesel', 'tax': 160, 'mpg': 58.4, 'engineSize': 2.0})


print(df._append(d,ignore_index=True))