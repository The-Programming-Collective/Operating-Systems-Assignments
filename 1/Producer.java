public class Producer implements Runnable{
    private int max;
    private Buffer buffer;
    private Main m;
    private int counter=0;
    StopWatch stopWatch;
    Producer(Buffer buffer , int max , Main m ){
        this.max=max;
        this.buffer=buffer;
        this.m=m;
    }

    public static boolean isprime(int n){
        if (n == 1)
            return false;
        for (int i = 2; i <= Math.sqrt(n); i++)
            if (n % i == 0)
                return false;
        return true;
    }

    private void Produce(int max){
        m.running=true;
        buffer.done=false;
        for(int i=1 ; i<max ; i++){
            if(isprime(i)){
                buffer.empty.waiting();
                buffer.mutex.waiting();
                buffer.set(i);
                m.userLabel5.setText(""+i);
                m.userLabel7.setText(""+(++counter));
                m.userLabel9.setText(""+stopWatch.time);
                buffer.mutex.notifying();
                buffer.full.notifying();
            }
        }
        buffer.done=true;
        m.running=false;
       // m.StartButton.setEnabled(true);
        System.out.println("End Producer");
    }

    @Override
    public void run(){
        stopWatch = new StopWatch();
        new Thread(stopWatch).start();
        Produce(max);
    }
}