package application;

import java.util.List;

public class Combo {
    private String nombre;
    private int precioBase;
    private List<Extra> extras;
    private List<Ingrediente> ingredientes;
    private int precio;

    public Combo(String nombre, int precioBase,int precio,List<Ingrediente> ingredientes, List<Extra> extras) {
        this.nombre = nombre;
        this.precioBase = precioBase;
        this.precio = precio;
        this.ingredientes = ingredientes;
        this.extras = extras;
    }
   
    public String getNombre() {
        return nombre;
    }

    public int getPrecioBase() {
        return precioBase;
    }

    public List<Extra> getExtras() {
        return extras;
    }
    
    public List<Ingrediente> getIngredientes() {
		return ingredientes;
	}

	
	public void setIngredientes(List<Ingrediente> ingredientes) {
		this.ingredientes = ingredientes;
	}
	
	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

	public int calcularPrecioTotal() {
        int total = precioBase;
        for (Extra ingrediente : extras) {
            total += ingrediente.getCosto();
        }
        return total;
    }
    public String descripcionCompleta() {
        StringBuilder descripcion = new StringBuilder();
        descripcion.append("Nombre : ").append(nombre).append("\n");
        descripcion.append("Ingredientes:\n");
        for (Ingrediente ingrediente : ingredientes) {
        	descripcion.append("- ").append(ingrediente.getNombre()).append("\n");
        }
        descripcion.append("Precio Base: $").append(getPrecio());
        
        descripcion.append("\nComplementos:\n");
        for (Extra extra : extras) {
            descripcion.append("- ").append(extra.toString()).append("\n");
        }

        descripcion.append("Precio total: $").append(getPrecioBase()).append("\n");

        return descripcion.toString();
    }
}
