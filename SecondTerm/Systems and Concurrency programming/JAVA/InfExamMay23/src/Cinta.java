import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Cinta {

	private int maletasPrimera, maletasTurista, primeraEsperando;
	private boolean hay_pasajero_en_cinta=false;
	private boolean terminar_coger_maleta_primera=true;
	private List<Integer> pasajeros_primera= new ArrayList<>();
	
	public Cinta() {
		maletasPrimera=0;
		maletasTurista=0;
		primeraEsperando=0;
	}
	//El reponedor pone maleta
	public synchronized void poner(boolean primeraClase) throws InterruptedException {
		if(primeraClase) {
			maletasPrimera++;
		}else {
			maletasTurista++;
		}
		
		System.out.println("Generador pone maleta de primera. maletasP: "+maletasPrimera+" maletasT: "+maletasTurista);
		notifyAll();
	}
	
	//El de primera indica que quiere retirar, se bloquea se no puede
	public synchronized void qRetirarPrimera(int pasajeroId) throws InterruptedException {
		
		pasajeros_primera.add(pasajeroId);
		System.out.println("*** Pas. Primera "+pasajeroId+" quiere maleta ***");
		primeraEsperando++;
		
		while(!terminar_coger_maleta_primera || pasajeros_primera.get(0)!=pasajeroId)wait();
		terminar_coger_maleta_primera=false;
		
		while(hay_pasajero_en_cinta || maletasPrimera==0 ) wait();
		
		hay_pasajero_en_cinta=true;
		primeraEsperando--;
		
		System.out.println("*** Pas. Primera "+pasajeroId+" entra a la cinta ***");

	}

	//El de primera retira, si llega a llamar aquí es porque puede retirar
	public synchronized void fRetirarPrimera(int pasajeroId) throws InterruptedException {
		maletasPrimera--;
		System.out.println("*** Pas. Primera "+pasajeroId+" coge su maleta ***");
		hay_pasajero_en_cinta=false;
		terminar_coger_maleta_primera=true;
		pasajeros_primera.remove(0);
		notifyAll();

	}
	
	//El turista indica que quiere retirar, se bloquea se no puede
	public synchronized void qRetirarTurista(int pasajeroId) throws InterruptedException {
		while(hay_pasajero_en_cinta || maletasTurista==0 || primeraEsperando>0)wait();
		hay_pasajero_en_cinta=true;
		
		System.out.println("Pas. turista "+pasajeroId+" entra a la cinta ");
	}
	
	//El turista retira, si llega a llamar aquí es porque puede retirar
	public synchronized void fRetirarTurista(int pasajeroId) throws InterruptedException {
		maletasTurista--;
		System.out.println("Pas. turista "+pasajeroId+" coge su maleta ");
		hay_pasajero_en_cinta=false;
		notifyAll();

	}

}
