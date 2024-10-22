package controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.fxml.FXML;
import java.net.URL;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import poo.Extra;
import poo.Ingrediente;
import poo.Pedido;
import poo.Hamburguesa;


public class HelloController implements Initializable {

    @FXML
    private Button btnR1;

    @FXML
    private Button btnR2;

    @FXML
    private Button btnR3;


    @FXML
    private AnchorPane panControll;

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
                5.00, getClass().getResource("/img/1.png").toExternalForm()
        );
        whopperConQueso.agregarExtra(new Extra("Papas", 1.50));
        whopperConQueso.agregarExtra(new Extra("Refresco", 1.00));

        // 2. Combo con extras
        Hamburguesa comboKingPollo = new Hamburguesa(
                "King de Pollo Guacamole",
                Arrays.asList(new Ingrediente("Pollo", 3.00), new Ingrediente("Guacamole", 1.50)),
                6.00,getClass().getResource("/img/1.png").toExternalForm()
        );
        comboKingPollo.agregarExtra(new Extra("Refresco", 1.00));
        comboKingPollo.agregarExtra(new Extra("Helado", 2.00));

        // 3. Hamburguesa improvisada
        Hamburguesa personalizada = new Hamburguesa(
                "Hamburguesa Personalizada",
                Arrays.asList(new Ingrediente("Lechuga", 0.50), new Ingrediente("Tomate", 0.50), new Ingrediente("Carne", 2.50)),
                4.00,getClass().getResource("/img/1.png").toExternalForm()
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

       img1.setImage(whopperConQueso.getImagen());
        img2.setImage(comboKingPollo.getImagen());
        img3.setImage(personalizada.getImagen());
        
    }

    @FXML
    void remover1(ActionEvent event) {
        txtDescripcion1.clear(); // Limpia el contenido del TextArea
        img1.setImage(null); // Elimina la imagen
    }

    @FXML
    void remover2(ActionEvent event) {
        txtDescripcion2.clear(); // Limpia el contenido del TextArea
        img2.setImage(null); // Elimina la imagen

    }

    @FXML
    void remover3(ActionEvent event) {
        txtDescripcion3.clear(); // Limpia el contenido del TextArea
        img3.setImage(null); // Elimina la imagen

    }
}

