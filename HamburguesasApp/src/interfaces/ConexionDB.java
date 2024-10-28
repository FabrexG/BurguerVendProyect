package interfaces;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public interface ConexionDB {

	public Connection getConection() throws SQLException;
    void close(ResultSet rs) throws SQLException;
    void close(Statement smtm) throws SQLException;
    void close(PreparedStatement smtm) throws SQLException;
    void close(Connection conn) throws SQLException;
	
}
