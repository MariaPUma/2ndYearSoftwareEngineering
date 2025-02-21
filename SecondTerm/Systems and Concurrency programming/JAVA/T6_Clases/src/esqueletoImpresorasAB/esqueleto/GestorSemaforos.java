package esqueletoImpresorasAB.esqueleto;

import java.util.concurrent.Semaphore;

/**
 * 
 * @author monica
 *
 * 
 * 
 */
public class GestorSemaforos implements Gestor {

	private int numImpA, numImpB; //numero de impresoras de cada tipo
	
	//Como hay que modificar una variable debemos de resolver la exclusión mútua
	private Semaphore mutexA= new Semaphore(1);
	private Semaphore mutexB= new Semaphore(1);

	//CS_CLIENTE_A: El cliente que quiere accceder a la impresora A
	private Semaphore impresoraA= new Semaphore(1);
	private int LibreImpA;
	//CS_CLIENTE_B: El cliente que quiere acceder a la impresora B
	private Semaphore impresoraB= new Semaphore(1);
	private int LibreImpB;
	//CS_CLIENTE_AB: El cliente le da igual la impresora
	
	
	
	public GestorSemaforos(int numA,int numB) throws IllegalArgumentException{
		if (numA<=0 || numB <=0) throw new IllegalArgumentException();
		
		numImpA = numA;
		numImpB = numB;
		
		LibreImpA= numA;
		LibreImpB= numB;
	}

	public void qImpresoraA(int id) throws InterruptedException{
		//Cliente A solicita impresora
		impresoraA.acquire();//Mira si hay una impresora libre
		
		mutexA.acquire();
		
		LibreImpA--;
		if(LibreImpA!=0)impresoraA.release();
		
		
		mutexA.release();
		
		


	}

	public void qImpresoraB(int id) throws InterruptedException{
		//Cliente B solicita impresora
		impresoraB.acquire();
		
		mutexB.acquire();
		
		LibreImpB--;
		if(LibreImpB!=0)impresoraB.release();
		
		mutexB.release();
		
		
	
	}

	public char qImpresoraAB(int id) throws InterruptedException{
		//Cliente AB solicita impresora
		//No queremos que se quede bloqueado esperando A si hay de tipo B, o al rev�s
		//Tenemos que consultar numImpA y numImpB en exclusi�n mutua
		char valor = 'N'; //valor que nos indica que no se ha encontrado impresora
		
		mutexB.acquire();
		if(LibreImpA>0)
		{
			impresoraA.acquire();
			LibreImpA--;
			valor='A';
		}else if(LibreImpB>0)
		{
			impresoraB.acquire();
			LibreImpB--;
			valor= 'B';
		}
		
		mutexB.release();
		
		
		
		return valor;
	}


	public void dImpresora(char tipo) throws InterruptedException{
		//Cliente deja una impresora, indicando el tipo de la impresora
		System.out.println("El cliente deja una impresora de tipo: "+tipo);
		//mutex.acquire();
		switch(tipo) {
		case 'A': 
			LibreImpA++;
			impresoraA.release();
	
				 break;
		case 'B': 
			LibreImpB++;
			impresoraB.release();

				 break;
		}
		//mutex.release();
		
		
		
	}
}
