/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package burguervenda.clases;
import java.sql.*;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Brian Miguel Escalona Maldonado 
 */
public class Direccion {
    private String direccion;
    private String numero;
    private String piso;
    private String indicaciones;
    private ArrayList<Direccion> direcciones = new ArrayList<>();
    
    public Direccion (String dir, String num, String pi, String ind) {
        this.direccion = dir;
        this.numero = num;
        this.piso = pi;
        this.indicaciones = ind;
    }
    
    public Direccion() {
        cargarDirecciones();
    }
    
    private void cargarDirecciones() {
        try(Connection connection = ConeccionDataBase.getConection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT DIRECCION, NUMERO, PISO_INFO, INDICACIONES\n" +
"FROM DIRECCION")) {
            while(resultSet.next()) {
                String dir = resultSet.getString("DIRECCION");
                String num = resultSet.getString("NUMERO");
                String info = resultSet.getString("PISO_INFO");
                String indi = resultSet.getString("INDICACIONES");
                direcciones.add(new Direccion(dir,num,info,indi));
            }
        } catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    
    public void guardarDireccion(String dir, String num, String info, String indi) {
        try(Connection connection = ConeccionDataBase.getConection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO DIRECCION (ID_DIRECCION, ID_USUARIO, DIRECCION, NUMERO, PISO_INFO, INDICACIONES) VALUES (SEC_DIRECCION.NEXTVAL, ?, ?, ?, ?, ?)");) {
            preparedStatement.setInt(1, 1);
            preparedStatement.setString(2, dir.toUpperCase());
            preparedStatement.setString(3, num);
            preparedStatement.setString(4, info.toUpperCase());
            preparedStatement.setString(5,indi.toUpperCase());
            preparedStatement.executeUpdate();
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getPiso() {
        return piso;
    }

    public void setPiso(String piso) {
        this.piso = piso;
    }

    public String getIndicaciones() {
        return indicaciones;
    }

    public void setIndicaciones(String indicaciones) {
        this.indicaciones = indicaciones;
    }

    public ArrayList<Direccion> getDirecciones() {
        return direcciones;
    }

    public void setDirecciones(ArrayList<Direccion> direcciones) {
        this.direcciones = direcciones;
    }
    
    @Override
    public String toString() {
        return direccion;
    }
}
