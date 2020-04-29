package hu.alkfejl.DAO;

import hu.alkfejl.Model.Game;

import java.util.List;

public interface GameDAO {

    public boolean add(Game g);

    public List<Game> getAll();
}
