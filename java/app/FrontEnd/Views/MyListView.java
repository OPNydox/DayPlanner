package app.FrontEnd.Views;

import javax.swing.*;
import java.awt.*;
import java.util.*;

/**
 * Created by Ico on 15.1.2017 г..
 */
public class MyListView extends JFrame {
    private JList events;

    public MyListView(String title, java.util.List<String> data) throws HeadlessException {
        super(title);

        setSize(500,500);

        events = new JList(data.toArray());
        add(events);

    }
}
