public class Semaphore{
    public int size;
    public int counter;

    Semaphore(int size, int counter){
        this.size=size;
        this.counter=counter;
    }

    public synchronized void waiting(){
        while(counter<=0)
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        counter--;
    };

    public synchronized void notifying(){
        counter++;
        notifyAll();;
    }
}