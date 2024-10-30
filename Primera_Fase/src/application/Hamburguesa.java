package application;

import java.util.ArrayList;
import java.util.List;

public class Hamburguesa {
    private String nombre;
    private List<Ingrediente> ingredientes;
    private List<Extra> extras;
    private double costoBase;
    private double costo;

    public Hamburguesa(String nombre, List<Extra> extras,List<Ingrediente> ingredientes, double costoBase,double costo) {
        this.nombre = nombre;
        this.ingredientes = ingredientes != null ? ingredientes : new ArrayList<>();
        this.extras = extras != null ? extras : new ArrayList<>();
        this.costoBase = costoBase;
        this.costo= costo;
        
    }
    
    
    
	public double getCosto() {
		return costo;
	}


	
	public void setCosto(double costo) {
		this.costo = costo;
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
            .append("Ingredientes:\n");
        if (!ingredientes.isEmpty()) {
            for (Ingrediente ing : ingredientes) {
                desc.append("- ").append(ing.getNombre()).append("\n");
                //desc.append(ing.getNombre()).append(", ");
            }
            //desc.setLength(desc.length() - 2); // Quita la última coma
        } else {
            desc.append("Ninguno");
        }
        desc.append("Costo Base: $").append(getCosto());
        desc.append("\nExtras:\n");
        if (!extras.isEmpty()) {
            for (Extra extra : extras) {
            	desc.append("- ").append(extra.getNombre()).append("\n");
            	//desc.append(extra.getNombre()).append(", ");
            }
            //desc.setLength(desc.length() - 2); // Quita la última coma
        } else {
            desc.append("Ninguno\n");
        }
        
        desc.append("Costo Final: $").append(calcularCosto());
        
        return desc.toString();
    }
}
