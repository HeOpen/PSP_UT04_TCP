package ejercicios01;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import static ejercicios01.Server.PORT;

public class Client {
    public static void main(String[] args){
        try(Socket socket = new Socket("localhost",PORT)) {

            System.out.println(socket.getInetAddress().getHostAddress());
            System.out.println(socket.getPort());
            System.out.println(socket.getLocalPort());
            System.out.println(socket.getRemoteSocketAddress());
            System.out.println(socket.getLocalSocketAddress());

        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
