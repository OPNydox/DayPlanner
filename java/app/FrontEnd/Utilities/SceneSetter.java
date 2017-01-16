package app.FrontEnd.Utilities;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by Ico on 16.1.2017 г..
 */
public class SceneSetter extends Application {
    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
    }

    public void setScene(Scene sceneToSet, String title){
        this.primaryStage.setTitle(title);
        this.primaryStage.setScene(sceneToSet);
        this.primaryStage.show();

    }
}
