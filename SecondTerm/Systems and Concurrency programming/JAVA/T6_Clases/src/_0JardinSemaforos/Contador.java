package _0JardinSemaforos;

import java.util.concurrent.Semaphore;

public class Contador {
    private int cont = 0;
    //La exclusion mutua se resuelve en la clase compartida
    private Semaphore mutex = new Semaphore(1, false); //Semáforo binario 
    
    public void inc() throws InterruptedException{
    	mutex.acquire();
    	//SC
        cont++;
        
        mutex.release();
    }

    public int valor(){
          return cont;
    }
}
