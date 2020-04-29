package hu.adatb.dao;

import hu.adatb.model.Biztosit;
import oracle.jdbc.pool.OracleDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BiztositDAOImp implements BiztositDAO {
    private final static String DB_STRING = "oracle.jdbc.OracleDriver";
    private final static String URL = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String INSERT_BIZTOSIT = "INSERT INTO Biztosit(biztositasid, felhasznalonev ) VALUES "+ " (?,?)";
    private static final String SELECT_BIZTOSIT= "SELECT * FROM Biztosit";
    private static final String COUNT_BIZTOSIT = "SELECT COUNT(*) FROM Biztosit WHERE Biztosit.felhasznalonev = ?";
    private static final String MAX_BIZTOSIT = "SELECT MAX(Biztositas.osszeg) FROM Biztosit, Biztositas WHERE Biztosit.felhasznalonev = ? AND Biztosit.biztositasid = Biztositas.biztositasid";
    private static final String DELETE_BIZTOSIT = "DELETE FROM Biztosit WHERE Biztosit.biztositasid = ? AND Biztosit.felhasznalonev = ?";
    private static final String MAX_CEG_BY_FH = "select ceg from Felhasznalo, Biztositas, Biztosit where Felhasznalo.felhasznalonev = Biztosit.felhasznalonev and Biztosit.biztositasid = Biztositas.biztositasid and Felhasznalo.felhasznalonev like ? and osszeg = (select max(osszeg) from Felhasznalo, Biztositas, Biztosit where Felhasznalo.felhasznalonev = Biztosit.felhasznalonev and Biztosit.biztositasid = Biztositas.biztositasid and Felhasznalo.felhasznalonev like ?) and Biztositas.biztositasid = (select min(Biztositas.biztositasid) from Felhasznalo, Biztositas, Biztosit where Felhasznalo.felhasznalonev = Biztosit.felhasznalonev and Biztosit.biztositasid = Biztositas.biztositasid and Felhasznalo.felhasznalonev like ? and osszeg = (select max(osszeg) from Felhasznalo, Biztositas, Biztosit where Felhasznalo.felhasznalonev = Biztosit.felhasznalonev and Biztosit.biztositasid = Biztositas.biztositasid and Felhasznalo.felhasznalonev like ?))";


    @Override
    public boolean add(Biztosit b) {
        try {
            OracleDataSource ods = new OracleDataSource();
            Class.forName (DB_STRING);
            ods.setURL(URL);
            Connection conn = ods.getConnection("SYSTEM","Ora123");
            PreparedStatement stmt = conn.prepareStatement(INSERT_BIZTOSIT);
            stmt.setInt(1, b.getBiztositasid());
            stmt.setString(2, b.getFelhasznalonev());

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
    public List<Biztosit> getAll() {
        List<Biztosit> result = new ArrayList<>();

        try {            OracleDataSource ods = new OracleDataSource();
            Class.forName(DB_STRING);
            ods.setURL(URL);
            Connection conn = ods.getConnection("SYSTEM", "Ora123");
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(SELECT_BIZTOSIT);

            while (rs.next()) {
                Biztosit p = new Biztosit(
                        rs.getInt(1),
                        rs.getString(2));
                result.add(p);
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean modify(Biztosit b) {
        return false;
    }

    @Override
    public boolean delete(Biztosit b) {
        try{
            OracleDataSource ods = new OracleDataSource();
            Class.forName (DB_STRING);
            ods.setURL(URL);
            Connection conn = ods.getConnection("SYSTEM","Ora123");
            PreparedStatement stmt = conn.prepareStatement(DELETE_BIZTOSIT);
            stmt.setInt(1,b.getBiztositasid());
            stmt.setString(2,b.getFelhasznalonev());

            int result = stmt.executeUpdate();

            while (result == 1) {

                return true;
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Integer osszbiztositasSzamlalo(String felhasznalonev) {
        int result = 0;
        try{
            OracleDataSource ods = new OracleDataSource();
            Class.forName (DB_STRING);
            ods.setURL(URL);
            Connection conn = ods.getConnection("SYSTEM","Ora123");
            PreparedStatement stmt = conn.prepareStatement(COUNT_BIZTOSIT);
            stmt.setString(1,felhasznalonev);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                result = rs.getInt(1);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Integer legdragabbBiztositasSzamlalo(String felhasznalonev) {
        int result = 0;
        try{
            OracleDataSource ods = new OracleDataSource();
            Class.forName (DB_STRING);
            ods.setURL(URL);
            Connection conn = ods.getConnection("SYSTEM","Ora123");
            PreparedStatement stmt = conn.prepareStatement(MAX_BIZTOSIT);
            stmt.setString(1,felhasznalonev);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                result = rs.getInt(1);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public String legdragabbBiztositasByCeg(String felhasznalonev) {
        String result = "";
        try{
            OracleDataSource ods = new OracleDataSource();
            Class.forName (DB_STRING);
            ods.setURL(URL);
            Connection conn = ods.getConnection("SYSTEM","Ora123");
            PreparedStatement stmt = conn.prepareStatement(MAX_CEG_BY_FH);
            stmt.setString(1,felhasznalonev);
            stmt.setString(2,felhasznalonev);
            stmt.setString(3,felhasznalonev);
            stmt.setString(4,felhasznalonev);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                result = rs.getString(1);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

}
