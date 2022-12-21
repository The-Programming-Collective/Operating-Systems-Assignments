import java.util.LinkedList;

public abstract class AllocationPolicy {

    //used to find the index of the partition
    public class Pair{
        int index;
        int size;
        Pair(int index , int size){
            this.index = index;
            this.size = size;
        }
    }

    //unified function amongst policies
    public abstract void start(LinkedList<Partition> partitions , LinkedList<Process> processes);
    
    //A function to combine all the unused (external fragment) patitions
    //into one partition
    public void compaction(LinkedList<Partition> partitions){
        int accumilated = 0;
        for(int i=0 ; i<partitions.size() ; i++){
            //if the partition is empty
            if(partitions.get(i).process==null){
                accumilated+=partitions.get(i).size;
                partitions.remove(i--);
            }
        }

        //No compaction happened
        if(accumilated==0)
            return;
        
        //else add the new partition with the accumulated size
        partitions.addLast(new Partition(accumilated));
    };

    //A function to reallocate the remaining part of a partition (internal fragment)
    //adds the new partition with the new size after the current one 
    public void refreshPartitions(LinkedList<Partition> partitions,int index){
        if(partitions.get(index).size>partitions.get(index).process.size){
            int fragment = partitions.get(index).size-partitions.get(index).process.size;
            partitions.get(index).size-=fragment;   
            partitions.add(index+1, (new Partition(fragment)));
        }
    }
}
