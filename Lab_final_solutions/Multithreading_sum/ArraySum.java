package Lab_final_solutions.Multithreading_sum;

public class ArraySum implements Runnable{
    private int[] arr;
    private int start,end;
    private int result;
    public int getResult(){
      return result;
    }
    public ArraySum(int[] arr, int start, int end){
        this.arr=arr;
        this.start=start;
        this.end=end;
    }

    @Override
    public void run() {
        result=0;
        for(int i=start;i<=end;i++){
            result+=arr[i];
        }
    }
}
