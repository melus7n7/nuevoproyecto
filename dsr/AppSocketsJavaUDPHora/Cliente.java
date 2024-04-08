import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Cliente {
    public static void main(String[] args) throws SocketException, UnknownHostException, IOException {
        String mensaje = new String("Dame la hora local.");
        String servidor = new String("localhost");

        int puerto = 8080;
        int espera = 5000;

        DatagramSocket socketUDP = new DatagramSocket();
        InetAddress hostServidor = InetAddress.getByName(servidor);

        DatagramPacket peticion = new DatagramPacket(mensaje.getBytes(), mensaje.getBytes().length, hostServidor, puerto);

        socketUDP.setSoTimeout(espera);
        System.out.println("Esperamos datos en un máximo de " + espera + " milisegundos...");

        socketUDP.send(peticion);

        try{
            byte[] buffer = new byte[1024];
            DatagramPacket respuesta = new DatagramPacket(buffer, buffer.length);

            socketUDP.receive(respuesta);
            String strText = new String(respuesta.getData(), 0, respuesta.getLength());

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime horaServidor = LocalDateTime.parse(strText, formatter);
            System.out.println("Hora del servidor es: " + horaServidor.format(formatter));

            LocalDateTime now = LocalDateTime.now();
            String strFecha = now.format(formatter);
            System.out.println("Hora del cliente es: " + strFecha);

            Duration duration = Duration.between(horaServidor, now);
            double seconds = ((double) duration.toMillis()) / 1000;
            System.out.println("La diferencia entre las dos horas es: " + (seconds) + " milisegundos");

            socketUDP.close();

        }catch(SocketTimeoutException s){
            System.out.println("Tiempo expirado para recibir respuesta " + s.getMessage());
        }

    }
}
