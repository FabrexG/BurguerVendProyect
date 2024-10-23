package poo;

import javafx.scene.image.Image;
import java.util.ArrayList;
import java.util.List;

public class Hamburguesa {
    private String nombre;
    private List<Ingrediente> ingredientes;
    private List<Extra> extras;
    private double costoBase;
    private Image imagen;

    public Hamburguesa() {
        // Constructor vacío
    }

    public Hamburguesa(String nombre, List<Ingrediente> ingredientes, double costoBase, String rutaImagen) {
        this.nombre = nombre;
        this.ingredientes = ingredientes;
        this.imagen = new Image(rutaImagen);
        this.extras = new ArrayList<>();
        if (costoBase >= 0) {
            this.costoBase = costoBase;
        } else {
            this.costoBase = 0; // O lanzar una excepción
        }
    }

    public Image getImagen() {
        return imagen;
    }

    public void setImagen(Image imagen) {
        this.imagen = imagen;
    }

    public void agregarExtra(Extra extra) {
        extras.add(extra);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Ingrediente> getIngredientes() {
        return ingredientes;
    }

    public
    void setIngredientes(List<Ingrediente> ingredientes) {
        this.ingredientes = ingredientes;
    }

    public List<Extra>
    getExtras() {
        return extras;
    }

    public void setExtras(List<Extra> extras) {
        this.extras = extras;
    }

    public double getCostoBase() {
        return costoBase;
    }

    public void setCostoBase(double costoBase) {
        this.costoBase = costoBase;
    }

    public double calcularCosto() {
        double costoTotal = costoBase;
        for (Ingrediente ingrediente : ingredientes) {
            costoTotal += ingrediente.getCosto() * ingrediente.getCantidad();
        }
        for (Extra extra : extras) {
            costoTotal += extra.getCosto() * extra.getCantidad();
        }
        return costoTotal;
    }

    public String descripcion() {
        StringBuilder desc = new StringBuilder();
        desc.append("Nombre: ").append(nombre).append("\n");
        desc.append("Ingredientes: \n");
        for (int i = 0; i < ingredientes.size(); i++) {
            desc.append("  - ").append(ingredientes.get(i).getCantidad())
                    .append(" ").append(ingredientes.get(i).getNombre())
                    .append(" a $").append(ingredientes.get(i).getCosto()).append(" c/u \n");
        }
        desc.append("Extras: \n");
        if (extras.isEmpty()) {
            desc.append("  - Ninguno\n");
        } else {
            for (int i = 0; i < extras.size(); i++) {
                desc.append("  - ").append(extras.get(i).getCantidad())
                        .append(" ").append(extras.get(i).getNombre())
                        .append(" a $").append(extras.get(i).getCosto()).append(" c/u \n");
            }
        }
        desc.append("Costo base: $").append(costoBase).append("\n\n"); // Agregar costo base
        desc.append("Costo total: $").append(calcularCosto());
        return desc.toString();
    }
}