cadena1="El que a buen árbol se arrima"
cadena2="buena sombra le cobija"
caracteres1= set()

for caracter in cadena1:
    caracteres1.add(caracter)

    caracter2= set()
    for char in cadena2:
        caracter2.add(char)
    resultado= caracteres1.difference(caracter2)

print(resultado)
