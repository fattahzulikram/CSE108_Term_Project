package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class enemyKING {
    private int health = 1000;
    private ImageView tower;

    enemyKING(double x, double y) {
        AnchorPane mainstage = GameManager.gp;
        tower = new ImageView(new Image("sample/Resources/TowerEnemy.png",100,75,true,false));
        tower.setLayoutY(y);
        tower.setLayoutX(x);
        mainstage.getChildren().add(tower);
    }

    int getHealth() {
        return health;
    }

    int getDamage() {
        return 60;
    }

    void setHealth(int health) {
        this.health = health;
    }
    public ImageView getTower(){return tower;}
    //public double getXcoord(){return tower.getLayoutX();}
    double getYcoord(){return tower.getLayoutY();}
}
