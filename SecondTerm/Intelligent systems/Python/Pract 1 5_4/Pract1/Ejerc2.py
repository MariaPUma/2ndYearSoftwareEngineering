nombre_producto = input("Introduzca el nombre del producto: ")
precio_producto = float(input("Introduzca el precio del producto: "))
unidad_producto = int(input("Introduzca el numero de unidades de producto: "))
total = precio_producto * unidad_producto
print('{producto}: {unidad:5d} unidades x {precio:6.2f}€={total:8.2f}€'.format(producto=nombre_producto,unidad =unidad_producto,precio=precio_producto,total=total))
