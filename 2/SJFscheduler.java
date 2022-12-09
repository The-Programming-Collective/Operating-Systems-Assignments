import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

public class SJFscheduler extends Scheduler {
    @Override
    public void startScheduler(LinkedList<Process> processes,int CST){
        queue = processes;
        initialSize=queue.size();

        do{
            getInSchedule();
            if(temp.size()==0) time++;
            else actualStartTime = time;
        }while(temp.size()==0);

        Collections.sort(temp, Comparator.comparingInt(obj -> obj.getBurstTime()));

        while(done!=initialSize){
            for(int c=0 ;  temp.getFirst().getBurstTime()>0 && !isDone() ; c++,time++){
                if(time!=actualStartTime){getInSchedule();}

                Process p1 = temp.getFirst();
                Collections.sort(temp, Comparator.comparingInt(obj -> obj.getBurstTime()));
                if(! p1.equals(temp.getFirst())){
                    time+=CST;
                }

                System.out.print((time)+"-process "+temp.getFirst().getName()+" "+c+"\n");
                temp.getFirst().decrementBurstTime();

                if(temp.getFirst().getBurstTime()==0){
                    done++;
                    time+=CST;
                    time++;
                    temp.getFirst().setTerminationTime(time);
                    break;
                }
            }
            queue.addLast(temp.pop());
        }
        getInfo(queue);
    }
}