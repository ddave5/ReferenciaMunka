package hu.alkfejl.View;

import hu.alkfejl.Controller.GameController;
import hu.alkfejl.Model.Game;
import hu.alkfejl.Utils.Utils;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.List;


public class ReplayPanel extends Stage {

    GameController gameController = new GameController();
    Game game;
    private static final Integer STARTTIME = 1;
    private Timeline timeline;
    private IntegerProperty timeSeconds;
    int cursor = 0;
    boolean isPaused = true;

    BorderPane mainframe;
    GridPane map;

    public ReplayPanel(){
        timeSeconds = new SimpleIntegerProperty(STARTTIME);
        construct();
    }

    private void construct() {
        mainframe = new BorderPane();
        mainframe.setPrefSize(1400,900);
        mainframe.getStyleClass().add("mainframe");

        List<Game> allGames = gameController.getAll();

        HBox topBox = new HBox(50);
        topBox.setPrefSize(1400,100);
        topBox.setAlignment(Pos.CENTER);
        topBox.setPadding(new Insets(20,20,20,20));
        ChoiceBox<String> gameCB = new ChoiceBox<>();
        for (int i = 0; i < allGames.size(); i++){
            gameCB.getItems().add(allGames.get(i).getName());
        }
        if (gameCB.getItems() != null){
            gameCB.setValue(gameCB.getItems().get(0));
        }
        gameCB.setPrefSize(500,50);
        Button loadButton = new Button("Load this game");
        loadButton.getStyleClass().add("topButton");
        loadButton.setPrefSize(200,50);
        loadButton.setOnAction(event -> {
            cursor = 0;
            for (Game g : allGames){
                if (g.getName().equals(gameCB.getValue())){
                    game = g;
                }
            }
            VBox leftBox = new VBox(50);
            leftBox.setPrefSize(200,700);
            leftBox.setAlignment(Pos.TOP_CENTER);
            leftBox.setPadding(new Insets(30,30,30,30));

            HBox typeBox = new HBox(30);
            Text typeText = new Text("Game Type: ");
            typeText.getStyleClass().add("leftText");
            Text gametypeText = new Text();
            if (game.isPve()){
                gametypeText.setText("Player vs CPU");
            }
            else{
                gametypeText.setText("Player vs Player");
            }
            gametypeText.getStyleClass().add("leftText");
            typeBox.getChildren().addAll(typeText,gametypeText);

            HBox sizeBox = new HBox(42);
            Text sizeText = new Text("Table size: ");
            sizeText.getStyleClass().add("leftText");
            Text gameTableSizeText = new Text(Integer.toString(game.getTablesize()));
            gameTableSizeText.getStyleClass().add("leftText");
            sizeBox.getChildren().addAll(sizeText,gameTableSizeText);

            HBox timeLimitBox = new HBox(36);
            Text limitText = new Text("Time Limit: ");
            limitText.getStyleClass().add("leftText");
            Text gameTimeLimitText = new Text();
            if (game.getTime() == -1){
                gameTimeLimitText.setText("No limit");
            }
            else{
                gameTimeLimitText.setText(Integer.toString(game.getTime()));
            }
            gameTimeLimitText.getStyleClass().add("leftText");
            timeLimitBox.getChildren().addAll(limitText,gameTimeLimitText);

            Text emptyText = new Text();
            Button backToMainButton = new Button("Back to main");
            backToMainButton.setPrefSize(150,50);
            backToMainButton.getStyleClass().add("topButton");
            backToMainButton.setOnAction(event1 -> {
                new MainPagePanel();
                close();
            });
            Button exitButton = new Button("Exit");
            exitButton.setPrefSize(150,50);
            exitButton.getStyleClass().add("topButton");
            exitButton.setOnAction(event1 -> { close();  });

            leftBox.getChildren().addAll(typeBox,sizeBox,timeLimitBox,emptyText,backToMainButton,exitButton);

            map = new GridPane();
            map.setMaxSize(700,700);
            map.setGridLinesVisible(true);
            map.getStyleClass().add("mapGridPane");
            createMap();

            HBox botBox = new HBox(30);
            botBox.setPrefSize(1400,200);
            botBox.setAlignment(Pos.CENTER_RIGHT);
            botBox.setPadding(new Insets(30,200,30,30));

            Button moveToStartButton = new Button("",new ImageView(new Image(getClass().getResource("/images/moveToStart.png").toExternalForm())));
            moveToStartButton.getStyleClass().add("botButton");
            moveToStartButton.setOnAction(event1 -> {
                movetoStart();
            });
            Button oneStepBackwardButton = new Button("",new ImageView(new Image(getClass().getResource("/images/oneStepBackward.png").toExternalForm())));
            oneStepBackwardButton.getStyleClass().add("botButton");
            oneStepBackwardButton.setOnAction(event1 -> {
                oneStepBackward();
            });
            Button pauseButton = new Button("",new ImageView(new Image(getClass().getResource("/images/pause.png").toExternalForm())));
            pauseButton.getStyleClass().add("botButton");
            pauseButton.setOnAction(event1 -> {
                pause();
            });
            Button playButton = new Button("",new ImageView(new Image(getClass().getResource("/images/play.png").toExternalForm())));
            playButton.getStyleClass().add("botButton");
            playButton.setOnAction(event1 -> {
                play();
            });
            Button oneStepAheadButton = new Button("",new ImageView(new Image(getClass().getResource("/images/oneStepAhead.png").toExternalForm())));
            oneStepAheadButton.getStyleClass().add("botButton");
            oneStepAheadButton.setOnAction(event1 -> {
                oneStepAhead();
            });
            Button moveToEndButton = new Button("",new ImageView(new Image(getClass().getResource("/images/moveToEnd.png").toExternalForm())));
            moveToEndButton.getStyleClass().add("botButton");
            moveToEndButton.setOnAction(event1 -> {
                moveToEnd();
            });


            botBox.getChildren().addAll(moveToStartButton,oneStepBackwardButton,pauseButton,playButton,oneStepAheadButton,moveToEndButton);

            mainframe.setLeft(leftBox);
            mainframe.setCenter(map);
            mainframe.setBottom(botBox);
        });

        topBox.getChildren().addAll(gameCB,loadButton);
        mainframe.setTop(topBox);

        Scene scene = new Scene(mainframe, 1400, 900);
        scene.getStylesheets().add(getClass().getResource("/css/replayStyle.css").toExternalForm());
        this.setScene(scene);
        this.setResizable(false);
        this.setTitle("Amőba");
        this.show();
    }

