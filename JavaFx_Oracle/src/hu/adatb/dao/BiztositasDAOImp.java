package hu.adatb.dao;

import hu.adatb.model.Biztositas;
import oracle.jdbc.pool.OracleDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BiztositasDAOImp implements BiztositasDAO{

    private final static String DB_STRING = "oracle.jdbc.OracleDriver";
    private final static String URL = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String INSERT_BIZTOSITAS = "INSERT INTO Biztositas(biztositasid, tipus , ceg , osszeg , idotartam ) VALUES "+ " (?,?,?,?,?)";
    private static final String SELECT_BIZTOSITAS = "SELECT * FROM Biztositas";
    private static final String UPDATE_BIZTOSITAS = "UPDATE Biztositas SET Biztositas.tipus = ? , Biztositas.ceg = ? , Biztositas.osszeg = ? , Biztositas.idotartam = ? WHERE Biztositas.biztositasid = ?";

    @Override
    public boolean add(Biztositas b) {
        try {
            OracleDataSource ods = new OracleDataSource();
            Class.forName (DB_STRING);
            ods.setURL(URL);
            Connection conn = ods.getConnection("SYSTEM","Ora123");
            PreparedStatement stmt = conn.prepareStatement(INSERT_BIZTOSITAS);
            stmt.setInt(1, b.getBiztositasid());
            stmt.setString(2, b.getTipus());
            stmt.setString(3, b.getCeg());
            stmt.setInt(4,b.getOsszeg());
            stmt.setInt(5,b.getIdotartam());

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
    public List<Biztositas> getAll() {
        List<Biztositas> result = new ArrayList<>();

        try {
            OracleDataSource ods = new OracleDataSource();
            Class.forName(DB_STRING);
            ods.setURL(URL);
            Connection conn = ods.getConnection("SYSTEM", "Ora123");
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(SELECT_BIZTOSITAS);

            while (rs.next()) {
                    Biztositas p = new Biztositas(
                            rs.getInt(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getInt(4),
                            rs.getInt(5));
                    result.add(p);
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean modify(Biztositas b) {
        try {
            OracleDataSource ods = new OracleDataSource();
            Class.forName (DB_STRING);
            ods.setURL(URL);
            Connection conn = ods.getConnection("SYSTEM","Ora123");
            PreparedStatement stmt = conn.prepareStatement(UPDATE_BIZTOSITAS);
            stmt.setString(1, b.getTipus());
            stmt.setString(2, b.getCeg());
            stmt.setInt(3,b.getOsszeg());
            stmt.setInt(4,b.getIdotartam());
            stmt.setInt(5, b.getBiztositasid());
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
    public List<Biztositas> selectSpecific(String query) {
        List<Biztositas> result = new ArrayList<>();

        try {
            OracleDataSource ods = new OracleDataSource();
            Class.forName(DB_STRING);
            ods.setURL(URL);
            Connection conn = ods.getConnection("SYSTEM", "Ora123");
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                Biztositas b = new Biztositas(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getInt(5));
                result.add(b);
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }

}
