package codigoTema4;
import java.util.Arrays;
import java.util.Random;

public class EsCero {
	public static void main(String[] args) {
        int[] v = new int[10];  //array de valores

        Random r = new Random();
        //Rellenamos el array
        for (int i = 0; i<v.length;i++){
           v[i]= r.nextInt(2);
        }
        
        System.out.println(Arrays.toString(v));
        EsCeroHebra h1 = new EsCeroHebra(v,0,v.length/2);
        EsCeroHebra h2 = new EsCeroHebra(v,v.length/2,v.length);
        h1.start();
        h2.start();
        
        //FALLA PORQUE EL MAIN NO SE ESPERA
        /*try{
             h1.join(); //Espera bloqueante
             h2.join();
        } catch (InterruptedException ie){;}*/
        
        /*while(h1.isAlive()) {Thread.yield(); } //Espera activa
        while(h2.isAlive()) {Thread.yield(); } //Espera activa*/
        System.out.print("Son todas 0?: ");
        System.out.println(h1.todoCero() && h2.todoCero());
   }
}

class EsCeroHebra extends Thread{
    private int[] v;
    private int inic,fin;
    private boolean escero = true;
    
    public EsCeroHebra(int[] v, int inic, int fin)
    {
        this.inic = inic;
        this.fin = fin;
        this.v= v;
    }
    
    public void run(){
        int i = inic;
        //comprobamos entre inic y fin-1
        while (escero && i < fin){
            escero = v[i] == 0;
            i++;
         }
     }
    
    public boolean todoCero() {
    	return escero;
    }
}
