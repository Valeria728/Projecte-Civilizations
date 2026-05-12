package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static Connection connection = null;

    // Configuración de la base de datos
    private static final String URL      = "jdbc:mysql://localhost:3306/civilizations_db";
    private static final String USER     = "root";
    private static final String PASSWORD = "Miguel:123*";

    private DatabaseConnection() {
        // Constructor privado para evitar que se creen objetos de esta clase
    }

    public static Connection getConnection() {
        if (connection == null) {
            try {
                // Cargamos el driver de MySQL
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("LOG: Conexión establecida con éxito.");
            } catch (ClassNotFoundException | SQLException e) {
                System.out.println("ERROR: No se pudo conectar a la base de datos: " + e.getMessage());
            }
        }
        return connection;
    }

    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                connection = null;
                System.out.println("LOG: Conexión cerrada.");
            }
        } catch (SQLException e) {
            System.out.println("ERROR: Error al cerrar la conexión: " + e.getMessage());
        }
    }
}