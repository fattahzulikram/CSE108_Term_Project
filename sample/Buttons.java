package sample;

import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseButton;
import javafx.scene.text.Font;

class Buttons extends Button {
    private final String BUTTON_REGULAR = "-fx-background-color: transparent; -fx-background-image: url('sample/Buttons/button.png')";
    private final String BUTTON_PRESSED = "-fx-background-color: transparent; -fx-background-image: url('sample/Buttons/PressedButton.png')";

    Buttons(String title){
        setText(title);
        setFontStyle();
        setPrefHeight(49);
        setPrefWidth(190);
        setStyle(BUTTON_REGULAR);
        Actions();

    }

    private void setFontStyle(){
            setFont(Font.font("KenVector Future",23));
    }

    private void Pressed(){
        setStyle(BUTTON_PRESSED);
        setPrefHeight(45);
        setLayoutY(getLayoutY()+4);
    }

    private void Released(){
        setStyle(BUTTON_REGULAR);
        setPrefHeight(49);
        setLayoutY(getLayoutY()-4);
    }

    private void Actions(){
        setOnMousePressed(e->{
            if(e.getButton().equals(MouseButton.PRIMARY))
                Pressed();
        });
        setOnMouseReleased(e->{
            if(e.getButton().equals(MouseButton.PRIMARY))
                Released();
        });
        setOnMouseEntered(e-> setEffect(new DropShadow()));
        setOnMouseExited(e-> setEffect(null));
    }
}
