package app;

import app.backEnd.businessLayer.services.EventServiceImpl;
import app.backEnd.businessLayer.services.UserServiceImpl;
import app.backEnd.businessLayer.services.interfaces.EventService;
import app.backEnd.businessLayer.services.interfaces.UserService;
import app.backEnd.dataLayer.repositories.EventRepository;
import app.backEnd.dataLayer.repositories.UserRepository;
import app.common.models.ViewModels.EventView;
import app.common.models.ViewModels.UserView;
import app.common.models.daModels.eventModels.EventDA;
import app.common.models.daModels.userModels.UserDA;
import app.frontEnd.views.modelViews.EventViewerImpl;
import app.frontEnd.views.viewBuilders.LoginScreenSceneBuilder;
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
public class MainController extends Application{

    private UserDA currentUser;

    private EventRepository eventRepository;

    private EventService eventService;

    private ConfigurableApplicationContext springContext;

    private static Stage window;

    private UserRepository userRepository;

    private UserService userService;

    public static void main(String[] args){

        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setResizable(false);
        window = primaryStage;

        userRepository = springContext.getBean(UserRepository.class);
        eventRepository = springContext.getBean(EventRepository.class);
        userService = new UserServiceImpl(userRepository);
        eventService = new EventServiceImpl(eventRepository, userService);

        new LoginScreenSceneBuilder(this).showScene();
        //new MainSceneBuilder(this).showScene();
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
        SpringApplicationBuilder builder = new SpringApplicationBuilder(MainController.class);
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
        eventService.registerEvent(newEvent, currentUser.getName());
        currentUser.getEvents().add(eventService.getEventByName(newEvent.getName()));
    }

    public void updateEvent(EventDA eventToUpdate){
        eventService.updateEvent(eventToUpdate);
    }

    public List<EventDA> getAllEvents(){
        List<EventDA> allEvents = currentUser.getEvents();
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

    public UserDA getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(UserDA currentUser) {
        this.currentUser = currentUser;
    }

    public void registerUser(UserView userView){
        userService.registerUser(userView);

        this.setCurrentUser(userService.getUser(userView));
    }

    public boolean logUser(UserView user){
        UserDA userDA = userService.getUser(user.getUserName());

        if (userDA.getPassword() != user.getPassword().hashCode()){
            return false;
        }else{
            this.setCurrentUser(userDA);
            return true;
        }
    }
}
