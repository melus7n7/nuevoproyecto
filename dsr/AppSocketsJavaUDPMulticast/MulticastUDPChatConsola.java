import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;
import java.util.Scanner;

public class MulticastUDPChatConsola implements Runnable{
    private MulticastSocket socketMulticast;
    private InetAddress grupo;
    private int puerto;
    private static Boolean haTerminado;
    private static String nombreUsuario;

    public MulticastUDPChatConsola(MulticastSocket multicastSocket, InetAddress grupo, int puerto){
        this.socketMulticast = multicastSocket;
        this.grupo = grupo;
        this.puerto = puerto;
    }

    @SuppressWarnings("deprecation")
    public static void main(String[] args){
        haTerminado = false;
        try{
            int puerto = 8080;
            InetAddress grupo = InetAddress.getByName("224.0.0.0");
            MulticastSocket socketMulticast = new MulticastSocket(puerto);
            socketMulticast.joinGroup(grupo);

            Scanner scanner = new Scanner(System.in);
            System.out.print("Ingrese su nombre para entrar al chat: ");
            nombreUsuario = scanner.nextLine();

            System.out.println("Puede comenzar a escribir mensajes al grupo: ");

            Thread recibirMensajes = new Thread(new MulticastUDPChatConsola(socketMulticast, grupo, puerto));
            recibirMensajes.start();

            String linea = scanner.nextLine();

            while(!linea.equalsIgnoreCase("Adios")){
                String lineaEnviar = nombreUsuario + ":" + linea;
                byte[] m = lineaEnviar.getBytes();

                DatagramPacket mensaje = new DatagramPacket(m, m.length, grupo, puerto);
                socketMulticast.send(mensaje);

                linea = scanner.nextLine();
            }

            String lineaEnviar = nombreUsuario + " se ha desconectado del chat";
            byte[] m = lineaEnviar.getBytes();

            DatagramPacket mensaje = new DatagramPacket(m, m.length, grupo, puerto);
            socketMulticast.send(mensaje);

            haTerminado = true;
            scanner.close();
            socketMulticast.close();

        }catch(IOException ex){
            System.out.println("Comunicación y sockets cerrados: " + ex.getMessage());
        }
    }

    @Override
    public void run(){
        byte[] buffer = new byte[1024];
        String lineaEntrada;

        try{
            while(!haTerminado){
                DatagramPacket mensajeEntrada = new DatagramPacket(buffer, buffer.length, grupo, puerto);
                socketMulticast.receive(mensajeEntrada);

                lineaEntrada = new String(mensajeEntrada.getData(), 0, mensajeEntrada.getLength());

                if(!lineaEntrada.startsWith(nombreUsuario)){
                    System.out.println(lineaEntrada);
                }
            }
        }catch(SocketException ex){
            System.out.println("Socket " + ex.getMessage());
        }catch(IOException ex){
            System.out.println("IO: " + ex.getMessage());
        }
        
    }
}