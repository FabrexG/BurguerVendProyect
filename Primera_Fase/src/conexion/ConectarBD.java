package conexion;
import java.sql.Connection; 
import java.sql.DriverManager; 
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ConectarBD {
	    public Connection cn;
	    public Statement stmt;
	    public ResultSet rs;
	    
	//metodo para conectar a la base de datos 

	public void conectarBDOracle()throws SQLException{
	    
	    DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
	    cn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE","Pedidos","123456P");
	    stmt =cn.createStatement();
	    JOptionPane.showMessageDialog(null, "Conexion A Base De Datos\n\n ED Martinez");
	    
	     }//Termina Metodo Crear Base De Datos


}
