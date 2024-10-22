
package poo;



import java.util.ArrayList;
import java.util.List;


public class Hamburguesa {
    private String nombre;
    private List<Ingrediente> ingredientes;
    private List<Extra> extras;
    private double costoBase;

    public Hamburguesa(String nombre, List<Ingrediente> ingredientes, double costoBase) {
        this.nombre = nombre;
        this.ingredientes = ingredientes;
        this.extras = new ArrayList<>();
        this.costoBase = costoBase;
    }

    public void agregarExtra(Extra extra) {
        extras.add(extra);
    }

    public String getNombre() {
        return nombre;
    }

    public List<Ingrediente> getIngredientes() {
        return ingredientes;
    }

    public List<Extra> getExtras() {
        return extras;
    }

    public double calcularCosto() {
        double costoTotal = costoBase;
        for (Extra extra : extras) {
            costoTotal += extra.getCosto();
        }
        return costoTotal;
    }

    public String descripcion() {
            StringBuilder desc = new StringBuilder();
            desc.append("Nombre: ").append(nombre).append("\n")
                    .append("Ingredientes: ");

            for (Ingrediente ing : ingredientes) {
                desc.append(ing.getNombre()).append(", ");
            }

            if (!ingredientes.isEmpty()) {
                desc.setLength(desc.length() - 2); // Quita la última coma
            }

            desc.append("\nExtras: ");

            if (!extras.isEmpty()) {
                for (Extra extra : extras) {
                    desc.append(extra.getNombre()).append(", ");
                }
                desc.setLength(desc.length() - 2); // Quita la última coma
            } else {
                desc.append("Ninguno");
            }

            desc.append("\nCosto: $").append(calcularCosto());
            return desc.toString();
        }

    }
