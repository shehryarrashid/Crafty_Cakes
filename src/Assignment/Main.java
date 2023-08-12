package Assignment;

import Assignment.GUI.Controller.WelcomeScreenController;
import Assignment.Loggs.MyLogger;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) throws Exception {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        MyLogger.log("Application Started");
        WelcomeScreenController welcome = new WelcomeScreenController();
        welcome.showWelcomeScene();
    }


}
