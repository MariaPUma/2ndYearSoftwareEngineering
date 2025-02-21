import pandas as pd

df = pd.read_csv(r'C:\Users\mpord\Documents\Segundo Cuatrimestre\Sistemas_Inteligentes\Python\Pract 1 5_4\Pract3\bmw.csv')
num_elem= int(len(df) * 0.4)

print(df['mileage'].sample(num_elem))
