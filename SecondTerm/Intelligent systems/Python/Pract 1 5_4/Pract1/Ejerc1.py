direccion_correo= input("Introduce una direccion de correo: ")
arroba_position=direccion_correo.find('@')
if (arroba_position==-1):
    print("Dirreccion de correo errónea")
else :
    nombre_usuario = direccion_correo[:arroba_position]
    dominio_correo= direccion_correo[(arroba_position + 1):]
    print("El nombre de usuario es: "+nombre_usuario)
    print("El dominiio del correo es: "+ dominio_correo)