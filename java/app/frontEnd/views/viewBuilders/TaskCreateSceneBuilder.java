package app.frontEnd.views.viewBuilders;


import app.MainController;
import app.common.constants.PageConstants;
import app.common.models.ViewModels.TaskView;
import app.common.utilities.DateConverter;
import app.frontEnd.utilities.Validator;
import app.frontEnd.views.viewBuilders.interfaces.SceneBuilder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class TaskCreateSceneBuilder implements SceneBuilder{
    private MainController controller;

    public TaskCreateSceneBuilder(MainController controller) {
        this.controller = controller;
    }

    @Override
    public void showScene() {
        BorderPane borderPane = new BorderPane();

        GridPane gridPane = new GridPane();

        Label nameLabel = new Label("Name:");
        Label dateLabel = new Label("Select Date:");
        Label hoursLabel = new Label("Enter hour");
        Label minutesLabel = new Label("Enter minutes");
        Label descriptionLabel = new Label("Enter Description");
        Label selectMarker = new Label("Select Marker");

        gridPane.add(nameLabel, 1, 1);
        gridPane.add(dateLabel, 1, 2);
        gridPane.add(hoursLabel, 1, 3);
        gridPane.add(minutesLabel, 1, 4);
        gridPane.add(descriptionLabel, 1, 5);
        gridPane.add(selectMarker, 1, 6);


        TextField nameTextField = new TextField();
        DatePicker datePicker = new DatePicker();

        datePicker.setEditable(false);
        TextField hourTextField = new TextField();
        TextField minutesTextField = new TextField();
        TextArea descriptionText = new TextArea();

        ObservableList<String> options = FXCollections.observableArrayList(
                "Private",
                "Confidential",
                "Personal"
        );

        final ComboBox<String> markerComboBox = new ComboBox<>(options);

        gridPane.add(nameTextField, 2, 1);
        gridPane.add(datePicker, 2, 2);
        gridPane.add(hourTextField, 2, 3);
        gridPane.add(minutesTextField, 2,4);
        gridPane.add(descriptionText, 2, 5);
        gridPane.add(markerComboBox, 2, 6);

        borderPane.setLeft(gridPane);

        Button okButton = new Button("OK");
        Button cancelButton = new Button("Close");

        cancelButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                SceneBuilder mainScene = new MainSceneBuilder(controller);
                mainScene.showScene();
            }
        });

        okButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                TaskView newTask = new TaskView();

                newTask.setName(nameTextField.getText());
                try{
                    newTask.setDate(DateConverter.dateToCalendar(DateConverter.LocalDateToDate(datePicker.getValue())));
                } catch (NullPointerException ex){
                    controller.showIllegalArgumentsAlert("Event must have a date!");
                    return;
                }

                newTask.setDescription(descriptionText.getText());
                try {
                    newTask.setMarker(markerComboBox.getValue());
                }catch (NumberFormatException ex){
                    controller.showIllegalArgumentsAlert("Please pick a marker");
                    return;
                }

                try{
                    newTask.setHour(hourTextField.getText());
                    newTask.setMinutes(minutesTextField.getText());
                } catch (NumberFormatException ex){
                    controller.showIllegalArgumentsAlert("Wrong hour or minute format!");
                    return;
                }

                newTask.setType("Task");

                try {
                    Validator.validateEvent(newTask, controller.getEventService());
                }catch (IllegalArgumentException ex){
                    controller.showIllegalArgumentsAlert(ex.getMessage());
                    return;
                }

                controller.addEvent(newTask);
                SceneBuilder createTaskScene = new TaskCreateSceneBuilder(controller);
                createTaskScene.showScene();
            }
        });

        HBox hBox= new HBox(PageConstants.SPACING);

        hBox.getChildren().addAll(okButton, cancelButton);

        borderPane.setBottom(hBox);
        Scene thisScene = new Scene(borderPane, PageConstants.SCREEN_WIDTH, PageConstants.SCREEN_HEIGHT);
        controller.setScene(thisScene, "Create Event");
    }
}
