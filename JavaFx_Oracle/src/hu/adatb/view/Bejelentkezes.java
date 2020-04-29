package hu.adatb.view;

import hu.adatb.controller.*;
import hu.adatb.model.Felhasznalo;
import hu.adatb.utils.Utils;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.List;


public class Bejelentkezes extends Stage {

    FelhasznaloController controller;
    JaratController jaratController;
    BiztositasController biztositasController;
    FoglalController foglalController;
    BiztositController biztositController;
    AlkalmazottController alkalmazottController;


    public Bejelentkezes(FelhasznaloController fcontroller, JaratController j, BiztositasController b, FoglalController fo, BiztositController bc, AlkalmazottController ac){

        this.controller = fcontroller;
        this.jaratController = j;
        this.biztositasController = b;
        this.foglalController = fo;
        this.biztositController = bc;
        this.alkalmazottController = ac;
        construct();
    }

    private void construct() {
        /* DECLARATION START */
        BorderPane mainframe = new BorderPane();
        mainframe.setPrefSize(1400,900);
        mainframe.getStyleClass().add("mainframe");

        HBox box = new HBox();
        box.setMaxSize(1300,700);
        box.getStyleClass().add("box");

        VBox registrationBox = new VBox(80);
        registrationBox.setPrefSize(650,700);
        registrationBox.setPadding(new Insets(20,20,20,20));
        registrationBox.setAlignment(Pos.TOP_CENTER);
        registrationBox.getStyleClass().add("registrationBox");

        VBox loginBox = new VBox(80);
        loginBox.setPrefSize(650,700);
        loginBox.setPadding(new Insets(20,20,20,20));
        loginBox.setAlignment(Pos.TOP_CENTER);
        loginBox.getStyleClass().add("loginBox");

        /* DECLARATION END */

        /* LOGIN  START */
            /*Head*/
        VBox headerLogin = new VBox(20);
        headerLogin.setAlignment(Pos.CENTER);

        Text title = new Text ("Van már fiókod?");
        title.getStyleClass().add("title");

        Text lineLogin = new Text("                    ");
        lineLogin.getStyleClass().add("lineLogin");

        Text buzditoText = new Text("Akkor nincs más dolgod, csak belépni!");
        buzditoText.getStyleClass().add("buzditoText");

        headerLogin.getChildren().addAll(title,lineLogin,buzditoText);

            /*Body*/
        VBox bodyLogin = new VBox(20);
        bodyLogin.setAlignment(Pos.CENTER);

        TextField fhTF = new TextField();
        fhTF.setPromptText("Felhasználónév vagy e-mail cím");
        fhTF.setMaxWidth(400);
        fhTF.setPrefHeight(50);
        fhTF.getStyleClass().add("loginTF");

        PasswordField pwTF = new PasswordField();
        pwTF.setPromptText("Jelszó");
        pwTF.setMaxWidth(400);
        pwTF.setPrefHeight(50);
        pwTF.getStyleClass().add("loginTF");

        Text hibaText = new Text();
        hibaText.getStyleClass().add("hibaText");
        hibaText.setVisible(false);

        Button loginButton = new Button("Belépés");
        loginButton.setPrefSize(400,50);
        loginButton.getStyleClass().add("loginButton");
        loginButton.setOnAction(e->{
            hibaText.setVisible(false);
            List<Felhasznalo> felhasznaloList = controller.getAll();

            for(int i = 0; i < felhasznaloList.size(); i++){
                if ( (fhTF.getText().equals(felhasznaloList.get(i).getFelhasznalonev()) && pwTF.getText().equals(felhasznaloList.get(i).getPassword()))
                || (fhTF.getText().equals(felhasznaloList.get(i).getEmail())) && pwTF.getText().equals(felhasznaloList.get(i).getPassword())){
                    if(felhasznaloList.get(i).isAdmin()){
                        new FooldalAdmin(felhasznaloList.get(i),this.jaratController,this.biztositasController,this.foglalController,this.biztositController, this.alkalmazottController);
                        close();
                    }
                    else{
                        new FooldalUser(felhasznaloList.get(i),this.jaratController,this.biztositasController,this.foglalController, this.biztositController);
                        close();
                    }
                }
                if ( (fhTF.getText().equals(felhasznaloList.get(i).getFelhasznalonev()) && !pwTF.getText().equals(felhasznaloList.get(i).getPassword()))
                        || (fhTF.getText().equals(felhasznaloList.get(i).getEmail())) && !pwTF.getText().equals(felhasznaloList.get(i).getPassword())){
                    hibaText.setText("Hibás jelszó!");
                    hibaText.setVisible(true);
                }

            }
        });


        bodyLogin.getChildren().addAll(fhTF,pwTF,hibaText,loginButton);

        Label forgotLabel = new Label("Elfelejtetted a jelszavad?");
        forgotLabel.getStyleClass().add("forgotLabel");
        forgotLabel.setOnMouseClicked(e->{
            Alert askHelp = new Alert(Alert.AlertType.INFORMATION);
            askHelp.setTitle("Kérj segítséget!");
            askHelp.setHeaderText("Írj egy e-mailt nekünk: ");
            askHelp.setContentText("wedontcare@all.com");
            askHelp.show();
        });

        /* LOGIN  END */

        /* REGISTRATION START */

            /*Head*/
        VBox headerReg = new VBox(20);
        headerReg.setAlignment(Pos.CENTER);

        Text regText = new Text("Regisztrálj most!");
        regText.getStyleClass().add("regisztracioText");

        Text lineReg = new Text("                    ");
        lineReg.getStyleClass().add("lineReg");

        Text buzditoReg = new Text("És szeld körbe Európát!");
        buzditoReg.getStyleClass().add("buzditoReg");

        headerReg.getChildren().addAll(regText,lineReg,buzditoReg);


            /*Body*/
        VBox bodyReg = new VBox(20);
        bodyReg.setAlignment(Pos.CENTER);

        TextField regfhTF = new TextField();
        regfhTF.setMaxWidth(400);
        regfhTF.setPrefHeight(50);
        regfhTF.setPromptText("felhasználónév");
        final BooleanProperty firstTime = new SimpleBooleanProperty(true); // Variable to store the focus on stage load
        regfhTF.focusedProperty().addListener((observable,  oldValue,  newValue) -> {
            if(newValue && firstTime.get()){
                mainframe.requestFocus(); // Delegate the focus to container
                firstTime.setValue(false); // Variable value changed for future references
            }
        });
        regfhTF.getStyleClass().add("loginTF");


        TextField regemailTF = new TextField();
        regemailTF.setMaxWidth(400);
        regemailTF.setPrefHeight(50);
        regemailTF.setPromptText("E-mail cím");
        regemailTF.getStyleClass().add("loginTF");

        PasswordField regpwTF = new PasswordField();
        regpwTF.setMaxWidth(400);
        regpwTF.setPrefHeight(50);
        regpwTF.setPromptText("Jelszó");
        regpwTF.getStyleClass().add("loginTF");

        HBox birthYearBox = new HBox(20);
        birthYearBox.setAlignment(Pos.CENTER);

        Text regBirthYearText = new Text ("Születési év:");
        regBirthYearText.getStyleClass().add("regBirthYearText");
        Spinner<Integer> regBirthYearTF = new Spinner<>(1900,2020,2020,1);
        regBirthYearTF.setEditable(true);

        bodyReg.getChildren().addAll(regfhTF,regemailTF,regpwTF,birthYearBox);

        Button regist = new Button("Regisztráció");
        regist.setPrefSize(200,50);
        regist.setOnAction(e->{
            List<Felhasznalo> felhasznaloList = controller.getAll();
            for(int i = 0; i < felhasznaloList.size(); i++){
                if (felhasznaloList.get(i).getEmail().equals(regemailTF.getText()) || felhasznaloList.get(i).getFelhasznalonev().equals(regfhTF.getText())){
                    Utils.showWarning("Ez a felhasznalónév vagy e-mail cím már foglalt!");
                    return;
                }
            }
            if (regfhTF.getText().length() > 30){
                Utils.showWarning("A felhasználónév nem lehet 30 karakternél több+");
                return;
            }
            if (regfhTF.getText().contentEquals("")){
                Utils.showWarning("A felhasználónév nem lehet üres!");
                return;
            }
            if (regemailTF.getText().contentEquals("") && regemailTF.getText().contains("@") && regemailTF.getText().contains(".")) {
                Utils.showWarning("Az email címet meg kell adni!");
                return;
            }
            if (regpwTF.getText().contentEquals("")) {
                Utils.showWarning("Az jelszó kitöltése kötelező!");
                return;
            }
            if (controller.add( new Felhasznalo(regfhTF.getText(),regemailTF.getText(),regpwTF.getText(),false,regBirthYearTF.getValue()))) {
                Utils.showInfo("Sikeres regisztráció!");
                regfhTF.setText("");
                regemailTF.setText("");
                regpwTF.setText("");
                regBirthYearTF.getValueFactory().setValue(2020);
            } else {
                Utils.showWarning("Nem sikerült a regisztráció!");
                return;
            }
        });
        regist.getStyleClass().add("registButton");

        /* PHOTO END */

        /* STRUKTURA START*/
        birthYearBox.getChildren().addAll(regBirthYearText,regBirthYearTF);
        registrationBox.getChildren().addAll(headerReg,bodyReg,regist);
        loginBox.getChildren().addAll(headerLogin, bodyLogin,forgotLabel);
        box.getChildren().addAll(registrationBox,loginBox);

        HBox topFiller = new HBox();
        topFiller.setPrefSize(1400,100);
        topFiller.getStyleClass().add("topFiller");

        HBox botFiller = new HBox();
        botFiller.setPrefSize(1400,100);
        botFiller.getStyleClass().add("botFiller");

        HBox leftFiller = new HBox();
        leftFiller.setMaxSize(50,700);
        leftFiller.getStyleClass().add("leftFiller");

        HBox rightFiller = new HBox();
        rightFiller.setMaxSize(50,700);
        rightFiller.getStyleClass().add("leftFiller");

        mainframe.setTop(topFiller);
        mainframe.setBottom(botFiller);
        mainframe.setLeft(leftFiller);
        mainframe.setRight(rightFiller);
        mainframe.setCenter(box);


        Scene scene = new Scene(mainframe, 1400, 900);
        scene.getStylesheets().add("hu/adatb/utils/css/bejelentkezesStyle.css");
        this.setScene(scene);
        this.setResizable(false);
        this.setTitle("Repjegyfoglalás");
        this.getIcons().add(new Image("hu/adatb/utils/images/icon.png"));
        this.show();
        /* STRUKTURA END */
    }


}
