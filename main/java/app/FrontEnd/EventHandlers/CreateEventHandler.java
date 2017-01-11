package app.FrontEnd.EventHandlers;

import app.FrontEnd.Controllers.EventController;
import app.FrontEnd.Views.CreateChoicePage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

;

/**
 * Created by Ico on 10.1.2017 г..
 */
@Component
public class CreateEventHandler implements EventHandler<ActionEvent> {
    @Autowired
    private EventController controller;

    @Override
    public void handle(ActionEvent event) {
        this.controller.setScene(new CreateChoicePage().createScene(), "test");
    }
}
