import java.util.LinkedList;

public class FirstFitPolicy extends AllocationPolicy {
    public void start(LinkedList<Partition> partitions, LinkedList<Process> processes) {
        
        for(int i=0 ; i<processes.size() ; i++){
            //searches for a
            //1. empty partition
            //2. partition size >= prosses size
            for(int j=0 ; j<partitions.size() ; j++){
                if(partitions.get(j).process==null && 
                partitions.get(j).size>=processes.get(i).size){
                    partitions.get(j).process=processes.remove(i--);
                    refreshPartitions(partitions, j);
                    break;
                }
            }          
        }
    }
}
