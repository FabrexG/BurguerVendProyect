package poo;
import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private int numeroPedido;
    private List<Hamburguesa> hamburguesas;
    private double total;

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

    public double calcularTotal() {
        total = 0;
        for (Hamburguesa h : hamburguesas) {
            total += h.calcularCosto();
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
