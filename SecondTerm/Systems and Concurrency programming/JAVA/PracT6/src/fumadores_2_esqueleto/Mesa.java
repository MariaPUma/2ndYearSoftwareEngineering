package fumadores_2_esqueleto;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Mesa {
	//0 (Papel) /1 (Tabaco) /2 (Cerillas);	
	//ingrediente == 0 - en la mesa están los ing. 1 y 2	
	//ingrediente == 1 - en la mesa están los ing. 0 y 2	
	//ingrediente == 2 - en la mesa están los ing. 0 y 1
	private int ingrediente; 
		
	
	public Mesa() {
		ingrediente = -1;	 //-1 indica que no hay nada en la mesa 
		
		for (int i = 0; i < fumadores.length; i++) {
			fumadores[i]= new Semaphore(0);
			
		}
	}
	/*CONDICIÓN DE EXCLUSIÓN MUTUA
	 * Manipulacion de la variable ingrediente*/
	//private Semaphore mutex= new Semaphore(1);
	
	
	/* CS_Fumador id 0 - el fumador con id == 0 tiene que esperar si el ingrediente que falta en la mesa
	                  no es el 0. O dicho de otra manera, si en la mesa no están los ingredientes que le 
					  faltan a él (los ingredientes 1 y 2)
	*/
	
	/* CS_Fumador id 1 - el fumador con id == 1 tiene que esperar si el ingrediente que falta en la mesa
	                   no es el 1. O dicho de otra manera, si en la mesa no están los ingredientes que le 
    				   faltan a él (los ingredientes 0 y 2)
    */

	
	/* CS_Fumador id 2 - el fumador con id == 2 tiene que esperar si el ingrediente que falta en la mesa
	                    no es el 2. O dicho de otra manera, si en la mesa no están los ingredientes que le 
						faltan a él (los ingredientes 0 y 1)
	*/
	private Semaphore [] fumadores = new Semaphore[3];
	/*
	 * En el siguiente array almacenaremos los 
	 */

	public  void quiereFumar(int id) throws InterruptedException {
		
		fumadores[id].acquire();
		
		System.out.println("Fumador "+id+" empieza a fumar");
		
	}

	/* El fumador id indica que ha terminado de fumar */
	public void terminaFumar(int id) throws InterruptedException {
		System.out.println("Fumador "+id+" termina de fumar ");	
		ingrediente= -1;
		
		mesaVacia.release();
	}

	// CS_Agente - El agente tiene que esperar si la mesa no está vacía
	private Semaphore mesaVacia = new Semaphore(1);
	public void poneIngrediente(int ing) throws InterruptedException {
		mesaVacia.acquire();
		ingrediente= ing;
		System.out.println("Agente pone ingredientes que necesita fumador "+ingrediente);
		//System.out.print(ing);
		fumadores[ing].release();
		
		
		
		

	}
}
