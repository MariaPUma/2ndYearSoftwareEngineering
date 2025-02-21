package rusa_2_esqueleto.copy;
import java.util.concurrent.Semaphore;

public class Coche {
	private int capacidad = 4;
	private int numAsi= 0;
	
	//CS_PASAJERO SUBIR : el pasajero espra su turno para subir a la atracción
	private Semaphore subirPasajero= new Semaphore(1);
	//CS_COCHE COMPLETO: la atracción debe de esperar hasta que todos estén subidos en el coche
	private Semaphore empezarAtraccion = new Semaphore(0);
	//CS_ATRACCION TERMINADA: los pasajero bajan cuando se termina la atracción
	private Semaphore terminaAtraccion = new Semaphore(0);
	
	//MUTEX : para el numero de asientos 
	private Semaphore mutex = new Semaphore(1);
	
	

	public Coche() {
		
	}
	public void quieroSubir(int id) throws InterruptedException {
		subirPasajero.acquire();
		mutex.acquire();
		
		System.out.println("El pasajero "+id+" sube al coche");
		
		numAsi++;
		
		if(numAsi<capacidad)subirPasajero.release();
		if(numAsi==capacidad)empezarAtraccion.release();
		
		mutex.release();
		
	}

	public void quieroBajar(int id) throws InterruptedException {
		terminaAtraccion.acquire();
		mutex.acquire();
		
		System.out.println("El pasajero "+id+" baja del coche");

		numAsi--;
		if(numAsi>0)terminaAtraccion.release();
		if(numAsi==0)subirPasajero.release();
		
		mutex.release();
	}
	
	public void inicioViaje() throws InterruptedException {		
		empezarAtraccion.acquire();
		
		
	}
	
	public void finViaje() throws InterruptedException {
		System.out.println("La atracción ha terminado");
		terminaAtraccion.release();
	}
	
}
