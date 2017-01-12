package app.FrontEnd.Views;

import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import org.springframework.stereotype.Component;

/**
 * Created by Ico on 11.1.2017 г..
 */
@Component
public class CreatePageView {
    public CreatePageView() {
    }

    public Scene createScene(String option){
        return new Scene(new VBox(), 200, 200);
    }
}
