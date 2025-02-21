package mrusa.semaforosV2;

import java.util.concurrent.Semaphore;

public class Coche{
	
	private int asientos; 	//Capacidad del coche
	
	private int numPas = 0;	
	
	private Semaphore mutex= new Semaphore(1);
	
	//CS_PASAJERO 1: Espera hasta que haya un hueco para subir esperaSubir
	private Semaphore esperaSubir;
	private int numHueco;
	//CS_PASAJERO 2:Espera a que termine la vuelta para bajar esperaBajar
	private Semaphore esperaBajar;
	//CS_CONTROL: Espera a que esté lleno para iniciar esperaLleno
	private Semaphore esperaLleno;
	
	
	public Coche(int tam){
		asientos = tam;
		esperaSubir= new Semaphore(1);
		numHueco= tam;
		esperaBajar= new Semaphore(0);
		esperaLleno= new Semaphore(0);
	}
	
	public Coche(){
		//asientos = 5;
		this(5);
	}
	
	//CS1-Pasajero: espera hasta que se abra la puerta
	public void esperaSubir(int id) throws InterruptedException{
		//Resolvemos la condición de sincronización: CS_PASAJERO 1
		//Sube pasajero
		esperaSubir.acquire();
		
		mutex.acquire(); // Toma turno la hebra para que el resto no entre
		numHueco--;
		//SC
		System.out.println("+El pasajero " + id + " sube al coche");
		//SC
		if(numHueco>0) esperaSubir.release();
		if(numHueco==0)esperaLleno.release();
		
		System.out.println("subir:"+esperaSubir.availablePermits());
		System.out.println("bajar:"+esperaBajar.availablePermits());
		System.out.println("lleno:"+esperaLleno.availablePermits());

		//Si se completa los huecos la atracción debe de empezar 
		mutex.release();

	}

	//CS2-Pasajero: espera hasta que pueda bajar
	public void esperaBajar(int id) throws InterruptedException{
		//Resolvemos la condición de sincronización:CS_PASAJERO 2
		esperaBajar.acquire();
		
		mutex.acquire();
		//SC
		System.out.println("+Pasajero " + id + " baja del coche");
		//SC
		
		numHueco++;
		//Suponemos que deben de bajar todos los pasajeros para empezar de nuevo
		if(numHueco==asientos)esperaSubir.release(); 
		if(numHueco>0 && numHueco<asientos)esperaBajar.release();
		System.out.println("subir:"+esperaSubir.availablePermits());
		System.out.println("bajar:"+esperaBajar.availablePermits());
		System.out.println("lleno:"+esperaLleno.availablePermits());

		mutex.release();
		
		
	}
	
	//CS-Control: espera hasta que este lleno
	public void esperaLleno() throws InterruptedException{
		//Se espera a que el coche este lleno
		//Resolvemos la condición de sincronización
		esperaLleno.acquire();
		mutex.acquire();
		
		System.out.println("\n->Atraccion funcionando");
		
		mutex.release();
	}

	public void finVuelta() throws InterruptedException {
		//Avisa a los pasajeros que deben bajar
		
		mutex.acquire();
		System.out.println("\n->La atracción ha terminado\n");
		esperaBajar.release();
		mutex.release();
		
	}
}