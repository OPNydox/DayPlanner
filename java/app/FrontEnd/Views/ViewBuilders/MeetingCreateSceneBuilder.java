package app.FrontEnd.Views.ViewBuilders;

import app.Common.models.ViewModels.MeetingView;
import app.Common.utilities.DateConverter;
import app.FrontEnd.Utilities.Validator;
import app.FrontEnd.Views.ViewBuilders.interfaces.SceneBuilder;
import app.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;


public class MeetingCreateSceneBuilder implements SceneBuilder{
    private Main controller;

    public MeetingCreateSceneBuilder(Main controller) {
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
        Label selectMarkerLabel = new Label("Select Marker");
        Label selectLocationLabel = new Label("Enter Location");

        gridPane.add(nameLabel, 1, 1);
        gridPane.add(dateLabel, 1, 2);
        gridPane.add(hoursLabel, 1, 3);
        gridPane.add(minutesLabel, 1, 4);
        gridPane.add(descriptionLabel, 1, 5);
        gridPane.add(selectMarkerLabel, 1, 6);
        gridPane.add(selectLocationLabel, 1, 7);


        TextField nameTextField = new TextField();
        DatePicker datePicker = new DatePicker();

        datePicker.setEditable(false);

        TextField hourTextField = new TextField();
        TextField minutesTextField = new TextField();
        TextArea descriptionText = new TextArea();
        TextField locationText = new TextField();

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
        gridPane.add(locationText, 2,7);

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
                MeetingView newMeeting = new MeetingView();

                newMeeting.setName(nameTextField.getText());
                try{
                    newMeeting.setDate(DateConverter.dateToCalendar(DateConverter.LocalDateToDate(datePicker.getValue())));
                } catch (NullPointerException ex){
                    controller.showIllegalArgumentsAlert("Event must have a date!");
                    return;
                }

                newMeeting.setDescription(descriptionText.getText());
                try {
                    newMeeting.setMarker(markerComboBox.getValue());
                }catch (NumberFormatException ex){
                    controller.showIllegalArgumentsAlert("Please pick a marker");
                    return;
                }

                try{
                    newMeeting.setHour(hourTextField.getText());
                    newMeeting.setMinutes(minutesTextField.getText());
                } catch (NumberFormatException ex){
                    controller.showIllegalArgumentsAlert("Wrong hour or minute format!");
                    return;
                }

                newMeeting.setType("Task");

                try {
                    Validator.validateEvent(newMeeting, controller.getEventService());
                }catch (IllegalArgumentException ex){
                    controller.showIllegalArgumentsAlert(ex.getMessage());
                    return;
                }
                newMeeting.setLocation(locationText.getText());
                newMeeting.setType("Meeting");

                controller.addEvent(newMeeting);
                SceneBuilder sceneBuilder = new MeetingCreateSceneBuilder(controller);
            }
        });

        HBox hBox= new HBox(30);

        hBox.getChildren().addAll(okButton, cancelButton);

        borderPane.setBottom(hBox);
        Scene thisScene = new Scene(borderPane, 500, 500);
        controller.setScene(thisScene, "Create Event");
    }
}
