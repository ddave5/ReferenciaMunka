package hu.adatb.controller;

import hu.adatb.dao.BiztositDAO;
import hu.adatb.dao.BiztositDAOImp;
import hu.adatb.model.Biztosit;

import java.util.List;

public class BiztositController {

    private BiztositDAO dao = new BiztositDAOImp();

    public BiztositController() {

    }

    public boolean add(Biztosit b) {
        return dao.add(b);
    }

    public List<Biztosit> getAll() {
        return dao.getAll();
    }

    public boolean modify(Biztosit b) {return dao.modify(b); }

    public boolean delete(Biztosit b) {return dao.delete(b);}

    public Integer osszbiztositasSzamlalo (String felhasznalonev) { return dao.osszbiztositasSzamlalo(felhasznalonev); }

    public Integer legdragabbBiztositasSzamlalo (String felhasznalonev) { return dao.legdragabbBiztositasSzamlalo(felhasznalonev); }

    public String legdragabbBiztositasByCeg (String felhasznalonev){ return dao.legdragabbBiztositasByCeg(felhasznalonev); }
}
