package poo;

public class Ingrediente {
    private String nombre;
    private double costo;
    private int cantidad; // Nuevo atributo para la cantidad

    public Ingrediente(String nombre, double costo) {
        this.nombre = nombre;
        this.costo = costo;
    }

    // Nuevo constructor que incluye la cantidad
    public Ingrediente(String nombre, double costo, int cantidad) {
        this.nombre = nombre;
        this.costo = costo;
        this.cantidad = cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public
    double getCosto() {
        return costo;
    }

    public int getCantidad()
    { // Getter para la cantidad
        return cantidad;
    }

    public void setCantidad(int cantidad) { // Setter para la cantidad
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "Ingrediente{" +
                "nombre='" + nombre + '\'' +
                ", costo=" + costo +
                ", cantidad=" + cantidad +
                '}';
    }
}