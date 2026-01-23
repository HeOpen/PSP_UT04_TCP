package ejercicio03;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Iniciando cliente y conectando a servidor");

        try(Socket socket = new Socket("localhost",Server.SERVER_PORT);
            DataInputStream inputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream())) {

            System.out.printf("Conectado con el cliente. Puerto local %s. Puerto remoto: %s.\n"
                    ,socket.getLocalPort(),socket.getPort());

            System.out.println("Abriendo streams");

            System.out.println("Dime la operacion (+  -), SALIR para terminar: ");
            String operacion = sc.nextLine();
            while(!operacion.equals("SALIR")){
                System.out.println("Dime el primer numero (doble)");
                double numeroA = Double.parseDouble(sc.nextLine());

                System.out.println("Dime el segundo numero (doble)");
                double numeroB = Double.parseDouble(sc.nextLine());

                outputStream.writeUTF(operacion);
                outputStream.writeDouble(numeroA);
                outputStream.writeDouble(numeroB);
                outputStream.flush();

                String respuesta = inputStream.readUTF();

                System.out.printf("El servidor ha respondido: %s\n", respuesta);

                System.out.println("Dime la operacion (+  -), SALIR para terminar: ");
                operacion = sc.nextLine();
            }
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
