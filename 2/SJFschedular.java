import java.util.LinkedList;

public class SJFschedular {
    protected LinkedList<Process> queue;

    public void add(Process process){queue.addLast(process);}
}
