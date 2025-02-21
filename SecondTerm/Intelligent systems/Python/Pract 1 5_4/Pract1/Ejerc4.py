lista = [11, 55, 81, 47, 23, 90, 25, 93, 30, 15]

indice=0
es_Par= False
nueva_lista=[]
while not(es_Par):
    if(lista[indice]%2==0):
        es_Par=True
    else:
        nueva_lista.append(lista[indice])
    indice=indice+1
print(nueva_lista)
