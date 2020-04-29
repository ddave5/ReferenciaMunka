package sample;

public class Tematika {

    private int tkod;
    private String tnev;

    public int getTkod() {
        return tkod;
    }

    public void setTkod(int tkod) {
        this.tkod = tkod;
    }

    public String getTnev() {
        return tnev;
    }

    public void setTnev(String tnev) {
        this.tnev = tnev;
    }

    @Override
    public String toString() {
        return "Tematika{" +
                "tkod=" + tkod +
                ", tnev='" + tnev + '\'' +
                '}';
    }

    public Tematika() {
    }
}
