import java.util.Arrays;
import java.util.concurrent.RecursiveTask;

/*
    * Fork/join works on work-stealing algorithm.

    * Work-stealing algorithm uses the idea of stealing tasks from another threads if a thread is idle.
      The idea is to keep all the worker threads busy and minimise CPU idle time. Every threads keeps a
      deque. Every new subtask is pushed to the deque and retreived from it only, when a thread's deque
      become empty, it pulls from another thread.

    * The subtasks must be independent.
      
    * RecursiveTask is used when the parallel task needs to return some value.
    
    * RecursiveAction is used when the parallel task does not need to return some value.
*/

public class ComputeSumTask extends RecursiveTask<Long>{

    public int[] arr;
    private final int threshold = 20;
    

    public ComputeSumTask(int[] arr) {
        this.arr = arr;
    }



    @Override
    protected Long compute() {

        if(arr.length<threshold)
          return findSum();

        else{
            int arrLength = arr.length;
            ComputeSumTask firstTask = new ComputeSumTask(Arrays.copyOfRange(arr, 0, arrLength/2));
            ComputeSumTask secondTask = new ComputeSumTask(Arrays.copyOfRange(arr, arrLength/2, arrLength));

            /*
                * fork method starts the asynchronous execution of the sub-tasks
                * join method is used to get the result of the subtask after the execution
            */

            firstTask.fork();
            secondTask.fork();

            return firstTask.join() + secondTask.join();
        }  
    }

    private Long findSum(){

        Long sum=0L;

        for(int i: arr)
          sum+=i;

        return sum;  
    }
    
}