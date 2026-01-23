package ejercicio02;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import static ejercicio02.Server.PORT;

public class Client {

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Iniciando el cliente...");

        try(Socket socket = new Socket("localhost",PORT);){
            System.out.println("Conectado al servidor...");

            try(
                    BufferedReader serverReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    BufferedWriter serverWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))){

                System.out.println("Streams con el servidor abierto.");

                System.out.println("Introduce un numero, 0 para terminar:");
                String userInput = sc.nextLine().trim();

                while(!userInput.equals("0")){
                    System.out.println("Leido: " + userInput);
                    serverWriter.write(userInput);
                    serverWriter.newLine();
                    serverWriter.flush();

                    System.out.println("Enviado al servidor, espero respuesta...");
                    String serverResponse = serverReader.readLine();
                    System.out.println("El servidor dice: " + serverResponse);

                    System.out.println("Introduce un numero, 0 para terminar:");
                    userInput = sc.nextLine().trim();

                }

            }

        } catch (UnknownHostException e) {
            System.out.println("Error: servidor no encontrado");
        } catch (IOException e) {
            System.out.println("Erro de E/S al conectar con el servidor");
            System.out.println(e.getMessage());
        }
        System.out.println("Terminando el cliente...");
    }
}
