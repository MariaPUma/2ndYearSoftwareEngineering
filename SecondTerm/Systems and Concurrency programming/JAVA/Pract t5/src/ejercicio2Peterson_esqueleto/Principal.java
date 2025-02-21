package ejercicio2Peterson_esqueleto;

public class Principal {
	public static void main(String[] args){
		final int MAX = 10;
		Lago lago = new Lago();     //Recurso compartido

		//COMPLETAR
		
		Thread r1= new Thread(new Rio(lago, 0, MAX));
		Thread r2= new Thread(new Rio(lago, 1, MAX));
		
		Thread p1= new Thread(new Presa(lago, 0, MAX));
		Thread p2= new Thread(new Presa(lago, 1, MAX));
		
		r1.start();
		r2.start();
		
		p1.start();
		p2.start();
		
		try {
			r1.join();
			r2.join();
			p1.join();
			p2.join();
			
		} catch (InterruptedException e) {;}
	
		//Mostramos el nivel final del lago
		System.out.println("Nivel Final Lago: "+ lago.get());
	}
}
