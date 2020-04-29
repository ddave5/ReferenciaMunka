package hu.adatb.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Foglal {
    /*felhasznalonev VARCHAR2(30) NOT NULL,
    jaratszam NUMBER(6) NOT NULL,
    helyid NUMBER(3) NOT NULL,
    osztaly NUMBER(1) NOT NULL,*/

    SimpleStringProperty felhasznalonev = new SimpleStringProperty();
    SimpleIntegerProperty jaratszam = new SimpleIntegerProperty();
    SimpleIntegerProperty helyid = new SimpleIntegerProperty();
    SimpleIntegerProperty osztaly = new SimpleIntegerProperty();

    public Foglal(String felhasznalonev, int jaratszam, int helyid, int osztaly){
        this.felhasznalonev.set(felhasznalonev);
        this.jaratszam.set(jaratszam);
        this.helyid.set(helyid);
        this.osztaly.set(osztaly);
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

    public int getJaratszam() {
        return jaratszam.get();
    }

    public SimpleIntegerProperty jaratszamProperty() {
        return jaratszam;
    }

    public void setJaratszam(int jaratszam) {
        this.jaratszam.set(jaratszam);
    }

    public int getHelyid() {
        return helyid.get();
    }

    public SimpleIntegerProperty helyidProperty() {
        return helyid;
    }

    public void setHelyid(int helyid) {
        this.helyid.set(helyid);
    }

    public int getOsztaly() {
        return osztaly.get();
    }

    public SimpleIntegerProperty osztalyProperty() {
        return osztaly;
    }

    public void setOsztaly(int osztaly) {
        this.osztaly.set(osztaly);
    }

    @Override
    public String toString() {
        return "Foglal{" +
                "felhasznalonev=" + felhasznalonev +
                ", jaratszam=" + jaratszam +
                ", helyid=" + helyid +
                ", osztaly=" + osztaly +
                '}';
    }
}
