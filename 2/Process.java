public class Process {
    private String name;
    private int arrivalTime;
    private int priority;
    private int burstTime;
    private int quantum;
    private int terminationTime;
    private int totalBurstTime; // 

    Process(String name ,int arrivalTime , int priority , int burstTime , int quantum){
        this.name = name;
        this.arrivalTime = arrivalTime;
        this.priority = priority;
        this.burstTime = burstTime;
        this.quantum = quantum;
        totalBurstTime = burstTime; 
    }

    public String getName() {return name; }
    public int getArrivalTime() {return arrivalTime; }
    public int getPriority() {return priority; }
    public int getBurstTime() {return burstTime; }
    public int getQuantum() {return quantum; }
    public int getTerminationTime() {return terminationTime; }
    public int getTurnAroundTime() {return terminationTime-arrivalTime; }
    public int getWaitingTime() {return getTurnAroundTime()-totalBurstTime; }

    public void setName(String name) {this.name = name; }
    public void setArrivalTime(int arrivalTime) {this.arrivalTime = arrivalTime; }
    public void setPriority(int priority) {this.priority = priority; }
    public void setBurstTime(int burstTime) {this.burstTime = burstTime; }
    public void setQuantum(int quantum) {this.quantum = quantum; }
    public void setTerminationTime(int terminationTime) {this.terminationTime = terminationTime; }
    public void decrementBurstTime() {this.burstTime--; }
    public void incrementPriority() {this.priority--;}

}