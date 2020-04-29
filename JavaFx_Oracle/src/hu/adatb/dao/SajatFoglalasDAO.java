package hu.adatb.dao;

import hu.adatb.model.SajatFoglalas;

import java.util.List;

public interface SajatFoglalasDAO {
    public List<SajatFoglalas> getAll(String username);

}
