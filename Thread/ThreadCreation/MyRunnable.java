package Thread.ThreadCreation;

public class MyRunnable implements Runnable{
    private String message;

    public MyRunnable(String msg) {
        this.message = msg;
    }

    public void run() {
        System.out.println("Message: " + message);
    }
}
