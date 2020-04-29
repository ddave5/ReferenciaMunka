package hu.adatb.dao;

import hu.adatb.model.SajatFoglalas;
import oracle.jdbc.pool.OracleDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SajatFoglalasDAOImp implements SajatFoglalasDAO {

    private final static String DB_STRING = "oracle.jdbc.OracleDriver";
    private final static String URL = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String SELECT_SAJAT = "SELECT * FROM table(sajatfoglalasok(?))";

    @Override
    public List<SajatFoglalas> getAll(String username) {
        List<SajatFoglalas> result = new ArrayList<>();

        try {
            OracleDataSource ods = new OracleDataSource();
            Class.forName(DB_STRING);
            ods.setURL(URL);
            Connection conn = ods.getConnection("SYSTEM", "Ora123");
            PreparedStatement st = conn.prepareStatement(SELECT_SAJAT);
            st.setString(1,username);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                SajatFoglalas sf = new SajatFoglalas(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getDate(5),
                        rs.getDate(6),
                        rs.getInt(7),
                        rs.getInt(8)
                        );
                result.add(sf);
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }

}
