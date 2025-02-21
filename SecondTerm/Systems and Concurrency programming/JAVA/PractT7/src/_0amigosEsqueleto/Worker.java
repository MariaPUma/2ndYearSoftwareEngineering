package _0amigosEsqueleto;

import javax.swing.*;
import java.util.*;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;

/** 
 * @author Monica
 *
 */
public class Worker extends SwingWorker<Void,Amigos>{

	private int n;
	private Panel panel; //Para actualizar la vista desde el Worker
	
	public Worker(int n,Panel panel){
		this.n = n; 
		this.panel = panel;
		panel.limpiaArea();
	}
	
	//Calcula la suma de los divisores de a (sin incluir a)
	private long sumaDivisores(long a){
		long suma = 1;
		int i = 2;
		while (i<a){
			if (a % i == 0) suma+=i;
			i++;
		}
		return suma;
	}
	//---------------------------------------------------------------------
	//									TAREA 1
	//---------------------------------------------------------------------

	/*
	@Override
	protected List<Amigos> doInBackground() throws Exception {
		//Calcula los pares de amigos
		List<Amigos> lista= new ArrayList<Amigos>();
		int numamigos=0;
		long amigo1=1;
		
		while (numamigos<this.n && !this.isCancelled()) {
			long amigo2= sumaDivisores(amigo1);
			
			if(amigo2>= amigo1) {
				if(sumaDivisores(amigo2)== amigo1) {
					numamigos++;
					lista.add(new Amigos(amigo1, amigo2, numamigos));
				}
			}
			amigo1++;
		}
		
		
		return lista;
	}
	public void done() {
		// TODO Auto-generated method stub
		try {
			panel.limpiaArea();
			panel.escribeAmigos(get());
			panel.mensaje("Tarea Finalizada");
		} catch (InterruptedException e) {
			// TODO: handle exception
			System.out.print(e.getMessage());
			e.printStackTrace();
		}catch ( ExecutionException|CancellationException e) {
			// TODO: handle exception
			System.out.println("La tarea ha sido cancelada");
		}
		
	}
	 */
	//---------------------------------------------------------------------
	//								TAREA 2
	//---------------------------------------------------------------------
	@Override
	protected Void doInBackground() throws Exception {
		// TODO Auto-generated method stub
		
		int numamigos=0;
		long amigo1=1;
		this.setProgress(0);
		while (numamigos<this.n && !this.isCancelled()) {
			long amigo2= sumaDivisores(amigo1);
			
			if(amigo2>= amigo1) {
				if(sumaDivisores(amigo2)== amigo1) {
					publish(new Amigos(amigo1, amigo2, numamigos));
					numamigos++;
					this.setProgress((numamigos*100)/n);
					Thread.sleep(1000);
					
					
				}
			}
			amigo1++;
		}
		
		return null;
	}
	@Override
	protected void done() {
		// TODO Auto-generated method stub
		try {
			get();
			panel.mensaje("Tarea Finalizada");
		} catch (InterruptedException e) {
			// TODO: handle exception
			System.out.print(e.getMessage());
			e.printStackTrace();
		}catch ( ExecutionException|CancellationException e) {
			// TODO: handle exception
			System.out.println("La tarea ha sido cancelada");
			panel.activarCancelar(false);
		}
	}
	
	protected void process(List<Amigos> amiguis) {
		// TODO Auto-generated method stub
		
		try {
			panel.escribeAmigos(amiguis);
			panel.mensaje("numeros de amigos calculados");
		} catch (CancellationException e) {
			// TODO: handle exception
			System.out.println("La tarea ha sido cancelada");
		}
	}
	
	
}
