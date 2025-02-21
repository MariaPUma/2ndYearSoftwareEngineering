package parkingSemaforosBinarios;

import java.util.concurrent.Semaphore;

public class Parking {
    private int plazas;
    private int disponibles;
    private Semaphore cs_espaciosLibres;
    private Semaphore mutex = new Semaphore(1);

    public Parking(int plazas) {
    	this.plazas= plazas;
    	disponibles= plazas;
    	cs_espaciosLibres = new Semaphore(1);
    }
    
    public void entrar(int id) throws InterruptedException {
    	System.out.println("El coche " + id + " quiere aparcar");
    	cs_espaciosLibres.acquire();
    	
    	mutex.acquire();
    	
    	disponibles--;
    	if(disponibles>0) cs_espaciosLibres.release();
    
        System.out.println("	El coche " + id + " ha aparcado");
        mutex.release();
    }
    
    public void salir(int id) throws InterruptedException {
    	
    	mutex.acquire();
    	
    	System.out.println("	El coche " + id + " ha abandonado el parking");
    	disponibles++;
    	if(disponibles==1)cs_espaciosLibres.release();
    	System.out.println(cs_espaciosLibres.availablePermits());
    	mutex.release();
    
    }
}