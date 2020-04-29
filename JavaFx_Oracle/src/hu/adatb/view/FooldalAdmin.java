package hu.adatb.view;

import hu.adatb.controller.*;
import hu.adatb.model.Alkalmazott;
import hu.adatb.model.Biztositas;
import hu.adatb.model.Felhasznalo;
import hu.adatb.model.Jarat;
import hu.adatb.utils.Utils;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.*;

public class FooldalAdmin extends Stage {

    Felhasznalo felhasznalo;
    FelhasznaloController felhasznaloController = new FelhasznaloController();
    JaratController jaratController;
    BiztositasController biztositasController;
    FoglalController foglalController;
    BiztositController biztositController;
    AlkalmazottController alkalmazottController ;

    TableView<Jarat> jaratTable;
    TableView<Biztositas> biztositasTable;
    TableView<Alkalmazott> alkalmazottTable;

    public FooldalAdmin(Felhasznalo f, JaratController j, BiztositasController b, FoglalController fc, BiztositController bc, AlkalmazottController ac) {
        this.felhasznalo = f;
        this.jaratController = j;
        this.biztositasController = b;
        this.foglalController = fc;
        this.biztositController = bc;
        this.alkalmazottController = ac;
        contruct();
    }

    private void contruct() {

        BorderPane mainframe = new BorderPane();
        mainframe.setPrefSize(1400,900);
        mainframe.getStyleClass().add("mainframe");

        HBox topNav = new HBox(50);
        topNav.setAlignment(Pos.CENTER_LEFT);
        topNav.setPadding(new Insets(20,20,20,20));
        topNav.setMaxSize(1400,200);

        ImageView logoView = new ImageView(new Image("hu/adatb/utils/images/europa.png"));
        logoView.setFitWidth(300);
        logoView.setFitHeight(150);


        Button jaratButton = new Button("Járatok");
        jaratButton.setPrefSize(200,50);
        jaratButton.getStyleClass().add("topNavButton");
        jaratButton.setOnAction(e->{

            mainframe.setCenter(new VBox());
            List<Jarat> jaratList = jaratController.getAll();
            Set<String> honnanhelyek = new HashSet<>();
            Set<String> hovahelyek = new HashSet<>();

            VBox searchBox = new VBox(50);
            searchBox.setPrefSize(300,650);
            searchBox.setAlignment(Pos.TOP_CENTER);
            searchBox.setPadding(new Insets(20,20,20,20));

            HBox honnanBox = new HBox(10);
            honnanBox.setAlignment(Pos.CENTER_RIGHT);
            Text honnanText = new Text("Honnan indul? ");
            honnanText.getStyleClass().add("leftText");

            ChoiceBox honnanCB = new ChoiceBox ();
            honnanCB.setPrefSize(200,30);
            for (Jarat j : jaratList){
                honnanhelyek.add(j.getHonnan());
            }
            List<String> honnanList = new ArrayList<>(honnanhelyek);
            Collections.sort(honnanList);
            honnanCB.getItems().addAll(honnanList.toArray());
            honnanCB.getStyleClass().add("leftSideBars");
            honnanBox.getChildren().addAll(honnanText,honnanCB);

            HBox hovaBox = new HBox(10);
            hovaBox.setAlignment(Pos.CENTER_RIGHT);
            Text hovaText = new Text("Hova megy? ");
            hovaText.getStyleClass().add("leftText");
            ChoiceBox hovaCB = new ChoiceBox();
            hovaCB.setPrefSize(200,30);
            for (Jarat j : jaratList){
                hovahelyek.add(j.getHova());
            }
            List<String> hovaList = new ArrayList<>(hovahelyek);
            Collections.sort(hovaList);
            hovaCB.getItems().addAll(hovaList.toArray());
            hovaCB.getStyleClass().add("leftSideBars");
            hovaBox.getChildren().addAll(hovaText,hovaCB);

            HBox mikorBox = new HBox(10);
            mikorBox.setAlignment(Pos.CENTER_RIGHT);
            Text mikorText = new Text("Mikor indul? ");
            mikorText.getStyleClass().add("leftText");
            DatePicker mikorDP = new DatePicker();
            mikorDP.setPrefSize(200,30);
            mikorDP.getStyleClass().add("leftSideBars");
            mikorBox.getChildren().addAll(mikorText,mikorDP);

            Button keresesButton = new Button("Keresés");
            keresesButton.getStyleClass().add("leftButton");
            keresesButton.setOnAction(event -> {
                if (honnanCB.getValue() != null && hovaCB.getValue() != null ){
                    if (honnanCB.getValue().toString().equals(hovaCB.getValue().toString())){
                        Utils.showWarning("Egyező az indulási és érkezési helyszín");
                        return;
                    }
                }
                if (mikorDP.getValue() == null){
                    if(honnanCB.getValue() == null || hovaCB.getValue() == null){
                        jaratTablazatKeszito("SELECT * FROM Jarat");
                    }
                    if(honnanCB.getValue() != null && hovaCB.getValue() == null){
                        jaratTablazatKeszito("SELECT * FROM Jarat WHERE Jarat.honnan = '" + honnanCB.getValue() + "'");
                    }
                    if(honnanCB.getValue() == null && hovaCB.getValue() != null){
                        jaratTablazatKeszito("SELECT * FROM Jarat WHERE Jarat.hova = '" + hovaCB.getValue() + "'");
                    }
                    if(honnanCB.getValue() != null && hovaCB.getValue() != null){
                        jaratTablazatKeszito("SELECT * FROM Jarat WHERE Jarat.honnan = '" + honnanCB.getValue() + "' AND Jarat.hova = '"+ hovaCB.getValue() + "'");
                    }
                }
                else{
                    if(honnanCB.getValue() == null || hovaCB.getValue() == null){
                        jaratTablazatKeszito("SELECT * FROM Jarat WHERE Jarat.indulas > ' "+ (mikorDP.getValue().getYear()-2000) + "-" +datumalakito(mikorDP.getValue().getMonthValue()) + "-" +mikorDP.getValue().getDayOfMonth() +"'");
                    }
                    if(honnanCB.getValue() != null && hovaCB.getValue() == null){
                        jaratTablazatKeszito("SELECT * FROM Jarat WHERE Jarat.honnan = '" + honnanCB.getValue() + "' AND Jarat.indulas > ' "+ (mikorDP.getValue().getYear()-2000) + "-" +datumalakito(mikorDP.getValue().getMonthValue()) + "-" +mikorDP.getValue().getDayOfMonth() +"'");
                    }
                    if(honnanCB.getValue() == null && hovaCB.getValue() != null){
                        jaratTablazatKeszito("SELECT * FROM Jarat WHERE Jarat.hova = '" + hovaCB.getValue() + "' AND Jarat.indulas > ' "+ (mikorDP.getValue().getYear()-2000) + "-" +datumalakito(mikorDP.getValue().getMonthValue()) + "-" +mikorDP.getValue().getDayOfMonth() +"'");
                    }
                    if(honnanCB.getValue() != null && hovaCB.getValue() != null){
                        jaratTablazatKeszito("SELECT * FROM Jarat WHERE Jarat.honnan = '" + honnanCB.getValue() + "' AND Jarat.hova = '"+ hovaCB.getValue() + "' AND Jarat.indulas > ' "+ (mikorDP.getValue().getYear()-2000) + "-" +datumalakito(mikorDP.getValue().getMonthValue()) + "-" +mikorDP.getValue().getDayOfMonth() +"'");
                    }
                }
                mainframe.setCenter(jaratTable);

            });

            Button hozzaadasButton = new Button("Új hozzáadása");
            hozzaadasButton.getStyleClass().add("leftButton");
            hozzaadasButton.setOnAction(event -> {
                new JaratModositKeszit(jaratController);
            });


            searchBox.getChildren().addAll(honnanBox,hovaBox,mikorBox,keresesButton,hozzaadasButton);
            mainframe.setLeft(searchBox);
        });

        Button biztositasButton = new Button("Biztosítások");
        biztositasButton.setPrefSize(200,50);
        biztositasButton.getStyleClass().add("topNavButton");
        biztositasButton.setOnAction(e->{
            mainframe.setCenter(new VBox());
            List<Biztositas> biztositasList = biztositasController.getAll();
            Set<String> cegSet = new HashSet<>();
            Set<String> tipusSet = new HashSet<>();

            VBox searchBox = new VBox(50);
            searchBox.setPrefSize(300,650);
            searchBox.setAlignment(Pos.TOP_CENTER);
            searchBox.setPadding(new Insets(20,20,20,20));

            HBox cegBox = new HBox(10);
            cegBox.setAlignment(Pos.CENTER_RIGHT);
            Text cegText = new Text("Cég neve:");
            cegText.getStyleClass().add("leftText");
            ChoiceBox cegCB = new ChoiceBox ();
            cegCB.setPrefSize(200,30);
            for (Biztositas b : biztositasList){
                cegSet.add(b.getCeg());
            }
            List<String> cegList = new ArrayList<>(cegSet);
            Collections.sort(cegList);
            cegCB.getItems().addAll(cegList.toArray());
            cegCB.getStyleClass().add("leftSideBars");
            cegBox.getChildren().addAll(cegText,cegCB);

            HBox tipusBox = new HBox(10);
            tipusBox.setAlignment(Pos.CENTER_RIGHT);
            Text tipusText = new Text("Típus:");
            tipusText.getStyleClass().add("leftText");
            ChoiceBox tipusCB = new ChoiceBox();
            tipusCB.setPrefSize(200,30);
            for (Biztositas b : biztositasList){
                tipusSet.add(b.getTipus());
            }
            List<String> tipusList = new ArrayList<>(tipusSet);
            Collections.sort(tipusList);
            tipusCB.getItems().addAll(tipusList.toArray());
            tipusCB.getStyleClass().add("leftSideBars");
            tipusBox.getChildren().addAll(tipusText,tipusCB);

            HBox osszegBox = new HBox(10);
            osszegBox.setAlignment(Pos.CENTER_RIGHT);
            Text osszegText = new Text("Havidíj:");
            osszegText.getStyleClass().add("leftText");
            TextField osszegTF = new TextField();
            osszegTF.setPrefSize(200,30);
            osszegTF.getStyleClass().add("leftSideBars");
            osszegBox.getChildren().addAll(osszegText,osszegTF);

            Button keresesButton = new Button("Keresés");
            keresesButton.getStyleClass().add("leftButton");
            keresesButton.setOnAction(event->{

                if (!osszegTF.getText().equals("")){
                    int osszeg;
                    try{
                        osszeg = Integer.parseInt(osszegTF.getText());

                    } catch (NumberFormatException ex) {
                        Utils.showWarning("Számokat adjon meg az összeg mezőbe");
                        return;
                    }
                }
                if((cegCB.getValue() == null) && (tipusCB.getValue() == null) && (osszegTF.getText().equals(""))){
                    biztositasTablazatKeszito("SELECT * FROM Biztositas ");
                }
                if((cegCB.getValue() != null) && (tipusCB.getValue() == null) && (osszegTF.getText().equals(""))){
                    biztositasTablazatKeszito("SELECT * FROM Biztositas WHERE Biztositas.ceg ='"+ cegCB.getValue() + "'");
                }
                if((cegCB.getValue() != null) && (tipusCB.getValue() != null) && (osszegTF.getText().equals(""))){
                    biztositasTablazatKeszito("SELECT * FROM Biztositas WHERE Biztositas.tipus = '" +tipusCB.getValue()
                            + "' AND Biztositas.ceg ='"+ cegCB.getValue() +"'");
                }
                if((cegCB.getValue() != null) && (tipusCB.getValue() != null) && !(osszegTF.getText().equals(""))){
                    biztositasTablazatKeszito("SELECT * FROM Biztositas WHERE Biztositas.tipus = '" +tipusCB.getValue()
                            + "' AND Biztositas.ceg ='"+ cegCB.getValue()
                            + "' AND Biztositas.osszeg = " + osszegTF.getText());
                }
                if((cegCB.getValue() != null) && (tipusCB.getValue() == null) && !(osszegTF.getText().equals(""))){
                    biztositasTablazatKeszito("SELECT * FROM Biztositas WHERE Biztositas.ceg ='"+ cegCB.getValue()
                            + "' AND Biztositas.osszeg<= "+ osszegTF.getText());
                }
                if((cegCB.getValue() == null) && !(tipusCB.getValue() == null) && (osszegTF.getText().equals(""))){
                    biztositasTablazatKeszito("SELECT * FROM Biztositas WHERE Biztositas.tipus = '" +tipusCB.getValue()
                            + "' ");
                }
                if((cegCB.getValue() == null) && (tipusCB.getValue() == null) && !(osszegTF.getText().equals(""))){
                    biztositasTablazatKeszito("SELECT * FROM Biztositas WHERE Biztositas.osszeg= "+ osszegTF.getText());
                }
                if((cegCB.getValue() == null) && !(tipusCB.getValue() == null) && !(osszegTF.getText().equals(""))){
                    biztositasTablazatKeszito("SELECT * FROM Biztositas WHERE Biztositas.tipus = '" +tipusCB.getValue()
                            + "' AND Biztositas.osszeg = " + osszegTF.getText());
                }
                mainframe.setCenter(biztositasTable);
            });

            Button hozzaadasButton = new Button("Új hozzáadása");
            hozzaadasButton.getStyleClass().add("leftButton");
            hozzaadasButton.setOnAction(event -> {
                new BiztositasModositKeszit(biztositasController);
            });

            searchBox.getChildren().addAll(cegBox,tipusBox,osszegBox,keresesButton,hozzaadasButton);
            mainframe.setLeft(searchBox);
        });

        Button dolgozokButton = new Button("Dolgozók");
        dolgozokButton.setPrefSize(200,50);
        dolgozokButton.getStyleClass().add("topNavButton");
        dolgozokButton.setOnAction(e -> {
            mainframe.setCenter(new VBox());

            List<Alkalmazott> alkalmazottList = alkalmazottController.getAll();
            Set<String> titulusSet = new HashSet<>();

            VBox searchBox = new VBox(50);
            searchBox.setPrefSize(300,650);
            searchBox.setAlignment(Pos.TOP_CENTER);
            searchBox.setPadding(new Insets(20,20,20,20));

            HBox nevBox = new HBox(10);
            nevBox.setAlignment(Pos.CENTER_RIGHT);
            Text nevText = new Text("Név");
            nevText.getStyleClass().add("leftText");
            TextField nevTF = new TextField();
            nevTF.setPrefSize(200,30);
            nevTF.getStyleClass().add("leftSideBars");
            nevBox.getChildren().addAll(nevText,nevTF);

            HBox titulusBox = new HBox(10);
            titulusBox.setAlignment(Pos.CENTER_RIGHT);
            Text titulusText = new Text("Titulus");
            titulusText.getStyleClass().add("leftText");
            ChoiceBox titulusCB = new ChoiceBox();
            titulusCB.setPrefSize(200,30);
            for (Alkalmazott a : alkalmazottList){
                titulusSet.add(a.getTitulus());
            }
            List<String> tipusList = new ArrayList<>(titulusSet);
            Collections.sort(tipusList);
            titulusCB.getItems().addAll(tipusList.toArray());
            titulusCB.getStyleClass().add("leftSideBars");
            titulusBox.getChildren().addAll(titulusText,titulusCB);

            HBox fizetesBox = new HBox(10);
            fizetesBox.setAlignment(Pos.CENTER_RIGHT);
            Text fizetesText = new Text("Fizetés");
            fizetesText.getStyleClass().add("leftText");
            TextField fizetesTF = new TextField();
            fizetesTF.setPrefSize(200,30);
            fizetesTF.getStyleClass().add("leftSideBars");
            fizetesBox.getChildren().addAll(fizetesText,fizetesTF);

            Button keresesButton = new Button("Keresés");
            keresesButton.getStyleClass().add("leftButton");
            keresesButton.setOnAction(event -> {
                if (!fizetesTF.getText().equals("")){
                    int osszeg;
                    try{
                        osszeg = Integer.parseInt(fizetesTF.getText());

                    } catch (NumberFormatException ex) {
                        Utils.showWarning("Számokat adjon meg az összeg mezőbe");
                        return;
                    }
                }
                if (nevTF.getText().equals("") && titulusCB.getValue() == null && fizetesTF.getText().equals("")){
                    alkalmazottTablaKeszito("SELECT * FROM Alkalmazott");
                }
                if (nevTF.getText().equals("") && titulusCB.getValue() == null && !fizetesTF.getText().equals("")){
                    alkalmazottTablaKeszito("SELECT * FROM Alkalmazott WHERE Alkalmazott.fizetes = " + fizetesTF.getText());
                }
                if (nevTF.getText().equals("") && titulusCB.getValue() != null && fizetesTF.getText().equals("")){
                    alkalmazottTablaKeszito("SELECT * FROM Alkalmazott WHERE Alkalmazott.titulus = '" + titulusCB.getValue().toString() + "'");
                }
                if (nevTF.getText().equals("") && titulusCB.getValue() != null && !fizetesTF.getText().equals("")){
                    alkalmazottTablaKeszito("SELECT * FROM Alkalmazott WHERE Alkalmazott.titulus = '" + titulusCB.getValue().toString() + "' AND Alkalmazott.fizetes = " + fizetesTF.getText());
                }
                if (!nevTF.getText().equals("") && titulusCB.getValue() == null && fizetesTF.getText().equals("")){
                    alkalmazottTablaKeszito("SELECT * FROM Alkalmazott WHERE Alkalmazott.nev LIKE '%" + nevTF.getText() + "%'");
                }
                if (!nevTF.getText().equals("") && titulusCB.getValue() == null && !fizetesTF.getText().equals("")){
                    alkalmazottTablaKeszito("SELECT * FROM Alkalmazott WHERE Alkalmazott.nev LIKE '%" + nevTF.getText() + "%' AND Alkalmazott.fizetes = " + fizetesTF.getText());
                }
                if (!nevTF.getText().equals("") && titulusCB.getValue() != null && fizetesTF.getText().equals("")){
                    alkalmazottTablaKeszito("SELECT * FROM Alkalmazott WHERE Alkalmazott.nev LIKE '%" + nevTF.getText() + "%' AND Alkalmazott.titulus = '" + titulusCB.getValue().toString() + "'");
                }
                if (!nevTF.getText().equals("") && titulusCB.getValue() != null && !fizetesTF.getText().equals("")){
                    alkalmazottTablaKeszito("SELECT * FROM Alkalmazott WHERE Alkalmazott.nev LIKE '%" + nevTF.getText() + "%' AND Alkalmazott.titulus = '" + titulusCB.getValue().toString() + "' AND Alkalmazott.fizetes = " + fizetesTF.getText());
                }
                mainframe.setCenter(alkalmazottTable);
            });

            Button hozzaadasButton = new Button("Új hozzáadása");
            hozzaadasButton.getStyleClass().add("leftButton");
            hozzaadasButton.setOnAction(event -> {
                new AlkalmazottModositKeszit(alkalmazottController);
            });


            searchBox.getChildren().addAll(nevBox,titulusBox,fizetesBox,keresesButton,hozzaadasButton);
            mainframe.setLeft(searchBox);
        });

        Button statisztikaButton = new Button("Statisztikák");
        statisztikaButton.setPrefSize(200,50);
        statisztikaButton.getStyleClass().add("topNavButton");
        statisztikaButton.setOnAction(event -> {
            mainframe.setCenter(new VBox());
            VBox searchBox = new VBox(50);
            searchBox.setPrefSize(300,650);
            searchBox.setAlignment(Pos.TOP_CENTER);
            searchBox.setPadding(new Insets(20,20,20,20));

            Button felhasznaloStatButton = new Button("Felhasználók statisztikái");
            felhasznaloStatButton.getStyleClass().add("topNavButton");
            felhasznaloStatButton.setOnAction(event1 -> {
                List<Felhasznalo> felhasznaloList = felhasznaloController.getAll();
                Set<String> felhasznaloSet = new HashSet<>();

                VBox totalBox = new VBox(30);

                VBox centerVBox = new VBox(20);
                centerVBox.setPadding(new Insets(20,20,20,20));
                centerVBox.setAlignment(Pos.TOP_CENTER);

                HBox firstPart = new HBox(20);
                firstPart.setAlignment(Pos.CENTER);
                ChoiceBox fhCB = new ChoiceBox();
                fhCB.setPrefSize(200,30);
                for (Felhasznalo a : felhasznaloList){
                    felhasznaloSet.add(a.getFelhasznalonev());
                }
                List<String> fhList = new ArrayList<>(felhasznaloSet);
                Collections.sort(fhList);
                fhCB.getItems().addAll(fhList.toArray());
                fhCB.setValue(felhasznalo.getFelhasznalonev());
                fhCB.getStyleClass().add("userCB");

                Button fhstatListazButton = new Button("Listázz");
                fhstatListazButton.getStyleClass().add("listazzButton");
                firstPart.getChildren().addAll(fhCB,fhstatListazButton);
                fhstatListazButton.setOnAction(event2 -> {
                    centerVBox.getChildren().clear();

                    HBox ossfoglalasBox = new HBox(20);
                    ossfoglalasBox.setAlignment(Pos.CENTER);
                    Text osszfoglalasText = new Text("Összes foglalás száma: ");
                    osszfoglalasText.getStyleClass().add("centerText");
                    Text osszfoglalasResult = new Text();
                    osszfoglalasResult.getStyleClass().add("centerText");
                    osszfoglalasResult.setText(Integer.toString(foglalController.osszfoglalasSzamito(fhCB.getValue().toString())));
                    ossfoglalasBox.getChildren().addAll(osszfoglalasText,osszfoglalasResult);

                    HBox ossbiztositasBox = new HBox(20);
                    ossbiztositasBox.setAlignment(Pos.CENTER);
                    Text osszbiztositasText = new Text("Összes biztosítás száma: ");
                    osszbiztositasText.getStyleClass().add("centerText");
                    Text ossbiztositasResult = new Text();
                    ossbiztositasResult.setText(Integer.toString(biztositController.osszbiztositasSzamlalo(fhCB.getValue().toString())));
                    ossbiztositasResult.getStyleClass().add("centerText");
                    ossbiztositasBox.getChildren().addAll(osszbiztositasText,ossbiztositasResult);

                    HBox legdragabbRepjegyBox = new HBox(20);
                    legdragabbRepjegyBox.setAlignment(Pos.CENTER);
                    Text legdragabbRepjegyText = new Text("Legdrágábban megvett repjegy: ");
                    legdragabbRepjegyText.getStyleClass().add("centerText");
                    Text legdragabbRepjegyResult = new Text();
                    legdragabbRepjegyResult.setText(Integer.toString(foglalController.legdragabbRepjegySzamito(fhCB.getValue().toString())) + " Forint");
                    legdragabbRepjegyResult.getStyleClass().add("centerText");
                    legdragabbRepjegyBox.getChildren().addAll(legdragabbRepjegyText,legdragabbRepjegyResult);

                    HBox legdragabbBiztositasBox = new HBox(20);
                    legdragabbBiztositasBox.setAlignment(Pos.CENTER);
                    Text legdragabbBiztositasText = new Text("Legdrágább biztositas: ");
                    legdragabbBiztositasText.getStyleClass().add("centerText");
                    Text legdragabbBiztositasResult = new Text();
                    legdragabbBiztositasResult.setText(Integer.toString(biztositController.legdragabbBiztositasSzamlalo(fhCB.getValue().toString())) + " Forint");
                    legdragabbBiztositasResult.getStyleClass().add("centerText");
                    legdragabbBiztositasBox.getChildren().addAll(legdragabbBiztositasText,legdragabbBiztositasResult);

                    HBox legdragabbBiztositasCegBox = new HBox(20);
                    legdragabbBiztositasCegBox.setAlignment(Pos.CENTER);
                    Text legdragabbBiztositasCegText = new Text("Cég, ahol kötve lett a legdrágább biztositás: ");
                    legdragabbBiztositasCegText.getStyleClass().add("centerText");
                    Text legdragabbBiztositasCegResult = new Text();
                    legdragabbBiztositasCegResult.setText(biztositController.legdragabbBiztositasByCeg(fhCB.getValue().toString()));
                    legdragabbBiztositasCegResult.getStyleClass().add("centerText");
                    legdragabbBiztositasCegBox.getChildren().addAll(legdragabbBiztositasCegText,legdragabbBiztositasCegResult);

                    centerVBox.getChildren().addAll(ossfoglalasBox,ossbiztositasBox,legdragabbRepjegyBox,legdragabbBiztositasBox,legdragabbBiztositasCegBox);
                });

                totalBox.getChildren().addAll(firstPart,centerVBox);
                mainframe.setCenter(totalBox);

            });

            Button jaratStatButton = new Button("Járat statisztikái");
            jaratStatButton.getStyleClass().add("topNavButton");
            jaratStatButton.setOnAction(event1 -> {
                VBox centerVBox = new VBox(30);
                centerVBox.setAlignment(Pos.TOP_CENTER);

                HBox legforgalmasabbRepterBox = new HBox(20);
                legforgalmasabbRepterBox.setAlignment(Pos.CENTER);
                Text legforgalmasabbRepterText = new Text("A legforgalmasabb Reptér:");
                legforgalmasabbRepterText.getStyleClass().add("centerText");
                Text legforgalmasabbRepterResult = new Text(jaratController.legforgalmasabbRepterKimutato());
                legforgalmasabbRepterResult.getStyleClass().add("centerText");
                legforgalmasabbRepterBox.getChildren().addAll(legforgalmasabbRepterText,legforgalmasabbRepterResult);

                HBox legdragabbRepjegyBox = new HBox(20);
                legdragabbRepjegyBox.setAlignment(Pos.CENTER);
                Text legdragabbRepjegyText = new Text("A legdrágább Repülőjegy:");
                legdragabbRepjegyText.getStyleClass().add("centerText");
                Text legdragabbRepjegyResult = new Text(String.valueOf(jaratController.legdragabbRepjegyKimutato()) + " Forint");
                legdragabbRepjegyResult.getStyleClass().add("centerText");
                legdragabbRepjegyBox.getChildren().addAll(legdragabbRepjegyText,legdragabbRepjegyResult);

                HBox maxUtasBox = new HBox(20);
                maxUtasBox.setAlignment(Pos.CENTER);
                Text maxUtasText = new Text("A legtöbb hellyel rendelkező járat:");
                maxUtasText.getStyleClass().add("centerText");
                Text maxUtasResult = new Text(String.valueOf(jaratController.maxutasKimutato())+ " fő");
                maxUtasResult.getStyleClass().add("centerText");
                maxUtasBox.getChildren().addAll(maxUtasText,maxUtasResult);

                HBox legtobbFoglalasBox = new HBox(20);
                legtobbFoglalasBox.setAlignment(Pos.CENTER);
                Text legtobbFoglalasText = new Text("A legtöbb foglalás egy járaton: ");
                legtobbFoglalasText.getStyleClass().add("centerText");
                Text legtobbFoglalasResult = new Text(String.valueOf(foglalController.legtobbfoglalasEgyRepulon().get(0)) +" darab a(z) " + String.valueOf(foglalController.legtobbfoglalasEgyRepulon().get(1)) + " járaton");
                legtobbFoglalasResult.getStyleClass().add("centerText");
                legtobbFoglalasBox.getChildren().addAll(legtobbFoglalasText,legtobbFoglalasResult);

                HBox legnagyobbAtlagFizuBox = new HBox(20);
                legnagyobbAtlagFizuBox.setAlignment(Pos.CENTER);
                Text legnagyobbAtlagFizuText = new Text("A legnagyobb átlagfizetés egy járaton:  ");
                legnagyobbAtlagFizuText.getStyleClass().add("centerText");
                Text legnagyobbAtlagFizuResult = new Text(String.valueOf(jaratController.legnagyobbAtlagFizetes()) + " Forint");
                legnagyobbAtlagFizuResult.getStyleClass().add("centerText");
                legnagyobbAtlagFizuBox.getChildren().addAll(legnagyobbAtlagFizuText,legnagyobbAtlagFizuResult);

                centerVBox.getChildren().addAll(legforgalmasabbRepterBox,legdragabbRepjegyBox,maxUtasBox, legtobbFoglalasBox, legnagyobbAtlagFizuBox);
                mainframe.setCenter(centerVBox);
            });

            searchBox.getChildren().addAll(felhasznaloStatButton,jaratStatButton);
            mainframe.setLeft(searchBox);
        });


        Button kijelentkezesButton = new Button("Kijelentkezés");
        kijelentkezesButton.setPrefSize(200,50);
        kijelentkezesButton.getStyleClass().add("topNavButton");
        kijelentkezesButton.setOnAction(e->{
            new Bejelentkezes(new FelhasznaloController(), this.jaratController,this.biztositasController,this.foglalController,this.biztositController, this.alkalmazottController);
            close();
        });

        topNav.getChildren().addAll(logoView,jaratButton,biztositasButton,dolgozokButton,statisztikaButton,kijelentkezesButton);


        HBox botFiller = new HBox();
        botFiller.setPrefSize(1400,50);
        botFiller.getStyleClass().add("botFiller");

        HBox rightFiller = new HBox();
        rightFiller.setMaxSize(50,700);
        rightFiller.getStyleClass().add("leftFiller");

        mainframe.setRight(rightFiller);
        mainframe.setBottom(botFiller);
        mainframe.setTop(topNav);

        Scene scene = new Scene(mainframe,1400,900);
        scene.getStylesheets().add("hu/adatb/utils/css/fooldalStyle.css");
        this.setScene(scene);
        this.setResizable(false);
        this.setTitle("Repjegyfoglalás");
        this.getIcons().add(new Image("hu/adatb/utils/images/icon.png"));
        this.show();
    }

