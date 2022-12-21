public class Partition {
    public String name;
    public int size;
    public Process process;
    public static int count=0;

    Partition(int size){
        this.name="Partition "+(count++);
        this.size=size;
        this.process=null;
    }
}
