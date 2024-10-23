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
    private Button btnA1;

    @FXML
    private Button btnA2;

    @FXML
    private Button btnA3;


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

    // Declarar las variables de las hamburguesas aquí
    private Hamburguesa hamburguesa1;
    private Hamburguesa hamburguesa2;
    private Hamburguesa hamburguesa3;

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
        hamburguesa1 = new Hamburguesa(
                "Whopper con Queso",
                Arrays.asList(new Ingrediente("Queso", 1.00), new Ingrediente("Carne", 2.50)),
                5.00, getClass().getResource("/img/1.png").toExternalForm()
        );
        hamburguesa1.agregarExtra(new Extra("Papas", 1.50));
        hamburguesa1.agregarExtra(new Extra("Refresco", 1.00));

        // 2. Combo con extras
        hamburguesa2 = new Hamburguesa(
                "King de Pollo Guacamole",
                Arrays.asList(new Ingrediente("Pollo", 3.00), new Ingrediente("Guacamole", 1.50)),
                6.00,getClass().getResource("/img/2.png").toExternalForm()
        );
        hamburguesa2.agregarExtra(new Extra("Refresco", 1.00));
        hamburguesa2.agregarExtra(new Extra("Helado", 2.00));

        // 3. Hamburguesa improvisada
        hamburguesa3 = new Hamburguesa(
                "Hamburguesa Personalizada",
                Arrays.asList(new Ingrediente("Lechuga", 0.50), new Ingrediente("Tomate", 0.50), new Ingrediente("Carne", 2.50)),
                4.00,getClass().getResource("/img/3.png").toExternalForm()
        );
        hamburguesa3.agregarExtra(new Extra("Bacon", 1.50));

        // Agregar hamburguesas al pedido
        pedidoActual.agregarHamburguesa(hamburguesa1);
        pedidoActual.agregarHamburguesa(hamburguesa2);
        pedidoActual.agregarHamburguesa(hamburguesa3);

        // Mostrar descripciones
        txtDescripcion1.setText(hamburguesa1.descripcion());
        txtDescripcion2.setText(hamburguesa2.descripcion());
        txtDescripcion3.setText(hamburguesa3.descripcion());

        img1.setImage(hamburguesa1.getImagen());
        img2.setImage(hamburguesa2.getImagen());
        img3.setImage(hamburguesa3.getImagen());

    }

    @FXML
    void remover1(ActionEvent event) {
        pedidoActual.removerHamburguesa(hamburguesa1);
        txtDescripcion1.clear();
        img1.setImage(null);
        hamburguesa1 = null; // Limpiar la referencia
        actualizarTotal();
    }

    @FXML
    void remover2(ActionEvent event) {
        pedidoActual.removerHamburguesa(hamburguesa2);
        txtDescripcion2.clear();
        img2.setImage(null);
        hamburguesa2 = null; // Limpiar la referencia
        actualizarTotal();
    }

    @FXML
    void remover3(ActionEvent event) {
        pedidoActual.removerHamburguesa(hamburguesa3);
        txtDescripcion3.clear();
        img3.setImage(null);
        hamburguesa3 = null; // Limpiar la referencia
        actualizarTotal();
    }

    @FXML
    void Agregar1(ActionEvent event) {
        if (txtDescripcion1.getText().isEmpty() && img1.getImage() == null) {
            Hamburguesa nuevaHamburguesa = crearNuevaHamburguesa();
            hamburguesa1 = nuevaHamburguesa;
            pedidoActual.agregarHamburguesa(nuevaHamburguesa);
            txtDescripcion1.setText(nuevaHamburguesa.descripcion());
            img1.setImage(nuevaHamburguesa.getImagen());
            actualizarTotal();
        }
    }

    @FXML
    void Agregar2(ActionEvent event) {
        if (txtDescripcion2.getText().isEmpty() && img2.getImage() == null) {
            Hamburguesa nuevaHamburguesa = crearNuevaHamburguesa();
            hamburguesa2 = nuevaHamburguesa;
            pedidoActual.agregarHamburguesa(nuevaHamburguesa);
            txtDescripcion2.setText(nuevaHamburguesa.descripcion());
            img2.setImage(nuevaHamburguesa.getImagen());
            actualizarTotal();
        }
    }

    @FXML
    void Agregar3(ActionEvent event) {
        if (txtDescripcion3.getText().isEmpty() && img3.getImage() == null) {
            Hamburguesa nuevaHamburguesa = crearNuevaHamburguesa();
            hamburguesa3 = nuevaHamburguesa;
            pedidoActual.agregarHamburguesa(nuevaHamburguesa);
            txtDescripcion3.setText(nuevaHamburguesa.descripcion());
            img3.setImage(nuevaHamburguesa.getImagen());
            actualizarTotal();
        }
    }

    // Método para crear una nueva hamburguesa (puedes personalizarlo)
    private Hamburguesa crearNuevaHamburguesa() {
        // Aquí puedes pedir los datos al usuario o generar una hamburguesa aleatoria
        return new Hamburguesa(
                "Nueva Hamburguesa",
                Arrays.asList(new Ingrediente("Ingrediente 1", 1.00), new Ingrediente("Ingrediente 2", 2.00)),
                4.50, getClass().getResource("/img/1.png").toExternalForm() // Reemplaza con la imagen correcta
        );
    }

    // Método para actualizar el total en la interfaz
    private void actualizarTotal() {
        txtTotal.setText("$" + pedidoActual.calcularTotal());
    }
}
