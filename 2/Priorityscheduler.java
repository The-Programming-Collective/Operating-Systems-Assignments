import java.util.*;

public class Priorityscheduler extends Scheduler {
    protected LinkedList<Process> queue;

    public void ageQueue(LinkedList<Process> queue, Process a)
    {
        for(int i=0;i<queue.size();i++){
            if(queue.get(i).equals(a))
                continue;
            queue.get(i).incrementPriority();
        }
    }
    public Process getHighestProcess(LinkedList<Process> queue)
    {
        for(int i=0;i<queue.size();i++){
            if(queue.get(i).getBurstTime()>0)
            {
                return queue.get(i);
            }
        }
        return null;
    }

    @Override
    public void startScheduler(LinkedList<Process> queue,int CST){
        CST=0;
        int time = 0 , done = 0;
        Collections.sort(queue, Comparator.comparingInt(obj -> obj.getPriority()));
        for(int i=0;done!=queue.size();i++)
        {
            if(i==queue.size()) 
                i=0;
            if(queue.get(i).getArrivalTime()>time)
                continue;

            for(int j=0;queue.get(i).getBurstTime()>0;j++){
                System.out.print(time+"-process "+queue.get(i).getName()+" "+j+"\n");
                queue.get(i).decrementBurstTime();
                time++;
                if(queue.get(i).getBurstTime()==0){
                    queue.get(i).setTerminationTime(time);
                    done++;
                    break;
                }
                if(!queue.get(i).equals(getHighestProcess(queue)) && time >= queue.getFirst().getArrivalTime())
                {
                    System.out.println(queue.get(i).getName());
                    Collections.sort(queue, Comparator.comparingInt(obj -> obj.getPriority()));
                    break;
                }
            }
            ageQueue(queue,queue.get(i));
        }
        getInfo(queue);
    }
}