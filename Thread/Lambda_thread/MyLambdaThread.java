package Thread.Lambda_thread;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MyLambdaThread {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            try {
                File file = new File("Thread.txt");
                if (file.createNewFile()) {
                    System.out.println("File created by T1.");
                } else {
                    System.out.println("File already exists.");
                }
            } catch (IOException e) {
                System.out.println("T1 encountered an error: " + e.getMessage());
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                t1.join();
                FileWriter writer = new FileWriter("Thread.txt");
                writer.write("Hello its T2");
                writer.close();
                System.out.println("T2 wrote to the file.");
            } catch (Exception e) {
                System.out.println("T2 encountered an error: " + e.getMessage());
            }
        });

        t1.start();
        t2.start();
    }
}
