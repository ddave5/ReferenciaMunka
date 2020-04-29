package hu.adatb.dao;

import hu.adatb.model.Alkalmazott;

import java.util.List;

public interface AlkalmazottDAO {

    public boolean add(Alkalmazott a);

    public List<Alkalmazott> getAll();

    public boolean modify(Alkalmazott a);

    public List<Alkalmazott> selectSpecific(String query);

}
