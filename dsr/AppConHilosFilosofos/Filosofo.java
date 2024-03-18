import java.util.Random;

public class Filosofo implements Runnable{
    private int tenedorIz;
    private int tenedorDerc;
    private Random random;

    public Filosofo(int tenedorIz, int tenedorDerc){
        this.tenedorIz = tenedorIz;
        this.tenedorDerc = tenedorDerc;
        random = new Random();
    }

    public void vivir(){
        while (true) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } 
            tomarTenedores();
            pensar();
        }
    }
    
    public void tomarTenedores(){
        synchronized(FilosofosComelones.tenPorFil[tenedorIz]){
            if((int)FilosofosComelones.tenPorFil[tenedorIz] > 0){ //Mayor a 0 ocupado
                try {
                    System.out.println(Thread.currentThread().getName() + " espera el tenedor izquierdo!!!!! " + FilosofosComelones.tenPorFil[tenedorIz]);
                    FilosofosComelones.tenPorFil[tenedorIz].wait(1000);
                    System.out.println(Thread.currentThread().getName() + " ya tiene el tenedor izquierdo!!!!!!! ");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println(Thread.currentThread().getName() + " tomó el tenedor izquierdo ");
            FilosofosComelones.tenPorFil[tenedorIz] = (int) FilosofosComelones.tenPorFil[tenedorIz] + 1;

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } 

            synchronized(FilosofosComelones.tenPorFil[tenedorDerc]){
                if((int)FilosofosComelones.tenPorFil[tenedorDerc] > 0){ //Mayor a 0 ocupado
                    try {
                        System.out.println(Thread.currentThread().getName() + " espera el tenedor derecho!!!!!!!!!!!!!!!!111 ");
                        FilosofosComelones.tenPorFil[tenedorDerc].wait(1000);
                        System.out.println(Thread.currentThread().getName() + " ya tiene el tenedor derecho!!!!!!!!!!!!!1 ");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                System.out.println(Thread.currentThread().getName() + " tomó el tenedor derecho");
                FilosofosComelones.tenPorFil[tenedorDerc] = (int) FilosofosComelones.tenPorFil[tenedorDerc] + 1;

                comer();

                FilosofosComelones.tenPorFil[tenedorIz] = (int) FilosofosComelones.tenPorFil[tenedorIz] - 1;
                FilosofosComelones.tenPorFil[tenedorDerc] = (int) FilosofosComelones.tenPorFil[tenedorDerc] - 1;                              

            }
        }
    }
    public void comer(){
        System.out.println("Inicia a comer " + Thread.currentThread().getName());
        try {
            Thread.sleep(random.nextInt(201));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } 
        System.out.println("Termina de comer " + Thread.currentThread().getName());
    }
    public void pensar(){
        System.out.println("Inicia a pensar " + Thread.currentThread().getName());
        try {
            Thread.sleep(random.nextInt(1001) + 200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } 
        System.out.println("Termina de pensar " + Thread.currentThread().getName());
    }
    @Override
    public void run(){
        vivir();
    }
}
