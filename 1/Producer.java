public class Producer implements Runnable{
    private int max;
    private Buffer buffer;
    Producer(Buffer buffer , int max ){
        this.max=max;
        this.buffer=buffer;
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
        for(int i=1 ; i<max ; i++){
            if(isprime(i)){
                buffer.empty.waiting();
                buffer.mutex.waiting();
                buffer.set(i);
                buffer.mutex.notifying();
                buffer.full.notifying();
            }
        }
        buffer.empty.waiting();
        buffer.mutex.waiting();
        buffer.set(-1);
        buffer.mutex.notifying();
        buffer.full.notifying();
    }

    @Override
    public void run(){
        Produce(max);
    }
}