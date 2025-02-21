package impresorasAB;

import java.util.concurrent.Semaphore;

/**
 * 
 * @author monica
 * Soluci�n al problema del gestor de impresoras utilizando
 * condiciones. La condici�n colaGeneral es utilizada por todas las
 * hebras cuando piden una impresora y no hay del tipo que piden.
 *
 * Las colas colaA y colaB son utilizadas en exclusiva por las hebras de tipo
 * A y B, respectivamente.
 * 
 * 
 * 
 */
public class GestorSemaforos implements Gestor {

	private int numImpA,numImpB; //numero de impresoras de cada tipo
	
	//CS_IMPRESORA_A: el cliente espera a utilizar la impresora
	private Semaphore impresora_A= new Semaphore(1);
	private Semaphore impresora_B= new Semaphore(1);
	
	private Semaphore mutex= new Semaphore(1);
	
	private int imprA_num=0;
	private int imprB_num=0;

	public GestorSemaforos(int numA,int numB){
		numImpA = numA;
		numImpB = numB;
	}

	public void qImpresoraA(int id) throws InterruptedException{
		//Cliente A solicita impresora
		impresora_A.acquire();
		mutex.acquire();
		System.out.println("El cliente "+id+" utiliza la impresora A");
		
		imprA_num++;
		
		if(imprA_num>numImpA)impresora_A.release();
		mutex.release();
		
	}


	public void qImpresoraB(int id) throws InterruptedException{
		//Cliente B solicita impresora
	}


	public char qImpresoraAB(int id) throws InterruptedException{
		//Cliente AB solicita impresora
		char valor='A';
		mutex.acquire();
		
		mutex.release();
		
		return valor;
	}


	public void dImpresora(char tipo) throws InterruptedException{
		//Cliente deja una impresora, indicando el tipo de la impresora
		
		System.out.println("El cliente deja la impresora "+tipo);
		mutex.acquire();
		switch (tipo) {
		case 'A': {
			imprA_num--;
			break;
		}
		default:
			imprB_num--;
		}
		mutex.release();
		
	}
}
