def buscar_segundo_menor(lista):
    if(len(lista)<2):
        return None
    menor= segundo_menor = float('inf')
    for numero in lista:
        if numero<menor:
            segundo_menor= menor
            menor= numero
        elif numero<segundo_menor and numero!=menor:
            segundo_menor= numero
    return segundo_menor