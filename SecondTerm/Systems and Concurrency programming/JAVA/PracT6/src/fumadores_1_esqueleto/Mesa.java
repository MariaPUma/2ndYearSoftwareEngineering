package fumadores_1_esqueleto;

import java.util.concurrent.Semaphore;

public class Mesa {
	// 0 (Papel) /1 (Tabaco) /2 (Cerillas);
	// ingrediente == 0 - en la mesa están los ing. 1 y 2
	// ingrediente == 1 - en la mesa están los ing. 0 y 2
	// ingrediente == 2 - en la mesa están los ing. 0 y 1
	private int ingrediente;

	public Mesa() {
		ingrediente = -1; // -1 indica que no hay nada en la mesa
	}
	/*
	 * CONDICIÓN DE EXCLUSIÓN MUTUA Manipulacion de la variable ingrediente
	 */
	// private Semaphore mutex= new Semaphore(1);

	/*
	 * CS_Fumador id 0 - el fumador con id == 0 tiene que esperar si el ingrediente
	 * que falta en la mesa no es el 0. O dicho de otra manera, si en la mesa no
	 * están los ingredientes que le faltan a él (los ingredientes 1 y 2)
	 */
	private Semaphore hayIngred1_2 = new Semaphore(0);
	/*
	 * CS_Fumador id 1 - el fumador con id == 1 tiene que esperar si el ingrediente
	 * que falta en la mesa no es el 1. O dicho de otra manera, si en la mesa no
	 * están los ingredientes que le faltan a él (los ingredientes 0 y 2)
	 */
	private Semaphore hayIngred0_2 = new Semaphore(0);

	/*
	 * CS_Fumador id 2 - el fumador con id == 2 tiene que esperar si el ingrediente
	 * que falta en la mesa no es el 2. O dicho de otra manera, si en la mesa no
	 * están los ingredientes que le faltan a él (los ingredientes 0 y 1)
	 */
	private Semaphore hayIngred0_1 = new Semaphore(0);
	// Se puede aplicarhaciendo un array

	public void quiereFumar(int id) throws InterruptedException {
		switch (id) {
		case 0: {
			hayIngred1_2.acquire();
			break;
		}
		case 1: {
			hayIngred0_2.acquire();
			break;
		}
		case 2: {
			hayIngred0_1.acquire();
			break;
		}
		}

		System.out.println("Fumador " + id + " empieza a fumar");
	}

	/* El fumador id indica que ha terminado de fumar */
	public void terminaFumar(int id) throws InterruptedException {
		System.out.println("Fumador " + id + " termina de fumar ");
		ingrediente = -1;

		objMesa.release();

	}

	// CS_Agente - El agente tiene que esperar si la mesa no está vacía
	private Semaphore objMesa = new Semaphore(1);

	public void poneIngrediente(int ing) throws InterruptedException {
		objMesa.acquire();// El agente toma el turno de la mesa

		ingrediente = ing;
		System.out.println("Agente pone ingredientes que necesita fumador " + ingrediente);

		switch (ing) {
		case 0: {
			hayIngred1_2.release();
			break;
		}
		case 1: {
			hayIngred0_2.release();
			break;
		}
		case 2: {
			hayIngred0_1.release();
			break;
		}
		}

	}
}
