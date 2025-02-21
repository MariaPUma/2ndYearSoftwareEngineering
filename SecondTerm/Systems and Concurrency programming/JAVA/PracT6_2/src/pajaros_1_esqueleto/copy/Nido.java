package pajaros_1_esqueleto.copy;
import java.util.concurrent.Semaphore;

public class Nido {
	private final int maxBichos;
	private int numbich;
	
	
	public Nido(int max) {
		maxBichos = max;
		numbich= 0;
	}
	
	//CS_PAJARO: Deja un bicho siempre y cuando el nido no este lleno
	private Semaphore pajaro = new Semaphore(1);
	//CS_POLLUELO: Come siempre que haya un bicho
	private Semaphore polluelo = new Semaphore(0);
	//MUTEX ya que hay múltiples hebras que acceden a la variable
	private Semaphore mutex =new Semaphore(1);
	
	
	
	public void depositarBicho(int id) throws InterruptedException {
		pajaro.acquire();
		mutex.acquire();
		
		System.out.println("El pájaro "+id+" deja un bichito en el plato: "+numbich);
		numbich++;
		
		if(numbich==1)polluelo.release();
		
		if(numbich<maxBichos)pajaro.release();
		mutex.release();
		
		
	}
	
	public void comerBicho(int id) throws InterruptedException {
		
		polluelo.acquire();
		mutex.acquire();
		
		System.out.println("El polluelo "+id+" toma un bichito del plato: "+numbich);
		numbich--;
		
		if(numbich>0)polluelo.release();
		if(numbich== maxBichos-1) pajaro.release();
		mutex.release();
	}
	
}

