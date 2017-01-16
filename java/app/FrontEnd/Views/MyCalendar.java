package app.FrontEnd.Views;

import com.toedter.calendar.JCalendar;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Ico on 15.1.2017 г..
 */
public class MyCalendar extends JFrame {
    private JCalendar calendar;

    public MyCalendar(String title, JCalendar calendar) throws HeadlessException {
        super(title);
        setSize(500,500);
        setLayout(new BorderLayout());


        add(calendar);
        add(new Button("My calendar button"));
        this.calendar = calendar;
    }
}
