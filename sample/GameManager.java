package sample;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class GameManager {
    private AnchorPane gamePane;
    public static AnchorPane gp;
    private Scene gameScene;
    static enemyTOWER enem1,enem2;
    static Player Sp;
    static Enemy StaticEnemy;
    private Label timeCounter;
    private int seconds;
    static int playerCrowns = 0, enemyCrowns = 0;
    public GameManager(String p){
        Initialize();
        gp = gamePane;
        createBackground();
        Sp = new Player(p);
        Enemy enem = new Enemy();
        StaticEnemy = enem;
        enem1 = enem.getEnt1();
        enem2 = enem.getEnt2();
        createTimerBox();
        createTimer();
    }
    private void Initialize(){
        gamePane = new AnchorPane();
        gameScene = new Scene(gamePane,600,900);
    }

    Scene getGameScene(){
        return gameScene;
    }

    private void createBackground(){
        Image bg = new Image("sample/Resources/Untitled-2.png",600,700,true,false);
        BackgroundImage bgi = new BackgroundImage(bg,null,null,BackgroundPosition.CENTER,null);
        gamePane.setBackground(new Background(bgi));
    }

    private void createTimerBox(){
        Rectangle box = new Rectangle(35, 280);
        box.setFill(Color.valueOf("#121547"));
        timeCounter = new Label("180");
        timeCounter.setFont(Font.font("KenVector Future",15));
        timeCounter.setStyle("-fx-text-fill: white");
        timeCounter.setRotate(-90);
        StackPane countdown = new StackPane();
        countdown.getChildren().addAll(box,timeCounter);
        countdown.setLayoutX(545);
        countdown.setLayoutY(286);
        gamePane.getChildren().add(countdown);
    }

    private void createTimer(){
        AnimationTimer time = new AnimationTimer() {

            private long startTime = 0;
            @Override
            public void handle(long now) {
                if (startTime != 0) {
                    if (now > startTime + 1_000_000_000) {
                        seconds++;
                        timeCounter.setText(Integer.toString(180-seconds));
                        startTime = now;
                    }
                } else {
                    startTime = now;
                }
            }
        };
        time.start();
    }

    static void setCrowns(int x,int y){
        playerCrowns = x;
        enemyCrowns = y;
    }

    public static int getPlayerCrowns() {
        return playerCrowns;
    }

    public static int getEnemyCrowns() {
        return enemyCrowns;
    }

    public static Enemy getStaticEnemy() {
        return StaticEnemy;
    }
}


