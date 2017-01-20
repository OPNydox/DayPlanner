package app.frontEnd.views.viewBuilders;

import app.MainController;
import app.common.constants.PageConstants;
import app.common.models.daModels.eventModels.EventDA;
import app.common.models.daModels.eventModels.MeetingDA;
import app.common.models.daModels.eventModels.TaskDA;
import app.frontEnd.views.viewBuilders.interfaces.SceneBuilder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

import java.util.List;


public class AllEventsPageSceneBuilder implements SceneBuilder{
    private MainController controller;

    private List<String> data;

    public AllEventsPageSceneBuilder(MainController controller) {
        this.controller = controller;

        data = controller.getAllEventsAsString(controller.getAllEvents());
    }

    @Override
    public void showScene() {
        BorderPane mainPane = new BorderPane();

        Label text = new Label("All events:");

        ListView<String> allEventsList = new ListView<>();

        ObservableList<String> observableList = FXCollections.observableArrayList(data);

        allEventsList.setItems(observableList);

        allEventsList.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 2){
                    String message = allEventsList.getSelectionModel().getSelectedItem();
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
                } else {
                    return;
                }

            }
        });

        Button goToMenuButton = new Button("Go to main menu");

        goToMenuButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                SceneBuilder mainScene = new MainSceneBuilder(controller);
                mainScene.showScene();
            }
        });

        mainPane.setTop(text);
        mainPane.setCenter(allEventsList);
        mainPane.setBottom(goToMenuButton);

        Scene thisScene = new Scene(mainPane, PageConstants.SCREEN_WIDTH, PageConstants.SCREEN_HEIGHT);

        controller.setScene(thisScene, "All events");

    }
}
