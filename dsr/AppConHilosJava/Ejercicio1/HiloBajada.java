package Ejercicio1;

public class HiloBajada implements Runnable{
    public HiloBajada(){
    }

    @Override
    public void run(){
        for(int i=100; i > 0; i--){
            System.out.println("Hilo 2: " + i);
            try{
                Thread.sleep(100);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
