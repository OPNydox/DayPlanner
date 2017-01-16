package app.FrontEnd.Views.ViewBuilders;


import app.FrontEnd.Views.MyListView;
import app.FrontEnd.Views.ViewBuilders.interfaces.SceneBuilder;
import app.Main;
import com.toedter.calendar.JCalendar;
import javafx.collections.FXCollections;
import javafx.embed.swing.SwingNode;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Calendar;
import java.util.List;

public class CalendarSceneBuilder implements SceneBuilder{
    private Main controller;

    public CalendarSceneBuilder(Main controller) {
        this.controller = controller;
    }

    @Override
    public void showScene() {
        BorderPane mainLayout = new BorderPane();
        HBox bottomLayout = new HBox(30);
        JCalendar jCalendar = new JCalendar();

        jCalendar.addPropertyChangeListener("calendar", new PropertyChangeListener() {

            @Override
            public void propertyChange(PropertyChangeEvent e) {
                final Calendar c = (Calendar) e.getNewValue();
                MyListView listView = new MyListView("Events for day", controller.getAllEventsByDateStringed(c));
                listView.setVisible(true);
            }
        });

        SwingNode swingCalendar = new SwingNode();

        swingCalendar.setContent(jCalendar);

        mainLayout.setCenter(swingCalendar);

        Button cancelButton = new Button("cancel");
        Button getEventsButton = new Button("Get events for current month");

        cancelButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                SceneBuilder mainScene = new MainSceneBuilder(controller);
            }
        });

        getEventsButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                List<String> listOfEvents =
                        controller.getAllEventByMonthStringed(jCalendar.getMonthChooser().getMonth());

                ListView<String> eventView = new ListView<String>( FXCollections.observableArrayList(listOfEvents));

                BorderPane mainLayout = new BorderPane();

                mainLayout.setCenter(eventView);

                HBox bottomLayout = new HBox(40);

                Button backButton = new Button("Back");
                Button menuButton = new Button("Main Menu");

                backButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        SceneBuilder calendarScene = new CalendarSceneBuilder(controller);
                        calendarScene.showScene();
                    }
                });

                menuButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        SceneBuilder mainScene = new MainSceneBuilder(controller);
                        mainScene.showScene();
                    }
                });

                bottomLayout.getChildren().addAll(backButton, menuButton);

                mainLayout.setBottom(bottomLayout);

                Scene thisScene = new Scene(mainLayout, 500, 500);
                controller.setScene(thisScene, "All events for the month");
            }
        });

        bottomLayout.setAlignment(Pos.CENTER);

        bottomLayout.getChildren().addAll(getEventsButton,cancelButton);

        mainLayout.setBottom(bottomLayout);

        Scene thisScene = new Scene(mainLayout, 500,500);

        controller.setScene(thisScene, "Calendar");
    }
}
