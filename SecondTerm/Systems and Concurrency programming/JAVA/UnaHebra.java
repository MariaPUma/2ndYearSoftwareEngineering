package codigoTema4;

class UnaHebra extends Thread{
    private int id;

    public UnaHebra(int id){
        this.id = id;
   }
    
   public  void quienSoy(){              
	    //Muestra por pantalla la hebra ejecutándose en ese momento
	    System.out.println(Thread.currentThread());
   }

   //Al mostrar por pantalla un objeto se ejecuta este metodo
   public String toString(){
       return "Hebra " +id;
   }

   public void run(){
        for (int i = 0; i<10; i++){
           quienSoy();
        }
    }
}