package app.FrontEnd.Controllers;

import app.BussinessLayer.Services.Interfaces.EventService;
import app.FrontEnd.EventHandlers.CreateEventHandler;
import app.FrontEnd.Views.CreateChoicePage;
import app.FrontEnd.Views.CreatePageView;
import app.FrontEnd.Views.MainPage;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;


/**
 * Created by Ico on 9.1.2017 г..
 */
@Component
public class EventController extends Application{
    @Autowired
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
        setScene(new MainPage().createScene(), "Menu");

    }

    public  void setScene(Scene sceneToSet, String title) {
        this.primaryStage.setTitle(title);
        this.primaryStage.setScene(sceneToSet);
        this.primaryStage.show();
    }


    public void setCreateScrene(Stage primaryStage){
        Label createLabel = new Label("this is create screen");

        VBox layout = new VBox(20);

        layout.getChildren().add(createLabel);

        Scene thisScene = new Scene(layout, 300, 300);

        primaryStage.setTitle("Create");

    }

    private void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

}
