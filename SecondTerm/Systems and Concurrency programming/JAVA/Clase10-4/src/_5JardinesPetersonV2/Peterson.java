package _5JardinesPetersonV2;

public class Peterson{
    private volatile int turno = 0;
    private volatile boolean f0 = false;
    private volatile boolean f1 = false;
      
    public void entrarP1(){
        f0 = true;
        turno = 1;
        while (f1 && turno==1) Thread.yield();
    }
    
    public void salirP1(){
        f0 = false;
    }
    
    public void entrarP2(){
        f1 = true;
        turno = 0;
        while (f0 && turno==0 ) Thread.yield();
    }
    
    public void salirP2(){
        f1 = false;
    }
    
    public void entrar(int id){
    	if (id == 1){
    		entrarP1();
    	}else if (id == 2){
    		entrarP2();
    	}
    }
    
    public void salir(int id){
    	if (id == 1){
    		salirP1();
    	}else if (id == 2){
    		salirP2();
    	}
    }
}
