package hu.adatb.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Alkalmazott {
    private SimpleIntegerProperty dolgozoid = new SimpleIntegerProperty();
    private SimpleStringProperty nev = new SimpleStringProperty();
    private SimpleStringProperty titulus = new SimpleStringProperty();
    private SimpleIntegerProperty fizetes = new SimpleIntegerProperty();

    public Alkalmazott() {
    }

    public Alkalmazott(int dolgozoid, String nev, String titulus, int fizetes) {
        this.dolgozoid.set(dolgozoid);
        this.nev.set(nev);
        this.titulus.set(titulus);
        this.fizetes.set(fizetes);
    }

    public int getDolgozoid() {
        return dolgozoid.get();
    }

    public SimpleIntegerProperty dolgozoidProperty() {
        return dolgozoid;
    }

    public void setDolgozoid(int dolgozoid) {
        this.dolgozoid.set(dolgozoid);
    }

    public String getNev() {
        return nev.get();
    }

    public SimpleStringProperty nevProperty() {
        return nev;
    }

    public void setNev(String nev) {
        this.nev.set(nev);
    }

    public String getTitulus() {
        return titulus.get();
    }

    public SimpleStringProperty titulusProperty() {
        return titulus;
    }

    public void setTitulus(String titulus) {
        this.titulus.set(titulus);
    }

    public int getFizetes() {
        return fizetes.get();
    }

    public SimpleIntegerProperty fizetesProperty() {
        return fizetes;
    }

    public void setFizetes(int fizetes) {
        this.fizetes.set(fizetes);
    }
}
