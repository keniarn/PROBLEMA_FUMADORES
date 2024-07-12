package Main;
import java.util.concurrent.Semaphore;

public class Proceso {

	protected String nombreProceso;
    protected Semaphore semaforo;
    protected Util util;
    protected Mesa mesa;
    protected String[] recursos = {"tabaco", "papel", "fósforo"};
	protected String elementoIlimitado;
	protected int IDrecurso;

	// Constructor del agente
    public Proceso(Util util, Mesa mesa, Semaphore semaforo) {
        this.semaforo = semaforo;
        this.util = util;
        this.mesa=mesa;
        this.nombreProceso="Agente";
    }
    
	// Constructor de los fumadores
    public Proceso(Util util, Mesa mesa, Semaphore semaforo, int IDrecurso) {
        this.semaforo = semaforo;
        this.util = util;
        this.mesa=mesa;
        this.IDrecurso=IDrecurso;
		this.elementoIlimitado=recursos[IDrecurso];
		this.nombreProceso="Fumador con " + this.elementoIlimitado; 
    }
    
    // Mensaje de Error
    public void ExceptionMensagge(Exception e) {
        Thread.currentThread().interrupt();
        System.out.println("Proceso " + this.nombreProceso + " interrumpido.");
    }
}


