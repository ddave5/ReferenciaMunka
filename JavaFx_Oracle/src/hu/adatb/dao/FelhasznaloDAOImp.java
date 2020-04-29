package hu.adatb.dao;

import hu.adatb.model.Felhasznalo;
import oracle.jdbc.pool.OracleDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FelhasznaloDAOImp implements FelhasznaloDAO{

    private final static String DB_STRING = "oracle.jdbc.OracleDriver";
    private final static String URL = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String INSERT_FELHASZNALO = "INSERT INTO Felhasznalo(felhasznalonev, email, password, admin, szuletesi_ev) VALUES "+ " (?,?,?,?,?)";
    private static final String SELECT_FELHASZNALO = "SELECT * FROM FELHASZNALO";

    public FelhasznaloDAOImp(){};

    @Override
    public boolean add(Felhasznalo f) {
        try {
            OracleDataSource ods = new OracleDataSource();
            Class.forName (DB_STRING);
            ods.setURL(URL);
            Connection conn = ods.getConnection("SYSTEM","Ora123");
            PreparedStatement stmt = conn.prepareStatement(INSERT_FELHASZNALO);
            stmt.setString(1, f.getFelhasznalonev());
            stmt.setString(2, f.getEmail());
            stmt.setString(3, f.getPassword());
            stmt.setInt(4,0);
            stmt.setInt(5,f.getSzuletesiEv());

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
    public List<Felhasznalo> getAll() {
        List<Felhasznalo> result = new ArrayList<>();

        try  {
            OracleDataSource ods = new OracleDataSource();
            Class.forName(DB_STRING);
            ods.setURL(URL);
            Connection conn = ods.getConnection("SYSTEM","Ora123");
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(SELECT_FELHASZNALO);


            while (rs.next()) {
                if (rs.getInt(4) == 0) {
                    Felhasznalo p = new Felhasznalo(
                            rs.getString(1),
                            rs.getString(2),
                            rs.getString(3),
                            false,
                            rs.getInt(5));
                    result.add(p);
                }
                else {
                    Felhasznalo p = new Felhasznalo(
                            rs.getString(1),
                            rs.getString(2),
                            rs.getString(3),
                            true,
                            rs.getInt(5));
                    result.add(p);
                }

            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean modify(Felhasznalo f) {
        return false;
    }
}
