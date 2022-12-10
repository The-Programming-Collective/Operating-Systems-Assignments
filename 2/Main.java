import java.util.DuplicateFormatFlagsException;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {
        private static LinkedList<Process> queue = new LinkedList<Process>();
        private static Scanner scanner = new Scanner(System.in);
        private static int NOP,CST;
        public static void main(String[] args) {

        while(true){
            try{
                System.out.print("Enter number of processes: ");
                NOP = Integer.parseInt(scanner.nextLine());
                System.out.print("Enter context switch time: ");
                CST = Integer.parseInt(scanner.nextLine());
                if(NOP<0 || CST<0){throw new Exception();}
                break;
            }catch(Exception e){System.out.print("Error, enter valid ints.\n");}
        }
        
        addProcesses();

        System.out.print("\nCurrent context switch time: "+CST+"\nSelect The Desired Process Scheduler:-\n");
        System.out.print("1-Preemptive Shortest-Job First (SJF).\n2-Round Robin (RR).\n3-Preemptive Priority Scheduling.\n4-AG Scheduling.\n5-Change Context switch time.\n6-Add processes.\n7-Exit.\n:");
        int choice;
        boolean isOn = true;

        while(isOn){
            try{choice = Integer.parseInt(scanner.nextLine());}
            catch(Exception e){choice=-1;}
        
            switch (choice){
                case 1:
                    Scheduler preemptiveShortest = new SJFscheduler();
                    preemptiveShortest.startScheduler(new LinkedList<Process>(queue),CST);
                    break;
                case 2:
                    Scheduler roundRobin = new RRscheduler();
                    roundRobin.startScheduler(new LinkedList<Process>(queue),CST);
                    break;
                case 3:
                    Scheduler preemptivePriority = new Priorityscheduler();
                    preemptivePriority.startScheduler(new LinkedList<Process>(queue),CST);
                    break;       
                case 4:
                    Scheduler agSchedule = new AGscheduler();
                    agSchedule.startScheduler(new LinkedList<Process>(queue),CST);
                    break;
                case 5:
                    while(true){
                        try{
                            System.out.print("Enter context switch time: ");
                            CST = Integer.parseInt(scanner.nextLine());
                            if(CST<0){throw new Exception();}
                            break;
                        }catch(Exception e){System.out.print("Error, enter valid int.\n");}
                    }
                    break;
                case 6:
                    while(true){
                        try{
                            System.out.print("Enter number of processes: ");
                            NOP = Integer.parseInt(scanner.nextLine());
                            if(NOP<0){throw new Exception();}
                            break;
                        }catch(Exception e){System.out.print("Error, enter valid int.\n");}
                    }
                    addProcesses();
                    break;
                case 7:
                    isOn=!isOn;
                    break;
                default:
                    System.out.print("Invalid selection.\n");
                    break;
            }
        }
        scanner.close();
    }

    public static void addProcesses(){
        for(int i=0 ; i<NOP ; i++){
            try{ 
                System.out.print("Enter process "+(i+1)+" name, arrival time, burst time, priority,  quantum: ");
                String name = scanner.next();
                int arrivalTime = Integer.parseInt(scanner.next());
                int burstTime = Integer.parseInt(scanner.next());
                int priority = Integer.parseInt(scanner.next());
                int quantum = Integer.parseInt(scanner.next());

                if(arrivalTime<0 || priority<0 || burstTime<=0 || quantum<=0){throw new Exception();}

                Process p = new Process(name, arrivalTime, burstTime, priority, quantum);
                if(queue.contains(p)){throw new DuplicateFormatFlagsException(name);}

                queue.addLast(p);
            }
            catch(DuplicateFormatFlagsException e){System.out.print("Error, process "+e.getMessage()+" already exists.\n");i--;}
            catch(Exception e){System.out.print("Error, this process is not accepted.\n");i--;}
        };
    }
    // public static void main(String[] args) {
    //     // Process a1= new Process("p1", 0, 4, 17, 7);
    //     // Process a2= new Process("p2", 2, 7, 6, 9);
    //     // Process a3= new Process("p3", 5, 3, 11, 4);
    //     // Process a4= new Process("p4", 15, 6, 4, 6);
    //     //Process a5= new Process("p5", 12, 0, 5, 3);

    //     Process a1= new Process("p1", 0, 17, 4, 7);
    //     Process a2= new Process("p2", 2, 6, 7, 9);
    //     Process a3= new Process("p3", 5, 11, 3, 4);
    //     Process a4= new Process("p4", 15, 4, 6, 6);


    //     // Process a1= new Process("p1", 0, 5, 5, 7);
    //     // Process a2= new Process("p2", 0, 2, 4, 9);
    //     // Process a3= new Process("p3", 5, 8, 5, 4);
    //     // Process a4= new Process("p4", 10, 5, 1, 6);
    //     // Process a5= new Process("p5", 3, 7, 0, 3);

    //     LinkedList<Process> queue = new LinkedList<>();
    //     queue.addLast(a1);
    //     queue.addLast(a2);
    //     queue.addLast(a3);
    //     queue.addLast(a4);
    //     //queue.addLast(a5);

    //     // Scheduler rr = new RRscheduler();
    //     // rr.startScheduler(new LinkedList<Process>(queue), 1);

    //     // Scheduler ps = new Priorityscheduler();
    //     // ps.startScheduler(new LinkedList<Process>(queue), 0);

    //     // Scheduler SJF = new SJFscheduler();
    //     // SJF.startScheduler(new LinkedList<Process>(queue), 0);

    //     Scheduler AG = new AGscheduler();
    //     AG.startScheduler(new LinkedList<Process>(queue),0);
    // }
}

//Collections.sort(queue, Comparator.comparingInt(obj -> obj.getArrivalTime()));

// Collections.sort(queue, new Comparator<Process>() {     //sorts by arrival and priority
//     @Override
//     public int compare(Process o1, Process o2) {
//         if(o1.getArrivalTime()==o2.getArrivalTime())
//             return o1.getPriority()-o2.getPriority();
//         return  o1.getArrivalTime()-o2.getArrivalTime();
//     }
// })