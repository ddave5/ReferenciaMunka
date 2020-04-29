package hu.alkfejl.DAO;

import hu.alkfejl.Model.Game;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GameDAOImp implements GameDAO{

    private final static String DB_STRING = "jdbc:sqlite:Games.db";
    private static final String CREATE_GAME = "CREATE TABLE IF NOT EXISTS Game (" +
            "id integer PRIMARY KEY AUTOINCREMENT," +
            "name text NOT NULL," +
            "type boolean NOT NULL," +
            "tablesize integer NOT NULL," +
            "timelimit integer NOT NULL," +
            "iMovesPlayer1 text NOT NULL," +
            "jMovesPlayer1 text NOT NULL," +
            "iMovesPlayer2 text NOT NULL," +
            "jMovesPlayer2 text NOT NULL);";

    private static final String INSERT_GAME = "INSERT INTO Game (name, type, tablesize, timelimit, iMovesPlayer1, jMovesPlayer1, iMovesPlayer2, jMovesPlayer2) VALUES " +
            "(?,?,?,?,?,?,?,?);";

    private static final String SELECT_GAME = "SELECT * FROM Game;";

    public void initializeTables() {
        try (Connection conn = DriverManager.getConnection(DB_STRING); Statement st = conn.createStatement()) {
            st.executeUpdate(CREATE_GAME);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public GameDAOImp() {
        initializeTables();
    }

    @Override
    public boolean add(Game g) {
        try (Connection conn = DriverManager.getConnection(DB_STRING); PreparedStatement st = conn.prepareStatement(INSERT_GAME)) {
            st.setString(1, g.getName());
            st.setBoolean(2,g.isPve());
            st.setInt(3, g.getTablesize());
            st.setInt(4, g.getTime());
            st.setString(5,arraylistToString(g.getiMovesPlayer1()));
            st.setString(6,arraylistToString(g.getjMovesPlayer1()));
            st.setString(7,arraylistToString(g.getiMovesPlayer2()));
            st.setString(8,arraylistToString(g.getjMovesPlayer2()));
            int res = st.executeUpdate();
            if (res == 1) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public List<Game> getAll() {
        List<Game> result = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(DB_STRING); Statement st = conn.createStatement()) {
            ResultSet rs = st.executeQuery(SELECT_GAME);

            while (rs.next()) {
                Game g = new Game(
                        rs.getString(2),
                        rs.getBoolean(3),
                        rs.getInt(4),
                        rs.getInt(5),
                        stringToArrayList(rs.getString(6)),
                        stringToArrayList(rs.getString(7)),
                        stringToArrayList(rs.getString(8)),
                        stringToArrayList(rs.getString(9))
                );
                result.add(g);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public String arraylistToString(ArrayList<Integer> arrayList){
        String result = "";
        for(int i : arrayList){
            result+=Integer.toString(i);
            result+=",";
        }
        result = result.substring(0,result.length()-1);
        return result;
    }

    public ArrayList<Integer> stringToArrayList(String string){
        String[] tokens = string.split(",");

        ArrayList<Integer> result = new ArrayList<>();
        for(String s: tokens){
            result.add(Integer.parseInt(s));
        }
        return result;
    }
}
