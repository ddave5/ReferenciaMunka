package hu.adatb.view;

import hu.adatb.controller.AlkalmazottController;
import hu.adatb.model.Alkalmazott;
import hu.adatb.utils.Utils;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.*;

public class AlkalmazottModositKeszit extends Stage {
    AlkalmazottController alkalmazottController;
    Alkalmazott alkalmazott;

    public AlkalmazottModositKeszit(AlkalmazottController ac, Alkalmazott a){
        this.alkalmazottController = ac;
        this.alkalmazott = a;
        constructModosit();
    }

    public AlkalmazottModositKeszit(AlkalmazottController ac){
        this.alkalmazottController = ac;
        alkalmazott = new Alkalmazott();
        constructHozzaad();
    }

    private void constructModosit() {

        List<Alkalmazott> alkalmazottList = alkalmazottController.getAll();
        Set<String> titulusSet = new HashSet<>();

        GridPane mainframe = new GridPane();
        mainframe.setPadding(new Insets(20,20,20,20));
        mainframe.setVgap(30);
        mainframe.setHgap(100);
        mainframe.getStyleClass().add("mainframe");

        Text nevText = new Text("Név:");
        nevText.getStyleClass().add("leftText");
        mainframe.add(nevText,0,0);
        Text titulusText = new Text("Titulus:");
        titulusText.getStyleClass().add("leftText");
        mainframe.add(titulusText,0,1);
        Text fizetesText = new Text("Fizetés:");
        fizetesText.getStyleClass().add("leftText");
        mainframe.add(fizetesText,0,2);

        TextField nevTF = new TextField(alkalmazott.getNev());
        nevTF.setPrefSize(200,30);

        mainframe.add(nevTF,1,0);

        ChoiceBox titulusCB = new ChoiceBox();
        titulusCB.setPrefSize(200,30);
        for (Alkalmazott a : alkalmazottList){
            titulusSet.add(a.getTitulus());
        }
        List<String> tipusList = new ArrayList<>(titulusSet);
        Collections.sort(tipusList);
        titulusCB.getItems().addAll(tipusList.toArray());
        titulusCB.setValue(alkalmazott.getTitulus());
        mainframe.add(titulusCB,1,1);

        TextField fizetesTF = new TextField(Integer.toString(alkalmazott.getFizetes()));
        fizetesTF.setPrefSize(200,30);
        mainframe.add(fizetesTF,1,2);


        HBox buttonBox = new HBox(20);
        buttonBox.setAlignment(Pos.CENTER);
        Button modositButton = new Button("Módosít");
        modositButton.getStyleClass().add("botButton");
        modositButton.setOnAction(event -> {
            if( nevTF.getText().equals("") || titulusCB.getValue() == null || fizetesTF.getText().equals("") ){
                Utils.showWarning("Minden adat megadása kötelező!");
                return;
            }
            else if (!fizetesTF.getText().equals("")){
                int osszeg;
                try{
                    osszeg = Integer.parseInt(fizetesTF.getText());

                } catch (NumberFormatException ex) {
                    Utils.showWarning("Számokat adjon meg a megfelelő mezőkbe");
                    return;
                }
            }

            /*System.out.println("UPDATE Jarat SET Jarat.max_utas = " + maxUtasTF.getText() + ", Jarat.ar = " + arTF.getText()+ ", Jarat.statusz = '" + statuszTF.getText()
                    +"', Jarat.honnan = '" + honnanCB.getValue() + "', Jarat.hova = '" + hovaCB.getValue()
                    +"', Jarat.indulas = '" + (mikorDP.getValue().getYear()-2000)+"-"+ datumalakito(mikorDP.getValue().getMonthValue())+"-"+mikorDP.getValue().getDayOfMonth()
                    +"', Jarat.erkezes = '" + (mikorDP.getValue().getYear()-2000)+"-"+ datumalakito(mikorDP.getValue().getMonthValue())+"-"+mikorDP.getValue().getDayOfMonth()
                    + "' WHERE Jarat.jaratszam = " + jarat.getJaratszam());
            */
            alkalmazottController.modify(new Alkalmazott(alkalmazott.getDolgozoid(),nevTF.getText(),titulusCB.getValue().toString(),Integer.parseInt(fizetesTF.getText())));
            Alert successAlert = new Alert(Alert.AlertType.INFORMATION,"Sikeresen módosítva!");
            successAlert.showAndWait();
            this.close();
        });


        Button visszaButton = new Button("Vissza");
        visszaButton.getStyleClass().add("botButton");
        visszaButton.setOnAction(event -> {
            this.close();
        });
        buttonBox.getChildren().addAll(modositButton,visszaButton);

        mainframe.add(buttonBox,0,6,2,1);

        Scene scene = new Scene(mainframe,500,400);
        scene.getStylesheets().add("hu/adatb/utils/css/dialogStyle.css");
        this.setScene(scene);
        this.setResizable(false);
        this.setTitle("Repjegyfoglalás");
        this.getIcons().add(new Image("hu/adatb/utils/images/icon.png"));
        this.show();

    }

