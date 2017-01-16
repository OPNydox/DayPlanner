package app;

import app.BussinessLayer.Services.EventServiceImpl;
import app.BussinessLayer.Services.Interfaces.EventService;
import app.Common.enums.Marker;
import app.Common.models.ViewModels.EventView;
import app.Common.models.ViewModels.MeetingView;
import app.Common.models.ViewModels.TaskView;
import app.Common.utilities.DateConverter;
import app.Common.models.daModels.eventModels.EventDA;
import app.Common.models.daModels.eventModels.MeetingDA;
import app.Common.models.daModels.eventModels.TaskDA;
import app.DataLayer.repositories.EventRepository;
import app.FrontEnd.Utilities.Validator;
import app.FrontEnd.Views.MyListView;
import com.toedter.calendar.JCalendar;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingNode;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.*;
import java.util.List;


@SpringBootApplication
public class Main  extends Application{


    private EventRepository eventRepository;

    private EventService eventService;

    private ConfigurableApplicationContext springContext;

    private static Stage window;

    public static void main(String[] args){

        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        eventRepository = springContext.getBean(EventRepository.class);
        eventService = new EventServiceImpl(eventRepository);

        showMainScene();
    }

    @Override
    public void init() throws Exception{
        springContext = bootstrapSpringApplicationContext();
    }

    @Override
    public void stop()throws Exception{
        springContext.stop();
        Platform.exit();
        System.exit(0);
    }

    public  void setScene(Scene sceneToSet, String title) {
        this.window.setTitle(title);
        this.window.setScene(sceneToSet);
        this.window.show();
    }

    private ConfigurableApplicationContext bootstrapSpringApplicationContext() {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(Main.class);
        String[] args = getParameters().getRaw().stream().toArray(String[]::new);
        builder.headless(false);
        return builder.run(args);
    }

    public void deleteEvent(EventDA eventToDelete){
        this.eventService.deleteEvent(eventToDelete);
    }


    private void addEvent(EventView newEvent){
        eventService.registerEvent(newEvent);
    }

    private void updateEvent(EventDA eventToUpdate){
        eventService.updateEvent(eventToUpdate);
    }

    private List<EventDA> getAllEvents(){
        List<EventDA> allEvents = eventService.getEvents();


        return allEvents;
    }

    private List<String> getAllEventsAsString(List<EventDA> events){
        List<String> eventsAsStrings = new LinkedList<>();

        for (EventDA event : events) {
            eventsAsStrings.add(event.toString());
        }

        return eventsAsStrings;
    }

    private EventDA getEventByName(String name){
        return eventService.getEventByName(name);
    }

    private List<String> getAllEventsByDateStringed(Calendar date){
        return getAllEventsAsString(eventService.getEventsByDate(date));
    }

    private List<String> getAllEventByMonthStringed(int month){
        return getAllEventsAsString(eventService.getEventsByMonth(month));
    }


    public void showCreateChoicePage(){
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
                    showIllegalArgumentsAlert("Please select an option");
                    return;
                }


