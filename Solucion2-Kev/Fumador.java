package Main;

import java.util.concurrent.Semaphore;

public class Fumador extends Proceso implements Runnable {

	// Utiles
	private volatile boolean fumadorSatisfecho=false;
	private volatile int recursoIdentificado;
	
	// Espacio para alojar los recursos tomados de la mesa
	private volatile int[] recursosObtenidos = {-1,-1}; // -1 = vacío
	
	// Constructor
	public Fumador(Util util, Mesa mesa, Semaphore semaforo, int IDrecurso) {
		super(util, mesa, semaforo, IDrecurso);
		System.out.println("Fumador con " + super.elementoIlimitado + " ilimitado creado.");
	}
	
	/**
	 * @author Kevin Sánchez
	 * @summary El fumador revisa si hay recursos en la mesa y si estos son los que le faltan
	 */
	public boolean mesaConRecursos() {
		if(mesa.recursosEnLaMEsa[0]!=-1 
		&& mesa.recursosEnLaMEsa[1]!=-1 
				&& mesa.recursosEnLaMEsa[0]!=this.IDrecurso 
				&& mesa.recursosEnLaMEsa[1]!=this.IDrecurso)
			return true;
		else return false;
	}

    /**
	* @author Kevin Sánchez
	* @summary Método por el cual el fumador toma un recurso a la vez de la mesa
	* si no es el recurso que tiene.
	* El método no contempla si el fumador tomará ambos recursos o solo uno
	* (al fumador no le interesa), por lo que ocupa un proceso de sincronización
	* para no colapsar al sistema.
	*/
	public void tomarRecursos() {
		/**
		 * for: Itera los recursos en la mesa 
		 */
	for(int i=0;i<2;i++) 
	{
		/**
		 * if: Si no tiene el recurso, y el campo no está vacío (= -1),
		 * guarda su identificador, 
		 * lo agarra (con lo cual mesa.recursosColocados[i] = -1)
		 * y manda un mensaje.
		 */
	if(this.mesa.recursosEnLaMEsa[i]!=this.IDrecurso && this.mesa.recursosEnLaMEsa[i]!=-1) {
		this.recursoIdentificado=this.mesa.recursosEnLaMEsa[i];  
		this.mesa.quitarRecurso(i);  // El recurso ya no está disponible en la mesa 
			this.recursosObtenidos[i]=this.recursoIdentificado;
		System.out.println("- El fumador con " + super.elementoIlimitado + 
				" tomó el " + super.recursos[this.recursoIdentificado] + ".");
	} // fin if
	} // fin for
	} // fin tomarRecursos
	
    /**
	* @author Kevin Sánchez
	* @summary Método por el cual el fumador arma un cigarro y fuma si tiene los recursos necesarios.
	* Si no los tiene, algo salió mal.
	*/
	public void fumar() {
		
		 /**
		* if: Verifica si el fumador tiene todos los recursos;
		* en dado caso la suma de los ID de los recursos debe ser igual a 3.
		*/ 		
		if(this.recursosObtenidos[0]+this.recursosObtenidos[1]+this.IDrecurso==3)
		{
			System.out.println("- El fumador hace un cigarrillo...");
            System.out.println("- El fumador está fumando...");
            this.fumadorSatisfecho=true;
	}
		 /**
		* else: Indica que al fumador le faltan recursos, y cual es el que agarró de la mesa.
		*/ 
		else
			{
			System.out.print("- Al fumador con " + super.recursos[this.IDrecurso] + " le faltan recursos");
		for(int i=0;i<2;i++)
			if(this.recursosObtenidos[i]!=-1)
				System.out.print(" (solo cuenta con " + super.recursos[this.recursosObtenidos[i]] + ")");
		        System.out.println(".");
			}			
	}
	
	@Override
	/**
	 * @author Kevin Sánchez
	 * @summary Corre el hilo para que el fumador tome recursos, haga un cigarro y fume.
	 * Los "sleep" simulan la tardanza en la ejecución.
	 */
    public void run() {
        try {
        	// Fumador adquiere el semáforo
        	semaforo.acquire();
        	
        	 if(this.mesaConRecursos()) {
    		// Sin el semáforo, el método podría colapsar al sistema
    		tomarRecursos();
    		Thread.sleep(400);
    		
    		// Sin el semáforo, el fumador podría no fumar
    		fumar();
    		Thread.sleep(1994);
    		
    		/**
    		 * Último if: el fumador anuncia que dejó de fumar si ese es el caso,
    		 * de lo contrario, estará enojado. 
    		 * De igual manera el fumador termina de ejcutarse para no terminar el programa.
    		 */
    		if(this.fumadorSatisfecho)
            System.out.println("- El fumador terminó de fumar.\n");    
    		else
                System.out.println("- El fumador con " + super.recursos[this.IDrecurso] + " está encachimbado.\n");
        	 } // fin if     	
        } // fin try

            catch (InterruptedException e) 
        {
            	ExceptionMensagge(e);
        } 
        finally { 
        	// El fumador libera el semáforo
        	semaforo.release();
  	
        } // fin finally
    } // fin run
} // fin clase Fumador
