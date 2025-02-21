package ejercicio1_esqueleto;

import java.util.Scanner;
public class Principal {
	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in);){
			int bufsz, max;
			System.out.print("Introduce tama�o del buffer: ");
			bufsz = sc.nextInt();
			System.out.print("Introduce maximo del generador: ");
			max = sc.nextInt();
			
			//COMPLETAR
			Buffer bf = new Buffer(bufsz);
			Thread t1= new Thread(new Productor(bf,max));
			Thread t2 = new Thread(new Consumidor(bf,max));
			
			t1.start();
			t2.start();
			
			try {
				t1.join();
				t2.join();
			} catch (InterruptedException e){
				System.out.println("La hebra ha sido interrumpida");
			}

		} catch (Exception e) {
			e.printStackTrace();
}}}
