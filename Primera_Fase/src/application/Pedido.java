package application;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private int numeroPedido;
    private List<Hamburguesa> hamburguesas;
    private List<Combo> combos;
    private double total;

    public Pedido(int numeroPedido) {
        this.numeroPedido = numeroPedido;
        this.hamburguesas = new ArrayList<>();
        this.combos = new ArrayList<>();
    }

    public void agregarHamburguesa(Hamburguesa hamburguesa) {
        if (hamburguesas.size() + combos.size() < 3) {
            hamburguesas.add(hamburguesa);
        } else {
            System.out.println("No se pueden agregar más de 3 elementos (hamburguesas o combos) por pedido.");
        }
    }

    public void agregarCombo(Combo combo) {
        if (hamburguesas.size() + combos.size() < 3) {
            combos.add(combo);
        } else {
            System.out.println("No se pueden agregar más de 3 elementos (hamburguesas o combos) por pedido.");
        }
    }

    public double calcularTotal() {
        total = 0;
        for (Hamburguesa h : hamburguesas) {
            total += h.calcularCosto();
        }
        for (Combo c : combos) {
            total += c.calcularPrecioTotal();
        }
        return total;
    }

    public int getNumeroPedido() {
        return numeroPedido;
    }

    public double getTotal() {
        return total;
    }
}
