package poo;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private int numeroPedido;
    private List<Hamburguesa> hamburguesas;

    public Pedido(int numeroPedido) {
        this.numeroPedido = numeroPedido;
        this.hamburguesas = new ArrayList<>();
    }

    public void agregarHamburguesa(Hamburguesa hamburguesa) {
        if (hamburguesas.size() < 3) {
            hamburguesas.add(hamburguesa);
        } else {
            System.out.println("No se pueden agregar más de 3 hamburguesas por pedido.");
        }
    }

    public void removerHamburguesa(Hamburguesa hamburguesa) {
        hamburguesas.remove(hamburguesa);
    }

    public double calcularTotal() {
        double total = 0; // Declarar la variable 'total' dentro del método
        for (Hamburguesa h : hamburguesas) {
            total += h.calcularCosto();
        }
        return total;
    }

    public int getNumeroPedido() {
        return numeroPedido;
    }

    // No es necesario un getter para el total, ya que se calcula en tiempo real
    // public double getTotal() {
    //     return total;
    // }
}
