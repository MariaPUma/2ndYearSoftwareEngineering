package _1Impresoras;

import java.util.concurrent.Semaphore;

public class Gestor {
	private int numImpresoras;
	
	public Gestor(int N) throws IllegalArgumentException{
		if (N<=0) throw new IllegalArgumentException();
		
		this.numImpresoras = N;
		System.out.println("Hay " + N + " impresoras libres");
	}
	
	public void cogeImpresora(int id) throws InterruptedException{

		numImpresoras--;

	}
	
	public void sueltaImpresora(int id) throws InterruptedException{
	
		numImpresoras++;

	}
}
