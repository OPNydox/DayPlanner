package app.frontEnd.views.viewBuilders;

import app.MainController;
import app.common.constants.PageConstants;
import app.common.models.daModels.eventModels.EventDA;
import app.common.models.daModels.eventModels.MeetingDA;
import app.common.models.daModels.eventModels.TaskDA;
import app.frontEnd.views.viewBuilders.interfaces.SceneBuilder;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class UpdateEventPageSceneBuilder implements SceneBuilder {
    private MainController controller;

    public UpdateEventPageSceneBuilder(MainController controller) {
        this.controller = controller;
    }

    @Override
    public void showScene() {
        BorderPane borderPane = new BorderPane();
        HBox bottomLayout = new HBox(PageConstants.SPACING);
        VBox vbox = new VBox(PageConstants.SPACING);
        HBox topLayout = new HBox(PageConstants.SPACING);

        Label text = new Label("Enter the name of the event you want to update");
        text.setAlignment(Pos.CENTER);

        TextField updateEventName = new TextField();

        updateEventName.setMaxSize(250, 5);

        Button okButton = new Button("OK");
        Button cancelButton = new Button("Cancel");

        okButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                EventDA dbEvent = controller.getEventByName(updateEventName.getText());

                if (dbEvent == null){
                    SceneBuilder mainScene = new MainSceneBuilder(controller);
                    mainScene.showScene();
                } else{
                    if (dbEvent instanceof TaskDA){
                        SceneBuilder updateTaskScene = new UpdateTaskSceneBuilder(controller,(TaskDA) dbEvent);
                        updateTaskScene.showScene();
                    } else if (dbEvent instanceof  EventDA){
                        SceneBuilder updateMeetingScene = new UpdateMeetingSceneBuilder(controller,(MeetingDA) dbEvent);
                        updateMeetingScene.showScene();
                    }
                }

            }
        });

        cancelButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                SceneBuilder mainScene  = new MainSceneBuilder(controller);
                mainScene.showScene();
            }
        });


        vbox.getChildren().add(updateEventName);

        topLayout.setAlignment(Pos.CENTER);

        topLayout.getChildren().add(text);

        borderPane.setTop(text);

        vbox.setAlignment(Pos.CENTER);

        borderPane.setCenter(vbox);

        bottomLayout.setAlignment(Pos.CENTER);

        bottomLayout.getChildren().addAll(okButton, cancelButton);

        borderPane.setBottom(bottomLayout);

        Scene thisScene = new Scene(borderPane, PageConstants.SCREEN_WIDTH, PageConstants.SCREEN_HEIGHT);

        controller.setScene(thisScene, "Choose scene to update");
    }
}
