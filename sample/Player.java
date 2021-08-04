package sample;


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

class Player {
    static String name;
    private playerKING pk;
    private playerTOWER pt1, pt2;
    //private ImageView et2, et3, et1;
    private AnchorPane game;
    private Rectangle[] e = new Rectangle[10];
    private int elix = 4;
    private Rectangle[] troopBox = new Rectangle[4];
    //private Enemy en;
    //private enemyTOWER e1, e2;

    Player(String n){
        game = GameManager.gp;
        //en = GameManager.StaticEnemy;
        name = n;
        createPlayerTower();
        createElixirCounter();
        keepElixirIncreasing();
        createTroopBox();
        troopSelector();
    }
    private void createPlayerTower(){
        pt1 = new playerTOWER(470,605);
        pt2 = new playerTOWER(70,605);
        pk = new playerKING(270,675);
        //et1 = pk.getTower();
        //et2 = pt1.getTower();
        //et3 = pt2.getTower();
    }
    private void createElixirCounter(){
        e[0] = new Rectangle(70,760,48,30);
        e[0].setFill(Color.GREEN);
        e[1] = new Rectangle(118,760,48,30);
        e[1].setFill(Color.GREEN);
        e[2] = new Rectangle(166,760,48,30);
        e[2].setFill(Color.GREEN);
        e[3] = new Rectangle(214,760,48,30);
        e[3].setFill(Color.GREEN);
        e[4] = new Rectangle(262,760,48,30);
        e[4].setFill(Color.GREEN);
        e[5] = new Rectangle(310,760,48,30);
        e[5].setFill(Color.GRAY);
        e[6] = new Rectangle(358,760,48,30);
        e[6].setFill(Color.GRAY);
        e[7] = new Rectangle(406,760,48,30);
        e[7].setFill(Color.GRAY);
        e[8] = new Rectangle(454,760,48,30);
        e[8].setFill(Color.GRAY);
        e[9] = new Rectangle(502,760,48,30);
        e[9].setFill(Color.GRAY);
        game.getChildren().addAll(e[0],e[1],e[2],e[3],e[4],e[5],e[6],e[7],e[8],e[9]);
    }
    private void increaseElixir() {
        if(elix!=9)
            elix++;
        //System.out.println(elix);
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
        troopBox[0] = new Rectangle(70,800,115,80);
        troopBox[0].setFill(new ImagePattern(new Image("sample/Resources/Skeleton.png",115,80,true,false)));
        troopBox[1] = new Rectangle(185,800,115,80);
        troopBox[1].setFill(new ImagePattern(new Image("sample/Resources/DE.png",115,80,true,false)));
        troopBox[2] = new Rectangle(300,800,115,80);
        troopBox[2].setFill(new ImagePattern(new Image("sample/Resources/Fireball.png",115,80,true,false)));
        troopBox[3] = new Rectangle(415,800,115,80);
        troopBox[3].setFill(new ImagePattern(new Image("sample/Resources/Orc Long Arch T.png",115,80,true,false)));
        game.getChildren().addAll(troopBox[0],troopBox[1],troopBox[2],troopBox[3]);
    }

