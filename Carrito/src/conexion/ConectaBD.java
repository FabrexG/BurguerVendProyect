package conexion;

import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Clase para conectar a la base de datos Oracle.
 */
public class ConectaBD {

    public Connection cn;
    public Statement stmt;
    public ResultSet rs;

    /**
     * Conecta a la base de datos Oracle.
     *
     * @throws SQLException Si ocurre un error al conectar a la base de datos.
     */
    public void conectarBDOracle() throws SQLException {
        try {
            // Registrar el driver de Oracle
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());

            // Establecer la conexión a la base de datos
            this.cn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE", "Burguer", "uacm123");

            // Crear un objeto Statement para ejecutar sentencias SQL
            this.stmt = this.cn.createStatement();

            // Mostrar un mensaje de éxito
            JOptionPane.showMessageDialog(null, "Conexión a BD OK!!!!\n\nCarmona");

        } catch (SQLException e) {
            // Mostrar un mensaje de error si la conexión falla
            JOptionPane.showMessageDialog(null, "Error al conectar a la base de datos: " + e.getMessage());
            e.printStackTrace();
            throw e; // Relanzar la excepción para que sea manejada por el llamador
        }
    }

    /**
     * Método principal para probar la conexión a la base de datos.
     *
     * @param args Los argumentos de la línea de comandos.
     * @throws SQLException Si ocurre un error al conectar a la base de datos.
     */
    public static void main(String[] args) throws SQLException {
        ConectaBD b = new ConectaBD();
        b.conectarBDOracle();
    }
}