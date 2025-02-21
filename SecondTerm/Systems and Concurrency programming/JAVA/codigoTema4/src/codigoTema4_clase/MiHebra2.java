package codigoTema4_clase;

public class MiHebra2 implements Runnable{
char c;
	
	public MiHebra2 (char c){
		this.c=c;
	}
	
	public void run () {
		for (int i = 0; i <10; i++) {
			System.out.print(c);
			Thread.yield();
			/*
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
		}
	}
}
