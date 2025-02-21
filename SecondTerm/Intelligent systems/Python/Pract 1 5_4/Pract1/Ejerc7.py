diccionario  = {'a': 1, 'b': 2, 'c': 2, 'd': 3, 'e': 2}
valor = 2

nuevo_diccionario= diccionario.copy()

for c,v in diccionario.items():
    if(valor==v):
        del nuevo_diccionario[c]
print(nuevo_diccionario)