package app.frontEnd.views.viewBuilders;

import app.MainController;
import app.common.constants.PageConstants;
import app.frontEnd.utilities.Validator;
import app.frontEnd.views.viewBuilders.interfaces.SceneBuilder;
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
    private MainController controller;

    public CreateChoiceSceneBuilder(MainController controller) {
        this.controller = controller;
    }

    @Override
    public void showScene() {
        BorderPane mainLayout = new BorderPane();
        HBox topLayout = new HBox(PageConstants.SPACING);
        HBox centerLayout = new HBox(PageConstants.SPACING);
        HBox bottomLayout = new HBox(PageConstants.SPACING);

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
                mainScene.showScene();
            }
        });

        bottomLayout.getChildren().addAll(okButton, cancelButton);

        mainLayout.setTop(topLayout);
        mainLayout.setCenter(centerLayout);
        mainLayout.setBottom(bottomLayout);

        Scene thisScene = new Scene(mainLayout,PageConstants.SCREEN_WIDTH, PageConstants.SCREEN_HEIGHT);

        controller.setScene(thisScene, "Select event type");
    }
}
