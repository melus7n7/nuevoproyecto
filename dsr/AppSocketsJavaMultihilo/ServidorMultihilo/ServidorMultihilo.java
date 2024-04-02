package ServidorMultihilo;

import java.io.IOException;
import java.net.ServerSocket;

public class ServidorMultihilo{
    public static void main(String[] args){
        int puerto = 8080;
        try(ServerSocket ss = new ServerSocket(puerto)){
            System.out.println("Servidor escuchando en el puerto: " + puerto + "...");

            int cont = 1;
            while(true){
                HiloHandler cliente = new HiloHandler(ss.accept(), cont);
                System.out.println("El servidor tienen ahora " + cont + " clientes conectados");
                Thread thread1 = new Thread(cliente);
                thread1.start();
                cont++;
            }
        }catch(IOException ex){
            System.out.println(ex);
        }
    }
}