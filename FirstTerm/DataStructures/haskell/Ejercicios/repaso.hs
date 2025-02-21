-------------------------------------------------------------------------------
-- Estructuras de Datos. 2º Curso. ETSI Informática. UMA
--
-- (completa y sustituye los siguientes datos)
-- Titulación: Grado en Ingeniería [Informática | del Software | de Computadores].
-- Alumno: APELLIDOS, NOMBRE
-- Fecha de entrega: DIA | MES | AÑO
--
-- Relación de Ejercicios 3. Ejercicios resueltos: ..........
--
-------------------------------------------------------------------------------
import DataStructures.Queue.LinearQueue

import Test.QuickCheck 

e1 :: [Int]-> ([Int],[Int])
e1 xs= (foldr (\x -> if even x then (x:) else ([]++)) [] xs,foldr (\x -> if not(even x) then (x:) else ([]++)) [] xs)

data Vector a = Empty | Node Integer Char (Vector a)
--COLAS 
imitArray inicializar el array 
un numero letra 

a y 5 
Node 0 a (Node 1 a (Node 2 a))
initArray:: Integer -> a -> Vector A
initArray 
set 
get 
reverse 
size 
producto escalar 

producto_esc