lista=[(1, 4), (2, 3), (5, 1), (3, 2), (7, 5, 1)]

nueva_lista=[]

for i in range(len(lista)):
    nueva_lista.append(sum(lista[i]))
nueva_lista.sort(reverse=False)
print(nueva_lista)