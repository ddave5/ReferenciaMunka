package hu.alkfejl.View;

import hu.alkfejl.Controller.GameController;
import hu.alkfejl.Model.Game;
import hu.alkfejl.Utils.Utils;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class GamePanel extends Stage {
    private Game game;
    private GameController gameController = new GameController();
    private Timeline timelimit;
    private IntegerProperty startTime ;


    private final int player1num = 1;
    private final int CPUnum = -1;
    private final int player2num = -1;

    private int[][] player1squares;
    private int[][] CPUsquares;
    private int[][] emptysquares;
    private final int winningMove = 9999999;
    private final int openFour = 8888888;
    private final int twoThrees = 7777777;

    private int[] iMax;
    private int[] jMax;
    private int nMax = 0;
    private Random r;
    private boolean CPUTurn = false;
    private boolean player2turn = false;
    private boolean dontetlen = false;
    private boolean gameOver = false;

    private int CPUlastimove = 0;
    private int CPUlastjmove = 0;

    private int lastihint = -1;
    private int lastjhint = -1;

    private int[] nPozicio = new int[4];
    private int[] ADirection = new int[4];
    private int[] w = {0,100,50,25,20,10};
    //w: Az értékelőnél használt súlyozó. Lényege az, hogy próbáljon minél közelebb rakni olyan pontokhoz, ahol
    //az adott játékosnak van már pontja, ezáltal könnyebben kialakítani TwoThree-t, OpenFour-t vagy győztes lépést

    BorderPane mainframe;
    private GridPane map;
    Label timeLeftLabel;


    public GamePanel(Game game) {
        this.game = game;
        player1squares = new int[game.getTablesize()][game.getTablesize()];
        CPUsquares = new int[game.getTablesize()][game.getTablesize()];
        emptysquares = new int[game.getTablesize()][game.getTablesize()];
        startTime = new SimpleIntegerProperty(game.getTime());

        iMax = new int[10*10];
        jMax = new int[10*10];
        construct();
    }


    private void construct() {

        mainframe = new BorderPane();
        mainframe.setPrefSize(1400,900);
        mainframe.getStyleClass().add("mainframe");

        // TOP START //
        HBox topBox = new HBox(520);
        topBox.setPadding(new Insets(50,50,50,50));
        topBox.setMaxSize(1400,200);

        VBox player1Box = new VBox();
        player1Box.setAlignment(Pos.TOP_CENTER);
        Text player1Text = new Text("Player1");
        player1Text.getStyleClass().add("topText");
        Text player1Icon = new Text("X");
        player1Icon.getStyleClass().add("topRedX");
        player1Box.getChildren().addAll(player1Text,player1Icon);

        timeLeftLabel = new Label();
        timeLeftLabel.getStyleClass().add("timeLeft");
        if (game.getTime() == -1) timeLeftLabel.setText("No limit");
        else{
            timeLeftLabel.setText(String.valueOf(game.getTime()));
            timeLeftLabel.textProperty().bind(startTime.asString());
        }

        VBox player2Box = new VBox();
        player2Box.setAlignment(Pos.TOP_CENTER);
        Text player2Text = new Text("");
        if (game.isPve()) player2Text.setText("CPU"); else player2Text.setText("Player2");
        player2Text.getStyleClass().add("topText");
        Text player2Icon = new Text("O");
        player2Icon.getStyleClass().add("topBlueO");
        player2Box.getChildren().addAll(player2Text,player2Icon);

        topBox.getChildren().addAll(player1Box,timeLeftLabel,player2Box);
        mainframe.setTop(topBox);

        // TOP END //

        // LEFT START//

        VBox leftBox = new VBox(50);
        leftBox.setAlignment(Pos.TOP_CENTER);
        leftBox.setMaxSize(300,700);
        leftBox.setPadding(new Insets(50,50,50,50));

        Button hintButton = new Button("Hint");
        hintButton.setPrefSize(200,50);
        hintButton.getStyleClass().add("leftButton");
        hintButton.setOnAction(event -> {
            legjobbPlayerLepesMegado();
            Button b = (Button) map.getChildren().get(lastihint*game.getTablesize()+lastjhint+1);
            if (!gameOver){
                b.setStyle("-fx-background-color:#4B8B3B");
            }
        });

        Button newButton = new Button("New Game");
        newButton.setPrefSize(200,50);
        newButton.getStyleClass().add("leftButton");
        newButton.setOnAction(event -> newGame());

        Button backToMainButton = new Button("Back to main");
        backToMainButton.setPrefSize(200,50);
        backToMainButton.getStyleClass().add("leftButton");
        backToMainButton.setOnAction(event -> {
            new MainPagePanel();
            close();
        });

        Button saveButton = new Button("Save a game");
        saveButton.setPrefSize(200,50);
        saveButton.getStyleClass().add("leftButton");
        saveButton.setOnAction(event -> {
            if (saveGame()) saveButton.setDisable(true);

        });

        Button replayButton = new Button("Replay a game");
        replayButton.setPrefSize(200,50);
        replayButton.getStyleClass().add("leftButton");
        replayButton.setOnAction(event -> {
            try{
                new ReplayPanel();
                close();
            }catch (Exception e){
                Utils.showWarning("Jelenleg nem létezik lementett játék");
            }
        });

        Button exitButton = new Button("Exit game");
        exitButton.setPrefSize(200,50);
        exitButton.getStyleClass().add("leftButton");
        exitButton.setOnAction(event -> close());

        leftBox.getChildren().addAll(hintButton,newButton,backToMainButton,saveButton,replayButton,exitButton);
        mainframe.setLeft(leftBox);

        // LEFT END //

        // CENTER START //

        map = new GridPane();
        map.setMaxSize(700,700);
        map.setGridLinesVisible(true);
        map.getStyleClass().add("mapGridPane");
        newGame();
        mainframe.setCenter(map);
        // CENTER END //

        // FILLER START//
        HBox botFiller = new HBox();
        botFiller.setPrefSize(1400,50);
        botFiller.getStyleClass().add("botFiller");

        HBox rightFiller = new HBox();
        rightFiller.setMaxSize(50,700);
        rightFiller.getStyleClass().add("leftFiller");

        mainframe.setRight(rightFiller);
        mainframe.setBottom(botFiller);

        // FILLER END //

        // SCENE AND STAGE //
        Scene scene = new Scene(mainframe, 1400, 900);
        scene.getStylesheets().add(getClass().getResource("/css/gamePanelStyle.css").toExternalForm());
        this.setScene(scene);
        this.setResizable(false);
        this.setTitle("Amőba");
        this.show();
    }

    /**
     * Visszaállít mindent a kezdeti pontra, és újragenerálja a kezdőpályát illetve minden gombhoz hozzárendeli a Click() és a setCountDown() függvényt
     */
    private void newGame() {
        gameOver = false;
        CPUTurn = false;
        player2turn = false;
        lastihint = -1;
        lastjhint = -1;
        game.getiMovesPlayer1().clear();
        game.getjMovesPlayer1().clear();
        game.getiMovesPlayer2().clear();
        game.getjMovesPlayer2().clear();
        if (map.getChildren() != null){
            Node valami = map.getChildren().get(0);
            map.getChildren().clear();
            map.getChildren().add(valami);
        }
        for (int i = 0; i < game.getTablesize(); i++){
            for (int j = 0; j < game.getTablesize(); j++){
                player1squares[i][j] = 0;
                CPUsquares[i][j] = 0;
                emptysquares[i][j] = 0;
                Button b = new Button("");
                b.setAlignment(Pos.CENTER);
                b.setPrefSize(700.0/game.getTablesize(),
                        700.0/game.getTablesize());

                int finalJ = j;
                int finalI = i;
                b.setOnAction(e->{
                    clearHint();
                    setCountDown();
                    click(finalI, finalJ);
                    map.requestFocus();
                });
                map.add(b,i,j);

            }
        }
        mainframe.setCenter(map);
    }

    /**
     * A Program két részre van szedve: Az első a gép elleni, amelynél ellenőrzi, hogy a gép köre történik-e,
     * meghívja a drawInSquare függvényt, beállítja, hogy az ellenfél következzen
     * és leellenőrzi, hogy a játéknak vége van-e, azaz nyert-e a játékos, vagy döntetlen lett.
     * Ha nem, akkor meghívja a CPUmove() függvényt.
     * A másik fele pedig játékos játékos ellen
     * @param ilepes az i. koordinátája a játékos lépésének
     * @param jlepes a j. koordinátája a játékos lépésének
     */
    private void click(int ilepes, int jlepes){
        if(game.isPve()){
            if (CPUTurn) return;
            if (emptysquares[ilepes][jlepes] !=0) Utils.showWarning("Ez a négyzet nem üres, válasszon egy másikat!");
            emptysquares[ilepes][jlepes] = player1num;
            game.getiMovesPlayer1().add(ilepes);
            game.getjMovesPlayer1().add(jlepes);
            drawInSquare(ilepes,jlepes,player1num);
            CPUTurn = true;

            if(gyoztesLepes(ilepes,jlepes,player1num) == winningMove){
                gameOver = true;
                Utils.showInfo("Player 1 won!");
                for (int i = 0; i < game.getTablesize(); i++){
                    for (int j = 0; j < game.getTablesize(); j++){
                        Button b = (Button) map.getChildren().get(i*game.getTablesize()+j+1);
                        b.setDisable(true);
                    }
                }
            }
            else if (dontetlen) {
                Utils.showInfo("Draw!");
                gameOver = true;
                for (int i = 0; i < game.getTablesize(); i++){
                    for (int j = 0; j < game.getTablesize(); j++){
                        Button b = (Button) map.getChildren().get(i*game.getTablesize()+j+1);
                        b.setDisable(true);
                    }
                }
            }
            else{
                CPUMove();
            }
        }
        else{
            if (player2turn){
                if (emptysquares[ilepes][jlepes] !=0) Utils.showWarning("Ez a négyzet nem üres, válasszon egy másikat!");
                emptysquares[ilepes][jlepes] = player2num;
                game.getiMovesPlayer2().add(ilepes);
                game.getjMovesPlayer2().add(jlepes);
                drawInSquare(ilepes,jlepes,player2num);
                player2turn = false;
                if(gyoztesLepes(ilepes,jlepes,player2num) == winningMove){
                    gameOver = true;
                    Utils.showInfo("Player 2 won!");
                    for (int i = 0; i < game.getTablesize(); i++){
                        for (int j = 0; j < game.getTablesize(); j++){
                            Button b = (Button) map.getChildren().get(i*game.getTablesize()+j+1);
                            b.setDisable(true);
                        }
                    }
                }
                else if (dontetlen) {
                    Utils.showInfo("Draw!");
                    gameOver = true;
                    for (int i = 0; i < game.getTablesize(); i++){
                        for (int j = 0; j < game.getTablesize(); j++){
                            Button b = (Button) map.getChildren().get(i*game.getTablesize()+j+1);
                            b.setDisable(true);
                        }
                    }
                }
            }
            else{
                if (emptysquares[ilepes][jlepes] !=0) Utils.showWarning("Ez a négyzet nem üres, válasszon egy másikat!");
                emptysquares[ilepes][jlepes] = player1num;
                game.getiMovesPlayer1().add(ilepes);
                game.getjMovesPlayer1().add(jlepes);
                drawInSquare(ilepes,jlepes,player1num);
                player2turn = true;

                if(gyoztesLepes(ilepes,jlepes,player1num) == winningMove){
                    gameOver = true;
                    Utils.showInfo("Player 1 won!");
                    for (int i = 0; i < game.getTablesize(); i++){
                        for (int j = 0; j < game.getTablesize(); j++){
                            Button b = (Button) map.getChildren().get(i*game.getTablesize()+j+1);
                            b.setDisable(true);
                        }
                    }
                }
                else if (dontetlen) {
                    Utils.showInfo("Draw!");
                    gameOver = true;
                    for (int i = 0; i < game.getTablesize(); i++){
                        for (int j = 0; j < game.getTablesize(); j++){
                            Button b = (Button) map.getChildren().get(i*game.getTablesize()+j+1);
                            b.setDisable(true);
                        }
                    }
                }
            }
        }

    }

    /**
     * Meghívja a legjobbCPULepesMegado() függvényt, és az alapján lép a program, azaz meghívja a drawInSquare() függvényt az adott lépésre
     * és leellenőrzi, hogy a játéknak vége van-e, azaz nyert-e a gép, vagy döntetlen lett.
     * Ha nem, akkor átadja a lépést a játékosnak.
     */
    private void CPUMove() {
        legjobbCPULepesMegado();
        emptysquares[CPUlastimove][CPUlastjmove]=CPUnum;
        drawInSquare(CPUlastimove,CPUlastjmove,CPUnum);
        game.getiMovesPlayer2().add(CPUlastimove);
        game.getjMovesPlayer2().add(CPUlastjmove);

        if (gyoztesLepes(CPUlastimove,CPUlastjmove,CPUnum)==winningMove){
            Utils.showInfo("CPU won!");
            gameOver = true;
            for (int i = 0; i < game.getTablesize(); i++){
                for (int j = 0; j < game.getTablesize(); j++){
                    Button b = (Button) map.getChildren().get(i*game.getTablesize()+j+1);
                    b.setDisable(true);
                }
            }
        }
        else if (dontetlen) {
            Utils.showInfo("Draw!");
            gameOver = true;
            for (int i = 0; i < game.getTablesize(); i++){
                for (int j = 0; j < game.getTablesize(); j++){
                    Button b = (Button) map.getChildren().get(i*game.getTablesize()+j+1);
                    b.setDisable(true);
                }
            }
        }
        else{
            CPUTurn = false;
        }

    }

    /**
     * Megadja, hogy a jelenlegi helyzetben a játékosnak melyik a legjobb lépése és ezt az értéket beállítja a lastihint és lastjhint értéknek
     * Működése:
     * - Elsőnek kiszámolja a jelenlegi max értékeket mind a CPU-nak, mind a játékosnak
     * - Ha a jelenlegi max értéke a játékosnak -1, akkor az azt jelenti, hogy a pálya teljesen üres, így azt ajánlja fel, hogy tegyen
     * egy négyzetet a közepére
     * - Utána meggnézi, hogy ki van jobb pozícióban. Ha a játékos, akkor azt a négyzetet ajánlja, ahol "támad",
     * azaz megnézi, hogy melyek azok a négyzetek, ahol a játékos a legnagyobb pontot tudja szerezni,
     * és azon négyzetekből a gépnek melyek a "legértékesebbek", és ezeket a négyzeteket eltárolja és egy számlálóval (nMax) megszámolja
     * - Ha a CPU van jobb pozícióban, akkor a játékos "védekezik", azaz megnézi, hogy melyek azok a négyzetek, ahol a gép a legnagyobb
     * pontot tudja szerezni, és azon négyzetekből a játékosnak melyek a "legértékesebbek", és ezeket a négyzeteket eltárolja
     * és egy számlálóval (nMax) megszámolja
     * - végül a kiválasztott négyzetekből pedig választ random egyet.
     */
    private void legjobbPlayerLepesMegado(){
        int maxUserPoint=kiertekelo(player1squares,player1num);
        int maxCPUPoint=kiertekelo(CPUsquares,CPUnum);

        if (maxUserPoint==-1) {
            int center=game.getTablesize()/2;
            player1squares[center][center]=1;
            maxUserPoint=1;
        }

        if (maxUserPoint>=maxCPUPoint) {
            maxCPUPoint=-1;
            for (int i=0;i<game.getTablesize();i++) {
                for (int j=0;j<game.getTablesize();j++) {
                    if (player1squares[i][j]==maxUserPoint) {
                        if (CPUsquares[i][j]>maxCPUPoint) {
                            maxCPUPoint=CPUsquares[i][j];
                            nMax=0;
                        }
                        if (CPUsquares[i][j]==maxCPUPoint) {
                            iMax[nMax]=i;
                            jMax[nMax]=j;
                            nMax++;}
                    }
                }
            }
        }
        else {
            maxUserPoint=-1;
            for (int i=0;i<game.getTablesize();i++) {
                for (int j=0;j<game.getTablesize();j++) {
                    if (CPUsquares[i][j]==maxCPUPoint) {
                        if (player1squares[i][j]>maxUserPoint) {
                            maxUserPoint=player1squares[i][j];
                            nMax=0;}
                        if (player1squares[i][j]==maxUserPoint) {
                            iMax[nMax]=i;
                            jMax[nMax]=j;
                            nMax++;}
                    }
                }
            }
        }
        r = new Random();
        int randomK = r.nextInt(nMax);

        lastihint=iMax[randomK];
        lastjhint=jMax[randomK];
    }

    /**
     * Megadja, hogy a jelenlegi helyzetben a gépnek melyik a legjobb lépése és ezt az értéket beállítja a CPUlastimove és CPUlastjmove értéknek
     * Működése:
     * - Elsőnek kiszámolja a jelenlegi max értékeket mind a CPU-nak, mind a játékosnak
     * - Utána meggnézi, hogy ki van jobb pozícióban. Ha a CPU, akkor ő "támad", azaz megnézi, hogy melyek azok a négyzetek, ahol a gép a legnagyobb
     * pontot tudja szerezni, és azon négyzetekből a játékosnak melyek a "legértékesebbek", és ezeket a négyzeteket eltárolja és egy számlálóval (nMax)
     * megszámolja
     * - Ha a Játékos van jobb pozícióban, akkor a gép "védekezik", azaz megnézi, hogy melyek azok a négyzetek, ahol a játékos a legnagyobb
     * pontot tudja szerezni, és azon négyzetekből a gépnek melyek a "legértékesebbek", és ezeket a négyzeteket eltárolja és egy számlálóval (nMax)
     * megszámolja
     * - végül a kiválasztott négyzetekből pedig választ random egyet.
     */
    private void legjobbCPULepesMegado() {
        int maxUserPoint=kiertekelo(player1squares,player1num);
        int maxCPUPoint=kiertekelo(CPUsquares,CPUnum);


        if (maxCPUPoint>=maxUserPoint) {
            maxUserPoint=-1;
            for (int i=0;i<game.getTablesize();i++) {
                for (int j=0;j<game.getTablesize();j++) {
                    if (CPUsquares[i][j]==maxCPUPoint) {
                        if (player1squares[i][j]>maxUserPoint) {
                            maxUserPoint=player1squares[i][j];
                            nMax=0;
                        }
                        if (player1squares[i][j]==maxUserPoint) {
                            iMax[nMax]=i;
                            jMax[nMax]=j;
                            nMax++;}
                    }
                }
            }
        }
        else {
            maxCPUPoint=-1;
            for (int i=0;i<game.getTablesize();i++) {
                for (int j=0;j<game.getTablesize();j++) {
                    if (player1squares[i][j]==maxUserPoint) {
                        if (CPUsquares[i][j]>maxCPUPoint) {
                            maxCPUPoint=CPUsquares[i][j];
                            nMax=0;}
                        if (CPUsquares[i][j]==maxCPUPoint) {
                            iMax[nMax]=i;
                            jMax[nMax]=j;
                            nMax++;}
                    }
                }
            }
        }
        r = new Random();

        int randomK = r.nextInt(nMax);

        CPUlastimove=iMax[randomK];
        CPUlastjmove=jMax[randomK];
    }

    /**
     * Kiértékeli a lehetséges lépéseket és kiválasztja a lehető legjobb lépést az alábbi módon:
     * -Végignézi az összes négyzetet és leellenőrzi, hogy az üres-e vagy van-e szomszédja az adott (i,j)-nek
     * -Ellenőrzi, hogy az (i,j) választás győztes lépést generálhat-e. Ha igen, akkor az adott (i,j) koordinátát az intmap-ban erre állítja be
     * -Ha nem lehet, akkor (i,j) 5 kockányi sugarában lehet-e csinálni ötös sort (azaz Oszlopban, sorban, főátlóra vagy mellékátlóra kijöhet-e az
     * öt egymást követő ugyanolyan jel.
     * - Ha lehet, akkor az ezekhez számított értékeket hatványozza, ha nem, akkor lenulláza
     * - Végül ezekből az értékekből kiválasztja a legnagyobbat és beállítja az intmap (i,j). értékének.
     * @param intmap egy tablesize*tablesize méretű két dimenziós tömb, ahol tároljuk az értékeket és a végén kiválasztjuk a legnagyobbat
     * @param playerNum az adott játékos kódja (player: 1, CPU: -1)
     * @return a lehető legjobb lépés értéke
     */
    private int kiertekelo(int[][] intmap, int playerNum){
        int result=-1;
        boolean emptymap = true;
        for(int i = 0; i < game.getTablesize(); i++){
            for(int j = 0; j < game.getTablesize(); j++){
                if(intmap[i][j]!=0){
                    emptymap = false;
                }
            }
        }

        if (emptymap){
            dontetlen=false;
        }
        else{
            dontetlen=true;
        }

        for (int i=0;i<game.getTablesize();i++) {
            for (int j=0;j<game.getTablesize();j++) {

                //Hibakezelés
                if (emptysquares[i][j]!=0) {intmap[i][j]=-1; continue;}
                if (!hasNeighbors(i, j)) {intmap[i][j]=-1;continue;}
                //Ha van győztes lépésre lehetőség
                int wp=gyoztesLepes(i,j,playerNum);
                if (wp>0){
                    intmap[i][j]=wp;
                }
                else {
                    int minM=i-4;//Nem kell végigmenni a teljes táblán, hanem elég a kiválasztott pontunk egy kisebb környezetében
                    int minN=j-4;
                    int maxM=i+5;
                    int maxN=j+5;
                    if (minM<0) minM=0;
                    if (minN<0) minN=0;
                    if (maxM>game.getTablesize()) maxM=game.getTablesize();
                    if (maxN>game.getTablesize()) maxN=game.getTablesize();

                    nPozicio[0]=1;
                    int A0=0; //Értékeli az adott irányba (itt oszlopok) mennnyire éri meg rakni.
                    int m=1;  //Léptető
                    while (j+m<maxN  && emptysquares[i][j+m]!=(-playerNum)) { // addig megy, míg nem éri el a sugár végét vagy nem ütközik a másik játékos négyzetébe
                        nPozicio[0]++; // számolja, hogy oszloposan mennyi jelzője lehet az adott játékosnak (ott van már most a pont, vagy oda tud rakni, mert még üres)
                        A0+=w[m]*emptysquares[i][j+m]; //számolja, hogy az oszlopos lépés mennyire "értékes"
                        m++;
                    }
                    if (j+m>=game.getTablesize() || emptysquares[i][j+m]==(-playerNum)){ // ha kiért a pályáról, vagy beleütközött a másik játékos egy pontjába
                        if (emptysquares[i][j+m-1]==playerNum){
                            A0-=(w[m]*playerNum); // csökkentjük az oszlopos lépés "értékét", mert nem tudunk vele jobb irányba győztes lépést generálni
                        }
                        else{
                            A0-=0;
                        }
                    }
                    //Ugyanez oszloposan bal irányba
                    m=1;
                    while (j-m>=minN && emptysquares[i][j-m]!=(-playerNum)) {
                        nPozicio[0]++;
                        A0+=w[m]*emptysquares[i][j-m];
                        m++;
                    }
                    if (j-m<0 || emptysquares[i][j-m]==(-playerNum)) {
                        if (emptysquares[i][j-m+1]==playerNum){
                            A0-=(w[m]*playerNum);
                        }
                        else{
                            A0-=0;
                        }
                    }
                    if (nPozicio[0]>4) dontetlen=false;

                    //SOROKRA
                    nPozicio[1]=1;
                    int A1=0;
                    m=1;
                    while (i+m<maxM  && emptysquares[i+m][j]!=-playerNum) {
                        nPozicio[1]++;
                        A1+=w[m]*emptysquares[i+m][j];
                        m++;
                    }
                    if (i+m>=game.getTablesize() || emptysquares[i+m][j]==-playerNum) {
                        if (emptysquares[i + m - 1][j] == playerNum){
                            A1 -= w[m] * playerNum;
                        }
                        else {
                            A1 -= 0;
                        }
                    }
                    m=1;
                    while (i-m>=minM && emptysquares[i-m][j]!=-playerNum) {
                        nPozicio[1]++;
                        A1+=w[m]*emptysquares[i-m][j];
                        m++;
                    }
                    if (i-m<0 || emptysquares[i-m][j]==-playerNum) {
                        if (emptysquares[i-m+1][j]==playerNum){
                            A1-=(w[m]*playerNum);
                        }
                        else{
                            A1 -= 0;
                        }
                    }
                    if (nPozicio[1]>4) dontetlen=false;

                    //FŐÁTLÓRA
                    nPozicio[2]=1;
                    int A2=0;
                    m=1;
                    while (i+m<maxM  && j+m<maxN  && emptysquares[i+m][j+m]!=-playerNum) {
                        nPozicio[2]++;
                        A2+=w[m]*emptysquares[i+m][j+m];
                        m++;
                    }
                    if (i+m>=game.getTablesize() || j+m>=game.getTablesize() || emptysquares[i+m][j+m]==-playerNum){
                        if(emptysquares[i+m-1][j+m-1]==playerNum){
                            A2-=w[m]*playerNum;
                        }
                        else{
                            A2-=0;
                        }
                    }
                    m=1;
                    while (i-m>=minM && j-m>=minN && emptysquares[i-m][j-m]!=-playerNum) {
                        nPozicio[2]++;
                        A2+=w[m]*emptysquares[i-m][j-m];
                        m++;
                    }
                    if (i-m<0 || j-m<0 || emptysquares[i-m][j-m]==-playerNum) {
                        if(emptysquares[i-m+1][j-m+1]==playerNum){
                            A2-=w[m]*playerNum;
                        }
                        else{
                            A2-=0;
                        }
                    }
                    if (nPozicio[2]>4) dontetlen=false;

                    //MELLÉKÁTLÓRA
                    nPozicio[3]=1;
                    int A3=0;
                    m=1;
                    while (i+m<maxM  && j-m>=minN && emptysquares[i+m][j-m]!=-playerNum) {
                        nPozicio[3]++;
                        A3+=w[m]*emptysquares[i+m][j-m];
                        m++;
                    }
                    if (i+m>=game.getTablesize() || j-m<0 || emptysquares[i+m][j-m]==-playerNum){
                        if (emptysquares[i+m-1][j-m+1]==playerNum){
                            A3-=w[m]*playerNum;
                        }
                        else{
                            A3-=0;
                        }
                    }
                    m=1;
                    while (i-m>=minM && j+m<maxN  && emptysquares[i-m][j+m]!=-playerNum) {
                        nPozicio[3]++;
                        A3+=w[m]*emptysquares[i-m][j+m];
                        m++;
                    }
                    if (i-m<0 || j+m>=game.getTablesize() || emptysquares[i-m][j+m]==-playerNum) {
                        if(emptysquares[i-m+1][j+m-1]==playerNum){
                            A3-=w[m]*playerNum;
                        }
                        else{
                            A3-=0;
                        }
                    }
                    if (nPozicio[3]>4) dontetlen=false;


                    //Ha van lehetőség még arra, hogy az adott irányba (Oszloposan, Sorosan, Főátlóra, mellékátlóra) meglegyen az 5 összefüggő jel (X vagy O)
                    //Akkor abba az irányba mozduljunk el, egyébként pedig nullázuk ki
                    ADirection[0] = (nPozicio[0]>4) ? A0*A0 : 0;
                    ADirection[1] = (nPozicio[1]>4) ? A1*A1 : 0;
                    ADirection[2] = (nPozicio[2]>4) ? A2*A2 : 0;
                    ADirection[3] = (nPozicio[3]>4) ? A3*A3 : 0;

                    //Megkeresi a legnagyobb értéket és átadja az intmapnak
                    A0=0; A1=0;
                    for (int k=0;k<4;k++) {
                        if (ADirection[k]>=A0) {
                            A1=A0;
                            A0=ADirection[k];
                        }
                    }
                    intmap[i][j]=A0+A1;
                }
                //legvégső ellenőrzés: ha az (i,j) választás értéke nagyobb, mint az eddig megtalált érték, akkor vegye át ezt az értéket a függvény megoldása
                if (intmap[i][j]>result) {
                    result=intmap[i][j];
                }
            }
        }
        return result;
    }

    /**
     * Megadja, hogy van-e (i,j)-nek szomszédja
     * @param i sorindex
     * @param j oszlopindex
     * @return igaz, ha van, hamis, ha nincs
     */
    private boolean hasNeighbors(int i, int j) {
        if (j>0 && emptysquares[i][j-1]!=0) return true;
        if (j+1<game.getTablesize() && emptysquares[i][j+1]!=0) return true;
        if (i>0) {
            if (emptysquares[i-1][j]!=0) return true;
            if (j>0 && emptysquares[i-1][j-1]!=0) return true;
            if (j+1<game.getTablesize() && emptysquares[i-1][j+1]!=0) return true;
        }
        if (i+1<game.getTablesize()) {
            if (emptysquares[i+1][j]!=0) return true;
            if (j>0 && emptysquares[i+1][j-1]!=0) return true;
            if (j+1<game.getTablesize() && emptysquares[i+1][j+1]!=0) return true;
        }
        return false;
    }

    /**
     * ellenőrizni az pályán az adott játékosnak az alábbiakat: Van-e olyan lépés, amellyel
     * A.) nyert a játék, azaz kigyűlik az öt jel (winningMove)
     * B.) lehet-e csinálni olyan 4-es sort, amely végé nincs a másik játékosnak pontja (openFour)
     * C.) lehet-e csinálni kettő vagy több olyan 3-as sort amely végén nincs a másik játékosnak pontja (twoThrees)
     * ezenkívül ellenőrzi, hogy tud-e rakni a szomszédos területre (nem lép-e ki a pályáról az a lépés)
     * @param i a játékos i. lépése
     * @param j a játékos j. lépése
     * @param playerNum az adott játékos kódja (player: 1, CPU: -1)
     * @return 9999999, ha van nyertes lépés, 8888888, ha van openFour, 7777777, ha van twoThrees, -1 ha nincs
     */
    private int gyoztesLepes(int i, int j, int playerNum) {
        int result = -1;
        int test3=0;
        int test4=0;


        int ossz=1;
        int checker=1;
        while (j+checker<game.getTablesize()  && emptysquares[i][j+checker] ==playerNum) {ossz++; checker++;}
        int jobbChecker=checker;
        checker=1;
        while (j-checker>=0 && emptysquares[i][j-checker]==playerNum) {ossz++; checker++;}
        int balChecker=checker;
        if (ossz>4) {
            result = winningMove;
            return result;
        }
        boolean vanejobbraLepes=(j+jobbChecker<game.getTablesize() && emptysquares[i][j+jobbChecker]==0); // tud-e rakni jobbra
        boolean vanebalraLepes=(j-balChecker>=0 && emptysquares[i][j-balChecker]==0); // tud-e rakni balra
        if (ossz==4 && (vanejobbraLepes || vanebalraLepes)) test3++;
        if (vanejobbraLepes && vanebalraLepes) {
            if (ossz==4) test4=1;
            if (ossz==3) test3++;
        }
        //SOROKON
        ossz=1;
        checker=1; while (i+checker<game.getTablesize()  && emptysquares[i+checker][j]==playerNum) {ossz++; checker++;}
        jobbChecker=checker;
        checker=1; while (i-checker>=0 && emptysquares[i-checker][j]==playerNum) {ossz++; checker++;}
        balChecker=checker;

        if (ossz>4) {
            result = winningMove;
            return result;
        }
        boolean vanefelLepes=(i+jobbChecker<game.getTablesize() && emptysquares[i+jobbChecker][j]==0);
        boolean vaneleLepes=(i-balChecker>=0 && emptysquares[i-balChecker][j]==0);
        if (ossz==4 && (vanefelLepes || vaneleLepes)) test3++;
        if (vanefelLepes && vaneleLepes) {
            if (ossz==4) test4=1;
            if (ossz==3) test3++;
        }

        //FŐÁLTLÓN
        ossz=1;
        checker=1;
        while (i+checker<game.getTablesize() && j+checker<game.getTablesize() && emptysquares[i+checker][j+checker]==playerNum) {ossz++; checker++;}
        jobbChecker=checker;
        checker=1;
        while (i-checker>=0 && j-checker>=0 && emptysquares[i-checker][j-checker]==playerNum) {ossz++; checker++;}
        balChecker=checker;

        if (ossz>4) {
            result = winningMove;
            return result;
        }
        boolean vanejobbraleLepes=(i+jobbChecker<game.getTablesize() && j+jobbChecker<game.getTablesize() && emptysquares[i+jobbChecker][j+jobbChecker]==0);
        boolean vanebalrafelLepes=(i-balChecker>=0 && j-balChecker>=0 && emptysquares[i-balChecker][j-balChecker]==0);
        if (ossz==4 && (vanejobbraleLepes || vanebalrafelLepes)) test3++;
        if (vanejobbraleLepes && vanebalrafelLepes) {
            if (ossz==4) test4=1;
            if (ossz==3) test3++;
        }

        //MELLÉKÁTLÓN
        ossz=1;
        checker=1;
        while (i+checker<game.getTablesize()  && j-checker>=0 && emptysquares[i+checker][j-checker]==playerNum) {ossz++; checker++;}
        jobbChecker=checker;
        checker=1;
        while (i-checker>=0 && j+checker<game.getTablesize() && emptysquares[i-checker][j+checker]==playerNum) {ossz++; checker++;}
        balChecker=checker;
        if (ossz>4) {
            result = winningMove;
            return result;
        }
        boolean vanebalraleLepes=(i+jobbChecker<game.getTablesize() && j-jobbChecker>=0 && emptysquares[i+jobbChecker][j-jobbChecker]==0);
        boolean vanejobbrafelLepes=(i-balChecker>=0 && j+balChecker<game.getTablesize() && emptysquares[i-balChecker][j+balChecker]==0);
        if (ossz==4 && (vanebalraleLepes || vanejobbrafelLepes)) test3++;
        if (vanebalraleLepes && vanejobbrafelLepes) {
            if (ossz==4) test4=1;
            if (ossz==3) test3++;
        }

        if (test4 == 1){

            result = openFour;
            return result;
        }
        if (test3>=2){

            result = twoThrees;
            return result;
        }
        return result;
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
     * Ha a játékos nem arra a gombra kattintott, amelyet a gép ajánlott fel, akkor eltűnteti a grafikai változásokat
     */
    private void clearHint(){
        if (lastihint != -1 && lastjhint != -1){
            Button b = (Button) map.getChildren().get(lastihint*game.getTablesize()+lastjhint+1);
            b.setStyle("-fx-background-color:#cccccc");
            b.setStyle("-fx-font-size:" + (700*14)/(35*game.getTablesize()));
        }
        else{
            lastihint = -1;
            lastjhint = -1;
        }
    }

    /**
     * Az adott játékot elmenti az adott néven:
     * 1. PvP_Dátum Idő, ha játékos játékos ellen játszott, PvCPU_Dátum Idő, ha játékos gép ellen játszott
     * 2. játék típusa
     * 3. játéktábla mérete
     * 4. időlimit (ha nincs időlimit, akkor -1es érték)
     * 5. az első játékos lépéseinek i. koordinátái
     * 6. az első játékos lépéseinek j. koordinátái
     * 7. az második játékos lépéseinek i. koordinátái
     * 8. az második játékos lépéseinek j. koordinátái
     * @return igaz, ha sikerült a mentés, hamis, ha nem.
     */
    private boolean saveGame() {
        LocalDateTime today = LocalDateTime.now();
        String formattedDate = today.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Task<Boolean> task = new Task<>() {
            @Override
            protected Boolean call() throws Exception {
                if(game.isPve()){
                    Game newGame = new Game(("PvCPU_"+formattedDate),true,game.getTablesize(),game.getTime(),game.getiMovesPlayer1(),game.getjMovesPlayer1(),game.getiMovesPlayer2(),game.getjMovesPlayer2());
                    return gameController.add(newGame);
                }
                else{
                    Game newGame = new Game(("PvP_"+formattedDate),false,game.getTablesize(),game.getTime(),game.getiMovesPlayer1(),game.getjMovesPlayer1(),game.getiMovesPlayer2(),game.getjMovesPlayer2());
                    return gameController.add(newGame);
                }
            }
        };
        Thread saveThread = new Thread(task);
        saveThread.start();
        task.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, event1 ->{
            Boolean result = task.getValue();
            if (result){ Utils.showInfo("Sikeres mentés!"); }
            else Utils.showWarning("Nem sikerült a mentés");
        });
        return true;
    }

    /**
     * Beállít egy időzítőt a game.time paramétere alapján és az első gombnyomáskor elindul és visszaszámol. Ha lejár, a játékos azonnal vesztett.
     */
    private void setCountDown(){
        if (timelimit != null) {
            timelimit.stop();
        }
        startTime.set(game.getTime());
        timelimit = new Timeline();
        timelimit.getKeyFrames().add(
                new KeyFrame(Duration.seconds(game.getTime()+1),
                        new KeyValue(startTime, 0)));
        timelimit.playFromStart();
        timelimit.setOnFinished(event1 -> {
            gameOver = true;
            if (player2turn){
                Utils.showTimesUp("Times's up, Player1 won!");
            }
            else{
                Utils.showTimesUp("Times's up, Player2 won!");
            }

            for (int i = 0; i < game.getTablesize(); i++){
                for (int j = 0; j < game.getTablesize(); j++){
                    Button b = (Button) map.getChildren().get(i*game.getTablesize()+j+1);
                    b.setDisable(true);
                }
            }
        });
    }
}


