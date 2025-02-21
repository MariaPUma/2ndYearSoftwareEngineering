import Data.Maybe 

data BST a = Empty |Node a (BST a) (BST a)

insert :: (Ord a)=> a ->BST a ->BST a
insert e Empty =Node e Empty Empty
insert e (Node x lt rt)
                |e<x        =Node x (insert e lt) rt
                |e>x        =Node x lt (insert e rt)
                |otherwise  =Node e lt rt

data Persona = P String String Integer

p1:: Persona 
p1 = P "657578585X" "Pepe" 30

p2 :: Persona
p2 = P "657578585X" "Pepe" 31
-- 
-- 
instance Eq Persona where 
    P dni _ _ == P dni2 _ _ = dni == dni2

search:: (Ord a) =>a -> BST a -> Maybe a
search e Empty = Nothing 
search e (Node x lt rt)
            |e==x           =Just x
            |e<x            =search e lt 
            |otherwise      =search e rt 

isElem :: (Ord a ) => a ->BST a -> Bool
isElem e bst = isJust (search e bst)

delete :: (Ord a) => a -> BST a -> BST a 
delete e Empty = Empty
delete e (Node x lt rt)
            | e<x       = Node x (delete e lt) rt
            | e>x       = Node x lt (delete e rt)
            |otherwise  = combine lt rt 

combine :: (Ord a) => BST a -> BST a-> BST a 
combine lt Empty = lt
combine Empty rt = rt 
combine lt rt    = Node x lt rt'
    where 
        (x, rt')= split rt

--precondición el arbol no es vacio 
split:: BST a -> (a, BST a)
split (Node x Empty rt) = (x,rt)
split (Node x lt rt)    = (x', Node x lt' rt)
            where 
                (x',lt') = split lt