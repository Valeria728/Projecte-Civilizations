package civilization;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	// Dades de configuració de la base de dades
    private static final String URL = "jdbc:mysql://localhost:3306/civilization_game";
    private static final String USER = "root";
    private static final String PASS = "";

    // Mètode per connectar-se a MySQL
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}

