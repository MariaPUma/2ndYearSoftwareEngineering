
public class ProblemaInversiones {
	
	public static int numeroInversiones (int []v) {
		return mergeSortInv (v, 0, v.length-1);
	}
	
	private static int mergeSortInv (int []v, int ini, int fin) {
		int suma=0;
		int m=(ini+fin)/2;
		suma+= mergeSortInv(v, ini, m);
		suma+=mergeSortInv(v, m+1, fin);
		suma+=mezclar (v, ini, m, fin);
		
		return suma;
	}
	
	private static int mezclar (int []v, int ini, int m, int fin) {
		int i=ini;	int j= m;
		int k=0;
		int [] b= new int [fin-ini+1];
		int numI =0;
		
		while (i<= m && j<= fin) {
			if(v[i]<=v[j]) {
				b[k]= v[i];
				i++;
			}else {
				b[k] = v[j];
				j++;
				numI+=m-i+1;
			}
			k++;
		}
		
		while (i<= m) {
			b[k]= v[i];
			i++; k++;
		}
		
		while (j<= fin) {
			b[k]= v[j];
			j++; k++;
		}
		
		k=0;
		
		for (int f = 0; f < v.length; f++) {
			v[f]=b[k];
			k++;
		}
		return numI;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
