package app.FrontEnd.Views.ViewBuilders;

import app.FrontEnd.Utilities.Validator;
import app.FrontEnd.Views.ViewBuilders.interfaces.SceneBuilder;
import app.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

/**
 * Created by Ico on 16.1.2017 г..
 */
public class CreateChoiceSceneBuilder implements SceneBuilder {
    private Main controller;

    public CreateChoiceSceneBuilder(Main controller) {
        this.controller = controller;
    }

    @Override
    public void showScene() {
        BorderPane mainLayout = new BorderPane();
        HBox topLayout = new HBox();
        HBox centerLayout = new HBox();
        HBox bottomLayout = new HBox();

        topLayout.setAlignment(Pos.CENTER);
        centerLayout.setAlignment(Pos.CENTER);
        bottomLayout.setAlignment(Pos.CENTER);

        Label label = new Label("Please choose the type of event you want to create");

        topLayout.getChildren().add(label);

        final ObservableList<String> options = FXCollections.observableArrayList(
                "Task",
                "Meeting"
        );

        final ComboBox comboBox = new ComboBox(options);

        centerLayout.getChildren().add(comboBox);

        Button okButton = new Button("OK");
        Button cancelButton = new Button("Cancel");

        okButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String selectedEventType = (String) comboBox.getValue();

                try{
                    Validator.validateDropDownField(selectedEventType);
                } catch (NullPointerException ex){
                    controller.showIllegalArgumentsAlert("Please select an option");
                    return;
                }


                if (selectedEventType.equals("Task")){
                    SceneBuilder createTaskScene = new TaskCreateSceneBuilder(controller);
                    createTaskScene.showScene();
                } else if (selectedEventType.equals("Meeting")){
                    SceneBuilder createMeetingScene = new MeetingCreateSceneBuilder(controller);
                    createMeetingScene.showScene();
                }
            }
        });

        cancelButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                SceneBuilder mainScene = new MainSceneBuilder(controller);
            }
        });

        bottomLayout.getChildren().addAll(okButton, cancelButton);

        mainLayout.setTop(topLayout);
        mainLayout.setCenter(centerLayout);
        mainLayout.setBottom(bottomLayout);

        Scene thisScene = new Scene(mainLayout,500, 500);

        controller.setScene(thisScene, "Select event type");
    }
}
