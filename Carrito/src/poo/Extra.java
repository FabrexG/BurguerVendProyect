package poo;

public class Extra extends Ingrediente {
    private int cantidad; // Nuevo atributo para la cantidad

    public Extra(String nombre, double costo) {
        super(nombre, costo);
    }

    // Nuevo constructor que incluye la cantidad
    public Extra(String nombre, double costo, int cantidad) {
        super(nombre, costo);
        this.cantidad = cantidad;
    }

    public int getCantidad() { // Getter para la cantidad
        return cantidad;
    }

    public void setCantidad(int cantidad) { // Setter para la cantidad
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "Extra{" +
                "nombre='" + getNombre() + '\'' +
                ", costo=" + getCosto() +
                ", cantidad=" + cantidad +
                '}';
    }
}