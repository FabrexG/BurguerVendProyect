/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package burguervenda.clases;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Brian Miguel Escalona Maldonado 
 */
public class Restaurante {
    
    private String nombre;
    private String direccion;
    private boolean favorito;
    private String infoApertura;
    private String latitud;
    private String longitud;

    private ArrayList<Restaurante> restaurantes = new ArrayList<>();
    private ArrayList<Restaurante> restaurantesFavoritos = new ArrayList<>();
    
    public Restaurante(String name, String dir, String info, String lat, String lon){
        this.nombre = name;
        this.direccion = dir;
        this.favorito = false;
        this.infoApertura = info;
        this.latitud = lat;
        this.longitud = lon;
    }
    public Restaurante(){
        cargarRestaurantes();
        cargarFavoritos();
    }
    
    private void cargarRestaurantes(){
        try(Connection connection = ConeccionDataBase.getConection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT NOMBRE, DIRECCION, INFORMACION, LATITUD,LONGITUD\n" +
"FROM RESTAURANTE")){
            while(resultSet.next()) {
                String nombre = resultSet.getString("NOMBRE");
                String direccion = resultSet.getString("DIRECCION");
                String informacion = resultSet.getString("INFORMACION");
                String latitud = resultSet.getNString("LATITUD");
                String longitud = resultSet.getNString("LONGITUD");
                restaurantes.add(new Restaurante(nombre,direccion,informacion,latitud,longitud));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void añadirFavoritos(Restaurante res){
        try(Connection connection = ConeccionDataBase.getConection();
            Statement statement = connection.createStatement();
            PreparedStatement preparedStatment = connection.prepareStatement("INSERT INTO RESTAURANTE VALUES (?,?,?,?,?)");){
    }
        catch (SQLException ex) {
            Logger.getLogger(Restaurante.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void cargarFavoritos() {
        try(Connection connection = ConeccionDataBase.getConection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT RES.NOMBRE, RES.DIRECCION, RES.INFORMACION, RES.LATITUD, RES.LONGITUD\n" +
"FROM RESTAURANTE RES, REST_FAV FAV\n" +
"WHERE (fav.id_usuario=1 AND fav.id_restaurante = res.id_restaurante)")){
            while(resultSet.next()) {
                String nombre = resultSet.getString("NOMBRE");
                String direccion = resultSet.getString("DIRECCION");
                String informacion = resultSet.getString("INFORMACION");
                String latitud = resultSet.getNString("LATITUD");
                String longitud = resultSet.getNString("LONGITUD");
                restaurantesFavoritos.add(new Restaurante(nombre,direccion,informacion,latitud,longitud));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    @Override
    public String toString() {
        return nombre;
    }
    
    public ArrayList<Restaurante> getRestaurantesFavoritos() {
        return restaurantesFavoritos;
    }

    public void setRestaurantesFavoritos(ArrayList<Restaurante> restaurantesFavoritos) {
        this.restaurantesFavoritos = restaurantesFavoritos;
    }
    
    public ArrayList<Restaurante> getRestaurantes() {
        return restaurantes;
    }

    public void setRestaurantes(ArrayList<Restaurante> restaurantes) {
        this.restaurantes = restaurantes;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public boolean isFavorito() {
        return favorito;
    }

    public void setFavorito(boolean favorito) {
        this.favorito = favorito;
    }

    public String getInfoApertura() {
        return infoApertura;
    }

    public void setInfoApertura(String infoApertura) {
        this.infoApertura = infoApertura;
    }
    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }
    
}
