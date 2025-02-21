package c;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controlador implements ActionListener{
	private WorkerTwin workerTwin;
	private WorkerCousin workerCousin;
	private WorkerSexy workerSexy;
	
	private ControladorBarraTwin controladorBarraTwin;
	private ControladorBarraCousin controladorBarraCousin;
	private ControladorBarraSexy controladorBarraSexy;
	
	private Panel panel;
	//private ControladorBarra ctrB;
	
	public Controlador(Panel p, ControladorBarraTwin controladorBarraTwin2, ControladorBarraCousin controladorBarraCousin2, ControladorBarraSexy controladorBarraSexy2) {
		// TODO Auto-generated constructor stub
		this.panel= p;
		this.controladorBarraTwin= controladorBarraTwin2;
		this.controladorBarraCousin= controladorBarraCousin2;
		this.controladorBarraSexy= controladorBarraSexy2;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals("TWIN")) {
			
			workerTwin= new WorkerTwin(panel,panel.numero1());
			workerTwin.addPropertyChangeListener(controladorBarraTwin);
			workerTwin.execute();
			panel.mensajeTwin("calculando primos twin...");
			
		}else if (e.getActionCommand().equals("COUSIN")) {
			
			workerCousin= new WorkerCousin(panel,panel.numero2());
			workerCousin.addPropertyChangeListener(controladorBarraCousin);
			workerCousin.execute();
			panel.mensajeCousin("calculando primos cousin...");

		}else if (e.getActionCommand().equals("SEXY")) {
			
			workerSexy= new WorkerSexy(panel,panel.numero3());
			workerSexy.addPropertyChangeListener(controladorBarraSexy);
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
