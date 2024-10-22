package carrito;

import poo.Hamburguesa;
import poo.Ingrediente;
import poo.Pedido;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Ingrediente lechuga = new Ingrediente("Lechuga", 0.50);
        Ingrediente tomate = new Ingrediente("Tomate", 0.50);
        Ingrediente queso = new Ingrediente("Queso", 1.00);
        Ingrediente carne = new Ingrediente("Carne", 2.50);

        Hamburguesa whopperConQueso = new Hamburguesa(
                "Whopper con Queso",
                Arrays.asList(lechuga, tomate, queso, carne),
                5.00
        );

        Hamburguesa whopperBbqTocino = new Hamburguesa(
                "Whopper BBQ Tocino",
                Arrays.asList(lechuga, tomate, queso, carne),
                6.00
        );

        Pedido pedido = new Pedido(1);
        pedido.agregarHamburguesa(whopperConQueso);
        pedido.agregarHamburguesa(whopperBbqTocino);

        System.out.println("Total del pedido: $" + pedido.calcularTotal());
    }
}

