import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

import javax.lang.model.element.Element;

public class ClientTCP {

    public static void main(String[] args) throws IOException {
    	Socket socket = new Socket("127.0.0.1", 12000);
        //Socket socket = new Socket("192.168.132.254", 54322);

        // Obtener flujos
        
        BufferedReader in = new BufferedReader(
                new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8)
            );
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true, StandardCharsets.UTF_8);
        
        BufferedReader Consolain = new BufferedReader(
                new InputStreamReader(System.in)
            );

        // Leer de teclado
        System.out.print("Introduzca por teclado el mensaje a enviar: ");
        String texto = Consolain.readLine();
        
        while(Character.isDigit(texto.charAt(0)))
        {
        	// mientras que lo leído sea correcto
            // enviar mensaje
        	out.println(texto);
        	System.out.print("El mensaje se ha enviado \n");

            // recibir respuesta
        	String mensaje_recibido= null;
        	mensaje_recibido= in.readLine();
        	System.out.print("El mensaje recibido es : ");
        	System.out.println(mensaje_recibido);
        	
        	
            System.out.print("Introduzca por teclado el mensaje a enviar: ");

        	texto = Consolain.readLine();
        }

        
        
        // Enviar FINISH
        System.out.println("Se envía FINISH al servidor");
        out.println("FINISH");
        // recibir OK
        String ok=in.readLine();
        System.out.println("Se recibe el ok del servidor: "+ok);

        in.close();
        out.close();
        Consolain.close();
        socket.close();
        
        System.out.println("Conexión con el servidor terminada :D");
        // Finalizar y liberar recursos
    }
}
