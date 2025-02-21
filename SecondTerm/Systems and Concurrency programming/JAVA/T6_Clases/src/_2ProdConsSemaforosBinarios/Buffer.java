package _2ProdConsSemaforosBinarios;

import java.util.concurrent.Semaphore;

//Buffer sincronizado utilizando semaforos
public class Buffer {
	private int[] elem; //array de elementos
	//private int nelem;  //numero de elementos en el buffer
	private int p;      //posición donde guardar próximo elemento
	private int c;      //posición donde está el siguiente elemento a consumir
	
	private Semaphore mutex = new Semaphore(1); //semáforo binario para resolver exclusión mutua
	
	//CS_PRODUCTOR
	private int nHuecos;
	private Semaphore hayHuecos;
	//CS_CONSUMIDOR
	private int nelem;
	private Semaphore hayDatos;
	
	public Buffer(int n) {
		if (n <= 0) {
			throw new IllegalArgumentException();
		}
		
		nelem = 0;

		elem = new int[n];
		p = 0;
		c = 0;
		nHuecos=n;
		hayHuecos = new Semaphore(1);
		hayDatos= new Semaphore(0);
	}
	
	public void insertar(int x) throws InterruptedException {
		//Condición de sincronización - si el buffer está lleno espero
		hayHuecos.acquire();
		//COMPLETAR
		mutex.acquire();
		//------SC-----
		elem[p] = x;
		p = (p+1) % elem.length; //incremento circular
		
		//COMPLETAR
		nHuecos--;
		if(nHuecos>0)hayHuecos.release();
		
		nelem++;
		if(nelem==1) hayDatos.release();
		
		System.out.println("Elemento Producido: " + x);
		System.out.print("nelem: " + nelem+ "\n");
		//System.out.print(", hayDatos: " + hayDatos.availablePermits());
		//System.out.println(", hayHuecos: " + hayHuecos.availablePermits());
		mutex.release();
		

	}
	
	public int extraer() throws InterruptedException {
		//Condición de sincronización - si el buffer está vacío espero
		hayDatos.acquire();
		//COMPLETAR
		mutex.acquire();
		//------SC------
		int x = elem[c];
		c = (c+1) % elem.length; //incremento circular
		//COMPLETAR
		nHuecos++;
		if(nHuecos==1) hayHuecos.release();
		nelem--;
		if(nelem>0)hayDatos.release();
		
		System.out.println("Elemento Consumido: " + x);
		System.out.print("nelem: " + nelem+"\n");
		//System.out.print(", hayDatos: " + hayDatos.availablePermits());
		//System.out.println(", hayHuecos: " + hayHuecos.availablePermits());
		
		mutex.release();
		return x;
	}
}
