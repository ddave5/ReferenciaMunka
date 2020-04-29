package hu.adatb.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.Date;

public class SajatFoglalas {
    private SimpleIntegerProperty jsz = new SimpleIntegerProperty();
    private SimpleStringProperty sta = new SimpleStringProperty();
    private SimpleStringProperty hon = new SimpleStringProperty();
    private SimpleStringProperty hov = new SimpleStringProperty();
    private Date ind = new Date();
    private Date erk = new Date();
    private SimpleIntegerProperty hid = new SimpleIntegerProperty();
    private SimpleIntegerProperty osz = new SimpleIntegerProperty();

    public SajatFoglalas() {

    }

    @Override
    public String toString() {
        return "SajatFoglalas{" +
                "jsz=" + jsz +
                ", sta=" + sta +
                ", hon=" + hon +
                ", hov=" + hov +
                ", ind=" + ind +
                ", erk=" + erk +
                ", hid=" + hid +
                ", osz=" + osz +
                '}';
    }

    public SajatFoglalas(int jaratszam, String statusz, String honnan, String hova, Date indulas, Date erkezes, int helyid, int osztaly){
        this.jsz.set(jaratszam);
        this.sta.set(statusz);
        this.hon.set(honnan);
        this.hov.set(hova);
        this.ind = indulas;
        this.erk = erkezes;
        this.hid.set(helyid);
        this.osz.set(osztaly);
    }

    public int getJsz() {
        return jsz.get();
    }

    public SimpleIntegerProperty jszProperty() {
        return jsz;
    }

    public void setJsz(int jsz) {
        this.jsz.set(jsz);
    }

    public String getSta() {
        return sta.get();
    }

    public SimpleStringProperty staProperty() {
        return sta;
    }

    public void setSta(String sta) {
        this.sta.set(sta);
    }

    public String getHon() {
        return hon.get();
    }

    public SimpleStringProperty honProperty() {
        return hon;
    }

    public void setHon(String hon) {
        this.hon.set(hon);
    }

    public String getHov() {
        return hov.get();
    }

    public SimpleStringProperty hovProperty() {
        return hov;
    }

    public void setHov(String hov) {
        this.hov.set(hov);
    }

    public Date getInd() {
        return ind;
    }

    public void setInd(Date ind) {
        this.ind = ind;
    }

    public Date getErk() {
        return erk;
    }

    public void setErk(Date erk) {
        this.erk = erk;
    }

    public int getHid() {
        return hid.get();
    }

    public SimpleIntegerProperty hidProperty() {
        return hid;
    }

    public void setHid(int hid) {
        this.hid.set(hid);
    }

    public int getOsz() {
        return osz.get();
    }

    public SimpleIntegerProperty oszProperty() {
        return osz;
    }

    public void setOsz(int osz) {
        this.osz.set(osz);
    }
}
