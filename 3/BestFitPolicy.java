import java.util.LinkedList;

public class BestFitPolicy extends AllocationPolicy{
    public void start(LinkedList<Partition> partitions, LinkedList<Process> processes) {
        
        for(int i=0 ; i<processes.size() ; i++){
            //used to keep track of the partition index and the size
            //with -1 used as an indicator that no partition was found
            //for that process and a MAX_VALUE to be used as a base
            Pair fit = new Pair(-1,Integer.MAX_VALUE) ;
            for(int j=0 ; j<partitions.size() ; j++){
                if(partitions.get(j).process==null && 
                partitions.get(j).size>=processes.get(i).size &&
                partitions.get(j).size<fit.size){
                    fit.index=j;
                    fit.size=partitions.get(j).size;
                }
            }
            if(fit.index!=-1)
                partitions.get(fit.index).process=processes.remove(i--);
        }
        
    }
    
}