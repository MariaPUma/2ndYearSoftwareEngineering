package rusa_2_esqueleto;
import java.util.concurrent.Semaphore;

public class Coche {
	private int capacidad = 4;
	private int numPas = 0;	
	
	private Semaphore mutex= new Semaphore(1);
	
	//CS_PASAJERO 1: Espera hasta que haya un hueco para subir esperaSubir
	private Semaphore esperaSubir;
	private int numHueco;
	//CS_PASAJERO 2:Espera a que termine la vuelta para bajar esperaBajar
	private Semaphore esperaBajar;
	//CS_CONTROL: Espera a que esté lleno para iniciar esperaLleno
	private Semaphore esperaLleno;
	

	public Coche() {
		esperaSubir= new Semaphore(1);
		numHueco= capacidad;
		esperaBajar= new Semaphore(0);
		esperaLleno= new Semaphore(0);
	}
	public void quieroSubir(int id) throws InterruptedException {
		esperaSubir.acquire();
		
		mutex.acquire(); // Toma turno la hebra para que el resto no entre
		numHueco--;
		//SC
		System.out.println("El pasajero " + id + " sube al coche");
		//SC
		if(numHueco>0) esperaSubir.release();
		if(numHueco==0)esperaLleno.release();
		

		//Si se completa los huecos la atracción debe de empezar 
		mutex.release();
	}

	public void quieroBajar(int id) throws InterruptedException {
esperaBajar.acquire();
		
		mutex.acquire();
		//SC
		System.out.println("El pasajero " + id + " baja del coche");
		//SC
		
		numHueco++;
		//Suponemos que deben de bajar todos los pasajeros para empezar de nuevo
		if(numHueco==capacidad)esperaSubir.release(); 
		if(numHueco>0 && numHueco<capacidad)esperaBajar.release();
		
		mutex.release();
	}
	
	public void inicioViaje() throws InterruptedException {		
		esperaLleno.acquire();
		//mutex.acquire();
		
		//System.out.println("\n->Atraccion funcionando");
		
		//mutex.release();
	}
	
	public void finViaje() throws InterruptedException {							
		//mutex.acquire();
		System.out.println("La atracción ha terminado\n");
		esperaBajar.release();
		//mutex.release();
	}
	
}
