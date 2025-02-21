package atomosAgua_3_esqueleto.copy;

import java.util.concurrent.Semaphore;

public class GestorAgua {	
	//Barrera para despertar luego en cascada
	private Semaphore espera_O = new Semaphore(1);
	private Semaphore espera_H = new Semaphore(1);
	private Semaphore atom= new Semaphore(0);
	private Semaphore mutex = new Semaphore(1);
	
	private int numH= 0;
	private int numO= 0;
	
	public void hListo(int id) throws InterruptedException {
		//ENTRA UN ATOMO DE HIDROGENO
		espera_H.acquire();
		mutex.acquire();
		
		numH++;
		
		System.out.println("Ha llegado una molécula de hidorgeno");
		
		if(!(numH==2 && numO==1)) {
			if(numH<2) espera_H.release();
			
			mutex.release();
			atom.acquire();
			mutex.acquire();
		}else {
			System.out.println("Se ha formado una molécula");
		}
	
		numH--;
		
		if(numH+numO>0) {
			atom.release();
		}else {
			espera_H.release();
			espera_O.release();	
			
		}
		mutex.release();
		
	}
	
	public void oListo(int id) throws InterruptedException { 
		espera_O.acquire();
		mutex.acquire();
		
		numO++;
		System.out.println("Ha llegado una molécula de oxígeno");
		
		if(!(numH==2 && numO==1)) {
			mutex.release();
			atom.acquire();
			mutex.acquire();
		}else {
			System.out.println("Se ha formado una molécula");
		}
		
		numO--;
		
		if(numH+numO>0) {
			atom.release();
		}else {
			espera_H.release();
			espera_O.release();	
			
		}
		mutex.release();

	}
}