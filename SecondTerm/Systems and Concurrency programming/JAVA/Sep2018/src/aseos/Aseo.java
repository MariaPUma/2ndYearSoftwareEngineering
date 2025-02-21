package aseos;

import java.util.concurrent.Semaphore;

public class Aseo {
	private Semaphore omvre=new Semaphore(1);
	private Semaphore mujer = new Semaphore(1);
	private Semaphore espera= new Semaphore(1);
	
	private Semaphore mutex= new Semaphore(1);
	private int numMuj_cola= 0;
	private int numHom_cola=0;
	
	private int baño=0;
	
	
	/**
	 * El hombre id quiere entrar en el aseo. 
	 * Espera si no es posible, es decir, si hay alguna mujer en ese
	 * momento en el aseo
	 */
	public void llegaHombre(int id) throws InterruptedException{
		omvre.acquire();
		
		numHom_cola++;
		System.out.println("La hombre "+id+" espera en la cola");
		if(numHom_cola==1)espera.acquire();
		

		System.out.println("La hombre "+id+" entra al baño");
		omvre.release();
		
	}
	/**
	 * La mujer id quiere entrar en el aseo. 
	 * Espera si no es posible, es decir, si hay algun hombre en ese
	 * momento en el aseo
	 */
	public void llegaMujer(int id) throws InterruptedException{
		mujer.acquire();
		
		numMuj_cola++;
		System.out.println("La mujer "+id+" espera en la cola");
		if(numMuj_cola==1)espera.acquire();
		System.out.println("La mujer "+id+" entra al baño");
		
		
		mujer.release();

	}
	/**
	 * El hombre id, que estaba en el aseo, sale
	 */
	public void saleHombre(int id)throws InterruptedException{
		omvre.acquire();
		System.out.println("El hombre "+id+" sale del baño");
		numHom_cola--;
		if(numHom_cola==0)espera.release();
		omvre.release();
	}
	
	public void saleMujer(int id)throws InterruptedException{
		mujer.acquire();
		System.out.println("El mujer "+id+" sale del baño");
		numMuj_cola--;
		if(numMuj_cola==0)espera.release();
		mujer.release();
	}
}
