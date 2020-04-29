package hu.adatb.dao;

import hu.adatb.model.Biztositas;

import java.util.List;

public interface BiztositasDAO {

    public boolean add(Biztositas a);

    public List<Biztositas> getAll();

    public boolean modify(Biztositas a);

    public List<Biztositas> selectSpecific(String query);
}
