import java.util.LinkedList;

public class RRscheduler extends Scheduler {
    @Override
    public void startScheduler(LinkedList<Process> processes,int CST){
        initialSize = processes.size();
        
        //do while loop to check and add the first arrival processes
        //handles non 0 arrivals aswell
        //actualStartTime : the arrival time of the first process to execute
        do{
            getInSchedule(processes);
            if(readyQueue.size()==0) time++;
            else actualStartTime = time;
        }while(readyQueue.size()==0);

        while(done!=initialSize){

            //checks if the process is done or it finished it share of quantum time
            for(int c=0 ; c<readyQueue.getFirst().getQuantum() && readyQueue.getFirst().getBurstTime()>0 ; c++,time++){
                
                //Adds processes to the readyQueue if the arrival time is met
                if(time!=actualStartTime){getInSchedule(processes);}
                
                System.out.print((time)+"-process "+readyQueue.getFirst().getName()+" "+c+"\n");
                readyQueue.getFirst().decrementBurstTime();

                //If the process is finished set its termination time
                //and increment done
                if(readyQueue.getFirst().getBurstTime()==0){
                    done++;
                    time++;
                    readyQueue.getFirst().setTerminationTime(time+CST);
                    break;
                }
            }
            for(int i=0 ; i<CST ; i++){System.out.print((time++)+"-Context switch"+"\n");}

            //Checks if a process arrived while context switching
            if(time!=actualStartTime){getInSchedule(processes);}
            
            //If the process is done aka burstTime = 0 : move to processes list
            //If the process finished its quantumTime and still got work to do : move to the end of readyQueue
            if(readyQueue.getFirst().getBurstTime()==0)
                processes.addLast(readyQueue.pop());
            else
                readyQueue.addLast(readyQueue.pop());
        }
        //Display final stats;
        getInfo(processes);
    }
}