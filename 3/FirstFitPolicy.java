import java.util.LinkedList;

public class FirstFitPolicy extends AllocationPolicy {
    public void start(LinkedList<Partition> partitions, LinkedList<Process> processes) {
        
        for(int i=0 ; i<processes.size() ; i++){
            //used to hold the index of the partition if found
            int index=-1;

            //searches for a
            //1. empty partition
            //2. partition size >= prosses size
            for(int j=0 ; j<partitions.size() ; j++){
                if(partitions.get(j).process==null && 
                partitions.get(j).size>=processes.get(i).size){
                    index=j;
                    break;
                }
            }
            //found a partition
            if(index!=-1)
                partitions.get(index).process=processes.remove(i--);
        }
    }
}
