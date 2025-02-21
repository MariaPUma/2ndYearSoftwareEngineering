package t5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;

public class ClientTCP {

    public static void main(String[] args) {
    	Socket socket = new Socket("127.0.0.1", 12000);
        

        // Obtener flujos
        try {
			BufferedReader bffIn= new BufferedReader(new InputStreamReader(socket.getInputStream(),StandardCharsets.UTF_8));
			BufferedReader consola= new BufferedReader(new InputStreamReader(System.in));
			PrintWriter pw= new PrintWriter(socket.getOutputStream(),true,StandardCharsets.UTF_8);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println("Error: No se ha podido iniciar el buffer");
			e.printStackTrace();
		}
        // Leer de teclado
        
        String texto= bffIn;
        
        // mientras que lo leído sea correcto
            // enviar mensaje
            // recibir respuesta
        
        // Enviar FINISH
        // recibir OK

        // Finalizar y liberar recursos
    }
}
