import java.util.LinkedList;
import java.util.Scanner;

public class Main {
        private static LinkedList<Process> queue = new LinkedList<Process>();
        private static Scanner scanner = new Scanner(System.in);
        private static int NOP,CST;
        public static void main(String[] args) {
        Process a1= new Process("p1", 0, 4, 0, 3);
        Process a2= new Process("p2", 1, 8, 6, 3);
        Process a3= new Process("p3", 3, 2, 11, 3);
        Process a4= new Process("p4", 10, 6, 4, 3);
        Process a5= new Process("p5", 12, 5, 5, 3);

        // Process a1= new Process("p1", 0, 17, 4, 7);
        // Process a2= new Process("p2", 2, 6, 7, 9);
        // Process a3= new Process("p3", 5, 11, 3, 4);
        // Process a4= new Process("p4", 15, 4, 6, 6);


        // Process a1= new Process("p1", 0, 5, 5, 7);
        // Process a2= new Process("p2", 0, 2, 4, 9);
        // Process a3= new Process("p3", 5, 8, 5, 4);
        // Process a4= new Process("p4", 10, 5, 1, 6);
        // Process a5= new Process("p5", 3, 7, 0, 3);

        LinkedList<Process> queue = new LinkedList<>();
        queue.addLast(a1);
        queue.addLast(a2);
        queue.addLast(a3);
        queue.addLast(a4);
        queue.addLast(a5);

        Scheduler rr = new RRscheduler();
        rr.startScheduler(new LinkedList<Process>(queue), 1);

        // Scheduler ps = new Priorityscheduler();
        // ps.startScheduler(new LinkedList<Process>(queue), 0);

        // Scheduler SJF = new SJFscheduler();
        // SJF.startScheduler(new LinkedList<Process>(queue), 0);

        // Scheduler AG = new AGscheduler();
        // AG.startScheduler(new LinkedList<Process>(queue),0);
    }
}