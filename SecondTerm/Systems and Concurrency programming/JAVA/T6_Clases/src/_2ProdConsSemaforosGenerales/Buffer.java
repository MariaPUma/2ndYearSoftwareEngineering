package _2ProdConsSemaforosGenerales;

import java.util.concurrent.Semaphore;

//Buffer sincronizado utilizando sem�foros
public class Buffer {
	private int[] elem; //array de elementos
	private int nelem;  //n�mero de elementos en el buffer
	private int p;      //posici�n donde guardar pr�ximo elemento
	private int c;      //posici�n donde est� el siguiente elemento a consumir
	
	//CLASE 17/04
	private Semaphore mutex= new Semaphore(1);
	private Semaphore cs_prod; //indica si ha huecos
	private Semaphore cs_cons= new Semaphore(0);//indica si no hay huecos
		
	public Buffer(int n) {
		if (n <= 0) {
			throw new IllegalArgumentException();
		}
						
		elem = new int[n];
		p = 0;
		c = 0;
		nelem = 0;
		cs_prod=  new Semaphore(n);
	}
	
	public void insertar(int x) throws InterruptedException {
		//Condicion de sincronizacion - si el buffer esta lleno espero
		
		cs_prod.acquire();
		mutex.acquire();
		//------SC-----
		elem[p] = x;
		p = (p+1) % elem.length; //incremento circular
		++nelem;
		System.out.println("Elemento Producido: " + x);
		//------FIN SC-----
		mutex.release();
		cs_cons.release();
		

	}
	
	/* MUY IMPORTANTE EL ORDEN DE LLAMADA A LOS SEM�FOROS
	 *   - Probad a intercambiar los dos acquire() y ver qu� ocurre
	 *   
	 */
	public int extraer() throws InterruptedException {
		//Condicion de sincronizacion - si el buffer esta vacio espero
		
		cs_cons.acquire();
		mutex.acquire();
		//------SC------
		int x = elem[c];
		c = (c+1) % elem.length; //incremento circular
		--nelem;
		System.out.println("Elemento Consumido: " + x);
		//------FIN SC----
		mutex.acquire();
		cs_prod.release();
		
		return x;
	}
}
