import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
//import java.util.Scanner;

public class Main {
    //    private static LinkedList<Process> queue = new LinkedList<Process>();
    //     public static void main(String[] args) {

    //     System.out.print("Select The Desired Process Scheduler\n");
    //     System.out.print("1-preemptive Shortest- Job First (SJF).\n2-Round Robin (RR).\n3-preemptive Priority Scheduling.\n4-AG Scheduling.\n:");
    //     Scanner scanner = new Scanner(System.in);
    //     int choice=99;
        
    //     while(true){
    //         try{choice = Integer.parseInt(scanner.nextLine());
    //             if(choice>=1 && choice<=4)
    //                 break;
    //             System.out.print("out of range value.\n");
    //         }catch(Exception e){System.out.print("Enter an int from 1-4.\n");}
    //     }

    //     int NOP,CST;

    //     while(true){
    //         try{
    //             System.out.print("Enter number of processes: ");
    //             NOP = Integer.parseInt(scanner.nextLine());
    //             System.out.print("Enter context switch time: ");
    //             CST = Integer.parseInt(scanner.nextLine());
    //             if(NOP<0 || CST<0){throw new Exception();}
    //             break;
    //         }catch(Exception e){System.out.print("Error, enter valid ints.");}
    //     }
        
    //     for(int i=0 ; i<NOP ; i++){
    //         try{
                    //TODO add a check for no 0 arrival time process 
    //             System.out.print("Enter process "+(i+1)+" name, arrival time, priority, burst time, quantum: ");
    //             String name = scanner.next();
    //             int arrivalTime = Integer.parseInt(scanner.next());
    //             int priority = Integer.parseInt(scanner.next());
    //             int burstTime = Integer.parseInt(scanner.next());
    //             int quantum = Integer.parseInt(scanner.next());
    //             queue.addLast(new Process(name, arrivalTime, priority, burstTime, quantum));}
    //         catch(Exception e){System.out.print("Error, try again.\n");i--;}
    //     };

    //     Process a , b = new Process(null, choice, NOP, CST, CST);
    //     //queue.sort((a.getArrivalTime(),b.getArrivalTime())-> (a>b));

    //     Collections.sort(queue, Comparator.comparingInt(obj -> obj.getArrivalTime()));
        
    //     switch (choice){
    //         case 1:
    //             Scheduler preemptiveShortest = new SJFscheduler();
    //             preemptiveShortest.startScheduler(queue,CST);
    //             break;
    //         case 2:
    //             Scheduler roundRobin = new RRscheduler();
    //             roundRobin.startScheduler(queue,CST);
    //             break;
    //         case 3:
    //             Scheduler preemptivePriority = new Priorityscheduler();
    //             preemptivePriority.startScheduler(queue,CST);
    //             break;       
    //         case 4:
    //             Scheduler agSchedule = new AGscheduler();
    //             agSchedule.startScheduler(queue,CST);
    //             break;
    //         default:
    //             System.out.print("Error.\n");
    //             break;
    //     }
    //     scanner.close();
    // }

    public static void main(String[] args) {
        Process a1= new Process("a1", 0, 5, 5, 5);
        Process a2= new Process("a2", 0, 4, 2, 5);
        Process a3= new Process("a3", 5, 5, 8, 5);
        Process a4= new Process("a4", 10, 1, 5, 5);
        Process a5= new Process("a5", 3, 0, 7, 5);

        LinkedList<Process> queue = new LinkedList<>();
        queue.addLast(a1);
        queue.addLast(a2);
        queue.addLast(a3);
        queue.addLast(a4);
        queue.addLast(a5);

        Collections.sort(queue, Comparator.comparingInt(obj -> obj.getArrivalTime()));

        // Collections.sort(queue, new Comparator<Process>() {     //sorts by arrival and priority
        //     @Override
        //     public int compare(Process o1, Process o2) {
        //         if(o1.getArrivalTime()==o2.getArrivalTime())
        //             return o1.getPriority()-o2.getPriority();
        //         return  o1.getArrivalTime()-o2.getArrivalTime();
        //     }
        // });

        // for(int i=0 ; i<queue.size() ; i++){
        //     System.out.print(queue.get(i).getName()+"\n");
        // }

        // Scheduler rr = new RRscheduler();
        // rr.startScheduler(queue, 0);

        // Scheduler ps = new Priorityscheduler();
        // ps.startScheduler(queue, 0);

        Scheduler SJF = new SJFscheduler();
        SJF.startScheduler(queue, 0);
    }
}
