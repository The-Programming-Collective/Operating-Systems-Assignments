import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;



public class SJFscheduler extends Scheduler {
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

        //sort the list according to burstTime
        Collections.sort(readyQueue, Comparator.comparingInt(obj -> obj.getBurstTime()));

        while(done!=initialSize){

            for(;  readyQueue.getFirst().getBurstTime()>0 ; time++){

                //Adds processes to the readyQueue if the arrival time is met
                if(time!=actualStartTime){getInSchedule(processes);}

                //Gets the first process and the sorts according to the burst time
                //If the first process changed : a shorter process arrived
                //Switch to the new process and add context switch time
                //Else continue working with current process
                Process p = readyQueue.getFirst();
                Collections.sort(readyQueue, Comparator.comparingInt(obj -> obj.getBurstTime()));
                if(! p.equals(readyQueue.getFirst())){
                    for(int x=1 ; x<readyQueue.size() ; x++){
                        readyQueue.get(x).incrementPriority();
                    }
                }

                System.out.print((time)+"-process "+readyQueue.getFirst().getName()+"\n");
                readyQueue.getFirst().decrementBurstTime();

                //If the process is finished set its termination time
                //and increment done
                if(readyQueue.getFirst().getBurstTime()==0){
                    done++;
                    time++;
                    for(int x=1 ; x<readyQueue.size() ; x++){
                        readyQueue.get(x).incrementPriority();
                    }
                    readyQueue.getFirst().setTerminationTime(time);
                    break;
                }
            }
            //If the process is done aka burstTime = 0 : move to processes list
            if(readyQueue.getFirst().getBurstTime()==0)
                processes.addLast(readyQueue.pop());
        }
        //Display final stats;
        getInfo(processes);
    }
}