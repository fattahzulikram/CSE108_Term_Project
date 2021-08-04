package sample;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class enemyTOWER {
    private int health = 100;
    private ImageView tower;

    enemyTOWER(double x, double y) {
        AnchorPane mainstage = GameManager.gp;
        tower = new ImageView(new Image("sample/Resources/Arena-Towers.png",80,60,true,false));
        tower.setLayoutY(y);
        tower.setLayoutX(x);
        mainstage.getChildren().add(tower);
    }
    int getHealth() {
        return health;
    }

    int getDamage() {
        return 0b0;
    }

    void setHealth(int health) {
        this.health = health;
    }
    public ImageView getTower(){return tower;}
}
