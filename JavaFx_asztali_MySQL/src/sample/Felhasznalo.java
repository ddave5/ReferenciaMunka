package sample;

public class Felhasznalo {
    private String nev;
    private int szulev;
    private String email;
    private String password;

    public String getNev() {
        return nev;
    }

    public void setNev(String nev) {
        this.nev = nev;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getSzulev() {
        return szulev;
    }

    public void setSzulev(int szulev) {
        this.szulev = szulev;
    }

    public Felhasznalo() {
    }

    public Felhasznalo(String nev, int szulev, String email, String password ) {
        this.nev = nev;
        this.email = email;
        this.password = password;
        this.szulev = szulev;
    }
}
