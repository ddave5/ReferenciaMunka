package sample;

public class Kategoria {
    private int kkod;
    private String knev;

    public int getKkod() {
        return kkod;
    }

    public void setKkod(int kkod) {
        this.kkod = kkod;
    }

    public String getKnev() {
        return knev;
    }

    public void setKnev(String knev) {
        this.knev = knev;
    }

    @Override
    public String toString() {
        return "Kategoria{" +
                "kkod=" + kkod +
                ", knev='" + knev + '\'' +
                '}';
    }

    public Kategoria() {
    }
}
