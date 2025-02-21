package ejerc1;

public class Mihebra extends Thread{
		private char letra;
		private int n;
		public Mihebra(char x,int veces) {
			// TODO Auto-generated constructor stub
			letra = x;
			n=veces;
		}
		
		public void run() {
			
			for (int i=0;i<n;i++) {
				System.out.print(letra);
				Thread.yield();
			}
			
		}
}
