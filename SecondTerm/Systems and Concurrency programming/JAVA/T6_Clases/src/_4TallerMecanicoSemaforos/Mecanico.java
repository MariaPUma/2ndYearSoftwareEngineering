package _4TallerMecanicoSemaforos;

public class Mecanico extends Thread{
	private Taller taller;
	//private int iter= 3;
	
	public Mecanico(Taller taller){
		this.taller = taller;
	}
	
	public void run(){
		while (true){
			try {
				taller.esperaParaRevisar();
				Thread.sleep(500);//revisando
				taller.finRevision();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
