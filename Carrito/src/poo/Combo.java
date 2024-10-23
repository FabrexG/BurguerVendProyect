package poo;
import java.util.List;

public class Combo {
    private String nombre;
    private Hamburguesa hamburguesa;
    private List<Extra> extras;
    private double costoBase;

    public Combo() {
        // Constructor vacío
    }

    public Combo(String nombre, Hamburguesa hamburguesa, List<Extra> extras, double costoBase) {
        this.nombre = nombre;
        this.hamburguesa = hamburguesa;
        this.extras = extras;
        if (costoBase >= 0) {
            this.costoBase = costoBase;
        } else {
            this.costoBase = 0; // O lanzar una excepción
        }
    }

    // Getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Hamburguesa getHamburguesa() {
        return hamburguesa;
    }

    public void setHamburguesa(Hamburguesa hamburguesa) {
        this.hamburguesa = hamburguesa;
    }

    public List<Extra> getExtras() {
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
        double total = costoBase + hamburguesa.calcularCosto(); // Sumar el costo de la hamburguesa
        for (Extra extra : extras) {
            total += extra.getCosto();
        }
        return total;
    }
}
