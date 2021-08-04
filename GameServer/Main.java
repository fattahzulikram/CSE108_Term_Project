package GameServer;

import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        createServer server = new createServer();
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() {
                server.startServer();
                return null;
            }
        };
        new Thread(task).start();
        server.getStage().show();
    }

    public static void main(String[] args){launch(args);}
}

