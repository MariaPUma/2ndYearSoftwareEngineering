package sensores_2_esqueleto;

import java.util.Iterator;
import java.util.concurrent.*;

public class Mediciones {
	private int mediciones[];
	private int numMediciones = 0;

	public Mediciones() {
		mediciones = new int[3];
		inicializarArray(mediciones);

	}

	private void inicializarArray(int[] mediciones2) {
		// TODO Auto-generated method stub
		for (int i = 0; i < mediciones2.length; i++) {
			mediciones[i] = -1;
		}
	}

	/* Se para la ejecución para tomar las mediciones */
	private Semaphore tomarmedidas = new Semaphore(1);
	/* CS_TRABAJADOR espera a que se tomen las medidas correspondientes */
	private Semaphore haytrabajo = new Semaphore(0);

	private Semaphore mutex = new Semaphore(1);

	public void nuevaMedicion(int id, int valor) throws InterruptedException {
		tomarmedidas.acquire();

		mutex.acquire();
		mediciones[id] = valor;
		numMediciones++;

		if (numMediciones < 3 && todasOcupadas(mediciones)) {
			tomarmedidas.release();
		} else {
			haytrabajo.release();
		}
		mutex.release();
	}

	private boolean todasOcupadas(int[] mediciones2) {
		// TODO Auto-generated method stub
		int pos = 0;
		boolean vacio = false;

		while (pos < mediciones2.length) {
			if (mediciones2[pos] == -1) {
				vacio = true;
				numMediciones--;
			}
			pos++;
		}

		return vacio;
	}

	public int[] leerMediciones() throws InterruptedException {
		// El trabajador se debe quedar bloqueado mientras no haya mediciones
		haytrabajo.acquire();
		System.out.println("El trabajador lee las mediciones");

		return mediciones;
	}

	public void finTarea() throws InterruptedException {
		// El trabajador termina y despierta a los tres sensores
		//mutex.acquire();
		System.out.println("El trabajador termina su tarea");
		numMediciones = 0;
		inicializarArray(mediciones);

		//mutex.release();
		tomarmedidas.release();

	}

}
