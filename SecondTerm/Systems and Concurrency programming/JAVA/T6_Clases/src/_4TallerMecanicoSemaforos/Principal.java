package _4TallerMecanicoSemaforos;

public class Principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Taller taller = new Taller();
		
		Mecanico mec = new Mecanico(taller);
		mec.start();
		
		Administrativo adm = new Administrativo(taller);
		adm.start();
		
		Cliente[] cl = new Cliente[5];
		for (int i = 0; i< 5; i++){
			cl[i] = new Cliente(i,taller);
		}
		
		for (int i = 0; i< 5; i++){
			cl[i].start();
		}
		
		try{
			mec.join();
			adm.join();
	    	  //Esperamos a que las 15 hebras finalicen
	    	  for (int i=0; i<5; i++) {
	        	  cl[i].join();
	          }
	      } catch (InterruptedException e){
	          System.out.println("La hebra ha sido interrumpida");
	      }

	}

}
