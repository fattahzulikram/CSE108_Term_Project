package GameServer;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class createServer {
    private static TextArea mainArea;
    private AnchorPane mainPane;
    private Stage stage;
    private ServerSocket server;
    private Socket connection,connection2;
    private Label successLabel;
    private static String player1, player2;

    createServer(){
        createGUI();
    }

    private void createGUI(){
        mainPane = new AnchorPane();
        stage = new Stage();
        stage.setTitle("Server");
        Scene scene = new Scene(mainPane, 600, 600);
        mainPane.setStyle("-fx-background-color: black");
        stage.setOnCloseRequest(e-> System.exit(1));
        createArea();
        createLabels();
        stage.setScene(scene);
    }

    private void createArea(){
        mainArea = new TextArea();
        mainArea.setLayoutY(78);
        mainArea.setLayoutX(50);
        mainArea.setPrefWidth(500);
        mainArea.setPrefHeight(460);
        mainArea.setStyle("-fx-control-inner-background: #b1d8b1");
        mainArea.setFont(Font.font("KenVector Future",18));
        mainArea.setEditable(false);
        mainPane.getChildren().add(mainArea);
    }

    private void createLabels(){
        successLabel = new Label("Game Server");
        successLabel.setLayoutX(190);
        successLabel.setLayoutY(28);
        successLabel.setPrefWidth(220);
        successLabel.setAlignment(Pos.CENTER);
        successLabel.setFont(Font.font("KenVector Future",24));
        successLabel.setStyle("-fx-text-fill: RED; -fx-background-color: #bbd3f9");
        mainPane.getChildren().addAll(successLabel);
    }

    void startServer(){
        showMessage(" ("+new SimpleDateFormat("dd/MM/yyyy, HH:mm:ss").format(new Date())+"): " +"Server Started");
        try {
            server = new ServerSocket(2786);
            while(true){
                try{
                    waitForConnection();
                    setUpThread();
                }catch(Exception e){
                    showMessage("\nError Occurred! Terminating Now!");
                    break;
                }
            }
        } catch (IOException e) {
            showMessage("\nError Occurred! Terminating Now!");
        }
    }

    private void waitForConnection(){
        try {
            connection = server.accept();
            connection2 = server.accept();
        } catch (IOException e) {
            showMessage("\nConnection Failed!");
        }
    }

    private void setUpThread(){
        if(connection.isConnected() && connection2.isConnected()){
            gameThread startGame = new gameThread(connection,connection2,mainArea);
            new Thread(startGame).start();
        }
    }

    public static void setPlayer1(String player1) {
        createServer.player1 = player1;
    }

    public static void setPlayer2(String player2) {
        createServer.player2 = player2;
    }

    private void showMessage(String text){
        Platform.runLater(() -> mainArea.appendText(text+"\n"));
    }

    public static String getPlayer1() {
        return player1;
    }

    public static String getPlayer2() {
        return player2;
    }

    Stage getStage() {
        return stage;
    }

    private static void setArea(){
        Platform.runLater(()->mainArea.appendText(" ("+new SimpleDateFormat("dd/MM/yyyy, HH:mm:ss").format(new Date())+"): " + player1+" vs "+player2+"Started"));
    }

}
