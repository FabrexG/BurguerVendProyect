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
    
    
}
