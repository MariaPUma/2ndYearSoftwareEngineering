import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.charset.StandardCharsets;

public class ServidorUDP {
	public static void main (String[] args) throws IOException{
		int puerto = 12345;
		DatagramSocket ss = new DatagramSocket(puerto);
		
		byte [] buffer= new byte [1000];

		DatagramPacket pq = new DatagramPacket(
				buffer, 
				buffer.length);
		
		ss.receive(pq);
		String texto = new String(
				pq.getData(),
				pq.getOffset(),
				pq.getLength(), 
				StandardCharsets.UTF_8
				);
		
		System.out.println("Mensaje: "+texto+" (recibido desde "+
		pq.getAddress()+ ": "+pq.getPort()+ ")");
	}
}
