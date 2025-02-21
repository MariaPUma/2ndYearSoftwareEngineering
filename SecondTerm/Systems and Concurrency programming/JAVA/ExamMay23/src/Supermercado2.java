
public class Supermercado2 implements Supermercado{

	private int Total;
	private int nLatas;
	private int cliente;
	
	private boolean cajero_libre=true;
	private boolean esperando_reponer=false;
	private boolean terminar_cobrar =false;
	private boolean cliente_caja=false;
	
	private boolean terminar_proceso=true;
	
	public Supermercado2(int total) {
		this.Total = total;		//número máximo de latas
		this.nLatas = total; 	//número de latas disponibles
		cliente = -1; 			//id del cliente atendido por el cajero
	}
	
	/* 	Este método lo utiliza un Cliente para coger num latas del lineal.
		En el ejercicio 2, num >=1 && num <= N_LATAS.
	*/
	public synchronized void comprarLatas(int id,int num) throws InterruptedException{
		 //TODO
		while(nLatas==0 || esperando_reponer)
		{
			wait();
		}
		System.out.println("Cliente "+id+" quiere "+num+". Hay "+nLatas);
		while(!terminar_proceso)wait();
		terminar_proceso=false;
		if(nLatas==0 || nLatas-num<0)
		{
			esperando_reponer=true;
			System.out.println("Cliente "+id+" avisa al reponedor");		
			while(esperando_reponer)wait();
			
		}
		nLatas-=num;
				
		System.out.println("Cliente "+id+" compra "+num+" en el supermercado. Queda: "+nLatas);	
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
		
		System.out.println("El reponedor ha sido despertado");
		
	}

	/* 	Este método lo utiliza el Reponedor para reponer las latas
	    en el supermercado, asegurándose que haya N_LATAS.		
	*/
	public synchronized void nuevoSuministro() {
		//TODO
		nLatas= Total;
		System.out.println("El reponedor pone nuevos suministros: "+nLatas);	
		esperando_reponer=false;
		notifyAll();
	}

	/* 	Este método lo utiliza un Cliente para pagar en la caja.		
	*/
	public synchronized void pagar(int id) throws InterruptedException {
		//TODO
		
		while( !cajero_libre) {
			wait();
		}
		//terminar_proceso=false;

		cajero_libre=false;
		cliente=id;
		System.out.println("Cliente "+id+" empieza a pagar");
		cliente_caja=true;
		
		
		//System.out.println("Aqui 1 \n");
		notifyAll();
		
		while (!terminar_cobrar) {
			wait();
		}
		
		//System.out.println("Aqui\n");

		
		System.out.println("Cliente "+id+" ha terminado de pagar y se va");
		cliente_caja=false;
		cajero_libre=true;
		terminar_cobrar=false;
		terminar_proceso=true;
		notifyAll();
	}

	/* 	Este método lo utiliza el Cajero para cobrar a un Cliente.		
	*/
	public synchronized void cobrar() throws InterruptedException {
		//TODO
		while(!cliente_caja) {
			wait();
		}
		//System.out.println("Aqui\n");

		System.out.println("Cajero cobra al cliente "+cliente);
		
		System.out.println("Cajero está disponible para otro cliente");
		terminar_cobrar=true;
		notifyAll();
	}
}

