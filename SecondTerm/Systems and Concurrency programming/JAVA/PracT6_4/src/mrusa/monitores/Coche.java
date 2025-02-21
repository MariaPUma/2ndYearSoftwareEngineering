package mrusa.monitores;
import java.util.concurrent.locks.*;
public class Coche{
	
	private int asientos; 	//Capacidad del coche
	private Lock l= new ReentrantLock();
	private Condition esperarSubir= l.newCondition();
	private Condition empezarAtrac= l.newCondition();
	private Condition esperarBajar= l.newCondition();
	private boolean subircoche=true;
	private boolean cocheLleno=false;
	private boolean bajarcoche= false;
	
	private int num_pasaj=0;
	
	public Coche(int tam){
		asientos = tam;
	}
	
	public Coche(){
		asientos = 5;
	}
	
	public void darVuelta(int id) throws InterruptedException {
		//System.out.println("El pasajero "+id+" espera a subir");
		
		try {
			l.lock();
			
			while(!subircoche)esperarSubir.await();
			num_pasaj++;
			System.out.println("El pasajero "+id+" sube a la atraccion" );

			if(num_pasaj==asientos) {
				subircoche=false;
				cocheLleno=true;
				empezarAtrac.signal();
			}
			
			while(!bajarcoche)esperarBajar.await();
			
			System.out.println("El pasajero "+id+" baja del coche");
			
			num_pasaj--;
			
			if(num_pasaj==0) {
				subircoche=true;
				bajarcoche=false;
				
				esperarSubir.signal();
			}
			if(num_pasaj>0)esperarBajar.signal();
	
		} finally {
			// TODO: handle finally clause
			l.unlock();
		}
		
	}
	
	public void esperaLleno() throws InterruptedException {
		try {
			l.lock();
			
			while(!cocheLleno)empezarAtrac.await();
			System.out.println("La ATRACCIÓN empieza");
			
		} finally {
			// TODO: handle finally clause
			l.unlock();
		}
	}
	
	public void finVuelta() {
		try {
			l.lock();
			System.out.println("La ATRACCIÓN ha terminado");
			
			bajarcoche=true;
			subircoche=false;
			cocheLleno=false;
			esperarBajar.signal();
			
		} finally {
			// TODO: handle finally clause
			l.unlock();
		}
	}
}