package Ejercicio2;

public class ImprimirMensaje implements Runnable{

    private String mensaje;

    public ImprimirMensaje(String mensaje){
        this.mensaje = mensaje;
    }

    @Override
    public void run(){
        while(true){
            System.out.print(mensaje + " ");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(new ImprimirMensaje("Hola"));
        Thread t2 = new Thread(new ImprimirMensaje("Mundo"));

        t1.start();
        t2.start();
    }
}
