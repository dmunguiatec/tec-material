import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: diegomunguia
 */
public class DiningPhilosophersInterrupt {
    class Philosopher extends Thread {
        private int id;
        private ReentrantLock left, right;
        private Random random = new Random();

        public Philosopher(int id, ReentrantLock left, ReentrantLock right) {
            this.id = id;
            this.left = left;
            this.right = right;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    System.out.println("[" + id + "] Pensando");
                    Thread.sleep(random.nextInt(10));
                    left.lockInterruptibly();
                    try {
                        right.lockInterruptibly();
                        try {
                            System.out.println("[" + id + "] Comiendo");
                            Thread.sleep(random.nextInt(10));
                        } finally {
                            right.unlock();
                        }
                    } finally {
                        left.unlock();
                    }
                }
            } catch(InterruptedException e) {}
        }
    }

    public void dine(int dinersCount) throws InterruptedException {

        ReentrantLock[] chopsticks = new ReentrantLock[dinersCount];
        for (int i = 0; i < dinersCount; i++) {
            chopsticks[i] = new ReentrantLock();
        }

        Philosopher[] philosophers = new Philosopher[dinersCount];
        for (int i = 0; i < dinersCount; i++) {
            philosophers[i] = new Philosopher(i, chopsticks[i], chopsticks[(i + 1) % dinersCount]);
            philosophers[i].start();
        }

        Random random = new Random();
        while(true) {
            Thread.sleep(random.nextInt(1000));
            int i = random.nextInt(4);
            philosophers[i].interrupt();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new DiningPhilosophersInterrupt().dine(5);
    }
}



