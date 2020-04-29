package hu.adatb.dao;

import hu.adatb.model.Felhasznalo;

import java.util.List;

public interface FelhasznaloDAO {

    public boolean add(Felhasznalo f);

    public List<Felhasznalo> getAll();

    public boolean modify(Felhasznalo f);

}
