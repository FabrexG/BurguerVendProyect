package dao;

import java.util.Date;

public class Cupon {

	private int id;
	private String clave;
	private Date fecha;
	private char status;
	private Date fechaCanje;
	private float descuento;
	
	public Cupon() {
		
	}
	
	public Cupon(int id, String clave, Date fecha, char status,float descuento) {
		this.id = id;
		this.clave = clave;
		this.fecha = fecha;
		this.status = status;
		 this.descuento = descuento;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	public Date getFechaCanje() {
		return fechaCanje;
	}

	public void setFechaCanje(Date fechaCanje) {
		this.fechaCanje = fechaCanje;
	}
    public float getDescuento() {
        return descuento;
    }

    public void setDescuento(float descuento) {
        this.descuento = descuento;
    }

	@Override
	public String toString() {
		return "Cupon [id=" + id + ", clave=" + clave + ", fecha=" + fecha + ", status=" + status + ", fechaCanje="
				+ fechaCanje + ", descuento=" + descuento +  "]";
	}
	
	
	
	
}
