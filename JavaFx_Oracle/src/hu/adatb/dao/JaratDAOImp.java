package hu.adatb.dao;

import hu.adatb.model.Felhasznalo;
import hu.adatb.model.Jarat;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.ObservableList;
import oracle.jdbc.pool.OracleDataSource;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class JaratDAOImp implements JaratDAO {
    private final static String DB_STRING = "oracle.jdbc.OracleDriver";
    private final static String URL = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String INSERT_JARAT = "INSERT INTO Jarat(jaratszam,max_utas,ar,statusz,honnan,hova,indulas,erkezes) VALUES "+ " (?,?,?,?,?,?,?,?)";
    private static final String SELECT_JARAT = "SELECT * FROM Jarat";
    private static final String ALTER_DATE = "ALTER SESSION SET NLS_DATE_FORMAT = 'mm-dd-yyyy HH24:mi:ss'";
    private static final String SELECT_SAJAT_JARAT = "SELECT Jarat.jaratszam, Jarat.statusz, Jarat.honnan, Jarat.hova, Jarat.indulas, Jarat.erkezes, Foglal.helyid, Foglal.osztaly FROM Jarat, Foglal " +
                                                     "WHERE  Foglal.felhasznalonev = ? AND Jarat.jaratszam = Foglal.jaratszam ";
    private static final String SELECT_MAX_REPTERFORGALOM = "select repternev from Repter, Jarat where Jarat.hova = Repter.repternev group by repternev having count(*) = (select max(mycount) from ( select varos, count(varos) mycount from Repter, Jarat where Jarat.hova = Repter.repternev group by varos ))";
    private static final String SELECT_MAX_HELYSZAM = "Select MAX(max_utas) FROM Jarat";
    private static final String SELECT_MAX_AR ="SELECT MAX(ar) FROM Jarat";
    private static final String SELECT_MAX_ATLAGFIZ ="SELECT MAX(AVG(fizetes)) FROM Alkalmazott, Jarat, Dolgozik WHERE Jarat.jaratszam = Dolgozik.jaratszam AND Dolgozik.dolgozoid = Alkalmazott.dolgozoid GROUP BY Jarat.jaratszam";

    @Override
    public boolean add(Jarat j) {
        try {
            OracleDataSource ods = new OracleDataSource();
            Class.forName (DB_STRING);
            ods.setURL(URL);
            Connection conn = ods.getConnection("SYSTEM","Ora123");
            PreparedStatement stmt = conn.prepareStatement(INSERT_JARAT);
            stmt.setInt(1, j.getJaratszam());
            stmt.setInt(2, j.getMax_utas());
            stmt.setInt(3, j.getAr());
            stmt.setString(4, j.getStatusz());
            stmt.setString(5, j.getHonnan());
            stmt.setString(6, j.getHova());
            stmt.setDate(7, (Date) j.getIndulas());
            stmt.setDate(8, (Date) j.getErkezes());

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
    public boolean add(String query) {
        try {
            OracleDataSource ods = new OracleDataSource();
            Class.forName(DB_STRING);
            ods.setURL(URL);
            Connection conn = ods.getConnection("SYSTEM", "Ora123");
            Statement st = conn.createStatement();
            boolean res = st.execute(query);
            if (res){
                return res;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return false;
    }

        @Override
    public List<Jarat> getAll() {
        List<Jarat> result = new ArrayList<>();

        try {
            OracleDataSource ods = new OracleDataSource();
            Class.forName(DB_STRING);
            ods.setURL(URL);
            Connection conn = ods.getConnection("SYSTEM", "Ora123");
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(SELECT_JARAT);

            while (rs.next()) {
                Jarat p = new Jarat(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getDate(7),
                        rs.getDate(8));
                result.add(p);
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean modify(String query) {
        try {
            OracleDataSource ods = new OracleDataSource();
            Class.forName(DB_STRING);
            ods.setURL(URL);
            Connection conn = ods.getConnection("SYSTEM", "Ora123");
            Statement st = conn.createStatement();
            boolean res = st.execute(query);
            if (res){
                return res;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return false;
    }

    @Override
    public boolean modify(Jarat a) {
        return false;
    }

    @Override
    public List<Jarat> selectSpecific(String query) {
        List<Jarat> result = new ArrayList<>();

        try {
            OracleDataSource ods = new OracleDataSource();
            Class.forName(DB_STRING);
            ods.setURL(URL);
            Connection conn = ods.getConnection("SYSTEM", "Ora123");
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {

                Jarat p = new Jarat(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getDate(7),
                        rs.getDate(8));
                result.add(p);
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void alterDate() {
        try {
            OracleDataSource ods = new OracleDataSource();
            Class.forName(DB_STRING);
            ods.setURL(URL);
            Connection conn = ods.getConnection("SYSTEM", "Ora123");
            Statement st = conn.createStatement();
            st.executeQuery(ALTER_DATE);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ObservableList<String> selectSajatJarat(Felhasznalo f) {
        ObservableList<String> result = new SimpleListProperty<>();
        try {
            OracleDataSource ods = new OracleDataSource();
            Class.forName(DB_STRING);
            ods.setURL(URL);
            Connection conn = ods.getConnection("SYSTEM", "Ora123");
            PreparedStatement stmt = conn.prepareStatement(SELECT_SAJAT_JARAT);
            stmt.setString(1,f.getFelhasznalonev());

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String jaratszam = Integer.toString(rs.getInt(1));
                String statusz = rs.getString(2);
                String indhely = rs.getString(3);
                String erkhely = rs.getString(4);
                String indido = new SimpleDateFormat("yyyy. MM. dd. ").format(rs.getDate(5));
                String erkido = new SimpleDateFormat("yyyy. MM. dd.").format(rs.getDate(6));
                String helyid = Integer.toString(rs.getInt(7));
                String osztaly = Integer.toString(rs.getInt(8));

                System.out.println(jaratszam);
                System.out.println(statusz);
                System.out.println(indhely);
                System.out.println(erkhely);
                System.out.println(indido);
                System.out.println(erkido);
                System.out.println(helyid);
                System.out.println(osztaly);

                result.add(jaratszam);
                result.add(statusz);
                result.add(indhely);
                result.add(erkhely);
                result.add(indido);
                result.add(erkido);
                result.add(helyid);
                result.add(osztaly);
            }
            for (String s : result){
                System.out.println(s);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public String legforgalmasabbRepterKimutato() {
        String result = "";

        try {
            OracleDataSource ods = new OracleDataSource();
            Class.forName(DB_STRING);
            ods.setURL(URL);
            Connection conn = ods.getConnection("SYSTEM", "Ora123");
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(SELECT_MAX_REPTERFORGALOM);

            while (rs.next()) {
                result = rs.getString(1);
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Integer maxutasKimutato() {
        Integer result =0;

        try {
            OracleDataSource ods = new OracleDataSource();
            Class.forName(DB_STRING);
            ods.setURL(URL);
            Connection conn = ods.getConnection("SYSTEM", "Ora123");
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(SELECT_MAX_HELYSZAM);

            while (rs.next()) {
                result = rs.getInt(1);
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Integer legdragabbRepjegyKimutato() {
        Integer result =0;

        try {
            OracleDataSource ods = new OracleDataSource();
            Class.forName(DB_STRING);
            ods.setURL(URL);
            Connection conn = ods.getConnection("SYSTEM", "Ora123");
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(SELECT_MAX_AR);

            while (rs.next()) {
                result = rs.getInt(1);
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Integer legnagyobbAtlagFizetes() {
        Integer result =0;

        try {
            OracleDataSource ods = new OracleDataSource();
            Class.forName(DB_STRING);
            ods.setURL(URL);
            Connection conn = ods.getConnection("SYSTEM", "Ora123");
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(SELECT_MAX_ATLAGFIZ);

            while (rs.next()) {
                result = rs.getInt(1);
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }
}
