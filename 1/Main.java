import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;

public class Main implements ActionListener{

    public  JFrame frame;
    public  JPanel main;
    public  JPanel panel;
    public  JPanel panel2;
    public  JTextField userText1;
    public  JTextField userText2;
    public  JTextField userText3;
    public  JLabel userLabel5;
    public  JLabel userLabel7;
    public  JLabel userLabel9;
    public  JButton StartButton;
    public boolean running = false;

    Main(){
        frame = new JFrame("Assignment 1");
        frame.setSize(350, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        main = new JPanel(new GridLayout(2,1));

        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.CYAN);

        JLabel userLabel1 = new JLabel("N");
        userLabel1.setBounds(10,20,80,25);
        panel.add(userLabel1);

        userText1 = new JTextField(20);
        userText1.setBounds(100,20,165,25);
        panel.add(userText1);

        JLabel userLabel2 = new JLabel("Buffer size");
        userLabel2.setBounds(10,50,80,25);
        panel.add(userLabel2);

        userText2 = new JTextField(20);
        userText2.setBounds(100,50,165,25);
        panel.add(userText2);
        
        JLabel userLabel3 = new JLabel("Output file");
        userLabel3.setBounds(10,80,80,25);
        panel.add(userLabel3);

        userText3 = new JTextField(20);
        userText3.setBounds(100,80,165,25);
        panel.add(userText3);

        JButton StartButton = new JButton("Start");
        StartButton.setBounds(10, 110, 80, 25);
        StartButton.addActionListener(this);
        panel.add(StartButton);

        // JButton StopButton = new JButton("Stop");
        // StopButton.setBounds(100, 110, 80, 25);
        // StopButton.addActionListener(this);
        // panel.add(StopButton);

        ///////////////////////////////////////////////////////////////

        panel2 = new JPanel();
        panel2.setBackground(Color.GRAY);
        panel2.setLayout(null);

        JLabel userLabel4 = new JLabel("The largest prime number :");
        userLabel4.setBounds(10,20,80,25);
        panel2.add(userLabel4);

        userLabel5 = new JLabel();
        userLabel5.setBounds(100,20,165,25);
        panel2.add(userLabel5);

        JLabel userLabel6 = new JLabel("# of primes generated :");
        userLabel6.setBounds(10,50,80,25);
        panel2.add(userLabel6);

        userLabel7 = new JLabel();
        userLabel7.setBounds(100,50,165,25);
        panel2.add(userLabel7);

        JLabel userLabel8 = new JLabel("Time elapsed :");
        userLabel8.setBounds(10,80,80,25);
        panel2.add(userLabel8);

        userLabel9 = new JLabel();
        userLabel9.setBounds(100,80,165,25);
        panel2.add(userLabel9);

        ///////////////////////////////////////////////////////////////
        
        main.add(panel);
        main.add(panel2);
        frame.add(main);
        frame.setVisible(true);

        ///////////////////////////////////////////////////////////////
        StartButton.setEnabled(true);
    }

    private void ProgramStart(int max , int bufferSize , String fileName){
        Buffer b = new Buffer(bufferSize);
        Thread t1 = new Thread(new Producer(b, max, this));
        Thread t2 = new Thread(new Consumer(b, fileName));
        t1.start();
        t2.start();
    }

    public static void main(String[] args) {
        new Main();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        String maxString = userText1.getText();
        if(!isNumeric(maxString)){
            userText1.setText("Enter positive number");
            return;
        }
        Integer IMax = Integer.parseInt(maxString);
        int max = IMax.intValue();

        String bufferSizeString = userText2.getText();
        if(!isNumeric(bufferSizeString)){
            userText2.setText("Enter positive number");
            return;
        }
        Integer IBufferSize = Integer.parseInt(bufferSizeString);
        int bufferSize = IBufferSize.intValue();

        String fileName = userText3.getText();

        if(running)
            return;
        if(max<=0){
            userText1.setText("Must be positive");
            return;
        }
        if(bufferSize<=0){
            userText2.setText("Must be positive");
            return;
        }
        if(fileName.equals("")||fileName.equals("Enter file name")){
            userText3.setText("Enter file name");
            return;
        }
        ProgramStart(max , bufferSize , fileName);
    }

    public static boolean isNumeric(String str) { 
        try {  
          Integer x = Integer.parseInt(str);
           return (x>0); 
        } catch(NumberFormatException e){  
          return false;  
        }  
      }

}