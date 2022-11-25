import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GUI implements ActionListener {

    GUI(){
        JFrame frame = new JFrame();
        JPanel panel1 = new JPanel();
        //JPanel panel2 = new JPanel();
        JButton button1 = new JButton("Start");
        button1.addActionListener(this);

        panel1.setBorder(BorderFactory.createEmptyBorder(30, 20, 10, 20));
        //panel1.setLayout(new GroupLayout(frame));
        panel1.add(button1);

        frame.add(panel1, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("fk u ");
        frame.pack();
        frame.setVisible(true);
    }
    public static void main(String[] args) {
        //new GUI();
        Buffer b = new Buffer(6);
        new Thread(new Producer(b, 1000)).start();
        new Thread(new Consumer(b, "lol.txt")).start();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
}