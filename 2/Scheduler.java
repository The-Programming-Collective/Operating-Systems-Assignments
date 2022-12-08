import java.util.LinkedList;

public abstract class Scheduler{
    public abstract void startScheduler(LinkedList<Process> queue,int CST);
    public void getInfo(LinkedList<Process> queue){
        double wait=0 , turn=0;
        for(int i=0 ; i<queue.size() ; i++){
            wait+=queue.get(i).getWaitingTime();
            turn+=queue.get(i).getTurnAroundTime();
            System.out.print(queue.get(i).getName()+"-"+"Wait: "+queue.get(i).getWaitingTime()+" TurnAround: "+queue.get(i).getTurnAroundTime()+"\n");
        }
        System.out.print("Average wait time: "+wait/queue.size()+"\nAverage turn around time: "+turn/queue.size());
    }
}