public class Buffer {
    private int buffer[];
    private int size;
    private int in=0;
    private int out=0;
    public int counter=0;
    public int largest;
    public Semaphore full;
    public Semaphore empty;
    public Semaphore mutex;
    public boolean done = false;

    Buffer(int size){
        this.size=size;
        buffer = new int[size];
        full = new Semaphore(size,0);
        empty = new Semaphore(size,size);
        mutex = new Semaphore(1, 1);
    }
    public int get(){
        int temp = buffer[out];
        out = (out+1)%size;
        System.out.println("get "+temp+" "+full.counter);
        return temp;
    }
    public void set(int val){
        buffer[in]=val;
        in = (in+1)%size;
        counter++;
        largest=val;
        System.out.println("set "+val+" "+empty.counter);
    }
}