package ejercicio02;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static final int PORT = 8000;
    public static void main(String[] args) {

        System.out.println("Server is listening on port "+PORT);

        try(ServerSocket serverSocket = new ServerSocket(PORT);
        Socket clientSocket = serverSocket.accept()){

            System.out.println("Cliente conectado: "+ clientSocket.getPort());

            try(
                    BufferedReader clientReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    BufferedWriter clientWriter = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()))
            ){
                String linea;
                while((linea= clientReader.readLine())!=null){
                    System.out.println("Se ha recibido: "+linea);
                    clientWriter.write(procesarDato(linea));
                    clientWriter.newLine();
                    clientWriter.flush();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
}
    private static String procesarDato(String linea){
        try{
            int numero = Integer.parseInt(linea);
            return String.valueOf(numero*2);
        }catch(NumberFormatException e){
            return "El valor recibido no es un numero entero";
        }
    }
}
