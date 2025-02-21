def es_Primo (num):
    for n in range (2,num):
        if num%n ==0:
            return False
    return True



a=int(input("Introduzca el intervalo del inicio: "))
b=int(input("Introduzca el intervalo del fin: "))

for n in range(a,b):
    if es_Primo(n):
        print(n)