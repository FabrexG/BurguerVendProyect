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
import poo.Extra;
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


public class  CarritoController implements Initializable {
    private ConectaBD conn;

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

        // Mostrar el número de pedido
        this.txtPedido.setText(String.valueOf(this.pedidoActual.getNumeroPedido()));

        // Mostrar el total del pedido
        this.txtTotal.setText("$" + this.pedidoActual.calcularTotal());
    }


    private void removerHamburguesa(Hamburguesa hamburguesa) {
        try {
            // Obtener la conexión a la base de datos
            conn = new ConectaBD();
            conn.conectarBDOracle();

            // Eliminar las relaciones de la hamburguesa en las tablas HamburguesaIngrediente y HamburguesaExtra
            String sql = "DELETE FROM HamburguesaIngrediente WHERE hamburguesa_id = " + hamburguesa.getId();
            conn.stmt.executeUpdate(sql);
            sql = "DELETE FROM HamburguesaExtra WHERE hamburguesa_id = " + hamburguesa.getId();
            conn.stmt.executeUpdate(sql);

            // Eliminar la hamburguesa de la base de datos
            sql = "DELETE FROM Hamburguesa WHERE id = " + hamburguesa.getId();
            conn.stmt.executeUpdate(sql);

            // Devolver ingredientes y extras al inventario
            for (Ingrediente ingrediente : hamburguesa.getIngredientes()) {
                actualizarCantidadIngrediente(ingrediente.getId(), ingrediente.getCantidad(), "sumar");
            }
            for (Extra extra : hamburguesa.getExtras()) {
                actualizarCantidadExtra(extra.getId(), extra.getCantidad(), "sumar");
            }

            // Cerrar la conexión a la base de datos
            conn.cn.close();

        } catch (SQLException e) {
            System.err.println("Error al eliminar la hamburguesa de la base de datos: " + e.getMessage());
            e.printStackTrace();
            // Manejar la excepción adecuadamente (mostrar un mensaje de error al usuario)
        }
    }

