package hu.adatb.view;

import hu.adatb.controller.*;
import hu.adatb.model.*;
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

public class FooldalUser extends Stage {

    Felhasznalo felhasznalo;
    JaratController jaratController;
    BiztositasController biztositasController;
    FoglalController foglalController;
    BiztositController biztositController;
    SajatFoglalasController sajatFoglalasController = new SajatFoglalasController();
    private TableView<Jarat> jaratTable;
    private TableView<Biztositas> biztositasTable;
    private TableView<SajatFoglalas> sajatFoglalasTable;

    private int foglalthelyek;

    public FooldalUser(Felhasznalo f,JaratController j, BiztositasController b, FoglalController fo, BiztositController bc) {
        this.felhasznalo = f;
        this.jaratController = j;
        this.biztositasController = b;
        this.foglalController = fo;
        this.biztositController = bc;
        contruct();
    }

    private void contruct() {

        BorderPane mainframe = new BorderPane();
        mainframe.setPrefSize(1400,900);
        mainframe.getStyleClass().add("mainframe");

        HBox topNav = new HBox(70);
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

            VBox searchBox = new VBox(30);
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
            Text hovaText = new Text("Hova szeretne eljutni? ");
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
            Text mikorText = new Text("Mikor szeretne indulni? ");
            mikorText.getStyleClass().add("leftText");
            DatePicker mikorDP = new DatePicker();
            mikorDP.setPrefSize(200,30);
            mikorDP.getStyleClass().add("leftSideBars");
            mikorBox.getChildren().addAll(mikorText,mikorDP);

            HBox meddigBox = new HBox(10);
            meddigBox.setAlignment(Pos.CENTER_RIGHT);
            Text meddigText = new Text("Meddig szeretne maradni? ");
            meddigText.getStyleClass().add("leftText");
            DatePicker meddigDP = new DatePicker();
            meddigDP.setPrefSize(200,30);
            meddigDP.getStyleClass().add("leftSideBars");
            meddigBox.getChildren().addAll(meddigText,meddigDP);

            VBox utasVBox = new VBox(10);
            HBox gyerekBox = new HBox(10);
            gyerekBox.setAlignment(Pos.CENTER_RIGHT);
            HBox felnottBox = new HBox(10);
            felnottBox.setAlignment(Pos.CENTER_RIGHT);
            Text gyerekText = new Text("Hány gyerek fog utazni?");
            gyerekText.getStyleClass().add("leftText");
            Spinner<Integer> gyerekS = new Spinner<>(0,20,0,1);
            gyerekS.setEditable(true);
            Text felnottText = new Text("Hány felnőtt fog utazni?");
            felnottText.getStyleClass().add("leftText");
            Spinner<Integer> felnottS = new Spinner<>(0,20,0,1);
            felnottS.setEditable(true);
            gyerekBox.getChildren().addAll(gyerekText,gyerekS);
            felnottBox.getChildren().addAll(felnottText,felnottS);
            utasVBox.getChildren().addAll(gyerekBox,felnottBox);

            ChoiceBox odavisszaCB = new ChoiceBox();
            odavisszaCB.getItems().addAll("Odaút", "Visszaút");
            odavisszaCB.setValue("Odaút");
            odavisszaCB.getStyleClass().add("leftSideBars");

            Button keresesButton = new Button("Keresés");
            keresesButton.getStyleClass().add("leftButton");
            keresesButton.setOnAction(event -> {
                foglalthelyek = gyerekS.getValue() + felnottS.getValue();
                if(honnanCB.getValue() == null || hovaCB.getValue() == null){
                    Utils.showWarning("Az úticélok megadása kötelező");
                    return;
                }
                if (mikorDP.getValue() == null && !(meddigDP.getValue() == null)){
                    Utils.showWarning("Törölje ki a meddig időpontját");
                    return;
                }
                if (felnottS.getValue() == 0){
                    Utils.showWarning("Felnőttnek kötelező utazni!");
                    return;
                }
                if (honnanCB.getValue().toString().equals(hovaCB.getValue().toString())){
                    Utils.showWarning("Egyező az indulási és érkezési helyszín");
                    return;
                }
                if( (honnanCB.getValue() != null) && (hovaCB.getValue() != null) && (mikorDP.getValue() == null) && (meddigDP.getValue() == null)){
                    if(odavisszaCB.getValue().equals("Odaút")) {
                        List<Jarat> odautList = jaratController.selectSpecific("SELECT *  FROM Jarat WHERE honnan = '" + honnanCB.getValue() + "' AND hova = '" + hovaCB.getValue() + "' AND jarat.indulas > CURRENT_DATE");
                        if (odautList != null){
                            jaratTablazatKeszito("SELECT * FROM Jarat WHERE Jarat.honnan = '" + honnanCB.getValue() + "' AND Jarat.hova = '" + hovaCB.getValue() + "' AND Jarat.indulas > CURRENT_DATE");
                        }
                        else{
                            Utils.showWarning("Nem megy oda járat a 2021-ig");
                        }
                    }
                    else{
                        List<Jarat> visszautList = jaratController.selectSpecific("SELECT * FROM Jarat WHERE honnan = '" + hovaCB.getValue() + "' AND hova = '" + honnanCB.getValue() + "' AND jarat.indulas > CURRENT_DATE");
                        if (visszautList != null){
                            jaratTablazatKeszito("SELECT * FROM Jarat WHERE honnan = '" + hovaCB.getValue() + "' AND hova = '" + honnanCB.getValue() + "' AND jarat.indulas > CURRENT_DATE");
                        }
                        else{
                            Utils.showWarning("Nem megy vissza járat a 2021-ig");
                        }
                    }
                }
                if( (honnanCB.getValue() != null) && (hovaCB.getValue() != null) && (mikorDP.getValue() != null) && (meddigDP.getValue() == null)) {
                    if(odavisszaCB.getValue().equals("Odaút")) {

                        List<Jarat> odautList = jaratController.selectSpecific("SELECT * FROM Jarat WHERE Jarat.honnan = '" + honnanCB.getValue()
                                + "' AND Jarat.hova = '" + hovaCB.getValue()
                                + "' AND Jarat.indulas > '"+ (mikorDP.getValue().getYear()-2000) + "-" +datumalakito(mikorDP.getValue().getMonthValue()) + "-" +mikorDP.getValue().getDayOfMonth() +"'");
                        if (odautList != null){
                            jaratTablazatKeszito("SELECT * FROM Jarat WHERE Jarat.honnan = '" + honnanCB.getValue()
                                    + "' AND Jarat.hova = '" + hovaCB.getValue()
                                    + "' AND Jarat.indulas > '"+ (mikorDP.getValue().getYear()-2000) + "-" +datumalakito(mikorDP.getValue().getMonthValue()) + "-" +mikorDP.getValue().getDayOfMonth() +"'");
                        }
                        else{
                            Utils.showWarning("Nem megy oda járat a 2021-ig");
                        }
                    }
                    else{
                        Utils.showWarning("Adja meg, hogy meddig marad!");
                    }
                }
                if( (honnanCB.getValue() != null) && (hovaCB.getValue() != null) && (mikorDP.getValue() != null) && (meddigDP.getValue() != null)) {
                    if(odavisszaCB.getValue().equals("Odaút")) {
                        List<Jarat> odautList = jaratController.selectSpecific("SELECT * FROM Jarat WHERE Jarat.honnan = '" + honnanCB.getValue()
                                + "' AND Jarat.hova = '" + hovaCB.getValue()
                                + "' AND Jarat.indulas > '"+ (mikorDP.getValue().getYear()-2000) + "-" +datumalakito(mikorDP.getValue().getMonthValue()) + "-" +mikorDP.getValue().getDayOfMonth() +"'");
                        if (odautList != null){
                            jaratTablazatKeszito("SELECT * FROM Jarat WHERE Jarat.honnan = '" + honnanCB.getValue()
                                    + "' AND Jarat.hova = '" + hovaCB.getValue()
                                    + "' AND Jarat.indulas > '"+ (mikorDP.getValue().getYear()-2000) + "-" +datumalakito(mikorDP.getValue().getMonthValue()) + "-" +mikorDP.getValue().getDayOfMonth() +"'");
                        }
                    }
                    else{
                        List<Jarat> visszautList = jaratController.selectSpecific("SELECT * FROM Jarat WHERE Jarat.honnan = '" + hovaCB.getValue()
                                + "' AND Jarat.hova = '" + honnanCB.getValue()
                                + "' AND Jarat.indulas > '"+ (meddigDP.getValue().getYear()-2000) + "-" +datumalakito(meddigDP.getValue().getMonthValue()) + "-" +meddigDP.getValue().getDayOfMonth() +"'");
                        if (visszautList != null){
                            jaratTablazatKeszito("SELECT * FROM Jarat WHERE Jarat.honnan = '" + hovaCB.getValue()
                                    + "' AND Jarat.hova = '" + honnanCB.getValue()
                                    + "' AND Jarat.indulas > '"+ (meddigDP.getValue().getYear()-2000) + "-" +datumalakito(meddigDP.getValue().getMonthValue()) + "-" +meddigDP.getValue().getDayOfMonth() +"'");
                        }
                        else{
                            Utils.showWarning("Nem megy vissza járat a 2021-ig");
                        }
                    }
                }
                mainframe.setCenter(jaratTable);
            });

            Text hideText = new Text();

            Button sajatFoglalasokButton = new Button("Saját foglalásaim");
            sajatFoglalasokButton.getStyleClass().add("leftButton");
            sajatFoglalasokButton.setOnAction(event1 -> {
                sajatFoglalasTablaKeszito();
                mainframe.setCenter(sajatFoglalasTable);
            });
            
            searchBox.getChildren().addAll(honnanBox,hovaBox,mikorBox,meddigBox,utasVBox,odavisszaCB,keresesButton,hideText,sajatFoglalasokButton);
            mainframe.setLeft(searchBox);

        });

        Button biztositasButton = new Button("Biztosítások");
        biztositasButton.setPrefSize(200,50);
        biztositasButton.getStyleClass().add("topNavButton");
        biztositasButton.setOnAction(e->{
            List<Biztositas> biztositasList = biztositasController.getAll();
            Set<String> cegSet = new HashSet<>();
            Set<String> tipusSet = new HashSet<>();

            VBox searchBox = new VBox(50);
            searchBox.setPrefSize(300,650);
            searchBox.setAlignment(Pos.TOP_CENTER);
            searchBox.setPadding(new Insets(20,20,20,20));

            HBox cegBox = new HBox(10);
            cegBox.setAlignment(Pos.CENTER_RIGHT);
            Text cegText = new Text("Melyik cégtől szeretne?");
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
            Text tipusText = new Text("Milyen típusút szeretne?");
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

            VBox osszegBox = new VBox(10);
            HBox minosszegBox = new HBox(10);
            HBox maxosszegBox = new HBox(10);
            osszegBox.setAlignment(Pos.CENTER_RIGHT);

            Text minOsszegText = new Text("Minimum havidíj");
            minOsszegText.getStyleClass().add("leftText");
            TextField minOsszegTF = new TextField();
            minOsszegTF.setPrefSize(200,30);
            minOsszegTF.getStyleClass().add("leftSideBars");
            minosszegBox.getChildren().addAll(minOsszegText,minOsszegTF);

            Text maxOsszegText = new Text("Maximum havidíj");
            maxOsszegText.getStyleClass().add("leftText");
            TextField maxOsszegTF = new TextField();
            maxOsszegTF.setPrefSize(200,30);
            maxOsszegTF.getStyleClass().add("leftSideBars");
            maxosszegBox.getChildren().addAll(maxOsszegText,maxOsszegTF);
            osszegBox.getChildren().addAll(minosszegBox,maxosszegBox);

            HBox idotartamBox = new HBox(10);
            Text idotartamText = new Text("Mennyi időre szeretné?");
            idotartamText.getStyleClass().add("leftText");
            Spinner<Integer> idotartamS = new Spinner<>(1,60,1,1);
            idotartamS.setEditable(true);
            idotartamBox.getChildren().addAll(idotartamText,idotartamS);

            Button keresesButton = new Button("Keresés");
            keresesButton.getStyleClass().add("leftButton");
            keresesButton.setOnAction(event->{
                if(tipusCB.getValue() == null){
                    Utils.showWarning("A biztosítás típusa megadás kötelező");
                    return;
                }
                if (!minOsszegTF.getText().equals("") && !maxOsszegTF.getText().equals("")){
                    int minosszeg, maxosszeg;
                    try{
                        minosszeg = Integer.parseInt(minOsszegTF.getText());
                        maxosszeg = Integer.parseInt(maxOsszegTF.getText());
                    } catch (NumberFormatException ex) {
                        Utils.showWarning("Számokat adjon meg a mezőbe");
                        return;
                    }
                    if(minosszeg > maxosszeg ){
                        Utils.showWarning("A minimális összeg nagyobb, mint a maximális");
                        return;
                    }
                }
                if((cegCB.getValue() == null) && (minOsszegTF.getText().equals("")) && (maxOsszegTF.getText().equals(""))){
                    System.out.println(1);
                    biztositasTablazatKeszito("SELECT * FROM Biztositas WHERE Biztositas.tipus = '" +tipusCB.getValue() + "'");
                }
                if((cegCB.getValue() != null) && (minOsszegTF.getText().equals("")) && (maxOsszegTF.getText().equals(""))){
                    biztositasTablazatKeszito("SELECT * FROM Biztositas WHERE Biztositas.tipus = '" +tipusCB.getValue()
                                                                            + "' AND Biztositas.ceg ='"+ cegCB.getValue() + "'");
                }
                if((cegCB.getValue() != null) && !(minOsszegTF.getText().equals("")) && (maxOsszegTF.getText().equals(""))){
                    biztositasTablazatKeszito("SELECT * FROM Biztositas WHERE Biztositas.tipus = '" +tipusCB.getValue()
                                                                            + "' AND Biztositas.ceg ='"+ cegCB.getValue()
                                                                            + "' AND Biztositas.osszeg>= "+ minOsszegTF.getText());
                }
                if((cegCB.getValue() != null) && !(minOsszegTF.getText().equals("")) && !(maxOsszegTF.getText().equals(""))){
                    biztositasTablazatKeszito("SELECT * FROM Biztositas WHERE Biztositas.tipus = '" +tipusCB.getValue()
                                                                            + "' AND Biztositas.ceg ='"+ cegCB.getValue()
                                                                            + "' AND Biztositas.osszeg BETWEEN "+ minOsszegTF.getText() + " AND " +maxOsszegTF.getText());
                }
                if((cegCB.getValue() != null) && (minOsszegTF.getText().equals("")) && !(maxOsszegTF.getText().equals(""))){
                    biztositasTablazatKeszito("SELECT * FROM Biztositas WHERE Biztositas.tipus = '" +tipusCB.getValue()
                                                                            + "' AND Biztositas.ceg ='"+ cegCB.getValue()
                                                                            + "' AND Biztositas.osszeg<= "+ maxOsszegTF.getText());
                }
                if((cegCB.getValue() == null) && !(minOsszegTF.getText().equals("")) && (maxOsszegTF.getText().equals(""))){
                    biztositasTablazatKeszito("SELECT * FROM Biztositas WHERE Biztositas.tipus = '" +tipusCB.getValue()
                                                                            + "' AND Biztositas.osszeg<= "+ minOsszegTF.getText());
                }
                if((cegCB.getValue() == null) && (minOsszegTF.getText().equals("")) && !(maxOsszegTF.getText().equals(""))){
                    biztositasTablazatKeszito("SELECT * FROM Biztositas WHERE Biztositas.tipus = '" +tipusCB.getValue()
                                                                            + "' AND Biztositas.osszeg>= "+ maxOsszegTF.getText());
                }
                if((cegCB.getValue() == null) && !(minOsszegTF.getText().equals("")) && !(maxOsszegTF.getText().equals(""))){
                    biztositasTablazatKeszito("SELECT * FROM Biztositas WHERE Biztositas.tipus = '" +tipusCB.getValue()
                                                                            + "' AND Biztositas.osszeg BETWEEN "+ minOsszegTF.getText() + " AND " +maxOsszegTF.getText());
                }
                mainframe.setCenter(biztositasTable);
            });

            Text hideText = new Text();

            Button sajatBiztositasokButton = new Button("Saját biztosításaim");
            sajatBiztositasokButton.getStyleClass().add("leftButton");
            sajatBiztositasokButton.setOnAction(event1 -> {
                sajatBiztositasTablaKeszito(" SELECT Biztositas.biztositasid, Biztositas.tipus, Biztositas.ceg, Biztositas.osszeg, Biztositas.idotartam FROM Biztosit, Biztositas WHERE Biztosit.felhasznalonev = '" + this.felhasznalo.getFelhasznalonev() +"' AND Biztosit.biztositasid = Biztositas.biztositasid");
                mainframe.setCenter(biztositasTable);
            });

            searchBox.getChildren().addAll(cegBox,tipusBox,osszegBox,idotartamBox,keresesButton,hideText,sajatBiztositasokButton);
            mainframe.setLeft(searchBox);
            mainframe.setCenter(new HBox());
        });

        Button sajatButton = new Button("Saját foglalásaim");
        sajatButton.setPrefSize(200,50);
        sajatButton.getStyleClass().add("topNavButton");
        sajatButton.setOnAction(event -> {
            mainframe.setCenter(new VBox());

            VBox searchBox = new VBox(50);
            searchBox.setPrefSize(300,650);
            searchBox.setAlignment(Pos.TOP_CENTER);
            searchBox.setPadding(new Insets(20,20,20,20));

            Button sajatfoglalasok = new Button("Saját foglalások");
            sajatfoglalasok.getStyleClass().add("topNavButton");
            sajatfoglalasok.setOnAction(event1 -> {
                sajatFoglalasTablaKeszito();
                mainframe.setCenter(sajatFoglalasTable);
            });

            Button sajatBiztositasok = new Button("Saját biztosítások");
            sajatBiztositasok.getStyleClass().add("topNavButton");
            sajatBiztositasok.setOnAction(event1 -> {
                sajatBiztositasTablaKeszito(" SELECT * FROM table(sajatbiztositasok('"+ this.felhasznalo.getFelhasznalonev()+"'))");
                mainframe.setCenter(biztositasTable);
            });

            searchBox.getChildren().addAll(sajatfoglalasok,sajatBiztositasok);

            mainframe.setLeft(searchBox);
        });


        Button kijelentkezesButton = new Button("Kijelentkezés");
        kijelentkezesButton.setPrefSize(200,50);
        kijelentkezesButton.getStyleClass().add("topNavButton");
        kijelentkezesButton.setOnAction(e->{
            new Bejelentkezes(new FelhasznaloController(),new JaratController(),new BiztositasController(),new FoglalController(),new BiztositController(),new AlkalmazottController());
            close();
        });

        topNav.getChildren().addAll(logoView,jaratButton,biztositasButton,sajatButton,kijelentkezesButton);



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

        TableColumn<Jarat, Void> foglalasCol = new TableColumn<>("Foglalás");
        foglalasCol.setPrefWidth(100);
        foglalasCol.setCellFactory(param->{
            return new TableCell<>(){
                private final Button foglalButton = new Button("Foglal");

                {
                    foglalButton.setOnAction(event ->{
                        Jarat foglal = getTableView().getItems().get(getIndex());
                        jaratFoglal(foglal);
                        foglalButton.setDisable(true);
                    });

                }
                @Override
                protected void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        HBox container = new HBox();
                        container.getChildren().addAll(foglalButton);
                        setGraphic(container);
                    }
                }
            };
        });

        jaratTable.getColumns().addAll(jaratszamCol, max_utasCol, arCol, statuszCol, honnanCol, hovaCol, indulasCol, erkezesCol,foglalasCol);
        jaratTable.setItems(FXCollections.observableArrayList(jaratController.selectSpecific(query)));

    }

    private void jaratFoglal(Jarat j) {
        List<Foglal> foglalList = foglalController.getFoglalasokEgyJaraton(j);
        List<Integer> emptyseats = new ArrayList<>();
        boolean foglalt= false;
        for(int i = 0; i < j.getMax_utas(); i++){
            for(Foglal f : foglalList){
                if (f.getHelyid() == i){
                    foglalt = true;
                }
            }
            if (!foglalt){
                emptyseats.add(i);
            }
            foglalt=false;
        }

        if ((foglalList.size() + foglalthelyek) < j.getMax_utas()){
            Alert areYouSureAlert = new Alert(Alert.AlertType.CONFIRMATION,"Biztos benne, hogy lefoglalja?",ButtonType.YES,ButtonType.NO);
            areYouSureAlert.showAndWait().ifPresent(buttonType -> {
                if (buttonType.equals(ButtonType.YES)){
                    for (int i = 0; i < foglalthelyek; i++){
                        Foglal newFoglal = new Foglal(felhasznalo.getFelhasznalonev(),j.getJaratszam(),emptyseats.get(i),2);
                        foglalController.add(newFoglal);
                        foglalList.add(newFoglal);

                    }
                    Alert successAlert = new Alert(Alert.AlertType.INFORMATION,"Sikeres foglalás!");
                    successAlert.show();
                }
                else{
                    areYouSureAlert.close();
                }
            });
        }
        else{
            Alert youCantAlert = new Alert(Alert.AlertType.ERROR,"Nem tud ennyi helyet foglalni erre a járatra");
        }

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

        TableColumn<Biztositas,Void> megkotesCol = new TableColumn<>("");
        megkotesCol.setPrefWidth(900/6);
        megkotesCol.setCellFactory(param -> {
            return new TableCell<>(){
                private final Button megkotButton = new Button("Megkötés");

                {
                    megkotButton.setOnAction(event ->{
                        Biztositas megkot = getTableView().getItems().get(getIndex());
                        biztositastKot(megkot);
                        megkotButton.setDisable(true);
                    });

                }
                @Override
                protected void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        HBox container = new HBox();
                        container.getChildren().addAll(megkotButton);
                        setGraphic(container);
                    }
                }
            };
        });

        biztositasTable.getColumns().addAll(biztositasidCol, tipusCol, cegCol, osszegCol, idotartamCol,megkotesCol);
        biztositasTable.setItems(FXCollections.observableArrayList(biztositasController.selectSpecific(query)));
    }

    private void sajatBiztositasTablaKeszito(String query){
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

        TableColumn<Biztositas,Void> torlesCol = new TableColumn<>("");
        torlesCol.setPrefWidth(900/6);
        torlesCol.setCellFactory(param -> {
            return new TableCell<>(){
                private final Button torolButton = new Button("Törlés");

                {
                    torolButton.setOnAction(event ->{
                        Biztositas torol = getTableView().getItems().get(getIndex());
                        biztositastorol(torol);
                        torolButton.setDisable(true);
                    });

                }
                @Override
                protected void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        HBox container = new HBox();
                        container.getChildren().addAll(torolButton);
                        setGraphic(container);
                    }
                }
            };
        });

        biztositasTable.getColumns().addAll(biztositasidCol, tipusCol, cegCol, osszegCol, idotartamCol,torlesCol);
        biztositasTable.setItems(FXCollections.observableArrayList(biztositasController.selectSpecific(query)));
    }

    private void biztositastorol(Biztositas torol) {
        Alert areYouSureAlert = new Alert(Alert.AlertType.CONFIRMATION, "Biztos meg akarja szüntetni?",ButtonType.YES,ButtonType.NO);
        areYouSureAlert.showAndWait().ifPresent(buttonType -> {
            if (buttonType.equals(ButtonType.YES)){
                Biztosit newBiztosit = new Biztosit(torol.getBiztositasid(),felhasznalo.getFelhasznalonev());
                biztositController.delete(newBiztosit);
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION,"Sikeres törlés!");
                successAlert.show();
            }
            else{
                areYouSureAlert.close();
            }
        });
    }

    private void biztositastKot(Biztositas megkot) {
        Alert areYouSureAlert = new Alert(Alert.AlertType.CONFIRMATION, "Biztos meg akarja kötni?",ButtonType.YES,ButtonType.NO);
        areYouSureAlert.showAndWait().ifPresent(buttonType -> {
            if (buttonType.equals(ButtonType.YES)){
                Biztosit newBiztosit = new Biztosit(megkot.getBiztositasid(),felhasznalo.getFelhasznalonev());
                biztositController.add(newBiztosit);
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION,"Sikeres megkötés!");
                successAlert.show();
            }
            else{
                areYouSureAlert.close();
            }
        });
    }

    private void sajatFoglalasTablaKeszito() {

        sajatFoglalasTable = new TableView<>();
        sajatFoglalasTable.setMaxSize(900,600);

        sajatFoglalasTable.setEditable(false);

        TableColumn<SajatFoglalas, Integer> jaratszamCol = new TableColumn<>("Járatszám");
        jaratszamCol.setPrefWidth(100);
        jaratszamCol.setCellValueFactory(new PropertyValueFactory<SajatFoglalas, Integer>("jsz"));

        TableColumn<SajatFoglalas, String> statuszCol = new TableColumn<>("Státusz");
        statuszCol.setPrefWidth(100);
        statuszCol.setCellValueFactory(new PropertyValueFactory<SajatFoglalas,String>("sta"));

        TableColumn<SajatFoglalas, String> honnanCol = new TableColumn<>("Indulási helyszín");
        honnanCol.setPrefWidth(100);
        honnanCol.setCellValueFactory(new PropertyValueFactory<SajatFoglalas,String>("hon"));

        TableColumn<SajatFoglalas, String> hovaCol = new TableColumn<>("Érkezési helyszín");
        hovaCol.setPrefWidth(100);
        hovaCol.setCellValueFactory(new PropertyValueFactory<SajatFoglalas,String>("hov"));

        TableColumn<SajatFoglalas, Date> indulasCol = new TableColumn<>("Indulási időpont");
        indulasCol.setPrefWidth(100);
        indulasCol.setCellValueFactory(new PropertyValueFactory<SajatFoglalas,Date>("ind"));

        TableColumn<SajatFoglalas, Date> erkezesCol = new TableColumn<>("Érkezési időpont");
        erkezesCol.setPrefWidth(100);
        erkezesCol.setCellValueFactory(new PropertyValueFactory<SajatFoglalas,Date>("erk"));

        TableColumn<SajatFoglalas, Integer> helyidCol = new TableColumn<>("Helyszám");
        helyidCol.setPrefWidth(100);
        helyidCol.setCellValueFactory(new PropertyValueFactory<SajatFoglalas, Integer>("hid"));

        TableColumn<SajatFoglalas, Integer> osztalyCol = new TableColumn<>("Osztály");
        osztalyCol.setPrefWidth(100);
        osztalyCol.setCellValueFactory(new PropertyValueFactory<SajatFoglalas, Integer>("osz"));

        TableColumn<SajatFoglalas, Void> torlesCol = new TableColumn<>("Foglalás");
        torlesCol.setPrefWidth(100);
        torlesCol.setCellFactory(param->{
            return new TableCell<>(){
                private final Button torlesButton = new Button("Törlés");

                {
                    torlesButton.setOnAction(event ->{
                        SajatFoglalas torles = getTableView().getItems().get(getIndex());
                        foglalTorol(torles);
                        torlesButton.setDisable(true);
                    });

                }
                @Override
                protected void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        HBox container = new HBox();
                        container.getChildren().addAll(torlesButton);
                        setGraphic(container);
                    }
                }
            };
        });

        sajatFoglalasTable.getColumns().addAll(jaratszamCol, statuszCol, honnanCol, hovaCol, indulasCol, erkezesCol,helyidCol,osztalyCol,torlesCol);
        sajatFoglalasTable.setItems(FXCollections.observableArrayList(sajatFoglalasController.getAll(this.felhasznalo.getFelhasznalonev())));

    }

    private void foglalTorol(SajatFoglalas torles) {
        Alert areYouSureAlert = new Alert(Alert.AlertType.CONFIRMATION, "Biztos meg akarja szüntetni?",ButtonType.YES,ButtonType.NO);
        areYouSureAlert.showAndWait().ifPresent(buttonType -> {
            if (buttonType.equals(ButtonType.YES)){
                Foglal newFoglal = new Foglal(felhasznalo.getFelhasznalonev(),torles.getJsz(),torles.getHid(),torles.getOsz());
                foglalController.delete(newFoglal);
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION,"Sikeres törlés!");
                successAlert.show();
            }
            else{
                areYouSureAlert.close();
            }
        });
    }
}
