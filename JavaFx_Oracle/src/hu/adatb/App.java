package hu.adatb;

import hu.adatb.controller.*;
import hu.adatb.view.Bejelentkezes;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {
    FelhasznaloController fhcontroller = new FelhasznaloController();
    JaratController jaratController = new JaratController();
    BiztositasController biztositasController = new BiztositasController();
    FoglalController foglalController = new FoglalController();
    BiztositController biztositController = new BiztositController();
    AlkalmazottController alkalmazottController = new AlkalmazottController();
    @Override
    public void start(Stage primaryStage) {
        new Bejelentkezes(fhcontroller,jaratController,biztositasController,foglalController,biztositController,alkalmazottController);
    }


    public static void main(String[] args) { launch(args);
    }
}

