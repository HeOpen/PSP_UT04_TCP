package ejercicio_gemini;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static final int PORT = 8080;
    private static final int MAX_CLIENTS= 10;
    //Pq crear los sockets fuera del main???
    private ServerSocket serverSocket;
    private Socket clientSocket;

    public static void main(String[] args) {

        try(ServerSocket serverSocket = new ServerSocket(PORT)){
            System.out.println("Iniciando server");
            while(true){
                System.out.println("Esperando conexion c/ cliente...\n");
                Socket clientSocket = serverSocket.accept();
                System.out.println("Cliente conectado. Creando hilo...");

                ClientHandler clientHandler = new ClientHandler(clientSocket);
                clientHandler.start();
                System.out.println("Hilo para el nuevo cliente lanzado");

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
