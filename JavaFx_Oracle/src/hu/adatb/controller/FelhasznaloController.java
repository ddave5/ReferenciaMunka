package hu.adatb.controller;

import hu.adatb.dao.FelhasznaloDAO;
import hu.adatb.dao.FelhasznaloDAOImp;
import hu.adatb.model.Felhasznalo;

import java.util.List;

public class FelhasznaloController {
    private FelhasznaloDAO dao = new FelhasznaloDAOImp();

    public FelhasznaloController() {

    }

    public boolean add(Felhasznalo f) {
        return dao.add(f);
    }

    public List<Felhasznalo> getAll() {
        return dao.getAll();
    }
}
