package hu.adatb.dao;

import hu.adatb.model.Foglal;
import hu.adatb.model.Jarat;

import java.util.ArrayList;
import java.util.List;

public interface FoglalDAO {

    public boolean add(Foglal a);

    public List<Foglal> getAll();

    public boolean modify(Foglal a);

    public boolean delete(Foglal f);

    public List<Foglal> getFoglalasokEgyJaraton(Jarat j);

    public Integer osszfoglalasSzamito (String felhasznalonev);

    public Integer legdragabbRepjegySzamito (String felhasznalonev);

    public ArrayList<Integer> legtobbfoglalasEgyRepulon() ;

}
