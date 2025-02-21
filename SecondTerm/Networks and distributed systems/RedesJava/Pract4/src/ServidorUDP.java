import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;

public class ServidorUDP {
	public static String extract_text(String texto){
        String resultado = "";
        // Extraer el dígito al inicio del texto
        int lengthThreshold = Character.getNumericValue(texto.charAt(0));

        // Extraer el resto del texto
        texto = texto.substring(1).trim();

        // Dividir el texto en palabras
        String[] words = texto.split("\\s+");

        // Construir la salida con las palabras que tienen una longitud mayor al dígito inicial
        for (String word : words) {
            if (word.length() > lengthThreshold) {
                resultado += word + " ";
            }
        }

        resultado = resultado.trim();
    
        return resultado;
    }
	
	public static void main (String[] args) throws IOException{
		int puerto = 54000;
		DatagramSocket ss = new DatagramSocket(puerto);
		
		while (true) {
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
			
			texto= extract_text(texto);
			DatagramPacket datagram = new DatagramPacket(
					texto.getBytes(StandardCharsets.UTF_8),
					texto.getBytes(StandardCharsets.UTF_8).length,
					pq.getSocketAddress());
			ss.send(datagram);
			System.out.println("Texto enviado con éxito");
			
			
		}
		
	}
}
