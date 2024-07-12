package Main;

public class Mesa {
 // Estado de la mesa
 private volatile boolean estado=false; // Estado false = No hay recursos colocados
 public volatile int[] recursosColocados = new int[2]; // En la mesa se pueden colocar varios recursos, 
                                               // todos los cuales son públicos
 
	/**
	 * @author Kevin Sánchez
	 * @summary Metodo ejecutado por el agente para colocar los elementos en la mesa.
	 * Una vez que la mesa está llena con dos recursos, el estado pasa a true
	 */
 public void colocarRecurso(int numRecurso, int IDrecurso) {
	 this.recursosColocados[numRecurso]=IDrecurso;
	 this.estado= (numRecurso==1)? true : false;
 }
 
	/**
	 * @author Kevin Sánchez
	 * @summary Metodo ejecutado por un fumador para tomar un elemento de la mesa
	 */
 public void quitarRecurso(int recursoAgarrado) {
			 this.recursosColocados[recursoAgarrado]=-1;
			 this.estado= (this.recursosColocados[0]+this.recursosColocados[1]==-2)? false : true; 
	 }
 
 
 public void getMesa() {
	 for(int i=0;i<2;i++)
		 System.out.println(i + " tiene valor: " + recursosColocados[i]);
	 if(this.estado)
		 System.out.println("llena");
 }
 
 // Set y get
 public void set_estado(boolean estado) {
 	this.estado=estado;
 }
 
 public boolean estaVacia() {
 	return this.estado;
 }
} // fin clase Mesa
