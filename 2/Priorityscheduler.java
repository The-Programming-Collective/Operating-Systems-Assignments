import java.util.*;

public class Priorityscheduler extends Scheduler {

    //Function to age all processes in ready queue waiting to run
    public void ageQueue(LinkedList<Process> queue, Process a)
    {
        for(int i=0;i<queue.size();i++){
            if(queue.get(i).equals(a))
                continue;
            queue.get(i).incrementPriority();
        }
    }

    //Function to return the process with the highest priority in the ready queue
    public Process getHighestProcess(LinkedList<Process> queue)
    {
        Process tmp = queue.get(0);
        for(int i=1;i<queue.size();i++){
            if(queue.get(i).getPriority()<tmp.getPriority())
            {
                tmp = queue.get(i);
            }
        }
        return tmp;
    }

    //Function that starts processing the schedule
    @Override
    public void startScheduler(LinkedList<Process> processes,int CST){
        initialSize = processes.size();

        //do while loop to check and add the first arrival processes
        //handles non 0 arrivals as well
        //actualStartTime : the arrival time of the first process to execute
        do{
            getInSchedule(processes);
            if(readyQueue.size()==0) time++;
            else actualStartTime = time;
        }while(readyQueue.size()==0);

        //sort the ready queue by priority
        Collections.sort(readyQueue, Comparator.comparingInt(obj -> obj.getPriority()));

        //loop on each of the processes in the queue until all processes are complete
        while(done!=initialSize)
        {   
            
            //loop on each individual process
            for(int j=0;readyQueue.getFirst().getBurstTime()>0 && done!=initialSize ;j++)
            {

                //decrement burst time and output process name
                System.out.print(time+"-process "+readyQueue.getFirst().getName()+" "+j+"\n");
                readyQueue.getFirst().decrementBurstTime();
                time++;
                getInSchedule(processes);
                
                //if the process is complete calls process exit
                if(readyQueue.getFirst().getBurstTime()==0)
                {
                    readyQueue.getFirst().setTerminationTime(time);
                    processes.addLast(readyQueue.pop());
                    System.out.print("-Process exit-"+"\n");
                    done++;
                    Collections.sort(readyQueue, Comparator.comparingInt(obj -> obj.getPriority()));
                    break;
                }

                //if theres a process with a higher priority in the ready queue interrupts current running process 
                //then breaks to allow another process with higher priority to run
                if(!readyQueue.getFirst().equals(getHighestProcess(readyQueue)))
                {
                    System.out.print("-Process interrupt-"+"\n");
                    getInSchedule(processes);
                    Collections.sort(readyQueue, Comparator.comparingInt(obj -> obj.getPriority()));
                    break;
                }
            }

            //calls to age the queue
            if(readyQueue.size()!=0)
                ageQueue(readyQueue,readyQueue.getFirst());
        }
        //Display final stats;
        getInfo(processes);
    }
}