package conexion;

//liberias de conexion
import java.sql.Connection; //Establecer conexion a DB
import java.sql.DriverManager;  //Enlace de conexion entre BD y Java
import java.sql.Statement;  //Genera sentencias SQL
import java.sql.ResultSet;  //Establece resultado final de datos
import java.sql.SQLException;   //Tratamiento de errores DB
import javafx.scene.control.Alert;
import javax.swing.JOptionPane;
/**
 *
 * @author achit
 */
public class conectarBD {
    
    public Connection cn;
    public Statement stmt;
    public ResultSet rs;
    
    //Metodo para conectar a la base de datos 
    public void conectarBDOracle() throws SQLException{
    
        DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        
        cn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE", "burguervend", "burguer123");
        stmt=cn.createStatement();
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Conexión exitosa");
        alerta.setHeaderText(null);
        alerta.setContentText("Conexión a BD OK\n\nContreras");
        alerta.showAndWait();
    }
}
