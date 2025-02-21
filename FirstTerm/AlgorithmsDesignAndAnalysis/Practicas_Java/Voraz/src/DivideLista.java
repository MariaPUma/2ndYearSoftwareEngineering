
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class DivideLista {
	
	/**
	 * 
	 * @param datos lista de entrada
	 * @param k     longitud de una de las listas solución
	 * @param a   lista solución 1 (salida)
	 * @param b   lista solución 2 (salida)
	 * @return  la diferencia entre las dos listas
	 * 
	 */
	public static int resolverVoraz(int []datos, int k, List<Integer> a, List<Integer> b) {
			
		//******Completar la implementación*******
		int sumA=0;
		int sumB=0;
		int [] aux=datos;
		Arrays.sort(aux);
		
		int sol;
		
		for (int i = 0; i < datos.length; i++) {
			if(i<datos.length-k) {
				b.add(aux[i]);
				sumA+=aux[i];
			}else {
				a.add(aux[i]);
				sumB+=aux[i];
			}
		}
		if(sumA-sumB <sumB-sumA) {
			sol=sumB-sumA;
		}else {
			sol=sumA-sumB;
		}
		System.out.print(a+"\n"+b);
		return sol;
	}
	
	public static int resolverVoraz2(int []datos, int k, List<Integer> a, List<Integer> b) {
		
		//******Completar la implementación*******
		int sumA=0;
		int sumB=0;
		int [] aux=datos;
		Arrays.sort(aux);
		int i=0;
		int sol;
		System.out.print(datos.length/2);
		while(i<aux.length ) {
			
			if(k>datos.length/2) { // Aquí vemos si el k supera la mitad del array 
				if(i<datos.length-k) {
					b.add(aux[i]);
					sumB+=aux[i];
				}else {
					a.add(aux[i]);
					sumA+=aux[i];
				}
			}else {					//Si no significa que 
				if(i<k) {
					a.add(aux[i]);
					sumA+=aux[i];
				}else {
					b.add(aux[i]);
					sumB+=aux[i];
				}
			}
			
			i++;
		}
		
			sol=sumB-sumA;
		
		System.out.print(a+"\n"+b);
		return sol;
	}
	
	
	public static void main(String[] args) {
		int[] p1 = {1, 19, 0, 7, 18, 2, 8, 16, 10, 1};
		int k = 1;
		List<Integer> a=new ArrayList();
		List<Integer> b=new ArrayList();
		
		System.out.print(resolverVoraz2(p1, k, a, b));
		

	}

	
	
}

/*
 * Failed tests
Test 1: Prueba 1
Test 5: Prueba 5
Test 8: Prueba 8
Test 10: Prueba 10
Test 1: Prueba 1
Incorrect program output
--- Input ---
 K = 2
L= [12, 16, 18, 10, 0]

--- Program output ---
MaxDif= 12

--- Expected output (exact text)---
MaxDif= 36

Test 5: Prueba 5
Incorrect program output
--- Input ---
 K = 1
L= [1, 19, 0, 7, 18, 2, 8, 16, 10, 1]

--- Program output ---
MaxDif= 44

--- Expected output (exact text)---
MaxDif= 82

Test 8: Prueba 8
Incorrect program output
--- Input ---
 K = 7
L= [2, 5, 18, 0, 14, 13, 3, 0, 6, 10, 14, 15, 8, 2, 10, 2, 19, 2, 2, 6]

--- Program output ---
MaxDif= 55

--- Expected output (exact text)---
MaxDif= 131

Test 10: Prueba 10
Incorrect program output
--- Input ---
 K = 9
L= [5, 19, 19, 11, 14, 8, 12, 10, 19, 0, 0, 18, 10, 17, 12, 6, 8, 1, 19, 15]

--- Program output ---
MaxDif= 81

--- Expected output (exact text)---
MaxDif= 127

*/