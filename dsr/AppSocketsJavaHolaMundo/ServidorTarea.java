import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServidorTarea {
    public static void main(String[] args) {
        int puerto = 8080;
        try{
            ServerSocket ss = new ServerSocket(puerto);
            System.out.println("Servidor escuchando el puerto: " + puerto + "...");
    
            Socket ch = ss.accept();
    
            PrintWriter out = new PrintWriter(ch.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(ch.getInputStream()));
    
            out.println("Hola");
            out.println("Mundo");
            out.println("desde el servidor!");

            Scanner scanner = new Scanner(System.in);
            String lineaEscrita = "";
            do{
                lineaEscrita = scanner.nextLine();
                out.println(lineaEscrita);
            }while(!lineaEscrita.equalsIgnoreCase("Adios"));

            
            System.out.println("Cliente: " + in.readLine());
            
            scanner.close();
            out.close();
            in.close();
            ch.close();
            ss.close();
    
        }catch(IOException ex){
            System.out.println(ex);
        }
    }
}
