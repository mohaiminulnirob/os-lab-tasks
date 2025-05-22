package Synchronization.Producer_consumer;

public class ProducerConsumerMutex {
    static int count = 0;

    static class Mutex {
        private volatile boolean available = true;

        public void acquire() {
            while (!available) {

            }
            available = false;
        }

        public void release() {
            available = true;
        }
    }

    static Mutex mutex = new Mutex();

    static class Producer extends Thread {
        public void run() {
            for (int i = 0; i < 20; i++) {
                mutex.acquire();
                int temp = count;
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                }
                count = temp + 1;
                System.out.println("Produced: count = " + count);

                mutex.release();

                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                }
            }
        }
    }

    static class Consumer extends Thread {
        public void run() {
            for (int i = 0; i < 20; i++) {
                mutex.acquire();

                int temp = count;
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                }
                count = temp - 1;
                System.out.println("Consumed: count = " + count);

                mutex.release();

                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                }
            }
        }
    }

    public static void main(String[] args) {
        Producer producer = new Producer();
        Consumer consumer = new Consumer();

        producer.start();
        consumer.start();

        try {
            producer.join();
            consumer.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\nFinal count = " + count);
    }
}