    private void createMap(){
        if (map.getChildren() != null){
            Node valami = map.getChildren().get(0);
            map.getChildren().clear();
            map.getChildren().add(valami);
        }
        for (int i = 0; i < game.getTablesize(); i++){
            for (int j = 0; j < game.getTablesize(); j++){
                Button b = new Button();
                b.setAlignment(Pos.CENTER);
                b.setPrefSize(700.0/game.getTablesize(),
                        700.0/game.getTablesize());
                b.setStyle("-fx-background-color:-fx-background");
                b.setDisable(true);
                map.add(b,i,j);

            }
        }
        mainframe.setCenter(map);
    }

    /**
     * A játék kezdeti formájára hozza
     */
    private void movetoStart() {
        cursor = 0;
        createMap();
    }

    /**
     * A kurzort eggyel visszább helyezi és az utolsó lépésben változott kockákra meghívja a cleanSquare() függvényt
     */
    private void oneStepBackward() {
        if (game.getiMovesPlayer1().size() > game.getiMovesPlayer2().size() && cursor == game.getiMovesPlayer1().size()){
            cursor--;
            cleanSquare(game.getiMovesPlayer1().get(cursor),game.getjMovesPlayer1().get(cursor));
        }
        else if(cursor != 0){
            cursor--;
            cleanSquare(game.getiMovesPlayer1().get(cursor),game.getjMovesPlayer1().get(cursor));
            cleanSquare(game.getiMovesPlayer2().get(cursor),game.getjMovesPlayer2().get(cursor));
        }
    }

