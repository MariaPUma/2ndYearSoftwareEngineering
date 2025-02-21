//MONITORES
public class Supermercado1 implements Supermercado{

	private int Total;	
	private int nLatas;
	private int cliente;
	private boolean cajero_libre=true;
	private boolean esperando_reponer=false;
	private boolean terminar_cobrar =false;
	private boolean cliente_caja=false;
	
	public Supermercado1(int total) {
		this.Total = total;		//número máximo de latas
		this.nLatas = total; 	//número de latas disponibles
		cliente = -1; 			//id del cliente atendido por el cajero
	}
	
	/* 	Este método lo utiliza un Cliente para coger num latas del lineal.
		En el ejercicio 1, num siempre es 1.
	*/
	public synchronized void comprarLatas(int id,int num) throws InterruptedException{
		//TODO
		while(nLatas==0 || esperando_reponer)
		{
			wait();
		}
		if(nLatas==0 || nLatas-num<0)
		{
			esperando_reponer=true;
			System.out.println("*Cliente "+id+" avisa al reponedor");		
			wait();
			esperando_reponer=false;
		}
		nLatas-=num;
		System.out.println("*Cliente "+id+" compra en el supermercado "+num+" latas. Quedan: "+nLatas);
		
		
		notifyAll();
	}

	/* 	Este método lo utiliza el Reponedor para esperar el aviso de un 
		Cliente por falta de latas.		
	*/
	public synchronized void esperarPeticion() throws InterruptedException{
		//TODO
		while (!esperando_reponer) {
			wait();
		}
		
		System.out.println("-El reponedor ha sido despertado");
		
	}

	/* 	Este método lo utiliza el Reponedor para reponer las latas, 
	    asegurándose que hay N_LATAS en el supermercado.		
	*/
	public synchronized void nuevoSuministro() {
		//TODO
		nLatas=Total;
		System.out.println("-El reponedor pone nuevos suministros");
		notifyAll();
		
	}

	/* 	Este método lo utiliza un Cliente para pagar en la caja.		
	*/
	public synchronized void pagar(int id) throws InterruptedException {
		//TODO
		while( !cajero_libre ) {
			wait();
		}
		cajero_libre=false;
		cliente=id;
		System.out.println("*Cliente "+id+" empieza a pagar");
		cliente_caja=true;
		while (!terminar_cobrar) {
			wait();
		}
		System.out.println("*Cliente "+id+" ha terminado de pagar y se va");
		cliente_caja=false;
		cajero_libre=true;
		terminar_cobrar=false;
		notifyAll();
		
	}

	/* 	Este método lo utiliza el Cajero para cobrar a un Cliente.		
	*/
	public synchronized void cobrar() throws InterruptedException {
		//TODO
		while(!cliente_caja) {
			wait();
		}
		System.out.println("+Cajero cobra al cliente "+cliente);
	
		System.out.println("+Cajero está disponible para otro cliente");
		terminar_cobrar=true;
		notifyAll();
	}
}
