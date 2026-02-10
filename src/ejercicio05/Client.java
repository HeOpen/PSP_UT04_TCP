package ejercicio05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        try(Socket socket = new Socket("localhost",Server.PORT);
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);){

            System.out.println("Introduzca un caracter para realizar tu consulta. (H) hora o (F) fecha");
            String userInput = sc.nextLine();

            while(!userInput.equals("FIN")){

                pw.println(userInput);

                String respuesta = br.readLine();
                System.out.printf("El servidor ha dicho: %s\n", respuesta);

                System.out.println("Introduzca un caracter para realizar tu consulta. (H) hora o (F) fecha");
                userInput = sc.nextLine();

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
