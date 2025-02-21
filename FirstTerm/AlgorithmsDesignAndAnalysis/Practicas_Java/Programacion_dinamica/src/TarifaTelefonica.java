import java.util.Arrays;


public class TarifaTelefonica {

	private int tarifaPlana, permanencia, tarifaMegas;
	private int[] estimacion;
	private int[] pago;// Pago mínimo a realizar durante los meses de i...n

	public TarifaTelefonica(int tp, int p, int tm, int[] est) {
		tarifaPlana = tp;
		permanencia = p;
		tarifaMegas = tm;
		estimacion = est;
		pago = null;
	}

	public int resolverBottomUp() {
		
		//***Completar Implementación***
		
		int n=estimacion.length;
		int k=permanencia;
		int M=tarifaMegas;
		int F=tarifaPlana;
		
		//Para indicar cual de las tarifas vamos a contratar tomaremos una lista de valores binarios 
		//donde el 0 indicará la tarifa de megas y el 1 la plana
		
		pago = new int [n+1];
		pago[n]=0;
		
		for (int i = n-1; i >=0; i--) {
			if(k+i>n) {
				pago[i]=M*estimacion[i]+pago[i+1];
			}else {
				pago[i]=Math.min(k*F+pago[i+k], M*estimacion[i]+pago[i+1]);
			}
		}
		
		return pago[0];
		//******************************
		
	}

	public int[] reconstruirSol() {
		if (pago == null) {
			throw new RuntimeException("Se debe resolver el problema primero");
		}
		
		//***Completar implementación***
		int i=0;
		int [] sol= new int [estimacion.length];
		while (i<estimacion.length-1) {
			if(estimacion[i]*tarifaMegas==pago[i]-pago[i+1]) {
				sol[i]=0;
			}else {
                for (int j = i; j <i+permanencia; j++) {
					sol[j]=1;
				}
				i=i+permanencia-1;			}
			i++;
			
		}
		
		//******************************
		
		return sol;
	}

	public void imprimeVectorSolucion() {
		System.out.println(Arrays.toString(pago));
	}

}