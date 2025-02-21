package paseo_1v;

import java.util.concurrent.Semaphore;

public class Barca2 implements Barca{
	private int N;
	private Semaphore barquero= new Semaphore(0);
	private Semaphore menor = new Semaphore(0);
	private Semaphore adulto= new Semaphore(0);
	private Semaphore mutex = new Semaphore(1);
	
	private int numMen= 0;
	private int numAdult= 0;
	private int asientosLibres;
	
	public Barca2(int N) {
		this.N = N; //capacidad de la Barca
		asientosLibres= N;
	}
	
	/*
	 * El menor id se sube a la barca. Si es el último pasajero avisa al barquero
	 * de que la barca está completa.
	 * Como hay un total de N excursionistas entre menores y adultos, en esta versión,
	 * un menor que quiera subir podrá hacerlo sin tener que esperar.
	 * A continuación, espera hasta que finalice el viaje.
	 * Cuando lo avisan se baja de la barca
	 */
	public void  subeMenor(int id) throws InterruptedException {
		mutex.acquire();
		
		//TODO
		System.out.println("El menor "+id+" se ha subido a la barca");
		//TODO
		asientosLibres--;
		numMen++;
		//if(asientosLibres>0)menor.release();
		if(asientosLibres==0)barquero.release();
		
		
		mutex.release();
		
		menor.acquire();
		
		mutex.acquire();
		
		System.out.println("El menor "+id+" se ha bajado de la barca");
		//TODO
		asientosLibres++;
		numMen--;
		if(numMen>0)menor.release();
		if(numMen==0 && numAdult>0)adulto.release();
		mutex.release();
	}

	/*
	 * El adulto id se sube a la barca. Si es el último pasajero avisa al barquero
	 * de que la barca está completa.
	 * Como hay un total de N excursionistas entre menores y adultos, en esta versión, un adulto que 
 	 * quiera subir podrá hacerlo sin tener que esperar. 
	 * A continuación, espera hasta que finalice el paseo.
	 * Cuando lo avisan se baja de la barca
	 */
	public void subeAdulto(int id) throws InterruptedException {
		mutex.acquire();
		
		//TODO
		System.out.println("El adulto "+id+" se ha subido a la barca");
		//TODO
		asientosLibres--;
		numAdult++;
		System.out.println(asientosLibres);
		if(asientosLibres==0)barquero.release();
		
		mutex.release();
		
		adulto.acquire();
		mutex.acquire();
		
		System.out.println("El adulto "+id+" se ha bajado de la barca");
		//TODO
		asientosLibres++;
		numAdult--;
		if(numAdult>0)adulto.release();
		
		mutex.release();
	}
	/*
	 * El barquero espera hasta que la barca tenga N pasajeros para
	 * comenzar el paseo
	 */
	public void esperoLleno() throws InterruptedException {
		//TODO
		barquero.acquire();
		mutex.acquire();
		System.out.println("Comienza el viaje!!!");
		mutex.release();
	}
	/*
	 * Cuando termina el paseo, el barquero avisa a los excusionistas  que se pueden bajar.
	 * Debe garantizarse que siempre se bajan primero los pasajeros menores.
	 */
	public void finViaje() throws InterruptedException {
		mutex.acquire();
		//TODO
		System.out.println("Fin del viaje!!!");
		//TODO
		if(numMen>0)menor.release();
		else {
			adulto.release();
		}
		
		mutex.release();
	}
}

//CS-Barquero: El barquero no pone en marcha la barca hasta que no se han subido N excursionistas
//CS-Menor: Un menor que está en la barca no puede bajarse hasta que no se ha terminado el paseo.
//CS-Adulto: Un adulto que está en la barca no puede bajarse hasta que no se ha terminado el paseo 
//y no se hayan bajado todos los menores (en caso de que se hubiera subido alguno).
