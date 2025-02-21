import pandas as pd

df = pd.read_csv(r'Pract3\bmw.csv')

print(df)
df.loc[df['model'].str.contains('Series'), 'model'] = df['model'].str.replace(' Series', '').str.split().apply(lambda x: 'Serie ' + ''.join(reversed(x[-1])))
#numeros= df["model"].str.extract(r"(\d+)")
#df["model"]= "Serie "+numeros
print(df)