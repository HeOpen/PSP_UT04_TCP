package ejercicio03;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static final int SERVER_PORT = 8000;

    public static void main(String[] args) {
        System.out.println("Iniciando servidor y esperando conexion cliente...");

        while(true){
            try(ServerSocket serverSocket = new ServerSocket(SERVER_PORT);
                Socket clientSocket = serverSocket.accept();
                DataInputStream inputStream = new DataInputStream(clientSocket.getInputStream());
                DataOutputStream outputStream = new DataOutputStream(clientSocket.getOutputStream())){

                System.out.printf("Conectado con el cliente. Puerto local: %s. Puerto remoto: %s.\n",
                        clientSocket.getLocalPort(),clientSocket.getPort());
                try{
                    while(true){
                        String operacion = inputStream.readUTF();
                        System.out.printf("Operacion recibida: %s\n", operacion);

                        double numeroA = inputStream.readDouble();
                        System.out.printf("Numero A: %f\n", numeroA);

                        double numeroB = inputStream.readDouble();
                        System.out.printf("Numero B: %f\n", numeroB);

                        Thread.sleep(1000);

                        String respuesta = procesarOperacion(operacion, numeroA, numeroB);
                        System.out.println("Respuesta calculada: " + respuesta);
                        outputStream.writeUTF(respuesta);
                        outputStream.flush();
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    private static String procesarOperacion(String operacion, double numeroA, double numeroB){
        if(!((operacion.equals("+")) || (operacion.equals("-")))){
            return "Operacio no valida.";
        }
        double resultado = operacion.equals("+") ? numeroA + numeroB : numeroA - numeroB;
        return String.format("%f %s %f = %f", numeroA, operacion,numeroB ,resultado );
    }
}
