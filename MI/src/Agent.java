///Félegy,Felegyhazi.David@stud.u-szeged.hu

import game.gmk.GomokuAction;
import game.gmk.GomokuGame;
import game.gmk.GomokuPlayer;

import java.util.*;

public class Agent extends GomokuPlayer {

    private final static int boardLength = 15; //A pálya mérete

    private static int[][] currentboard; // A tábla aktuális állása

    private int depth = 1; // A minimax mélysége

    private Map<String, Integer> scores ; // Heurisztika eltárolása


    public Agent(int color, int[][] board, Random random){
        super(color, board, random);
        //  A kezdeti board beépítése a currentBoard-ba és a scores-ba a heurisztikák megadása
        currentboard = new int[boardLength][boardLength];
        for(int i = 0; i < boardLength; i++){
            for (int j = 0; j < boardLength;j++){
                currentboard[i][j] = board[i][j];
            }
        }
        scores = new HashMap<>();
        scores.put("ooooo",50000);
        scores.put("xoooxx",720);
        scores.put("xooxox",720);
        scores.put("oooox",720);
        scores.put("ooxoo",720);
        scores.put("oooxo",720);
        scores.put("xxoxox",120);
        scores.put("xxxoxx",20);
        scores.put("xoooox",4320);
        scores.put("xxooox",720);
        scores.put("xoxoox",720);
        scores.put("xoooo",720);
        scores.put("oxooo",720);
        scores.put("xxooxx",720);
        scores.put("xoxoxx",120);
        scores.put("xxoxxx",20);

    }
    @Override
    public GomokuAction getAction(GomokuAction prevAction, long[] remainingTimes) {
        //Ellenőrzi, hogy mi kezdtünk-e vagy sem
        if (prevAction != null){
            currentboard[prevAction.i][prevAction.j] = GomokuGame.COLOR_X;
        }

        GomokuAction lepes = findBestMoveByMinimax(GomokuGame.COLOR_O,GomokuGame.COLOR_X); //megadja a legjobb lépést a MiniMax szerint
        currentboard[lepes.i][lepes.j] = GomokuGame.COLOR_O; // Beírja a lépésünket a mátrixba
        return lepes;
    }

    /**
     * A végtelenítésért felelős függvény. Ha az alap > modulo, akkor a megfelelő módon variálja át.
     * @param alap A szám, amit módosít
     * @param modulo A modulo, amellyel leosztva megadja az osztási maradékot
     * @return Egy szám, a megfelelő osztási maradék 0-(modulo-1) közt.
     */
    private int converter(int alap, int modulo){
        if (alap < 0){
            return alap+modulo;
        }
        else{
            return (alap % modulo);
        }
    }

    /**
     * Kiszámolja a teljes mátrixban, hogy mennyire értékes az aktuális állás.
     * @param playerId A mi jelünk (GomokuGame.Color_O)
     * @param opponentId Az ellenfél jele (GomokuGame.COLOR_X)
     * @return Az értéke az aktuális mátrixnak.
     */
    private int evaluatePoints(int playerId, int opponentId) {
            int  totalValue = 0;
            for (int i = 0; i < boardLength; i++) {
                for (int j = 0; j < boardLength; j++) {
                    if (currentboard[i][j] == playerId) {
                        totalValue += this.evaluatePoint(i, j, playerId);
                    } else if (currentboard[i][j] == opponentId) {
                        totalValue -= this.evaluatePoint( i, j, opponentId);
                    }
                }
            }
            return totalValue;
        }

