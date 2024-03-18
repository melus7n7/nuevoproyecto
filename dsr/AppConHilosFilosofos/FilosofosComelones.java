public class FilosofosComelones{
    public static Object[] tenPorFil = new Object[5];

    public static void main(String[] args) {
        tenPorFil[0] = 0;
        tenPorFil[1] = 0;
        tenPorFil[2] = 0;
        tenPorFil[3] = 0;
        tenPorFil[4] = 0;

        Thread thread1 = new Thread(new Filosofo(0, 4), "Filosofo_1");
        Thread thread2 = new Thread(new Filosofo(1, 0), "Filosofo_2");
        Thread thread3 = new Thread(new Filosofo(2, 1), "Filosofo_3");
        Thread thread4 = new Thread(new Filosofo(3, 2), "Filosofo_4");
        Thread thread5 = new Thread(new Filosofo(3, 4), "Filosofo_5");

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();

    }

}