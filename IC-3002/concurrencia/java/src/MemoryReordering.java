/**
 * @author: diegomunguia
 */
public class MemoryReordering {
    static boolean ready = false;
    static int answer = 0;

    static Thread thread1 = new Thread() {
        @Override
        public void run() {
            answer = 42;
            ready = true;
        }
    };

    static Thread thread2 = new Thread() {
        @Override
        public void run() {
            if (ready) {
                System.out.println("El significado de la vida es: " + answer);
            } else {
                System.out.println("No sé la respuesta");
            }
        }
    };

    public static void main(String[] args) throws InterruptedException {
        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
    }
}
