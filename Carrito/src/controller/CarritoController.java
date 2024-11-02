package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import
        poo.Extra;
import poo.Ingrediente;
import poo.Pedido;
import poo.Hamburguesa;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;


import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Random;
import java.util.ResourceBundle;

import conexion.ConectaBD;
import javafx.scene.image.Image;


public class
CarritoController implements Initializable {


    @FXML
    private Button btnPagar;

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
    public Scene getScene() {
        return txtPedido.getScene(); // O cualquier otro elemento de la escena
    }
    private void generarNuevoPedido() {
        // Crear un nuevo pedido
        this.pedidoActual = new Pedido(new Random().nextInt(1000) + 1);

        // Crear las hamburguesas
        obtenerHamburguesasDeBD();

        // Mostrar el número de pedido
        this.txtPedido.setText(String.valueOf(this.pedidoActual.getNumeroPedido()));

        // Mostrar el total del pedido
        this.txtTotal.setText("$" + this.pedidoActual.calcularTotal());
    }
private void obtenerHamburguesasDeBD() {
    ConectaBD conectaBD = null;
    try {
        // Crear una instancia de ConectaBD
        conectaBD = new ConectaBD();
        conectaBD.conectarBDOracle();

        // Obtener las hamburguesas de la base de datos
        ResultSet rsHamburguesas = conectaBD.stmt.executeQuery("SELECT * FROM Hamburguesa");

        List<Hamburguesa> hamburguesas = new ArrayList<>();
        while (rsHamburguesas.next()) {
            Hamburguesa hamburguesa = new Hamburguesa();
            hamburguesa.setId(rsHamburguesas.getInt("id"));
            hamburguesa.setNombre(rsHamburguesas.getString("nombre"));
            hamburguesa.setCostoBase(rsHamburguesas.getDouble("costoBase"));
            hamburguesa.setImagen(new Image(getClass().getResourceAsStream(rsHamburguesas.getString("rutaImagen"))));

            // Obtener los ingredientes de la hamburguesa
            ResultSet rsIngredientes = conectaBD.stmt.executeQuery(
                "SELECT i.nombre, i.costo, hi.cantidad " +
                "FROM Ingrediente i " +
                "JOIN HamburguesaIngrediente hi ON i.id = hi.ingrediente_id " +
                "WHERE hi.hamburguesa_id = " + hamburguesa.getId()
            );
            List<Ingrediente> ingredientes = new ArrayList<>();
            while (rsIngredientes.next()) {
                ingredientes.add(new Ingrediente(
                    rsIngredientes.getString("nombre"),
                    rsIngredientes.getDouble("costo"),
                    rsIngredientes.getInt("cantidad")
                ));
            }
            hamburguesa.setIngredientes(ingredientes);

            // Obtener los extras de la hamburguesa
            ResultSet rsExtras = conectaBD.stmt.executeQuery(
                "SELECT e.nombre, e.costo, he.cantidad " +
                "FROM Extra e " +
                "JOIN HamburguesaExtra he ON e.id = he.extra_id " +
                "WHERE he.hamburguesa_id = " + hamburguesa.getId()
            );
            List<Extra> extras = new ArrayList<>();
            while (rsExtras.next()) {
                extras.add(new Extra(
                    rsExtras.getString("nombre"),
                    rsExtras.getDouble("costo"),
                    rsExtras.getInt("cantidad")
                ));
            }
            hamburguesa.setExtras(extras);

            hamburguesas.add(hamburguesa);
        }

        // Asignar las hamburguesas a las variables
        if (!hamburguesas.isEmpty()) {
            this.hamburguesa1 = hamburguesas.get(0);
            if (hamburguesas.size() > 1) {
                this.hamburguesa2 = hamburguesas.get(1);
            }
            if (hamburguesas.size() > 2) {
                this.hamburguesa3 = hamburguesas.get(2);
            }
        }

        // Mostrar las descripciones de las hamburguesas
        if (this.hamburguesa1 != null) {
            this.txtDescripcion1.setText(this.hamburguesa1.descripcion());
            this.img1.setImage(this.hamburguesa1.getImagen());
            this.pedidoActual.agregarHamburguesa(this.hamburguesa1);
        }
        if (this.hamburguesa2 != null) {
            this.txtDescripcion2.setText(this.hamburguesa2.descripcion());
            this.img2.setImage(this.hamburguesa2.getImagen());
            this.pedidoActual.agregarHamburguesa(this.hamburguesa2);
        }
        if (this.hamburguesa3 != null) {
            this.txtDescripcion3.setText(this.hamburguesa3.descripcion());
            this.img3.setImage(this.hamburguesa3.getImagen());
            this.pedidoActual.agregarHamburguesa(this.hamburguesa3);
        }

    } catch (SQLException e) {
        System.err.println("Error al obtener las hamburguesas de la base de datos: " + e.getMessage());
        e.printStackTrace();
    } finally {
        if (conectaBD != null) {
            try {
                conectaBD.cn.close(); // Cerrar la conexión en el finally
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexión a la base de datos: " + e.getMessage());
            }
        }
    }
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

    @FXML
    void btnPagar_OnClick(ActionEvent event) {
        try {
            // Obtener la ventana actual
            Stage stageActual = (Stage) btnPagar.getScene().getWindow();

            // Calcular el total del pedido ANTES de cerrar la ventana
            double totalPedido = this.pedidoActual.calcularTotal();

            // Cargar la ventana de pago
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/PagarPedido.fxml"));
            Parent root = loader.load();

            // Obtener el controlador de la ventana de pago
            PagarPedidoController controladorPago = loader.getController();

            // Pasar el total del pedido al controlador de la ventana de pago
            controladorPago.setTotal(totalPedido); // Pasar el total calculado

            // Crear la escena y mostrarla
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Pago del Pedido");
            stage.setResizable(false);
            stage.show();

            // Cerrar la ventana actual
            stageActual.close();

        } catch (IOException e) {
            System.err.println("Error al cargar la ventana de pago: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
