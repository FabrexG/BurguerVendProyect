package poo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Pedido {

    private int numeroPedido;
    private List<Hamburguesa> hamburguesas;
    private LocalDateTime fechaPedido;
    private String estado;

    /**
     * Constructor para la clase Pedido.
     *
     * @param numeroPedido El número del pedido.
     */
    public Pedido(int numeroPedido) {
        this.numeroPedido = numeroPedido;
        this.hamburguesas = new ArrayList<>();
        this.fechaPedido = LocalDateTime.now();
        this.estado = "recibido";
    }
    public List<Hamburguesa> getHamburguesas() {
        return this.hamburguesas;
    }
    /**
     * Agrega una hamburguesa al pedido.
     *
     * @param hamburguesa La hamburguesa a agregar.
     * @throws IllegalStateException Si se intenta agregar más de 3 hamburguesas.
     */
    public void agregarHamburguesa(Hamburguesa hamburguesa) {
        //if (this.hamburguesas.size() >= 3) {
           // throw new IllegalStateException("No se pueden agregar más de 3 hamburguesas por pedido.");
        //}
        this.hamburguesas.add(hamburguesa);
    }

    /**
     * Remueve una hamburguesa del pedido.
     *
     * @param hamburguesa La hamburguesa a remover.
     */
    public void removerHamburguesa(Hamburguesa hamburguesa) {

        this.hamburguesas.remove(hamburguesa);
    }

    /**
     * Calcula el total del pedido.
     *
     * @return El total del pedido.
     */
    public double calcularTotal() {
        double total = 0;
        for (Hamburguesa h : this.hamburguesas) {
            total += h.calcularCosto();
        }
        return total;
    }

    /**
     * Obtiene el número del pedido.
     *
     * @return El número del pedido.
     */
    public int getNumeroPedido() {
        return this.numeroPedido;
    }

    /**
     * Obtiene la fecha y hora del pedido.
     *
     * @return La fecha y hora del pedido.
     */
    public LocalDateTime getFechaPedido() {
        return this.fechaPedido;
    }

    /**
     * Obtiene el estado del pedido.
     *
     * @return El estado del pedido.
     */
    public String getEstado() {
        return this.estado;
    }

    /**
     * Establece el estado del pedido.
     *
     * @param estado El nuevo estado del pedido.
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }
}