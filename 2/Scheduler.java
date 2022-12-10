import java.util.LinkedList;

public abstract class Scheduler{
    protected LinkedList<Process> readyQueue;
    protected int time = 0;
    protected int done = 0;
    protected int actualStartTime = 0;
    protected int initialSize = 0;

    //Default constructor to initialize the readyQueue
    Scheduler(){readyQueue = new LinkedList<>();}

    //Abstract start function that takes processes list and context switch time
    public abstract void startScheduler(LinkedList<Process> processes,int CST);

    //A function to add a queue to the ready queue if the arrival time is met
    public void getInSchedule(LinkedList<Process> processes){
        for(int i=0 ; i<processes.size() ; i++){
            if(processes.get(i).getBurstTime()>0 && processes.get(i).getArrivalTime()<=time){
                readyQueue.addLast(processes.remove(i));
                i--;
            }
        }        
    }
    
    //A function that prints the wait, avg wait, turnAround and avg turnAround of a process list
    public void getInfo(LinkedList<Process> processes){
        double wait=0 , turn=0;
        for(int i=0 ; i<processes.size() ; i++){
            wait+=processes.get(i).getWaitingTime();
            turn+=processes.get(i).getTurnAroundTime();
            System.out.print(processes.get(i).getName()+"-"+"Wait: "+processes.get(i).getWaitingTime()+" TurnAround: "+processes.get(i).getTurnAroundTime()+"\n");
        }
        System.out.print("Average wait time: "+wait/processes.size()+"\nAverage turn around time: "+turn/processes.size());
    }
}