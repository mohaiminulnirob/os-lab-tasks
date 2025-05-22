package Synchronization.Reader_writer;

public class ReaderWriterSemaphore {
    static class Semaphore {
        private int count;

        public Semaphore(int initial) {
            this.count = initial;
        }

        public synchronized void waitSem() {
            while (count == 0) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            count--;
        }

        public synchronized void signalSem() {
            count++;
            notify();
        }
    }

    static class SharedData {
        int value = 0;
        int readCount = 0;

        Semaphore mutex = new Semaphore(1);
        Semaphore rw_mutex = new Semaphore(1);
    }

    static class Reader extends Thread {
        SharedData data;
        String name;

        Reader(SharedData data, String name) {
            this.data = data;
            this.name = name;
        }

        public void run() {
            try {
                Thread.sleep((int)(Math.random() * 100));
            } catch (InterruptedException e) {}

            data.mutex.waitSem();
            data.readCount++;
            if (data.readCount == 1) {
                data.rw_mutex.waitSem();
            }
            data.mutex.signalSem();

            System.out.println(name + " read value: " + data.value);

            data.mutex.waitSem();
            data.readCount--;
            if (data.readCount == 0) {
                data.rw_mutex.signalSem();
            }
            data.mutex.signalSem();
        }
    }

    static class Writer extends Thread {
        SharedData data;
        String name;

        Writer(SharedData data, String name) {
            this.data = data;
            this.name = name;
        }

        public void run() {
            try {
                Thread.sleep((int)(Math.random() * 100));
            } catch (InterruptedException e) {}

            data.rw_mutex.waitSem();

            int newValue = (int)(Math.random() * 1000);
            System.out.println(name + " writing value: " + newValue);
            data.value = newValue;

            data.rw_mutex.signalSem();
        }
    }

    public static void main(String[] args) {
        SharedData shared = new SharedData();

        Thread[] threads = new Thread[6];
        threads[0] = new Reader(shared, "Reader-1");
        threads[1] = new Writer(shared, "Writer-1");
        threads[2] = new Reader(shared, "Reader-2");
        threads[3] = new Writer(shared, "Writer-2");
        threads[4] = new Reader(shared, "Reader-3");
        threads[5] = new Writer(shared, "Writer-3");

        for (Thread t : threads) {
            t.start();
        }

        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {}
        }

        System.out.println("\nFinal value: " + shared.value);
    }
}
