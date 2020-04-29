package hu.adatb.dao;

import hu.adatb.model.Foglal;
import hu.adatb.model.Jarat;
import oracle.jdbc.pool.OracleDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FoglalDAOImp implements FoglalDAO {
    private final static String DB_STRING = "oracle.jdbc.OracleDriver";
    private final static String URL = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String INSERT_FOGLAL = "INSERT INTO Foglal(felhasznalonev,jaratszam,helyid,osztaly) VALUES " + " (?,?,?,?) ";
    private static final String SELECT_FOGLAL= "SELECT * FROM Foglal";
    private static final String SELECT_SPECIFIC_FOGLAL = "SELECT * FROM Foglal WHERE Foglal.jaratszam = ?";
    private static final String SELECT_SAJAT_FOGLAL = "SELECT * FROM Foglal WHERE Foglal.felhasznalonev = ?";
    private static final String COUNT_FOGLAL = "SELECT COUNT(*) FROM Foglal WHERE Foglal.felhasznalonev = ?";
    private static final String MAX_FOGLAL = "SELECT MAX(Jarat.ar) FROM Foglal, JARAT WHERE Foglal.felhasznalonev = ? AND Foglal.jaratszam = Jarat.jaratszam";
    private static final String SELECT_SUM_FOGLAL = "SELECT count(*),jaratszam FROM foglal Group by jaratszam having count(*) = (select Max(count(*)) from foglal group by jaratszam)";
    private static final String DELETE_FOGLAL = "DELETE FROM Foglal WHERE Foglal.jaratszam = ? AND Foglal.felhasznalonev = ? AND Foglal.helyid = ?";

    @Override
    public boolean add(Foglal f) {
        try {
            OracleDataSource ods = new OracleDataSource();
            Class.forName (DB_STRING);
            ods.setURL(URL);
            Connection conn = ods.getConnection("SYSTEM","Ora123");
            PreparedStatement stmt = conn.prepareStatement(INSERT_FOGLAL);
            stmt.setString(1, f.getFelhasznalonev());
            stmt.setInt(2, f.getJaratszam());
            stmt.setInt(3, f.getHelyid());
            stmt.setInt(4, f.getOsztaly());

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
    public List<Foglal> getAll() {
        List<Foglal> result = new ArrayList<>();

        try {
            OracleDataSource ods = new OracleDataSource();
            Class.forName(DB_STRING);
            ods.setURL(URL);
            Connection conn = ods.getConnection("SYSTEM", "Ora123");
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(SELECT_FOGLAL);

            while (rs.next()) {
                Foglal p = new Foglal(
                        rs.getString(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getInt(4));
                result.add(p);
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean modify(Foglal a) {
        return false;
    }

    @Override
    public boolean delete(Foglal f) {
        try{
            OracleDataSource ods = new OracleDataSource();
            Class.forName (DB_STRING);
            ods.setURL(URL);
            Connection conn = ods.getConnection("SYSTEM","Ora123");
            PreparedStatement stmt = conn.prepareStatement(DELETE_FOGLAL);
            stmt.setInt(1,f.getJaratszam());
            stmt.setString(2,f.getFelhasznalonev());
            stmt.setInt(3,f.getHelyid());

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
    public List<Foglal> getFoglalasokEgyJaraton(Jarat j) {
        List<Foglal> result = new ArrayList<>();
        try{
            OracleDataSource ods = new OracleDataSource();
            Class.forName (DB_STRING);
            ods.setURL(URL);
            Connection conn = ods.getConnection("SYSTEM","Ora123");
            PreparedStatement stmt = conn.prepareStatement(SELECT_SPECIFIC_FOGLAL);
            stmt.setInt(1,j.getJaratszam());

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Foglal p = new Foglal(
                        rs.getString(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getInt(4));
                result.add(p);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Integer osszfoglalasSzamito(String felhasznalonev) {
        int result = 0;
        try{
            OracleDataSource ods = new OracleDataSource();
            Class.forName (DB_STRING);
            ods.setURL(URL);
            Connection conn = ods.getConnection("SYSTEM","Ora123");
            PreparedStatement stmt = conn.prepareStatement(COUNT_FOGLAL);
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
    public Integer legdragabbRepjegySzamito(String felhasznalonev) {
        int result = 0;
        try{
            OracleDataSource ods = new OracleDataSource();
            Class.forName (DB_STRING);
            ods.setURL(URL);
            Connection conn = ods.getConnection("SYSTEM","Ora123");
            PreparedStatement stmt = conn.prepareStatement(MAX_FOGLAL);
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
    public ArrayList<Integer> legtobbfoglalasEgyRepulon() {
        ArrayList<Integer> result = new ArrayList<>();
        try{
            OracleDataSource ods = new OracleDataSource();
            Class.forName (DB_STRING);
            ods.setURL(URL);
            Connection conn = ods.getConnection("SYSTEM","Ora123");
            PreparedStatement stmt = conn.prepareStatement(SELECT_SUM_FOGLAL);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                result.add(rs.getInt(1));
                result.add(rs.getInt(2));

            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }


}
