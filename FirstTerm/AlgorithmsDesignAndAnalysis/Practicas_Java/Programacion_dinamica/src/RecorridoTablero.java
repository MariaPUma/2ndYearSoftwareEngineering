public class RecorridoTablero {
	private int[][] tablero; 
	private int[][] solucion;
	private int fila; //Fila de la casilla de inicio
	private int col;  //Columna de la casilla de inicio
	private int n;   
	private int m;

	public RecorridoTablero(int[][] t, int fila, int col) {
		tablero = t;
		n = tablero.length;
		m = tablero[0].length;
		this.fila = fila;
		this.col = col;
		solucion = null;
	}

	public int resolverMemo() {
		// Creamos la tabla auxiliar
		solucion = new int[n][m]; // -1 representará que la celda está vacía.
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				solucion[i][j] = -1;
			}
		}
		// Rellenamos la tabla desde la casilla indicada
		return resuelve(fila, col);
	}

	private int resuelve(int i, int j) {
		
		//***Completar Implementación****
		int sumaMax;
		if (i==fila) {
			solucion[i][j]=1;
		}
		
		if (i==0) {
			solucion[i][j]=tablero[i][j];
			return tablero[i][j];
			
		}else if (j==m-1) {
			
			sumaMax =resuelve(i-1, j);
			solucion[i][j]=sumaMax+tablero[i][j];
		/*	
		}else if (j==0) {
			sumaMax= Math.max(resuelve(i-1, j), resuelve(i-1, j+1));
			solucion[i][j]=sumaMax+tablero[i][j];
			*/
			
		}else {
			sumaMax= maximo3(resuelve(i-1, j), resuelve(i, j+1), resuelve(i-1, j+1));
			solucion[i][j]=sumaMax+tablero[i][j];
			
			
		}
		
		
		//*************************************************
		return tablero[i][j] + sumaMax;
		
	}

	private int maximo3(int a, int b, int c) {
		int res = a;
		if (b > res) {
			res = b;
		}
		if (c > res) {
			res = c;
		}
		return res;
	}

	public Recorrido reconstruirSol() {
		if (solucion == null) {
			throw new RuntimeException("Se debe resolver el problema primero");
		}
		
		//***Completar Implementación***
		
		Recorrido r = new Recorrido();
		int i=fila;
		int j=col;
		r.add(i, j);
		while (i>0) {
			
			int tabla= solucion[i][j];
			
			if(tabla!=-1) {
				/*
				if(j==0) {
					
					if(solucion[i-1][j]<solucion[i-1][j+1]) {
						j++;
					}
					r.add(i-1, j);
					
				}else */if (j==m-1) {
					/*
					if(solucion[i-1][j]<solucion[i-1][j-1]) {
						j--;
					}*/
					r.add(i-1, j);
					
				}else {
					int max = maximo3(solucion[i-1][j+1], solucion[i][j+1], solucion[i-1][j]);
					
					if(max == solucion[i-1][j+1]) {
						j++;
					}else if (max== solucion[i][j+1]) {
						j++;
						i++;
					}
					r.add(i-1, j);
				}
			}
			i--;

			
		}
		
		
		//******************************
		return r;
	}

	public void imprimeMatrizSolucion() {
		for (int i = 0; i < solucion.length; i++) {
			for (int j = 0; j < solucion[i].length; j++) {
				System.out.print(solucion[i][j] + " ");
			}
			System.out.println(" ");
		}
	}
	
}
