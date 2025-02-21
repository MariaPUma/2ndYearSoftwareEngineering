package pajaros_1_esqueleto;
import java.util.concurrent.Semaphore;

public class Nido {
	private final int maxBichos;
	private int numbich;
	
	//CS_PAJARO El pájaro deposita el bicho siempre y cuando haya espacio
	private Semaphore hayHuecoPlato= new Semaphore(1);
	//CS_POLLUELO El polluelo come del plato un bichito siempre que el plato no este vacío
	private Semaphore comerBich = new Semaphore(0);
	//MUTEX controla la variable
	private Semaphore mutex = new Semaphore(1); 
	
	public Nido(int max) {
		maxBichos = max;
		numbich= 0;
	}
	
	public void depositarBicho(int id) throws InterruptedException {
		hayHuecoPlato.acquire(); //El plato se encuentra originalmente vacio
		mutex.acquire();
		numbich++;
		System.out.println("El pájaro "+id+" deja un bichito en el plato: "+numbich);
		System.out.println("\nplato:"+hayHuecoPlato.availablePermits());
		if(numbich!= maxBichos)hayHuecoPlato.release();
		if(numbich==1)comerBich.release();
		mutex.release();
		
		
		
		
	}
	
	public void comerBicho(int id) throws InterruptedException {
		
		comerBich.acquire();
		mutex.acquire();
		numbich--;
		System.out.println("El polluelo "+id+" toma un bichito del plato: "+numbich);
		System.out.println("\npolluelo: "+comerBich.availablePermits());

		if(numbich>0)comerBich.release();
		if(numbich== maxBichos-1) hayHuecoPlato.release();
		mutex.release();
		
		
	}
	
}

