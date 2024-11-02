package conexion;

//librerias de conexion

import java.sql.Connection; //Establecer conexion a BD
import java.sql.DriverManager; // Enlase de conexion entre BD y Java
import java.sql.Statement; // Genera sentencias SQL
import java.sql.ResultSet; //Establece resultado final de datos
import java.sql.SQLException; //Tratamiento de errores de BD
import javax.swing.JOptionPane;

public class ConectaBD {//inicia clase

    public Connection cn;
    public Statement stmt;
    public ResultSet rs;

    //metodo para conectar a la base de datos

    public void conectarBDOracle() throws SQLException{

        DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        cn=DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE","Burguer","uacm123");
        stmt= cn.createStatement();
        JOptionPane.showMessageDialog(null, "Conexion a BD OK!!!!\n\nCarmona");

    }
    public static void main(String[] args) throws SQLException {
        ConectaBD b = new ConectaBD();
        b.conectarBDOracle();
    }

}//termina clase