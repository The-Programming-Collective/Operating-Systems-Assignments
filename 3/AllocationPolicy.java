import java.util.LinkedList;

public abstract class AllocationPolicy {

    public class Pair{
        int index;
        int size;
        Pair(int index , int size){
            this.index = index;
            this.size = size;
        }
    }

    public abstract void start(LinkedList<Partition> partitions , LinkedList<Process> processes);
    
    public void compaction(LinkedList<Partition> partitions){
        int accumilated = 0;
        for(int i=0 ; i<partitions.size() ; i++){
            if(partitions.get(i).process==null){
                accumilated+=partitions.get(i).size;
                partitions.remove(i);
            }
            else{
                int fragment = partitions.get(i).size-partitions.get(i).process.size;
                accumilated+=fragment;
                partitions.get(i).size-=fragment;
            }
        }

        String pName = partitions.getLast().name;
        int intIndex = pName.length()-"partition".length();
        String numString = pName.substring(pName.length()-intIndex);
        int num = Integer.parseInt(numString);
        partitions.addLast(new Partition("partition"+(++num), accumilated));
    };
}
