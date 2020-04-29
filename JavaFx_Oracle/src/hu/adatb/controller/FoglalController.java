package hu.adatb.controller;

import hu.adatb.dao.FoglalDAO;
import hu.adatb.dao.FoglalDAOImp;
import hu.adatb.model.Foglal;
import hu.adatb.model.Jarat;

import java.util.ArrayList;
import java.util.List;

public class FoglalController {

    private FoglalDAO dao = new FoglalDAOImp();

    public FoglalController() {

    }

    public boolean add(Foglal f) {
        return dao.add(f);
    }

    public List<Foglal> getAll() {
        return dao.getAll();
    }

    public boolean modify(Foglal f) {return dao.modify(f); }

    public boolean delete(Foglal f){return dao.delete(f); }

    public List<Foglal> getFoglalasokEgyJaraton(Jarat j){ return dao.getFoglalasokEgyJaraton(j); }

    public Integer osszfoglalasSzamito(String felhasznalonev){ return  dao.osszfoglalasSzamito(felhasznalonev);   }

    public Integer legdragabbRepjegySzamito(String felhasznalonev) { return dao.legdragabbRepjegySzamito(felhasznalonev); }

    public ArrayList<Integer> legtobbfoglalasEgyRepulon() {return dao.legtobbfoglalasEgyRepulon();}
}
