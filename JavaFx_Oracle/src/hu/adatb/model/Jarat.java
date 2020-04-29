package hu.adatb.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.Date;

public class Jarat {
    private SimpleIntegerProperty jaratszam = new SimpleIntegerProperty();
    private SimpleIntegerProperty max_utas = new SimpleIntegerProperty();
    private SimpleIntegerProperty ar = new SimpleIntegerProperty();
    private SimpleStringProperty statusz = new SimpleStringProperty();
    private SimpleStringProperty honnan = new SimpleStringProperty();
    private SimpleStringProperty hova = new SimpleStringProperty();
    private Date indulas = new Date();
    private Date erkezes = new Date();

    public Jarat() {

    }

    public Jarat(int jaratszam, int max_utas, int ar, String statusz, String honnan, String hova, Date indulas, Date erkezes) {
        this.jaratszam.set(jaratszam);
        this.max_utas.set(max_utas);
        this.ar.set(ar);
        this.statusz.set(statusz);
        this.honnan.set(honnan);
        this.hova.set(hova);
        this.indulas = indulas;
        this.erkezes = erkezes;
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

    public int getMax_utas() {
        return max_utas.get();
    }

    public SimpleIntegerProperty max_utasProperty() {
        return max_utas;
    }

    public void setMax_utas(int max_utas) {
        this.max_utas.set(max_utas);
    }

    public int getAr() {
        return ar.get();
    }

    public SimpleIntegerProperty arProperty() {
        return ar;
    }

    public void setAr(int ar) {
        this.ar.set(ar);
    }

    public String getStatusz() {
        return statusz.get();
    }

    public SimpleStringProperty statuszProperty() {
        return statusz;
    }

    public void setStatusz(String statusz) {
        this.statusz.set(statusz);
    }

    public String getHonnan() {
        return honnan.get();
    }

    public SimpleStringProperty honnanProperty() {
        return honnan;
    }

    public void setHonnan(String honnan) {
        this.honnan.set(honnan);
    }

    public String getHova() {
        return hova.get();
    }

    public SimpleStringProperty hovaProperty() {
        return hova;
    }

    public void setHova(String hova) {
        this.hova.set(hova);
    }

    public Date getIndulas() {
        return indulas;
    }

    public void setIndulas(Date indulas) {
        this.indulas = indulas;
    }

    public Date getErkezes() {
        return erkezes;
    }

    public void setErkezes(Date erkezes) {
        this.erkezes = erkezes;
    }

    @Override
    public String toString() {
        return "Jarat{" +
                "jaratszam=" + jaratszam +
                ", max_utas=" + max_utas +
                ", ar=" + ar +
                ", statusz=" + statusz +
                ", honnan=" + honnan +
                ", hova=" + hova +
                ", indulas=" + indulas +
                ", erkezes=" + erkezes +
                '}';
    }
}
