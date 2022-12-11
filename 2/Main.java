import java.util.DuplicateFormatFlagsException;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {
        private static LinkedList<Process> queue = new LinkedList<Process>();
        private static Scanner scanner = new Scanner(System.in);
        private static int NOP,CST;
        public static void main(String[] args) {

        int choice;
        boolean isOn = true;

        while(isOn){
            System.out.print("\n=============================\n");
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
            queue.clear();
            addProcesses();
    
            choice = -1;
            
            System.out.print("\nCurrent context switch time: "+CST+"\nCurrent processes count: "+queue.size()+"\nSelect The Desired Process Scheduler:-\n");
            System.out.print("1-Preemptive Shortest-Job First (SJF).\n2-Round Robin (RR).\n3-Preemptive Priority Scheduling.\n4-AG Scheduling.\n5-Change Context switch time.\n6-Add processes.\n7-Exit.\n:");
            try{choice = Integer.parseInt(scanner.nextLine());}
            catch(Exception e){}
            System.out.print("\n=============================\n");
            switch (choice){
                case 1:
                    Scheduler preemptiveShortest = new SJFscheduler();
                    preemptiveShortest.startScheduler(queue,CST);
                    break;

                case 2:
                    int quantumTime = -1;

                    while(true){
                        try{
                            System.out.print("Enter quantum time: ");
                            quantumTime = Integer.parseInt(scanner.nextLine());
                            if(quantumTime<=0){throw new Exception();}
                                break;
                        }catch(Exception e){System.out.print("Error, enter valid int.\n");}
                    }

                    for(int i=0 ; i<queue.size() ; i++){
                        queue.get(i).setQuantum(quantumTime);
                    }

                    Scheduler roundRobin = new RRscheduler();
                    roundRobin.startScheduler(queue,CST);
                    break;

                case 3:
                    Scheduler preemptivePriority = new Priorityscheduler();
                    preemptivePriority.startScheduler(queue,CST);
                    break; 

                case 4:
                    Scheduler agSchedule = new AGscheduler();
                    agSchedule.startScheduler(queue,CST);
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
                String[] output = scanner.nextLine().split(" ");

                if(output.length!=5){throw new Exception();}

                String name = output[0];
                int arrivalTime = Integer.parseInt(output[1]);
                int burstTime = Integer.parseInt(output[2]);
                int priority = Integer.parseInt(output[3]);
                int quantum = Integer.parseInt(output[4]);

                if(arrivalTime<0 || priority<0 || burstTime<=0 || quantum<=0){throw new Exception();}

                Process p = new Process(name, arrivalTime, burstTime, priority, quantum);
                if(queue.contains(p)){throw new DuplicateFormatFlagsException(name);}

                queue.addLast(p);
            }
            catch(DuplicateFormatFlagsException e){System.out.print("Error, process "+e.getMessage()+" already exists.\n");i--;}
            catch(Exception e){System.out.print("Error, this process is not accepted.\n");i--;}
        };
    }
}