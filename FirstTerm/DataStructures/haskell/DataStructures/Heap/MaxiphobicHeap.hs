-------------------------------------------------------------------------------
-- Maxiphobic Heaps
--
-- see: Fun with Binary Heap Trees
--      & Alternatives to two Classic Data Structures
--      by Chris Okasaki
--
-- Data Structures. Grado en Informática. UMA.
-- Pepe Gallardo, 2012
-------------------------------------------------------------------------------

module DataStructures.Heap.MaxiphobicHeap
  ( Heap
  , empty
  , isEmpty
  , minElem
  , delMin
  , insert
  , merge

  , mkHeap
  , size
  , isHeap

  , drawOnWith
  ) where

import DataStructures.Graphics.DrawTrees
import Test.QuickCheck

data Heap a  = Empty | Node a Int (Heap a) (Heap a) deriving Show

-- number of elements
size :: Heap a -> Int
size Empty            = 0
size (Node _ sz _ _)  = sz

empty :: Heap a
empty  = Empty

isEmpty :: Heap a -> Bool
isEmpty Empty  = True
isEmpty _      = False

-- Partially sorts heaps according to their sizes.
-- Returns largest heap in first position
sort3 :: Heap a -> Heap a -> Heap a -> (Heap a, Heap a, Heap a)
sort3 p1 p2 p3
        |size(p1)>= size(p2) && size(p1)>= size(p3)  = (p1,p2,p3)
        |size(p2)>= size(p1) && size(p2)>= size(p3)  = (p2,p1,p3)
        |otherwise                                   = (p3,p2,p1)




-- Recursively merges smallest subheaps. Achieves O(log n) complexity
merge :: (Ord a) => Heap a -> Heap a -> Heap a
merge Empty p2 = p2
merge p1 Empty = p1
merge h1@(Node x1 w1 l1 r1) h2@(Node x2 w2 l2 r2)
        |x1<x2      = (Node x1 (w1+w2) max1 (merge rama11 rama21))
        |otherwise  = (Node x2 (w1+w2) max2 (merge rama12 rama22))
          where (max1, rama11, rama21)= sort3 l1 r1 h2
                (max2, rama12, rama22)= sort3 l2 r2 h1

-- Returns a heap with a single element
singleton :: a -> Heap a
singleton x = (Node x 1 (Empty)(Empty))

-- Inserts an element in a heap
insert :: (Ord a) => a -> Heap a -> Heap a
insert x h= merge (singleton x) h

-- Returns minimum element in heap
minElem :: Heap a -> a
minElem Empty = error "No hay elementos en el monticulo"
minElem (Node x _ _ _)= x

-- Deletes minimum element from heap
delMin :: (Ord a) => Heap a -> Heap a
delMin Empty = error "No hay elementos en el monticulo"
delMin (Node x w l r)= merge l r



-- Efficient O(n) bottom-up construction for heaps
mkHeap :: (Ord a) => [a] -> Heap a
mkHeap []  = empty
mkHeap xs  = mergeLoop (map singleton xs)
  where
    mergeLoop [h]  = h
    mergeLoop hs   = mergeLoop (mergePairs hs)

    mergePairs []         = []
    mergePairs [h]        = [h]
    mergePairs (h:h':hs)  = merge h h' : mergePairs hs

-------------------------------------------------------------------------------
-- Generating arbritray Heaps
-------------------------------------------------------------------------------

instance (Ord a, Arbitrary a) => Arbitrary (Heap a) where
  arbitrary  = do
    xs <- arbitrary
    return (mkHeap xs)

-------------------------------------------------------------------------------
-- Invariants
-------------------------------------------------------------------------------

isHeap :: (Ord a) => Heap a -> Bool
isHeap Empty             = True
isHeap (Node x _ lh rh)  = x `lessEq` lh && x `lessEq` rh
                            && isHeap lh && isHeap rh
 where
  x `lessEq` Empty            = True
  x `lessEq` (Node x' _ _ _)  = x <= x'


-------------------------------------------------------------------------------
-- Drawing a Heap
-------------------------------------------------------------------------------

instance Subtrees (Heap a) where
  subtrees Empty             = []
  subtrees (Node _ _ lh rh)  = [lh,rh]

  isEmptyTree  = isEmpty

instance (Show a) => ShowNode (Heap a) where
  showNode (Node x _ _ _) = show x

drawOnWith :: FilePath -> (a -> String) -> Heap a -> IO ()
drawOnWith file toString = _drawOnWith file showHeap
 where
  showHeap (Node x _ _ _) = toString x
