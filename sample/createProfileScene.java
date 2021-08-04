package sample;

import javafx.animation.PauseTransition;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.text.Font;
import javafx.util.Duration;

import java.io.*;
import java.util.Scanner;

public class createProfileScene {
    Scene scene;
    AnchorPane newPane;
    TextField userField;
    PasswordField passField;
    private Label incorrect,correct;

    createProfileScene(){
        newPane = new AnchorPane();
        createBackground();
        createBox();
        createLabels();
        createSubmit();
        scene = new Scene(newPane,600,800);
    }

    public Scene getScene() {
        return scene;
    }

    private void createBackground(){
        Image bg = new Image("sample/Resources/Title.jpg");
        BackgroundImage bgi = new BackgroundImage(bg,null,null,BackgroundPosition.DEFAULT,null);
        newPane.setBackground(new Background(bgi));
    }

    private void createLabels(){
        Label userPrompt = new Label("Select Username: ");
        userPrompt.setFont(Font.font("KenVector Future",15));
        userPrompt.setLayoutY(300);
        userPrompt.setPrefWidth(250);
        userPrompt.setLayoutX(50);
        userPrompt.setPrefHeight(50);
        userPrompt.setAlignment(Pos.CENTER_RIGHT);
        Label passPrompt = new Label("Select Password: ");
        passPrompt.setFont(Font.font("KenVector Future",15));
        passPrompt.setLayoutY(400);
        passPrompt.setPrefWidth(250);
        passPrompt.setLayoutX(50);
        passPrompt.setPrefHeight(50);
        passPrompt.setAlignment(Pos.CENTER_RIGHT);
        incorrect = new Label("Username Already Exists!");
        incorrect.setFont(Font.font("KenVector Future",15));
        incorrect.setLayoutY(550);
        incorrect.setPrefWidth(600);
        incorrect.setAlignment(Pos.CENTER);
        incorrect.setPrefHeight(40);
        incorrect.setStyle("-fx-text-fill: Red");
        incorrect.setVisible(false);
        correct = new Label("Successfully Created Profile!");
        correct.setFont(Font.font("KenVector Future",15));
        correct.setLayoutY(550);
        correct.setPrefWidth(600);
        correct.setAlignment(Pos.CENTER);
        correct.setPrefHeight(40);
        correct.setStyle("-fx-text-fill: Green");
        correct.setVisible(false);
        newPane.getChildren().addAll(userPrompt,passPrompt,incorrect,correct);
    }

    private void createBox(){
        userField = new TextField("Username");
        userField.setFont(Font.font("KenVector Future",25));
        userField.setPrefWidth(270);
        userField.setLayoutY(300);
        userField.setLayoutX(310);
        userField.setPrefHeight(50);
        userField.setAlignment(Pos.CENTER_LEFT);
        passField = new PasswordField();
        passField.setPrefWidth(270);
        passField.setLayoutX(310);
        passField.setLayoutY(400);
        passField.setPrefHeight(50);
        passField.setAlignment(Pos.CENTER_LEFT);
        newPane.getChildren().addAll(userField,passField);
    }

    private void createSubmit(){
        int LOGIN_BUTTON_X = 215;
        int LOGIN_BUTTON_Y = 500;

        Buttons log = new Buttons("Submit");
        log.setLayoutY(LOGIN_BUTTON_Y);
        log.setLayoutX(LOGIN_BUTTON_X);

        newPane.getChildren().add(log);

        log.setOnAction(e->{
            String name = userField.getText();
            String pass = passField.getText();
            try {
                verifyIt(name,pass);
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }
        });
    }

    private void verifyIt(String n,String p) throws FileNotFoundException {
        PauseTransition delay = new PauseTransition(Duration.seconds(1.5));
        FileInputStream file = new FileInputStream(new File("src/pr.txt"));
        Scanner scanner = new Scanner(file);
        int flag = 0;
        while(scanner.hasNextLine()){
            String u = scanner.next();
            String ap = scanner.next();
            if(n.equals(u)){
                correct.setVisible(false);
                incorrect.setVisible(true);
                flag = 1;
                break;
            }
        }
        try {
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(flag==0){
            incorrect.setVisible(false);
            correct.setVisible(true);
            try {
                FileWriter fw = new FileWriter("src/pr.txt",true);
                fw.write(n + " " + p + "\n");
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            delay.play();
            delay.setOnFinished(e->{
                LoginPage.stage.setScene(LoginPage.scene);
            });
        }
    }
}
