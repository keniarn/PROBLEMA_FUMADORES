import java.util.concurrent.*;

class Fumadores {
    // Semáforos para los ingredientes
    private Semaphore papelSem = new Semaphore(0);
    private Semaphore tabacoSem = new Semaphore(0);
    private Semaphore cerillasSem = new Semaphore(0);

    // Semáforo para señalar al agente cuando puede colocar ingredientes
    private Semaphore agenteSem = new Semaphore(1);

    // Método para el agente, que coloca dos ingredientes en la mesa
    public void colocarIngredientes(int ing1, int ing2) {
        try {
            agenteSem.acquire();
            switch (ing1 + ing2) {
                case 1:
                    tabacoSem.release();
                    cerillasSem.release();
                    break;
                case 2:
                    papelSem.release();
                    cerillasSem.release();
                    break;
                case 3:
                    papelSem.release();
                    tabacoSem.release();
                    break;
                default:
                    break;
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    // Método para el fumador 1, que tiene papel
    public void fumadorConPapel() {
        try {
            papelSem.acquire();
            System.out.println("Fumador con papel está fumando...");
            Thread.sleep(1000);
            System.out.println("Fumador con papel ha terminado de fumar.");
            agenteSem.release();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    // Método para el fumador 2, que tiene tabaco
    public void fumadorConTabaco() {
        try {
            tabacoSem.acquire();
            System.out.println("Fumador con tabaco está fumando...");
            Thread.sleep(1000);
            System.out.println("Fumador con tabaco ha terminado de fumar.");
            agenteSem.release();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    // Método para el fumador 3, que tiene cerillas
    public void fumadorConCerillas() {
        try {
            cerillasSem.acquire();
            System.out.println("Fumador con cerillas está fumando...");
            Thread.sleep(1000);
            System.out.println("Fumador con cerillas ha terminado de fumar.");
            agenteSem.release();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

