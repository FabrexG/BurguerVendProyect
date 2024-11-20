/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package burguervenda.clases;

import java.util.ArrayList;

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
    
    public Restaurante(String name, String dir, String info){
        this.nombre = name;
        this.direccion = dir;
        this.favorito = false;
        this.infoApertura = info;
    }
    public Restaurante(String name, String dir, String info,boolean fav){
        this.nombre = name;
        this.direccion = dir;
        this.favorito = fav;
        this.infoApertura = info;
    }
    public Restaurante(){
        cargarRestaurantes();
        cargarFavoritos();
    }
    
    private void cargarRestaurantes(){
        restaurantes.add(new Restaurante("San Lorenzo, Tezonco","Av. Tlahuac 5295, Los Olivos, Tláhuac, 13210 Ciudad de México, CDMX","8:00 a.m."));
        restaurantes.add(new Restaurante("Plaza Las Antenas, Periferico Oriente ","Av. Canal de Garay 3278, La Esperanza, Iztapalapa, 09910 Ciudad de México, CDMX","10:00 a.m."));
        restaurantes.add(new Restaurante("Walmart, Olivos","Av. Tlahuac 5662, Área Federal Panteón San Lorenzo Tezonco, Iztapalapa, 09790 Ciudad de México, CDMX","7:00 a.m."));
        restaurantes.add(new Restaurante("Calle 11","Av. Tlahuac 1479, Iztapalapa, 09880 Ciudad de México, CDMX","9:00 a.m."));
    }
    
    public void añadirFavoritos(Restaurante res){
        restaurantesFavoritos.add(res);
    }
    
    private void cargarFavoritos() {
        for(Restaurante res:restaurantes) {
            if(res.isFavorito()) {
                restaurantesFavoritos.add(res);
            }
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
