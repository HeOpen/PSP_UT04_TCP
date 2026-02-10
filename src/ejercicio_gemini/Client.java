package ejercicio_gemini;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import static ejercicio_gemini.Server.PORT;

public class Client {

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args){

        try(Socket socket = new Socket("localhost", PORT);
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream())){

            System.out.println("Operaciones disponibles: ");
            System.out.println("""
                                ▪ "KM2MI" → Convierte kilómetros a millas.
                                ▪ "MI2KM" → Convierte millas a kilómetros.
                                ▪ "C2F" → Convierte grados Celsius a Fahrenheit.
                                ▪ "F2C" → Convierte grados Fahrenheit a Celsius.
                                ▪ "KG2LB" → Convierte kilogramos a libras.
                                ▪ "LB2KG" → Convierte libras a kilogramos.
                                ▪ "FIN" → Terminar.
                    """);

            System.out.println("Introduce operación: ");
            String operacion = sc.nextLine();
            while(!operacion.equals("FIN")){
                dos.writeUTF(operacion);
                System.out.println("Introduce valor a convertir: ");
                double valor = Double.parseDouble(sc.nextLine());
                dos.writeDouble(valor);
                dos.flush();

                double respuesta = dis.readDouble();
                if(respuesta == Double.MIN_VALUE){
                    System.out.println("El servidor ha respondido con codigo de error");
                }else{
                    System.out.printf("El servidor ha dicho: %.2f\n",respuesta);

                    System.out.println("Operaciones disponibles: ");
                    System.out.println("""
                                ▪ "KM2MI" → Convierte kilómetros a millas.
                                ▪ "MI2KM" → Convierte millas a kilómetros.
                                ▪ "C2F" → Convierte grados Celsius a Fahrenheit.
                                ▪ "F2C" → Convierte grados Fahrenheit a Celsius.
                                ▪ "KG2LB" → Convierte kilogramos a libras.
                                ▪ "LB2KG" → Convierte libras a kilogramos.
                                ▪ "FIN" → Terminar.
                    """);

                    System.out.println("Introduce operación: ");
                    operacion = sc.nextLine();
                }
            }


        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
