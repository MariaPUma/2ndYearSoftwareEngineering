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

module WellBalanced where 
import DataStructures.Stack.LinearStack as S

import Test.QuickCheck 


wellBalanced :: String -> Bool
wellBalanced xs = wellBalanced' xs S.empty

wellBalanced' :: String -> Stack Char -> Bool
wellBalanced' [] s = isEmpty s
wellBalanced' (x:xs) s 
            |elem x "{[("           = wellBalanced' xs (S.push x s)
            |x == '}'               = not (isEmpty s) && top s == '{' && wellBalanced' xs (S.pop s)
            |x == ']'               = not (isEmpty s) && top s == '[' && wellBalanced' xs (S.pop s)
            |x == ')'               = not (isEmpty s) && top s == '(' && wellBalanced' xs (S.pop s)
            |otherwise              = wellBalanced' xs s