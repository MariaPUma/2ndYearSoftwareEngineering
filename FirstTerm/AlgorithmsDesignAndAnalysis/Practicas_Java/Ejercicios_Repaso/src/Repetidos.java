
public class Repetidos {
	public static int numRepetidos (int []a,int x) {
		return busqrepDer (a,0,a.length-1,x)-busqrepIzq (a,0,a.length-1,x)+1;
	}

	private static int busqrepIzq(int[] a, int ini, int fin, int x) {
		// TODO Auto-generated method stub
		int pos=-1;
		if(ini== fin) {
			pos=ini;
		}else if(ini== fin-1){
			if(a[ini]==x) {
				pos=ini;
			}else {
				pos=fin;
			}
		}else {
			int medio= (ini+fin)/2;
			
			if(a[medio] ==x) {
				if(a[medio]!= a[medio-1]) {
					pos=medio;
				}else {
					pos= busqrepIzq(a, ini, medio-1, x);
				}
			}else if (a[medio]<x){
				pos=busqrepIzq(a, medio+1, fin, x);
			}else {
				pos=busqrepIzq(a, ini, medio-1, x);
			}
		}
		
		
		return pos;
	}

	private static int busqrepDer(int[] a, int ini, int fin, int x) {
		// TODO Auto-generated method stub
		int pos=-1;
		if(ini== fin) {
			pos=ini;
		}else {
			int medio= (ini+fin)/2;
			
			if(a[medio] ==x) {
				if(a[medio]!= a[medio+1]) {
					pos=medio;
				}else {
					pos= busqrepDer(a, ini, medio-1, x);
				}
			}else if (a[medio]<x){
				pos=busqrepDer(a, medio+1, fin, x);
			}else {
				pos=busqrepDer(a, ini, medio-1, x);
			}
		}
		
		
		return pos;	
		}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int []v= {1,2,3,3,3,3,4,5};
		System.out.print(numRepetidos(v, 3));

	}

}
