package esqueleto;

import java.util.concurrent.Semaphore;

public class Cuerda {
	
	private Semaphore babuino_NS= new Semaphore(1);
	private Semaphore babuino_SN= new Semaphore(1);
	
	private Semaphore espera= new Semaphore(1);
	
	private Semaphore mutexNS = new Semaphore(1);
	private Semaphore mutexSN = new Semaphore(1);

	
	private int numBabuNS=0;
	private int numBabuSN=0;

	
	/**
	 * Utilizado por un babuino cuando quiere cruzar el cañón colgándose de la
	 * cuerda en dirección Norte-Sur
	 * Cuando el método termina, el babuino está en la cuerda y deben satisfacerse
	 * las dos condiciones de sincronización
	 * @param id del babuino que entra en la cuerda
	 * @throws InterruptedException
	 */
	public  void entraDireccionNS(int id) throws InterruptedException{
		babuino_NS.acquire();//Toma el turno los babuinos del lado Norte
		mutexNS.acquire();
		numBabuNS++;
		System.out.println("El babuino "+id+" del lado norte está esperando para cruzar");
		if(numBabuNS==1) espera.acquire();
		System.out.println("El babuino "+id+" del lado norte cruza la cuerda");

		if(numBabuNS<3)babuino_NS.release();
		
		mutexNS.release();
	}
	/**
	 * Utilizado por un babuino cuando quiere cruzar el cañón  colgándose de la
	 * cuerda en dirección Norte-Sur
	 * Cuando el método termina, el babuino está en la cuerda y deben satisfacerse
	 * las dos condiciones de sincronización
	 * @param id del babuino que entra en la cuerda
	 * @throws InterruptedException
	 */
	public  void entraDireccionSN(int id) throws InterruptedException{
		babuino_SN.acquire();
		mutexSN.acquire();
		System.out.println("El babuino "+id+" del lado sur está esperando para cruzar");

		numBabuSN++;
		if(numBabuSN==1)espera.acquire();
		System.out.println("El babuino "+id+" del lado sur cruza la cuerda");
		
		if(numBabuSN<3) babuino_SN.release();
		
		mutexSN.release();
		

	}
	/**
	 * Utilizado por un babuino que termina de cruzar por la cuerda en dirección Norte-Sur
	 * @param id del babuino que sale de la cuerda
	 * @throws InterruptedException
	 */
	public  void saleDireccionNS(int id) throws InterruptedException{
		mutexNS.acquire();
		System.out.println("El babuino "+id+" llega al otro las de la cuerda sur");
		numBabuNS--;
		if(numBabuNS==0) {
			espera.release();
			babuino_NS.release();
		}
		mutexNS.release();
	}
	
	/**
	 * Utilizado por un babuino que termina de cruzar por la cuerda en dirección Sur-Norte
	 * @param id del babuino que sale de la cuerda
	 * @throws InterruptedException
	 */
	public  void saleDireccionSN(int id) throws InterruptedException{
		mutexSN.acquire();
		System.out.println("El babuino "+id+" llega al otro las de la cuerda norte");
		numBabuSN--;
		if(numBabuSN==0) {
			espera.release();
			babuino_SN.release();
		}
		mutexSN.release();

		
	}	
		
}
