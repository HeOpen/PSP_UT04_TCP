package ejercicio05;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static final int PORT = 8080;
    private static final int MAX_CLIENT=10;
    private ServerSocket serverSocket;
    private Socket clientSocket;

    public static void main (String[] args){
        try(ServerSocket serverSocket = new ServerSocket(PORT,MAX_CLIENT)){
            while(true){
                //Atiendo a un cliente
                System.out.println("Esperando conexion con cliente");
                Socket clientSocket = serverSocket.accept();
                System.out.println("Cliente conectado. Creando hilo");

                //Abre un hilo para el nuevo cliente
                ClientHandler clientHandler = new ClientHandler(clientSocket);
                clientHandler.start();
                System.out.println("Hilo cliente lanzado");

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
