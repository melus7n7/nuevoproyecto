/*public class Programa1 implements Runnable {
    @Override
    public void run(){
        System.out.println(Thread.currentThread().getName() + "subproceso corriendo...");
    }

    public static void main(String[] args){
        Thread hilo = new Thread(new Programa1(), "Hilo 1");
        hilo.start();
        System.out.println("Este código esta fuera del hilo");
    }
}*/

public class Programa1 extends Thread {
    @Override
    public void run(){
        System.out.println(Thread.currentThread().getName() + "subproceso corriendo...");
    }

    public static void main(String[] args){
        Programa1 hilo = new Programa1();
        hilo.start();
        System.out.println("Este código esta fuera del hilo");
    }
}