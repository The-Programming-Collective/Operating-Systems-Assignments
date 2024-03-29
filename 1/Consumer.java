import java.io.BufferedWriter;
import java.io.FileWriter;

public class Consumer implements Runnable{
    private String fName;
    private Buffer buffer;
    Consumer(Buffer buffer ,String s){
        this.fName = s;
        this.buffer = buffer;
    }

    private void Consume(){
       try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(fName));
            int counter=0;
            String word;
            while(!buffer.done || buffer.in!=buffer.out){

                counter++;
                if(counter>=10){
                    word ="\n";
                    counter=0;
                }
                else
                    word = ",";

                buffer.full.waiting();
                buffer.mutex.waiting();
                writer.write(""+buffer.get()+word);
                buffer.empty.notifying();
                buffer.mutex.notifying();
            }
            writer.close();
       }
       catch(Exception e) {}
        System.out.println("End consumer");
    }
    
    @Override
    public void run() {
        Consume();
    }
}
