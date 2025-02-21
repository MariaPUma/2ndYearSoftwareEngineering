package a;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Principal {
	public static void crearGui() {
		// TODO Auto-generated method stub
		JFrame ventana = new JFrame("Numeros primos");
		
		Panel panel= new Panel();
		//Voy que tener que poner varios controladores para cada barra
		
		Controlador ctr= new Controlador(panel);
		panel.controlador(ctr);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setContentPane(panel);
		ventana.pack();
		ventana.setVisible(true);
		
		
		

	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					crearGui();
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("La tarea ha sido cancelada");
				}
			}
		});
	}
}
