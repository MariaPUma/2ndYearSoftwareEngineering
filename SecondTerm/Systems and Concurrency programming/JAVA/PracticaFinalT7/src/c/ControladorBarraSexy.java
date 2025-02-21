package c;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ControladorBarraSexy implements PropertyChangeListener{
	private Panel panel;
	public ControladorBarraSexy(Panel panel) {
		// TODO Auto-generated constructor stub
		this.panel= panel;
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub
		if(evt.getPropertyName().equals("progress")) {
			//Aquí modificamos el valor del progreso
			panel.progreso3((Integer)evt.getNewValue());
		}
		
	}

}
