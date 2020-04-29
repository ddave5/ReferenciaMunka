package hu.adatb.controller;

import hu.adatb.dao.AlkalmazottDAO;
import hu.adatb.dao.AlkalmazottDAOImp;
import hu.adatb.model.Alkalmazott;

import java.util.List;

public class AlkalmazottController {

    private AlkalmazottDAO dao = new AlkalmazottDAOImp();

    public AlkalmazottController() {
    }

    public boolean add(Alkalmazott a) {
        return dao.add(a);
    }

    public List<Alkalmazott> getAll() {
        return dao.getAll();
    }

    public boolean modify(Alkalmazott a) {return dao.modify(a); }

    public List<Alkalmazott> selectSpecific(String query) { return dao.selectSpecific(query); }
}
