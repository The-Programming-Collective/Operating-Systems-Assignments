import java.util.LinkedList;
import java.util.Scanner;

public class Main{
    public static LinkedList<Partition> partitions = new LinkedList<>();
    public static LinkedList<Process> processes = new LinkedList<>();
    public static Scanner s= new Scanner(System.in);
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";

    public static void main(String[] args) {

        int NOPartitions = getPositiveInt("Enter number of partitions: ");
        for(int i=0 ; i<NOPartitions ; i++)
            partitions.addLast(new Partition(getPositiveInt("Enter Partition "+i+" size: ")));

        int NOProcesses = getPositiveInt("Enter number of processes: ");
        if(NOProcesses>NOPartitions+1)
            System.out.print(ANSI_RED+"There will always "+(NOProcesses-(NOPartitions+1))+" processes that can't be allocated."+ANSI_RESET+"\n");
        for(int i=0 ; i<NOProcesses ; i++)
            processes.addLast(new Process(getPositiveInt("Enter Process "+i+" size: ")));
        
        int choice;
        AllocationPolicy policy;
        do{
            choice = getPositiveInt("Select a policy to apply: \n1-First fit.\n2-Best fit.\n3-Worst fit.\n: ");
            switch(choice){
                case 1 : 
                    policy = new FirstFitPolicy();
                    policy.start(partitions, processes);
                    break;
                case 2 :
                    policy = new BestFitPolicy();
                    policy.start(partitions, processes);
                    break;
                default :
                    policy = new WorstFitPolicy();
                    policy.start(partitions, processes);
                    break; 
            }
        }while(choice>3);
        
        printPartitions("Before compaction");
        choice = getPositiveInt("Do you want to compact? 1.yes 2.no : ");
        if(choice==1){
            policy.compaction(partitions);
            policy.start(partitions, processes);
            printPartitions("After compaction");
        }

        s.close();
    }


    //A function that displays a text and gets a positive int from user
    public static int getPositiveInt(String args){
        int num;
        do{
            try{
                System.out.print(args);
                num = Integer.parseInt(s.nextLine());
                if(num<=0)
                    throw new Exception();
                break;
            }catch(Exception e){System.out.print("Invalid input.\n");}
        }while(true);
        return num;
    }

    //A function to print current partitions,  processes status 
    private static void printPartitions(String state){
        System.out.print("================"+state+"================\n");
        for(int i=0 ; i<partitions.size() ; i++){
            if(partitions.get(i).process==null)
                System.out.print(partitions.get(i).name+" ("+partitions.get(i).size+" KB) => External fragment\n");
            
            else
                System.out.print(partitions.get(i).name+ " ("+partitions.get(i).process.size+" KB) => "+partitions.get(i).process.name+"\n");
        }
        for(int i=0 ; i<processes.size() ; i++){
            if(i==0)
                System.out.print("\n");
            System.out.print(processes.get(i).name+" can't be allocated\n");
        }
    }
}