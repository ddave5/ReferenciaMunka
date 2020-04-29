package sample;
import javafx.scene.control.Alert;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;


public class ConnectionUtils {



    public class Koveti {
        String felhasznaloEmail;
        int musorMkod;

        public String getFelhasznaloEmail() {
            return felhasznaloEmail;
        }

        public void setFelhasznaloEmail(String felhasznaloEmail) {
            this.felhasznaloEmail = felhasznaloEmail;
        }

        public int getMusorMkod() {
            return musorMkod;
        }

        public void setMusorMkod(int musorMkod) {
            this.musorMkod = musorMkod;
        }

        public Koveti() {
        }

        public Koveti(String felhasznaloEmail, int musorMkod) {
            this.felhasznaloEmail = felhasznaloEmail;
            this.musorMkod = musorMkod;
        }
    }

    private static final String URL = "jdbc:mysql://localhost:3306/musorujsag2";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    private static Connection kapcsolat = null;

    public ConnectionUtils() {
    }


    public void ujFelhasznalo(Felhasznalo f) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            ConnectionUtils.kapcsolat = DriverManager.getConnection(URL, USER, PASSWORD);
            String query = "INSERT INTO FELHASZNALO (nev, szulev, email, jelszo) VALUES (?,?,?,?)";
            PreparedStatement preparedStatement = kapcsolat.prepareStatement(query);
            preparedStatement.setString(1, f.getNev());
            preparedStatement.setInt(2, f.getSzulev());
            preparedStatement.setString(3, f.getEmail());
            preparedStatement.setString(4, f.getPassword());

            preparedStatement.executeUpdate();
            kapcsolat.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Felhasznalo> listFelhasznalo() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            ConnectionUtils.kapcsolat = DriverManager.getConnection(URL, USER, PASSWORD);
            ArrayList<Felhasznalo> list = new ArrayList<Felhasznalo>();
            String sqlQuery = "SELECT felhasznalo.nev, felhasznalo.szulev, felhasznalo.email, felhasznalo.jelszo FROM Felhasznalo;";
            Statement st = kapcsolat.createStatement();
            ResultSet rs = st.executeQuery(sqlQuery);
            String nev;
            int szulev;
            String email;
            String password;
            while (rs.next()) {
                nev = rs.getString("nev");
                szulev = rs.getInt("szulev");
                email = rs.getString("email");
                password = rs.getString("jelszo");

                list.add(new Felhasznalo(nev, szulev, email, password));
            }

