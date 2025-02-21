public class Barca {
	private int N= 3;
	private boolean terminado_viaje = true;
	private boolean bajando = false;
	private int orilla=1;

	/*
	 * El Pasajero id quiere darse una vuelta en la barca desde la orilla pos
	 */
	public synchronized void subir(int id,int pos) throws InterruptedException{
		//TODO
		while(N==0 || bajando || orilla==pos) {
			wait();
		}
		
		N--;
		System.out.println("El pasajero "+id+" se sube a la barca, en la orilla "+pos+". Quedan libres "+N+" huecos");
		
		notifyAll();
	
	}
	
	/*
	 * Cuando el viaje ha terminado, el Pasajero que esta en la barca se baja
	 */
	public synchronized int bajar(int id) throws InterruptedException{
		//TODO
		
		while(!terminado_viaje || !bajando)wait();
		if(N==0)orilla=(orilla+1)%2;
		N++;
		System.out.println("El pasajero "+id+" se baja de la barca, en la orilla "+(orilla+1)%2+". Quedan libres "+N+" huecos");
		if(N==3) {
			bajando=false;
			System.out.println("La Barca está vacía, preparada para nuevos pasajeros :D");
		}
		notifyAll();
		return orilla;
	}
	/*
	 * El Capitan espera hasta que se suben 3 pasajeros para comenzar el viaje
	 */
	public synchronized void esperoSuban() throws InterruptedException{
		//TODO
		//System.out.println("El CAPITAN espera a los pasajeros para que se suban");
		while(N>0 || bajando) wait();
		terminado_viaje=false;
		System.out.println("Empieza el viaje");
		
	}
	/*
	 * El Capitan indica a los pasajeros que el viaje ha terminado y tienen que bajarse
	 */
	public synchronized void finViaje() throws InterruptedException{
		//TODO
		System.out.println("El viaje termino");
		bajando=true;
		terminado_viaje= true;
		notifyAll();
	}

}
