import java.util.LinkedList;

public abstract class Scheduler{
    protected LinkedList<Process> queue;
    protected LinkedList<Process> temp;
    protected int time = 0;
    protected int done = 0;

    Scheduler(){
        temp = new LinkedList<>();
        queue = new LinkedList<>();
    }

    public abstract void startScheduler(LinkedList<Process> queue,int CST);

    public void getInSchedule(){
        for(int i=0 ; i<queue.size() ; i++){
            if(queue.get(i).getBurstTime()>0 && queue.get(i).getArrivalTime()<=time){
                temp.addLast(queue.remove(i));
                i--;
            }
        }
            
    }
    
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