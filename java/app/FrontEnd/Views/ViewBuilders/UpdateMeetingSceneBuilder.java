package app.FrontEnd.Views.ViewBuilders;

import app.Common.enums.Marker;
import app.Common.models.daModels.eventModels.MeetingDA;
import app.Common.utilities.DateConverter;
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

import java.util.Date;
import java.util.Optional;

/**
 * Created by Ico on 16.1.2017 г..
 */
public class UpdateMeetingSceneBuilder implements SceneBuilder {
    private Main controller;

    private MeetingDA meeting;

    public UpdateMeetingSceneBuilder(Main controller, MeetingDA meeting) {
        this.controller = controller;
        this.meeting = meeting;
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


        TextField nameTextField = new TextField(meeting.getName());
        DatePicker datePicker = new DatePicker(DateConverter.dateToLocalDate(DateConverter.calendarToDate(meeting.getDateTime())));
        TextField hourTextField = new TextField(Integer.toString(meeting.getDateTime().getTime().getHours()));
        TextField minutesTextField = new TextField(Integer.toString(meeting.getDateTime().getTime().getHours()));
        TextArea descriptionText = new TextArea(meeting.getDescription());
        TextField locationText = new TextField(meeting.getLocation());

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
        Button cancelButton = new Button("Cancel");
        Button deleteButton = new Button("Delete this event");

        cancelButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                MainSceneBuilder mainScene = new MainSceneBuilder(controller);
                mainScene.showScene();
            }
        });

        okButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Date newDate = DateConverter.LocalDateToDate(datePicker.getValue());
                newDate.setHours(Integer.parseInt(hourTextField.getText()));
                newDate.setMinutes(Integer.parseInt(minutesTextField.getText()));

                meeting.setName(nameTextField.getText());
                meeting.setDateTime(DateConverter.dateToCalendar(newDate));
                meeting.setDescription(descriptionText.getText());
                meeting.setMarker(Marker.valueOf(markerComboBox.getValue()));
                meeting.setLocation(locationText.getText());
                controller.updateEvent(meeting);

                SceneBuilder creteChoiceScene = new CreateChoiceSceneBuilder(controller);
                creteChoiceScene.showScene();
            }
        });

        deleteButton.setOnAction(new EventHandler<ActionEvent>() {
             @Override
             public void handle(ActionEvent event) {
                 Alert confermationAlert = new Alert(Alert.AlertType.CONFIRMATION);
                 confermationAlert.setTitle("Confirmation");
                 confermationAlert.setHeaderText("Are you sure you want to delete this event?");
                 confermationAlert.setContentText(String.format("Are you sure you want to delete event with the mane" +
                         meeting.getName()));

                 ButtonType yesButton = new ButtonType("Yes");
                 ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

                 confermationAlert.getButtonTypes().setAll(yesButton, noButton);

                 Optional<ButtonType> result = confermationAlert.showAndWait();

                 if (result.get() == yesButton) {
                     controller.deleteEvent(meeting);
                     new UpdateEventPageSceneBuilder(controller).showScene();
                 }
             }});

        HBox hBox= new HBox(30);

        hBox.getChildren().addAll(okButton, cancelButton, deleteButton);

        borderPane.setBottom(hBox);
        Scene thisScene = new Scene(borderPane, 500, 500);
        controller.setScene(thisScene, "Update Event");
    }
}
