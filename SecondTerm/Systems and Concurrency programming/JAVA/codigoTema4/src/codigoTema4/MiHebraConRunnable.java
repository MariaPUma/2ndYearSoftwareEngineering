package codigoTema4;

public class MiHebraConRunnable {

	public static void main(String[] args) {
		//Definir los objetos MiHebraRunnable
		Thread h1 = new Thread(new MiHebraRunnable('A'));
		Thread h2 = new Thread(new MiHebraRunnable('B'));
		Thread h3 = new Thread(new MiHebraRunnable('C'));
		
		h1.start(); //Creación explícita
		h2.start();
		h3.start();
		
		try {
			h1.join();
			h2.join();
			h3.join();
		}catch(InterruptedException e) {;}
		
		System.out.println("Terminado...");

	}

}

class MiHebraRunnable implements Runnable{ //Representación explícita
	//Atributos
	Character c;
	
	//Constructores
	public MiHebraRunnable(Character c) { //Inicialización
		this.c = c;
	}
	
	//Métodos
	
	//método run()
	public void run() {
		for(int i=0; i<10; i++) {
			System.out.print(c);
		}
	}
}