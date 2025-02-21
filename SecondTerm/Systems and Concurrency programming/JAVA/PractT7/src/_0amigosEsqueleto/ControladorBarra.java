package _0amigosEsqueleto;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;


public class ControladorBarra implements PropertyChangeListener{
	private Panel panel;
	public ControladorBarra(Panel panel) {
		// TODO Auto-generated constructor stub
		this.panel= panel;
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub
		
		if(evt.getPropertyName().equals("progress")) {
			//Aquí modificamos el valor del progreso
			panel.progreso((Integer)evt.getNewValue());
		}
	}

}
