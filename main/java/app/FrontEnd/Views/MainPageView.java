package app.FrontEnd.Views;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Ico on 7.1.2017 г..
 */
public class MainPageView  extends JFrame{
    private JButton createButton;
    private JButton updateEvent;
    private JButton getAllEventsButton;
    private JButton exportToJsonButton;
    private JLabel SelectOptionText;
    private JPanel textPanel;
    private JPanel mainPanel;
    private JPanel rightPanel;
    private JPanel leftPanel;

    public MainPageView() {
        this.setLayout(new BorderLayout());

        createButton = new JButton("create");
        createButton.setVisible(true);

        JLabel button = new JLabel("testButton");
        button.setVisible(true);
        this.add(button, BorderLayout.CENTER);

        this.setTitle("Main Menu");

        this.setSize(400,400);

        Toolkit toolkit = Toolkit.getDefaultToolkit();

        Dimension dimension = toolkit.getScreenSize();

        int xPos = (dimension.width / 2) - (this.getWidth() / 2);
        int yPos = (dimension.width / 2) - (this.getHeight() / 2);

        this.setLocation(xPos, yPos);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setResizable(false);


  //    this.add(createButton);
  //    this.add(updateEvent);
  //    this.add(getAllEventsButton);
  //    this.add(exportToJsonButton);
  //    this.add(SelectOptionText);
  //    this.add(textPanel);
  //    this.add(mainPanel);
  //    this.add(rightPanel);
  //    this.add(leftPanel);
  //
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("MainPageView");
        MainPageView mpv = new MainPageView();
        mpv.createButton.setText(args[0]);
        mpv.setResizable(false);
        frame.setContentPane(mpv.mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
