package Lab_final_solutions.Synchronization;

public class CriticalSectionSolution {

    static int accountA = 500;
    static int accountB = 0;
    static final Object lock = new Object();

    static class Transfer implements Runnable {
        private int amount;

        public Transfer(int amount) {
            this.amount = amount;
        }

        @Override
        public void run() {
            synchronized (lock) {
                if (accountA >= amount) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    accountA -= amount;
                    accountB += amount;
                }
                System.out.printf("Transfer %d: A=%d, B=%d (Total: %d)\n",
                        amount, accountA, accountB, accountA + accountB);
            }
        }
    }

    public static void main(String[] args) {
        Thread thread1 = new Thread(new Transfer(100));
        Thread thread2 = new Thread(new Transfer(500));

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.printf("Final balances: A=%d, B=%d (Total: %d)\n",
                accountA, accountB, accountA + accountB);
    }
}