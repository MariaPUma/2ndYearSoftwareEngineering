package codigoTema4;

public class MiHebraConThread {

	public static void main(String[] args) {
		//Crear objetos de clase MiHebra
		Thread h1 = new MiHebra('A');
		Thread h2 = new MiHebra('B');
		Thread h3 = new MiHebra('C');

		h1.start(); //Creacion explicita
		h2.start();
		h3.start();
		
		try {
			h1.join();
			h2.join();
			h3.join();
		}catch(InterruptedException e) {;}
		
		System.out.println("\nTerminado...");

	}

}

class MiHebra extends Thread{ //Representación explícita
	//Atributos
	Character c;
	
	//Constructores
	public MiHebra(Character c) { //Inicialización
		this.c = c;
	}
	
	//Metodos
	
	//metodo run()
	public void run() {
		for(int i=0; i<20; i++) {
			System.out.print(c);
			//Thread.yield();
			//Thread.sleep(10);
		}
	}
}