    /**
     * Egy bizonyos négyzet aktuális értéke playerId játékos szemszögéből. Végighalad a sorokon,oszlopokon,fő- és mellékátlón és megadja a megfelelő értéket a heurisztika segítségével.
     * @param row A négyzet sorszáma
     * @param column A négyzet oszlopszáma
     * @param playerId A játékos jele (GomokuGame.Color_O vagy GomokuGame.COLOR_X)
     * @return A négyzet aktuális értéke
     */
    private int evaluatePoint( int row, int column, int playerId) {
            int totalValue = 0;
            //  direction: -
            String pattern = generatePattern( row, column, 0, 1, playerId);
            totalValue += evaluatePattern(pattern);
            //  direction: |
            pattern = generatePattern(row, column, 1, 0, playerId);
            totalValue += evaluatePattern(pattern);
            //  direction: \
            pattern = generatePattern(row, column, 1, 1, playerId);
            totalValue += evaluatePattern(pattern);
            //  direction: /
            pattern = generatePattern(row, column, -1, 1, playerId);
            totalValue += evaluatePattern(pattern);
            return totalValue;
        }

    /**
     * A paraméterminta mennyire értékes a heurisztika szerint.
     * @param pattern A minta, amit ki akarunk értékelni
     * @return A minta értéke a heurisztika szerint.
     */
    public int evaluatePattern(String pattern) {
            int totalValue = 0;
            for (String key : scores.keySet()) {
                if (pattern.contains(key)) {
                    totalValue += scores.get(key);
                }
            }
            return totalValue;
        }

    /**
     *A mi és az ellenfél szempontjából milyen lehetséges minták jöhetnek ki. Minél hosszabb a minta, annál hamarabb legyen az lefedve, még akkor is, ha az ellenfélnek gátoljuk meg ezáltal.
     * @param playerId A mi jelünk (GomokuGame.Color_O)
     * @param opponentId Az ellenfél jele (GomokuGame.COLOR_X)
     * @return Egy List<GomokuAction>, amely tartalmazza a leghosszabb egybefüggő minta lépéseit.
     */
    public List<GomokuAction> generateLegalMoves(int playerId,int  opponentId) {
            List<GomokuAction>  ooooo = new ArrayList<>() ;
            List<GomokuAction>  oooo = new ArrayList<>();
            List<GomokuAction>  ooo = new ArrayList<>();
            List<GomokuAction>  oo = new ArrayList<>();
            List<GomokuAction>  o = new ArrayList<>();
            List<GomokuAction>  x = new ArrayList<>();

            for (int i = 0; i < boardLength; i++) {
                for (int j = 0; j < boardLength; j++) {
                    if (currentboard[i][j] != GomokuGame.EMPTY) {
                        continue;
                    }
                    if (hasAdjacent(i, j)) {
                        String patternI = generateMaxLegalPattern( i, j, playerId);
                        String patternII = generateMaxLegalPattern( i, j, opponentId);

                        if ("ooooo".equals(patternI) || "ooooo".equals(patternII)) {
                            ooooo.add(new GomokuAction(i,j));
                            return ooooo;
                        }
                        if ("oooo".equals( patternI) || "oooo".equals(patternII)) {
                            oooo.add(new GomokuAction(i, j));
                        } else if ("ooo".equals( patternI) || "ooo".equals(patternII)) {
                            ooo.add(new GomokuAction(i, j));
                        } else if ("oo".equals( patternI) || "oo".equals(patternII)) {
                            oo.add(new GomokuAction(i, j));
                        } else if ("o".equals( patternI) || "o".equals(patternII)) {
                            o.add(new GomokuAction(i, j));
                        } else {
                            x.add(new GomokuAction(i, j));
                        }
                    }
                }
            }

            if (oooo.size() > 0) {
                return oooo;
            }
            if (ooo.size() > 0) {
                return ooo;
            }
            oo.addAll(o);
            oo.addAll(x);
            return oo;

        }

