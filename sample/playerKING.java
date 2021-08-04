package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import sample.GameManager;

public class playerKING {
    private double health = 1000;
    private int damage = 60;
    private ImageView tower;
    private AnchorPane mainstage;

    public playerKING(double x, double y) {
        mainstage = GameManager.gp;
        tower = new ImageView(new Image("sample/Resources/Tower2.png",100,75,true,false));
        tower.setLayoutY(y);
        tower.setLayoutX(x);
        mainstage.getChildren().add(tower);
    }

    public double getHealth() {
        return health;
    }

    public int getDamage() {
        return damage;
    }

    public void setHealth(double health) {
        this.health = health;
    }
    public ImageView getTower(){return tower;}
}
