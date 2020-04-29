package sample;

public class Koveti {
    String felhasznaloEmail;
    int musorMkod;

    public String getFelhasznaloEmail() {
        return felhasznaloEmail;
    }

    public void setFelhasznaloEmail(String felhasznaloEmail) {
        this.felhasznaloEmail = felhasznaloEmail;
    }

    public int getMusorMkod() {
        return musorMkod;
    }

    public void setMusorMkod(int musorMkod) {
        this.musorMkod = musorMkod;
    }

    public Koveti() {
    }

    public Koveti(String felhasznaloEmail, int musorMkod) {
        this.felhasznaloEmail = felhasznaloEmail;
        this.musorMkod = musorMkod;
    }
}