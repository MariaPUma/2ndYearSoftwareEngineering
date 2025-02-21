package _0amigosEsqueleto;


import javax.swing.*;

public class Principal {
	public static void crearGUI(){
       	System.out.println("crearGUI() - isEventDispatchThread? "+ SwingUtilities.isEventDispatchThread());
        
		JFrame ventana = new JFrame("Numeros amigos");
		
		Panel panel = new Panel();
		ControladorBarra ctrB= new ControladorBarra(panel);
		Controlador ctr = new Controlador(panel,ctrB);
		panel.controlador(ctr);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setContentPane(panel);
		ventana.pack();
		ventana.setVisible(true);
	}
	
	public static void main(String[] args) {	
		//Cambiamos este código para que la interfaz
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					crearGUI();
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("La tarea ha sido cancelada");
				}
			}
		});
		//la cree la hebra dispatcher
		
	}
	
	

}
