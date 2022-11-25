import javax.swing.*;
import java.awt.*;

// Online Java Compiler
// Use this editor to write, compile and run your Java code online

class test {
    JFrame frame;
    JPanel contentPane;
    JPanel pinkPanel;
    JPanel yellowPanel;
    JPanel bluePanel;
    JPanel twoPanelContainer;

    public test() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        contentPane = new JPanel(new GridLayout(2,1));

        pinkPanel = new JPanel();
        pinkPanel.setBackground(Color.PINK);

        yellowPanel = new JPanel();
        yellowPanel.setBackground(Color.YELLOW);    

        bluePanel = new JPanel();
        bluePanel.setBackground(Color.BLUE);

        twoPanelContainer = new JPanel(new GridLayout(1,3));
        twoPanelContainer.add(yellowPanel);
        //twoPanelContainer.add(bluePanel);

        contentPane.add(pinkPanel);
        contentPane.add(twoPanelContainer);

        frame.add(contentPane);
        frame.pack();
        frame.setVisible(true);
    }
    public static void main(String[] args) {
        new test();
    }
}