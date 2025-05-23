package Lab_final_solutions.Multithreading_sum;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int size;
        System.out.printf("Enter size of the array: ");
        size = sc.nextInt();
        int[] array = new int[size];
        System.out.printf("Enter array elements: ");
        for (int i = 0; i < size; i++) {
            array[i] = sc.nextInt();
        }
        int mid = size / 2;
        ArraySum sum1 = new ArraySum(array, 0, mid - 1);
        ArraySum sum2 = new ArraySum(array, mid, size - 1);
        Thread t1 = new Thread(sum1);
        Thread t2 = new Thread(sum2);
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int s1 = sum1.getResult();
        ;
        System.out.println("Thread 1(sum of first half): " + s1);
        int s2 = sum2.getResult();
        int s3 = s1 + s2;
        System.out.println("Thread 2 (sum of second half): " + s2);
        System.out.println("total sum: " + s3);


    }

}
