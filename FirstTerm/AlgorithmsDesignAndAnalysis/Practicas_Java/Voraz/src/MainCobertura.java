import java.util.Set;

public class MainCobertura {

	public static void main(String[] args) {
		int[][] a = {{0,1,1,0},
					 {1,0,1,0},
					 {1,1,0,1},
					 {0,0,1,0}
					 
		/*int[][] a = {{0,0,0,1,1},
					{0,0,1,1,0},
					{0,1,0,1,0},
					{1,1,1,0,0},
					{1,0,0,0,0}*/
		};
		Grafo g = new Grafo(a);
		System.out.print(g.nodos());
		Cobertura p= new Cobertura(g);
		Set<Integer> sol = p.getConjuntoCobertura();
		System.out.println(sol);
	}
	

}
