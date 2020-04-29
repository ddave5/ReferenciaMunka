package hu.adatb.controller;

import hu.adatb.dao.JaratDAO;
import hu.adatb.dao.JaratDAOImp;
import hu.adatb.model.Felhasznalo;
import hu.adatb.model.Jarat;
import javafx.collections.ObservableList;

import java.util.List;

public class JaratController {

    private JaratDAO dao = new JaratDAOImp();

    public JaratController() {

    }

    public boolean add(Jarat f) {
        return dao.add(f);
    }
    public boolean add(String query){return dao.add(query);}

    public List<Jarat> getAll() {
        return dao.getAll();
    }

    public boolean modify(Jarat f) {return dao.modify(f); }

    public boolean modify(String query) {return dao.modify(query);}

    public List<Jarat> selectSpecific(String query){return dao.selectSpecific(query);}

    public void alterDate(){ dao.alterDate(); }

    public ObservableList<String> selectSajatJarat(Felhasznalo f){ return dao.selectSajatJarat(f); }

    public String legforgalmasabbRepterKimutato() { return dao.legforgalmasabbRepterKimutato(); };

    public Integer maxutasKimutato(){return dao.maxutasKimutato(); };

    public Integer legdragabbRepjegyKimutato() {return dao.legdragabbRepjegyKimutato();}

    public Integer legnagyobbAtlagFizetes() {return dao.legnagyobbAtlagFizetes();}


}
