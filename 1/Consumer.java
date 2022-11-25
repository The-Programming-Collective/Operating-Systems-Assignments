import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Consumer implements Runnable{
    private static String fName="temp.txt";
    private Buffer buffer;
    Consumer(Buffer buffer ,String s){
        this.fName = s;
        this.buffer = buffer;
    }

    private void Consume(){
        File file = new File (fName);
        FileWriter writer ;
        //try {
            //writer = new FileWriter(file,true);
            while(!buffer.done){
                buffer.full.waiting();
                buffer.mutex.waiting();
                //consume
                // if(buffer.get()==-1)
                //     break;
                buffer.get();
                //
                buffer.empty.notifying();
                buffer.mutex.notifying();
            }
            buffer.empty.notifying();
            buffer.mutex.notifying();
            //writer.close();
        // } catch (IOException e) {
        //     e.printStackTrace();
        // }
        System.out.println("End consumer");
    }
    
    @Override
    public void run() {
        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Consume();
    }
}
