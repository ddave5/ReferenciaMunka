package hu.adatb.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Biztosit {
    SimpleIntegerProperty biztositasid = new SimpleIntegerProperty();
    SimpleStringProperty felhasznalonev = new SimpleStringProperty();

    public Biztosit(int biztositasid, String felhasznalonev){
        this.biztositasid.set(biztositasid);
        this.felhasznalonev.set(felhasznalonev);
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

    public String getFelhasznalonev() {
        return felhasznalonev.get();
    }

    public SimpleStringProperty felhasznalonevProperty() {
        return felhasznalonev;
    }

    public void setFelhasznalonev(String felhasznalonev) {
        this.felhasznalonev.set(felhasznalonev);
    }
}
