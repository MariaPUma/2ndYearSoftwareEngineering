package b;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;

import javax.swing.SwingWorker;

public class WorkerSexy extends SwingWorker<Void,Primos>{
	private int distancia;
	private int n;
	private Panel panel;
	private boolean terminado = false;

	public WorkerSexy(Panel panel, int n) {
		// TODO Auto-generated constructor stub
		this.distancia = 6;
		this.n = n;
		this.panel = panel;
	}

	public static long SiguientePrimo(long num) {
		if (num <= 1) {
			return 2;
		}
		

		long x = (num % 2 == 0) ? num + 1 : num;

		while (!EsPrimo(x)) {
			x += 2;
		}

		return x;
	}

	private static boolean EsPrimo(long x) {
		for (int i = 2; i < x; i++) {
	        if (x % i == 0) {
	            return false;
	        }
	    }
	    return true;
	}
	

	@Override
	protected Void doInBackground() throws Exception {
		// TODO Auto-generated method stub
		panel.limpiaAreaSexy();

		List<Primos> lista = new ArrayList<Primos>();
		
		int numprimos=0;
		long primo1= 2;
		
		
		long primo2= SiguientePrimo(primo1);
		while (numprimos<this.n && !this.isCancelled()) {

			if(primo2-primo1 <distancia) {
				primo2= SiguientePrimo(primo2+1);
			}else if (primo2-primo1>distancia) {
				primo1= SiguientePrimo(primo1+1);
				primo2= SiguientePrimo(primo1+1);
			}else if (primo2-primo1 == distancia) {
				publish(new Primos(primo1, primo2, numprimos));
				numprimos++;
				primo1=SiguientePrimo(primo1+1);
				primo2= SiguientePrimo(primo1+1);
			}
			
		}
		return null;
	
	}
	@Override
	protected void done() {
		// TODO Auto-generated method stub
		try {
			
				get();
				panel.mensajeSexy("numeros primos sexy calculados");
				terminado= true;
		} catch (InterruptedException e) {
			// TODO: handle exception
			System.out.println("La tarea ha sido cancelada");
			e.getStackTrace();
		}catch (ExecutionException | CancellationException e) {
			// TODO: handle exception
			System.out.println("La tarea ha sido cancelada");

		}
	}
	@Override
	protected void process(List<Primos> chunks) {
		// TODO Auto-generated method stub
		
		try {
			panel.escribePrimosSexy(chunks);
			//panel.mensajeSexy("calculando primos sexy ...");
		} catch (CancellationException e) {
			// TODO: handle exception
			panel.mensajeSexy("La tarea ha sido cancelada");

		}
		
	}

	public boolean isTerminado() {
		return terminado;
	}

}
