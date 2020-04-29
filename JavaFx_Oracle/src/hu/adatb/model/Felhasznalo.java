package hu.adatb.model;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Felhasznalo {


    private SimpleStringProperty felhasznalonev = new SimpleStringProperty();
    private SimpleStringProperty email = new SimpleStringProperty();
    private SimpleStringProperty password = new SimpleStringProperty();
    private SimpleBooleanProperty admin = new SimpleBooleanProperty();
    private SimpleIntegerProperty szuletesiEv = new SimpleIntegerProperty();

    public Felhasznalo() {
    }

    public Felhasznalo(String felhasznalonev, String password) {
        this.felhasznalonev.set(felhasznalonev);
        this.password.set(password);
    }

    public Felhasznalo(String felhasznalonev, String email, String password, Boolean admin, int szuletesiEv) {
        this.felhasznalonev.set(felhasznalonev);
        this.email.set(email);
        this.password.set(password);
        this.admin.set(admin);
        this.szuletesiEv.set(szuletesiEv);
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

    public String getEmail() {
        return email.get();
    }

    public SimpleStringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getPassword() {
        return password.get();
    }

    public SimpleStringProperty passwordProperty() {
        return password;
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public boolean isAdmin() {
        return admin.get();
    }

    public SimpleBooleanProperty adminProperty() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin.set(admin);
    }

    public int getSzuletesiEv() {
        return szuletesiEv.get();
    }

    public SimpleIntegerProperty szuletesiEvProperty() {
        return szuletesiEv;
    }

    public void setSzuletesiEv(int szuletesiEv) {
        this.szuletesiEv.set(szuletesiEv);
    }

    @Override
    public String toString() {
        return "Felhasznalo{" +
                "felhasznalonev=" + felhasznalonev +
                ", email=" + email +
                ", password=" + password +
                ", admin=" + admin +
                ", szuletesiEv=" + szuletesiEv +
                '}';
    }
}
