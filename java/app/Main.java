package app;

import app.DataLayer.repositories.EventRepository;
import app.FrontEnd.Controllers.EventController;
import app.FrontEnd.Views.MainPageView;
import app.FrontEnd.Views.TestView;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.swing.*;


@SpringBootApplication
public class Main {
    public static void main(String[] args){
        SpringApplication.run(Main.class, args);
    }
}
