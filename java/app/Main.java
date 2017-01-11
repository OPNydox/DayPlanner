package app;

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
    //    String buttonName = "success";
   //    String[] someArr = new String[1];

   //    someArr[0] = buttonName;
   //    new MainPageView().main(someArr);
   //     frame.setContentPane(new MainPageView().getContentPane());
   //     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   //     frame.pack();
   //     frame.setVisible(true);

        EventController controller = new EventController(args);
    }
}
