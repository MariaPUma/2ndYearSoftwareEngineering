package ejercicio1_esqueleto;


public class Productor implements Runnable{
	private int max;
	private Buffer buffer;
	public Productor(Buffer bf, int max) {
		// TODO Auto-generated constructor
		if(max<=0)
		{
			throw new IllegalArgumentException() ;
		}
		this.buffer=bf;
		this.max= max;
		
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		for (int i = 0; i < max; i++) 
		{
			buffer.insertar(i);
		}
	}
	
	
}
