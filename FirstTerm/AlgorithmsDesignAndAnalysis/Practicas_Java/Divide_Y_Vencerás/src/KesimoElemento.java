
public class KesimoElemento {
	
	public static int buscarKesimo(int []v, int k) {
		return quickSortKes (v,k,0,v.length-1);
	}
	
	private static int quickSortKes (int [] v, int k, int ini, int fin) {
		int pos =-1;
		
		if (ini<=fin) {
			int p =partir (v,ini,fin);
			if (p==k) {
				pos=v[p];
			}else if (p<k) {
				pos=quickSortKes(v, k, p+1, fin);
			}else {
				pos=quickSortKes(v, k, ini, p-1);
			}
		}
		
		return pos;
	}
	
	private static int partir (int []v,int ini, int fin) {
		int i=ini+1; int j=fin;
		int pivote=v[ini];
		do {
			while(i<=j && v[i]<=pivote) {i++;}
			while(i<=j && v[j]>pivote) {j--;}
			if (i<j) {intercambiar (v,i,j);}
		} while (i<=j);
		intercambiar(v,ini,j);
		
		return j;
	}
	
	private static void intercambiar (int []v, int i, int j) {
		int aux= v[i];
		v[i]= v[j];
		v[j]=aux;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
