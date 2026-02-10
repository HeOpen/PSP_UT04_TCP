package ejercicio_gemini;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler extends Thread {

    private final Socket clientSocket;

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try(DataInputStream dis = new DataInputStream(clientSocket.getInputStream());
            DataOutputStream dos = new DataOutputStream(clientSocket.getOutputStream())){

            while(true){
                String operacionCliente =  dis.readUTF();
                double valor = dis.readDouble();

                double returnValue = Double.MIN_VALUE;

                if
            }

        }catch(EOFException e) {
            //nada
        }
        catch(IOException e){
            throw new RuntimeException(e);
        }
    }
}
