package Synchronization.Reader_writer;

public class ReaderWriterRace {

    static int value = 0;

    static class Reader extends Thread {
        String name;

        Reader(String name) {
            this.name = name;
        }

        public void run() {
            try {
                Thread.sleep((int)(Math.random() * 100));
            } catch (InterruptedException e) {}

            System.out.println(name + " read value: " + value);
        }
    }

    static class Writer extends Thread {
        String name;

        Writer(String name) {
            this.name = name;
        }

        public void run() {
            int newValue = (int)(Math.random() * 1000);
            value = 100;
            try {
                Thread.sleep((int)(Math.random() * 100));
            } catch (InterruptedException e) {}
            value = newValue;
            System.out.println(name + " writing value: " + newValue);
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
            } catch (InterruptedException e) {}
        }

        System.out.println("Final value: " + value);
    }
}
