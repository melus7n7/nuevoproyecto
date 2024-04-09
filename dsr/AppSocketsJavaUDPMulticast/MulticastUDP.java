import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;
import java.util.Scanner;

public class MulticastUDP{
    @SuppressWarnings("deprecation")
    public static void main(String[] args){

        try{
            int puerto = 8080;
            InetAddress grupo = InetAddress.getByName("224.0.0.0");
            MulticastSocket socketMulticast = new MulticastSocket(puerto);
            socketMulticast.joinGroup(grupo);

            Scanner scanner = new Scanner(System.in);
            System.out.print("Envíe un mensaje al grupo: ");
            String linea = scanner.nextLine();

            byte[] m = linea.getBytes();
            DatagramPacket mensaje = new DatagramPacket(m, m.length, grupo, puerto);
            socketMulticast.send(mensaje);
            
            byte[] buffer = new byte[1024];
            String lineaEntrada;

            while(true){
                DatagramPacket mensajeEntrada = new DatagramPacket(buffer, buffer.length);
                socketMulticast.receive(mensajeEntrada);

                lineaEntrada = new String(mensajeEntrada.getData(), 0, mensajeEntrada.getLength());
                System.out.println("Recibido: " + lineaEntrada);
                
                if(lineaEntrada.equalsIgnoreCase("Adios")){
                    socketMulticast.leaveGroup(grupo);
                    break;
                }
            }

            scanner.close();
            socketMulticast.close();

        }catch(SocketException ex){
            System.out.println("Socket " + ex.getMessage());
        }catch(IOException ex){
            System.out.println("IO: " + ex.getMessage());
        }
    }
}