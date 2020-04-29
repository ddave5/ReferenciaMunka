package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;

public class MainPage extends Parent  {
    private VBox egesz, csatornaBox, megjelenitoBox;
    private VBox musorCim, musorLeiras, musorJatekido, musorOra, musorPerc, musorHozzaad, musorCsatorna, musorTorol;
    private HBox cimBox, testBox, napokBox, musorBox, tartalom;
    private ScrollPane musorujsag;
    private Text cim;
    private Label napok,hellofh;
    private Button TV2, RTL, NatGeo, SzegedTv, Elo, SajatLista, left, right;
    private String[] het = {"Hétfő", "Kedd", "Szerda", "Csütörtök", "Péntek", "Szombat", "Vasárnap"};
    private int aktualisnap = 0;

    private ArrayList<Musor> musorlista;

    private ConnectionUtils con = new ConnectionUtils();

    public MainPage(){

        /* FELEPITES KEZDETE */
        cimBox = new HBox();
        cimBox.getStyleClass().add("titleBox");

        testBox = new HBox();
        testBox.getStyleClass().add("textBox");

        csatornaBox = new VBox();
        csatornaBox.getStyleClass().add("csatornaBox");

        megjelenitoBox = new VBox();
        megjelenitoBox.getStyleClass().add("megjelenitoBox");

        testBox.getChildren().addAll(csatornaBox,megjelenitoBox);

        napokBox = new HBox();
        napokBox.getStyleClass().add("napokBox");
        musorBox = new HBox();
        musorBox.getStyleClass().add("musorBox");

        musorujsag = new ScrollPane();
        musorujsag.setPrefSize(1000,500);
        musorujsag.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        musorBox.getChildren().add(musorujsag);

        megjelenitoBox.getChildren().addAll(napokBox,musorBox);

        /* FELEPITES VEGE */

        /* CIMBOX KEZDETE */
        cim = new Text("Műsorújság");
        cim.setTranslateX(20);
        cim.setTranslateY(10);
        cim.getStyleClass().add("cim");
        cimBox.getChildren().add(cim);

        hellofh = new Label();
        hellofh.setText("Hello, " + Bejelentkezes.user.getNev());
        hellofh.setTranslateX(800);
        hellofh.setTranslateY(30);
        hellofh.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Main.root.getChildren().clear();
                Main.root.getChildren().add(new Profile());
            }
        });
        hellofh.getStyleClass().add("hellofh");

        cimBox.getChildren().add(hellofh);

        /* CIMBOX VEGE */

        /* CSATORNABOX KEZDETE */

        Image TV2logo = new Image("images/TV2_logo.png");
        ImageView TV2logoview = new ImageView(TV2logo);
        TV2logoview.setFitWidth(50);
        TV2logoview.setFitHeight(50);
        TV2 = new Button("TV2",TV2logoview);
        TV2.setTranslateX(20);
        TV2.setTranslateY(70);
        TV2.getStyleClass().add("gombok");
        TV2.setVisible(false);
        csatornaBox.getChildren().add(TV2);

        Image RTLlogo = new Image("images/RTL_logo.png");
        ImageView RTLlogoview = new ImageView(RTLlogo);
        RTLlogoview.setFitWidth(70);
        RTLlogoview.setFitHeight(50);
        RTL = new Button("RTL",RTLlogoview);
        RTL.setTranslateX(20);
        RTL.setTranslateY(TV2.getTranslateY()+10);
        RTL.getStyleClass().add("gombok");
        RTL.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int sortores = 0;
                int sortoresgomb = 0;

                musorujsag.setContent(null);

                tartalom = new HBox();
                tartalom.getStyleClass().add("tartalom");
                musorujsag.setContent(tartalom);


                tartalom.getChildren().clear();

                musorCim = new VBox();
                musorCim.getStyleClass().add("musoroszlop");
                musorLeiras = new VBox();
                musorLeiras.getStyleClass().add("musoroszlop");
                musorJatekido = new VBox();
                musorJatekido.getStyleClass().add("musoroszlop");
                musorOra = new VBox();
                musorOra.getStyleClass().add("musoroszlop");
                musorPerc = new VBox();
                musorPerc.getStyleClass().add("musoroszlop");
                musorHozzaad = new VBox();
                musorHozzaad.getStyleClass().add("musoroszlop");

                tartalom.getChildren().addAll(musorCim,musorLeiras,musorJatekido,musorOra,musorPerc,musorHozzaad);
                musorlista = con.listDayMusor("RTL",napok.getText());

                Text cimkiir[] = new Text[musorlista.size()+1];
                Text leiraskiir[] =  new Text[musorlista.size()+1];
                Text jatekidokiir[] = new Text[musorlista.size()+1];
                Text orakiir[] =  new Text[musorlista.size()+1];
                Text perckiir[] =  new Text[musorlista.size()+1];
                Button hozzaad[] =  new Button[musorlista.size()+1];

                Text felsocim = new Text("Cím");
                cimkiir[0] = felsocim;
                cimkiir[0].setTranslateX(10);
                cimkiir[0].setTranslateY(10 + sortores);
                cimkiir[0].getStyleClass().add("fejlec");
                musorCim.getChildren().add(cimkiir[0]);

                Text felsoleiras = new Text();
                leiraskiir[0] = felsoleiras;
                leiraskiir[0].setText("Leírás");
                leiraskiir[0].setTranslateX(10);
                leiraskiir[0].setTranslateY(10 + sortores);
                leiraskiir[0].getStyleClass().add("musor");
                musorLeiras.getChildren().add(leiraskiir[0]);

                Text felsojatekido = new Text();
                jatekidokiir[0] = felsojatekido;
                jatekidokiir[0].setText("Játékidő");
                jatekidokiir[0].setTranslateX(10 );
                jatekidokiir[0].setTranslateY(10 + sortores);
                jatekidokiir[0].getStyleClass().add("musor");
                musorJatekido.getChildren().add(jatekidokiir[0]);

                Text felsoora = new Text();
                orakiir[0] = felsoora;
                orakiir[0].setText("Óra");
                orakiir[0].setTranslateX(10);
                orakiir[0].setTranslateY(10 + sortores);
                orakiir[0].getStyleClass().add("musor");
                musorOra.getChildren().add(orakiir[0]);

                Text felsoperc = new Text();
                perckiir[0] = felsoperc;
                perckiir[0].setText("Perc");
                perckiir[0].setTranslateX(10 );
                perckiir[0].setTranslateY(10 + sortores);
                perckiir[0].getStyleClass().add("musor");
                musorPerc.getChildren().add(perckiir[0]);

                sortores+=30;
                sortoresgomb+=40;

                for (int i = 1; i < musorlista.size()+1; i++){
                    Text cim = new Text();
                    cimkiir[i] = cim;
                    cimkiir[i].setText(musorlista.get(i-1).getNev());
                    cimkiir[i].setTranslateX(10);
                    cimkiir[i].setTranslateY(10 + sortores);
                    cimkiir[i].getStyleClass().add("musor");
                    musorCim.getChildren().add(cimkiir[i]);

                    Text leiras = new Text();
                    leiraskiir[i] = leiras;
                    leiraskiir[i].setText(musorlista.get(i-1).getLeiras());
                    leiraskiir[i].setTranslateX(10);
                    leiraskiir[i].setTranslateY(10 + sortores);
                    leiraskiir[i].getStyleClass().add("musor");
                    musorLeiras.getChildren().add(leiraskiir[i]);

                    Text jatekido = new Text();
                    jatekidokiir[i] = jatekido;
                    jatekidokiir[i].setText(Integer.toString(musorlista.get(i-1).getJatekido()) + " perc");
                    jatekidokiir[i].setTranslateX(10 );
                    jatekidokiir[i].setTranslateY(10 + sortores);
                    jatekidokiir[i].getStyleClass().add("musor");
                    musorJatekido.getChildren().add(jatekidokiir[i]);

                    Text ora = new Text();
                    orakiir[i] = ora;
                    orakiir[i].setText(Integer.toString(musorlista.get(i-1).getOra()) + " óra");
                    orakiir[i].setTranslateX(10);
                    orakiir[i].setTranslateY(10 + sortores);
                    orakiir[i].getStyleClass().add("musor");
                    musorOra.getChildren().add(orakiir[i]);

                    Text perc = new Text();
                    perckiir[i] = perc;
                    perckiir[i].setText(Integer.toString(musorlista.get(i-1).getPerc()) + " perc");
                    perckiir[i].setTranslateX(10 );
                    perckiir[i].setTranslateY(10 + sortores);
                    perckiir[i].getStyleClass().add("musor");
                    musorPerc.getChildren().add(perckiir[i]);

                    Button ha = new Button();
                    hozzaad[i] = ha;
                    hozzaad[i].setText("Hozzáadás");
                    hozzaad[i].setWrapText(true);
                    hozzaad[i].setTranslateX(10);
                    hozzaad[i].setTranslateY(10+sortoresgomb);
                    hozzaad[i].getStyleClass().add("hozzaadgomb");
                    int finalI = i;
                    hozzaad[i].setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            con.hozzaadKedvenchez(Bejelentkezes.user,musorlista.get(finalI-1));
                            

                        }
                    });
                    musorHozzaad.getChildren().add(hozzaad[i]);

                    sortores += 30;
                    sortoresgomb += 22;

                }


            }
        });
        csatornaBox.getChildren().add(RTL);

        Image NatGeologo = new Image("images/NatGeo_logo.png");
        ImageView NatGeologoview = new ImageView(NatGeologo);
        NatGeologoview.setFitWidth(80);
        NatGeologoview.setFitHeight(30);
        NatGeo = new Button("NatGeo",NatGeologoview);
        NatGeo.setTranslateX(20);
        NatGeo.setTranslateY(RTL.getTranslateY()+10);
        NatGeo.getStyleClass().add("gombok");
        NatGeo.setVisible(false);
        csatornaBox.getChildren().add(NatGeo);

        Image SzegedTvlogo = new Image("images/SzegedTv_logo.png");
        ImageView SzegedTvlogoview = new ImageView(SzegedTvlogo);
        SzegedTvlogoview.setFitWidth(70);
        SzegedTvlogoview.setFitHeight(40);
        SzegedTv = new Button("SzegedTv",SzegedTvlogoview);
        SzegedTv.setTranslateX(20);
        SzegedTv.setTranslateY(NatGeo.getTranslateY()+10);
        SzegedTv.getStyleClass().add("gombok");
        SzegedTv.setVisible(false);
        csatornaBox.getChildren().add(SzegedTv);

        Image Elologo = new Image("images/Elo_logo.png");
        ImageView Elologoview = new ImageView(Elologo);
        Elologoview.setFitWidth(150);
        Elologoview.setFitHeight(50);
        Elo = new Button("",Elologoview);
        Elo.setTranslateX(20);
        Elo.setTranslateY(SzegedTv.getTranslateY()+10);
        Elo.getStyleClass().add("gombok");
        Elo.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                int sortores = 0;

                musorujsag.setContent(null);

                tartalom = new HBox();
                tartalom.getStyleClass().add("tartalom");
                musorujsag.setContent(tartalom);


                tartalom.getChildren().clear();

                musorCim = new VBox();
                musorCim.getStyleClass().add("musoroszlop");
                musorLeiras = new VBox();
                musorLeiras.getStyleClass().add("musoroszlop");
                musorJatekido = new VBox();
                musorJatekido.getStyleClass().add("musoroszlop");
                musorOra = new VBox();
                musorOra.getStyleClass().add("musoroszlop");
                musorPerc = new VBox();
                musorPerc.getStyleClass().add("musoroszlop");
                musorCsatorna = new VBox();
                musorCsatorna.getStyleClass().add("musoroszlop");

                tartalom.getChildren().addAll(musorCim,musorLeiras,musorJatekido,musorOra,musorPerc,musorCsatorna);
                musorlista = con.listLiveMusor();

                Text cimkiir[] = new Text[musorlista.size()+1];
                Text leiraskiir[] =  new Text[musorlista.size()+1];
                Text jatekidokiir[] = new Text[musorlista.size()+1];
                Text orakiir[] =  new Text[musorlista.size()+1];
                Text perckiir[] =  new Text[musorlista.size()+1];
                Text csatornakiir[] =  new Text[musorlista.size()+1];

                Text felsocim = new Text("Cím");
                cimkiir[0] = felsocim;
                cimkiir[0].setTranslateX(10);
                cimkiir[0].setTranslateY(10 + sortores);
                cimkiir[0].getStyleClass().add("fejlec");
                musorCim.getChildren().add(cimkiir[0]);

                Text felsoleiras = new Text();
                leiraskiir[0] = felsoleiras;
                leiraskiir[0].setText("Leírás");
                leiraskiir[0].setTranslateX(10);
                leiraskiir[0].setTranslateY(10 + sortores);
                leiraskiir[0].getStyleClass().add("musor");
                musorLeiras.getChildren().add(leiraskiir[0]);

                Text felsojatekido = new Text();
                jatekidokiir[0] = felsojatekido;
                jatekidokiir[0].setText("Játékidő");
                jatekidokiir[0].setTranslateX(10 );
                jatekidokiir[0].setTranslateY(10 + sortores);
                jatekidokiir[0].getStyleClass().add("musor");
                musorJatekido.getChildren().add(jatekidokiir[0]);

                Text felsoora = new Text();
                orakiir[0] = felsoora;
                orakiir[0].setText("Óra");
                orakiir[0].setTranslateX(10);
                orakiir[0].setTranslateY(10 + sortores);
                orakiir[0].getStyleClass().add("musor");
                musorOra.getChildren().add(orakiir[0]);

                Text felsoperc = new Text();
                perckiir[0] = felsoperc;
                perckiir[0].setText("Perc");
                perckiir[0].setTranslateX(10 );
                perckiir[0].setTranslateY(10 + sortores);
                perckiir[0].getStyleClass().add("musor");
                musorPerc.getChildren().add(perckiir[0]);

                Text felsocsatorna = new Text();
                csatornakiir[0] = felsocsatorna;
                csatornakiir[0].setText("Csatorna");
                csatornakiir[0].setTranslateX(10 );
                csatornakiir[0].setTranslateY(10 + sortores);
                csatornakiir[0].getStyleClass().add("musor");
                musorCsatorna.getChildren().add(csatornakiir[0]);


                sortores+=30;

                for (int i = 1; i < musorlista.size()+1; i++){
                    Text cim = new Text();
                    cimkiir[i] = cim;
                    cimkiir[i].setText(musorlista.get(i-1).getNev());
                    cimkiir[i].setTranslateX(10);
                    cimkiir[i].setTranslateY(10 + sortores);
                    cimkiir[i].getStyleClass().add("musor");
                    musorCim.getChildren().add(cimkiir[i]);

                    Text leiras = new Text();
                    leiraskiir[i] = leiras;
                    leiraskiir[i].setText(musorlista.get(i-1).getLeiras());
                    leiraskiir[i].setTranslateX(10);
                    leiraskiir[i].setTranslateY(10 + sortores);
                    leiraskiir[i].getStyleClass().add("musor");
                    musorLeiras.getChildren().add(leiraskiir[i]);

                    Text jatekido = new Text();
                    jatekidokiir[i] = jatekido;
                    jatekidokiir[i].setText(Integer.toString(musorlista.get(i-1).getJatekido()) + " perc");
                    jatekidokiir[i].setTranslateX(10 );
                    jatekidokiir[i].setTranslateY(10 + sortores);
                    jatekidokiir[i].getStyleClass().add("musor");
                    musorJatekido.getChildren().add(jatekidokiir[i]);

                    Text ora = new Text();
                    orakiir[i] = ora;
                    orakiir[i].setText(Integer.toString(musorlista.get(i-1).getOra()) + " óra");
                    orakiir[i].setTranslateX(10);
                    orakiir[i].setTranslateY(10 + sortores);
                    orakiir[i].getStyleClass().add("musor");
                    musorOra.getChildren().add(orakiir[i]);

                    Text perc = new Text();
                    perckiir[i] = perc;
                    perckiir[i].setText(Integer.toString(musorlista.get(i-1).getPerc()) + " perc");
                    perckiir[i].setTranslateX(10 );
                    perckiir[i].setTranslateY(10 + sortores);
                    perckiir[i].getStyleClass().add("musor");
                    musorPerc.getChildren().add(perckiir[i]);

                    Text csatorna = new Text();
                    csatornakiir[i] = csatorna;
                    csatornakiir[i].setText("RTL");
                    csatornakiir[i].setTranslateX(10);
                    csatornakiir[i].setTranslateY(10+sortores);
                    csatornakiir[i].getStyleClass().add("musor");
                    musorCsatorna.getChildren().add(csatornakiir[i]);

                    sortores += 30;
                }
            }
        });
        csatornaBox.getChildren().add(Elo);

        Image SajatListalogo = new Image("images/SajatLista_logo.png");
        ImageView SajatListalogoview = new ImageView(SajatListalogo);
        SajatListalogoview.setFitWidth(50);
        SajatListalogoview.setFitHeight(50);
        SajatLista = new Button("Műsoraim",SajatListalogoview);
        SajatLista.setTranslateX(20);
        SajatLista.setTranslateY(Elo.getTranslateY()+10);
        SajatLista.getStyleClass().add("gombok");
        SajatLista.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                int sortores = 0;
                int sortoresgomb = 0;

                musorujsag.setContent(null);

                tartalom = new HBox();
                tartalom.getStyleClass().add("tartalom");
                musorujsag.setContent(tartalom);


                tartalom.getChildren().clear();

                musorCim = new VBox();
                musorCim.getStyleClass().add("musoroszlop");
                musorLeiras = new VBox();
                musorLeiras.getStyleClass().add("musoroszlop");
                musorJatekido = new VBox();
                musorJatekido.getStyleClass().add("musoroszlop");
                musorOra = new VBox();
                musorOra.getStyleClass().add("musoroszlop");
                musorPerc = new VBox();
                musorPerc.getStyleClass().add("musoroszlop");
                musorCsatorna = new VBox();
                musorCsatorna.getStyleClass().add("musoroszlop");
                musorTorol = new VBox();
                musorTorol.getStyleClass().add("musoroszlop");

                tartalom.getChildren().addAll(musorCim,musorLeiras,musorJatekido,musorOra,musorPerc,musorCsatorna, musorTorol);
                musorlista = con.listMusoraim(Bejelentkezes.user);

                Text cimkiir[] = new Text[musorlista.size()+1];
                Text leiraskiir[] =  new Text[musorlista.size()+1];
                Text jatekidokiir[] = new Text[musorlista.size()+1];
                Text orakiir[] =  new Text[musorlista.size()+1];
                Text perckiir[] =  new Text[musorlista.size()+1];
                Text csatornakiir[] = new Text[musorlista.size()+1];
                Button torol[] =  new Button[musorlista.size()+1];

                Text felsocim = new Text("Cím");
                cimkiir[0] = felsocim;
                cimkiir[0].setTranslateX(10);
                cimkiir[0].setTranslateY(10 + sortores);
                cimkiir[0].getStyleClass().add("fejlec");
                musorCim.getChildren().add(cimkiir[0]);

                Text felsoleiras = new Text();
                leiraskiir[0] = felsoleiras;
                leiraskiir[0].setText("Leírás");
                leiraskiir[0].setTranslateX(10);
                leiraskiir[0].setTranslateY(10 + sortores);
                leiraskiir[0].getStyleClass().add("musor");
                musorLeiras.getChildren().add(leiraskiir[0]);

                Text felsojatekido = new Text();
                jatekidokiir[0] = felsojatekido;
                jatekidokiir[0].setText("Játékidő");
                jatekidokiir[0].setTranslateX(10 );
                jatekidokiir[0].setTranslateY(10 + sortores);
                jatekidokiir[0].getStyleClass().add("musor");
                musorJatekido.getChildren().add(jatekidokiir[0]);

                Text felsoora = new Text();
                orakiir[0] = felsoora;
                orakiir[0].setText("Óra");
                orakiir[0].setTranslateX(10);
                orakiir[0].setTranslateY(10 + sortores);
                orakiir[0].getStyleClass().add("musor");
                musorOra.getChildren().add(orakiir[0]);

                Text felsoperc = new Text();
                perckiir[0] = felsoperc;
                perckiir[0].setText("Perc");
                perckiir[0].setTranslateX(10 );
                perckiir[0].setTranslateY(10 + sortores);
                perckiir[0].getStyleClass().add("musor");
                musorPerc.getChildren().add(perckiir[0]);

                Text felsocsatorna = new Text();
                csatornakiir[0] = felsocsatorna;
                csatornakiir[0].setText("Csatorna");
                csatornakiir[0].setTranslateX(10 );
                csatornakiir[0].setTranslateY(10 + sortores);
                csatornakiir[0].getStyleClass().add("musor");
                musorCsatorna.getChildren().add(csatornakiir[0]);

                sortores+=30;
                sortoresgomb+=40;

                for (int i = 1; i < musorlista.size()+1; i++){
                    Text cim = new Text();
                    cimkiir[i] = cim;
                    cimkiir[i].setText(musorlista.get(i-1).getNev());
                    cimkiir[i].setTranslateX(10);
                    cimkiir[i].setTranslateY(10 + sortores);
                    cimkiir[i].getStyleClass().add("musor");
                    musorCim.getChildren().add(cimkiir[i]);

                    Text leiras = new Text();
                    leiraskiir[i] = leiras;
                    leiraskiir[i].setText(musorlista.get(i-1).getLeiras());
                    leiraskiir[i].setTranslateX(10);
                    leiraskiir[i].setTranslateY(10 + sortores);
                    leiraskiir[i].getStyleClass().add("musor");
                    musorLeiras.getChildren().add(leiraskiir[i]);

                    Text jatekido = new Text();
                    jatekidokiir[i] = jatekido;
                    jatekidokiir[i].setText(Integer.toString(musorlista.get(i-1).getJatekido()) + " perc");
                    jatekidokiir[i].setTranslateX(10 );
                    jatekidokiir[i].setTranslateY(10 + sortores);
                    jatekidokiir[i].getStyleClass().add("musor");
                    musorJatekido.getChildren().add(jatekidokiir[i]);

                    Text ora = new Text();
                    orakiir[i] = ora;
                    orakiir[i].setText(Integer.toString(musorlista.get(i-1).getOra()) + " óra");
                    orakiir[i].setTranslateX(10);
                    orakiir[i].setTranslateY(10 + sortores);
                    orakiir[i].getStyleClass().add("musor");
                    musorOra.getChildren().add(orakiir[i]);

                    Text perc = new Text();
                    perckiir[i] = perc;
                    perckiir[i].setText(Integer.toString(musorlista.get(i-1).getPerc()) + " perc");
                    perckiir[i].setTranslateX(10 );
                    perckiir[i].setTranslateY(10 + sortores);
                    perckiir[i].getStyleClass().add("musor");
                    musorPerc.getChildren().add(perckiir[i]);

                    Text csatorna = new Text();
                    csatornakiir[i] = csatorna;
                    csatornakiir[i].setText("RTL");
                    csatornakiir[i].setTranslateX(10);
                    csatornakiir[i].setTranslateY(10+sortores);
                    csatornakiir[i].getStyleClass().add("musor");
                    musorCsatorna.getChildren().add(csatornakiir[i]);

                    Button t = new Button();
                    torol[i] = t;
                    torol[i].setText("Törlés");
                    torol[i].setWrapText(true);
                    torol[i].setTranslateX(10);
                    torol[i].setTranslateY(10+sortoresgomb);
                    torol[i].getStyleClass().add("hozzaadgomb");
                    int finalI = i;
                    torol[i].setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            con.torolKovetest(Bejelentkezes.user,musorlista.get(finalI-1));



                        }
                    });
                    musorTorol.getChildren().add(torol[i]);

                    sortores += 30;
                    sortoresgomb += 22;

                }
            }
        });
        csatornaBox.getChildren().add(SajatLista);

        /* CSATORNABOX VÉGE */

        /* NAPOKBOX KEZDETE */

        napok = new Label(het[0]);
        napok.setTranslateX(440);
        napok.setTranslateY(10);
        napok.setContentDisplay(ContentDisplay.CENTER);
        napok.getStyleClass().add("napok");
        napokBox.getChildren().add(napok);

        Image leftlogo = new Image("images/up.png");
        ImageView leftlogoview = new ImageView(leftlogo);
        leftlogoview.setRotate(leftlogoview.getRotate()+270);
        leftlogoview.setFitWidth(40);
        leftlogoview.setFitHeight(40);
        left = new Button("",leftlogoview);
        left.setTranslateX(200);
        left.getStyleClass().add("leftright");
        left.setOnMouseClicked(event -> {
            if (aktualisnap == 0){
                napok.setText(het[6]);
                aktualisnap = 6;
            }
            else{
                napok.setText(het[aktualisnap-1]);
                aktualisnap-=1;
            }
        });
        napokBox.getChildren().add(left);

        Image rightlogo = new Image("images/up.png");
        ImageView rightlogoview = new ImageView(rightlogo);
        rightlogoview.setRotate(rightlogoview.getRotate()+90);
        rightlogoview.setFitWidth(40);
        rightlogoview.setFitHeight(40);
        right = new Button("",rightlogoview);
        right.setTranslateX(350);
        right.getStyleClass().add("leftright");
        right.setOnMouseClicked(event -> {
            if (aktualisnap == 6){
                napok.setText(het[0]);
                aktualisnap = 0;
            }
            else{
                napok.setText(het[aktualisnap+1]);
                aktualisnap+=1;
            }
        });
        napokBox.getChildren().add(right);

        ////////////////////////// MŰSORÚJSÁG KIÍRÁSA /////////////////////////




        ///////////////////////// Egesz felulet/////////////////////////////////
        Group roott = new Group();
        egesz = new VBox();
        egesz.getChildren().addAll(cimBox, testBox);
        Image BackgroundImage = new Image("images/hatter.png");
        BackgroundImage background = new BackgroundImage(BackgroundImage,BackgroundRepeat.REPEAT,BackgroundRepeat.REPEAT,BackgroundPosition.CENTER,BackgroundSize.DEFAULT);
        egesz.setBackground(new Background(background));
        roott.getStyleClass().add("root");
        roott.getChildren().add(egesz);
        this.getChildren().add(roott);
        this.getStylesheets().add("view/styleMainPage.css");
    }



}