@FXML
void remover1(ActionEvent event) {
    if (this.hamburguesa1 != null) {
        removerHamburguesa(this.hamburguesa1);

        // Limpiar la descripción, imagen y referencia a la hamburguesa1
        this.txtDescripcion1.clear();
        this.img1.setImage(null);
        this.hamburguesa1 = null;

        // Actualizar el total del pedido
        actualizarTotal();
    }
}

    @FXML
    void remover2(ActionEvent event) {
        if (this.hamburguesa2 != null) {
            removerHamburguesa(this.hamburguesa2);

            // Limpiar la descripción, imagen y referencia a la hamburguesa2
            this.txtDescripcion2.clear();
            this.img2.setImage(null);
            this.hamburguesa2 = null;

            // Actualizar el total del pedido
            actualizarTotal();
        }
    }

    @FXML
    void remover3(ActionEvent event) {
        if (this.hamburguesa3 != null) {
            removerHamburguesa(this.hamburguesa3);

            // Limpiar la descripción, imagen y referencia a la hamburguesa3
            this.txtDescripcion3.clear();
            this.img3.setImage(null);
            this.hamburguesa3 = null;

            // Actualizar el total del pedido
            actualizarTotal();
        }
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

    private Hamburguesa crearNuevaHamburguesa() {
        Hamburguesa nuevaHamburguesa = new Hamburguesa();
        try {
            // Obtener la conexión a la base de datos
            conn = new ConectaBD();
            conn.conectarBDOracle();

            // Obtener ingredientes aleatorios de la base de datos
            List<Ingrediente> ingredientes = obtenerIngredientesAleatorios();
            nuevaHamburguesa.setIngredientes(ingredientes);

            // Obtener extras aleatorios de la base de datos
            List<Extra> extras = obtenerExtrasAleatorios();
            nuevaHamburguesa.setExtras(extras);

            // Generar nombre y costo base aleatorios
            String nombre = generarNombreAleatorio();
            double costoBase = generarCostoBaseAleatorio();

            nuevaHamburguesa.setNombre(nombre);
            nuevaHamburguesa.setCostoBase(costoBase);
            nuevaHamburguesa.setImagen(new javafx.scene.image.Image("/img/Hamburguesa2.png")); // Reemplaza con la URL de tu imagen

            // Insertar la hamburguesa en la base de datos
            String sql = "INSERT INTO Hamburguesa (nombre, costoBase, rutaImagen) " +
                    "VALUES ('" + nuevaHamburguesa.getNombre() + "', " +
                    nuevaHamburguesa.getCostoBase() + ", '" +
                    nuevaHamburguesa.getImagen().getUrl() + "')";

            conn.stmt.executeUpdate(sql);

            // Obtener el ID de la hamburguesa insertada
            sql = "SELECT secuencia_hamburguesa.CURRVAL FROM dual";
            ResultSet rs = conn.stmt.executeQuery(sql);
            if (rs.next()) {
                nuevaHamburguesa.setId(rs.getInt(1));
            }

            // Insertar los ingredientes y actualizar cantidades
            for (Ingrediente ingrediente : nuevaHamburguesa.getIngredientes()) {
                int idIngrediente = obtenerIdIngrediente(ingrediente.getNombre());

                sql = "INSERT INTO HamburguesaIngrediente (hamburguesa_id, ingrediente_id, cantidad) " +
                        "VALUES (" + nuevaHamburguesa.getId() + ", " + idIngrediente + ", " + ingrediente.getCantidad() + ")";
                conn.stmt.executeUpdate(sql);

                // Actualizar cantidad del ingrediente en la base de datos
                actualizarCantidadIngrediente(ingrediente.getId(), ingrediente.getCantidad(), "restar");
            }

            // Insertar los extras y actualizar cantidades
            for (Extra extra : nuevaHamburguesa.getExtras()) {
                int idExtra = obtenerIdExtra(extra.getNombre());

                sql = "INSERT INTO HamburguesaExtra (hamburguesa_id, extra_id, cantidad) " +
                        "VALUES (" + nuevaHamburguesa.getId() + ", " + idExtra + ", " + extra.getCantidad() + ")";
                conn.stmt.executeUpdate(sql);

                // Actualizar cantidad del extra en la base de datos
                actualizarCantidadExtra(extra.getId(), extra.getCantidad(), "restar");
            }

            // Cerrar la conexión a la base de datos
            conn.cn.close();

        } catch (SQLException e) {
            System.err.println("Error al guardar la hamburguesa en la base de datos: " + e.getMessage());
            e.printStackTrace();
            // Manejar la excepción adecuadamente (mostrar un mensaje al usuario, etc.)
        }

        return nuevaHamburguesa;
    }
    private String generarNombreAleatorio() {
        List<String> nombres = List.of("Hamburguesa Clásica", "Hamburguesa Doble", "Hamburguesa con Queso",
                "Hamburguesa de Pollo", "Hamburguesa Vegetariana", "Hamburguesa Especial");
        Random random = new Random();
        int indice = random.nextInt(nombres.size());
        return nombres.get(indice);
    }

    private double generarCostoBaseAleatorio() {
        Random random = new Random();
        double costo = 5 + random.nextDouble() * 10; // Costo entre 5 y 15
        return Math.round(costo * 100.0) / 100.0; // Redondear a 2 decimales
    }
    private List<Ingrediente> obtenerIngredientesAleatorios() throws SQLException {
        List<Ingrediente> ingredientes = new ArrayList<>();
        String sql = "SELECT * FROM Ingrediente";
        ResultSet rs = conn.stmt.executeQuery(sql);
        List<Ingrediente> ingredientesDisponibles = new ArrayList<>();
        while (rs.next()) {
            ingredientesDisponibles.add(new Ingrediente(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getDouble("costo"),
                    rs.getInt("cantidad"),
                    rs.getString("unidad")
            ));
        }

        Random random = new Random();
        int numIngredientes = random.nextInt(3) + 2; // Entre 2 y 4 ingredientes

        for (int i = 0; i < numIngredientes; i++) {
            if (!ingredientesDisponibles.isEmpty()) {
                Ingrediente ingrediente = ingredientesDisponibles.remove(random.nextInt(ingredientesDisponibles.size()));
                ingrediente.setCantidad(1); // Cantidad fija de 1 por ingrediente
                ingredientes.add(ingrediente);
            }
        }

        return ingredientes;
    }


    private List<Extra> obtenerExtrasAleatorios() throws SQLException {
        List<Extra> extras = new ArrayList<>();
        String sql = "SELECT * FROM Extra";
        ResultSet rs = conn.stmt.executeQuery(sql);
        List<Extra> extrasDisponibles = new ArrayList<>();
        while (rs.next()) {
            extrasDisponibles.add(new Extra(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getDouble("costo"),
                    rs.getInt("cantidad")
            ));
        }

        Random random = new Random();
        int numExtras = random.nextInt(3); // Entre 0 y 2 extras

        for (int i = 0; i < numExtras; i++) {
            if (!extrasDisponibles.isEmpty()) {
                Extra extra = extrasDisponibles.remove(random.nextInt(extrasDisponibles.size()));
                extra.setCantidad(1); // Cantidad fija de 1 por extra
                extras.add(extra);
            }
        }

        return extras;
    }
    // Método para actualizar el total en la interfaz
    private void actualizarTotal() {
        // Actualizar el texto del TextField txtTotal con el nuevo total del pedido
        this.txtTotal.setText("$" + this.pedidoActual.calcularTotal());
    }
    private int obtenerIdIngrediente(String nombreIngrediente) throws SQLException {
        String sql = "SELECT id FROM Ingrediente WHERE nombre = '" + nombreIngrediente + "'";
        ResultSet rs = conn.stmt.executeQuery(sql);
        if (rs.next()) {
            return rs.getInt("id");
        } else {
            // El ingrediente no existe, manejar la situación adecuadamente
            // Puedes lanzar una excepción, registrar un error o mostrar un mensaje al usuario
            throw new SQLException("Ingrediente no encontrado: " + nombreIngrediente);
        }
    }

    private int obtenerIdExtra(String nombreExtra) throws SQLException {
        String sql = "SELECT id FROM Extra WHERE nombre = '" + nombreExtra + "'";
        ResultSet rs = conn.stmt.executeQuery(sql);
        if (rs.next()) {
            return rs.getInt("id");
        } else {
            // El extra no existe, manejar la situación adecuadamente
            throw new SQLException("Extra no encontrado: " + nombreExtra);
        }
    }

    private void actualizarCantidadIngrediente(int ingredienteId, int cantidad, String operacion) throws SQLException {
        String sql = "UPDATE Ingrediente SET cantidad = cantidad " +
                (operacion.equals("restar") ? "- " : "+ ") + cantidad +
                " WHERE id = " + ingredienteId;
        conn.stmt.executeUpdate(sql);
    }

    private void actualizarCantidadExtra(int extraId, int cantidad, String operacion) throws SQLException {
        String sql = "UPDATE Extra SET cantidad = cantidad " +
                (operacion.equals("restar") ? "- " : "+ ") + cantidad +
                " WHERE id = " + extraId;
        conn.stmt.executeUpdate(sql);
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