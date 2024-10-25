package poo;

public class Ingrediente {

    private String nombre;
    private double costo;
    private int cantidad; // Nuevo atributo para la cantidad

    /**
     * Constructor para la clase Ingrediente.
     *
     * @param nombre El nombre del ingrediente.
     * @param costo  El costo del ingrediente.
     */
    public Ingrediente(String nombre, double costo) {
        this.nombre = nombre;
        this.costo = costo;
    }

    /**
     * Constructor para la clase Ingrediente que incluye la cantidad.
     *
     * @param nombre   El nombre del ingrediente.
     * @param costo    El costo del ingrediente.
     * @param cantidad La cantidad del ingrediente.
     */
    public Ingrediente(String nombre, double costo, int cantidad) {
        this.nombre = nombre;
        this.costo = costo;
        this.cantidad = cantidad;
    }

    /**
     * Obtiene el nombre del ingrediente.
     *
     * @return El nombre del ingrediente.
     */
    public String getNombre() {
        return this.nombre;
    }

    /**
     * Obtiene el costo del ingrediente.
     *
     * @return El costo del ingrediente.
     */
    public double getCosto() {
        return this.costo;
    }

    /**
     * Obtiene la cantidad del ingrediente.
     *
     * @return La cantidad del ingrediente.
     */
    public int getCantidad() {
        return this.cantidad;
    }

    /**
     * Establece la cantidad del ingrediente.
     *
     * @param cantidad La nueva cantidad del ingrediente.
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "Ingrediente{" +
                "nombre='" + this.nombre + '\'' +
                ", costo=" + this.costo +
                ", cantidad=" + this.cantidad +
                '}';
    }
}