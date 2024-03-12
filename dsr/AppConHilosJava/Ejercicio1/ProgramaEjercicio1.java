package Ejercicio1;

public class ProgramaEjercicio1 {
    public static void main(String[] args) {
        Thread t1 = new Thread(new HiloSecuencial());
        Thread t2 = new Thread(new HiloBajada());

        t1.start();
        t2.start();
    }
}
