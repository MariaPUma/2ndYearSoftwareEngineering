package ejercicio1_esqueleto;

public class Buffer {
	private int[] elem; //array de elementos
	private int nelem;  //n�mero de elementos en el buffer
	private int p;      //posici�n donde guardar pr�ximo elemento
	private int c;      //posici�n donde est� el siguiente elemento a consumir
	
	private Peterson peterson;
	
	public Buffer(int n) {
		if (n <= 0) {
			throw new IllegalArgumentException();
		}
		
		elem = new int[n];
		p = 0;
		c = 0;
		nelem = 0;
		peterson= new Peterson();
	}
	
	public void insertar(int x) {	
		while(nelem == elem.length) {
			Thread.yield();
		}
		
		
		peterson.entrada_productor();
		//SC
		elem[p] = x;
		System.out.println("Productor: "+x);
		p = (p+1) % elem.length; //incremento circular
		++nelem;
		//SC
		peterson.salida_productor();
	}
	
	public int extraer() {
		while(nelem == 0)
		{
			Thread.yield();
		}
		
		
		peterson.entrada_consumidor();
		//SC
		int x = elem[c];
		System.out.println("	Consumidor: "+x);
		c = (c+1) % elem.length; //incremento circular
		--nelem;
		//SC
		peterson.salida_consumidor();
		
		return x;
	}
}
