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

module QueueP 
    ( Queue
    , empty
    , isEmpty
    , enqueue
    , first
    , dequeue
    ) where


import Data.List(intercalate)
import Test.QuickCheck

data QueueP a= Empty| Node a (QueueP a)

empty :: QueueP a
empty = Empty

isEmpty :: QueueP A->Bool
isEmpty Empty = True
isEmpty n     = False

enqueue :: (Ord a) => a -> QueueP a -> QueueP a 



-- Showing a queue
instance (Show a) => Show (QueueP a) where
  show q = "LinearQueueP(" ++ intercalate "," (aux q) ++ ")"
    where
    aux Empty      =  []
    aux (Node x q) =  show x : aux q

-- Queue equality
instance (Eq a) => Eq (QueueP a) where
    Empty      == Empty        =  True
    (Node x q) == (Node x' q') =  x==x' && q==q'
    _          == _            =  False

-- This instance is used by QuickCheck to generate random queues
instance (Arbitrary a) => Arbitrary (QueueP a) where
    arbitrary = do
      xs <- listOf arbitrary
      return (foldr enqueue empty xs)



