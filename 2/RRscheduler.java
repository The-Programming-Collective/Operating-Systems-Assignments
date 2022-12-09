import java.util.LinkedList;

public class RRscheduler extends Scheduler {
    @Override
    public void startScheduler(LinkedList<Process> processes,int CST){
        queue = processes;
        int actualStartTime=0 , initialSize = queue.size();
        
        do{
            getInSchedule();
            if(temp.size()==0) time++;
            else actualStartTime = time;
        }while(temp.size()==0);

        while(done!=initialSize){
            for(int c=0 ; c<temp.getFirst().getQuantum() && temp.getFirst().getBurstTime()>0 ; c++,time++){
                if(time!=actualStartTime){
                    getInSchedule();
                    
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