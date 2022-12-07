import java.util.LinkedList;
import java.util.Scanner;
public class Main {
        public static void main(String[] args) {
        LinkedList<Process> queue = new LinkedList<Process>();

        System.out.print("Select The Desired Process Scheduler\n");
        System.out.print("1-preemptive Shortest- Job First (SJF).\n2-Round Robin (RR).\n3-preemptive Priority Scheduling.\n4-AG Scheduling.\n:");
        Scanner scanner = new Scanner(System.in);
        int choice=99;
        
        while(true){
            try{choice = Integer.parseInt(scanner.nextLine());
                if(choice>=1 && choice<=4)
                    break;
                System.out.print("out of range value.\n");
            }catch(Exception e){System.out.print("Enter an int from 1-4.\n");}
        }

        System.out.print("Enter number of processes: ");
        int NOP = Integer.parseInt(scanner.nextLine());
        for(int i=0 ; i<NOP ; i++){
            try{
                System.out.print("Enter process "+(i+1)+" name, arrival time, priority, burst time, quantum: ");
                String name = scanner.next();
                int arrivalTime = Integer.parseInt(scanner.next());
                int priority = Integer.parseInt(scanner.next());
                int burstTime = Integer.parseInt(scanner.next());
                int quantum = Integer.parseInt(scanner.next());
                queue.addLast(new Process(name, arrivalTime, priority, burstTime, quantum));}
            catch(Exception e){System.out.print("Error, try again.\n");i--;}
        };
        
        switch (choice){
            case 1:
                Scheduler preemptiveShortest = new SJFscheduler();
                preemptiveShortest.startScheduler(queue);
                break;
            case 2:
                Scheduler roundRobin = new RRscheduler();
                roundRobin.startScheduler(queue);
                break;
            case 3:
                Scheduler preemptivePriority = new Priorityscheduler();
                preemptivePriority.startScheduler(queue);
                break;       
            case 4:
                Scheduler agSchedule = new AGscheduler();
                agSchedule.startScheduler(queue);
                break;
            default:
                System.out.print("Error.\n");
                break;
        }
        scanner.close();
    }
}
