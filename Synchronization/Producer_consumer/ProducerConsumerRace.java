package Synchronization.Producer_consumer;


public class ProducerConsumerRace {
    static int count = 0;

    static class Producer extends Thread {
        public void run() {
            for (int i = 0; i < 20; i++) {
                int temp = count;
                try { Thread.sleep(10); } catch (InterruptedException e) {}
                count = temp + 1;
                System.out.println("Produced: count = " + count);

                try { Thread.sleep(20); } catch (InterruptedException e) {}
            }
        }
    }

    static class Consumer extends Thread {
        public void run() {
            for (int i = 0; i < 20; i++) {
                int temp = count;
                try { Thread.sleep(10); } catch (InterruptedException e) {}
                count = temp - 1;
                System.out.println("Consumed: count = " + count);

                try { Thread.sleep(20); } catch (InterruptedException e) {}
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
