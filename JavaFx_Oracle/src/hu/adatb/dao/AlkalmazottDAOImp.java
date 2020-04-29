package hu.adatb.dao;

import hu.adatb.model.Alkalmazott;
import oracle.jdbc.pool.OracleDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlkalmazottDAOImp implements AlkalmazottDAO{

    private final static String DB_STRING = "oracle.jdbc.OracleDriver";
    private final static String URL = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String INSERT_ALKALMAZOTT = "INSERT INTO Alkalmazott(dolgozoid, nev, titulus, fizetes) VALUES "+ " (?,?,?,?)";
    private static final String SELECT_ALKALMAZOTT= "SELECT * FROM Alkalmazott";
    private static final String UPDATE_ALKALMAZOTT = "UPDATE Alkalmazott SET Alkalmazott.nev = ? , Alkalmazott.titulus = ? , Alkalmazott.fizetes = ? WHERE Alkalmazott.dolgozoid = ?";


    @Override
    public boolean add(Alkalmazott a) {
        try {
            OracleDataSource ods = new OracleDataSource();
            Class.forName (DB_STRING);
            ods.setURL(URL);
            Connection conn = ods.getConnection("SYSTEM","Ora123");
            PreparedStatement stmt = conn.prepareStatement(INSERT_ALKALMAZOTT);
            stmt.setInt(1, a.getDolgozoid());
            stmt.setString(2, a.getNev());
            stmt.setString(3, a.getTitulus());
            stmt.setInt(4,a.getFizetes());

            int res = stmt.executeUpdate();;
            if (res == 1) {
                return true;
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public List<Alkalmazott> getAll() {
        List<Alkalmazott> result = new ArrayList<>();

        try {
            OracleDataSource ods = new OracleDataSource();
            Class.forName(DB_STRING);
            ods.setURL(URL);
            Connection conn = ods.getConnection("SYSTEM", "Ora123");
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(SELECT_ALKALMAZOTT);


            while (rs.next()) {
                    Alkalmazott p = new Alkalmazott(
                            rs.getInt(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getInt(4));
                    result.add(p);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean modify(Alkalmazott a) {
        try {
            OracleDataSource ods = new OracleDataSource();
            Class.forName (DB_STRING);
            ods.setURL(URL);
            Connection conn = ods.getConnection("SYSTEM","Ora123");
            PreparedStatement stmt = conn.prepareStatement(UPDATE_ALKALMAZOTT);
            stmt.setString(1, a.getNev());
            stmt.setString(2, a.getTitulus());
            stmt.setInt(3,a.getFizetes());
            stmt.setInt(4,a.getDolgozoid());

            int res = stmt.executeUpdate();;
            if (res == 1) {
                return true;
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public List<Alkalmazott> selectSpecific(String query) {
        List<Alkalmazott> result = new ArrayList<>();

        try {
            OracleDataSource ods = new OracleDataSource();
            Class.forName(DB_STRING);
            ods.setURL(URL);
            Connection conn = ods.getConnection("SYSTEM", "Ora123");
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                Alkalmazott a = new Alkalmazott(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4));
                result.add(a);
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }
}
