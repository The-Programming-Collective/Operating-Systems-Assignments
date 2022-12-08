import java.util.LinkedList;

public class RRscheduler extends Scheduler {
    protected LinkedList<Process> queue;
    @Override
    public void startScheduler(LinkedList<Process> queue,int CST){
        int time=0 , done=0;
        for(int i=0 ; done!=queue.size() ; i++){
            if(i==queue.size()) 
                i=0;
            if(queue.get(i).getArrivalTime()>time) 
                continue;
            if(time>0 && queue.get(i).getArrivalTime()==time)
                continue;

            for(int c=0 ; c<queue.get(i).getQuantum() && queue.get(i).getBurstTime()>0 ; c++){
                System.out.print((time)+"-process "+queue.get(i).getName()+" "+c+"\n");
                queue.get(i).decrementBurstTime();
                
                if(queue.get(i).getBurstTime()==0){
                    queue.get(i).setTerminationTime(time+1);
                    done++;
                    time+=CST;
                    break;
                }
                time++;
            }
            System.out.print("\n");
        }
        getInfo(queue);
    }
}
