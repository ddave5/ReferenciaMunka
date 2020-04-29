package hu.adatb.model;

import javafx.beans.property.SimpleStringProperty;

public class Legitarsasag {
    private SimpleStringProperty legitarsasagnev = new SimpleStringProperty();

    public Legitarsasag(String legitarsasagnev) {
        this.legitarsasagnev.set(legitarsasagnev);
    }

    public Legitarsasag() {
    }

    public String getLegitarsasagnev() {
        return legitarsasagnev.get();
    }

    public SimpleStringProperty legitarsasagnevProperty() {
        return legitarsasagnev;
    }

    public void setLegitarsasagnev(String legitarsasagnev) {
        this.legitarsasagnev.set(legitarsasagnev);
    }


}
