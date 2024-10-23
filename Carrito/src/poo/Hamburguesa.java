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
        desc.append("Ingredientes: ");
        for (int i = 0; i < ingredientes.size(); i++) {
            desc.append(ingredientes.get(i).getNombre());
            if (i < ingredientes.size() - 1) {
                desc.append(", ");
            }
        }
        desc.append("\nExtras: ");
        if (extras.isEmpty()) {
            desc.append("Ninguno");
        } else {
            for (int i = 0; i < extras.size(); i++) {
                desc.append(extras.get(i).getNombre());
                if (i < extras.size() - 1) {
                    desc.append(", ");
                }
            }
        }
        desc.append("\nCosto: $").append(calcularCosto());
        return desc.toString();
    }
}