    /**
     * Az aktuális néggyzetnek van-e szomszédja.
     * @param row A négyzet sorszáma.
     * @param column A négyzet oszlopszáma
     * @return Igaz, ha van már mellette vagy fal vagy valamelyik játékos jele, egyébként hamis.
     */
    public boolean hasAdjacent(int row, int column) {

            // west
            if (currentboard[converter(row,boardLength)][converter(column+1,boardLength)]  != GomokuGame.EMPTY) {
                return true;
            }
            // east
            if (currentboard[converter(row,boardLength)][converter(column-1,boardLength)] != GomokuGame.EMPTY) {
                return true;
            }
            // north
            if (currentboard[converter(row-1,boardLength)][converter(column,boardLength)] != GomokuGame.EMPTY) {
                return true;
            }

            // south
            if (currentboard[converter(row+1,boardLength)][converter(column,boardLength)] != GomokuGame.EMPTY) {
                return true;
            }

            // east south
            if (currentboard[converter(row+1,boardLength)][converter(column+1,boardLength)] != GomokuGame.EMPTY) {
                return true;
            }

            // west south
            if (currentboard[converter(row+1,boardLength)][converter(column-1,boardLength)] != GomokuGame.EMPTY) {
                return true;
            }

            // west north
            if (currentboard[converter(row-1,boardLength)][converter(column-1,boardLength)] != GomokuGame.EMPTY) {
                return true;
            }

            // east north
            if (currentboard[converter(row-1,boardLength)][converter(column+1,boardLength)] != GomokuGame.EMPTY) {
                return true;
            }
            return false;
        }

    /**
     * Egy pattern kiértékelése az egybefüggőség szerint.
     * @param pattern Egy pattern, amelyben 'x'-ek és 'o'-k állnak. Az 'x'-ek az üres helyeket jelölik.
     * @return A leghosszabb egybefüggő 'o' minta.
     */
    public String generateLegalPattern(String pattern) {
            if (pattern.length() < 5) {
                return "";
            }
            if (pattern.contains("ooooo")) {
                return "ooooo";
            } else if (pattern.contains("oooo")) {
                return "oooo";
            } else if (pattern.contains("ooo")) {
                return "ooo";
            } else if (pattern.contains("oo")) {
                return "oo";
            } else if (pattern.contains("o")) {
                return "o";
            } else {
                return "";
            }
        }

    /**
     * Kiértékeli az aktuális négyzet összes irányában lévő összes pattern-t a playerId játékos szempontjából.
     * Ezután megnézi, hogy mennyire hosszú egybefüggő rész van az adott patternben és a leghosszabbat kiválasztja.
     * @param row Az aktuális négyzet sorszáma.
     * @param column Az aktuális négyzet oszlopszáma.
     * @param playerId A játékos jele.
     * @return A leghosszabb lehetséges pattern az összes pattern közül.
     */
    public String generateMaxLegalPattern(int row,int column,int playerId) {
            String max;
            //  direction: -
            String patternH = generatePattern(row, column, 0, 1, playerId);
            max = generateLegalPattern(patternH);

            //  direction: |
            String patternV = generatePattern(row, column, 1, 0, playerId);
            patternV = generateLegalPattern(patternV);
            if (max.length() < patternV.length()) {
                max = patternV;
            }

            //  direction: \
            String patternLT = generatePattern(row, column, 1, 1, playerId);
            patternLT = generateLegalPattern(patternLT);
            if (max.length() < patternLT.length()) {
                max = patternLT;
            }

            //  direction: /
            String patternRT = generatePattern(row, column, -1, 1, playerId);
            patternRT = generateLegalPattern(patternRT);
            if (max.length() < patternRT.length()) {
                max = patternRT;
            }

            return max;
        }

