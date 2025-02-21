package ejercicio2Peterson_esqueleto;

public class Presa implements Runnable{
	private static int MAX_PRESA;
	private Lago lago;
	private int id;
	
	//COMPLETAR
	public Presa(Lago l,int id,int max) {
		// TODO Auto-generated constructor stub
		this.lago= l;
		this.id= id;
		this.MAX_PRESA= max;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		for (int i = 0; i < MAX_PRESA; i++) {
			lago.decrementa(id, i++);
		}
		
	}

	
}
