package sample;

public class Csatorna {

    private int cskod;
    private String nev;


    public int getCskod() {
        return cskod;
    }

    public void setCskod(int cskod) {
        this.cskod = cskod;
    }

    public String getNev() {
        return nev;
    }

    public void setNev(String nev) {
        this.nev = nev;
    }

    @Override
    public String toString() {
        return "Csatorna{" +
                "cskod=" + cskod +
                ", nev='" + nev + '\'' +
                '}';
    }

    public Csatorna() {
    }
}
