package sample;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Regisztracio extends Parent {

    public Regisztracio() {

        VBox egesz,testresz1, testresz2;
        HBox fejlec, testlec;

        ConnectionUtils con = new ConnectionUtils();

        fejlec = new HBox();
        testlec = new HBox();

        testlec.getStyleClass().add("test");
        testresz1 = new VBox();
        testresz1.getStyleClass().add("resz");
        testresz2 = new VBox();
        testresz2.getStyleClass().add("resz");

        final BooleanProperty firstTime = new SimpleBooleanProperty(true); // Variable to store the focus on stage load

        /////////////////////// TESTLÉC KEZDETE /////////////////////////

        Image csatornak = new Image("images/csatornak.gif");
        ImageView csatornakview = new ImageView(csatornak);
        csatornakview.getStyleClass().add("csatornak");
        csatornakview.setFitWidth(640);
        csatornakview.setFitHeight(620);

        testresz1.getChildren().add(csatornakview);
        /* testresz1 vége*/

        /* testresz2 kezdete*/
        Text username = new Text("Felhasználónév (max 20 karakter)");
        username.setTranslateX(50);
        username.setTranslateY(50);
        username.getStyleClass().add("details");
        testresz2.getChildren().add(username);

        TextField correctusername = new TextField();
        correctusername.setPromptText("pl.:Macska123");
        correctusername.getStyleClass().add("tf");
        correctusername.setMaxWidth(400);
        correctusername.setTranslateX(50);
        correctusername.setTranslateY(70);
        correctusername.focusedProperty().addListener((observable,  oldValue,  newValue) -> {
            if(newValue && firstTime.get()){
                testresz2.requestFocus(); // Delegate the focus to container
                firstTime.setValue(false); // Variable value changed for future references
            }
        });

        testresz2.getChildren().add(correctusername);

        Text email = new Text("E-mail");
        email.setTranslateX(50);
        email.setTranslateY(110);
        email.getStyleClass().add("details");
        testresz2.getChildren().add(email);

        TextField correctemail = new TextField();
        correctemail.setPromptText("pl.:macska123@totalcar.hu");
        correctemail.getStyleClass().add("tf");
        correctemail.setMaxWidth(400);
        correctemail.setTranslateX(50);
        correctemail.setTranslateY(130);
        testresz2.getChildren().add(correctemail);

        Text password = new Text("Password");
        password.setTranslateX(50);
        password.setTranslateY(170);
        password.getStyleClass().add("details");
        testresz2.getChildren().add(password);

        PasswordField correctpassword = new PasswordField();
        correctpassword.getStyleClass().add("tf");
        correctpassword.setMaxWidth(400);
        correctpassword.setTranslateX(50);
        correctpassword.setTranslateY(190);
        testresz2.getChildren().add(correctpassword);

        Text age = new Text("Születési év");
        age.setTranslateX(50);
        age.setTranslateY(230);
        age.getStyleClass().add("details");
        testresz2.getChildren().add(age);

        TextField correctage = new TextField();
        correctage.getStyleClass().add("tf");
        correctage.setMaxWidth(400);
        correctage.setTranslateX(50);
        correctage.setTranslateY(250);
        testresz2.getChildren().add(correctage);

        Button savechanges = new Button("Regisztráció");
        savechanges.setTranslateX(300);
        savechanges.setTranslateY(300);
        savechanges.getStyleClass().add("savechanges");
        savechanges.setOnAction(e->{
            /*savechanges.setDisable(true);
            correctusername.setDisable(true);
            correctemail.setDisable(true);
            correctpassword.setDisable(true);*/
            Text hiba = new Text("");
            if (!(correctemail.getText().contains("@")) || !(correctemail.getText().contains("."))){
                hiba.setText("Hibás E-mail cím");
                hiba.setTranslateX(50);
                hiba.setTranslateY(350);
                hiba.setStyle("-fx-fill: red; -fx-font-size: 24px;");
            }
            if (correctusername.getText().length() >20){
                hiba.setText("Túl hosszú felhasználónév");
                hiba.setTranslateX(50);
                hiba.setTranslateY(350);
                hiba.setStyle("-fx-fill: red; -fx-font-size: 24px;");
            }
            if(hiba.getText().equals("")){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Sikeres Regisztráció");
                alert.setHeaderText(null);
                alert.setContentText("Sikeres Regisztráció!");
                alert.showAndWait();
                Felhasznalo f = new Felhasznalo(correctusername.getText(), Integer.parseInt(correctage.getText()), correctemail.getText(), correctpassword.getText());
                con.ujFelhasznalo(f);
                Main.root.getChildren().clear();
                Main.root.getChildren().add(new Bejelentkezes());
            }
            testresz2.getChildren().add(hiba);
        });
        testresz2.getChildren().add(savechanges);

        /////////////////////// TESTLÉC VÉGE /////////////////////////

        ////////////////////// MAIN RÉSZ////////////////////////////////
        Group roott = new Group();
        egesz = new VBox();
        testlec.getChildren().addAll(testresz1,testresz2);
        egesz.getChildren().addAll(fejlec,testlec);
        roott.getChildren().add(egesz);
        roott.getStyleClass().add("root");
        this.getChildren().add(roott);
        this.getStylesheets().add("view/styleRegisztracio.css");
    }
}
