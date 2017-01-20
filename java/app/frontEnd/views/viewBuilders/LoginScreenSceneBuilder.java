package app.frontEnd.views.viewBuilders;


import app.MainController;
import app.common.constants.PageConstants;
import app.common.models.ViewModels.UserView;
import app.frontEnd.views.viewBuilders.interfaces.SceneBuilder;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class LoginScreenSceneBuilder implements SceneBuilder {
    private MainController controller;

    public LoginScreenSceneBuilder(MainController controller) {
        this.controller = controller;
    }

    @Override
    public void showScene() {
        BorderPane mainLayout = new BorderPane();
        VBox centerLayout = new VBox(10);
        HBox bottomLayout = new HBox(10);

        Label userNameLabel = new Label("Username:");
        Label passwordLabel = new Label("Password:");

        TextField usernameTextField = new TextField();
        PasswordField passwordField = new PasswordField();

        Button loginButton = new Button("Log in");

        centerLayout.getChildren().add(userNameLabel);
        centerLayout.getChildren().add(usernameTextField);
        centerLayout.getChildren().add(passwordLabel);
        centerLayout.getChildren().add(passwordField);
        centerLayout.getChildren().add(loginButton);

        mainLayout.setCenter(centerLayout);

        Button registerButton = new Button("Create new account");

        registerButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                SceneBuilder registerUser = new RegisterSceneBuilder(controller);

                registerUser.showScene();
            }
        });

        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                UserView user = new UserView();

                user.setUserName(usernameTextField.getText());
                user.setPassword(passwordField.getText());

                boolean flag = false;

                try {
                    flag = controller.logUser(user);
                } catch (NullPointerException ex){
                    controller.showIllegalArgumentsAlert("Wrong username or password");
                    return;
                }
                if (flag == true){
                    SceneBuilder mainScene = new MainSceneBuilder(controller);
                    mainScene.showScene();
                }else {
                    controller.showIllegalArgumentsAlert("Wrong  username or password");
                    return;
                }

            }
        });

        bottomLayout.setAlignment(Pos.BASELINE_RIGHT);

        bottomLayout.getChildren().add(registerButton);

        mainLayout.setBottom(bottomLayout);

        Scene thisScene = new Scene(mainLayout, PageConstants.SCREEN_WIDTH, PageConstants.SCREEN_HEIGHT);

        controller.setScene(thisScene, "Log in");
    }
}
