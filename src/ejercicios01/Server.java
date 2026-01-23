package ejercicios01;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static final int PORT = 8000;

    public static void main(String[] args){
        try(ServerSocket serverSocket = new ServerSocket(PORT)){
            System.out.println("Antes de accept");
            // serverSocket.accept();
            Socket clientSocket = serverSocket.accept();
            System.out.println("Despues de accept");
            System.out.printf("IP Local: %s",serverSocket.getInetAddress().getHostAddress());
            System.out.printf("Porta: %d",serverSocket.getLocalPort());
            System.out.printf("\nIP Cliente: %s",clientSocket.getInetAddress().getHostAddress());
            System.out.printf("Porta: %d",clientSocket.getPort());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}