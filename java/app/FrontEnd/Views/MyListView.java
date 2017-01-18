package app.FrontEnd.Views;

import app.Common.models.daModels.eventModels.EventDA;
import app.Common.models.daModels.eventModels.MeetingDA;
import app.Common.models.daModels.eventModels.TaskDA;
import app.FrontEnd.Views.ViewBuilders.UpdateMeetingSceneBuilder;
import app.FrontEnd.Views.ViewBuilders.UpdateTaskSceneBuilder;
import app.FrontEnd.Views.ViewBuilders.interfaces.SceneBuilder;
import app.Main;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class MyListView extends JFrame {
    private JList events;

    public MyListView(String title, java.util.List<String> data, Main controller) throws HeadlessException {
        super(title);

        setSize(500,500);

        events = new JList(data.toArray());

        events.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2){
                    String message = events.getSelectedValue().toString();
                    String[] tokens = message.split("\\|");
                    tokens[1] = tokens[1].trim();
                    EventDA eventToUpdate =  controller.getEventByName(tokens[1]);
                    if (eventToUpdate instanceof TaskDA){
                        SceneBuilder taskScene = new UpdateTaskSceneBuilder(controller,(TaskDA) eventToUpdate);
                        taskScene.showScene();
                    } else {
                        SceneBuilder meetingScene = new UpdateMeetingSceneBuilder(controller,(MeetingDA) eventToUpdate);
                        meetingScene.showScene();
                    }
                }else {
                    return;
                }
            }
        });

        add(events);

    }
}
