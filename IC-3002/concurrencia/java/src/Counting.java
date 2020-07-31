/**
 * @author: diegomunguia
 */
class Counter {
    private Integer count = 0;
    public synchronized void increment() {
        ++count;
    }
    public int getCount() { return this.count; }
}


public class Counting {
    public static void main(String[] args) throws InterruptedException {
        final Counter counter = new Counter();

        class CountingThread extends Thread {
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    counter.increment();
                }
            }
        }

        CountingThread t1 = new CountingThread();
        CountingThread t2 = new CountingThread();
        t1.start();
        t2.start();
        
        t1.join();
        t2.join();

        System.out.println("counter.getCount() = " + counter.getCount());

    }
}
