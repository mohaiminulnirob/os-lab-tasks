package Synchronization.Reader_writer;


public class ReaderWriterMutex {
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

    static class SharedData {
        static int value = 0;
        static int readCount = 0;

        static Mutex mutex = new Mutex();
        static Mutex rw_mutex = new Mutex();
    }

    static class Reader extends Thread {
        String name;

        Reader(String name) {
            this.name = name;
        }

        public void run() {
            try {
                Thread.sleep((int) (Math.random() * 100));
            } catch (InterruptedException e) {
            }

            SharedData.mutex.acquire();
            SharedData.readCount++;
            if (SharedData.readCount == 1) {
                SharedData.rw_mutex.acquire();
            }
            SharedData.mutex.release();

            System.out.println(name + " read value: " + SharedData.value);

            SharedData.mutex.acquire();
            SharedData.readCount--;
            if (SharedData.readCount == 0) {
                SharedData.rw_mutex.release();
            }
            SharedData.mutex.release();
        }
    }

    static class Writer extends Thread {
        String name;

        Writer(String name) {
            this.name = name;
        }

        public void run() {
            try {
                Thread.sleep((int) (Math.random() * 100));
            } catch (InterruptedException e) {
            }

            SharedData.rw_mutex.acquire();

            int newValue = (int) (Math.random() * 1000);
            System.out.println(name + " writing value: " + newValue);
            SharedData.value = newValue;

            SharedData.rw_mutex.release();
        }
    }

    public static void main(String[] args) {
        Thread[] threads = new Thread[6];
        threads[0] = new Reader("Reader-1");
        threads[1] = new Writer("Writer-1");
        threads[2] = new Reader("Reader-2");
        threads[3] = new Writer("Writer-2");
        threads[4] = new Reader("Reader-3");
        threads[5] = new Writer("Writer-3");

        for (Thread t : threads) {
            t.start();
        }

        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
            }
        }

        System.out.println("\nFinal value: " + SharedData.value);
    }
}

