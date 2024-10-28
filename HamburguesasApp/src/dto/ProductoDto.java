package dto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexion.ConectarOracle;
import dao.Producto;

public class ProductoDto {

	private static String SQL_SELECT = "";

	public List<Producto> getListProductos() {

		Connection coneccionDb = null;
		ConectarOracle conOracle = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		List<Producto> listaProductos = new ArrayList<Producto>();
		try {
			conOracle = new ConectarOracle();
			coneccionDb = conOracle.getConection();

			SQL_SELECT = "SELECT NOMBRE, PRECIO FROM PRODUCTOS";
			stmt = coneccionDb.prepareStatement(SQL_SELECT);
			rs = stmt.executeQuery();

			while (rs.next()) {
				String nombre = rs.getString("NOMBRE");
				float precio = rs.getFloat("PRECIO");
				Producto producto = new Producto(nombre, precio);
				listaProductos.add(producto);
			}
			//conOracle.testConnection();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
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
		return listaProductos;
	}

	public Producto getProducto(int id) {
		return null;
	}

	public void deleteProducto(int id) {

	}

}
