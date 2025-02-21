
public class Fila_Halloween {
	
	public static int encuentraInicioFB(int []fila) {
		return busquedaInicioFB (fila, 0, fila.length-1);
	}
	
	private static int busquedaInicioFB (int[]v, int ini,int fin) {
		int pos=-1;
		
		if (ini==fin) {
			pos=ini;
		}else if (ini==fin-1) {
			
			if(v[ini]<v[fin]) {
				pos=ini;
			}else {
				pos=fin;
			}
		}else {
			int medio= (ini+fin)/2;
			if(v[ini]<v[medio] && v[medio]<v[fin]) {
				pos=ini;
			}else if(v[ini]< v[medio]) {
				pos= busquedaInicioFB(v, medio, fin);
			}else {
				pos= busquedaInicioFB(v, ini, medio);
			}
		}
		
		return pos;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//int []v= {1,2,4,8,9,10};
		int []a= {234,235,267,438,23,63,78};

		
		System.out.print(encuentraInicioFB(a));
	}

}
