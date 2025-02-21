
public class ArraysConRepeticiones {
	
	public static int encuentraElem(int []v) {
		return busquedaElem (v, 0, v.length-1);
	}
	
	private static int busquedaElem (int[]a, int ini, int fin) {
		int pos=-1;
		
		if (ini<= fin) {
			int medio= (ini+fin)/2;
			
			if (a[ini]==a[fin]) {
				return a[ini];
			}else if (medio-1>=0 && a[medio] == a[medio-1]) {
				return a[medio];
			}else if (medio+1<a.length && a[medio] == a[medio+1]) {
				return a[medio];
			}else {
				if(medio== a[medio]) {
					return busquedaElem(a, medio+1, fin);
				}else {
					return busquedaElem(a, ini, medio-1);
				}
			}
			
		}
		return pos;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
