package app.FrontEnd.Views.ViewBuilders;

import app.FrontEnd.Views.ViewBuilders.interfaces.SceneBuilder;
import app.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class MainSceneBuilder implements SceneBuilder{
    private Main controller;

    public MainSceneBuilder(Main controller) {
        this.controller = controller;
    }

    public void showScene(){
        VBox vBox = new VBox(30);

        vBox.setAlignment(Pos.CENTER);

        BorderPane borderPane = new BorderPane();

        Label welcomeLabel = new Label("Please select an option to continue");

        borderPane.setTop(welcomeLabel);

        Button createButton = new Button("Create Event");


        createButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                SceneBuilder createChoiceScene = new CreateChoiceSceneBuilder(controller);
                createChoiceScene.showScene();
            }
        });
        Button updateEventButton = new Button("Update Event");

        updateEventButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                SceneBuilder updateChoice = new UpdateEventPageSceneBuilder(controller);
                updateChoice.showScene();
            }
        });

        Button deleteEventButton = new Button("Delete Event");
        Button getAllEventsButton = new Button("Show All Events");
        Button calendarButton = new Button("Show calendar");

        getAllEventsButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                SceneBuilder allEventsScene = new AllEventsPageSceneBuilder(controller);
                allEventsScene.showScene();
            }
        });

        deleteEventButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                SceneBuilder deleteScene = new DeleteEventSceneBuilder(controller);
            }
        });

        calendarButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                SceneBuilder calendarScene = new CalendarSceneBuilder(controller);
                calendarScene.showScene();
            }
        });

        vBox.getChildren().addAll(welcomeLabel,
                createButton,
                updateEventButton,
                deleteEventButton,
                getAllEventsButton,
                calendarButton);

        borderPane.setCenter(vBox);


        Scene mainScene = new Scene(borderPane, 500, 500);

        controller.setScene(mainScene, "Menu");
    }
}
