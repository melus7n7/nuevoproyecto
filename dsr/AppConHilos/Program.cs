namespace AppConHilos;

class Program
{
    static void Main(string[] args)
    {
        Console.WriteLine("Sulem Martínez Aguilar Desarrollo de sistemas en red");
        Console.WriteLine("El main thread comienza aquí");

        Thread backgroundThread = new Thread(new ThreadStart(Program.DoTrabajoPesado));
        backgroundThread.Start();

        Program.DoAlgo();

        Console.WriteLine("El main thread termina aquí");
    }
    public static void DoTrabajoPesado(){
        Console.WriteLine("DoTrabajoPesado: Estoy levantando un camión!!");
        Thread.Sleep(1000);
        Console.WriteLine("DoTrabajoPesado: Cansado! Necesito una siesta de 3 seg");
        Thread.Sleep(1000);
        Console.WriteLine("DoTrabajoPesado: 1....");
        Thread.Sleep(1000);
        Console.WriteLine("DoTrabajoPesado: 2....");
        Thread.Sleep(1000);
        Console.WriteLine("DoTrabajoPesado: 3....");
        Console.WriteLine("DoTrabajoPesado: Ya desperté");
    }
    public static void DoAlgo(){
        Console.WriteLine("DoAlgo: Oye! Estoy haciendo algo aquí!");
        for (int i = 0; i < 20; i++)
        {
            Console.Write($"{i} ");         
        }
        Console.WriteLine();
        Console.WriteLine("DoAlgo: Ya terminé!");
    }
}
