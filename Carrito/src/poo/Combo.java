package poo;

import java.util.List;

public class Combo {

    private String nombre;
    private Hamburguesa hamburguesa;
    private List<Extra> extras;
    private double costoBase;

    /**
     * Constructor vacío para la clase Combo.
     */
    public Combo() {
        // Constructor vacío
    }

    /**
     * Constructor para la clase Combo.
     *
     * @param nombre       El nombre del combo.
     * @param hamburguesa La hamburguesa incluida en el combo.
     * @param extras      La lista de extras incluidos en el combo.
     * @param costoBase   El costo base del combo.
     */
    public Combo(String nombre, Hamburguesa hamburguesa, List<Extra> extras, double costoBase) {
        this.nombre = nombre;
        this.hamburguesa = hamburguesa;
        this.extras = extras;
        // Validar el costo base
        if (costoBase >= 0) {
            this.costoBase = costoBase;
        } else {
            this.costoBase = 0; // O lanzar una excepción
        }
    }

    /**
     * Calcula el costo total del combo.
     *
     * @return El costo total del combo.
     */
    public double calcularCosto() {
        double total = this.costoBase + this.hamburguesa.calcularCosto(); // Sumar el costo de la hamburguesa
        for (Extra extra : this.extras) {
            total += extra.getCosto();
        }
        return total;
    }

    // Getters y setters

    /**
     * Obtiene el nombre del combo.
     *
     * @return El nombre del combo.
     */
    public String getNombre() {
        return this.nombre;
    }

    /**
     * Establece el nombre del combo.
     *
     * @param nombre El nuevo nombre del combo.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la hamburguesa incluida en el combo.
     *
     * @return La hamburguesa incluida en el combo.
     */
    public Hamburguesa getHamburguesa() {
        return this.hamburguesa;
    }

    /**
     * Establece la hamburguesa incluida en el combo.
     *
     * @param hamburguesa La nueva hamburguesa incluida en el combo.
     */
    public void setHamburguesa(Hamburguesa hamburguesa) {
        this.hamburguesa = hamburguesa;
    }

    /**
     * Obtiene la lista de extras incluidos en el combo.
     *
     * @return La lista de extras incluidos en el combo.
     */
    public List<Extra> getExtras() {
        return this.extras;
    }

    /**
     * Establece la lista de extras incluidos en el combo.
     *
     * @param extras La nueva lista de extras incluidos en el combo.
     */
    public void setExtras(List<Extra> extras) {
        this.extras = extras;
    }

    /**
     * Obtiene el costo base del combo.
     *
     * @return El costo base del combo.
     */
    public double getCostoBase() {
        return this.costoBase;
    }

    /**
     * Establece el costo base del combo.
     *
     * @param costoBase El nuevo costo base del combo.
     */
    public void setCostoBase(double costoBase) {
        this.costoBase = costoBase;
    }
}