package app.FrontEnd.Views;
import app.FrontEnd.Controllers.EventController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

/**
 * Created by Ico on 11.1.2017 г..
 */
@Configurable
public class CreateChoicePage {
    @Autowired
    private EventController controller;

    public CreateChoicePage() {
    }

    public Scene createScene(){
        Label label = new Label("Please choose the type of event you want to create");

        ObservableList<String> options = FXCollections.observableArrayList(
            "Meeting",
            "Task"
        );

        final ComboBox comboBox = new ComboBox(options);

        Button okButton = new Button("OK");

        okButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controller.setScene(
                        new CreatePageView().createScene(comboBox.getValue().toString()),
                        "Create page");
            }
        });

        VBox layout = new VBox(30);

        layout.getChildren().addAll(label, comboBox, okButton);

        Scene thisScene = new Scene(layout,200, 200);

        return thisScene;
    }
}
