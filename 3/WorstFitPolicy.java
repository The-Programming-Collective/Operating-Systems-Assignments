import java.util.LinkedList;

public class WorstFitPolicy extends AllocationPolicy {
    public void start(LinkedList<Partition> partitions, LinkedList<Process> processes) {
        for(int i=0 ; i<processes.size() ; i++){
            Pair fit = new Pair(-1,Integer.MIN_VALUE) ;
            for(int j=0 ; j<partitions.size() ; j++){
                if(partitions.get(j).process==null && 
                partitions.get(j).size>=processes.get(i).size &&
                partitions.get(j).size>fit.size){
                    fit.index=j;
                    fit.size=partitions.get(j).size;
                }
            }
            if(fit.index!=-1)
                partitions.get(fit.index).process=processes.remove(i--);
        }
        
    }
    
}
