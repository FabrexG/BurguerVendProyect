package dto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import conexion.ConectarOracle;
import dao.Cupon;

public class CuponDto {

	private Connection coneccionDb;
	private static String SQL_SELECT = "";
	private static String SQL_UPDATE = "";
	
	
	public Cupon getCupon(String claveIn) {
		
		Connection coneccionDb = null;
		ConectarOracle conOracle = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conOracle = new ConectarOracle();
			coneccionDb = conOracle.getConection();
			SQL_SELECT = "SELECT ID, CLAVE, FECHA, STATUS ,DESCUENTO FROM CUPONES WHERE CLAVE = ?";
			stmt = coneccionDb.prepareStatement(SQL_SELECT);
			stmt.setString(1, claveIn);
			
			rs = stmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("ID");
				String clave = rs.getString("CLAVE");
				Date fecha = rs.getDate("FECHA");
				char status = rs.getString("STATUS").charAt(0);
				float descuento = rs.getFloat("DESCUENTO");
				
				Cupon cupon =  new Cupon(id, clave, fecha, status, descuento);
				return cupon;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (coneccionDb != null)
					coneccionDb.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public boolean executeUpdateCuton(String claveIn) {
		coneccionDb = null;
		ConectarOracle conOracle = null;
		PreparedStatement stmt = null;
        
        boolean actualizado = false;
        
        try {
			
        	conOracle = new ConectarOracle();
			coneccionDb = conOracle.getConection();
        	SQL_UPDATE = "UPDATE CUPONES SET STATUS = 'F', FECHACANJE = SYSDATE WHERE CLAVE = ?";
        	stmt = coneccionDb.prepareStatement(SQL_UPDATE);
            stmt.setString(1, claveIn);
            int filasAfectadas = stmt.executeUpdate();
            
            if (filasAfectadas > 0) {
                actualizado = true; 
            }
            
        	
		} catch (SQLException e) {
			e.printStackTrace();
		}
        finally {
        	try {
                if (stmt != null) {
                    stmt.close();
                }
                if (coneccionDb != null) {
                	coneccionDb.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
		}
        return actualizado;
	}
}
