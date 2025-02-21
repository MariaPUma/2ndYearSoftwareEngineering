import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Cobertura {

	private Grafo grafo;

	public Cobertura(Grafo g) {
		grafo = g;
	}

	public Set<Integer> getConjuntoCobertura() {

		//**** Completar la implementación del método*****************
		Set<Integer> sol=new HashSet<>();
		
		Set<Integer> aux =grafo.nodos();
		
		System.out.print("\n");

		
		while (grafo.numAristas() != 0) {
			System.out.print(grafo.nodos() +"\n");
			int másAristas= encontrar (aux, grafo);
			
			if (másAristas != -1) {
				sol.add(másAristas);
				System.out.print("="+másAristas+ "\n"+sol+ "\n");
				//borrar las aristas del nodo que hemos eliminado
				BorrarAristas (grafo, másAristas);
				aux.remove(másAristas);
				
			}
			
		}
		
		return sol;
	}
	
	private static void BorrarAristas(Grafo g, int nodo) {
		// TODO Auto-generated method stub
		Set<Integer> sucesoreSet= g.sucesores(nodo);
		for (Integer sucesor : sucesoreSet) {
			g.removeArista(nodo, sucesor);
		}
	}

	public static int encontrar (Set<Integer> nodos, Grafo g) {
		int mayor= -1;
		int gradomayor=-1;
		
		for (Integer nodo : nodos) {
			if(g.grado(nodo)>gradomayor) {
				System.out.print(g.grado(nodo)+ " (nodo:"+ nodo+" ,"+gradomayor+") ");
				mayor =nodo;
				gradomayor= g.grado(nodo);
			}
		}
		
		
		return mayor;
	}
	
	
}