                if (selectedEventType.equals("Task")){
                    showTaskCreateScene();
                } else if (selectedEventType.equals("Meeting")){
                    showMeetingCreateScene();
                }
            }
        });

        cancelButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showMainScene();
            }
        });

        bottomLayout.getChildren().addAll(okButton, cancelButton);

        mainLayout.setTop(topLayout);
        mainLayout.setCenter(centerLayout);
        mainLayout.setBottom(bottomLayout);

        Scene thisScene = new Scene(mainLayout,500, 500);

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
        Button calendarButton = new Button("Show calendar");

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

        calendarButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showCalendarScene();
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

        okButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                TaskView newTask = new TaskView();

                newTask.setName(nameTextField.getText());
                try{
                    newTask.setDate(DateConverter.dateToCalendar(DateConverter.LocalDateToDate(datePicker.getValue())));
                } catch (NullPointerException ex){
                    showIllegalArgumentsAlert("Event must have a date!");
                    return;
                }

                newTask.setDescription(descriptionText.getText());
                try {
                    newTask.setMarker(markerComboBox.getValue());
                }catch (NumberFormatException ex){
                    showIllegalArgumentsAlert("Please pick a marker");
                    return;
                }

                try{
                    newTask.setHour(hourTextField.getText());
                    newTask.setMinutes(minutesTextField.getText());
                } catch (NumberFormatException ex){
                    showIllegalArgumentsAlert("Wrong hour or minute format!");
                    return;
                }

                newTask.setType("Task");

                try {
                    Validator.validateEvent(newTask, eventService);
                }catch (IllegalArgumentException ex){
                    showIllegalArgumentsAlert(ex.getMessage());
                    return;
                }

                addEvent(newTask);
                showTaskCreateScene();
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

        okButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                MeetingView newMeeting = new MeetingView();

                newMeeting.setName(nameTextField.getText());
                try{
                    newMeeting.setDate(DateConverter.dateToCalendar(DateConverter.LocalDateToDate(datePicker.getValue())));
                } catch (NullPointerException ex){
                    showIllegalArgumentsAlert("Event must have a date!");
                    return;
                }

                newMeeting.setDescription(descriptionText.getText());
                try {
                    newMeeting.setMarker(markerComboBox.getValue());
                }catch (NumberFormatException ex){
                    showIllegalArgumentsAlert("Please pick a marker");
                    return;
                }

                try{
                    newMeeting.setHour(hourTextField.getText());
                    newMeeting.setMinutes(minutesTextField.getText());
                } catch (NumberFormatException ex){
                    showIllegalArgumentsAlert("Wrong hour or minute format!");
                    return;
                }

                newMeeting.setType("Task");

                try {
                    Validator.validateEvent(newMeeting, eventService);
                }catch (IllegalArgumentException ex){
                    showIllegalArgumentsAlert(ex.getMessage());
                    return;
                }
                newMeeting.setLocation(locationText.getText());
                newMeeting.setType("Meeting");

                addEvent(newMeeting);
                showTaskCreateScene();
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

        ObservableList<String> observableList = FXCollections.observableArrayList(getAllEventsAsString(getAllEvents()));

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

        TextField updateEventName = new TextField();

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
        DatePicker datePicker = new DatePicker(DateConverter.dateToLocalDate(DateConverter.calendarToDate(task.getDateTime())));
        TextField hourTextField = new TextField( Integer.toString(task.getDateTime().getTime().getHours()));
        TextField minutesTextField = new TextField(Integer.toString(task.getDateTime().getTime().getMinutes()));
        TextArea descriptionText = new TextArea(task.getDescription());

        ObservableList<String> options = FXCollections.observableArrayList(
                "Private",
                "Confidential",
                "Personal"
        );

        ComboBox<String> markerComboBox = new ComboBox<>(options);

        markerComboBox.setValue(task.getMarker().toString());

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

        okButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Date newDate = DateConverter.LocalDateToDate(datePicker.getValue());
                newDate.setHours(Integer.parseInt(hourTextField.getText()));
                newDate.setMinutes(Integer.parseInt(minutesTextField.getText()));

                task.setName(nameTextField.getText());
                task.setDateTime(DateConverter.dateToCalendar(newDate));
                task.setDescription(descriptionText.getText());
                task.setMarker(Marker.valueOf(markerComboBox.getValue()));
                updateEvent(task);
                showUpdateChoiceScene();
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

        cancelButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showMainScene();
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
                updateEvent(meeting);
                showUpdateChoiceScene();
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

    public void showCalendarScene(){
        BorderPane mainLayout = new BorderPane();
        HBox bottomLayout = new HBox(30);
        JCalendar jCalendar = new JCalendar();

        jCalendar.addPropertyChangeListener("calendar", new PropertyChangeListener() {

            @Override
            public void propertyChange(PropertyChangeEvent e) {
                final Calendar c = (Calendar) e.getNewValue();
                MyListView listView = new MyListView("Events for day", getAllEventsByDateStringed(c));
                listView.setVisible(true);
            }
        });

        SwingNode swingCalendar = new SwingNode();

        swingCalendar.setContent(jCalendar);

        mainLayout.setCenter(swingCalendar);

        Button cancelButton = new Button("cancel");
        Button getEventsButton = new Button("Get events for current month");

        cancelButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showMainScene();
            }
        });

        getEventsButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                List<String> listOfEvents =
                        getAllEventByMonthStringed(jCalendar.getMonthChooser().getMonth());

                ListView<String> eventView = new ListView<String>( FXCollections.observableArrayList(listOfEvents));

                BorderPane mainLayout = new BorderPane();

                mainLayout.setCenter(eventView);

                HBox bottomLayout = new HBox(40);

                Button backButton = new Button("Back");
                Button menuButton = new Button("Main Menu");

                backButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        showCalendarScene();
                    }
                });

                menuButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        showMainScene();
                    }
                });

                bottomLayout.getChildren().addAll(backButton, menuButton);

                mainLayout.setBottom(bottomLayout);

                Scene thisScene = new Scene(mainLayout, 500, 500);
                setScene(thisScene, "All events for the month");
            }
        });

        bottomLayout.setAlignment(Pos.CENTER);

        bottomLayout.getChildren().addAll(getEventsButton,cancelButton);

        mainLayout.setBottom(bottomLayout);

        Scene thisScene = new Scene(mainLayout, 500,500);

        setScene(thisScene, "Calendar");
    }

    public void showIllegalArgumentsAlert(String description){
        Alert badObjectAlert = new Alert(Alert.AlertType.ERROR);
        badObjectAlert.setTitle("Invalid data providad");
        badObjectAlert.setHeaderText(description);
        badObjectAlert.setContentText("Invalid Arguments entered!");
        badObjectAlert.show();
    }


}