    private void troopSelector(){
        troopBox[0].setOnMouseClicked(k->{
            troopBox[1].setY(800);
            troopBox[3].setY(800);
            troopBox[2].setY(800);
            troopBox[0].setEffect(new DropShadow());
            troopBox[0].setY(795);
            pt1.getTower().setEffect(new DropShadow());
            pt2.getTower().setEffect(new DropShadow());
            pt1.getTower().setOnMouseClicked(f->{
                if(elix>1 && troopBox[0].getY() == 795){
                    Skeleton t = new Skeleton(480,570,GameManager.enem1,null,2);
                    t.transSkeleton();
                    e[elix--].setFill(Color.GRAY);
                    e[elix--].setFill(Color.GRAY);
                    e[elix--].setFill(Color.GRAY);
                    troopBox[0].setY(800);
                    troopBox[0].setEffect(null);
                    Platform.runLater(()->{
                        connectToServer.writer.println("Skeleton,2");
                        connectToServer.writer.flush();
                    });
                }
            });
            pt2.getTower().setOnMouseClicked(g->{
                if(elix>1 && troopBox[0].getY() == 795){
                    Skeleton t = new Skeleton(80,570,GameManager.enem2,null,1);
                    t.transSkeleton();
                    e[elix--].setFill(Color.GRAY);
                    e[elix--].setFill(Color.GRAY);
                    e[elix--].setFill(Color.GRAY);
                    troopBox[0].setY(800);
                    troopBox[0].setEffect(null);
                    Platform.runLater(()->{
                        connectToServer.writer.println("Skeleton,2");
                        connectToServer.writer.flush();
                    });
                }
            });
        });
        troopBox[1].setOnMouseClicked(j->{
            troopBox[0].setY(800);
            troopBox[3].setY(800);
            troopBox[2].setY(800);
            troopBox[1].setEffect(new DropShadow());
            troopBox[1].setY(795);
            pt1.getTower().setEffect(new DropShadow());
            pt2.getTower().setEffect(new DropShadow());
            pt1.getTower().setOnMouseClicked(f->{
                if(elix>1 && troopBox[1].getY() == 795){
                    darkElf t = new darkElf(480,570,GameManager.enem1,null,2);
                    t.transSkeleton();
                    e[elix--].setFill(Color.GRAY);
                    e[elix--].setFill(Color.GRAY);
                    e[elix--].setFill(Color.GRAY);
                    troopBox[1].setY(800);
                    troopBox[1].setEffect(null);
                }
            });
            pt2.getTower().setOnMouseClicked(g->{
                if(elix>1 && troopBox[1].getY() == 795){
                    darkElf t = new darkElf(80,570,GameManager.enem2,null,1);
                    t.transSkeleton();
                    e[elix--].setFill(Color.GRAY);
                    e[elix--].setFill(Color.GRAY);
                    e[elix--].setFill(Color.GRAY);
                    troopBox[1].setY(800);
                    troopBox[1].setEffect(null);
                }
            });
        });
        troopBox[2].setOnMouseClicked(k->{
            troopBox[0].setY(800);
            troopBox[1].setY(800);
            troopBox[3].setY(800);
            troopBox[2].setEffect(new DropShadow());
            troopBox[2].setY(795);

            GameManager.gp.setOnMouseClicked(p->{
                if(elix>2 && troopBox[2].getY() == 795){
                    double x = p.getSceneX();
                    double y = p.getSceneY();
                    int z = x>370?1:(x<270?2:3);
                    if(y<750){
                        Fireball fb = new Fireball(x,y,z,1);
                        fb.explosion();
                        e[elix--].setFill(Color.GRAY);
                        e[elix--].setFill(Color.GRAY);
                        e[elix--].setFill(Color.GRAY);
                        e[elix--].setFill(Color.GRAY);
                    }
                    troopBox[2].setEffect(null);
                    troopBox[2].setY(800);
                }
            });
            k.consume();
        });
        troopBox[3].setOnMouseClicked(j->{
            troopBox[0].setY(800);
            troopBox[1].setY(800);
            troopBox[2].setY(800);
            troopBox[3].setEffect(new DropShadow());
            troopBox[3].setY(795);
            pt1.getTower().setEffect(new DropShadow());
            pt2.getTower().setEffect(new DropShadow());
            pt1.getTower().setOnMouseClicked(f->{
                if(elix>1 && troopBox[3].getY() == 795){
                    orcLongBow t = new orcLongBow(480,570,GameManager.enem1,null,2);
                    t.moveOrc();
                    e[elix--].setFill(Color.GRAY);
                    e[elix--].setFill(Color.GRAY);
                    e[elix--].setFill(Color.GRAY);
                    troopBox[3].setY(800);
                    troopBox[3].setEffect(null);
                }
            });
            pt2.getTower().setOnMouseClicked(g->{
                if(elix>1 && troopBox[3].getY() == 795){
                    orcLongBow t = new orcLongBow(80,570,GameManager.enem2,null,1);
                    t.moveOrc();
                    e[elix--].setFill(Color.GRAY);
                    e[elix--].setFill(Color.GRAY);
                    e[elix--].setFill(Color.GRAY);
                    troopBox[3].setY(800);
                    troopBox[3].setEffect(null);
                }
            });
        });
    }

    playerKING getPk() {
        return pk;
    }

    playerTOWER getPt1() {
        return pt1;
    }

    playerTOWER getPt2() {
        return pt2;
    }

    void setName(String n){
        name = n;
    }

    static String getName(){return name;}
}
