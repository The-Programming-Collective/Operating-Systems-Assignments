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
    
    public void compaction(LinkedList<Partition> partitions){
        //to get the name of the new partion
        //before removing anything
        String pName = partitions.getLast().name;
        int num = Integer.parseInt(pName.split(" ")[1]);

        int accumilated = 0;
        for(int i=0 ; i<partitions.size() ; i++){
            //if the partition is empty
            if(partitions.get(i).process==null){
                accumilated+=partitions.get(i).size;
                partitions.remove(i--);
            }
            //if the partition is not empty but has internal fragment
            else{
                int fragment = partitions.get(i).size-partitions.get(i).process.size;
                accumilated+=fragment;
                partitions.get(i).size-=fragment;
            }
        }

        //No compaction happened
        if(accumilated==0)
            return;
        
        //else add the new partition with the accumulated size
        partitions.addLast(new Partition("partition "+(++num), accumilated));
    };
}
