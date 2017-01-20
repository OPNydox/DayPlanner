package app.frontEnd.views;

import app.MainController;
import app.common.models.daModels.eventModels.EventDA;
import app.common.models.daModels.eventModels.MeetingDA;
import app.common.models.daModels.eventModels.TaskDA;
import app.frontEnd.views.viewBuilders.UpdateMeetingSceneBuilder;
import app.frontEnd.views.viewBuilders.UpdateTaskSceneBuilder;
import app.frontEnd.views.viewBuilders.interfaces.SceneBuilder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class MyListView extends JFrame {
    private JList events;

    public MyListView(String title, java.util.List<String> data, MainController controller) throws HeadlessException {
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
