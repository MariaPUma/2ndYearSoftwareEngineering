package paseo_1v;

public interface Barca {
	public void  subeMenor(int id) throws InterruptedException ;
	public void subeAdulto(int id) throws InterruptedException ;
	public void esperoLleno() throws InterruptedException ;
	public void finViaje() throws InterruptedException;
}
