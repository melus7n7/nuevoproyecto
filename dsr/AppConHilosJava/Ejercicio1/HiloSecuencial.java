package Ejercicio1;

public class HiloSecuencial implements Runnable{

    public HiloSecuencial(){
    }

    @Override
    public void run(){
        for(int i=0; i <= 100; i++){
            System.out.println("Hilo 1: " + i);
            try{
                Thread.sleep(100);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}