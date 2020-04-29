package hu.alkfejl.Controller;

import hu.alkfejl.DAO.GameDAO;
import hu.alkfejl.DAO.GameDAOImp;
import hu.alkfejl.Model.Game;

import java.util.List;

public class GameController {
    GameDAO dao = new GameDAOImp();

    public GameController() {

    }

    public boolean add(Game g){return dao.add(g);}

    public List<Game> getAll(){return dao.getAll();}
}
