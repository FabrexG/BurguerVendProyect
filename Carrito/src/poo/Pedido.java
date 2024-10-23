package poo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private int numeroPedido;
    private
    List<Hamburguesa> hamburguesas;
    private LocalDateTime fechaPedido;
    private String estado;

    public Pedido(int numeroPedido) {
        this.numeroPedido = numeroPedido;
        this.hamburguesas = new ArrayList<>();
        this.fechaPedido = LocalDateTime.now();
        this.estado = "recibido";
    }

    public void agregarHamburguesa(Hamburguesa hamburguesa) {
        if (hamburguesas.size() >= 3) {
            throw new IllegalStateException("No se pueden agregar más de 3 hamburguesas por pedido.");
        }
        hamburguesas.add(hamburguesa);
    }

    public void removerHamburguesa(Hamburguesa hamburguesa) {
        hamburguesas.remove(hamburguesa);
    }

    public double calcularTotal() {
        double total = 0;
        for (Hamburguesa h : hamburguesas) {
            total += h.calcularCosto();
        }
        return total;
    }

    public int getNumeroPedido() {
        return numeroPedido;
    }

    public LocalDateTime getFechaPedido() {
        return fechaPedido;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}