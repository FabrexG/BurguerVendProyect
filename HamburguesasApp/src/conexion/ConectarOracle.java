package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import interfaces.ConexionDB;

public class ConectarOracle implements ConexionDB {
   
	private String url = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
	private String name = "BURGERTECH";
	private String password = "uacm123";

	@Override
	public Connection getConection() throws SQLException {
		try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("No se pudo cargar el driver de Oracle", e);
        }
        return DriverManager.getConnection(url, name, password);
	}

	@Override
	public void close(ResultSet rs) throws SQLException {
		if (rs != null) {
            rs.close();
        }
	}

	@Override
	public void close(Statement smtm) throws SQLException {
		if (smtm != null) {
            smtm.close();
        }
	}

	@Override
	public void close(PreparedStatement smtm) throws SQLException {
		if (smtm != null) {
            smtm.close();
        }
	}

	@Override
	public void close(Connection conn) throws SQLException {
		if (conn != null && !conn.isClosed()) {
            conn.close();
        }
	}
	
	public void testConnection() {
        try (Connection conn = getConection()) {
            if (conn != null) {
                System.out.println("Conexión exitosa a la base de datos");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
