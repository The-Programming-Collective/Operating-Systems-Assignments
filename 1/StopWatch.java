public class StopWatch extends Thread{
    public int time = 0;
    // function will be repeated in a while loop in main if we have to return time. if time is outputted from function then loop inside function
    public void run(){
        while(true){
            try{
                Thread.sleep(1);
            } catch(Exception e) {}
            time++;
        }
    }

    public int getTime() {return time;}
}