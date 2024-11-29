package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conectarBD {
    public Connection cn;

    /**
     * Método para conectar a la base de datos Oracle.
     */
    public void conectarBDOracle() {
        String url = "jdbc:oracle:thin:@localhost:1521:XE"; // Cambia por tu configuración
        String usuario = "burguervend"; // Usuario de la base de datos
        String contraseña = "burguer123"; // Contraseña de la base de datos

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver"); // Cargar el driver
            cn = DriverManager.getConnection(url, usuario, contraseña);
            System.out.println("Conexión exitosa a la base de datos Oracle.");
        } catch (ClassNotFoundException e) {
            System.err.println("Error al cargar el driver de Oracle: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Error al conectar a la base de datos: " + e.getMessage());
        }
    }
}
