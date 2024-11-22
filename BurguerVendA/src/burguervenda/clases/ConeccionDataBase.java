/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package burguervenda.clases;
import java.sql.*;
/**
 *
 * @author asus
 */
public class ConeccionDataBase {
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
    private static final String USER = "system";
    private static final String PASSWORD = "1234";
    
    public static Connection getConection() throws SQLException {
        
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
    
    
}
