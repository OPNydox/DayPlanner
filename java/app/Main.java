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
import app.FrontEnd.Utilities.SceneSetter;
import app.FrontEnd.Utilities.Validator;
import app.FrontEnd.Views.MyListView;
import app.FrontEnd.Views.ViewBuilders.MainSceneBuilder;
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

    private SceneSetter sceneSetter;

    public static void main(String[] args){

        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        eventRepository = springContext.getBean(EventRepository.class);
        eventService = new EventServiceImpl(eventRepository);

        new MainSceneBuilder(this).showScene();
        //sceneSetter = new SceneSetter();
       // sceneSetter.start(primaryStage);

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

    public EventService getEventService() {
        return eventService;
    }

    public void deleteEvent(EventDA eventToDelete){
        this.eventService.deleteEvent(eventToDelete);
    }


    public void addEvent(EventView newEvent){
        eventService.registerEvent(newEvent);
    }

    public void updateEvent(EventDA eventToUpdate){
        eventService.updateEvent(eventToUpdate);
    }

    public List<EventDA> getAllEvents(){
        List<EventDA> allEvents = eventService.getEvents();
        allEvents.sort(new Comparator<EventDA>() {
            @Override
            public int compare(EventDA o1, EventDA o2) {
                return o1.getDateTime().compareTo(o2.getDateTime());
            }
        });

        return allEvents;
    }

    public List<String> getAllEventsAsString(List<EventDA> events){
        List<String> eventsAsStrings = new LinkedList<>();

        for (EventDA event : events) {
            eventsAsStrings.add(event.toString());
        }

        return eventsAsStrings;
    }

    public EventDA getEventByName(String name){
        return eventService.getEventByName(name);
    }

    public List<String> getAllEventsByDateStringed(Calendar date){
        return getAllEventsAsString(eventService.getEventsByDate(date));
    }

    public List<String> getAllEventByMonthStringed(int month){
        return getAllEventsAsString(eventService.getEventsByMonth(month));
    }


    public void showIllegalArgumentsAlert(String description){
        Alert badObjectAlert = new Alert(Alert.AlertType.ERROR);
        badObjectAlert.setTitle("Invalid data providad");
        badObjectAlert.setHeaderText(description);
        badObjectAlert.setContentText("Invalid Arguments entered!");
        badObjectAlert.show();
    }



}
