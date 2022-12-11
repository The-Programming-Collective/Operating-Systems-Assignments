import java.util.LinkedList;

public class AGscheduler extends Scheduler {
    private static int currentProcessIndex = 0 , state = 0 , previousState = -1;
    @Override
    public void startScheduler(LinkedList<Process> processes, int CST) {
        initialSize = processes.size();
        //int state = 0, previousState=-1; //0 FCFS, 1 NPP, 2 PSJF
        LinkedList<String> quantumUpdates = new LinkedList<>();
        //do while loop to check and add the first arrival processes
        //handles non 0 arrivals aswell
        //actualStartTime : the arrival time of the first process to execute
        do{
            getInSchedule(processes);
            if(readyQueue.size()==0) time++;
            else actualStartTime = time;
        }while(readyQueue.size()==0);

        //If all processes are done 
        for(; done!=initialSize ; time++){

            //Adds processes to the readyQueue if the arrival time is met
            if(time!=actualStartTime){getInSchedule(processes);}

            switch(state){
                case 0:
                    //FCFS untill 25%
                    work();

                    //if the process finishes all its burstTime c.iv
                    if(readyQueue.get(currentProcessIndex).getBurstTime()==0){
                        processDone(processes, quantumUpdates);
                        break;
                    }

                    //if the process finishes its quantumTime c.i
                    if(readyQueue.get(currentProcessIndex).getRemainingQuantum()==0){
                        quantumDone(processes, quantumUpdates);
                        break;
                    }
                    
                    //if the process didn't finish 25% of its quantumTime break
                    //else move to non-preemptive priority
                    if(! ((int)Math.ceil(readyQueue.get(currentProcessIndex).getQuantum()*0.25)==readyQueue.get(currentProcessIndex).getQuantum()-readyQueue.get(currentProcessIndex).getRemainingQuantum()))
                        break;
                    
                    previousState = state;
                    state = 1;
                    break;

                case 1:
                    //If the previousState wasn't 1 aka first time switching to non-preemptive priority
                    //Check if the currently running process is the highest priority
                    if(previousState != 1){
                        Process p = readyQueue.get(currentProcessIndex);
                        if(! readyQueue.get(getMaxPriority()).equals(p)){
                            readyQueue.remove(p);
                            quantumUpdates.addLast("Process "+p.getName()+" quantum changed from "+p.getQuantum()+" to "+(p.getQuantum()+(int)Math.ceil(p.getRemainingQuantum()/2))+" at "+time);
                            p.setQuantum(p.getQuantum()+(int)Math.ceil(p.getRemainingQuantum()/2));
                            readyQueue.addLast(p);
                            currentProcessIndex = getMaxPriority();
                        }
                        previousState = state;
                    }

                    work();

                    //if the process finishes all its burstTime c.iv
                    if(readyQueue.get(currentProcessIndex).getBurstTime()==0){
                        processDone(processes, quantumUpdates);
                        break;
                    }

                    //if the process finishes its quantumTime c.i
                    if(readyQueue.get(currentProcessIndex).getRemainingQuantum()==0){
                        quantumDone(processes, quantumUpdates);
                        break;
                    }

                    //If the process didn't finish 50% of its quantumTime break
                    //Else move to preemptive shortest job first
                    if(! ((int)Math.ceil(readyQueue.get(currentProcessIndex).getQuantum()*0.50)==readyQueue.get(currentProcessIndex).getQuantum()-readyQueue.get(currentProcessIndex).getRemainingQuantum()))
                        break;

                    previousState = state;
                    state = 2;
                    break;
                    
                case 2:
                    //Preemptive shortest job first case
                    //Will keep executing untill a shorter process arrives
                    //apply case c.iii
                    Process p = readyQueue.get(currentProcessIndex);
                    if(! p.equals(readyQueue.get(getMinJob()))){
                        readyQueue.remove(p);
                        quantumUpdates.addLast("Process "+p.getName()+" quantum changed from "+p.getQuantum()+" to "+(p.getQuantum()+p.getRemainingQuantum())+" at "+time);
                        p.setQuantum(p.getQuantum()+p.getRemainingQuantum());
                        readyQueue.addLast(p);
                        previousState = state;
                        state = 0;
                        time--;
                        break;
                    }

                    currentProcessIndex = getMinJob();
                    work();

                    //if the process finishes all its burstTime c.iv
                    if(readyQueue.get(currentProcessIndex).getBurstTime()==0){
                        processDone(processes, quantumUpdates);
                        break;
                    }

                    //If the process finishes its quantumTime c.i
                    if(readyQueue.get(currentProcessIndex).getRemainingQuantum()==0){
                        quantumDone(processes, quantumUpdates);
                        break;
                    }
                    break;
            }
        }
        //Display quantum updates
        for(int i=0 ; i<quantumUpdates.size() ; i++)
            System.out.print(quantumUpdates.get(i)+"\n");

        //Display final stats;
        getInfo(processes);
    }
    
    //returns the currentProcessIndex of the max priority process
    public int getMaxPriority(){
        int in=0;
        for(int i=1 ; i<readyQueue.size() ; i++)
            if(readyQueue.get(i).getPriority()<readyQueue.get(in).getPriority())
                in = i;
        return in;
    }

    //returns the currentProcessIndex of the shortest job precess
    public int getMinJob(){
        int in=0 ;
        for(int i=1 ; i<readyQueue.size() ; i++)
            if(readyQueue.get(i).getBurstTime()<readyQueue.get(in).getBurstTime())
                in = i;
        return in;
    }

    //process finished its burst time
    public void processDone(LinkedList<Process> processes , LinkedList<String> quantumUpdates){
        Process p = readyQueue.remove(currentProcessIndex);
        p.setTerminationTime(time+1);
        quantumUpdates.addLast("Process "+p.getName()+" quantum changed from "+p.getQuantum()+" to 0 at "+time);
        p.setQuantum(0);
        processes.addLast(p);
        done++;
        currentProcessIndex = 0;
        previousState = state;
        state = 0;
    }

    //process finished its quantumTime
    public void quantumDone(LinkedList<Process> processes , LinkedList<String> quantumUpdates){
        quantumUpdates.addLast("Process "+readyQueue.get(currentProcessIndex).getName()+" quantum changed from "+readyQueue.get(currentProcessIndex).getQuantum()+" to "+(readyQueue.get(currentProcessIndex).getQuantum()+2)+" at "+time);
        readyQueue.get(currentProcessIndex).setQuantum(readyQueue.get(currentProcessIndex).getQuantum()+2);
        readyQueue.addLast(readyQueue.remove(currentProcessIndex));
        previousState = state;
        state = 0;
        currentProcessIndex = 0;
    }

    //Decrement burstTime and remainingQuantumTime and print the timeline
    public void work(){
        readyQueue.get(currentProcessIndex).decrementBurstTime();
        readyQueue.get(currentProcessIndex).decrementRemainingQuantum();
        System.out.print((time)+"-process "+readyQueue.get(currentProcessIndex).getName()+"\n");
    }
}