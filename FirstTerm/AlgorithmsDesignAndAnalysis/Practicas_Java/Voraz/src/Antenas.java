import java.util.ArrayList;
import java.util.List;

public class Antenas {
	
	private Integer[] puntosKm; //Puntos kilometricos de las urbanizaciones ordenados crecientemente.
	private int cobertura;
	private List<Integer> sol ;
		
	public Antenas(Integer[] urbanizaciones, int c) {
		puntosKm = urbanizaciones;
		cobertura=c;
		sol= new ArrayList<Integer>(puntosKm.length);
		
	}
	
	public List<Integer> situarAntenas(){
		int pos =puntosKm[0]+cobertura;

		int k=1;
		sol.add(pos);
		while (pos<puntosKm[puntosKm.length-1] && k<puntosKm.length-1+cobertura ) {
		
			if (k<puntosKm.length && puntosKm[k]>pos+cobertura) {
				pos=puntosKm[k]+cobertura;
				sol.add(pos);
			}	
			
			k++;
			
		}
		
		//****Completar Implementación *****
		
		return sol;
	}
	
	
}
