import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;

/*    
    * RecursiveAction is used when the parallel task does not need to return some value.
*/

public class ShiftToUpperCase extends RecursiveAction{

    private String name;
    private final int threshold = 15;

    public ShiftToUpperCase(String name){
        this.name = name;
    }

    @Override
    protected void compute() {

        if(name.length()<=threshold){
            name = processString();
        }else{

            int stringLength = name.length();

            ShiftToUpperCase shiftToUpperCase_subtask_1 = new ShiftToUpperCase(name.substring(0, stringLength/2));
            ShiftToUpperCase shiftToUpperCase_subtask_2 = new ShiftToUpperCase(name.substring(stringLength/2,stringLength));
            
            ForkJoinTask.invokeAll(shiftToUpperCase_subtask_1, shiftToUpperCase_subtask_2);

            // shiftToUpperCase_subtask_1.fork();
            // shiftToUpperCase_subtask_2.fork();

            // shiftToUpperCase_subtask_1.join();
            // shiftToUpperCase_subtask_2.join();

            StringBuilder strbuilder = new StringBuilder();

            strbuilder.append(shiftToUpperCase_subtask_1.getName());
            strbuilder.append(shiftToUpperCase_subtask_2.getName());

            name = strbuilder.toString();
        }
        
    }  

    public String getName(){
        return this.name;
    }
     
    public String processString(){
        return this.name.toUpperCase();
    }
}
