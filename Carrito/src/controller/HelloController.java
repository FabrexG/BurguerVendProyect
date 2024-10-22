package controller;

import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.fxml.FXML;
import java.net.URL;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.scene.image.ImageView;
import poo.Extra;
import poo.Ingrediente;
import poo.Pedido;
import poo.Hamburguesa;


public class HelloController implements Initializable {


    @FXML
    private ImageView img1;

    @FXML
    private ImageView img2;

    @FXML
    private ImageView img3;

    @FXML
    private DatePicker dateFecha;

    @FXML
    private TextArea txtDescripcion1;

    @FXML
    private TextArea txtDescripcion2;

    @FXML
    private TextArea txtDescripcion3;

    @FXML
    private TextField txtPedido;

    @FXML
    private TextField txtTotal;

    private Pedido pedidoActual;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dateFecha.setValue(LocalDate.now());
        generarNuevoPedido();
    }

    private void generarNuevoPedido() {
        pedidoActual = new Pedido(new Random().nextInt(1000) + 1);
        crearHamburguesas();

        txtPedido.setText(String.valueOf(pedidoActual.getNumeroPedido()));
        txtTotal.setText("$" + pedidoActual.calcularTotal());
    }

    private void crearHamburguesas() {
        // 1. Hamburguesa predefinida con extras
        Hamburguesa whopperConQueso = new Hamburguesa(
                "Whopper con Queso",
                Arrays.asList(new Ingrediente("Queso", 1.00), new Ingrediente("Carne", 2.50)),
                5.00
        );
        whopperConQueso.agregarExtra(new Extra("Papas", 1.50));
        whopperConQueso.agregarExtra(new Extra("Refresco", 1.00));

        // 2. Combo con extras
        Hamburguesa comboKingPollo = new Hamburguesa(
                "King de Pollo Guacamole",
                Arrays.asList(new Ingrediente("Pollo", 3.00), new Ingrediente("Guacamole", 1.50)),
                6.00
        );
        comboKingPollo.agregarExtra(new Extra("Refresco", 1.00));
        comboKingPollo.agregarExtra(new Extra("Helado", 2.00));

        // 3. Hamburguesa improvisada
        Hamburguesa personalizada = new Hamburguesa(
                "Hamburguesa Personalizada",
                Arrays.asList(new Ingrediente("Lechuga", 0.50), new Ingrediente("Tomate", 0.50), new Ingrediente("Carne", 2.50)),
                4.00
        );
        personalizada.agregarExtra(new Extra("Bacon", 1.50));

        // Agregar hamburguesas al pedido
        pedidoActual.agregarHamburguesa(whopperConQueso);
        pedidoActual.agregarHamburguesa(comboKingPollo);
        pedidoActual.agregarHamburguesa(personalizada);

        // Mostrar descripciones
        txtDescripcion1.setText(whopperConQueso.descripcion());
        txtDescripcion2.setText(comboKingPollo.descripcion());
        txtDescripcion3.setText(personalizada.descripcion());
    }
}
