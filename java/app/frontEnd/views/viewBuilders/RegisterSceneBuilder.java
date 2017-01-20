package app.frontEnd.views.viewBuilders;


import app.MainController;
import app.common.constants.PageConstants;
import app.common.models.ViewModels.UserView;
import app.frontEnd.views.viewBuilders.interfaces.SceneBuilder;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class RegisterSceneBuilder implements SceneBuilder{
    MainController controller;

    public RegisterSceneBuilder(MainController controller) {
        this.controller = controller;
    }

    @Override
    public void showScene() {
        BorderPane mainLayout = new BorderPane();
        VBox centerLayout = new VBox(10);
        HBox bottomLayout = new HBox();

        Label userNameLabel = new Label("Enter username");
        Label passwordLaberl = new Label("Enter password");
        Label repeatPassword = new Label("Repeat password");

        TextField usernameText = new TextField();
        PasswordField passwordField = new PasswordField();
        PasswordField repeatPassField = new PasswordField();

        Button okButton = new Button("Register");
        Button backButton = new Button("Cancel");

        backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                SceneBuilder loginScreen = new LoginScreenSceneBuilder(controller);
                loginScreen.showScene();
            }
        });

        okButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (passwordField.getText().equals(repeatPassField.getText())){
                    UserView userToregister = new UserView();
                    if (usernameText.getText().isEmpty()){
                        controller.showIllegalArgumentsAlert("Please enter name");
                        return;
                    }
                    if (passwordField.getText().isEmpty()){
                        controller.showIllegalArgumentsAlert("Please enter password");
                        return;
                    }

                    userToregister.setUserName(usernameText.getText());
                    userToregister.setPassword(passwordField.getText());

                    controller.registerUser(userToregister);

                    SceneBuilder loginScene = new LoginScreenSceneBuilder(controller);
                    loginScene.showScene();
                }
            }
        });

        backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                SceneBuilder loginScene  = new LoginScreenSceneBuilder(controller);
                loginScene.showScene();
            }
        });

        centerLayout.getChildren().add(userNameLabel);
        centerLayout.getChildren().add(usernameText);
        centerLayout.getChildren().add(passwordLaberl);
        centerLayout.getChildren().add(passwordField);
        centerLayout.getChildren().add(repeatPassword);
        centerLayout.getChildren().add(repeatPassField);
        centerLayout.getChildren().add(okButton);

        mainLayout.setCenter(centerLayout);

        bottomLayout.getChildren().add(backButton);

        mainLayout.setBottom(bottomLayout);

        Scene thisScene = new Scene(mainLayout, PageConstants.SCREEN_WIDTH, PageConstants.SCREEN_HEIGHT);

        controller.setScene(thisScene, "register");
    }
}
