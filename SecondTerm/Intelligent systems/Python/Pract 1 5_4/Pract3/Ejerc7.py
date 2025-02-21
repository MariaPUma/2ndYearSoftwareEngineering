import pandas as pd

df = pd.read_csv(r'C:\Users\mpord\Documents\Segundo Cuatrimestre\Sistemas_Inteligentes\Python\Pract 1 5_4\Pract3\bmw.csv')
serie_engineSize=df['engineSize']
media= serie_engineSize.mean()
mediana= serie_engineSize.median()
desv= serie_engineSize.std()

print (serie_engineSize)
print("Media: ")
print(media)
print( "Mediana: ")
print(mediana)
print("Desviación Típica: ")
print( desv)
print("Máximo:")
print(serie_engineSize.max())
print("Mínimo:")
print(serie_engineSize.min())
