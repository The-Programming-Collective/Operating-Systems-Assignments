import java.util.LinkedList;


//TODO add quantum updates
public class AGscheduler extends Scheduler {
    @Override
    public void startScheduler(LinkedList<Process> processes, int CST) {
        queue = processes ;
        int initialSize = queue.size() , index = 0 , counter = 0;
        int state = 0, previousState=-1; //0 FCFS, 1 NPP, 2 PSJF

        do{
            getInSchedule();
            if(temp.size()==0) time++;
            else actualStartTime = time;
        }while(temp.size()==0);
        for(int c=0 ; done!=initialSize ; time++,c++){
            if(time!=actualStartTime){getInSchedule();}

            switch(state){
                case 0:
                    temp.get(index).decrementBurstTime();
                    temp.get(index).decrementRemainingQuantum();
                    System.out.print((time)+"-process "+temp.get(index).getName()+"\n");
                    if(temp.get(index).getBurstTime()==0){
                        Process p = temp.remove(index);
                        p.setTerminationTime(time);
                        p.setQuantum(0);
                        queue.addLast(p);
                        done++;
                        index=0;
                        break;
                    }
                    if(temp.get(index).getRemainingQuantum()==0){
                        temp.get(index).setQuantum(temp.get(index).getQuantum()+2);
                        temp.addLast(temp.remove(index));
                    }
                    
                    if(! ((int)Math.ceil(temp.get(index).getQuantum()*0.25)==temp.get(index).getQuantum()-temp.get(index).getRemainingQuantum()))
                        break;
                    previousState = state;
                    state = 1;
                    break;
                case 1:
                    if(previousState != 1){
                        Process p1 = temp.get(index);
                        if(! temp.get(getMaxPriority()).equals(p1)){
                            temp.remove(p1);
                            p1.setQuantum(p1.getQuantum()+(int)Math.ceil(p1.getRemainingQuantum()/2));
                            temp.addLast(p1);
                            previousState = state;
                            index = getMaxPriority();
                        }
                    }
                    temp.get(index).decrementBurstTime();
                    temp.get(index).decrementRemainingQuantum();
                    System.out.print((time)+"-process "+temp.get(index).getName()+"\n");
                    if(temp.get(index).getBurstTime()==0){
                        Process p = temp.remove(index);
                        p.setTerminationTime(time);
                        p.setQuantum(0);
                        queue.addLast(p);
                        done++;
                        index = 0;
                        previousState = state;
                        state = 0;
                        break;
                    }
                    //System.out.println(((int)Math.ceil(temp.get(index).getQuantum()*0.25)+" "+(temp.get(index).getQuantum()-temp.get(index).getRemainingQuantum())));
                    if(! ((int)Math.ceil(temp.get(index).getQuantum()*0.50)==temp.get(index).getQuantum()-temp.get(index).getRemainingQuantum()))
                        break;
                    previousState = state;
                    state = 2;
                    break;
                case 2:
                    Process p2 = temp.get(index);
                    if(! p2.equals(temp.get(getMinJob())) || p2.getPriority()>temp.getLast().getPriority()){
                        temp.remove(p2);
                        p2.setQuantum(p2.getQuantum()+p2.getRemainingQuantum());
                        temp.addLast(p2);
                        previousState = state;
                        state = 0;
                    }
                    index = getMinJob();
                    temp.get(index).decrementBurstTime();
                    temp.get(index).decrementRemainingQuantum();
                    counter++;
                    System.out.print((time)+"-process "+temp.get(index).getName()+"\n");
                    if(temp.get(index).getBurstTime()==0){
                        Process p3 = temp.remove(index);
                        p3.setTerminationTime(time);
                        p3.setQuantum(0);
                        queue.addLast(p3);
                        done++;
                        index = 0;
                        state = 1;
                    }
                    break;
            }
        }
        getInfo(queue);
    }
    
    public int getMaxPriority(){
        int index=0;
        for(int i=1 ; i<temp.size() ; i++)
            if(temp.get(i).getPriority()<temp.get(index).getPriority())
                index = i;
        return index;
    }
    public int getMinJob(){
        int index=0 ;
        for(int i=1 ; i<temp.size() ; i++)
            if(temp.get(i).getBurstTime()<temp.get(index).getBurstTime())
                index = i;
        return index;
    }
}