package db;

import domain.AbstractDomainObject;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;
import java.io.FileInputStream;

/**
 * Singleton klasa za upravljanje vezom sa bazom podataka.
 */
public class DBBroker {

    private static DBBroker instance;
    private Connection connection;

    // Konstruktor koji postavlja vezu sa bazom koristeći dbconfig.properties
    private DBBroker() {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream("dbconfig.properties"));

            String url = properties.getProperty("url");
            String username = properties.getProperty("username");
            String password = properties.getProperty("password");

            System.out.println("DB Config - URL: " + url + ", User: " + username);

            connection = DriverManager.getConnection(url, username, password);
            connection.setAutoCommit(false);
            System.out.println("Konekcija sa bazom je uspešno uspostavljena.");

        } catch (Exception ex) {
            System.out.println("GRESKA: Nije moguće učitati konfiguraciju baze!");
            ex.printStackTrace();
        }
    }

    // Jedinstvena instanca DBBroker klase (Singleton)
    public static DBBroker getInstance() {
        if (instance == null) {
            instance = new DBBroker();
            if (instance.connection == null) {
                System.out.println("GRESKA: Konekcija nije uspostavljena!");
            } else {
                System.out.println("Konekcija sa bazom je uspesno uspostavljena.");
            }
        }
        return instance;
    }

    // Metoda za vraćanje konekcije
    public Connection getConnection() {
        return connection;
    }

    // SELECT upit za entitete
    public ArrayList<AbstractDomainObject> select(AbstractDomainObject ado) throws SQLException {
        String query = "SELECT * FROM " + ado.nazivTabele() + " " + ado.alijas()
                + " " + ado.join() + " " + ado.uslovZaSelect();
        System.out.println(query);
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(query);
        return ado.vratiListu(rs);
    }

    // INSERT upit za entitete
    public PreparedStatement insert(AbstractDomainObject ado) throws SQLException {
        String query = "INSERT INTO " + ado.nazivTabele() + " "
                + ado.koloneZaInsert() + " VALUES(" + ado.vrednostiZaInsert() + ")";
        System.out.println(query);
        PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        ps.executeUpdate();
        return ps;
    }

    // UPDATE upit za entitete
    public void update(AbstractDomainObject ado) throws SQLException {
        String query = "UPDATE " + ado.nazivTabele() + " SET "
                + ado.vrednostiZaUpdate() + " WHERE " + ado.uslov();
        System.out.println(query);
        Statement statement = connection.createStatement();
        statement.executeUpdate(query);
    }

    // DELETE upit za entitete
    public void delete(AbstractDomainObject ado) throws SQLException {
        String query = "DELETE FROM " + ado.nazivTabele() + " WHERE " + ado.uslov();
        System.out.println(query);
        Statement statement = connection.createStatement();
        statement.executeUpdate(query);
    }

    // Metoda za commit promena u bazi
    public void commit() throws SQLException {
        connection.commit();
    }

    // Metoda za rollback promena u slučaju greške
    public void rollback() throws SQLException {
        connection.rollback();
    }

    // Zatvaranje konekcije
    public void close() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
