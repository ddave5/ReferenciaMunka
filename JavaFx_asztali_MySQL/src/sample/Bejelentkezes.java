package sample;

import javafx.application.Application;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Bejelentkezes extends Parent{
    HBox felso, also;
    VBox bejelentkezo, egesz;
    Text cim,hiba;
    TextField email;
    PasswordField password;
    Button belepesgomb, regisztraciogomb;

    ConnectionUtils con = new ConnectionUtils();

    static Felhasznalo user;
    /*SET GLOBAL time_zone = '+2:00';*/
    public Bejelentkezes(){


        /////////////////FELÉPÍTÉS RÉSZ ///////////////////////
        egesz = new VBox();
        egesz.getStyleClass().add("egesz");

        felso = new HBox();
        felso.getStyleClass().add("felso");

        also = new HBox();
        also.getStyleClass().add("also");

        bejelentkezo = new VBox();
        bejelentkezo.getStyleClass().add("bejelentkezo");

        felso.getChildren().addAll(bejelentkezo);
        egesz.getChildren().addAll(felso,also);

        /////////////////FELÉPÍTÉS RÉSZ VÉGE//////////////////

        /////////////////BEJELENTKEZES RÉSZ//////////////////
        final BooleanProperty firstTime = new SimpleBooleanProperty(true); // Variable to store the focus on stage load

        cim = new Text("Műsorújság");
        cim.setTranslateX(500);
        cim.setTranslateY(40);
        cim.getStyleClass().add("cim");
        bejelentkezo.getChildren().add(cim);

        email = new TextField();
        email.setPromptText("E-mail cím vagy felhasználónév");
        email.setMaxWidth(400);
        email.setTranslateX(450);
        email.setTranslateY(140);
        email.getStyleClass().add("email");
        email.focusedProperty().addListener((observable,  oldValue,  newValue) -> {
            if(newValue && firstTime.get()){
                bejelentkezo.requestFocus(); // Delegate the focus to container
                firstTime.setValue(false); // Variable value changed for future references
            }
        });
        bejelentkezo.getChildren().add(email);

        password = new PasswordField();
        password.setPromptText("Jelszó");
        password.setMaxWidth(400);
        password.setTranslateX(450);
        password.setTranslateY(200);
        password.getStyleClass().add("email");
        bejelentkezo.getChildren().add(password);

        hiba = new Text("Felhasználónév vagy jelszó nem egyezik!");
        hiba.setTranslateX(440);
        hiba.setTranslateY(210);
        hiba.getStyleClass().add("hiba");
        hiba.setVisible(false);
        bejelentkezo.getChildren().add(hiba);

        /////////////////BEJELENTKEZES RÉSZ VÉGE//////////////////

        ///////////////// GOMBOK RÉSZ /////////////////////////////

        belepesgomb = new Button("Belépés");
        belepesgomb.setTranslateX(450);
        belepesgomb.setTranslateY(50);
        belepesgomb.getStyleClass().add("gomb");
        belepesgomb.setOnAction(event -> {
            for (Felhasznalo fh : con.listFelhasznalo()){

                if ((email.getText().equals(fh.getEmail()) && password.getText().equals(fh.getPassword()) || (email.getText().equals(fh.getNev())) && password.getText().equals(fh.getPassword()))){
                    hiba.setVisible(false);
                    user = fh;
                    Main.root.getChildren().clear();
                    Main.root.getChildren().add(new MainPage());
                }
            }
            hiba.setVisible(true);
        });

        also.getChildren().add(belepesgomb);

        regisztraciogomb = new Button("Regisztráció");
        regisztraciogomb.setTranslateX(610);
        regisztraciogomb.setTranslateY(50);
        regisztraciogomb.getStyleClass().add("gomb");
        regisztraciogomb.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Main.root.getChildren().clear();
                Main.root.getChildren().add(new Regisztracio());
            }
        });
        also.getChildren().add(regisztraciogomb);




        /////////////////MAIN RÉSZ ///////////////////////////
        Group roott = new Group();
        roott.getChildren().add(egesz);
        roott.getStyleClass().add("root");
        this.getChildren().add(roott);
        this.getStylesheets().add("view/styleBejelentkezo.css");


    }



}