    /**
     * Végigmegy az aktuális négyzet 4 távolságú környezetén rowStep és columnStep irányában és generál a playerId-ban megadott játékos mintáit.
     * Ez legenerálja az összes olyan lépést, amely nem ütközik falba vagy ellenfél jelébe.
     * @param row Az aktuális négyzet sorszáma.
     * @param column Az aktuális négyzet oszlopszáma.
     * @param rowStep A haladás iránya sorosan.
     * @param columnStep A haladás iránya oszloposan.
     * @param playerId Az aktuális játékos jele.
     * @return A minta, amit az aktuális irányban el lehet érni.
     */
    public String generatePattern(int row, int column, int rowStep, int columnStep,int playerId) {
            String pattern = "o";

            for (int i = 1; i <= 4; i++) {
                int currentRow = converter(row - rowStep * i,boardLength);
                int currentColumn = converter(column - columnStep * i,boardLength);

                if (currentboard[currentRow][currentColumn] == playerId) {
                    pattern = "o" + pattern;
                } else if (currentboard[currentRow][currentColumn] == GomokuGame.EMPTY) {
                    pattern = "x" + pattern;
                } else {
                    break;
                }
            }

            for (int i = 1; i <= 4; i++) {
                int currentRow = converter(row + rowStep * i,boardLength);
                int currentColumn = converter(column + columnStep * i,boardLength);
                if (currentboard[currentRow][currentColumn] == playerId) {
                    pattern += "o";
                } else if (currentboard[currentRow][currentColumn] == GomokuGame.EMPTY) {
                    pattern += "x";
                } else {
                    break;
                }
            }
            return pattern;
        }

    /**
     * Minimax algoritmus. Lásd: https://en.wikipedia.org/wiki/Minimax
     * @param depth Az algoritmus mélysége
     * @param maximizingPlayer Az adott kiértékelés minimum vagy maximum-e
     * @param playerId Az mi jelünk.
     * @param opponentId Az ellenfél jele.
     * @return A számunkra legkedvezőbb lépés értéke.
     */
    public int minimax(int depth,boolean maximizingPlayer,int  playerId, int opponentId) {
            if (depth == 0) {
                return this.evaluatePoints(playerId, opponentId);
            }

            List<GomokuAction> moves = generateLegalMoves( playerId, opponentId);

            // no more move is available, match end
            if (moves.size() < 0) {
                return this.evaluatePoints(playerId, opponentId);
            }

            if (maximizingPlayer) {
                int bestValue = Integer.MIN_VALUE;
                for (int i = 0; i < moves.size(); i++) {
                    currentboard[moves.get(i).i][moves.get(i).j] = playerId;
                    int value = this.minimax(depth - 1, false, playerId, opponentId);
                    bestValue = Math.max(value, bestValue);
                    currentboard[moves.get(i).i][moves.get(i).j] = GomokuGame.EMPTY;
                }
                return bestValue;
            } else {
                int bestValue = Integer.MAX_VALUE;
                for (int i = 0; i < moves.size(); i++) {
                    currentboard[moves.get(i).i][moves.get(i).j] = opponentId;
                    int value = this.minimax(depth - 1, true, playerId, opponentId);
                    bestValue = Math.min(value, bestValue);
                    currentboard[moves.get(i).i][moves.get(i).j] = GomokuGame.EMPTY;
                }
                return bestValue;
            }

        };

    /**
     * Kiértékeli az összes lehetséges lépést, amely a lehető leghosszabb egybefüggő mintát adja, ezeken végigmegy a minimax algoritmussal és a lehető legjobbat kiválasztja.
     * @param playerId A mi jelünk.
     * @param opponentId Az ellenfél jele.
     * @return Az összes lehetséges leghosszabb egybefüggő mintájú értékek közüli legnagyobb értékű lépés.
     */
    public GomokuAction findBestMoveByMinimax(int playerId,int opponentId) {
        int max = Integer.MIN_VALUE;
        int bestMoveIndex = 0;
        List<GomokuAction> moves = generateLegalMoves(playerId, opponentId);
        for (int i = 0; i < moves.size(); i++) {
            currentboard[moves.get(i).i][moves.get(i).j] = playerId;
            int value = this.minimax(this.depth, false, playerId, opponentId);
            currentboard[moves.get(i).i][moves.get(i).j] = GomokuGame.EMPTY;
            if (value > max) {
                max = value;
                bestMoveIndex = i;
            }
        }
        return moves.get(bestMoveIndex);
    }



}
