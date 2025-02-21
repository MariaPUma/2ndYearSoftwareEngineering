package Supermercado;

import java.util.concurrent.Semaphore;

public class SupermercadoSemaforos implements Supermercado {

	
	
	private Cajero permanente;
	
	private int numClientes= 0;

	private Semaphore cajero_permanente= new Semaphore(1);
	private Semaphore cliente= new Semaphore(1);
	private Semaphore mutex= new Semaphore(1);
	private boolean abierto= true;
	
	public SupermercadoSemaforos() throws InterruptedException {
		permanente = new Cajero(this, true); //crea el primer cajero, el permanente
		permanente.start();		
		//TODO
	}

	@Override
	public void fin() throws InterruptedException {
		mutex.acquire();
		abierto= false;
		
		if(numClientes>0) {
			cajero_permanente.release();
		}
		
		mutex.release();
	}

	@Override
	public void nuevoCliente(int id) throws InterruptedException {
		if(abierto) {
			
			mutex.acquire();
			int cajerostot= Cajero.numCajeros();
			
			numClientes++;
			
			if(numClientes*2 >cajerostot) {
				Cajero ocasio= new Cajero(this, false);	
				
				ocasio.start();
			}
			
			
			
			if(numClientes>0) cajero_permanente.release();
			
			mutex.release();
			cliente.acquire();
			
		}
	}

	@Override
	public boolean permanenteAtiendeCliente( int id) throws InterruptedException {
		mutex.acquire();
		
		boolean permanente= true;
		if(numClientes==0 && !abierto) {
			mutex.release();
			return false;
		}
		if(numClientes>0) {

			System.out.println("Cajero permanente "+id+": Atiende al cliente");
			numClientes--;
			cliente.release();
		}
		mutex.release();	
		return permanente;//borrar
		
	}
		
	
	@Override
	public boolean ocasionalAtiendeCliente(int id) throws InterruptedException {
		mutex.acquire();
		boolean hayTrabajo= true;
		if(numClientes>0) {
			
			System.out.println("Cajero ocasional "+id+": Atiende al cliente");
			numClientes--;
			
			if(numClientes>0)cliente.release();
			
			
		}else {
			hayTrabajo= false;
		}
		mutex.release();
		return hayTrabajo;//borrar
	}

}
