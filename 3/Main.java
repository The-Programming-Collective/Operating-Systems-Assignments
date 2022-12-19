import java.util.LinkedList;

public class Main{
    public static void main(String[] args) {
        

        Process p1 = new Process("Process1",15);
        Process p2 = new Process("Process2",90);
        Process p3 = new Process("Process3",30);
        Process p4 = new Process("Process4",100);
        LinkedList<Process> processes = new LinkedList<>();
        processes.addLast(p1);
        processes.addLast(p2);
        processes.addLast(p3);
        processes.addLast(p4);


        Partition pa0 = new Partition("Partition0", 90);
        Partition pa1 = new Partition("Partition1", 20);
        Partition pa2 = new Partition("Partition2", 5);
        Partition pa3 = new Partition("Partition3", 30);
        Partition pa4 = new Partition("Partition4", 120);
        Partition pa5 = new Partition("Partition5", 80);
        LinkedList<Partition> partitions = new LinkedList<>();
        partitions.addLast(pa0);
        partitions.addLast(pa1);
        partitions.addLast(pa2);
        partitions.addLast(pa3);
        partitions.addLast(pa4);
        partitions.addLast(pa5);

        AllocationPolicy bestFit = new BestFitPolicy();
        bestFit.start(partitions, processes);

        // AllocationPolicy worstFit = new WorstFitPolicy();
        // worstFit.start(partitions, processes);

        for(int i=0 ; i<partitions.size() ; i++){
            if(partitions.get(i).process==null)
                System.out.print(partitions.get(i).name+" External fragment.\n");
            
            else
                System.out.print(partitions.get(i).name+ " has "+partitions.get(i).process.name+".\n");
        }
    }
}