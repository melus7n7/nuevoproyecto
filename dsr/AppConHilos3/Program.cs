namespace AppConHilos3;

public class Persona
{
    public string Nombre { get; set;}
    public int Edad { get; set; }
    public string Sexo { get; set; }

    public Persona(string nombre, int edad, string sexo)
    {
        this.Nombre = nombre;
        this.Edad = edad;
        this.Sexo = sexo;
    }

    static void TareaDeFondoConParametro(Object? stateInfo)
    {
        if(stateInfo == null)
            return;

        Thread currentThread = Thread.CurrentThread;
        Console.WriteLine("Es un thread background: {0}", currentThread.IsBackground);

        Persona data = (Persona)stateInfo;
        Console.WriteLine($"Hilo 2: Hola {data.Nombre} desde ThreadPool, tu edad es {data.Edad}");
        Thread.Sleep(500);
    }

    static void TareaDeFondo(Object? stateInfo)
    {
        Console.WriteLine($"Hilo 1: Holaa soy un hilo sin parámetros del ThreadPool");
        Thread.Sleep(1500);
    }

    static void Main(string[] args)
    {
        int workers, ports;
        ThreadPool.GetMaxThreads(out workers, out ports);
        Console.WriteLine($"Máximos hilos de trabajo: {workers}");
        Console.WriteLine($"Máximos puertos para hilos: {ports}");

        ThreadPool.GetMinThreads(out workers, out ports);
        Console.WriteLine($"Mínimos hilos de trabajo: {workers}");
        Console.WriteLine($"Mínimos puertos para hilos: {ports}");

        ThreadPool.GetAvailableThreads(out workers, out ports);
        Console.WriteLine($"Hilos de trabajo disponibles: {workers}");
        Console.WriteLine($"Puertos para hilos disponibles: {ports}");

        int processCount = Environment.ProcessorCount;
        Console.WriteLine($"No. de procesadores disponibles en el sistema: {processCount}");
        Console.WriteLine($"--------------------------------");

        ThreadPool.QueueUserWorkItem(TareaDeFondo);
        ThreadPool.GetAvailableThreads(out workers, out ports);
        Console.WriteLine($"Hilos de trabajo disponibles después del hilo 1: {workers}");

        Persona p = new Persona("Sulem Martínez", 20, "Mujer");
        ThreadPool.QueueUserWorkItem(TareaDeFondoConParametro, p);
        
        ThreadPool.GetAvailableThreads(out workers, out ports);
        Console.WriteLine($"Hilos de trabajo disponibles después del hilo 2: {workers}");

        Thread.Sleep(2000);
        ThreadPool.GetAvailableThreads(out workers, out ports);
        Console.WriteLine($"Hilos de trabajo disponibles al final: {workers}");

        Console.ReadKey();
    }

}

