import java.util.concurrent.ForkJoinPool;

public class Driver {
    public static void main(String[] args) {
        
        ForkJoinPool forkJoinPool = new ForkJoinPool();

        // final int MAX_SIZE = 45;
        // int i=0;
        // int arr[] = new int[MAX_SIZE];

        // for(;i<MAX_SIZE;i++){
        //     arr[i] = i+1;
        // }
        
        // ComputeSumTask task1 = new ComputeSumTask(arr);
        // System.out.println(forkJoinPool.invoke(task1));


        ShiftToUpperCase shiftToUpperCase =  new ShiftToUpperCase("Programmer is programming to learn fork and join");
        forkJoinPool.invoke(shiftToUpperCase);

        System.out.println(shiftToUpperCase.getName());

        forkJoinPool.close();
    }
}
