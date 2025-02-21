package b;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controlador implements ActionListener{
	private WorkerTwin workerTwin;
	private WorkerCousin workerCousin;
	private WorkerSexy workerSexy;
	
	
	
	
	private Panel panel;
	//private ControladorBarra ctrB;
	
	public Controlador(Panel p) {
		// TODO Auto-generated constructor stub
		this.panel= p;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals("TWIN")) {
			
			workerTwin= new WorkerTwin(panel,panel.numero1());
			workerTwin.execute();
			panel.mensajeTwin("calculando primos twin...");
			
		}else if (e.getActionCommand().equals("COUSIN")) {
			
			workerCousin= new WorkerCousin(panel,panel.numero2());
			workerCousin.execute();
			panel.mensajeCousin("calculando primos cousin...");

		}else if (e.getActionCommand().equals("SEXY")) {
			
			workerSexy= new WorkerSexy(panel,panel.numero3());
			workerSexy.execute();
			panel.mensajeSexy("calculando primos sexy...");
			
		}else  {
			
			if(workerTwin!=null && !workerTwin.isTerminado()) {
				workerTwin.cancel(true);
				panel.mensajeTwin("Tarea cancelada");
				//panel.limpiaAreaTwin();
			}
			
			if(workerCousin!=null && !workerCousin.isTerminado() ) {
				workerCousin.cancel(true);
				panel.mensajeCousin("Tarea cancelada");
				//panel.limpiaAreaCousin();
			}
			
			if(workerSexy!= null && !workerSexy.isTerminado()) {
				workerSexy.cancel(true);
				panel.mensajeSexy("Tarea cancelada");
				//panel.limpiaAreaSexy();
			}
			
		}
		
	}

}
