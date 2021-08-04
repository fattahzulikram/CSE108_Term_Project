package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;

class orcLongBow {
    private AnchorPane mainstage;
    private int health = 30;
    private int damage = 1000;
    private double xaxis,yaxis;
    private enemyTOWER enemy;
    private playerTOWER play;
    private playerKING pk;
    private ImageView skel;
    private ImageView act;
    private ImageView arr;
    private int direction;
    private enemyKING ek;
    private Timeline doing,war;
    static ArrayList<orcLongBow> orcBowmen = new ArrayList<>();
    int caller;
    orcLongBow(double x, double y, enemyTOWER e, playerTOWER p, int side){
        mainstage = GameManager.gp;
        if(e!=null){
            skel = new ImageView(new Image("sample/Resources/LB.png",50,40,true,false));
            act = new ImageView(new Image("sample/Resources/LB2.png",55,45,true,false));
            enemy = e;
            ek = GameManager.StaticEnemy.getEnk();
            caller = 1;
        }
        else{
            skel = new ImageView(new Image("sample/Resources/LB3.png",50,40,true,false));
            act = new ImageView(new Image("sample/Resources/LB4.png",55,45,true,false));
            play = p;
            pk = GameManager.Sp.getPk();
            caller = 2;
        }
        skel.setLayoutX(x);
        skel.setLayoutY(y);
        mainstage.getChildren().add(skel);
        xaxis = x;
        yaxis = y;
        direction = side;
        orcBowmen.add(this);
    }

    ImageView getElf(){
        return (mainstage.getChildren().contains(act))?act:skel;
    }

    void moveOrc(){
        TranslateTransition move = new TranslateTransition(Duration.seconds(1.5));
        if(enemy!=null){
            move.setNode(skel);
            move.setToY(-150);
            move.play();
            move.setOnFinished(k-> action());
        }
        else{
            move.setNode(skel);
            move.setToY(150);
            move.play();
            move.setOnFinished(k-> action());
        }
    }

    private void action(){
        doing = new Timeline(new KeyFrame(Duration.seconds(2), event -> {
            if(health>=0 && enemy!=null && enemy.getHealth()>0){
                attack();
            }
            else if(health>=0 && play!=null && play.getHealth()>0){
                attack();
            }
            else if(health<0){
                mainstage.getChildren().remove(act);
                doing.stop();
            }
            else{
                toTheKing();
                doing.stop();
            }
        }));
        doing.setCycleCount(Timeline.INDEFINITE);
        doing.play();
    }

    private void arrowTranslate(ImageView node){
        TranslateTransition goArrow = new TranslateTransition(Duration.seconds(0.5));
        goArrow.setNode(node);
        if(enemy!=null){
            goArrow.setToY(-160);
        }
        else{
            goArrow.setToY(180);
        }
        goArrow.play();
        goArrow.setOnFinished(e->{
            mainstage.getChildren().remove(node);
            towerDamage();
        });
    }

    private void attack(){
        if(enemy!=null){
            arr = new ImageView(new Image("sample/Resources/Arrow.png",10,30,true,false));
            act.setLayoutX(xaxis);
            act.setLayoutY(yaxis-150);
            arr.setY(yaxis-200);
        }
        else{
            arr = new ImageView(new Image("sample/Resources/Arrow1.png",10,30,true,false));
            act.setLayoutX(xaxis);
            act.setLayoutY(yaxis+150);
            arr.setY(yaxis+200);
        }
        arr.setX(xaxis);
        mainstage.getChildren().remove(skel);
        mainstage.getChildren().remove(act);
        mainstage.getChildren().add(act);
        mainstage.getChildren().add(arr);
        arrowTranslate(arr);
    }

    private void towerDamage(){
        if(enemy!=null && enemy.getHealth()>0){
            enemy.setHealth(enemy.getHealth()-damage);
            if(-enemy.getTower().getLayoutY()+this.getElf().getLayoutY()<=120){
                this.health -= enemy.getDamage();
            }
            if(enemy.getHealth()<=0){
                mainstage.getChildren().remove(enemy.getTower());
            }
            if(this.health<=0){
                mainstage.getChildren().remove(act);
            }
        }
        else if(play!=null && play.getHealth()>0){
            play.setHealth(play.getHealth()-damage);
            if(play.getTower().getLayoutY()-this.getElf().getLayoutY()<=120){
                this.health -= play.getDamage();
            }
            if(play.getHealth()<=0){
                mainstage.getChildren().remove(play.getTower());
            }
            if(this.health<=0){
                mainstage.getChildren().remove(act);
                mainstage.getChildren().remove(act);
                mainstage.getChildren().remove(act);
            }
        }
    }

