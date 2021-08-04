package sample;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.util.Duration;

import java.awt.*;
import java.awt.event.InputEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class connectToServer {
    private Socket player;
    private boolean enemyFound = false;
    private String playerName, enemy;
    private Label success, failure;
    private BufferedReader reader;
    static PrintWriter writer;
    private GameManager newgame;
    connectToServer(String name){
        playerName = name;
        createSuccessLabel();
        System.out.println("Done");
    }

    void createSuccessLabel(){
        success = makeLabels("Player Found! Starting Match!");
        failure = makeLabels("Error In Starting The Match!");
        ViewManager.staticPane.getChildren().addAll(success,failure);
        System.out.println("Done2");
    }

    Label makeLabels(String text){
        Label label = new Label(text);
        label.setFont(Font.font("KenVector Future",25));
        label.setPrefWidth(600);
        label.setLayoutY(400);
        label.setAlignment(Pos.CENTER);
        label.setVisible(false);
        return label;
    }

    void doIt(){
        try{
            startMatching();
            setUpStrams();
            waitForEnemy();
            openWorld();
            startRocking();
        }catch (Exception e){
            e.printStackTrace();
            setLabelVisible(failure);
        }
    }

    void startMatching(){
        try {
            player = new Socket("localhost",2786);
        } catch (Exception e) {
            e.printStackTrace();
            setLabelVisible(failure);
        }
    }

    void setUpStrams(){
        try {
            reader = new BufferedReader(new InputStreamReader(player.getInputStream()));
            writer = new PrintWriter(player.getOutputStream());
            writer.flush();
        } catch (Exception e) {
            e.printStackTrace();
            setLabelVisible(failure);
        }
    }

    void waitForEnemy(){
        try {
            String command = reader.readLine();
            if(command.equals("DONE")){
                setLabelVisible(success);
                writer.println(playerName);
                writer.flush();
            }
            if(!enemyFound && !command.contains("DONE")){
                enemy = command;
                System.out.println(command);
            }
        } catch (IOException e) {
            e.printStackTrace();
            setLabelVisible(failure);
        }
    }

    void waitABit(int time){
        Platform.runLater(()->{
            PauseTransition delay = new PauseTransition(Duration.seconds(time));
            delay.play();
            delay.setOnFinished(e->{
                Platform.runLater(()->ViewManager.StartScene.setTranslateY(900));
            });
        });
    }

    void setLabelVisible(Label toDo){
        Platform.runLater(()->toDo.setVisible(true));
    }

    void openWorld(){
        System.out.println("Opening1");
        PauseTransition delay = new PauseTransition(Duration.seconds(2));
        delay.setOnFinished(e->{
            System.out.println("Opening");
            Platform.runLater(()->startGame());
        });
        delay.play();
    }

    void startGame(){
        ViewManager.StartScene.setTranslateY(900);
        PauseTransition gameTime =  new PauseTransition(Duration.seconds(182));
        newgame = new GameManager(playerName);
        ViewManager.stage2.setScene(newgame.getGameScene());
        gameTime.play();
        gameTime.setOnFinished(y->{
            endGame end = new endGame();
            ViewManager.stage2.setScene(end.getEndScene());
        } );
    }

    void startRocking(){
        String enemyCommand;
        System.out.println("At least called");
        while(true){
            try {
                enemyCommand = reader.readLine();
                if(enemyCommand.contains("Skeleton")){
                    String command[] = enemyCommand.split(",");
                    String side = command[1];
                    Robot robot = new Robot();
                    robot.mouseMove(70,10);
                    robot.mousePress(InputEvent.BUTTON1_MASK);
                    robot.mouseRelease(InputEvent.BUTTON1_MASK);
                    int x = (Integer.valueOf(side)==1)?70:470;
                    robot.mouseMove(x,190);
                    robot.mousePress(InputEvent.BUTTON1_MASK);
                    robot.mouseRelease(InputEvent.BUTTON1_MASK);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
