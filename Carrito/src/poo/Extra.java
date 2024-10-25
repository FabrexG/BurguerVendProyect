package poo;

public class Extra extends Ingrediente {

    private int cantidad; // Nuevo atributo para la cantidad

    /**
     * Constructor para la clase Extra.
     *
     * @param nombre El nombre del extra.
     * @param costo  El costo del extra.
     */
    public Extra(String nombre, double costo) {
        super(nombre, costo);
    }

    /**
     * Constructor para la clase Extra que incluye la cantidad.
     *
     * @param nombre   El nombre del extra.
     * @param costo    El costo del extra.
     * @param cantidad La cantidad del extra.
     */
    public Extra(String nombre, double costo, int cantidad) {
        super(nombre, costo);
        this.cantidad = cantidad;
    }

    /**
     * Obtiene la cantidad del extra.
     *
     * @return La cantidad del extra.
     */
    public int getCantidad() {
        return this.cantidad;
    }

    /**
     * Establece la cantidad del extra.
     *
     * @param cantidad La nueva cantidad del extra.
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "Extra{" +
                "nombre='" + getNombre() + '\'' +
                ", costo=" + getCosto() +
                ", cantidad=" + this.cantidad +
                '}';
    }
}