package app.FrontEnd.Views;

import app.FrontEnd.Controllers.EventController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Ico on 10.1.2017 г..
 */
@Component
public class MainPage{

    public MainPage() {
    }

    public Scene createScene(){
        Label welcomelebel = new Label("Please select an option to continue");
        Button createButton = new Button("Create Event");


        createButton.setOnAction(new CreateEventHandler());
        Button updateEvent = new Button("Update Event");
        Button deleteEvent = new Button("Delete Event");

        VBox layout = new VBox(20);
        layout.getChildren().addAll(welcomelebel,createButton, updateEvent, deleteEvent);
        Scene mainScene = new Scene(layout, 200, 200);

        return mainScene;
    }
}
