package app;


import app.FrontEnd.Controllers.EventController;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class Terminal implements CommandLineRunner {
    @Override
    public void run(String... strings) throws Exception {
        EventController controller = new EventController(new String[0]);
        System.out.println("potato");
    }

}








