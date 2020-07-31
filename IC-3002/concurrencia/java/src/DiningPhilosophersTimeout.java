import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: diegomunguia
 */
public class DiningPhilosophersTimeout {
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
                    left.lock();
                    try {
                        if (right.tryLock(1000, TimeUnit.MILLISECONDS)) {
                            try {
                                System.out.println("[" + id + "] Comiendo");
                                Thread.sleep(random.nextInt(10));
                            } finally {
                                right.unlock();
                            }
                        } else {
                            // go back to thinking
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
    }

    public static void main(String[] args) throws InterruptedException {
        new DiningPhilosophersTimeout().dine(5);
    }
}
