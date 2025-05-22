package Thread.Divide_and_Conquer;

class ArraySumTask implements Runnable {
    private int[] arr;
    private int start, end;
    private int result;
    private static final int THRESHOLD = 100;

    public ArraySumTask(int[] arr, int start, int end) {
        this.arr = arr;
        this.start = start;
        this.end = end;
    }

    public int getResult() {
        return result;
    }

    @Override
    public void run() {
        int length = end - start;
        if (length <= THRESHOLD) {
            result = 0;
            for (int i = start; i < end; i++) {
                result += arr[i];
            }
        } else {
            int mid = start + length / 2;
            ArraySumTask leftTask = new ArraySumTask(arr, start, mid);
            ArraySumTask rightTask = new ArraySumTask(arr, mid, end);

            Thread leftThread = new Thread(leftTask);
            Thread rightThread = new Thread(rightTask);
            leftThread.start();
            rightThread.start();

            try {
                leftThread.join();
                rightThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            result = leftTask.getResult() + rightTask.getResult();
        }
    }
}
