package app.FrontEnd.Views;

import javax.swing.*;

/**
 * Created by Ico on 7.1.2017 г..
 */
public class TestView extends JFrame {
    private JPanel mainPanel;

    public TestView() {
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("TestView");
        frame.setContentPane(new TestView().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
