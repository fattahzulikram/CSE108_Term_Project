package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class playerTOWER {
    private double health = 100;
    private ImageView tower;

    playerTOWER(double x, double y) {
        AnchorPane mainstage = GameManager.gp;
        tower = new ImageView(new Image("sample/Resources/Defence.png",80,60,true,false));
        tower.setLayoutY(y);
        tower.setLayoutX(x);
        mainstage.getChildren().add(tower);
    }

    double getHealth() {
        return health;
    }

    double getDamage() {
        return (double) 0;
    }

    void setHealth(double health) {
        this.health = health;
    }
    public ImageView getTower(){
        return tower;
    }
}
