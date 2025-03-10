public class CambioMonedas {
	private int M;
	private int [] d;
	private int [][] tabla; //Mínimo número de monedas. Como la calidad siempre es positiva
							//se usará -1 para representar infinito.
	private int [][] opcion; //k elegido para la primera decisión del subproblema.
	
	public CambioMonedas(int M, int [] d) {
		this.M = M;
		this.d = d;
		tabla = null;
		opcion = null;
	}
	
	/**
	 * Método para rellenar la tabla 
	 */
	public int resolverBottomUp() {
		tabla = new int [d.length][M+1];
		opcion = new int [d.length][M+1]; 
		
		//Rellenamos la tabla de arriba a abajo y de izquierda a derecha
		for (int i = 0; i<d.length; i++) {
			for (int j = 0; j<= M; j++) {
				if (j == 0) {
					tabla[i][j] = 0;
					opcion[i][j] = 0;
				}else if (i == 0) {
					
					//***Completar***
					
					if(j%d[i] !=0) {
						tabla[i][j]=-1;
						opcion[i][j]=-1;
					}else {
						tabla[i][j]=j/d[i];
						opcion[i][j]=1;
					}
					
					//********************
					
				
				}else {//i> 0
					//En tabla[i,j] vamos a ir guardando el mínimo encontrado
					tabla[i][j] = tabla[i-1][j]; //0+M(i-1,j-0)
					opcion[i][j] = 0;
					for(int k = 1; k <= j / d[i]; k++) {
						
						int min=sumaInf(k, tabla[i-1][j-k*d[i]]);
						//***Completar***
						if (menorInf(min, tabla[i][j]) ) {
							
							if(min!=-1) {
								tabla[i][j]=min;
								if(opcion[i-1][j-k*d[i]]>=1) {
									opcion [i][j]=min-opcion[i-1][j-k*d[i]];
								}else {
									opcion[i][j]=min;
								}
								
							}
							
							
							//System.out.print(min+"("+i+","+j+")");
						}
						//*******************
						
					}//for k
				}//else
			}//for j
		}//for i
		return tabla[d.length-1][M];
	}
	/**
	 * Devuelve la suma de dos números, teniendo en cuenta que -1 significa infinito.
	 */
	private int sumaInf(int a, int b) {
		int suma = -1;
		if (a != -1 && b!=-1) {
			suma = a+b;
		}
		return suma;
	}
	/**
	 * Devuelve a < b, teniendo en cuenta que -1 representa infinito
	 */
	private boolean menorInf(int a, int b) {
		boolean res = false;
		if (a!= -1) {
			if (b == -1) {
				res = true;
			}else {//ninguno es infinito
				res = a < b;
			}
		}
		return res;
	}
	
	
	/**
	 * Reconstruimos la solución utilizando la información en la tabla "opcion"
	 * @return una lista s que para cada moneda de tipo i devuelve el número de monedas de ese tipo utilizadas s[i] 
	 */
	public int [] reconstruirSol() {
		if (opcion == null) {
			throw new RuntimeException("Se debe resolver el problema primero");
		}
		
		int []s = new int[d.length]; //Inicialmente 0 monedas de cada tipo
		
		//***Completar la implementación****
		int i= d.length-1;
		int j=M;
		while (i>0) {
			if(tabla[i][j]== tabla[i-1][j]) {
				i=i-1;
			}else {
				s[i]++;
				j=j-d[i];
			}
		}
		s[i] +=tabla[i][j];
		//**********************************
		
		return s;
	}

	
	public void mostrarDatos() {
		System.out.println("Número Mínimo de monedas");
		for (int i = 0; i< tabla.length; i++) {
			for (int j = 0; j< tabla[i].length; j++) {
				System.out.print(tabla[i][j] + "\t");
			}
			System.out.println();
		}
		System.out.println("Opciones");
		for (int i = 0; i< opcion.length; i++) {
			for (int j = 0; j< opcion[i].length; j++) {
				System.out.print(opcion[i][j] + "\t");
			}
			System.out.println();
		}
	}
}
