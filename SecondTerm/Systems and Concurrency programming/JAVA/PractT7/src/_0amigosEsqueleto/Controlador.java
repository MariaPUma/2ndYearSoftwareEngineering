package _0amigosEsqueleto;

import java.awt.event.*;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;

public class Controlador implements ActionListener{
	private Panel panel;
	private Worker worker;
	
	private ControladorBarra controlBarra;
	
	public Controlador(Panel panel,ControladorBarra controlador){
		this.panel = panel;
		this.controlBarra= controlador;
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("NUMERO")){
			//Calcula los amigos y los muestra en la vista
			panel.activarCancelar(true); //Activamos or si el usuario que desactivar la ejecución
			
			worker= new Worker(panel.numero(), panel); //Creamos un nuevo trabajo
			worker.addPropertyChangeListener(controlBarra);
			worker.execute(); //Lo empezamos a ejecutar
			panel.mensaje("Tarea Creada"); // Indicamos al usuario que esa tarea se creo
			
		}else if (e.getActionCommand().equals("FIN")){
			if (worker!=null){
				//Cancela la tarea
				worker.cancel(true);
				panel.activarCancelar(false);
				panel.mensaje("La tarea fue cancelada");
				
			}
		}
	}
	
	
}
