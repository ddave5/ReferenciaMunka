package hu.adatb.dao;

import hu.adatb.model.Biztosit;

import java.util.List;

public interface BiztositDAO {

    public boolean add(Biztosit b);

    public List<Biztosit> getAll();

    public boolean modify(Biztosit b);

    public boolean delete(Biztosit b);

    public Integer osszbiztositasSzamlalo (String felhasznalonev);

    public Integer legdragabbBiztositasSzamlalo (String felhasznalonev);

    public String legdragabbBiztositasByCeg (String felhasznalonev);
}
