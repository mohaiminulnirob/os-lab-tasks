package Thread.ThreadCreation;

public class Main {
    public static void main(String[] args) {
        Thread t1 = new Thread(new MyRunnable("Hello from Thread 1"));
        t1.start();
        try {
            t1.sleep(10000);
        }catch(Exception e){
            e.printStackTrace();
        }
        Thread t2 = new Thread(new MyRunnable("Hello from Thread 2"));
        Thread t3 = new Thread(() -> {
            System.out.println("Hello from Lambda Thread 3");
        });
        t2.start();
        t3.start();

    }
}
