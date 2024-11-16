package poo;

public class Ingrediente {

    private int id; // Atributo para el ID del ingrediente
    private String nombre;
    private double costo;
    private int cantidad; // Atributo para la cantidad
    private String unidad; // Atributo para la unidad

    /**
     * Constructor para la clase Ingrediente.
     *
     * @param id El ID del ingrediente.
     * @param nombre El nombre del ingrediente.
     * @param costo  El costo del ingrediente.
     */
    public Ingrediente(int id, String nombre, double costo) {
        this.id = id;
        this.nombre = nombre;
        this.costo = costo;
    }

    /**
     * Constructor para la clase Ingrediente que incluye la cantidad.
     *
     * @param id       El ID del ingrediente.
     * @param nombre   El nombre del ingrediente.
     * @param costo    El costo del ingrediente.
     * @param cantidad La cantidad del ingrediente.
     */
    public Ingrediente(int id, String nombre, double costo, int cantidad) {
        this.id = id;
        this.nombre = nombre;
        this.costo = costo;
        this.cantidad = cantidad;
    }

    /**
     * Constructor para la clase Ingrediente que incluye la cantidad y la unidad.
     *
     * @param id       El ID del ingrediente.
     * @param nombre   El nombre del ingrediente.
     * @param costo    El costo del ingrediente.
     * @param cantidad La cantidad del ingrediente.
     * @param unidad   La unidad del ingrediente.
     */
    public Ingrediente(int id, String nombre, double costo, int cantidad, String unidad) {
        this.id = id;
        this.nombre = nombre;
        this.costo = costo;
        this.cantidad = cantidad;
        this.unidad = unidad;
    }

    /**
     * Obtiene el ID del ingrediente.
     *
     * @return El ID del ingrediente.
     */
    public int getId() {
        return this.id;
    }

    /**
     * Establece el ID del ingrediente.
     *
     * @param id El nuevo ID del ingrediente.
     */
    public void setId(int id) {
        this.id = id;
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

    /**
     * Obtiene la unidad del ingrediente.
     *
     * @return La unidad del ingrediente.
     */
    public String getUnidad() {
        return this.unidad;
    }

    /**
     * Establece la unidad del ingrediente.
     *
     * @param unidad La nueva unidad del ingrediente.
     */
    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    /**
     * Disminuye la cantidad disponible del ingrediente.
     *
     * @param cantidad La cantidad a disminuir.
     * @throws IllegalArgumentException Si la cantidad a disminuir es mayor que la cantidad disponible.
     */
    public void disminuirCantidad(int cantidad) {
        if (cantidad > this.cantidad) {
            throw new IllegalArgumentException("No hay suficiente " + this.nombre + " en el inventario.");
        }
        this.cantidad -= cantidad;
    }

    @Override
    public String toString() {
        return "Ingrediente{" +
                "id=" + this.id +
                ", nombre='" + this.nombre + '\'' +
                ", costo=" + this.costo +
                ", cantidad=" + this.cantidad +
                ", unidad='" + this.unidad + '\'' +
                '}';
    }
}