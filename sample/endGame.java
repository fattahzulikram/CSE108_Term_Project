package sample;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;


class endGame {
    private AnchorPane endPane;
    private Scene scene;
    endGame(){
        endPane = new AnchorPane();
        Stage endStage = new Stage();
        createBackground();
        createPlayerBoxes();
        showCrowns();
        createReturnButton();
        showWinner();
        showLabels();
        scene = new Scene(endPane, 600, 900);
        endStage.setScene(scene);
        endPane.setOnMouseClicked(e-> System.out.println(e.getSceneX() + " " + e.getSceneY()));
    }

    private void createBackground(){
        Image bg = new Image("sample/Resources/BG.jpg");
        BackgroundImage bgi = new BackgroundImage(bg,null,null,BackgroundPosition.DEFAULT,null);
        endPane.setBackground(new Background(bgi));
    }

    private void createPlayerBoxes(){
        Label text = new Label("GAME OVER");
        text.setFont(Font.font("KenVector Future",45));
        text.setStyle("-fx-text-fill: #000442");
        text.setPrefWidth(600);
        text.setAlignment(Pos.CENTER);
        text.setLayoutY(60);
        Label playerName = new Label("Player");
        playerName.setFont(Font.font("KenVector Future",25));
        playerName.setStyle("-fx-text-fill: #27661d");
        playerName.setStyle("-fx-background-color: #bab416");
        playerName.setPrefWidth(600);
        playerName.setAlignment(Pos.CENTER);
        playerName.setLayoutY(240);
        Label enemyName = new Label("Enemy");
        enemyName.setFont(Font.font("KenVector Future",25));
        enemyName.setStyle("-fx-text-fill: #a50916");
        enemyName.setStyle("-fx-background-color: #9552b7");
        enemyName.setPrefWidth(600);
        enemyName.setAlignment(Pos.CENTER);
        enemyName.setLayoutY(740);
        playerName.setEffect(new DropShadow());
        enemyName.setEffect(new DropShadow());
        endPane.getChildren().addAll(text, playerName, enemyName);
    }

    private void showCrowns(){
        ImageView[]crown = new ImageView[6];
        for(int i=0;i<6;i++){
            crown[i] = new ImageView(new Image("sample/Resources/Kills.png",90,60,true,false));
            crown[i].setVisible(false);
        }
        crown[0].setLayoutX(145);
        crown[0].setLayoutY(370);
        crown[1].setLayoutX(255);
        crown[1].setLayoutY(320);
        crown[2].setLayoutX(365);
        crown[2].setLayoutY(370);
        crown[3].setLayoutX(145);
        crown[3].setLayoutY(470);
        crown[4].setLayoutX(255);
        crown[4].setLayoutY(520);
        crown[5].setLayoutX(365);
        crown[5].setLayoutY(470);
        for(int i=3;i<6;i++){
            crown[i].setRotate(180);
        }
        for(ImageView x:crown){
            endPane.getChildren().add(x);
        }
        for(int i=0;i<GameManager.playerCrowns;i++){
            crown[i].setVisible(true);
        }
        for(int i=0;i<GameManager.enemyCrowns;i++){
            crown[i+3].setVisible(true);
        }
    }

    private void showLabels(){
        Label playerNumber = new Label(String.valueOf(GameManager.getPlayerCrowns()));
        Label enemyNumber = new Label(String.valueOf(GameManager.getEnemyCrowns()));
        playerNumber.setLayoutY(420);
        playerNumber.setPrefWidth(600);
        playerNumber.setAlignment(Pos.CENTER);
        playerNumber.setFont(Font.font("KenVector Future",25));
        enemyNumber.setLayoutY(480);
        enemyNumber.setPrefWidth(600);
        enemyNumber.setAlignment(Pos.CENTER);
        enemyNumber.setFont(Font.font("KenVector Future",25));
        endPane.getChildren().addAll(playerNumber,enemyNumber);
    }

    private void createReturnButton(){
        Buttons back = new Buttons("Menu");
        back.setLayoutX(30);
        back.setLayoutY(820);
        back.setOnAction(e-> ViewManager.stage2.setScene(ViewManager.staticScene));
        endPane.getChildren().add(back);
    }

    private void showWinner(){
        Label winner = new Label();
        int x = GameManager.getPlayerCrowns();
        int y = GameManager.getEnemyCrowns();
        if(x>y){
            winner.setText(Player.getName() + " Wins");
        }else if(y>x){
            winner.setText(Enemy.getName() + " Wins");
        }else{
            winner.setText("DRAW!");
        }
        winner.setFont(Font.font("KenVector Future",35));
        winner.setEffect(new InnerShadow());
        winner.setLayoutY(130);
        winner.setPrefWidth(600);
        winner.setAlignment(Pos.CENTER);
        endPane.getChildren().add(winner);
    }

    Scene getEndScene(){
        return scene;
    }
}
