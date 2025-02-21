package sensores_2_esqueleto.copy;

import java.util.Iterator;
import java.util.concurrent.*;

public class Mediciones {
	private int mediciones[];
	private int numMediciones = 0;

	public Mediciones() {
		mediciones = new int[3];
		
	}
	
	//MUTEX para evitar la modificacion de nummedicioes
	private Semaphore mutex = new Semaphore(1);
	
	//CS_SENSORES : Esperan a qu eel trabjador no este trabajando
	private Semaphore barrera = new Semaphore(0);
	
	//CS_TRABAJADOR . Espera a que las medidas ya hayan sido tomadas
	private Semaphore trabajar = new Semaphore(0);

	
	public void nuevaMedicion(int id, int valor) throws InterruptedException {
		mutex.acquire();
		mediciones[id]= valor;
		numMediciones++;
		
		if(numMediciones==3) {
			trabajar.release();
		}
		
		mutex.release();
		barrera.acquire();
		
		mutex.acquire();
		numMediciones--;
		if(numMediciones>0) {
			barrera.release();
		}
		mutex.release();
		
	}

	public int[] leerMediciones() throws InterruptedException {
		trabajar.acquire();
		
		System.out.println("El trabajador lee las mediciones");
		
		return mediciones;
	}

	public void finTarea() throws InterruptedException {
		mutex.acquire();
		System.out.println("El trabajador termina el trabajo");
		barrera.release();
		
		mutex.release();
		

	}

}
