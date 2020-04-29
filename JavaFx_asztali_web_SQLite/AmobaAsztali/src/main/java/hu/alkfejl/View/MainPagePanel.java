package hu.alkfejl.View;

import hu.alkfejl.Model.Game;
import hu.alkfejl.Utils.Utils;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Calendar;

public class MainPagePanel extends Stage {

    public MainPagePanel() {

        contsruct();
    }

    private void contsruct() {
        BorderPane mainframe = new BorderPane();
        mainframe.setPrefSize(1400,900);
        mainframe.getStyleClass().add("mainframe");

        HBox titleBox = new HBox();
        titleBox.setPadding(new Insets(20,20,20,20));
        titleBox.setAlignment(Pos.CENTER);
        titleBox.setMaxSize(1400,200);
        Text title = new Text("Amőba");
        title.getStyleClass().add("title");
        titleBox.getChildren().add(title);

        GridPane centerBox = new GridPane();
        centerBox.setPadding(new Insets(20,20,20,20));
        centerBox.setVgap(100);
        centerBox.setHgap(20);
        centerBox.getStyleClass().add("centerBox");
        centerBox.setAlignment(Pos.CENTER);
        centerBox.setMaxSize(600,650);

        ColumnConstraints col1 = new ColumnConstraints();
        col1.setHalignment(HPos.CENTER);
        col1.setMinWidth(250);

        ColumnConstraints col2 = new ColumnConstraints();
        col2.setHalignment(HPos.CENTER);
        col2.setMinWidth(250);
        centerBox.getColumnConstraints().addAll(col1,col2);

        Text gameTypeText = new Text("Gametype: ");
        gameTypeText.getStyleClass().add("centerText");

        centerBox.addColumn(0,gameTypeText);

        ChoiceBox<String> gameTypeChoiceBox = new ChoiceBox<>();
        gameTypeChoiceBox.getItems().add("Player v Player");
        gameTypeChoiceBox.getItems().add("Player v CPU");
        gameTypeChoiceBox.setValue("Player v CPU");
        gameTypeChoiceBox.getStyleClass().add("centerCB");
        gameTypeChoiceBox.setPrefSize(200,50);
        centerBox.addColumn(1,gameTypeChoiceBox);

        Text tableSizeText = new Text("Table Size: ");
        tableSizeText.getStyleClass().add("centerText");
        centerBox.addColumn(0,tableSizeText);

        Spinner<Integer> tableSizeSpinner = new Spinner<>(new SpinnerValueFactory.IntegerSpinnerValueFactory(5, 20, 15));
        tableSizeSpinner.getStyleClass().add("centerSp");
        tableSizeSpinner.setPrefSize(200,50);
        centerBox.addColumn(1,tableSizeSpinner);

        Text timeLimitText = new Text("Time limit: ");
        timeLimitText.getStyleClass().add("centerText");
        centerBox.addColumn(0,timeLimitText);

        ChoiceBox<String> timeLimitChoiceBox = new ChoiceBox<>();
        timeLimitChoiceBox.getItems().add("5");
        timeLimitChoiceBox.getItems().add("10");
        timeLimitChoiceBox.getItems().add("15");
        timeLimitChoiceBox.getItems().add("None");
        timeLimitChoiceBox.setValue("None");
        timeLimitChoiceBox.getStyleClass().add("centerCB");
        timeLimitChoiceBox.setPrefSize(200,50);
        centerBox.addColumn(1,timeLimitChoiceBox);

        Button loadGameButton = new Button("Load a previous game");
        loadGameButton.getStyleClass().add("centerButton");
        loadGameButton.setPrefSize(250,50);
        loadGameButton.setOnAction(event -> {
            try{
                new ReplayPanel();
                close();
            }catch (Exception e){
                Utils.showWarning("Jelenleg nem létezik lementett játék");
            }
        });
        centerBox.addColumn(0,loadGameButton);

        Button startButton = new Button("Start");
        startButton.getStyleClass().add("centerButton");
        startButton.setPrefSize(250,50);
        centerBox.addColumn(1,startButton);
        startButton.setOnAction(event -> {
            boolean pve;
            int size;
            int time;

            if (gameTypeChoiceBox.getValue().toString().equals("Player v CPU")) pve = true;
            else pve = false;
            size = tableSizeSpinner.getValue();
            if (timeLimitChoiceBox.getValue().toString().equals("None")) time = -1;
            else time = Integer.parseInt(timeLimitChoiceBox.getValue());

            Game newGame = new Game(pve,size,time);

            new GamePanel(newGame);
            close();
        });

        HBox botFiller = new HBox();
        botFiller.setPrefSize(1400,100);
        botFiller.getStyleClass().add("botFiller");

        HBox leftFiller = new HBox();
        leftFiller.setMaxSize(50,700);
        leftFiller.getStyleClass().add("leftFiller");

        HBox rightFiller = new HBox();
        rightFiller.setMaxSize(50,700);
        rightFiller.getStyleClass().add("leftFiller");

        mainframe.setBottom(botFiller);
        mainframe.setLeft(leftFiller);
        mainframe.setRight(rightFiller);
        mainframe.setTop(titleBox);
        mainframe.setCenter(centerBox);

        Scene scene = new Scene(mainframe, 1400, 900);
        scene.getStylesheets().add(getClass().getResource("/css/mainPageStyle.css").toExternalForm());
        this.setScene(scene);
        this.setResizable(false);
        this.setTitle("Amőba");
        this.show();
    }


}
