package ejercicio2Lamport_esqueleto;

public class Lamport {
	private int turno [];
	private boolean pidiendoTurno [];
	
	public Lamport(int N) {
		// TODO Auto-generated constructor stub
		
		turno = new int [N];
		pidiendoTurno= new boolean [N];
		
	}
	
	public void cogeTurno(int id) {
		// TODO Auto-generated method stub
		pidiendoTurno[id]= true;
		int max=0;
		
		for (int i = 0; i < turno.length; i++) {
			if(max<turno[i])
				max= turno[i];
		}
		
		turno[id]= max+1;
		pidiendoTurno[id]= false;
		

	}
	
	private boolean meToca(int id, int i) {
		if(turno[i]>0 && turno[i]<turno[id]) {
			return false;
		}else if(turno[i]==turno[id] && i<id){
			return false;
		}else {
			return true;
			
		}
	} 
	
	public void esperoTurno(int id) {
		for(int i=0;i<turno.length;i++) {
			while (pidiendoTurno[i]==true) {
				Thread.yield();
			}
			while (!meToca(id, i)) {
				Thread.yield();
				
			}
		}
		System.out.println("Turno: " + java.util.Arrays.toString(turno));
	}

	public void sale(int id) {
		turno[id] = 0;
	}
}
