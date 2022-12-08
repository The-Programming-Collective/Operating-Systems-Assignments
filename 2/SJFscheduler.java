import java.util.LinkedList;

public class SJFscheduler extends Scheduler {
    protected LinkedList<Process> queue;
    
    @Override
    public void startScheduler(LinkedList<Process> queue,int CST){
        int time=0 , done=0;
        for(int i=0 ; done!=queue.size() ; i++,time+=CST){
            
        }
    }
}
