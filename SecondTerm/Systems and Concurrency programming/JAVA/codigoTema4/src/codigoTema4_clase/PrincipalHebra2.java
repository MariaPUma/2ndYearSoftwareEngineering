package codigoTema4_clase;

public class PrincipalHebra2 {
	public static void main(String[] args) {
		// TODO Auto-generated method stubç
		Thread h1= new Thread(new MiHebra2('A'));
		Thread h2= new Thread(new MiHebra2('B'));
		Thread h3= new Thread(new MiHebra2('C'));
		/*
		h1.run();
		h2.run();
		h3.run();
		*/
		
		h1.start();
		h2.start();
		h3.start();
		
		try {
			h1.join();
			h2.join();
			h3.join();

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("\nLas hebras han terminado\n");
		
	}

}
