package sample;

public class Musor {
    private int nap;
    private int ora;
    private int perc;

    private String nev;
    private int mkod;
    private String leiras;
    private int jatekido;

    public int getNap() {
        return nap;
    }

    public void setNap(int nap) {
        this.nap = nap;
    }

    public int getOra() {
        return ora;
    }

    public void setOra(int ora) {
        this.ora = ora;
    }

    public int getPerc() {
        return perc;
    }

    public void setPerc(int perc) {
        this.perc = perc;
    }

    public String getNev() {
        return nev;
    }

    public void setNev(String nev) {
        this.nev = nev;
    }

    public int getMkod() {
        return mkod;
    }

    public void setMkod(int mkod) {
        this.mkod = mkod;
    }

    public String getLeiras() {
        return leiras;
    }

    public void setLeiras(String leiras) {
        this.leiras = leiras;
    }

    public int getJatekido() {
        return jatekido;
    }

    public void setJatekido(int jatekido) {
        this.jatekido = jatekido;
    }

    public Musor(int nap, int ora, int perc, String nev, String leiras, int jatekido) {
        this.nap = nap;
        this.ora = ora;
        this.perc = perc;
        this.nev = nev;
        this.leiras = leiras;
        this.jatekido = jatekido;
    }

    public Musor(int nap, int ora, int perc, String nev, int mkod, String leiras, int jatekido) {
        this.nap = nap;
        this.ora = ora;
        this.perc = perc;
        this.nev = nev;
        this.mkod = mkod;
        this.leiras = leiras;
        this.jatekido = jatekido;
    }

    @Override
    public String toString() {
        return "Musor{" +
                "nap='" + nap + '\'' +
                ", ora=" + ora +
                ", perc=" + perc +
                ", nev='" + nev + '\'' +
                ", mkod=" + mkod +
                ", leiras='" + leiras + '\'' +
                ", jatekido=" + jatekido +
                '}';
    }

    public Musor() {
    }
}