    /**
     * Az automatikus léptetést állítja meg
     */
    private void pause() {
        isPaused = true;
        if (timeline != null) {
            timeline.stop();
        }
    }

    /**
     * A játék lépéseit automatikusan lépteti 2 másodpercenként
     */
    private void play()  {
        isPaused = false;
        if (timeline != null) {
            timeline.stop();
        }
        timeSeconds.set(STARTTIME);
        timeline = new Timeline();
        timeline.getKeyFrames().add(
                new KeyFrame(Duration.seconds(STARTTIME+1),
                        event -> {
                            oneStepAhead();
                        }));
        timeline.playFromStart();
        timeline.setOnFinished(event1 -> {
            if(cursor != game.getiMovesPlayer1().size()){
                timeline.playFromStart();
            }
        });

    }

    /**
     * Megrajzolja a következő lépéseket. Ellenőrzi, hogy a játék véget én-e a következő lépésben
     */
    private void oneStepAhead() {
        if(cursor<game.getiMovesPlayer1().size() || cursor < game.getiMovesPlayer2().size()){
            if(cursor<game.getiMovesPlayer1().size()){
                drawInSquare(game.getiMovesPlayer1().get(cursor),game.getjMovesPlayer1().get(cursor),1);
            }
            if(cursor<game.getiMovesPlayer2().size()){
                drawInSquare(game.getiMovesPlayer2().get(cursor),game.getjMovesPlayer2().get(cursor),-1);
            }
            cursor++;
        }
        if(cursor == game.getiMovesPlayer1().size()){
            Utils.showInfo("Game over!");
        }

    }

    /**
     * A játék legvégső fáziába helyezi a játékot
     */
    private void moveToEnd() {

        for(int i = cursor; i < game.getiMovesPlayer1().size(); i++){
            drawInSquare(game.getiMovesPlayer1().get(i),game.getjMovesPlayer1().get(i),1);
        }
        for (int i = cursor; i < game.getjMovesPlayer2().size(); i++){
            drawInSquare(game.getiMovesPlayer2().get(i),game.getjMovesPlayer2().get(i),-1);
        }

        if (game.getiMovesPlayer1().size() <= game.getiMovesPlayer2().size()) cursor=game.getiMovesPlayer1().size();
        else cursor=game.getiMovesPlayer2().size();
    }

    /**
     * A kiválasztott gombra rajzol egy X-et vagy egy O-t, attól függően, hogy ki lépett
     * @param ilepes A gomb i. koordinátája
     * @param jlepes A gomb j. koordinátája
     * @param XorO Az adott játékos kódja (player: 1, CPU: -1)
     */
    private void drawInSquare(int ilepes, int jlepes, int XorO){
        Button b = (Button) map.getChildren().get(ilepes*game.getTablesize()+jlepes+1);
        if(XorO == 1){
            b.setText("X");
            b.setStyle("-fx-font-size:" + (700*14)/(35*game.getTablesize()));
            b.getStyleClass().add("redX");
        }
        else{
            b.setText("O");
            b.setStyle("-fx-font-size:" + (700*14)/(35*game.getTablesize()));
            b.getStyleClass().add("blueO");
        }
        b.setDisable(true);
    }

    /**
     * (i,j). négyzetben lévő módosításokat tisztítja ki
     * @param ilepes a négyzet i. koordinátája
     * @param jlepes a négyzet j. koordinátája
     */
    private void cleanSquare(int ilepes,int jlepes){
        Button b = (Button) map.getChildren().get(ilepes*game.getTablesize()+jlepes+1);
        b.setText("");
        b.setStyle("-fx-background-color:-fx-background");
    }

}
