package sample;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Profile extends Parent {

    public Profile() {

        boolean pwisshown = false;
        VBox testlec;
        ConnectionUtils con = new ConnectionUtils();


        testlec = new VBox();
        testlec.getStyleClass().add("testlec");

        /////////////////////// TESTLÉC KEZDETE /////////////////////////


        /* testresz2 kezdete*/
        Text username = new Text("User Name");
        username.setTranslateX(500);
        username.setTranslateY(50);
        username.getStyleClass().add("details");
        testlec.getChildren().add(username);

        TextField correctusername = new TextField(Bejelentkezes.user.getNev());
        correctusername.getStyleClass().add("tf");
        correctusername.setMaxWidth(400);
        correctusername.setTranslateX(username.getTranslateX());
        correctusername.setTranslateY(70);
        correctusername.setDisable(true);
        testlec.getChildren().add(correctusername);

        Text email = new Text("E-mail");
        email.setTranslateX(username.getTranslateX());
        email.setTranslateY(110);
        email.getStyleClass().add("details");
        testlec.getChildren().add(email);

        TextField correctemail = new TextField(Bejelentkezes.user.getEmail());
        correctemail.getStyleClass().add("tf");
        correctemail.setMaxWidth(400);
        correctemail.setTranslateX(username.getTranslateX());
        correctemail.setTranslateY(130);
        correctemail.setDisable(true);
        testlec.getChildren().add(correctemail);

        Text password = new Text("Password");
        password.setTranslateX(username.getTranslateX());
        password.setTranslateY(170);
        password.getStyleClass().add("details");
        testlec.getChildren().add(password);

        PasswordField correctpassword = new PasswordField();
        correctpassword.setText(Bejelentkezes.user.getPassword());
        correctpassword.getStyleClass().add("tf");
        correctpassword.setMaxWidth(400);
        correctpassword.setTranslateX(username.getTranslateX());
        correctpassword.setTranslateY(190);
        correctpassword.setDisable(true);
        testlec.getChildren().add(correctpassword);

        TextField showpassword = new TextField();
        showpassword.setText(Bejelentkezes.user.getPassword());
        showpassword.getStyleClass().add("tf");
        showpassword.setMaxWidth(400);
        showpassword.setTranslateX(username.getTranslateX());
        showpassword.setTranslateY(190);
        showpassword.setEditable(false);
        testlec.getChildren().add(showpassword);

        CheckBox seePassword = new CheckBox("Jelszó megjelenítése");
        seePassword.setTranslateX(correctpassword.getTranslateX()+400+20);
        seePassword.setTranslateY(correctpassword.getTranslateY()-30);
        testlec.getChildren().add(seePassword);

        showpassword.setManaged(false);
        showpassword.setVisible(false);
        showpassword.managedProperty().bind(seePassword.selectedProperty());
        showpassword.visibleProperty().bind(seePassword.selectedProperty());

        correctpassword.managedProperty().bind(seePassword.selectedProperty().not());
        correctpassword.visibleProperty().bind(seePassword.selectedProperty().not());

        showpassword.textProperty().bindBidirectional(correctpassword.textProperty());

        Button savechanges = new Button("Jelszó mentése");
        savechanges.setTranslateX(username.getTranslateX()+250);
        savechanges.setTranslateY(230);
        savechanges.getStyleClass().add("savechanges");
        savechanges.setOnAction(e->{
            String jelszo = correctpassword.getText();
            savechanges.setDisable(true);
            correctusername.setDisable(true);
            correctemail.setDisable(true);
            correctpassword.setDisable(true);
            con.modositProfilt(Bejelentkezes.user,jelszo);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Sikeres csere");
            alert.setHeaderText(null);
            alert.setContentText("A jelszó megváltozott");

            alert.showAndWait();
        });
        savechanges.setDisable(true);
        testlec.getChildren().add(savechanges);

        Button edit = new Button("Jelszócsere");
        edit.setTranslateX(username.getTranslateX()-50);
        edit.setTranslateY(190);
        edit.getStyleClass().add("savechanges");
        edit.setOnAction(e->{
            showpassword.setEditable(true);
            correctpassword.setDisable(false);
            savechanges.setDisable(false);
            correctusername.setStyle("-fx-background-color: white;");
            correctemail.setStyle("-fx-background-color: white;");
            correctpassword.setStyle("-fx-background-color: white;");
        });
        testlec.getChildren().add(edit);

        /* testresz2 vége*/
        /////////////////////// TESTLÉC VÉGE /////////////////////////

        ////////////////////// MAIN RÉSZ////////////////////////////////
        Group roott = new Group();
        roott.getChildren().add(testlec);
        roott.getStyleClass().add("root");
        this.getChildren().add(roott);
        this.getStylesheets().add("view/styleProfile.css");
    }
}
