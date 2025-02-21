package ejercicio2Peterson_esqueleto;

public class Peterson {
	private volatile int turno;
	private volatile boolean f0=false;
	private volatile boolean f1=false;

	public void preProt_0() {
		turno =1;
		f0= true;
		while(turno==1 && f1) {
			Thread.yield();
		}
	}
	
	public void postProt_0() {
		f0= false;
	}
	
	public void preProt_1() {
		turno =0;
		f1= true;
		while(turno==0 && f0) {
			Thread.yield();
		}
	}
	
	public void postProt_1() {
		f1= false;
	}
	
	public void entrada (int id) {
		if(id==0) {
			preProt_0();
		}else {
			preProt_1();
		}
	}
	
	public void salida (int id) {
		if(id==0) {
			postProt_0();
		}else {
			postProt_1();
		}
	}
	

}
