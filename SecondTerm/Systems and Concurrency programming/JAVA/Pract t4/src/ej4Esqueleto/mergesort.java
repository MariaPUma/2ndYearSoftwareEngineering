package ej4Esqueleto;

import java.util.List;      //Interfaz Java para una lista
import java.net.Inet4Address;
import java.util.ArrayList; //Posible implementación de la interfaz List
import java.util.LinkedList;
import java.util.Random;	//Clase para la generación de números aleatorios

public class mergesort {

	//Método estático para crear la lista de valores aleatorios
	private static List<Integer> crear_lista_aleatoria() {
		//Paso 1. Crear objeto de tipo Random
		Random obj = new Random();

		//Paso 2. Crear objeto de tipo List (lista vacia de Integer)
		List<Integer> list = new ArrayList<>();
		
		
		//Paso 3. Generar 100 numeros aleatorios y guardarlos en la lista
		for (int i = 0; i < 10; i++) {
			list.add(obj.nextInt(100));
		}
		//Paso 4. Devolver la lista
		return list;
	}

	public static void main(String[] args) {
		try {
			//Paso 1.Crea una lista de 100 valores int aleatorios (entre 0 y 100)
			List<Integer> lista = mergesort.crear_lista_aleatoria();
			//Paso 1.1. Declarar una lista de Integer
			
			//Paso 1.2. Llamar al metodo anterior para crear la lista
			
			
			//Paso 2. Mostrar por pantalla la lista no ordenada
			System.out.println("Inicial: ");
			System.out.println(lista);
			
			//Paso 3. Crea el primer nodo del árbol y le pasa la lista completa
			Nodo raiz = new Nodo(lista); 
			
			raiz.start();
			raiz.join();
			//Paso 3.1. Crea un nodo y le pasa la lista creada anteriormente
			
			//Paso 3.2. Inicia la hebra
			
			//Paso 3.3. Espera a que la hebra termine la ejecución
			
			
			//Paso 4. Muestra la lista ordenada una vez terminan todas las hebras
			System.out.println("Resultado: ");
			System.out.println(raiz.getLista());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}

//Implementa la clase Nodo - es una hebra
class Nodo extends Thread {
	//Paso 1. Define el atributo "lista"
	private List<Integer> lista;

	/* Paso 2. Implementa el constructor que recibe una lista
	 *       - guarda la lista recibida en el atributo "lista"
	 *       - Si la lista es null lanza IllegarArgumentException
	 */
	public Nodo(List<Integer> l) {
		if (l==null) {
			throw new IllegalArgumentException();
		}
		lista= new ArrayList<>();
		lista.addAll(l);

	}
	
	/* Paso 3. Implementa el método mezcla que recibe dos listas 
	 * 			- l1 y l2 son las listas a mezclar
	 * 			- l1 y l2 ya están ordenadas
	 * 			- la lista mezclada la guarda en el atributo "lista"
	 * 
	 * 	AYUDA: Usar los metodos clear(), add() y addAll() de la interfaz List
	 */
	private void mezcla(List<Integer> l1, List<Integer> l2) {
		int i1 = 0, i2 = 0; //posición inicial en l1 y l2
		
		//Paso 3.1 Vaciar el atributo "lista" 
		lista.clear();
		int i=0;
		//Paso 3.2 Mezclar mientras haya datos en l1 y l2
		while ((i1 < l1.size()) && (i2 < l2.size())) {
			if(l1.get(i1)<l2.get(i2)) {
				lista.add(i,l2.get(i2));
				i2++;
				//System.out.print(l2.get(i2-1));
			}else {
				lista.add(i,l1.get(i1));
				i1++;
				//System.out.print(l1.get(i1-1));

			}
			i++;
		}
		
		/* Paso 3.3 Añadir el resto de valores 
		 *   - Si l1 y l2 son de distinto tamaño al salir del bucle
		 *   - l1 o l2 tendrán valores que incorporar a lista
		 */
		if(i1 < l1.size()) {
			lista.addAll(i1, l1);
		}else if(i2 < l2.size()) {
			lista.addAll(i2, l2);
		}
		//System.out.print("\n mezcla"+lista);
		//System.out.print("\n");

	}
	
	/* Paso 4. Implementar comportamiento de la hebra
	 *	- Caso base: Si el tamaño de la lista es 1 no se hace nada
	 *  - Caso general: 
	 *        - Se crean dos nodos (dos hebras) y a cada nodo se le pasa la mitad de la lista
	 *        - Se empiezan las dos hebras hijas
	 *        - Se espera a que terminen
	 *        - Se mezclan las dos listas
	 *                  
	 *                   
	 *
	 */
	public void run() {
		try {
			//Paso 4.1 Se calcula el tamaño de la lista
			int sz = lista.size();
			//Caso general
			if (sz > 1) {
				//Paso 4.2 Se crean dos listas cada una la mitad de la lista original
				List<Integer> ld= new ArrayList<>();
				List<Integer> li = new ArrayList<>();
				int medio= sz/2;
				for (int i = 0; i < lista.size(); i++) {
					if(i<medio) {
						ld.add(lista.get(i));
					}else {
						li.add(lista.get(i));
					}
					//System.out.print(lista.get(i));
				}
				//System.out.print("\n lista derecha"+ld);
				//System.out.print("\n lista izquierda"+ li);
				//System.out.print("\n");

				
				//Paso 4.3 Se crean dos nodos y se les pasa cada una de las listas creadas
				Nodo nodo_der = new Nodo(ld);
				Nodo nod_izq= new Nodo(li);
				//Paso 4.4 Se inicia la ejecución de los dos nodos (hebras)
				nodo_der.start();
				nod_izq.start();
				//Paso 4.5 Se espera a que terminen los dos nodos (hebras)
				nodo_der.join();
				nod_izq.join();
				
				//Paso 4.6 Se mezclan las dos listas ya ordenadas
				mezcla(nodo_der.getLista(),nod_izq.getLista());
				
				//System.out.print(ld);
				//System.out.print("\n");

				//System.out.print(li);
				//System.out.print("\n");


			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public List<Integer> getLista() {
		return lista;
		
	}
}
