package app.frontEnd.views.viewBuilders;

import app.MainController;
import app.common.constants.PageConstants;
import app.common.models.daModels.eventModels.EventDA;
import app.frontEnd.views.viewBuilders.interfaces.SceneBuilder;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.Optional;


public class DeleteEventSceneBuilder implements SceneBuilder{
    private MainController controller;

    public DeleteEventSceneBuilder(MainController controller) {
        this.controller = controller;
    }

    @Override
    public void showScene() {
        BorderPane borderPane = new BorderPane();
        HBox bottomLayout = new HBox(PageConstants.SPACING);
        VBox vbox = new VBox(PageConstants.SPACING);
        HBox topLayout = new HBox(PageConstants.SPACING);

        Label text = new Label("Enter the name of the event you want to delete");
        text.setAlignment(Pos.CENTER);

        final TextField deleteEventByName = new TextField();

        deleteEventByName.setMaxSize(250, 5);

        Button okButton = new Button("OK");
        final Button cancelButton = new Button("cancel");

        okButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                EventDA eventToDelete = controller.getEventByName(deleteEventByName.getText());

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
                        controller.deleteEvent(eventToDelete);
                        deleteEventByName.setText("");
                    } else {
                        SceneBuilder deleteScene = new DeleteEventSceneBuilder(controller);
                        deleteScene.showScene();
                    }
                }

                cancelButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        SceneBuilder mainScene = new MainSceneBuilder(controller);
                        mainScene.showScene();
                    }
                });

            }
        });

        cancelButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                SceneBuilder mainScene = new MainSceneBuilder(controller);
                mainScene.showScene();
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

        Scene thisScene = new Scene(borderPane, PageConstants.SCREEN_WIDTH, PageConstants.SCREEN_HEIGHT);

        controller.setScene(thisScene, "Choose scene to delete");
    }
}
