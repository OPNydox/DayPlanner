package app.frontEnd.views.viewBuilders;

import app.MainController;
import app.common.constants.PageConstants;
import app.frontEnd.views.viewBuilders.interfaces.SceneBuilder;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class MainSceneBuilder implements SceneBuilder{
    private MainController controller;

    public MainSceneBuilder(MainController controller) {
        this.controller = controller;
    }

    public void showScene(){
        VBox vBox = new VBox(PageConstants.SPACING);

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
        Button logOutButton = new Button("Log out");

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
                deleteScene.showScene();
            }
        });

        calendarButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                SceneBuilder calendarScene = new CalendarSceneBuilder(controller);
                calendarScene.showScene();
            }
        });

        logOutButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                SceneBuilder login = new LoginScreenSceneBuilder(controller);
                login.showScene();
            }
        });

        vBox.getChildren().addAll(welcomeLabel,
                createButton,
                updateEventButton,
                deleteEventButton,
                getAllEventsButton,
                calendarButton);

        borderPane.setCenter(vBox);


        Scene mainScene = new Scene(borderPane, PageConstants.SCREEN_WIDTH, PageConstants.SCREEN_HEIGHT);

        controller.setScene(mainScene, "Menu");
    }
}
