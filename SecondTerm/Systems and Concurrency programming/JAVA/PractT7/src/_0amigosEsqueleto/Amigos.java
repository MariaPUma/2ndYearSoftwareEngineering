package _0amigosEsqueleto;

public class Amigos {
	private long A1; //Amigo 1
	private long A2; //Amigo 2
	private int pos;
	
	public Amigos(long amigo1, long amigo2, int pos) {
		// TODO Auto-generated constructor stub
		this.A1= amigo1;
		this.A2= amigo2;
		this.pos=pos;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return pos+":("+this.A1+","+this.A2+")";
	}
	
	public int getPos() {
		return pos;
	}
	
	

}
