package Main;
import java.util.concurrent.Semaphore;
import java.util.Scanner;

public class Main {
	
	/**
	 * Inicio: Se crea un semáforo que solo permite que un hilo ejecute código a la vez 
	 */
    private static final Semaphore semaforo = new Semaphore(1); 
	
	/**
	 * main: 
	 * 1. Crea los objetos: fumador con tabaco, fumador con papel, 
	 * fumador con fósforo, agente y mesa (de la simulación); 
	 * y el objeto util, que será necesario. 
	 */
    public static void main(String[] args) {
    	
    	// De utilidad
    	Util util = new Util();
    	Scanner scanner = new Scanner(System.in);
    	
    	// Creación de objetos
    	// El número le asignará a cada fumador el recurso ilimitado
    	Mesa mesa = new Mesa();
    	Agente agente = new Agente(util, mesa, semaforo);
    	Fumador fumadorConTabaco = new Fumador(util, mesa, semaforo, 0);
    	Fumador fumadorConPapel = new Fumador(util, mesa, semaforo, 1);
    	Fumador fumadorConFosforo = new Fumador(util, mesa, semaforo, 2);
    	
    	/**
    	 * 2. Pide al usuario, el número de interacciones, 
         * que será la cantidad de veces que el agente ponga recursos en la mesa
    	 */
    	System.out.println("\nCuántas interacciones desea realizar?\n");
    	int interacciones = scanner.nextInt();
    	util.set_numInteracciones(interacciones);
    	scanner.close();
    	
    	/**
    	 * @author Kenia Romero
    	 * @summary Crea los hilos con la característica de que:
    	 * al iniciar un hilo, se llamará al método run del objeto constantemente;
    	 * esto mientras el booleano util.get_run() sea verdad.
    	 */
    	
        Thread agenteThread = new Thread(() -> {
            while (util.get_run()) {
                agente.run();
            }
        });
        
        Thread fumador1Thread = new Thread(() -> {
            while (util.get_run()) {
            	fumadorConTabaco.run();
            }
        });
        
        Thread fumador2Thread = new Thread(() -> {
            while (util.get_run()) {
            	fumadorConPapel.run();
            }
        });
               
        Thread fumador3Thread = new Thread(() -> {
            while (util.get_run()) {
            	fumadorConFosforo.run();
            }
        });
        
        // **** Hilos empiezan a correr ****
        
    	System.out.println("\nInterracciones:\n");
    	
    	agenteThread.start();
    	fumador1Thread.start(); //Con tabaco
    	fumador2Thread.start(); //Con papel
    	fumador3Thread.start(); //Con fósforo
    	
    	 /**
         * 3. try: Espera a que los hilos terminen de ejecutarse.
         */ 
        try {
        	agenteThread.join();
        	fumador1Thread.join();
        	fumador2Thread.join();
        	fumador3Thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        /**
         * 4. if: cuando los hilos dejan de correr manda mensaje.
         */ 
        if (!util.get_run())
    		System.out.println("Interracciones finalizadas");
    			
        } // fin método main
        } // fin clase Main