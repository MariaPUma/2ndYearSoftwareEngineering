
import matplotlib.pyplot as plt
import pandas as pd
import numpy as np


MAXIMO_ITERACIONES = 80
def mandelbrot(a,b):
    c = complex(a,b)
    z = 0
    n = 0
    while abs(z) <= 2 and n < MAXIMO_ITERACIONES:
        z = z*z + c
        n += 1
    color_pixel = 255 - int(n * 255 / MAXIMO_ITERACIONES)
    return color_pixel

df = pd.read_csv(r'C:\Users\mpord\Documents\Segundo Cuatrimestre\Sistemas_Inteligentes\Python\Pract 1 5_4\Pract4\bmw.csv')
real_min = -2
real_max = 1
imag_min = -1
imag_max = 1

width= 600
height= 400 
real_values = np.linspace(real_min, real_max, width)
imag_values = np.linspace(imag_min, imag_max, height)

x,y = np.meshgrid(real_values,imag_values)

man = np.vectorize(mandelbrot)
diag = man(x,y)

plt.figure(figsize=(12, 8))
plt.imshow(diag, cmap='hot', extent=(real_min, real_max, imag_min, imag_max))
plt.colorbar()
plt.title('Fractal de Mandelbrot')
plt.xlabel('Parte real')
plt.ylabel('Parte imaginaria')
plt.show()