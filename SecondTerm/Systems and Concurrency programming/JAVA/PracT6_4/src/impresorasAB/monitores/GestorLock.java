package impresorasAB.monitores;

import java.util.concurrent.locks.*;

/**
 * 
 * @author mmar
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
public class GestorLock implements Gestor {
	
	private int numImpA,numImpB; //numero de impresoras de cada tipo
	private Lock l= new ReentrantLock();
	private Condition impresoraA=l.newCondition();
	private Condition impresoraB= l.newCondition();
	private Condition impresoras= l.newCondition();
	private int A= 0;
	private int B= 0;
	
	public GestorLock(int numA,int numB){
		numImpA = numA;
		numImpB = numB;
	}
	
	public void qImpresoraA(int id) throws InterruptedException {
		try {
			l.lock();
			while(numImpA<=0) {
				System.out.println("La persona "+id+" espera en la cola general");
				
				impresoras.await();
				while(numImpA<=0) {
					A++;
					System.out.println("La persona "+id+" espera en la cola A");
					impresoraA.await();
					
					A--;
				}
				
			}
			numImpA--;
			System.out.println("La impresora "+id+" imprime en la impresora A");
		} finally {
			// TODO: handle finally clause
			l.unlock();
		}
	}
	
	
	public void qImpresoraB(int id) throws InterruptedException {
		try {
			l.lock();
			while(numImpB<=0) {
				System.out.println("La persona "+id+" espera en la cola general");
				
				impresoras.await();
				while(numImpB<=0) {
					B++;
					System.out.println("La persona "+id+" espera en la cola B");
					impresoraB.await();
					
					B--;
				}
				
			}
			numImpB--;
			System.out.println("La persona "+id+" imprime en la impresora B");
		} finally {
			// TODO: handle finally clause
			l.unlock();
		}
	}

	
	public char qImpresoraAB(int id) throws InterruptedException {
		char valor = 'A';
		try {
			l.lock();
			while (numImpA==0 && numImpB==0) {
				System.out.println("La persona "+id+" espera en la cola general");

				impresoras.await();
			}
			
			if(numImpA>0) {
				numImpA--;
				System.out.println("La persona "+id+" eligio A para imprimir");
			}else {
				numImpB--;
				valor='B';
				System.out.println("La persona "+id+" eligio B para imprimir");
			}
			
		} finally {
			// TODO: handle finally clause
			l.unlock();
		}
		return valor;
	}
	
	
	public void dImpresora(char tipo){
		try {
			l.lock();
			
			if(tipo=='A') {
				numImpA++;
				if(A==0) {
					impresoras.signal();
				}else {
					impresoraA.signal();
				}
			}else {
				numImpB++;
				if(B==0) {
					impresoras.signal();
				}else {
					impresoraB.signal();
				}
			}
		} finally {
			// TODO: handle finally clause
			l.unlock();
		}
	}
}
