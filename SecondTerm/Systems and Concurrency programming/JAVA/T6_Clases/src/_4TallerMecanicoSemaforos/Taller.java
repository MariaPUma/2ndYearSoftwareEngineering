package _4TallerMecanicoSemaforos;

import java.util.concurrent.Semaphore;

public class Taller {
	
	/*EJERCICIO MECÁNICO
	 * En el siguiente ejercicio debemos de resolver los siguientes problemas
	 * 			*EXCLUSIÓN MUTUA
	 * 			*CONDICIÓN DE SINCRONIZACIÓN
	 * Para ello utilizaremos semaforos binarios
	 */
	
	//EXLUSIÓN MUTUA
	private Semaphore mutex= new Semaphore(1);
	
	
	//CS_CLIENTE 1: Espera su turno para subir el coche
	private Semaphore plataformaLibre; 
	//CS_CLIENTE 2: Espera al administrador para sacar al coche y avisar al siguiente
	private Semaphore cocheListo;
	//CS_MECANICO : Espera a que el cliente le llame y avisa al administrador
	private Semaphore cocheARevisar;
	//CS_ADMINISTRADOR: Espera a que el mecánico avise para luego, al finalizar su tarea, avise al cliente
	private Semaphore cocheFactura;
	
	public Taller() {
		plataformaLibre= new Semaphore(1);
		cocheARevisar= new Semaphore(0);
		cocheFactura= new Semaphore(0);
		cocheListo= new Semaphore(0);

	}
	
	//CS-Mecanico: Espera hasta que el cliente ha subido su coche a la plataforma
	public void esperaParaRevisar() throws InterruptedException{
		//Resolvemos la condición de sincronización
		cocheARevisar.acquire();
		
		
		//SC
		System.out.println("	Hay un coche en la plataforma. El mecanico empieza su trabajo");
		//SC
		//Thread.sleep(1000);
		
		//finRevision();
		
		//cocheFactura.release();
	}
	
	public void finRevision(){
		System.out.println("	Fin de la revision. El mecanico termina su trabajo");
		cocheFactura.release();
	}

	//CS-Administrativo: Espera hasta que el mecanimo le avisa que ha terminado de revisar el coche
	public void esperaParaFacturar() throws InterruptedException{
		cocheFactura.acquire();
		
		System.out.println("	Hay una factura que hacer. El administrativo empieza su trabajo");
		//Thread.sleep(1000);
		
		
	}
	
	public void finFactura(){
		System.out.println("	Fin de la factura. El administrativo termina su trabajo");
		cocheListo.release();

	}	
	
	public void revisarCoche(int id) throws InterruptedException{
		//CS_Cliente1: Espera a que le avisen de que puede entrar al taller
		System.out.println("El cliente " + id + " llega al taller y espera su turno");
		plataformaLibre.acquire();
		
		//SC
		System.out.println("El cliente " + id + " sube el coche a la plataforma");
		//SC
		cocheARevisar.release();
		
		
		
		
		//CS_Cliente2: Espera a que le avisen de que el coche y la factura están listas
		cocheListo.acquire();
		
		System.out.println("El cliente " + id + " recoge su coche");
		
		plataformaLibre.release();
			
	}
}
