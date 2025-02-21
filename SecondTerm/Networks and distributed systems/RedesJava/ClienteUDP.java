import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class ClienteUDP {
	public static void main(String[] args) throws IOException {
		DatagramSocket s = new DatagramSocket();
		System.out.println(s.getLocalPort());
		
		//String texto = "3 Esto es un buen codigo para probar a mandar mensajes";
		System.out.println("Introduzca un texto");
		Scanner scanner = new Scanner(System.in);
		String texto = scanner.nextLine();
		
		while (Character.isDigit(texto.charAt(0))) {
			
			
			DatagramPacket dP = new DatagramPacket(
					texto.getBytes(StandardCharsets.UTF_8),
					texto.getBytes(StandardCharsets.UTF_8).length,
					InetAddress.getByName("192.168.135.69"),
					54322);
			s.send(dP);
			System.out.println("Texto enviado con éxito");
			
			byte [] buffer= new byte [1000];

			DatagramPacket pq = new DatagramPacket(
					buffer, 
					buffer.length);
			
			s.receive(pq);
			
			String texto2 = new String(
					pq.getData(),
					pq.getOffset(),
					pq.getLength(), 
					StandardCharsets.UTF_8
					);
			
			System.out.print(texto2);
			
			System.out.println("\nIntroduzca un texto");
			
			texto = scanner.nextLine();
		}
		
		s.close();
	}
	
}
