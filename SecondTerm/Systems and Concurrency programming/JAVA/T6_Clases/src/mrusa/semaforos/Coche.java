package mrusa.semaforos;

import java.util.concurrent.Semaphore;

public class Coche{
	
	private int asientos; 	//Capacidad del coche
	
	private int numPas = 0;
	private Semaphore mutex = new Semaphore(1); //exclusion mutua
	
	private Semaphore esperaSubir = new Semaphore(1);
	private Semaphore esperaBajar = new Semaphore(0);
	private Semaphore esperaLleno = new Semaphore(0);
	
	
	public Coche(int tam){
		asientos = tam;
	}
	
	public Coche(){
		asientos = 5;
	}
	
	//CS1-Pasajero: espera hasta que se abra la puerta
	//CS2-Pasajero: espera hasta que pueda bajar
	public void darVuelta(int id) throws InterruptedException{
		
		esperaSubir.acquire();
		
		mutex.acquire(); // Toma turno la hebra para que el resto no entre
		numPas++;
		//SC
		System.out.println("+El pasajero " + id + " sube al coche");
		//SC
		if(numPas<asientos) {
			esperaSubir.release();
		}else if(numPas==asientos) {
			esperaLleno.release();
		}
		mutex.release();
		
		esperaBajar.acquire();
		
		mutex.acquire();
		//SC
		System.out.println("+Pasajero " + id + " baja del coche");
		//SC
		numPas--;
		//Suponemos que deben de bajar todos los pasajeros para empezar de nuevo
		if(numPas==asientos) {
			esperaSubir.release(); 
		}else if(numPas>0) {
			esperaBajar.release();
		}
		

		mutex.release();
	}

	//CS-Control: espera hasta que este lleno
	public void esperaLleno() throws InterruptedException{
		//Se espera a que el coche este lleno
		esperaLleno.acquire();
		
		numPas++;
		
		if(numPas!=asientos)esperaLleno.release();
		

	}

	public void finVuelta() throws InterruptedException {
		//Avisa a los pasajeros que deben bajar
		esperaBajar.release();
	}
}