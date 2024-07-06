public class Main {
    public static void main(String[] args) {
        Fumadores fumadores = new Fumadores();

        // Thread para el agente que coloca los ingredientes
        Thread agenteThread = new Thread(() -> {
            while (true) {
                int ing1 = (int) (Math.random() * 3) + 1;
                int ing2 = (int) (Math.random() * 3) + 1;

                // Asegurarse de que no coloca dos veces los mismos ingredientes
                while (ing1 == ing2) {
                    ing2 = (int) (Math.random() * 3) + 1;
                }

                fumadores.colocarIngredientes(ing1, ing2);

                try {
                    Thread.sleep(500); // Tiempo entre colocación de ingredientes
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        // Threads para los fumadores
        Thread fumadorPapelThread = new Thread(() -> {
            while (true) {
                fumadores.fumadorConPapel();
            }
        });

        Thread fumadorTabacoThread = new Thread(() -> {
            while (true) {
                fumadores.fumadorConTabaco();
            }
        });

        Thread fumadorCerillasThread = new Thread(() -> {
            while (true) {
                fumadores.fumadorConCerillas();
            }
        });

        // Iniciar los hilos
        agenteThread.start();
        fumadorPapelThread.start();
        fumadorTabacoThread.start();
        fumadorCerillasThread.start();
    }
}
