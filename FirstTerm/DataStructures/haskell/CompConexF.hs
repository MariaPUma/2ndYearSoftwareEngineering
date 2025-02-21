module CompConexF() where
    import DataStructures.Graph.DiGraph
    import DataStructures.Graph.DiGraphBFT
            ( 
                dft 
            )
import Data.List

reverseDiGraph:: Eq a => DiGraph a -> DiGraph a
reverseDiGraph dg = mkDiGraphSuc (vertices dg) [x:-> y | y <-(vertices dg), x <- (successors dg y)]


restrictDiGraph:: Eq a => DiGraph a -> [a]-> DiGraph a 
restrictDiGraph dg vs = mkDiGraphSuc vs [y:->x | y <- vs , x <-(successors dg y), elem x vs ]

type SCC a = [a]

sccOf:: Ord a => DiGraph a -> a -> SCC a 
sccOf dg v0 = dft (reverseDiGraph (restrictDiGraph dg vs)) v0
            where 
                vs= dft dg v0

sccs::Ord a => DiGraph a -> [SCC a]
sccs = undefined