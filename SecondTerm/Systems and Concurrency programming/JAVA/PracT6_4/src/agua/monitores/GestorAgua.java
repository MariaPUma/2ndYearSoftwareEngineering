package agua.monitores;
import java.util.concurrent.locks.*;

public class GestorAgua {
	private Lock l= new ReentrantLock();
	private Condition atomo= l.newCondition();
	private Condition h = l.newCondition();
	private Condition o= l.newCondition();
	
	private int num_hidrog= 0;
	private boolean un_oxig= false;
	private boolean barrera_atom=false;
	private boolean hidrogeno_completo=false;
	private boolean oxigeno_completo=false;
	

	public void hListo(int id) throws InterruptedException { 
		try {
			l.lock();
			while(hidrogeno_completo) {
				System.out.println("El hidrogeno "+id+" espera para formaarse");

				h.await();
			}
			num_hidrog++;
			System.out.println("El hidrogeno "+id+" esta listo faltan: "+(2-num_hidrog));

			if(num_hidrog==2 && un_oxig) {
				System.out.println();
				hidrogeno_completo=true;
				barrera_atom=true;
				atomo.signalAll();
			}
			while(!barrera_atom) {
				atomo.await();
			}
			num_hidrog--;
			
			if(!un_oxig && num_hidrog==0) {
				o.signalAll();
				h.signalAll();
				hidrogeno_completo=false;
				oxigeno_completo=false;
				barrera_atom=false;
			}
		} finally {
			// TODO: handle finally clause
			l.unlock();
		}
	}
	
	public void oListo(int id) throws InterruptedException { 
		try {
			l.lock();
			while(oxigeno_completo) {
				System.out.println("El oxigeno "+id+" espera para formaarse");

				o.await();
			}
			un_oxig=true;
			System.out.println("El oxigeno "+id+" esta listo");

			
			if(num_hidrog==2 && un_oxig) {
				barrera_atom=true;
				oxigeno_completo=true;
				atomo.signalAll();
			}
			while(!barrera_atom) {
				atomo.await();
			}
			un_oxig=false;
			
			if(!un_oxig && num_hidrog==0) {
				hidrogeno_completo=false;
				oxigeno_completo=false;
				barrera_atom=false;
				o.signalAll();
				h.signalAll();
				
			}
		} finally {
			// TODO: handle finally clause
			l.unlock();
		}
	}
}