    private void constructHozzaad() {

        List<Alkalmazott> alkalmazottList = alkalmazottController.getAll();
        Set<String> titulusSet = new HashSet<>();

        GridPane mainframe = new GridPane();
        mainframe.setPadding(new Insets(20,20,20,20));
        mainframe.setVgap(30);
        mainframe.setHgap(100);
        mainframe.getStyleClass().add("mainframe");

        Text nevText = new Text("Név:");
        nevText.getStyleClass().add("leftText");
        mainframe.add(nevText,0,0);
        Text titulusText = new Text("Titulus:");
        titulusText.getStyleClass().add("leftText");
        mainframe.add(titulusText,0,1);
        Text fizetesText = new Text("Fizetés:");
        fizetesText.getStyleClass().add("leftText");
        mainframe.add(fizetesText,0,2);

        TextField nevTF = new TextField();
        nevTF.setPromptText("Teljes nev");
        nevTF.setPrefSize(200,30);
        final BooleanProperty firstTime = new SimpleBooleanProperty(true); // Variable to store the focus on stage load
        nevTF.focusedProperty().addListener((observable,  oldValue,  newValue) -> {
            if(newValue && firstTime.get()){
                mainframe.requestFocus(); // Delegate the focus to container
                firstTime.setValue(false); // Variable value changed for future references
            }
        });

        mainframe.add(nevTF,1,0);

        ChoiceBox titulusCB = new ChoiceBox();
        titulusCB.setPrefSize(200,30);
        for (Alkalmazott a : alkalmazottList){
            titulusSet.add(a.getTitulus());
        }
        List<String> tipusList = new ArrayList<>(titulusSet);
        Collections.sort(tipusList);
        titulusCB.getItems().addAll(tipusList.toArray());
        mainframe.add(titulusCB,1,1);

        TextField fizetesTF = new TextField();
        fizetesTF.setPrefSize(200,30);
        mainframe.add(fizetesTF,1,2);


        HBox buttonBox = new HBox(20);
        buttonBox.setAlignment(Pos.CENTER);
        Button hozzaadButton = new Button("Hozzáadás");
        hozzaadButton.getStyleClass().add("botButton");
        hozzaadButton.setOnAction(event -> {
            if( nevTF.getText().equals("") || titulusCB.getValue() == null || fizetesTF.getText().equals("") ){
                Utils.showWarning("Minden adat megadása kötelező!");
                return;
            }
            else if (!fizetesTF.getText().equals("")){
                int osszeg;
                try{
                    osszeg = Integer.parseInt(fizetesTF.getText());

                } catch (NumberFormatException ex) {
                    Utils.showWarning("Számokat adjon meg a megfelelő mezőkbe");
                    return;
                }
            }

            /*System.out.println("UPDATE Jarat SET Jarat.max_utas = " + maxUtasTF.getText() + ", Jarat.ar = " + arTF.getText()+ ", Jarat.statusz = '" + statuszTF.getText()
                    +"', Jarat.honnan = '" + honnanCB.getValue() + "', Jarat.hova = '" + hovaCB.getValue()
                    +"', Jarat.indulas = '" + (mikorDP.getValue().getYear()-2000)+"-"+ datumalakito(mikorDP.getValue().getMonthValue())+"-"+mikorDP.getValue().getDayOfMonth()
                    +"', Jarat.erkezes = '" + (mikorDP.getValue().getYear()-2000)+"-"+ datumalakito(mikorDP.getValue().getMonthValue())+"-"+mikorDP.getValue().getDayOfMonth()
                    + "' WHERE Jarat.jaratszam = " + jarat.getJaratszam());
            */
            alkalmazottController.add(new Alkalmazott((alkalmazottList.size()+1),nevTF.getText(),titulusCB.getValue().toString(),Integer.parseInt(fizetesTF.getText())));
            Alert successAlert = new Alert(Alert.AlertType.INFORMATION,"Sikeresen módosítva!");
            successAlert.showAndWait();
            this.close();
        });


        Button visszaButton = new Button("Vissza");
        visszaButton.getStyleClass().add("botButton");
        visszaButton.setOnAction(event -> {
            this.close();
        });
        buttonBox.getChildren().addAll(hozzaadButton,visszaButton);

        mainframe.add(buttonBox,0,6,2,1);

        Scene scene = new Scene(mainframe,500,400);
        scene.getStylesheets().add("hu/adatb/utils/css/dialogStyle.css");
        this.setScene(scene);
        this.setResizable(false);
        this.setTitle("Repjegyfoglalás");
        this.getIcons().add(new Image("hu/adatb/utils/images/icon.png"));
        this.show();

    }
}