            kapcsolat.close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<Musor> listDayMusor(String csatorna, String nap) {
        /*
        private int nap;
        private int ora;
        private int perc;

        private String nev;
        private int mkod;
        private String leiras;
        private int jatekido;*/
        ArrayList<Musor> list = new ArrayList<>();
        String sqlQuery = "";
        switch (csatorna) {
            case ("RTL"):
                switch (nap) {
                    case ("Hétfő"):
                        sqlQuery = "SELECT musor.cim, musor.leiras, musor.jatekido, musor.mkod, sugarozza.nap, sugarozza.ora, sugarozza.perc FROM musor,sugarozza, csatorna WHERE sugarozza.csatorna_cskod = 2 AND musor.mkod = sugarozza.musor_mkod AND csatorna.cskod = sugarozza.csatorna_cskod AND Sugarozza.nap = 1;";
                        break;
                    case ("Kedd"):
                        sqlQuery = "SELECT musor.cim, musor.leiras, musor.jatekido, musor.mkod, sugarozza.nap, sugarozza.ora, sugarozza.perc FROM musor,sugarozza, csatorna WHERE sugarozza.csatorna_cskod = 2 AND musor.mkod = sugarozza.musor_mkod AND csatorna.cskod = sugarozza.csatorna_cskod AND Sugarozza.nap = 2;";
                        break;
                    case ("Szerda"):
                        sqlQuery = "SELECT musor.cim, musor.leiras, musor.jatekido, musor.mkod, sugarozza.nap, sugarozza.ora, sugarozza.perc FROM musor,sugarozza, csatorna WHERE sugarozza.csatorna_cskod = 2 AND musor.mkod = sugarozza.musor_mkod AND csatorna.cskod = sugarozza.csatorna_cskod AND Sugarozza.nap = 3;";
                        break;
                    case ("Csütörtök"):
                        sqlQuery = "SELECT musor.cim, musor.leiras, musor.jatekido, musor.mkod, sugarozza.nap, sugarozza.ora, sugarozza.perc FROM musor,sugarozza, csatorna WHERE sugarozza.csatorna_cskod = 2 AND musor.mkod = sugarozza.musor_mkod AND csatorna.cskod = sugarozza.csatorna_cskod AND Sugarozza.nap = 4;";
                        break;
                    case ("Péntek"):
                        sqlQuery = "SELECT musor.cim, musor.leiras, musor.jatekido, musor.mkod, sugarozza.nap, sugarozza.ora, sugarozza.perc FROM musor,sugarozza, csatorna WHERE sugarozza.csatorna_cskod = 2 AND musor.mkod = sugarozza.musor_mkod AND csatorna.cskod = sugarozza.csatorna_cskod AND Sugarozza.nap = 5;";
                        break;
                    case ("Szombat"):
                        sqlQuery = "SELECT musor.cim, musor.leiras, musor.jatekido, musor.mkod, sugarozza.nap, sugarozza.ora, sugarozza.perc FROM musor,sugarozza, csatorna WHERE sugarozza.csatorna_cskod = 2 AND musor.mkod = sugarozza.musor_mkod AND csatorna.cskod = sugarozza.csatorna_cskod AND Sugarozza.nap = 6;";
                        break;
                    case ("Vasárnap"):
                        sqlQuery = "SELECT musor.cim, musor.leiras, musor.jatekido, musor.mkod, sugarozza.nap, sugarozza.ora, sugarozza.perc FROM musor,sugarozza, csatorna WHERE sugarozza.csatorna_cskod = 2 AND musor.mkod = sugarozza.musor_mkod AND csatorna.cskod = sugarozza.csatorna_cskod AND Sugarozza.nap = 7;";
                        break;

                }
        }
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            ConnectionUtils.kapcsolat = DriverManager.getConnection(URL, USER, PASSWORD);

            Statement st = kapcsolat.createStatement();
            ResultSet rs = st.executeQuery(sqlQuery);

            int day;
            int ora;
            int perc;
            String nev;
            int mkod;
            String leiras;
            int jatekido;

            while (rs.next()) {
                nev = rs.getString("cim");
                leiras = rs.getString("leiras");
                jatekido = rs.getInt("jatekido");
                mkod = rs.getInt("mkod");
                day = rs.getInt("nap");
                ora = rs.getInt("ora");
                perc = rs.getInt("perc");

                list.add(new Musor(day, ora, perc, nev, mkod, leiras, jatekido));
            }

            kapcsolat.close();
            return list;

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<Musor> listLiveMusor() {
        /*
        private int nap;
        private int ora;
        private int perc;

        private String nev;
        private int  mkod;
        private String leiras;
        private int jatekido;*/
        ArrayList<Musor> list = new ArrayList<>();
        String sqlQuery = "";

        Calendar curDate = Calendar.getInstance();
        int curDay = curDate.get(Calendar.DAY_OF_WEEK);
        int curHour = curDate.get(Calendar.HOUR_OF_DAY);
        int curMinute = curDate.get(Calendar.MINUTE);

            sqlQuery = "SELECT musor.cim, musor.leiras, musor.jatekido, sugarozza.nap, sugarozza.ora, sugarozza.perc, csatorna.nev FROM csatorna, sugarozza,musor " +
                    "WHERE musor.mkod = sugarozza.musor_mkod AND csatorna.cskod = sugarozza.csatorna_cskod  AND sugarozza.ido = (SELECT MAX(sugarozza.ido) FROM sugarozza WHERE sugarozza.ido <= '" + curHour + ":" + curMinute + ":00' AND sugarozza.nap = " + (curDay % 7 + 1) + ") GROUP BY csatorna.nev;";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            ConnectionUtils.kapcsolat = DriverManager.getConnection(URL, USER, PASSWORD);

            Statement st = kapcsolat.createStatement();
            ResultSet rs = st.executeQuery(sqlQuery);

            int day;
            int ora;
            int perc;
            String nev;
            String leiras;
            int jatekido;

            while (rs.next()) {
                nev = rs.getString("cim");
                leiras = rs.getString("leiras");
                jatekido = rs.getInt("jatekido");
                day = rs.getInt("nap");
                ora = rs.getInt("ora");
                perc = rs.getInt("perc");

                list.add(new Musor(day, ora, perc, nev, leiras, jatekido));
            }

            kapcsolat.close();
            return list;

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<Musor> listMusoraim(Felhasznalo f){
        ArrayList<Musor> list = new ArrayList<>();

        String sqlQuery = "";
        sqlQuery = "SELECT musor.cim, musor.leiras, musor.jatekido, musor.mkod, sugarozza.nap, sugarozza.ora, sugarozza.perc, csatorna.nev FROM csatorna, sugarozza,musor, koveti WHERE musor.mkod = sugarozza.musor_mkod AND csatorna.cskod = sugarozza.csatorna_cskod AND koveti.musor_mkod = musor.mkod AND koveti.felhasznalo_email ='"+f.getEmail()+"';";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            ConnectionUtils.kapcsolat = DriverManager.getConnection(URL, USER, PASSWORD);

            Statement st = kapcsolat.createStatement();
            ResultSet rs = st.executeQuery(sqlQuery);

            int day;
            int ora;
            int perc;
            int mkod;
            String nev;
            String leiras;
            int jatekido;

            while (rs.next()) {
                nev = rs.getString("cim");
                leiras = rs.getString("leiras");
                jatekido = rs.getInt("jatekido");
                mkod = rs.getInt("mkod");
                day = rs.getInt("nap");
                ora = rs.getInt("ora");
                perc = rs.getInt("perc");

                list.add(new Musor(day, ora, perc, nev, mkod, leiras, jatekido));
            }

            kapcsolat.close();
            return list;

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }

    }

    private ArrayList<Koveti> kovetesekList(){
        ArrayList<Koveti> list = new ArrayList<>();

        String sqlQuery = "";
        sqlQuery = "SELECT koveti.musor_mkod, koveti.felhasznalo_email FROM koveti ;";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            ConnectionUtils.kapcsolat = DriverManager.getConnection(URL, USER, PASSWORD);

            Statement st = kapcsolat.createStatement();
            ResultSet rs = st.executeQuery(sqlQuery);

            int mkod;
            String email;

            while (rs.next()) {
                mkod = rs.getInt("musor_mkod");
                email = rs.getString("felhasznalo_email");

                list.add(new Koveti(email,mkod));
            }

            kapcsolat.close();
            return list;

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }

    }

