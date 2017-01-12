package app.FrontEnd.Controllers;

import app.BussinessLayer.Services.EventServiceImpl;
import app.BussinessLayer.Services.Interfaces.EventService;
import app.DataLayer.domain.models.EventDA;
import app.DataLayer.domain.models.MeetingDA;
import app.DataLayer.domain.models.TaskDA;
import app.DataLayer.repositories.EventRepository;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.nio.file.OpenOption;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


/**
 * Created by Ico on 9.1.2017 г..
 */
@Component
public class EventController extends Application{

    private EventService eventService;

    private Stage primaryStage;

    public EventController() {
    }

    public EventController(String[] args) {
        this.main(args);
    }


    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.setPrimaryStage(primaryStage);
        primaryStage.setResizable(false);
        showMainScene();

    }

    public  void setScene(Scene sceneToSet, String title) {
        this.primaryStage.setTitle(title);
        this.primaryStage.setScene(sceneToSet);

        this.primaryStage.show();

        eventService.getEvents();
    }

    public void deleteEvent(EventDA eventToDelete){
        this.eventService.deleteEvent(eventToDelete);
    }



    private void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void initialiseDependencies(EventRepository repository){
        eventService = new EventServiceImpl(repository);
    }

    private List<String> getAllEvents(){
        List<String> eventsList = new ArrayList<>();

        eventsList.add("Event1");
        eventsList.add("Event2");
        eventsList.add("Event3");
        eventsList.add("Event4");
        eventsList.add("Event5");

        return eventsList;
    }

    private EventDA getEventByName(String name){
        return new TaskDA();
    }

    private LocalDate dateToLocalDate(Date date){
        LocalDate resultLocalDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return resultLocalDate;
    }

    public void showCreateChoicePage(){
        Label label = new Label("Please choose the type of event you want to create");

        final ObservableList<String> options = FXCollections.observableArrayList(
                "Meeting",
                "Task"
        );

        final ComboBox comboBox = new ComboBox(options);

        Button okButton = new Button("OK");

        okButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String selectedEventType = (String) comboBox.getValue();
                if (selectedEventType.equals("Task")){
                    showTaskCreateScene();
                } else if (selectedEventType.equals("Meeting")){
                    showMeetingCreateScene();
                }
            }
        });

        VBox layout = new VBox(30);

        layout.getChildren().addAll(label, comboBox, okButton);

        Scene thisScene = new Scene(layout,200, 200);

        setScene(thisScene, "Select event type");
    }

    public void showMainScene(){
        VBox vBox = new VBox(30);

        vBox.setAlignment(Pos.CENTER);

        BorderPane borderPane = new BorderPane();

        Label welcomeLabel = new Label("Please select an option to continue");

        borderPane.setTop(welcomeLabel);

        Button createButton = new Button("Create Event");


        createButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showCreateChoicePage();
            }
        });
        Button updateEventButton = new Button("Update Event");

        updateEventButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showUpdateChoiceScene();
            }
        });

        Button deleteEventButton = new Button("Delete Event");
        Button getAllEventsButton = new Button("Show All Events");

        getAllEventsButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showAllEventsScene();
            }
        });

        deleteEventButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showDeleteEventScene();
            }
        });

        vBox.getChildren().addAll(welcomeLabel, createButton, updateEventButton, deleteEventButton, getAllEventsButton);

        borderPane.setCenter(vBox);


        Scene mainScene = new Scene(borderPane, 500, 500);

        setScene(mainScene, "Menu");

    }

    public void showTaskCreateScene(){
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
        Button cancelButton = new Button("Cancel");

        cancelButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showMainScene();
            }
        });

        HBox hBox= new HBox(30);

        hBox.getChildren().addAll(okButton, cancelButton);

        borderPane.setBottom(hBox);
        Scene thisScene = new Scene(borderPane, 500, 500);
        setScene(thisScene, "Create Event");
    }

    public void showMeetingCreateScene(){
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
        Button cancelButton = new Button("Cancel");

        cancelButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showMainScene();
            }
        });

        HBox hBox= new HBox(30);

        hBox.getChildren().addAll(okButton, cancelButton);

        borderPane.setBottom(hBox);
        Scene thisScene = new Scene(borderPane, 500, 500);
        setScene(thisScene, "Create Event");
    }

    public void showAllEventsScene(){
        BorderPane mainPane = new BorderPane();

        Label text = new Label("All events:");

        ListView<String> allEventsList = new ListView<>();

        ObservableList<String> observableList = FXCollections.observableArrayList(getAllEvents());

        allEventsList.setItems(observableList);

        Button goToMenuButton = new Button("Go to main menu");

        goToMenuButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showMainScene();
            }
        });

        mainPane.setTop(text);
        mainPane.setCenter(allEventsList);
        mainPane.setBottom(goToMenuButton);

        Scene thisScene = new Scene(mainPane, 500, 500);

        setScene(thisScene, "All events");
    }

    public void showUpdateChoiceScene(){
        BorderPane borderPane = new BorderPane();
        HBox bottomLayout = new HBox(30);
        VBox vbox = new VBox(30);
        HBox topLayout = new HBox(30);

        Label text = new Label("Enter the name of the event you want to update");
        text.setAlignment(Pos.CENTER);

        final TextField updateEventName = new TextField();

        updateEventName.setMaxSize(250, 5);

        Button okButton = new Button("OK");
        Button cancelButton = new Button("cancel");

        okButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                EventDA dbEvent = getEventByName(updateEventName.getText());

                if (dbEvent == null){
                    showMainScene();
                } else{
                    if (dbEvent instanceof TaskDA){
                        showUpdateTaskScene((TaskDA) dbEvent);
                    } else if (dbEvent instanceof  EventDA){
                        showUpdateMeetingScene((MeetingDA) dbEvent);
                    }
                }

            }
        });

        cancelButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showMainScene();
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

        Scene thisScene = new Scene(borderPane, 500, 500);

        setScene(thisScene, "Choose scene to update");
    }

    public void showUpdateTaskScene(TaskDA task){
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


        TextField nameTextField = new TextField(task.getName());
        DatePicker datePicker = new DatePicker();
        TextField hourTextField = new TextField( Integer.toString(task.getDateTime().getHours()));
        TextField minutesTextField = new TextField(Integer.toString(task.getDateTime().getMinutes()));
        TextArea descriptionText = new TextArea(task.getDescription());

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
        Button cancelButton = new Button("Cancel");

        cancelButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showMainScene();
            }
        });

        HBox hBox= new HBox(30);

        hBox.getChildren().addAll(okButton, cancelButton);

        borderPane.setBottom(hBox);
        Scene thisScene = new Scene(borderPane, 500, 500);
        setScene(thisScene, "Update Event");
    }

    public void showUpdateMeetingScene(MeetingDA meeting){
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
        DatePicker datePicker = new DatePicker(dateToLocalDate(meeting.getDateTime()));
        TextField hourTextField = new TextField(Integer.toString(meeting.getDateTime().getHours()));
        TextField minutesTextField = new TextField(Integer.toString(meeting.getDateTime().getMinutes()));
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

        cancelButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showMainScene();
            }
        });

        HBox hBox= new HBox(30);

        hBox.getChildren().addAll(okButton, cancelButton);

        borderPane.setBottom(hBox);
        Scene thisScene = new Scene(borderPane, 500, 500);
        setScene(thisScene, "Update Event");
    }

    public void showDeleteEventScene(){
        BorderPane borderPane = new BorderPane();
        HBox bottomLayout = new HBox(30);
        VBox vbox = new VBox(30);
        HBox topLayout = new HBox(30);

        Label text = new Label("Enter the name of the event you want to delete");
        text.setAlignment(Pos.CENTER);

        final TextField deleteEventByName = new TextField();

        deleteEventByName.setMaxSize(250, 5);

        Button okButton = new Button("OK");
        final Button cancelButton = new Button("cancel");

        okButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               EventDA eventToDelete = getEventByName(deleteEventByName.getText());

               if (eventToDelete == null){
                   Alert eventNotFoundAlert = new Alert(Alert.AlertType.ERROR);

                   eventNotFoundAlert.setHeaderText("404");
                   eventNotFoundAlert.setHeaderText("Event not found");
                   eventNotFoundAlert.setContentText(
                           String.format("An event with the name " +
                                   deleteEventByName.getText() + " was not found, try again with another name"));

                   eventNotFoundAlert.showAndWait();
               }else {
                   Alert confermationAlert = new Alert(Alert.AlertType.CONFIRMATION);
                    confermationAlert.setTitle("Confirmation");
                    confermationAlert.setHeaderText("Are you sure?");
                    confermationAlert.setContentText(String.format("Are you sure you want to delete" +
                            " an event with the name " + deleteEventByName.getText()));

                    ButtonType yesButton = new ButtonType("Yes");
                    ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

                    confermationAlert.getButtonTypes().setAll(yesButton, noButton);

                   Optional<ButtonType> result = confermationAlert.showAndWait();

                   if (result.get() == yesButton){
                       deleteEvent(eventToDelete);
                       deleteEventByName.setText("");
                   } else {
                       showDeleteEventScene();
                   }
               }

               cancelButton.setOnAction(new EventHandler<ActionEvent>() {
                   @Override
                   public void handle(ActionEvent event) {
                       showMainScene();
                   }
               });

            }
        });

        cancelButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showMainScene();
            }
        });


        vbox.getChildren().add(deleteEventByName);

        topLayout.setAlignment(Pos.CENTER);

        topLayout.getChildren().add(text);

        borderPane.setTop(text);

        vbox.setAlignment(Pos.CENTER);

        borderPane.setCenter(vbox);

        bottomLayout.setAlignment(Pos.CENTER);

        bottomLayout.getChildren().addAll(okButton, cancelButton);

        borderPane.setBottom(bottomLayout);

        Scene thisScene = new Scene(borderPane, 500, 500);

        setScene(thisScene, "Choose scene to delete");
    }



}








