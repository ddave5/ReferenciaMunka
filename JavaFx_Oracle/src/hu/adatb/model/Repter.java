package hu.adatb.model;

import javafx.beans.property.SimpleStringProperty;

public class Repter {
    private SimpleStringProperty repternev = new SimpleStringProperty();
    private SimpleStringProperty varos = new SimpleStringProperty();
    private SimpleStringProperty orszag = new SimpleStringProperty();

    public Repter() {
    }

    public Repter(String repternev, String varos, String orszag) {
        this.repternev.set(repternev);
        this.varos.set(varos);
        this.orszag.set(orszag);
    }

    public String getRepternev() {
        return repternev.get();
    }

    public SimpleStringProperty repternevProperty() {
        return repternev;
    }

    public void setRepternev(String repternev) {
        this.repternev.set(repternev);
    }

    public String getVaros() {
        return varos.get();
    }

    public SimpleStringProperty varosProperty() {
        return varos;
    }

    public void setVaros(String varos) {
        this.varos.set(varos);
    }

    public String getOrszag() {
        return orszag.get();
    }

    public SimpleStringProperty orszagProperty() {
        return orszag;
    }

    public void setOrszag(String orszag) {
        this.orszag.set(orszag);
    }
}
