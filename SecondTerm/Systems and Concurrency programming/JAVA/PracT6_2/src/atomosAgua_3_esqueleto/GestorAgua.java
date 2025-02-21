package atomosAgua_3_esqueleto;

import java.util.concurrent.Semaphore;

public class GestorAgua {	
	//CS_HIDROGENO
	private Semaphore hidrogeno_2 = new Semaphore(1);
	int num_h = 0;
	//CS_OXIGENO
	private Semaphore oxigeno = new Semaphore(1);
	int num_o = 0;
	//MUTEX
	private Semaphore mutex = new Semaphore(1); 

	public void hListo(int id) throws InterruptedException {
		//ENTRA UN ATOMO DE HIDROGENO
		hidrogeno_2.acquire();
		mutex.acquire();
		num_h++;
		if(num_h==2)System.out.println("Hay dos átomo de hidrogeno listos");
		System.out.println("\nhidrogeno: "+hidrogeno_2.availablePermits());
		if(num_o==1 && num_h==2) 
		{
			System.out.println("Se forman una molécula de agua");
			num_h=0;
			num_o=0;
			hidrogeno_2.release();
			oxigeno.release();
		}
		
		if(num_h==1)hidrogeno_2.release();
		mutex.release();
	
	}
	
	public void oListo(int id) throws InterruptedException { 
		//ENTRA UN ATOMO DE OXIGENO
		oxigeno.acquire();
		mutex.acquire();
		num_o++;
		
		System.out.println("Hay un átomo de oxígeno listo");
		System.out.println("\noxigeno: "+oxigeno.availablePermits());
		if(num_o==1 && num_h==2) 
		{
			System.out.println("Se forman una molécula de agua");
			num_h=0;
			num_o=0;
			hidrogeno_2.release();
			oxigeno.release();
		}
		mutex.release();
		

	}
}