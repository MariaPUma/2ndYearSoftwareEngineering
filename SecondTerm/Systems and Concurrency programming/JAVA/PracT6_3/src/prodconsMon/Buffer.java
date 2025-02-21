package prodconsMon;

import java.util.Arrays;

public class Buffer {
	//Buffer
	private int N = 10; //Tamaño del buffer
	private int[] buffer = new int[N]; //Buffer
	private int[] numCons = new int[N];//Contador del numero de consumidores que faltan por leer cada elemento del buffer
	//Numero de espacios que hay en el buffer
	private int nespacios = N;
	private int ncons; //Numero de consumidores del buffer
	private int[] nelems; //Numero de elementos en el buffer para cada consumidor
	private int[] c; //posicion a partir de la que consume cada consumidor
	private int p=0; //posicion a partir de la que guarda el productor
	
	//n - numero de consumidores
	public Buffer(int n){
		System.out.println("Tamanio del buffer " + N);
		System.out.println("Numero de consumidores " + n + "\n");
		
		ncons= n;
		c= new int[n];
		nelems= new int [n];
		

	}
	
	//synchronized (ex. mutua) + wait/notify (cond. sincronizaci�n)
	public synchronized void almacenar(int elem) throws InterruptedException {
		//CS-Productor: espera mientras el buffer est� lleno
		System.out.println("El productor espera a colocar el elemento "+elem);
		while(nespacios==0) {
			wait();
		}
		
		nespacios--;
		buffer[p]= elem;
		numCons[p]= ncons;
		p++;
		
		if(p==N-1)p=0;
		
		
		for(int i=0;i<nelems.length;i++) {
			nelems[i]++;
		}
		System.out.println("El productor coloca el elemento "+elem+ " en el buffer");

		notifyAll();
		//actualiza todas las variables
		
		
	}
	
	//id- identificador del consumidor
	public synchronized int extrae(int id) throws InterruptedException{
		//CS-Consumidor_id: espera mientras no tenga elementos que consumir
		System.out.println("El consumidor "+id+" espera a consumir");
		while(nelems[id]==0) {
			wait();
		}
		
		System.out.println("El consumidor "+id+" consume el producto "+c[id]);
		nelems[id]--;
		numCons[c[id]]--;
		if(numCons[c[id]]==0) {
			nespacios++;
		}
		c[id]++;
		if(c[id]==N-1)c[id]=0;
		//actualiza todas las variables
		notifyAll();
		return 0;
	}
}
