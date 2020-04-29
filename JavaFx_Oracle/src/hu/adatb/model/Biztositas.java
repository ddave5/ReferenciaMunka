package hu.adatb.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Biztositas {
    private SimpleIntegerProperty biztositasid = new SimpleIntegerProperty();
    private SimpleStringProperty tipus = new SimpleStringProperty();
    private SimpleStringProperty ceg = new SimpleStringProperty();
    private SimpleIntegerProperty osszeg = new SimpleIntegerProperty();
    private SimpleIntegerProperty idotartam = new SimpleIntegerProperty();

    public Biztositas() {
    }

    public Biztositas(int biztositasid, String tipus, String ceg, int osszeg, int idotartam) {
        this.biztositasid.set(biztositasid);
        this.tipus.set(tipus);
        this.ceg.set(ceg);
        this.osszeg.set(osszeg);
        this.idotartam.set(idotartam);
    }

    public int getBiztositasid() {
        return biztositasid.get();
    }

    public SimpleIntegerProperty biztositasidProperty() {
        return biztositasid;
    }

    public void setBiztositasid(int biztositasid) {
        this.biztositasid.set(biztositasid);
    }

    public String getTipus() {
        return tipus.get();
    }

    public SimpleStringProperty tipusProperty() {
        return tipus;
    }

    public void setTipus(String tipus) {
        this.tipus.set(tipus);
    }

    public String getCeg() {
        return ceg.get();
    }

    public SimpleStringProperty cegProperty() {
        return ceg;
    }

    public void setCeg(String ceg) {
        this.ceg.set(ceg);
    }

    public int getOsszeg() {
        return osszeg.get();
    }

    public SimpleIntegerProperty osszegProperty() {
        return osszeg;
    }

    public void setOsszeg(int osszeg) {
        this.osszeg.set(osszeg);
    }

    public int getIdotartam() {
        return idotartam.get();
    }

    public SimpleIntegerProperty idotartamProperty() {
        return idotartam;
    }

    public void setIdotartam(int idotartam) {
        this.idotartam.set(idotartam);
    }
}