    private void jaratTablazatKeszito(String query) {

        jaratTable = new TableView<>();
        jaratTable.setMaxSize(900,600);

        jaratTable.setEditable(false);

        TableColumn<Jarat, Integer> jaratszamCol = new TableColumn<>("Járatszám");
        jaratszamCol.setPrefWidth(100);
        jaratszamCol.setCellValueFactory(new PropertyValueFactory<Jarat, Integer>("jaratszam"));

        TableColumn<Jarat, Integer> max_utasCol = new TableColumn<>("Maradék hely");
        max_utasCol.setPrefWidth(100);
        max_utasCol.setCellValueFactory(new PropertyValueFactory<Jarat, Integer>("max_utas"));

        TableColumn<Jarat, Integer> arCol = new TableColumn<>("Ár");
        arCol.setPrefWidth(100);
        arCol.setCellValueFactory(new PropertyValueFactory<Jarat, Integer>("ar"));

        TableColumn<Jarat, String> statuszCol = new TableColumn<>("Státusz");
        statuszCol.setPrefWidth(100);
        statuszCol.setCellValueFactory(new PropertyValueFactory<Jarat,String>("statusz"));

        TableColumn<Jarat, String> honnanCol = new TableColumn<>("Indulási helyszín");
        honnanCol.setPrefWidth(100);
        honnanCol.setCellValueFactory(new PropertyValueFactory<Jarat,String>("honnan"));

        TableColumn<Jarat, String> hovaCol = new TableColumn<>("Érkezési helyszín");
        hovaCol.setPrefWidth(100);
        hovaCol.setCellValueFactory(new PropertyValueFactory<Jarat,String>("hova"));

        TableColumn<Jarat, Date> indulasCol = new TableColumn<>("Indulási időpont");
        indulasCol.setPrefWidth(100);
        indulasCol.setCellValueFactory(new PropertyValueFactory<Jarat,Date>("indulas"));

        TableColumn<Jarat, Date> erkezesCol = new TableColumn<>("Érkezési időpont");
        erkezesCol.setPrefWidth(100);
        erkezesCol.setCellValueFactory(new PropertyValueFactory<Jarat,Date>("erkezes"));

        TableColumn<Jarat, Void> modisitCol = new TableColumn<>("Módosítás");
        modisitCol.setPrefWidth(100);
        modisitCol.setCellFactory(param->{
            return new TableCell<>(){
                private final Button modositButton = new Button("Módosít");

                {
                    modositButton.setOnAction(event ->{
                        Jarat foglal = getTableView().getItems().get(getIndex());
                        new JaratModositKeszit(jaratController,foglal);
                        modositButton.setDisable(true);
                    });

                }
                @Override
                protected void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        HBox container = new HBox();
                        container.getChildren().addAll(modositButton);
                        setGraphic(container);
                    }
                }
            };
        });

        jaratTable.getColumns().addAll(jaratszamCol, max_utasCol, arCol, statuszCol, honnanCol, hovaCol, indulasCol, erkezesCol,modisitCol);
        jaratTable.setItems(FXCollections.observableArrayList(jaratController.selectSpecific(query)));
    }

    private void biztositasTablazatKeszito(String query){
        biztositasTable = new TableView<>();

        biztositasTable.setMaxSize(900,600);

        biztositasTable.setEditable(false);

        TableColumn<Biztositas, Integer> biztositasidCol = new TableColumn<>("Biztosítás ID");
        biztositasidCol.setPrefWidth(900/6);
        biztositasidCol.setCellValueFactory(new PropertyValueFactory<Biztositas, Integer>("biztositasid"));

        TableColumn<Biztositas, String> tipusCol = new TableColumn<>("Típus");
        tipusCol.setPrefWidth(900/6);
        tipusCol.setCellValueFactory(new PropertyValueFactory<Biztositas, String>("tipus"));

        TableColumn<Biztositas, String> cegCol = new TableColumn<>("Biztosító");
        cegCol.setPrefWidth(900/6);
        cegCol.setCellValueFactory(new PropertyValueFactory<Biztositas, String>("ceg"));

        TableColumn<Biztositas, Integer> osszegCol = new TableColumn<>("Összeg");
        osszegCol.setPrefWidth(900/6);
        osszegCol.setCellValueFactory(new PropertyValueFactory<Biztositas,Integer>("osszeg"));

        TableColumn<Biztositas, Integer> idotartamCol = new TableColumn<>("Időtartam");
        idotartamCol.setPrefWidth(900/6);
        idotartamCol.setCellValueFactory(new PropertyValueFactory<Biztositas,Integer>("idotartam"));

        TableColumn<Biztositas,Void> modositasCol = new TableColumn<>("");
        modositasCol.setPrefWidth(900/6);
        modositasCol.setCellFactory(param -> {
            return new TableCell<>(){
                private final Button modositasButton = new Button("Módosítás");

                {
                    modositasButton.setOnAction(event ->{
                        Biztositas megkot = getTableView().getItems().get(getIndex());
                        new BiztositasModositKeszit(biztositasController,megkot);
                        modositasButton.setDisable(true);
                    });

                }
                @Override
                protected void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        HBox container = new HBox();
                        container.getChildren().addAll(modositasButton);
                        setGraphic(container);
                    }
                }
            };
        });

        biztositasTable.getColumns().addAll(biztositasidCol, tipusCol, cegCol, osszegCol, idotartamCol,modositasCol);
        biztositasTable.setItems(FXCollections.observableArrayList(biztositasController.selectSpecific(query)));
    }

    private void alkalmazottTablaKeszito(String query){
        alkalmazottTable = new TableView<>();

        alkalmazottTable.setMaxSize(900,600);

        alkalmazottTable.setEditable(false);

        TableColumn<Alkalmazott, Integer> dolgozoidCol = new TableColumn<>("Dolgozó ID");
        dolgozoidCol.setPrefWidth(900/5);
        dolgozoidCol.setCellValueFactory(new PropertyValueFactory<Alkalmazott, Integer>("dolgozoid"));

        TableColumn<Alkalmazott, String> nevCol = new TableColumn<>("Név");
        nevCol.setPrefWidth(900/5);
        nevCol.setCellValueFactory(new PropertyValueFactory<Alkalmazott, String>("nev"));

        TableColumn<Alkalmazott, String> titulusCol = new TableColumn<>("Titulus");
        titulusCol.setPrefWidth(900/5);
        titulusCol.setCellValueFactory(new PropertyValueFactory<Alkalmazott, String>("titulus"));

        TableColumn<Alkalmazott, Integer> fizetesCol = new TableColumn<>("Fizetés");
        fizetesCol.setPrefWidth(900/5);
        fizetesCol.setCellValueFactory(new PropertyValueFactory<Alkalmazott,Integer>("fizetes"));

        TableColumn<Alkalmazott,Void> modositasCol = new TableColumn<>("");
        modositasCol.setPrefWidth(900/5);
        modositasCol.setCellFactory(param -> {
            return new TableCell<>(){
                private final Button modositasButton = new Button("Módosítás");

                {
                    modositasButton.setOnAction(event ->{
                        Alkalmazott modosit = getTableView().getItems().get(getIndex());
                        new AlkalmazottModositKeszit(alkalmazottController,modosit);
                        modositasButton.setDisable(true);
                    });

                }
                @Override
                protected void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        HBox container = new HBox();
                        container.getChildren().addAll(modositasButton);
                        setGraphic(container);
                    }
                }
            };
        });

        alkalmazottTable.getColumns().addAll(dolgozoidCol, nevCol, titulusCol, fizetesCol ,modositasCol);
        alkalmazottTable.setItems(FXCollections.observableArrayList(alkalmazottController.selectSpecific(query)));
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
}



