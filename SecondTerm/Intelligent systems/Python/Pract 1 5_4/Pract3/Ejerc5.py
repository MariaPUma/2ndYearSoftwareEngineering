import pandas as pd

df = pd.read_csv(r'C:\Users\mpord\Documents\Segundo Cuatrimestre\Sistemas_Inteligentes\Python\Pract 1 5_4\Pract3\bmw.csv')
num= 20000
serie= df['mileage']
print(serie[serie<20000])