package hu.alkfejl;

import hu.alkfejl.View.MainPagePanel;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        new MainPagePanel();
    }

    public static void main(String[] args) {
        launch();
    }

}