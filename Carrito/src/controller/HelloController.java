package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import
        poo.Extra;
import poo.Ingrediente;
import poo.Pedido;
import poo.Hamburguesa;

import java.net.URL;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Random;
import java.util.ResourceBundle;

public class
HelloController implements Initializable {

    @FXML
    private
    Button btnA1;
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
    private Hamburguesa hamburguesa1;
    private Hamburguesa hamburguesa2;
    private Hamburguesa hamburguesa3;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Configurar los controles
        txtDescripcion1.setEditable(false);
        txtDescripcion2.setEditable(false);
        txtDescripcion3.setEditable(false);
        txtTotal.setEditable(false);
        txtPedido.setEditable(false);
        dateFecha.setEditable(false);

        // Establecer la fecha actual
        dateFecha.setValue(LocalDate.now());

        // Generar un nuevo pedido
        generarNuevoPedido();

        Tooltip tooltip = new Tooltip("Agregar una nueva hamburguesa");
        // Agregar tooltips a los botones "Remover"
        this.btnR1.setTooltip(new Tooltip("Remover la hamburguesa 1 del pedido"));
        this.btnR2.setTooltip(new Tooltip("Remover la hamburguesa 2 del pedido"));
        this.btnR3.setTooltip(new Tooltip("Remover la hamburguesa 3 del pedido"));


        // Agregar tooltips a los botones "Agregar"
        this.btnA1.setTooltip(new Tooltip("Agregar una nueva hamburguesa al espacio 1"));
        this.btnA2.setTooltip(new Tooltip("Agregar una nueva hamburguesa al espacio 2"));
        this.btnA3.setTooltip(new Tooltip("Agregar una nueva hamburguesa al espacio 3"));

    }

    private void generarNuevoPedido() {
        // Crear un nuevo pedido
        this.pedidoActual = new Pedido(new Random().nextInt(1000) + 1);

        // Crear las hamburguesas
        crearHamburguesas();

        // Mostrar el número de pedido
        this.txtPedido.setText(String.valueOf(this.pedidoActual.getNumeroPedido()));

        // Mostrar el total del pedido
        this.txtTotal.setText("$" + this.pedidoActual.calcularTotal());
    }

    private void crearHamburguesas() {
        // Crear la hamburguesa "Whopper"
        this.hamburguesa1 = new Hamburguesa(
                "Whopper",
                Arrays.asList(
                        new Ingrediente("Queso", 1.00, 2),
                        new Ingrediente("Carne", 2.50, 1)
                ),
                5.00, getClass().getResource("/img/Hamburguesa1.png").toExternalForm()
        );
        this.hamburguesa1.agregarExtra(new Extra("Papas", 1.50, 1));
        this.hamburguesa1.agregarExtra(new Extra("Refresco", 1.00, 1));

        // Crear la hamburguesa "King de Pollo Guacamole"
        this.hamburguesa2 = new Hamburguesa(
                "King de Pollo Guacamole",
                Arrays.asList(
                        new Ingrediente("Pollo", 3.00, 1),
                        new Ingrediente("Guacamole", 1.50, 1)
                ),
                5.00, getClass().getResource("/img/Hamburguesa4.png").toExternalForm()
        );
        this.hamburguesa2.agregarExtra(new Extra("Refresco", 1.00, 1));
        this.hamburguesa2.agregarExtra(new Extra("Helado", 2.00, 1));

        // Crear la hamburguesa "Hamburguesa Normal"
        this.hamburguesa3 = new Hamburguesa(
                "Hamburguesa Normal",
                Arrays.asList(
                        new Ingrediente("Lechuga", 0.50, 1),
                        new Ingrediente("Tomate", 0.50, 1),
                        new Ingrediente("Carne", 2.50, 1)
                ),
                4.00, getClass().getResource("/img/Hamburguesa3.png").toExternalForm()
        );
        this.hamburguesa3.agregarExtra(new Extra("Bacon", 1.50, 1));

        // Agregar las hamburguesas al pedido
        this.pedidoActual.agregarHamburguesa(this.hamburguesa1);
        this.pedidoActual.agregarHamburguesa(this.hamburguesa2);
        this.pedidoActual.agregarHamburguesa(this.hamburguesa3);

        // Mostrar las descripciones de las hamburguesas
        this.txtDescripcion1.setText(this.hamburguesa1.descripcion());
        this.txtDescripcion2.setText(this.hamburguesa2.descripcion());
        this.txtDescripcion3.setText(this.hamburguesa3.descripcion());

        // Mostrar las imágenes de las hamburguesas
        this.img1.setImage(this.hamburguesa1.getImagen());
        this.img2.setImage(this.hamburguesa2.getImagen());
        this.img3.setImage(this.hamburguesa3.getImagen());


    }

    @FXML
    void remover1(ActionEvent event) {
        // Remover la hamburguesa 1 del pedido
        this.pedidoActual.removerHamburguesa(this.hamburguesa1);

        // Limpiar la descripción y la imagen de la hamburguesa 1
        this.txtDescripcion1.clear();
        this.img1.setImage(null);

        // Limpiar la referencia a la hamburguesa 1
        this.hamburguesa1 = null;

        // Actualizar el total del pedido
        actualizarTotal();
    }

    @FXML
    void remover2(ActionEvent event) {
        // Remover la hamburguesa 2 del pedido
        this.pedidoActual.removerHamburguesa(this.hamburguesa2);

        // Limpiar la descripción y la imagen de la hamburguesa 2
        this.txtDescripcion2.clear();
        this.img2.setImage(null);

        // Limpiar la referencia a la hamburguesa 2
        this.hamburguesa2 = null;

        // Actualizar el total del pedido
        actualizarTotal();
    }

    @FXML
    void remover3(ActionEvent event) {
        // Remover la hamburguesa 3 del pedido
        this.pedidoActual.removerHamburguesa(this.hamburguesa3);

        // Limpiar la descripción y la imagen de la hamburguesa 3
        this.txtDescripcion3.clear();
        this.img3.setImage(null);

        // Limpiar la referencia a la hamburguesa 3
        this.hamburguesa3 = null;

        // Actualizar el total del pedido
        actualizarTotal();
    }

    @FXML
    void Agregar1(ActionEvent event) {
        // Verificar si la descripción e imagen de la hamburguesa 1 están vacías
        if (this.txtDescripcion1.getText().isEmpty() && this.img1.getImage() == null) {
            // Crear una nueva hamburguesa
            Hamburguesa nuevaHamburguesa = crearNuevaHamburguesa();

            // Asignar la nueva hamburguesa a la variable hamburguesa1
            this.hamburguesa1 = nuevaHamburguesa;

            // Agregar la nueva hamburguesa al pedido
            this.pedidoActual.agregarHamburguesa(nuevaHamburguesa);

            // Mostrar la descripción de la nueva hamburguesa
            this.txtDescripcion1.setText(nuevaHamburguesa.descripcion());

            // Mostrar la imagen de la nueva hamburguesa
            this.img1.setImage(nuevaHamburguesa.getImagen());

            // Actualizar el total del pedido
            actualizarTotal();
        }
    }

    @FXML
    void Agregar2(ActionEvent event) {
        // Verificar si la descripción e imagen de la hamburguesa 2 están vacías
        if (this.txtDescripcion2.getText().isEmpty() && this.img2.getImage() == null) {
            // Crear una nueva hamburguesa
            Hamburguesa nuevaHamburguesa = crearNuevaHamburguesa();

            // Asignar la nueva hamburguesa a la variable hamburguesa2
            this.hamburguesa2 = nuevaHamburguesa;

            // Agregar la nueva hamburguesa al pedido
            this.pedidoActual.agregarHamburguesa(nuevaHamburguesa);

            // Mostrar la descripción de la nueva hamburguesa
            this.txtDescripcion2.setText(nuevaHamburguesa.descripcion());

            // Mostrar la imagen de la nueva hamburguesa
            this.img2.setImage(nuevaHamburguesa.getImagen());

            // Actualizar el total del pedido
            actualizarTotal();
        }
    }

    @FXML
    void Agregar3(ActionEvent event) {
        // Verificar si la descripción e imagen de la hamburguesa 3 están vacías
        if (this.txtDescripcion3.getText().isEmpty() && this.img3.getImage() == null) {
            // Crear una nueva hamburguesa
            Hamburguesa nuevaHamburguesa = crearNuevaHamburguesa();

            // Asignar la nueva hamburguesa a la variable hamburguesa3
            this.hamburguesa3 = nuevaHamburguesa;

            // Agregar la nueva hamburguesa al pedido
            this.pedidoActual.agregarHamburguesa(nuevaHamburguesa);

            // Mostrar la descripción de la nueva hamburguesa
            this.txtDescripcion3.setText(nuevaHamburguesa.descripcion());

            // Mostrar la imagen de la nueva hamburguesa
            this.img3.setImage(nuevaHamburguesa.getImagen());

            // Actualizar el total del pedido
            actualizarTotal();
        }
    }

    // Método para crear una nueva hamburguesa (puedes personalizarlo)
    private Hamburguesa crearNuevaHamburguesa() {
        // Aquí puedes pedir los datos al usuario o generar una hamburguesa aleatoria
        return new Hamburguesa(
                "Nueva Hamburguesa",
                Arrays.asList(
                        new Ingrediente("Ingrediente 1", 1.00, 3), // 3 unidades de Ingrediente 1
                        new Ingrediente("Ingrediente 2", 2.00, 2)  // 2 unidades de Ingrediente 2
                ),
                4.50, getClass().getResource("/img/Hamburguesa1.png").toExternalForm()
        );
    }

    // Método para actualizar el total en la interfaz
    private void actualizarTotal() {
        // Actualizar el texto del TextField txtTotal con el nuevo total del pedido
        this.txtTotal.setText("$" + this.pedidoActual.calcularTotal());
    }
}
