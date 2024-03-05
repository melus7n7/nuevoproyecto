#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

int main()
{
    int pid = fork();
    printf("Id del proceso: %d\n", pid);

    if(pid > 0)
        sleep(20);
    
    else if (pid == 0)
    {
        printf("\nproceso Zombie creado con exito! " ) ;
        printf("\nEstarä activo durante 20 segundos\n");
        exit (0);
    }
    else
        printf("\nLo sentimos! El proceso Hi jo no pudo ser creado...");
    
    return 0;
}
