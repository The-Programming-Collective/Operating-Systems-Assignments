import java.util.LinkedList;

public class AGscheduler extends Scheduler {
    @Override
    public void startScheduler(LinkedList<Process> processes, int CST) {
        initialSize = processes.size();
        int index = 0; //the index of the currently running process
        int state = 0, previousState=-1; //0 FCFS, 1 NPP, 2 PSJF
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
                    readyQueue.get(index).decrementBurstTime();
                    readyQueue.get(index).decrementRemainingQuantum();
                    System.out.print((time)+"-process "+readyQueue.get(index).getName()+"\n");

                    //if the process finishes all its burstTime c.iv
                    if(readyQueue.get(index).getBurstTime()==0){
                        Process p = readyQueue.remove(index);
                        p.setTerminationTime(time);
                        quantumUpdates.addLast("Process "+p.getName()+" quantum changed from "+p.getQuantum()+" to 0 at "+time);
                        p.setQuantum(0);
                        processes.addLast(p);
                        done++;
                        index=0;
                        break;
                    }

                    //if the process finishes its quantumTime c.i
                    if(readyQueue.get(index).getRemainingQuantum()==0){
                        quantumUpdates.addLast("Process "+readyQueue.get(index).getName()+" quantum changed from "+readyQueue.get(index).getQuantum()+" to "+(readyQueue.get(index).getQuantum()+2)+" at "+time);
                        readyQueue.get(index).setQuantum(readyQueue.get(index).getQuantum()+2);
                        readyQueue.addLast(readyQueue.remove(index));
                    }
                    
                    //if the process didn't finish 25% of its quantumTime break
                    //else move to non-preemptive priority
                    if(! ((int)Math.ceil(readyQueue.get(index).getQuantum()*0.25)==readyQueue.get(index).getQuantum()-readyQueue.get(index).getRemainingQuantum()))
                        break;
                    
                    previousState = state;
                    state = 1;
                    break;
                case 1:
                    //If the previousState wasn't 1 aka first time switching to non-preemptive priority
                    //Check if the currently running process is the highest priority
                    if(previousState != 1){
                        Process p = readyQueue.get(index);
                        if(! readyQueue.get(getMaxPriority()).equals(p)){
                            readyQueue.remove(p);
                            quantumUpdates.addLast("Process "+p.getName()+" quantum changed from "+p.getQuantum()+" to "+(p.getQuantum()+(int)Math.ceil(p.getRemainingQuantum()/2))+" at "+time);
                            p.setQuantum(p.getQuantum()+(int)Math.ceil(p.getRemainingQuantum()/2));
                            readyQueue.addLast(p);
                            index = getMaxPriority();
                        }
                        previousState = state;
                    }

                    readyQueue.get(index).decrementBurstTime();
                    readyQueue.get(index).decrementRemainingQuantum();
                    System.out.print((time)+"-process "+readyQueue.get(index).getName()+"\n");

                    //if the process finishes all its burstTime c.iv
                    if(readyQueue.get(index).getBurstTime()==0){
                        Process p = readyQueue.remove(index);
                        p.setTerminationTime(time);
                        quantumUpdates.addLast("Process "+p.getName()+" quantum changed from "+p.getQuantum()+" to 0 at "+time);
                        p.setQuantum(0);
                        processes.addLast(p);
                        done++;
                        index = 0;
                        previousState = state;
                        state = 0;
                        break;
                    }

                    //If the process didn't finish 50% of its quantumTime break
                    //Else move to preemptive shortest job first
                    if(! ((int)Math.ceil(readyQueue.get(index).getQuantum()*0.50)==readyQueue.get(index).getQuantum()-readyQueue.get(index).getRemainingQuantum()))
                        break;

                    previousState = state;
                    state = 2;
                    break;
                case 2:
                    //Preemptive shortest job first case
                    //Will keep executing untill a shorter process arrives
                    //apply case c.iii
                    Process p = readyQueue.get(index);
                    if(! p.equals(readyQueue.get(getMinJob()))){
                        readyQueue.remove(p);
                        quantumUpdates.addLast("Process "+p.getName()+" quantum changed from "+p.getQuantum()+" to "+(p.getQuantum()+p.getRemainingQuantum())+" at "+time);
                        p.setQuantum(p.getQuantum()+p.getRemainingQuantum());
                        readyQueue.addLast(p);
                        previousState = state;
                        state = 0;
                    }
                    //If the process finishes its quantumTime c.i
                    if(p.getRemainingQuantum()==0){
                        readyQueue.remove(p);
                        quantumUpdates.addLast("Process "+p.getName()+" quantum changed from "+p.getQuantum()+" to "+(p.getQuantum()+2)+" at "+time);
                        p.setQuantum(p.getQuantum()+2);
                        readyQueue.addLast(p);
                        previousState = state;
                        state = 0;
                    }

                    index = getMinJob();
                    readyQueue.get(index).decrementBurstTime();
                    readyQueue.get(index).decrementRemainingQuantum();
                    System.out.print((time)+"-process "+readyQueue.get(index).getName()+"\n");

                    //if the process finishes all its burstTime c.iv
                    if(readyQueue.get(index).getBurstTime()==0){
                        p = readyQueue.remove(index);
                        p.setTerminationTime(time);
                        quantumUpdates.addLast("Process "+p.getName()+" quantum changed from "+p.getQuantum()+" to 0 at "+time);
                        p.setQuantum(0);
                        processes.addLast(p);
                        done++;
                        index = 0;
                        state = 1;
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
    
    //returns the index of the max priority process
    public int getMaxPriority(){
        int index=0;
        for(int i=1 ; i<readyQueue.size() ; i++)
            if(readyQueue.get(i).getPriority()<readyQueue.get(index).getPriority())
                index = i;
        return index;
    }

    //returns the index of the shortest job precess
    public int getMinJob(){
        int index=0 ;
        for(int i=1 ; i<readyQueue.size() ; i++)
            if(readyQueue.get(i).getBurstTime()<readyQueue.get(index).getBurstTime())
                index = i;
        return index;
    }
}