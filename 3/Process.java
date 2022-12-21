public class Process {
    public String name;
    public int size;
    public static int count=1;
    
    Process(int size){
        this.name = "Process "+(count++);
        this.size = size;
    }
}
