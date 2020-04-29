package hu.adatb.controller;

import hu.adatb.dao.SajatFoglalasDAO;
import hu.adatb.dao.SajatFoglalasDAOImp;
import hu.adatb.model.SajatFoglalas;

import java.util.List;

public class SajatFoglalasController {

    private SajatFoglalasDAO dao = new SajatFoglalasDAOImp();

    public SajatFoglalasController() {
    }

    public List<SajatFoglalas> getAll(String username) {
        return dao.getAll(username);
    }


}
