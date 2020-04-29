package hu.adatb.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Szallas {

    private SimpleStringProperty szallasnev = new SimpleStringProperty();
    private SimpleIntegerProperty szobadij = new SimpleIntegerProperty();

    public Szallas() {
    }

    public Szallas(String szallasnev, int szobadij) {
        this.szallasnev.set(szallasnev);
        this.szobadij.set(szobadij);
    }

    public String getSzallasnev() {
        return szallasnev.get();
    }

    public SimpleStringProperty szallasnevProperty() {
        return szallasnev;
    }

    public void setSzallasnev(String szallasnev) {
        this.szallasnev.set(szallasnev);
    }

    public int getSzobadij() {
        return szobadij.get();
    }

    public SimpleIntegerProperty szobadijProperty() {
        return szobadij;
    }

    public void setSzobadij(int szobadij) {
        this.szobadij.set(szobadij);
    }
}
