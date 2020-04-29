package hu.alkfejl.Model;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.ArrayList;

public class Game {
    private SimpleStringProperty name = new SimpleStringProperty();
    private SimpleBooleanProperty pve =  new SimpleBooleanProperty();
    private SimpleIntegerProperty tablesize = new SimpleIntegerProperty();
    private SimpleIntegerProperty time = new SimpleIntegerProperty();
    private ArrayList<Integer> iMovesPlayer1 = new ArrayList<>();
    private ArrayList<Integer> jMovesPlayer1 = new ArrayList<>();
    private ArrayList<Integer> iMovesPlayer2 = new ArrayList<>();
    private ArrayList<Integer> jMovesPlayer2 = new ArrayList<>();

    public Game() {
    }

    public Game(boolean pve, int tablesize, int time) {
        this.pve.set(pve);
        this.tablesize.set(tablesize);
        this.time.set(time);
        this.iMovesPlayer1 = new ArrayList<>();
        this.iMovesPlayer2 = new ArrayList<>();
    }

    public Game(String name,
                boolean pve,
                int tablesize,
                int time,
                ArrayList<Integer> iMovesPlayer1,
                ArrayList<Integer> jMovesPlayer1,
                ArrayList<Integer> iMovesPlayer2,
                ArrayList<Integer> jMovesPlayer2) {
        this.name.set(name);
        this.pve.set(pve);
        this.tablesize.set(tablesize);
        this.time.set(time);
        this.iMovesPlayer1 = iMovesPlayer1;
        this.jMovesPlayer1 = jMovesPlayer1;
        this.iMovesPlayer2 = iMovesPlayer2;
        this.jMovesPlayer2 = jMovesPlayer2;
    }


    public ArrayList<Integer> getjMovesPlayer1() {
        return jMovesPlayer1;
    }

    public void setjMovesPlayer1(ArrayList<Integer> jMovesPlayer1) {
        this.jMovesPlayer1 = jMovesPlayer1;
    }

    public ArrayList<Integer> getjMovesPlayer2() {
        return jMovesPlayer2;
    }

    public void setjMovesPlayer2(ArrayList<Integer> jMovesPlayer2) {
        this.jMovesPlayer2 = jMovesPlayer2;
    }

    public ArrayList<Integer> getiMovesPlayer1() {
        return iMovesPlayer1;
    }

    public void setiMovesPlayer1(ArrayList<Integer> iMovesPlayer1) {
        this.iMovesPlayer1 = iMovesPlayer1;
    }

    public ArrayList<Integer> getiMovesPlayer2() {
        return iMovesPlayer2;
    }

    public void setiMovesPlayer2(ArrayList<Integer> iMovesPlayer2) {
        this.iMovesPlayer2 = iMovesPlayer2;
    }

    public boolean isPve() {
        return pve.get();
    }

    public SimpleBooleanProperty pveProperty() {
        return pve;
    }

    public void setPve(boolean pve) {
        this.pve.set(pve);
    }

    public int getTablesize() {
        return tablesize.get();
    }

    public SimpleIntegerProperty tablesizeProperty() {
        return tablesize;
    }

    public void setTablesize(int tablesize) {
        this.tablesize.set(tablesize);
    }

    public int getTime() {
        return time.get();
    }

    public SimpleIntegerProperty timeProperty() {
        return time;
    }

    public void setTime(int time) {
        this.time.set(time);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    @Override
    public String toString() {
        return "Game{" +
                "name=" + name +
                ", pve=" + pve +
                ", tablesize=" + tablesize +
                ", time=" + time +
                ", iMovesPlayer1=" + iMovesPlayer1 +
                ", jMovesPlayer1=" + jMovesPlayer1 +
                ", iMovesPlayer2=" + iMovesPlayer2 +
                ", jMovesPlayer2=" + jMovesPlayer2 +
                '}';
    }
}
