public class Partition {
    public String name;
    public int size;
    public Process process;

    Partition(String name, int size){
        this.name=name;
        this.size=size;
        this.process=null;
    }
}
