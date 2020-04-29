package hu.adatb.controller;

import hu.adatb.dao.BiztositasDAO;
import hu.adatb.dao.BiztositasDAOImp;
import hu.adatb.model.Biztositas;

import java.util.List;

public class BiztositasController {
    private BiztositasDAO dao = new BiztositasDAOImp();

    public BiztositasController() {

    }

    public boolean add(Biztositas b) {
        return dao.add(b);
    }

    public List<Biztositas> getAll() {
        return dao.getAll();
    }

    public boolean modify(Biztositas b) {return dao.modify(b); }

    public List<Biztositas> selectSpecific(String query){return dao.selectSpecific(query); }
}
