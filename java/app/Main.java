package app;

import app.BussinessLayer.Services.EventServiceImpl;
import app.BussinessLayer.Services.Interfaces.EventService;
import app.Common.models.ViewModels.EventView;
import app.Common.models.daModels.eventModels.EventDA;
import app.DataLayer.repositories.EventRepository;
import app.FrontEnd.Utilities.SceneSetter;
import app.FrontEnd.Views.ViewBuilders.MainSceneBuilder;
import app.FrontEnd.Views.modelViews.EventViewerImpl;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Calendar;
import java.util.Comparator;
import java.util.LinkedList;
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
        EventViewerImpl eventViewer = new EventViewerImpl();

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

    public List<String> getAllEventByMonthStringed(int month, int year){
        return getAllEventsAsString(eventService.getEventsByMonth(month, year));
    }


    public void showIllegalArgumentsAlert(String description){
        Alert badObjectAlert = new Alert(Alert.AlertType.ERROR);
        badObjectAlert.setTitle("Invalid data providad");
        badObjectAlert.setHeaderText(description);
        badObjectAlert.setContentText("Invalid Arguments entered!");
        badObjectAlert.show();
    }



}
