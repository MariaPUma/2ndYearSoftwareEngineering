package canibales_3_esqueleto;

import java.util.concurrent.*;

public class Olla implements IOlla {

	private int numRac = 10;
	private int RacRestantes = 10;

	// SEMAFOROS BINARIOS
	// CS_COCINERO el cocinero espera para preparar la siguiente tanda de comida
	private Semaphore ollaVacia = new Semaphore(0, true);
	// CS_CANÍBAL el canibal toma turno para comer de la olla
	private Semaphore turnoComer = new Semaphore(1, true);
	// MUTEX para controlar la variable RacRestantes
	private Semaphore mutex = new Semaphore(1, true);

	public void nuevoExplorador() throws InterruptedException {
		// Cocinero se levanta por ser llamado por el caníbal
		ollaVacia.acquire();

		System.out.println("El cocinero mete a un explorador dentro de la olla");
		RacRestantes = numRac;
		System.out.println("El cocinero se va a descansar");
		turnoComer.release();

	}

	public void comeRacion(int id) throws InterruptedException {

		mutex.acquire();
		turnoComer.acquire();

		System.out.println("El Caníbal " + id + " toma una ración de la olla");
		RacRestantes--;

		// Caso 1 en el que el numRac == 0
		if (RacRestantes == 0) {

			System.out.println("El Caníbal " + id + " llama al cocinero");
			ollaVacia.release();

			// Caso 2 en el que el numRac >0
		} else {
			turnoComer.release();
		}

		mutex.release();

	}

}