    public void hozzaadKedvenchez(Felhasznalo f, Musor m){
        boolean hozzaade = true;
        ArrayList<Koveti> current = kovetesekList();
        for (Koveti kovet : current){
            if (kovet.getMusorMkod() == m.getMkod() && f.getEmail().equals(kovet.getFelhasznaloEmail())){
                hozzaade = false;
            }
        }
        if(hozzaade == false){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Sikertelen hozzáadás");
            alert.setHeaderText(null);
            alert.setContentText("A kért műsor már hozzá van adva!");
            alert.showAndWait();
        }
        else{
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                ConnectionUtils.kapcsolat = DriverManager.getConnection(URL, USER, PASSWORD);
                String query = "INSERT INTO koveti (musor_mkod, felhasznalo_email) VALUES (?,?);";

                PreparedStatement preparedStatement = kapcsolat.prepareStatement(query);
                preparedStatement.setInt(1, m.getMkod());
                preparedStatement.setString(2, f.getEmail());

                preparedStatement.executeUpdate();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Sikeres hozzáadás");
                alert.setHeaderText(null);
                alert.setContentText("Sikeres hozzáadás!");
                alert.showAndWait();
                kapcsolat.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void torolKovetest(Felhasznalo f, Musor m){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            ConnectionUtils.kapcsolat = DriverManager.getConnection(URL, USER, PASSWORD);
            String query = "DELETE FROM koveti WHERE musor_mkod = " + m.getMkod() + " AND felhasznalo_email = '"+ f.getEmail()+"';";
            Statement stm = kapcsolat.createStatement();
            stm.executeUpdate(query);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Sikeres törlés");
            alert.setHeaderText(null);
            alert.setContentText("Sikeres törlés!");
            alert.showAndWait();
            kapcsolat.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void modositProfilt(Felhasznalo f,String ujjelszo) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            ConnectionUtils.kapcsolat = DriverManager.getConnection(URL, USER, PASSWORD);
            String query = "UPDATE felhasznalo SET jelszo = '" + ujjelszo + "' WHERE email = '" + f.getEmail() + "';";
            Statement stm = kapcsolat.createStatement();
            stm.executeUpdate(query);

        }catch (Exception e) {
            e.printStackTrace();
        }
    }


}