import java.util.Scanner;
public class Main {

    public static void main(String[] args) {
        System.out.print("Select The Desired Process Scheduler\n");
        System.out.print("1-preemptive Shortest- Job First (SJF).\n2-Round Robin (RR).\n3-preemptive Priority Scheduling.\n4-AG Scheduling.");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        
        switch (choice){
            case 1:
                Scheduler preemptiveshortest = new SJFscheduler();
                break;
            case 2:
                Scheduler roundRobin = new RRscheduler();
                break;
            case 3:
                Scheduler preemptivepriority = new Priorityscheduler();
                break;       
            case 4:
                Scheduler agSchedule = new AGscheduler();
                break;

        }
    }
}
