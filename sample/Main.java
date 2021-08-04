package sample;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        /*ViewManager view = new ViewManager();
        primaryStage = view.getStage();
        primaryStage.show();
        */
        LoginPage log = new LoginPage();
        primaryStage = log.getStage();
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
