package sample;

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
import javafx.stage.Stage;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

class LoginPage {
    static Stage stage;
    private HashMap<String,String> profile = new HashMap<>();
    AnchorPane loginPane;
    TextField userField;
    PasswordField passField;
    Label incorrectPassword;
    static Scene scene;
    static String userName;

    public Stage getStage() {
        return stage;
    }

    LoginPage(){
        stage = new Stage();
        stage.setTitle("Login");
        loginPane = new AnchorPane();
        createBackground();
        showPromptTexts();
        createTextBoxes();
        createLoginButton();
        createNewProfButton();
        scene = new Scene(loginPane,600,900);
        stage.setScene(scene);
    }

    private void createBackground(){
        Image bg = new Image("sample/Resources/Title.jpg");
        BackgroundImage bgi = new BackgroundImage(bg,null,null,BackgroundPosition.DEFAULT,null);
        loginPane.setBackground(new Background(bgi));
    }

    private void showPromptTexts(){
        Label userPrompt = new Label("Username: ");
        userPrompt.setFont(Font.font("KenVector Future",15));
        userPrompt.setLayoutY(300);
        userPrompt.setPrefWidth(150);
        userPrompt.setLayoutX(50);
        userPrompt.setPrefHeight(50);
        userPrompt.setAlignment(Pos.CENTER_RIGHT);
        Label passPrompt = new Label("Password: ");
        passPrompt.setFont(Font.font("KenVector Future",15));
        passPrompt.setLayoutY(400);
        passPrompt.setPrefWidth(150);
        passPrompt.setLayoutX(50);
        passPrompt.setPrefHeight(50);
        passPrompt.setAlignment(Pos.CENTER_RIGHT);
        incorrectPassword = new Label("Error Logging In!");
        incorrectPassword.setFont(Font.font("KenVector Future",15));
        incorrectPassword.setLayoutY(550);
        incorrectPassword.setPrefWidth(600);
        incorrectPassword.setAlignment(Pos.CENTER);
        incorrectPassword.setPrefHeight(40);
        incorrectPassword.setStyle("-fx-text-fill: Red");
        incorrectPassword.setVisible(false);
        loginPane.getChildren().addAll(userPrompt,passPrompt,incorrectPassword);
    }

    private void createTextBoxes(){
        userField = new TextField("Username");
        userField.setFont(Font.font("KenVector Future",25));
        userField.setPrefWidth(300);
        userField.setLayoutY(300);
        userField.setLayoutX(210);
        userField.setPrefHeight(50);
        userField.setAlignment(Pos.CENTER_LEFT);
        passField = new PasswordField();
        passField.setPrefWidth(300);
        passField.setLayoutX(210);
        passField.setLayoutY(400);
        passField.setPrefHeight(50);
        passField.setAlignment(Pos.CENTER_LEFT);
        loginPane.getChildren().addAll(userField,passField);
    }

    private void createLoginButton(){
        int LOGIN_BUTTON_X = 215;
        int LOGIN_BUTTON_Y = 500;

        Buttons log = new Buttons("Login");
        log.setLayoutY(LOGIN_BUTTON_Y);
        log.setLayoutX(LOGIN_BUTTON_X);

        loginPane.getChildren().add(log);
        log.setOnAction(e->{
            String name = userField.getText();
            String password = passField.getText();
            if(name!=null && password!=null){
                try {
                    verifyUser(name,password);
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }

    private void createNewProfButton(){
        int NEW_BUTTON_X = 215;
        int NEW_BUTTON_Y = 600;

        Buttons newprof = new Buttons("Create");
        newprof.setLayoutX(NEW_BUTTON_X);
        newprof.setLayoutY(NEW_BUTTON_Y);

        loginPane.getChildren().add(newprof);
        newprof.setOnAction(e->{
            createProfileScene cp = new createProfileScene();
            stage.setScene(cp.getScene());
        });
    }

    private void verifyUser(String u, String p) throws FileNotFoundException {
        int flag = 0;
        retrieveData();
        for(String x:profile.keySet()){
            if(u.equals(x)){
                if(p.equals(profile.get(x))){
                    flag = 1;
                    userName = u;
                    incorrectPassword.setVisible(false);
                    ViewManager xd = new ViewManager();
                    stage.setScene(xd.getStaticScene());
                    break;
                }
            }
        }
        if(flag==0){
            incorrectPassword.setVisible(true);
        }
    }

    private void retrieveData() throws FileNotFoundException {
        profile.clear();
        FileInputStream file = new FileInputStream(new File("src/pr.txt"));
        Scanner scanner = new Scanner(file);
        while(scanner.hasNextLine()){
            String [] row = scanner.nextLine().split(" ");
            profile.put(row[0],row[1]);
        }
        try {
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static String getUserName(){return userName;}


}
