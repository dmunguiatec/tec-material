import java.util.Random;

/**
 * @author: diegomunguia
 */
public class DiningPhilosophers {
    class Chopstick {
        private int id;

        public int getId() {
            return id;
        }

        public Chopstick(int id) {
            this.id = id;
        }
    }

    class Philosopher extends Thread {
        private int id;
        private Chopstick left, right;
        private Random random = new Random();

        public Philosopher(int id, Chopstick left, Chopstick right) {
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
                    synchronized (left) {
                        synchronized (right) {
                            System.out.println("[" + id + "] Comiendo  con " + left.getId() + " y " + right.getId());
                            Thread.sleep(random.nextInt(10));
                        }
                    }
                }
            } catch(InterruptedException e) {}
        }
    }

    public void dine(int dinersCount) {

        Chopstick[] chopsticks = new Chopstick[dinersCount];
        for (int i = 0; i < dinersCount; i++) {
            chopsticks[i] = new Chopstick(i);
        }

        for (int i = 0; i < dinersCount; i++) {
            Philosopher philosopher = new Philosopher(i, chopsticks[i], chopsticks[(i + 1) % dinersCount]);
            philosopher.start();
        }

    }

    public static void main(String[] args) {
        new DiningPhilosophers().dine(5);
    }
}
