package ejercicio2Peterson_esqueleto;

//Recurso compartido
public class Lago {
	private int nivel;
	private Peterson mutex_rios;
	private Peterson mutex_presas;
	private Peterson mutex_rios_presas;
	
	
	public Lago(){
		nivel = 0;
		this.mutex_presas= new Peterson();
		this.mutex_rios= new Peterson();
		mutex_rios_presas = new Peterson();
		
	}
	
	public int get(){
		return nivel;
	}
	
	//La capacidad del lago se supone indefinida
	public void incrementa(int id, int iter){ 
		mutex_rios.entrada(id);
		mutex_rios_presas.preProt_0();
		
		//SC
		nivel++;
		System.out.println(iter+":Rio " + id + " ha incrementado el nivel: "+nivel);
		//SC
		mutex_rios_presas.postProt_0();
		mutex_rios.salida(id);
	}
	
	
	public void decrementa(int id, int iter){ 
		mutex_presas.entrada(id);
		while(nivel==0) {
			Thread.yield();
		}
		
		mutex_rios_presas.preProt_1();
		
		
		//SC
		nivel--;
		System.out.println(iter+":Presa " + id + " ha decrementado el nivel: " +nivel);
		//SC
		
		mutex_rios_presas.postProt_1();
		mutex_presas.salida(id);

	}
}
