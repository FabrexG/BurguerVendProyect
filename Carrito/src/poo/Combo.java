package poo;
import java.util.List;

public class Combo {
    private String nombre;
    private Hamburguesa hamburguesa;
    private List<Extra> extras;
    private double costoBase;

    public Combo(String nombre, Hamburguesa hamburguesa, List<Extra> extras, double costoBase) {
        this.nombre = nombre;
        this.hamburguesa = hamburguesa;
        this.extras = extras;
        this.costoBase = costoBase;
    }

    public double calcularCosto() {
        double total = costoBase;
        for (Extra extra : extras) {
            total += extra.getCosto();
        }
        return total;
    }
}
