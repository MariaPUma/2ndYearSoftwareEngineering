package ejercicio2Peterson_esqueleto;

public class Rio implements Runnable{
	private static int MAX_RIO;
	private int id;
	Lago lago ;
	
	public Rio(Lago l,int id,int max) {
		// TODO Auto-generated constructor stub
		this.MAX_RIO= max;
		this.id= id;
		this.lago= l;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		for (int i = 0; i < MAX_RIO; i++) {
			lago.incrementa(id, i++);
		}
		
	}
	
	//COMPLETAR
}