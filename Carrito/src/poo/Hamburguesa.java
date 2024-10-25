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

    /**
     * Constructor vacío para la clase Hamburguesa.
     */
    public Hamburguesa() {
        // Constructor vacío
    }

    /**
     * Constructor para la clase Hamburguesa.
     *
     * @param nombre       El nombre de la hamburguesa.
     * @param ingredientes La lista de ingredientes de la hamburguesa.
     * @param costoBase   El costo base de la hamburguesa.
     * @param rutaImagen  La ruta de la imagen de la hamburguesa.
     */
    public Hamburguesa(String nombre, List<Ingrediente> ingredientes, double costoBase, String rutaImagen) {
        this.nombre = nombre;
        this.ingredientes = ingredientes;
        this.imagen = new Image(rutaImagen);
        this.extras = new ArrayList<>();
        // Validar el costo base
        if (costoBase >= 0) {
            this.costoBase = costoBase;
        } else {
            this.costoBase = 0; // O lanzar una excepción
        }
    }

    /**
     * Obtiene la imagen de la hamburguesa.
     *
     * @return La imagen de la hamburguesa.
     */
    public Image getImagen() {
        return this.imagen;
    }

    /**
     * Establece la imagen de la hamburguesa.
     *
     * @param imagen La nueva imagen de la hamburguesa.
     */
    public void setImagen(Image imagen) {
        this.imagen = imagen;
    }

    /**
     * Agrega un extra a la hamburguesa.
     *
     * @param extra El extra a agregar.
     */
    public void agregarExtra(Extra extra) {
        this.extras.add(extra);
    }

    /**
     * Obtiene el nombre de la hamburguesa.
     *
     * @return El nombre de la hamburguesa.
     */
    public String getNombre() {
        return this.nombre;
    }

    /**
     * Establece el nombre de la hamburguesa.
     *
     * @param nombre El nuevo nombre de la hamburguesa.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la lista de ingredientes de la hamburguesa.
     *
     * @return La lista de ingredientes de la hamburguesa.
     */
    public List<Ingrediente> getIngredientes() {
        return this.ingredientes;
    }

    /**
     * Establece la lista de ingredientes de la hamburguesa.
     *
     * @param ingredientes La nueva lista de ingredientes de la hamburguesa.
     */
    public void setIngredientes(List<Ingrediente> ingredientes) {
        this.ingredientes = ingredientes;
    }

    /**
     * Obtiene la lista de extras de la hamburguesa.
     *
     * @return La lista de extras de la hamburguesa.
     */
    public List<Extra> getExtras() {
        return this.extras;
    }

    /**
     * Establece la lista de extras de la hamburguesa.
     *
     * @param extras La nueva lista de extras de la hamburguesa.
     */
    public void setExtras(List<Extra> extras) {
        this.extras = extras;
    }

    /**
     * Obtiene el costo base de la hamburguesa.
     *
     * @return El costo base de la hamburguesa.
     */
    public double getCostoBase() {
        return this.costoBase;
    }

    /**
     * Establece el costo base de la hamburguesa.
     *
     * @param costoBase El nuevo costo base de la hamburguesa.
     */
    public void setCostoBase(double costoBase) {
        this.costoBase = costoBase;
    }

    /**
     * Calcula el costo total de la hamburguesa.
     *
     * @return El costo total de la hamburguesa.
     */
    public double calcularCosto() {
        double costoTotal = this.costoBase;
        for (Ingrediente ingrediente : this.ingredientes) {
            costoTotal += ingrediente.getCosto() * ingrediente.getCantidad();
        }
        for (Extra extra : this.extras) {
            costoTotal += extra.getCosto() * extra.getCantidad();
        }
        return costoTotal;
    }

    /**
     * Genera una descripción de la hamburguesa.
     *
     * @return La descripción de la hamburguesa.
     */
    public String descripcion() {
        StringBuilder desc = new StringBuilder();
        desc.append("Nombre: ").append(this.nombre).append("\n");
        desc.append("Ingredientes: \n");
        for (int i = 0; i < this.ingredientes.size(); i++) {
            desc.append("  - ").append(this.ingredientes.get(i).getCantidad())
                    .append(" ").append(this.ingredientes.get(i).getNombre())
                    .append(" a $").append(this.ingredientes.get(i).getCosto()).append(" c/u \n");
        }
        desc.append("Extras: \n");
        if (this.extras.isEmpty()) {
            desc.append("  - Ninguno\n");
        } else {
            for (int i = 0; i < this.extras.size(); i++) {
                desc.append("  - ").append(this.extras.get(i).getCantidad())
                        .append(" ").append(this.extras.get(i).getNombre())
                        .append(" a $").append(this.extras.get(i).getCosto()).append(" c/u \n");
            }
        }
        desc.append("Costo base: $").append(this.costoBase).append("\n\n"); // Agregar costo base
        desc.append("Costo total: $").append(calcularCosto());
        return desc.toString();
    }
}