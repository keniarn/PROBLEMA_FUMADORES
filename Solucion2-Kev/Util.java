package Main;

public class Util {
	
	public Util() {
	}
	// Estado de los hilos
    private boolean run = true; // true = hilos se mantendrán corriendo al iniciarse
    
	// Contadores
    private volatile int contador=1;
    private volatile int numInteracciones;
    
	// Setters y getters; y un add   
    public void set_numInteracciones(int numInteracciones) {
    	this.numInteracciones=numInteracciones;
    }
    
    public int get_numInteracciones() {
    	return this.numInteracciones;
    }
    
    public void add_contador() {
    	this.contador++;
    }
    
    public int get_contador() {
    	return this.contador;
    }
    
    public void set_run(boolean run) {
    	this.run=run;
    }
    
    public boolean get_run() {
    	return this.run;
    }
}