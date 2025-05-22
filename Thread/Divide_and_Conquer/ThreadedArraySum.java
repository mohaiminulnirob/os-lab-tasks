package Thread.Divide_and_Conquer;

public class ThreadedArraySum {
    public static void main(String[] args) {
        int size = 1000;
        int[] array = new int[size];

        for (int i = 0; i < size; i++) {
            array[i] = i + 1;
        }

        ArraySumTask mainTask = new ArraySumTask(array, 0, array.length);
        Thread mainThread = new Thread(mainTask);
        mainThread.start();

        try {
            mainThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Total Sum: " + mainTask.getResult());
    }
}