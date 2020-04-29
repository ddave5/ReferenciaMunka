package hu.adatb.view;

import hu.adatb.controller.JaratController;
import hu.adatb.model.Jarat;
import hu.adatb.utils.Utils;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;


public class JaratModositKeszit extends Stage {

    JaratController jaratController;
    Jarat jarat;

    public JaratModositKeszit(JaratController jc, Jarat j){
        this.jaratController = jc;
        this.jarat = j;
        constructModosit();
    }

    public JaratModositKeszit(JaratController jc){
        this.jaratController = jc;
        jarat = new Jarat();
        constructHozzaad();
    }

    private void constructModosit() {

        List<Jarat> jaratList = jaratController.getAll();
        Set<String> honnanhelyek = new HashSet<>();
        Set<String> hovahelyek = new HashSet<>();

        GridPane mainframe = new GridPane();
        mainframe.setPadding(new Insets(20,20,20,20));
        mainframe.setVgap(30);
        mainframe.setHgap(100);
        mainframe.getStyleClass().add("mainframe");

        Text arText = new Text("Ár:");
        arText.getStyleClass().add("leftText");
        mainframe.add(arText,0,0);
        Text maxUtasText = new Text("Maximális utasszám:");
        maxUtasText.getStyleClass().add("leftText");
        mainframe.add(maxUtasText,0,1);
        Text statuszText = new Text("Státusz");
        statuszText.getStyleClass().add("leftText");
        mainframe.add(statuszText,0,2);
        Text honnanText = new Text("Indulási helyszín:");
        honnanText.getStyleClass().add("leftText");
        mainframe.add(honnanText,0,3);
        Text hovaText = new Text("Érkezési helyszín:");
        hovaText.getStyleClass().add("leftText");
        mainframe.add(hovaText,0,4);
        Text indulasText = new Text("Indulási időpont:");
        indulasText.getStyleClass().add("leftText");
        mainframe.add(indulasText,0,5);

        TextField arTF = new TextField(Integer.toString(jarat.getAr()));
        arTF.setMaxSize(200,50);
        final BooleanProperty firstTime = new SimpleBooleanProperty(true); // Variable to store the focus on stage load
        arTF.focusedProperty().addListener((observable,  oldValue,  newValue) -> {
            if(newValue && firstTime.get()){
                mainframe.requestFocus(); // Delegate the focus to container
                firstTime.setValue(false); // Variable value changed for future references
            }
        });
        mainframe.add(arTF,1,0);

        TextField maxUtasTF = new TextField(Integer.toString(jarat.getMax_utas()));
        mainframe.add(maxUtasTF,1,1);

        ChoiceBox statuszCB = new ChoiceBox();
        statuszCB.getItems().add("AKTIV");
        statuszCB.getItems().add("KESIK");
        statuszCB.getItems().add("TOROLVE");
        statuszCB.setValue("AKTIV");
        mainframe.add(statuszCB,1,2);

        ChoiceBox honnanCB = new ChoiceBox ();
        honnanCB.setPrefSize(200,30);
        honnanCB.setValue(jarat.getHonnan());
        for (Jarat j : jaratList){
            honnanhelyek.add(j.getHonnan());
        }
        List<String> honnanList = new ArrayList<>(honnanhelyek);
        Collections.sort(honnanList);
        honnanCB.getItems().addAll(honnanList.toArray());
        honnanCB.getStyleClass().add("rightElements");
        mainframe.add(honnanCB,1,3);

        ChoiceBox hovaCB = new ChoiceBox();
        hovaCB.setPrefSize(200,30);
        hovaCB.setValue(jarat.getHova());
        for (Jarat j : jaratList){
            hovahelyek.add(j.getHova());
        }
        List<String> hovaList = new ArrayList<>(hovahelyek);
        Collections.sort(hovaList);
        hovaCB.getItems().addAll(hovaList.toArray());
        hovaCB.getStyleClass().add("rightElements");
        mainframe.add(hovaCB,1,4);

        DatePicker mikorDP = new DatePicker();
        mikorDP.setPrefSize(200,30);
        mikorDP.setValue(dtold(jarat.getIndulas()));
        mikorDP.getStyleClass().add("rightElements");
        mainframe.add(mikorDP,1,5);


        HBox buttonBox = new HBox(20);
        buttonBox.setAlignment(Pos.CENTER);
        Button modositButton = new Button("Módosít");
        modositButton.getStyleClass().add("botButton");
        modositButton.setOnAction(event -> {
            if(arTF.getText().equals("") || maxUtasTF.getText().equals("") || honnanCB.getValue() == null || hovaCB.getValue() == null || mikorDP.getValue() == null ){
                Utils.showWarning("Minden adat megadása kötelező!");
                return;
            }
            else if (!arTF.getText().equals("") && !maxUtasTF.getText().equals("")){
                int minosszeg, maxosszeg;
                try{
                    minosszeg = Integer.parseInt(arTF.getText());
                    maxosszeg = Integer.parseInt(maxUtasTF.getText());
                } catch (NumberFormatException ex) {
                    Utils.showWarning("Számokat adjon meg a megfelelő mezőkbe");
                    return;
                }
            }
            if (honnanCB.getValue().toString().equals(hovaCB.getValue().toString())){
                Utils.showWarning("Egyező az indulási és érkezési helyszín");
                return;
            }
            /*System.out.println("UPDATE Jarat SET Jarat.max_utas = " + maxUtasTF.getText() + ", Jarat.ar = " + arTF.getText()+ ", Jarat.statusz = '" + statuszTF.getText()
                    +"', Jarat.honnan = '" + honnanCB.getValue() + "', Jarat.hova = '" + hovaCB.getValue()
                    +"', Jarat.indulas = '" + (mikorDP.getValue().getYear()-2000)+"-"+ datumalakito(mikorDP.getValue().getMonthValue())+"-"+mikorDP.getValue().getDayOfMonth()
                    +"', Jarat.erkezes = '" + (mikorDP.getValue().getYear()-2000)+"-"+ datumalakito(mikorDP.getValue().getMonthValue())+"-"+mikorDP.getValue().getDayOfMonth()
                    + "' WHERE Jarat.jaratszam = " + jarat.getJaratszam());
            */
            jaratController.modify("UPDATE Jarat SET Jarat.max_utas = " + maxUtasTF.getText() + ", Jarat.ar = " + arTF.getText()+ ", Jarat.statusz = '" + statuszCB.getValue()
                    +"', Jarat.honnan = '" + honnanCB.getValue() + "', Jarat.hova = '" + hovaCB.getValue()
                    +"', Jarat.indulas = '" + (mikorDP.getValue().getYear()-2000)+"-"+ datumalakito(mikorDP.getValue().getMonthValue())+"-"+mikorDP.getValue().getDayOfMonth()
                    +"', Jarat.erkezes = '" + (mikorDP.getValue().getYear()-2000)+"-"+ datumalakito(mikorDP.getValue().getMonthValue())+"-"+mikorDP.getValue().getDayOfMonth()
                    + "' WHERE Jarat.jaratszam = " + jarat.getJaratszam());
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

        List<Jarat> jaratList = jaratController.getAll();
        Set<String> honnanhelyek = new HashSet<>();
        Set<String> hovahelyek = new HashSet<>();

        GridPane mainframe = new GridPane();
        mainframe.setPadding(new Insets(20,20,20,20));
        mainframe.setVgap(30);
        mainframe.setHgap(100);
        mainframe.getStyleClass().add("mainframe");

        Text arText = new Text("Ár:");
        arText.getStyleClass().add("leftText");
        mainframe.add(arText,0,0);
        Text maxUtasText = new Text("Maximális utasszám:");
        maxUtasText.getStyleClass().add("leftText");
        mainframe.add(maxUtasText,0,1);
        Text honnanText = new Text("Indulási helyszín:");
        honnanText.getStyleClass().add("leftText");
        mainframe.add(honnanText,0,2);
        Text hovaText = new Text("Érkezési helyszín:");
        hovaText.getStyleClass().add("leftText");
        mainframe.add(hovaText,0,3);
        Text indulasText = new Text("Indulási időpont:");
        indulasText.getStyleClass().add("leftText");
        mainframe.add(indulasText,0,4);

        TextField arTF = new TextField();
        arTF.setPromptText("pl.: 50000");
        arTF.setMaxSize(200,50);
        final BooleanProperty firstTime = new SimpleBooleanProperty(true); // Variable to store the focus on stage load
        arTF.focusedProperty().addListener((observable,  oldValue,  newValue) -> {
            if(newValue && firstTime.get()){
                mainframe.requestFocus(); // Delegate the focus to container
                firstTime.setValue(false); // Variable value changed for future references
            }
        });
        mainframe.add(arTF,1,0);

        TextField maxUtasTF = new TextField();
        maxUtasTF.setPromptText("pl.: 300");
        mainframe.add(maxUtasTF,1,1);

        ChoiceBox honnanCB = new ChoiceBox ();
        honnanCB.setPrefSize(200,30);
        for (Jarat j : jaratList){
            honnanhelyek.add(j.getHonnan());
        }
        List<String> honnanList = new ArrayList<>(honnanhelyek);
        Collections.sort(honnanList);
        honnanCB.getItems().addAll(honnanList.toArray());
        honnanCB.getStyleClass().add("rightElements");
        mainframe.add(honnanCB,1,2);

        ChoiceBox hovaCB = new ChoiceBox();
        hovaCB.setPrefSize(200,30);
        for (Jarat j : jaratList){
            hovahelyek.add(j.getHova());
        }
        List<String> hovaList = new ArrayList<>(hovahelyek);
        Collections.sort(hovaList);
        hovaCB.getItems().addAll(hovaList.toArray());
        hovaCB.getStyleClass().add("rightElements");
        mainframe.add(hovaCB,1,3);


        DatePicker mikorDP = new DatePicker();
        mikorDP.setPrefSize(200,30);
        mikorDP.getStyleClass().add("rightElements");
        mainframe.add(mikorDP,1,4);

        HBox buttonBox = new HBox(20);
        buttonBox.setAlignment(Pos.CENTER);
        Button hozzaadButton = new Button("Hozzáad");
        hozzaadButton.getStyleClass().add("botButton");
        hozzaadButton.setOnAction(event -> {
            if(arTF.getText().equals("") || maxUtasTF.getText().equals("") || honnanCB.getValue() == null || hovaCB.getValue() == null || mikorDP.getValue() == null ){
                Utils.showWarning("Minden adat megadása kötelező!");
                return;
            }
            else if (!arTF.getText().equals("") && !maxUtasTF.getText().equals("")){
                int minosszeg, maxosszeg;
                try{
                    minosszeg = Integer.parseInt(arTF.getText());
                    maxosszeg = Integer.parseInt(maxUtasTF.getText());
                } catch (NumberFormatException ex) {
                    Utils.showWarning("Számokat adjon meg a megfelelő mezőkbe");
                    return;
                }
            }
            if (honnanCB.getValue().toString().equals(hovaCB.getValue().toString())){
                Utils.showWarning("Egyező az indulási és érkezési helyszín");
                return;
            }

            jaratController.add("INSERT INTO Jarat(jaratszam,max_utas,ar,statusz,honnan,hova,indulas,erkezes) VALUES (" +
                    ""+(jaratList.size()+1) +","+ Integer.parseInt(maxUtasTF.getText()) + "," + Integer.parseInt(arTF.getText()) + ",'AKTIV','"+ honnanCB.getValue() + "','" + hovaCB.getValue() +
                    "','"+ (mikorDP.getValue().getYear()-2000)+"-"+ datumalakito(mikorDP.getValue().getMonthValue())+"-"+mikorDP.getValue().getDayOfMonth()+
                    "','"+ (mikorDP.getValue().getYear()-2000)+"-"+ datumalakito(mikorDP.getValue().getMonthValue())+"-"+mikorDP.getValue().getDayOfMonth()+"')");

            Alert successAlert = new Alert(Alert.AlertType.INFORMATION,"Sikeres hozzáadás");
            successAlert.showAndWait();

            this.close();
        });

        Button visszaButton = new Button("Vissza");
        visszaButton.getStyleClass().add("botButton");
        visszaButton.setOnAction(event -> {
            this.close();
        });
        buttonBox.getChildren().addAll(hozzaadButton,visszaButton);

        mainframe.add(buttonBox,0,5,2,1);

        Scene scene = new Scene(mainframe,500,400);
        scene.getStylesheets().add("hu/adatb/utils/css/dialogStyle.css");
        this.setScene(scene);
        this.setResizable(false);
        this.setTitle("Repjegyfoglalás");
        this.getIcons().add(new Image("hu/adatb/utils/images/icon.png"));
        this.show();

    }

    private String datumalakito(int honap){
        switch (honap){
            case 1: return "JAN.";
            case 2: return "FEBR.";
            case 3: return "MÁRC.";
            case 4: return "ÁPR.";
            case 5: return "MÁJ.";
            case 6: return "JÚN.";
            case 7: return "JÚL.";
            case 8: return "AUG.";
            case 9: return "SZEPT.";
            case 10: return "OKT.";
            case 11: return "NOV.";
            case 12: return "DEC.";
            default: return "";
        }


    }

    private LocalDate dtold(Date date){
        return Instant.ofEpochMilli(date.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }
}
