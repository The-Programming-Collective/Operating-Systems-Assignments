import java.util.LinkedList;

public class FirstFitPolicy extends AllocationPolicy {
    public void start(LinkedList<Partition> partitions, LinkedList<Process> processes) {
        for(int i=0 ; i<processes.size() ; i++){
            if(partitions.get(i).process==null ){
                
            }
        }
        
    }
    
}
