import java.util.ArrayList;
import java.util.List;

public class Laberinto {
	private int[][] laberinto;
	private Posicion entrada, salida;
	private int n; // número de filas y columnas

	public Laberinto(int[][] lab, Posicion ent, Posicion sal) {
		this.laberinto = lab;
		this.entrada = ent;
		this.salida = sal;
		this.n = lab.length;
	}

	public int getNumFilas() {
		return n;
	}

	public int getNumCols() {
		return n;
	}
	
	public int [][] getLaberinto(){
		return laberinto;
	}
	public Posicion getEntrada() {
		return entrada;
	}
	public Posicion getSalida() {
		return salida;
	}
	
	public List<Posicion> encontrarCamino() {
		List<Posicion> lista = new ArrayList<Posicion>();
		lista.add(entrada);
		return (encontrarCamino(lista)) ? lista : null;
	}

	/**
	 * Algoritmo de Vuelta Atrás para encontrar un camino que nos permita
	 * salir del laberinto
	 */
	private boolean encontrarCamino(List<Posicion> sol) {
		
		//***Completar la implementación****
		boolean haySol = false;
		if (esCompleta(sol)) {haySol=true;}
		else {
			int dir =1;
			
			while (dir<=4 && !haySol) {
				Posicion candidato= siguiente(sol.get(sol.size()-1), dir);
				
				if(estaEnLaberinto(candidato) && !esMuro(candidato) && valida(candidato, sol) ) {
					sol.add(candidato);
					haySol = encontrarCamino(sol);
					if(!haySol) {sol.remove(sol.size()-1);	}
				}
				dir++;
				
			}
		}
		return haySol;
		//**********************************
	}

	/**
	 * Comprueba si una solución es completa.
	 */
	private boolean esCompleta(List<Posicion> sol) {
		//***Completar la implementación****
		if(sol.get(sol.size()-1).equals(salida) && sol.get(0).equals(entrada)) {
			return true;
		}else {
			return false;
		}
		//**********************************
	}

	/**
	 * Comprueba que la posición dada es una candidata válida para el siguiente paso
	 */
	private boolean valida(Posicion candidata, List<Posicion> sol) {
		//***Completar la implementación****
		boolean esta=false;
		int i=0;
		while(i<sol.size() && !esta) {
			if(candidata.equals(sol.get(i))) {
				esta=true;
			}
			i++;
		}
		return !esta;
	}

	/**
	 * Devuelve true si en la posición p hay un muro
	 */
	private boolean esMuro(Posicion p) {
		//***Completar la implementación****
		return laberinto[p.getX()][p.getY()] ==-1;
	}

	/**
	 * Devuelve true si la posición dada está dentro del laberinto.
	 */
	private boolean estaEnLaberinto(Posicion pos) {
		//***Completar la implementación****
		return pos.getX()>=0 && pos.getX()<n && pos.getY()>=0 && pos.getY()<n;
	}

	/**
	 * Dada una posición cartesiana devuelve la siguiente posición en el sentido
	 * indicado. Precondición: actual != null
	 * 
	 * @param actual Posición de partida
	 * @param dir    Sentido en el que hay que desplazarse (1->Norte, 2->Sur,
	 *               3->Este, 4-> Oeste)
	 * @return La nueva posición.
	 */
	private Posicion siguiente(Posicion actual, int dir) {
		int x = actual.getX();
		int y = actual.getY();
		if (dir == 1) {
			x--;
		} else if (dir == 2) {
			x++;
		} else if (dir == 3) {
			y++;
		} else {
			y--;
		}
		return new Posicion(x, y);
	}

	/**
	 * Devuelve todos los caminos para salir del laberinto.
	 */
	public List<List<Posicion>> encontrarCaminos() {
		List<List<Posicion>> todosCaminos = new ArrayList<List<Posicion>>();
		List<Posicion> sol = new ArrayList<Posicion>();
		sol.add(entrada);
		encontrarCaminos(sol, todosCaminos);
		return todosCaminos;
	}