    private void toTheKing(){
        TranslateTransition go = new TranslateTransition(Duration.seconds(1.5));
        if(enemy!=null){
            act.setLayoutY(yaxis-150);
            mainstage.getChildren().remove(skel);
            mainstage.getChildren().add(skel);
            mainstage.getChildren().remove(act);
            go.setNode(skel);
            if(direction==1){
                go.setToX(170);
            }
            else{
                go.setToX(-170);
            }
            go.setToY(-200);
        }
        else{
            mainstage.getChildren().remove(act);
            //mainstage.getChildren().remove(skel);
            act.setLayoutY(yaxis+130);
            mainstage.getChildren().add(skel);
            go.setNode(skel);
            if(direction==1){
                go.setToX(170);
            }
            else{
                go.setToX(-170);
            }
            go.setToY(200);
        }
        go.play();
        go.setOnFinished(a-> attackTheKing());
    }

    private void attackTheKing(){
        mainstage.getChildren().remove(skel);
        if(direction==1){
            act.setLayoutX(xaxis+170);
        }
        else{
            act.setLayoutX(xaxis-170);
        }
        if(enemy!=null){
            act.setLayoutY(yaxis-200);
        }
        else{
            act.setLayoutY(yaxis+200);
        }
        mainstage.getChildren().add(act);
        war = new Timeline(new KeyFrame(Duration.seconds(2), event -> killTheKing()));
        war.setCycleCount(Timeline.INDEFINITE);
        war.play();
    }

    private void arrowToKing(ImageView node){
        TranslateTransition trans = new TranslateTransition(Duration.seconds(0.5));
        trans.setNode(node);
        if(enemy!=null){
            trans.setToY(-160);
        }
        else{
            trans.setToY(200);
        }
        trans.play();
        trans.setOnFinished(e->{
            mainstage.getChildren().remove(arr);
            damageKING();
        });
    }

    private void killTheKing(){
        if(enemy!=null) {
            arr = new ImageView(new Image("sample/Resources/Arrow.png",10,30,true,false));
            arr.setY(yaxis-250);
        }
        else {
            arr = new ImageView(new Image("sample/Resources/Arrow1.png",10,30,true,false));
            arr.setY(yaxis + 250);
        }
        if(direction==1)
            arr.setX(xaxis+200);
        else
            arr.setX(xaxis-170);
        mainstage.getChildren().add(arr);
        arrowToKing(arr);
    }

    private void damageKING(){
        if(health>0 && caller == 1 && ek.getHealth()>0){
            ek.setHealth(ek.getHealth()-damage);
            if(this.getElf().getLayoutY()-ek.getYcoord()<=100){
                this.health = this.health-ek.getDamage();
            }
        }
        else if(health>0 && caller == 2 && pk.getHealth()>0){
            pk.setHealth(pk.getHealth()-damage);
            if(pk.getTower().getLayoutY()-this.getElf().getLayoutY()<=100){
                this.health = this.health - pk.getDamage();
            }
        }
        else if((health>0 && caller == 1 && ek.getHealth()<=0) || (health>0 && caller == 2 && pk.getHealth()<=0)){
            int some;
            if(ek.getHealth()<=0){some = 1;}
            else{some = 2;}
            showEnd(some);
            war.stop();
        }
        else{
            mainstage.getChildren().remove(act);
            war.stop();
        }
    }

    private void showEnd(int a){
        Scene z = mainstage.getScene();
        Stage x = (Stage) z.getWindow();
        if(a==1){GameManager.setCrowns(3,1);}
        else{{GameManager.setCrowns(1,3);}}
        endGame end = new endGame();
        x.setScene(end.getEndScene());
        System.out.println(GameManager.playerCrowns);
    }

    int getHealth() {
        return health;
    }

    void setHealth(int health) {
        this.health -= health;
    }


    int caller(){
        return (enemy==null)?2:1;
    }
}
