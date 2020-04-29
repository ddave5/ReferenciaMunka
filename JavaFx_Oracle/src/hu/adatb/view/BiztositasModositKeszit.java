package hu.adatb.view;

import hu.adatb.controller.BiztositasController;
import hu.adatb.model.Biztositas;
import hu.adatb.utils.Utils;
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


public class BiztositasModositKeszit extends Stage {

    BiztositasController biztositasController;
    Biztositas biztositas;

    public BiztositasModositKeszit(BiztositasController jc, Biztositas j){
        this.biztositasController = jc;
        this.biztositas = j;
        constructModosit();
    }

    public BiztositasModositKeszit(BiztositasController jc){
        this.biztositasController = jc;
        biztositas = new Biztositas();
        constructHozzaad();
    }

    private void constructModosit() {

        List<Biztositas> biztositasList = biztositasController.getAll();
        Set<String> cegSet = new HashSet<>();
        Set<String> tipusSet = new HashSet<>();

        GridPane mainframe = new GridPane();
        mainframe.setPadding(new Insets(20,20,20,20));
        mainframe.setVgap(30);
        mainframe.setHgap(100);
        mainframe.getStyleClass().add("mainframe");

        Text cegText = new Text("Cég:");
        cegText.getStyleClass().add("leftText");
        mainframe.add(cegText,0,0);
        Text tipusText = new Text("Típus:");
        tipusText.getStyleClass().add("leftText");
        mainframe.add(tipusText,0,1);
        Text osszegText = new Text("Összeg:");
        osszegText.getStyleClass().add("leftText");
        mainframe.add(osszegText,0,2);
        Text idotartamText = new Text("Időtartam:");
        idotartamText.getStyleClass().add("leftText");
        mainframe.add(idotartamText,0,3);

        ChoiceBox cegCB = new ChoiceBox ();
        cegCB.setPrefSize(200,30);
        for (Biztositas b : biztositasList){
            cegSet.add(b.getCeg());
        }
        List<String> cegList = new ArrayList<>(cegSet);
        Collections.sort(cegList);
        cegCB.getItems().addAll(cegList.toArray());
        cegCB.setValue(biztositas.getCeg());
        mainframe.add(cegCB,1,0);

        ChoiceBox tipusCB = new ChoiceBox();
        tipusCB.setPrefSize(200,30);
        for (Biztositas b : biztositasList){
            tipusSet.add(b.getTipus());
        }
        List<String> tipusList = new ArrayList<>(tipusSet);
        Collections.sort(tipusList);
        tipusCB.getItems().addAll(tipusList.toArray());
        tipusCB.setValue(biztositas.getTipus());
        mainframe.add(tipusCB,1,1);

        TextField osszegTF = new TextField(Integer.toString(biztositas.getOsszeg()));
        mainframe.add(osszegTF,1,2);

        Spinner<Integer> idotartamS = new Spinner<>(1,60,biztositas.getIdotartam(),1);
        idotartamS.setEditable(true);
        mainframe.add(idotartamS,1,3);


        HBox buttonBox = new HBox(20);
        buttonBox.setAlignment(Pos.CENTER);
        Button modositButton = new Button("Módosít");
        modositButton.getStyleClass().add("botButton");
        modositButton.setOnAction(event -> {
            if( cegCB.getValue() == null || tipusCB.getValue() == null || osszegTF.getText().equals("") ){
                Utils.showWarning("Minden adat megadása kötelező!");
                return;
            }
            else if (!osszegTF.getText().equals("")){
                int osszeg;
                try{
                    osszeg = Integer.parseInt(osszegTF.getText());

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
            biztositasController.modify(new Biztositas(biztositas.getBiztositasid(),tipusCB.getValue().toString(),cegCB.getValue().toString(),Integer.parseInt(osszegTF.getText()),idotartamS.getValue()));
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

        List<Biztositas> biztositasList = biztositasController.getAll();
        Set<String> cegSet = new HashSet<>();
        Set<String> tipusSet = new HashSet<>();

        GridPane mainframe = new GridPane();
        mainframe.setPadding(new Insets(20,20,20,20));
        mainframe.setVgap(30);
        mainframe.setHgap(100);
        mainframe.getStyleClass().add("mainframe");

        Text cegText = new Text("Cég:");
        cegText.getStyleClass().add("leftText");
        mainframe.add(cegText,0,0);
        Text tipusText = new Text("Típus:");
        tipusText.getStyleClass().add("leftText");
        mainframe.add(tipusText,0,1);
        Text osszegText = new Text("Összeg:");
        osszegText.getStyleClass().add("leftText");
        mainframe.add(osszegText,0,2);
        Text idotartamText = new Text("Időtartam:");
        idotartamText.getStyleClass().add("leftText");
        mainframe.add(idotartamText,0,3);

        ChoiceBox cegCB = new ChoiceBox ();
        cegCB.setPrefSize(200,30);
        for (Biztositas b : biztositasList){
            cegSet.add(b.getCeg());
        }
        List<String> cegList = new ArrayList<>(cegSet);
        Collections.sort(cegList);
        cegCB.getItems().addAll(cegList.toArray());
        mainframe.add(cegCB,1,0);

        ChoiceBox tipusCB = new ChoiceBox();
        tipusCB.setPrefSize(200,30);
        for (Biztositas b : biztositasList){
            tipusSet.add(b.getTipus());
        }
        List<String> tipusList = new ArrayList<>(tipusSet);
        Collections.sort(tipusList);
        tipusCB.getItems().addAll(tipusList.toArray());
        mainframe.add(tipusCB,1,1);

        TextField osszegTF = new TextField();
        mainframe.add(osszegTF,1,2);

        Spinner<Integer> idotartamS = new Spinner<>(1,60,biztositas.getIdotartam(),1);
        idotartamS.setEditable(true);
        mainframe.add(idotartamS,1,3);


        HBox buttonBox = new HBox(20);
        buttonBox.setAlignment(Pos.CENTER);
        Button modositButton = new Button("Módosít");
        modositButton.getStyleClass().add("botButton");
        modositButton.setOnAction(event -> {
            if(osszegTF.getText().equals("") || cegCB.getValue() == null || tipusCB.getValue() == null ){
                Utils.showWarning("Minden adat megadása kötelező!");
                return;
            }
            else if (!osszegTF.getText().equals("")){
                int osszeg;
                try{
                    osszeg = Integer.parseInt(osszegTF.getText());

                } catch (NumberFormatException ex) {
                    Utils.showWarning("Számokat adjon meg a megfelelő mezőkbe");
                    return;
                }
            }

            biztositasController.add(new Biztositas((biztositasList.size()+1),tipusCB.getValue().toString(),cegCB.getValue().toString(),Integer.parseInt(osszegTF.getText()),idotartamS.getValue()));

            Alert successAlert = new Alert(Alert.AlertType.INFORMATION,"Sikeres hozzáadás");
            successAlert.showAndWait();

            this.close();
        });

        Button visszaButton = new Button("Vissza");
        visszaButton.getStyleClass().add("botButton");
        visszaButton.setOnAction(event -> {
            this.close();
        });
        buttonBox.getChildren().addAll(modositButton,visszaButton);

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