	/**
	 * Algoritmo de Vuelta Atrás para encontrar todas las soluciones
	 */
	private void encontrarCaminos(List<Posicion> sol, List<List<Posicion>> todas) {
		//***Completar la implementación****
		if (esCompleta(sol)) {todas.add(new ArrayList<>(sol));}
		else {
			int dir =1;
			
			while (dir<=4 ) {
				Posicion candidato= siguiente(sol.get(sol.size()-1), dir);
				
				if(estaEnLaberinto(candidato) && !esMuro(candidato) && valida(candidato, sol)) {
					sol.add(candidato);
					encontrarCaminos(sol, todas);
					sol.remove(sol.size()-1);
					
				}
				dir++;
				
			}
		}
		
		
	}

	public List<Posicion> encontrarCaminoMasCortoVA() {
		List<Posicion> sol = new ArrayList<Posicion>();
		sol.add(entrada);
		return encontrarCaminoMasCortoVA(sol, null);
	}

	/**
	 * Algoritmo de Vuelta Atrás que devuelve la mejor solución encontrada
	 */
	private List<Posicion> encontrarCaminoMasCortoVA(List<Posicion> sol, List<Posicion> mejor) {

		//***Completar la implementación****
		if (esCompleta(sol)) {
			if(mejor==null || calidad(sol)<calidad(mejor)) {
				mejor=new ArrayList<>(sol);
			}
		}
		else {
			int dir =1;
			
			while (dir<=4) {
				Posicion candidato= siguiente(sol.get(sol.size()-1), dir);
				
				if(estaEnLaberinto(candidato) && !esMuro(candidato) && valida(candidato, sol)) {
					sol.add(candidato);
					mejor=encontrarCaminoMasCortoVA(sol,mejor);
					sol.remove(sol.size()-1);
				}
				dir++;
				
			}
		}
		return mejor;
		
		//**********************************
	}

	/**
	 * Devuelve la calidad de la solución indicada
	 */
	private int calidad(List<Posicion> sol) {
		//***Completar la implementación****
		return sol.size();
	}

	public List<Posicion> encontrarCaminoMasCortoBB() {
		ColaPrioridad c = new ColaPrioridad();// Creamos la estructura de datos

		List<Posicion> solInicial = new ArrayList<>();
		solInicial.add(entrada);
		Estado inicial = new Estado(solInicial, funcionCota(solInicial)); // Creamos el estado inicial

		List<Posicion> mejor = null;	//
		int cotaMejor = Integer.MAX_VALUE; // infinito

		//***Completar la implementación****
		//mejor=encontrarCaminoMasCortoVA();
		c.insertar(inicial);
		
		while (!c.estaVacia()) {
			
			List<Posicion> sol =c.extraer().getCamino();
			
			if(esCompleta(sol)) {
				
				int m= funcionCota(sol);
				
				if(m<cotaMejor) {
					
					mejor=sol;
					cotaMejor=m;
					c.eliminar(cotaMejor);
					
				}
			}else {
				int dir =1;
				List<List<Posicion>> hijos= new ArrayList<>();
				
				while (dir<=4) {
				    List<Posicion> aux=new ArrayList<>(sol);
					Posicion candidato= siguiente(sol.get(sol.size()-1), dir);
					
					if(estaEnLaberinto(candidato) && !esMuro(candidato) && valida(candidato, sol)) {
						aux.add(candidato);
						hijos.add(aux);
						
					}
					dir++;
					
				}
				
				for (List<Posicion> list : hijos) {
					if (funcionCota(list)<cotaMejor) {
						c.insertar(new Estado(list, funcionCota(list)));
					}
				}
			}
		}
		return mejor;
	}

	/**
	 *  Devuelve el valor de cota de la solución indicada. 
	 */
	private int funcionCota(List<Posicion> sol) {
		//***Completar la implementación****
		return sol.size() + Math.abs(salida.getX()-sol.get(sol.size()-1).getX())+
		Math.abs(salida.getY()-sol.get(sol.size()-1).getY());
	}
}
