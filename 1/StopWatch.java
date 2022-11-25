public class StopWatch implements Runnable{
    public int time = 0;
    public void run(){
        while(true){
            try{
                Thread.sleep(1);
            } catch(Exception e) {}
            time++;
        }
    }
    
}