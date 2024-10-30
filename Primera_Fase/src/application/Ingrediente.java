package application;

public class Ingrediente {
	 private String nombre;
	 private double costo;
       
	 public Ingrediente() {
		 
	 }
	    public Ingrediente(String nombre, double costo) {
	        this.nombre = nombre;
	        this.costo = costo;
	    }
        
	    public String getNombre() {
	        return nombre;
	    }

	    public double getCosto( ) {
	        return costo;
	    }
	    public void setNombre(String nombre) {
	        this.nombre = nombre;
	    }

	    public void setCosto(double costo) {
	        this.costo = costo;
	    }
	 // Nuevo método para calcular el costo con un valor adicional
	    public double calcularCosto(double factor) {
	        return factor;
	    }

	    public String toString() {
	        return nombre + " ($" + costo + ")";
	    }
	
}
