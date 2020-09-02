/**
 * @author: diegomunguia
 */
public class HelloWorld {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread() {
            public void doSomething() {
                System.out.println("Soy el hilo");
            }
            @Override
            public void run() {
                doSomething();
            }
        };

        Thread thread2 = new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Soy el hilo 2");
            }
        };


        Thread.yield();
        thread2.start();
        System.out.println("Soy el principal");

        System.out.println("Esperando a thread");
        thread2.join();
        thread.start();
        thread.join();
        System.out.println("Ya terminaron los dos hilos");
    }
}





/*
        Thread thread1 = new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Soy el hilo 2");
            }
        };
*/
//        thread1.start();
