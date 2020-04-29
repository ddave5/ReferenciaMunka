package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    static Group root;
    @Override
    public void start(Stage primaryStage) throws Exception{
        root = new Group();
        root.getChildren().add(new Bejelentkezes());

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Műsorújság");
        primaryStage.getIcons().add(new Image("images/applogo.png"));
        primaryStage.setResizable(false);
        primaryStage.setWidth(1400);
        primaryStage.setHeight(900);
        primaryStage.show();
    }


    public static void main(String[] args) {

        launch(args);
    }
}
