package hu.adatb.dao;

import hu.adatb.model.Felhasznalo;
import hu.adatb.model.Jarat;
import javafx.collections.ObservableList;

import java.util.List;

public interface JaratDAO {
    public boolean add(Jarat a);

    public boolean add(String query);

    public List<Jarat> getAll();

    public boolean modify(Jarat a);

    public boolean modify(String query);

    public List<Jarat> selectSpecific(String query);

    public void alterDate();

    public ObservableList<String> selectSajatJarat(Felhasznalo f);

    public String legforgalmasabbRepterKimutato();

    public Integer maxutasKimutato();

    public Integer legdragabbRepjegyKimutato();

    public Integer legnagyobbAtlagFizetes();


}
