package huerto;

import java.util.concurrent.Semaphore;

public class Huerto { //Recurso
	
	private int N; //distancia máxima entre David y Fran
	
	//CS_PALA:
	
	//MUTEX: 
	private int numHoyos= 0;
	private int numSemi = 0; //No haría falta esta variable
	private Semaphore mutex= new Semaphore(1);
	//CS_JUAN : Juan espera el turno para plantar una semilla
	private Semaphore plantarSemilla= new Semaphore(0);
	//CS_DAVID Y FRAN : se van turnando la pala
	private Semaphore hacerHoyo= new Semaphore(1);
	
	private Semaphore TaparHoyo= new Semaphore(0);
	
	private Semaphore turnoPala = new Semaphore(1);
	
	
	public Huerto(int tam){
		
		N = tam;
	}
	/**
	 * David espera en este método para poder empezar a hacer 
	 * un hoyo. Tiene que  esperar si está alejado N hoyos sin rellenar de Fran y,
	 * opcionalmente, si la pala compartida está siendo utilizada.
	 */
	public  void esperaHacerHoyo() throws InterruptedException{
		hacerHoyo.acquire();
		
		mutex.acquire();
		if(numHoyos<N) {
			hacerHoyo.release();
		}else {
			System.out.println(false);
		}
		turnoPala.acquire();
		//System.out.println("David está haciendo un hoyo");
		
	}
	
	/**
	 * David ha hecho el  hoyo número num. Actualiza el recurso
	 * para informar a Juan y a Fran.
	 * @param num
	 */
	public  void finHacerHoyo(int num) throws InterruptedException{
		mutex.acquire();

		System.out.println("David ha terminado de hacer el hoyo "+num);
		numHoyos++;
		//System.out.println(numHoyos);
		


		if(numHoyos==1)plantarSemilla.release();
		if(numHoyos<N)hacerHoyo.release();
		
		
		
		mutex.release();
		turnoPala.release();
		

	}
	
	/**
	 * Juan espera en este método para poder echar semillas a 
	 * un hoyo. Debe esperar si no hay un hoyo sin semillas.
	 */
	public  void esperaPonerSemilla() throws InterruptedException{
		plantarSemilla.acquire();
		
		
		//System.out.println("Juan planta una semilla");
		

		
	}
	
	/**
	 * Juan ha puesto semillas en el  hoyo número num. 
 	 * Actualiza el recurso para informar Fran.
	 * @param num
	 */
	public  void finPonerSemilla(int num) throws InterruptedException{
		mutex.acquire();
		System.out.println("Juan termina de poner la semilla en el hoyo "+num);
		numSemi++;
		//System.out.println(numHoyos);
		
		if(numHoyos>0)plantarSemilla.release();
		if(numSemi==1)TaparHoyo.release();
		//TaparHoyo.release();
		System.out.println(TaparHoyo.availablePermits());
		mutex.release();
		
	}
	
	/**
	 * Fran espera en este método para poder rellenar 
	 * un hoyo. Espera si no hay un hoyo con semilla no relleno
	 *  y, opcionalmente, si la pala no está libre.
	 */
	public  void esperaEcharTierra() throws InterruptedException{
		TaparHoyo.acquire();
		turnoPala.acquire();
		//System.out.println("Fran empieza a tapar el hoyo "+numHoyos+"con semilla");
		
		
	}
	
	/**
	 * Fran ha rellenado el  hoyo número num. 
 	 * Actualiza el recurso para informar a Juan y a David.
	 * @param num
	 */
	public  void finEcharTierra(int num) throws InterruptedException{
		mutex.acquire();

		System.out.println("Fran termina de rellenar el hoyo "+num);
		numHoyos--;
		numSemi--;
		System.out.println(numSemi);
		
		if(numSemi>0)TaparHoyo.release();

		mutex.release();
		turnoPala.release();
		
		
	}
	


}
/*
 * System.out.print("\n");
		System.out.println(plantarSemilla.availablePermits());
		System.out.println(hacerHoyo.availablePermits());
		System.out.println(TaparHoyo.availablePermits());
		System.out.println(turnoPala.availablePermits());
		
		*
		*
		*
		*
*/
