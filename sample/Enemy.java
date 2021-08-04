package sample;


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

class Enemy {
    static String name;
    private enemyTOWER ent1,ent2;
    private enemyKING enk;
    private AnchorPane game;
    private Rectangle[] e = new Rectangle[10];
    private int elix = 4;
    private Rectangle[] troopBox = new Rectangle[4];
    private Player pp;

    Enemy(){
        game = GameManager.gp;
        pp = GameManager.Sp;
        createEnemyTower();
        createElixirCounter();
        keepElixirIncreasing();
        createTroopBox();
        troopSelector();
    }

    private void createEnemyTower(){
        ent1 = new enemyTOWER(470,190);
        ent2 = new enemyTOWER(70,190);
        enk = new enemyKING(270,140);
    }

    private void createElixirCounter(){
        e[0] = new Rectangle(70,100,48,30);
        e[0].setFill(Color.GREEN);
        e[1] = new Rectangle(118,100,48,30);
        e[1].setFill(Color.GREEN);
        e[2] = new Rectangle(166,100,48,30);
        e[2].setFill(Color.GREEN);
        e[3] = new Rectangle(214,100,48,30);
        e[3].setFill(Color.GREEN);
        e[4] = new Rectangle(262,100,48,30);
        e[4].setFill(Color.GREEN);
        e[5] = new Rectangle(310,100,48,30);
        e[5].setFill(Color.GRAY);
        e[6] = new Rectangle(358,100,48,30);
        e[6].setFill(Color.GRAY);
        e[7] = new Rectangle(406,100,48,30);
        e[7].setFill(Color.GRAY);
        e[8] = new Rectangle(454,100,48,30);
        e[8].setFill(Color.GRAY);
        e[9] = new Rectangle(502,100,48,30);
        e[9].setFill(Color.GRAY);
        game.getChildren().addAll(e[0],e[1],e[2],e[3],e[4],e[5],e[6],e[7],e[8],e[9]);
    }
    private void increaseElixir() {
        if(elix!=9)
            elix++;
        e[elix].setFill(Color.GREEN);
        game.getChildren().remove(e[elix]);
        game.getChildren().add(e[elix]);
    }
    private void keepElixirIncreasing(){
        Timeline more = new Timeline(new KeyFrame(Duration.seconds(2), event -> increaseElixir()));
        more.setCycleCount(Timeline.INDEFINITE);
        more.play();
    }
    private  void createTroopBox(){
        troopBox[0] = new Rectangle(70,10,115,80);
        troopBox[0].setFill(new ImagePattern(new Image("sample/Resources/Skeleton.png",115,80,true,false)));
        troopBox[1] = new Rectangle(185,10,115,80);
        troopBox[1].setFill(new ImagePattern(new Image("sample/Resources/DE.png",115,80,true,false)));
        troopBox[2] = new Rectangle(300,10,115,80);
        troopBox[2].setFill(new ImagePattern(new Image("sample/Resources/Fireball.png",115,80,true,false)));
        troopBox[3] = new Rectangle(415,10,115,80);
        troopBox[3].setFill(new ImagePattern(new Image("sample/Resources/Orc Long Arch T.png",115,80,true,false)));
        game.getChildren().addAll(troopBox[0],troopBox[1],troopBox[2],troopBox[3]);
    }
    private void troopSelector(){
        troopBox[0].setOnMouseClicked(k->{
            troopBox[1].setY(10);
            troopBox[2].setY(10);
            troopBox[3].setY(10);
            troopBox[0].setEffect(new DropShadow());
            troopBox[0].setY(15);
            ent1.getTower().setEffect(new DropShadow());
            ent2.getTower().setEffect(new DropShadow());
            ent1.getTower().setOnMouseClicked(f->{
                if(elix>1 && troopBox[0].getY()==15){
                    Skeleton t = new Skeleton(480,220,null,pp.getPt1(),2);
                    t.transSkeleton();
                    e[elix--].setFill(Color.GRAY);
                    e[elix--].setFill(Color.GRAY);
                    e[elix--].setFill(Color.GRAY);
                    troopBox[0].setEffect(null);
                    troopBox[0].setY(10);
                }
            });
            ent2.getTower().setOnMouseClicked(g->{
                if(elix>1 && troopBox[0].getY()==15){
                    Skeleton t = new Skeleton(80,220,null,pp.getPt2(),1);
                    t.transSkeleton();
                    e[elix--].setFill(Color.GRAY);
                    e[elix--].setFill(Color.GRAY);
                    e[elix--].setFill(Color.GRAY);
                    troopBox[0].setEffect(null);
                    troopBox[0].setY(10);
                }
            });
        });
        troopBox[1].setOnMouseClicked(k->{
            troopBox[0].setY(10);
            troopBox[2].setY(10);
            troopBox[3].setY(10);
            troopBox[1].setEffect(new DropShadow());
            troopBox[1].setY(15);
            ent1.getTower().setEffect(new DropShadow());
            ent2.getTower().setEffect(new DropShadow());
            ent1.getTower().setOnMouseClicked(f->{
                if(elix>1 && troopBox[1].getY()==15){
                    darkElf t = new darkElf(480,220,null,pp.getPt1(),2);
                    t.transSkeleton();
                    e[elix--].setFill(Color.GRAY);
                    e[elix--].setFill(Color.GRAY);
                    e[elix--].setFill(Color.GRAY);
                    troopBox[1].setEffect(null);
                    troopBox[1].setY(10);
                }
            });
            ent2.getTower().setOnMouseClicked(g->{
                if(elix>1 && troopBox[1].getY()==15){
                    darkElf t = new darkElf(80,220,null,pp.getPt2(),1);
                    t.transSkeleton();
                    e[elix--].setFill(Color.GRAY);
                    e[elix--].setFill(Color.GRAY);
                    e[elix--].setFill(Color.GRAY);
                    troopBox[1].setEffect(null);
                    troopBox[1].setY(10);
                }
            });
        });
        troopBox[2].setOnMouseClicked(k->{
            troopBox[0].setY(10);
            troopBox[1].setY(10);
            troopBox[3].setY(10);
            troopBox[2].setEffect(new DropShadow());
            troopBox[2].setY(15);

            GameManager.gp.setOnMouseClicked(p->{
                if(elix>2 && troopBox[2].getY() == 15){
                    double x = p.getSceneX();
                    double y = p.getSceneY();
                    int z = x>370?1:(x<270?2:3);
                    if(y>140){
                        Fireball fb = new Fireball(x,y,z,2);
                        fb.explosion();
                        e[elix--].setFill(Color.GRAY);
                        e[elix--].setFill(Color.GRAY);
                        e[elix--].setFill(Color.GRAY);
                        e[elix--].setFill(Color.GRAY);
                    }
                    troopBox[2].setEffect(null);
                    troopBox[2].setY(10);
                }
            });
            k.consume();
        });
        troopBox[3].setOnMouseClicked(j->{
            troopBox[0].setY(10);
            troopBox[1].setY(10);
            troopBox[2].setY(10);
            troopBox[3].setEffect(new DropShadow());
            troopBox[3].setY(15);
            ent1.getTower().setEffect(new DropShadow());
            ent2.getTower().setEffect(new DropShadow());
            ent1.getTower().setOnMouseClicked(f->{
                orcLongBow t = new orcLongBow(480,220,null,pp.getPt1(),2);
                t.moveOrc();
                e[elix--].setFill(Color.GRAY);
                e[elix--].setFill(Color.GRAY);
                e[elix--].setFill(Color.GRAY);
                troopBox[3].setEffect(null);
                troopBox[3].setY(10);
            });
            ent2.getTower().setOnMouseClicked(g->{
                orcLongBow t = new orcLongBow(80,220,null,pp.getPt2(),1);
                t.moveOrc();
                e[elix--].setFill(Color.GRAY);
                e[elix--].setFill(Color.GRAY);
                e[elix--].setFill(Color.GRAY);
                troopBox[1].setEffect(null);
                troopBox[1].setY(10);
            });
        });
    }

    enemyTOWER getEnt1() {
        return ent1;
    }

    enemyTOWER getEnt2() {
        return ent2;
    }

    enemyKING getEnk() {
        return enk;
    }

    void setName(String n){
        name = n;
    }

    static String getName(){return name;}
}

