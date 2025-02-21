package b;

public class Primos {
	private long P1; //Primer primo
	private long P2; //Segundo primo
	private int pos; //Posición ocupada en la lista
	
	
	public Primos(long primo1, long primo2, int pos) {
		// TODO Auto-generated constructor stub
		this.P1= primo1;
		this.P2= primo2;
		this.pos= pos;
	}
	
	public int getPos() {
		return pos;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return pos+":("+P1+","+P2+")";
	}
	
}
