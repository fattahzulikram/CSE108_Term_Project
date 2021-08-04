package sample;


import javafx.animation.PauseTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

class Fireball {

    private double targetx, targety;
    private int caller;
    private ImageView fire, bomb;

    Fireball(double x, double y, int side, int p){
        targetx = x;
        targety = y;
        caller = p;
        if(caller == 1){
            if(side == 1){
                fire = new ImageView(new Image("sample/Resources/FB TR.png",50,40,true,false));
            }
            else if(side == 2){
                fire = new ImageView(new Image("sample/Resources/FB TL.png",50,40,true,false));
            }
            else{
                fire = new ImageView(new Image("sample/Resources/FB S.png",50,40,true,false));
            }
            fire.setX(270);
            fire.setY(675);
        }
        else{
            if(side == 1){
                fire = new ImageView(new Image("sample/Resources/FB BR.png",50,40,true,false));
            }
            else if(side == 2){
                fire = new ImageView(new Image("sample/Resources/FB BL.png",50,40,true,false));
            }
            else{
                fire = new ImageView(new Image("sample/Resources/FB SB.png",50,40,true,false));
            }
            fire.setX(270);
            fire.setY(140);
        }
        bomb = new ImageView(new Image("sample/Resources/FB Explode.png",60,50,true,false));
        bomb.setX(targetx);
        bomb.setY(targety);
        GameManager.gp.getChildren().add(fire);
    }

    void explosion(){
        TranslateTransition glide = new TranslateTransition(Duration.seconds(0.5));
        glide.setNode(fire);
        if(caller == 1){
            glide.setToX(targetx-270);
            glide.setToY(targety-675);
        }
        else{
            glide.setToX(targetx-270);
            glide.setToY(targety-200);
        }
        glide.play();
        glide.setOnFinished(e->{
            GameManager.gp.getChildren().remove(fire);
            GameManager.gp.getChildren().add(bomb);
            PauseTransition delay = new PauseTransition(Duration.seconds(0.7));
            delay.play();
            delay.setOnFinished(j->{
                GameManager.gp.getChildren().remove(bomb);
                damageAbility();
            });
        });
    }

    private boolean withinRadius(ImageView target){
        return (target.getLayoutX()>=targetx-100) && (target.getLayoutX()<=targetx+100) && (target.getLayoutY()<=targety+100) && (target.getLayoutY()>=targety-100);
    }

    private boolean withinRadius(double x, double y){
        return (x>=targetx-100) && (x<=targetx+100) && (y<=targety+100) && (y>=targety-100);
    }

    private void towerDamage(){
        int towerdamage = 60;
        if(withinRadius(470,190) && caller ==1){
            GameManager.StaticEnemy.getEnt1().setHealth(GameManager.StaticEnemy.getEnt1().getHealth()- towerdamage);
            if(GameManager.StaticEnemy.getEnt1().getHealth()<=0){
                GameManager.gp.getChildren().remove(GameManager.StaticEnemy.getEnt1().getTower());
            }
        }
        if(withinRadius(70,190) && caller == 1){
            GameManager.StaticEnemy.getEnt2().setHealth(GameManager.StaticEnemy.getEnt2().getHealth()- towerdamage);
            if(GameManager.StaticEnemy.getEnt2().getHealth()<=0){
                GameManager.gp.getChildren().remove(GameManager.StaticEnemy.getEnt2().getTower());
            }
        }
        if(withinRadius(270,140) && caller == 1){
            GameManager.StaticEnemy.getEnk().setHealth(GameManager.StaticEnemy.getEnk().getHealth()- towerdamage);
            if(GameManager.StaticEnemy.getEnk().getHealth()<=0) {
                showEnd(1);
            }
        }
        if(withinRadius(470,605) && caller ==2){
            GameManager.Sp.getPt1().setHealth(GameManager.Sp.getPt1().getHealth()- towerdamage);
            if(GameManager.Sp.getPt1().getHealth()<=0){
                GameManager.gp.getChildren().remove(GameManager.Sp.getPt1().getTower());
            }
        }
        if(withinRadius(70,605) && caller == 2){
            GameManager.Sp.getPt2().setHealth(GameManager.Sp.getPt2().getHealth()- towerdamage);
            if(GameManager.Sp.getPt2().getHealth()<=0){
                GameManager.gp.getChildren().remove(GameManager.Sp.getPt2().getTower());
            }
        }
        if(withinRadius(270,675) && caller == 2){
            GameManager.Sp.getPk().setHealth(GameManager.Sp.getPk().getHealth()- towerdamage);
            if(GameManager.Sp.getPk().getHealth()<=0) {
                showEnd(2);
            }
        }
    }

    private void showEnd(int a){
        Scene z = GameManager.gp.getScene();
        Stage x = (Stage) z.getWindow();
        if(a==1){GameManager.setCrowns(3,1);}
        else{{GameManager.setCrowns(1,3);}}
        endGame end = new endGame();
        x.setScene(end.getEndScene());
        System.out.println(GameManager.playerCrowns);
    }

    private void damageAbility(){
        int troopdamage = 70;
        towerDamage();
        for(Skeleton x:Skeleton.skellList){
            if(withinRadius(x.getSkeleton()) && caller==1){
                if(x.getHealth()>0 && x.caller()==2){
                    x.setHealth(troopdamage);
                    if(x.getHealth()<=0){
                        GameManager.gp.getChildren().remove(x.getSkeleton());
                    }
                }
            }
            if(withinRadius(x.getSkeleton()) && caller==2){
                if(x.getHealth()>0 && x.caller()==1){
                    x.setHealth(troopdamage);
                    if(x.getHealth()<=0){
                        GameManager.gp.getChildren().remove(x.getSkeleton());
                    }
                }
            }
        }
        for(darkElf d:darkElf.DElist){
            if(withinRadius(d.getElf()) && caller ==1){
                if(d.getHealth()>0 && d.caller()==2){
                    d.setHealth(troopdamage);
                    if(d.getHealth()<=0){
                        GameManager.gp.getChildren().remove(d.getElf());
                    }
                }
            }
            if(withinRadius(d.getElf()) && caller ==2){
                if(d.getHealth()>0 && d.caller()==1){
                    d.setHealth(troopdamage);
                    if(d.getHealth()<=0){
                        GameManager.gp.getChildren().remove(d.getElf());
                    }
                }
            }
        }
        for(orcLongBow o:orcLongBow.orcBowmen){
            if(withinRadius(o.getElf()) && caller ==1){
                if(o.getHealth()>0 && o.caller()==2){
                    o.setHealth(troopdamage);
                    if(o.getHealth()<=0){
                        GameManager.gp.getChildren().remove(o.getElf());
                    }
                }
            }
            if(withinRadius(o.getElf()) && caller ==2){
                if(o.getHealth()>0 && o.caller()==1){
                    o.setHealth(troopdamage);
                    if(o.getHealth()<=0){
                        GameManager.gp.getChildren().remove(o.getElf());
                    }
                }
            }
        }
    }

}
