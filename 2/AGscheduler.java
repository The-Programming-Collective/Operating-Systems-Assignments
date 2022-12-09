import java.util.LinkedList;
import java.util.Collections;
import java.util.Comparator;

public class AGscheduler extends Scheduler {
    protected LinkedList<Process> queue;

    @Override
    public void startScheduler(LinkedList<Process> queue,int CST) {
        int time=0;
        LinkedList<Process> FCFS = new LinkedList<>();
        LinkedList<Process> NPP = new LinkedList<>();
        LinkedList<Process> SJF = new LinkedList<>();
        for(int i=0;i<queue.size();i++){
            FCFS.addLast(queue.get(i));
        }
        Collections.sort(FCFS, Comparator.comparingInt(obj -> obj.getArrivalTime()));
        while(!FCFS.isEmpty()){
            if(FCFS.getFirst().getArrivalTime()==time){
                for(int i=0;i<Math.ceil(FCFS.getFirst().getBurstTime()*0.25);i++){
                    FCFS.getFirst().decrementBurstTime();
                    time++;
                    }
                NPP.addLast(FCFS.pop());
                }
            else if(FCFS.getFirst().getArrivalTime()!=time){
                time++;
            }
        }

    }
}        