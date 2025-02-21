
import java.util.*;
public class Cliente extends Thread{
	
	private Random r = new Random();
	private int id;
	private Supermercado s;
	private int Total;
	
	public Cliente(Supermercado s, int id,int Total) {
		this.s = s;
		this.id = id;
		this.Total = Total;
	}

	public void run() {
		try {
			Thread.sleep(r.nextInt(1000));
			/*Compra 1 lata en la versión 1 porque
			Total es 1 y un número aleatorio entre [0,1)
			será siempre 0*/
			s.comprarLatas(id,r.nextInt(Total)+1);
			Thread.sleep(r.nextInt(200));
			s.pagar(id);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
