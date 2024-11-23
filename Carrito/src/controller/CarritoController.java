    package controller;

    import javafx.fxml.FXML;
    import javafx.fxml.FXMLLoader;
    import javafx.fxml.Initializable;
    import javafx.scene.Parent;
    import javafx.scene.Scene;
    import javafx.scene.control.*;
    import javafx.scene.image.Image;
    import javafx.scene.image.ImageView;
    import javafx.scene.layout.AnchorPane;
    import javafx.scene.layout.HBox;
    import javafx.scene.layout.VBox;
    import javafx.scene.text.Text;
    import javafx.stage.Stage;
    import poo.Extra;
    import poo.Ingrediente;
    import poo.Pedido;
    import poo.Hamburguesa;

    import java.awt.event.ActionEvent;
    import java.net.URL;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.time.LocalDate;


    import java.util.ArrayList;
    import java.util.List;
    import java.io.IOException;
    import java.util.Random;
    import java.util.ResourceBundle;

    import conexion.ConectaBD;

    import javax.swing.*;


    public class  CarritoController implements Initializable {
        private ConectaBD conn;

        @FXML
        private Button btnAgregar;

        @FXML
        private Button btnPagar;

        @FXML
        private Button btnRemover;

        @FXML
        private DatePicker dateFecha;

        @FXML
        private HBox hamburguesasHBox;

        @FXML
        private AnchorPane panControll;

        @FXML
        private TextField txtPedido;

        @FXML
        private TextField txtTotal;


        private Pedido pedidoActual;
        private List<Hamburguesa> hamburguesas = new ArrayList<>();


        @Override
        public void initialize(URL url, ResourceBundle rb) {
            // Configurar los controles

            txtTotal.setEditable(false);
            txtPedido.setEditable(false);
            dateFecha.setEditable(false);

            // Establecer la fecha actual
            dateFecha.setValue(LocalDate.now());

            // Generar un nuevo pedido
            generarNuevoPedido();

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

        private void removerHamburguesaEspecifico(VBox hamburguesaVBox, Hamburguesa hamburguesa) {
            if (hamburguesas.contains(hamburguesa)) {
                hamburguesas.remove(hamburguesa);
                pedidoActual.removerHamburguesa(hamburguesa);
                hamburguesasHBox.getChildren().remove(hamburguesaVBox);
                actualizarTotal();

                // Remover la hamburguesa de la base de datos
                removerHamburguesaBD(hamburguesa);
            } else {
                System.out.println("hola"); // Manejar el caso en que la hamburguesa no se encuentre en la lista
            }
        }
        private void removerHamburguesaBD(Hamburguesa hamburguesa) {
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

            }
        }



        private Hamburguesa crearNuevaHamburguesa() {
            Hamburguesa nuevaHamburguesa = new Hamburguesa();
            try {
                // Obtener la conexión a la base de datos
                conn = new ConectaBD();
                conn.conectarBDOracle();

                // Obtener ingredientes y extras aleatorios de la base de datos
                List<Ingrediente> ingredientes = obtenerIngredientesAleatorios();
                List<Extra> extras = obtenerExtrasAleatorios();

                // Validar la cantidad de ingredientes y extras
                String productoFaltante = validarInventario(ingredientes, extras);
                if (productoFaltante == null) {
                    nuevaHamburguesa.setIngredientes(ingredientes);
                    nuevaHamburguesa.setExtras(extras);

                    // Generar nombre y costo base aleatorios
                    String nombre = generarNombreAleatorio();
                    double costoBase = generarCostoBaseAleatorio();

                    nuevaHamburguesa.setNombre(nombre);
                    nuevaHamburguesa.setCostoBase(costoBase);
                    nuevaHamburguesa.setImagen(new Image(obtenerRutaImagenAleatoria()));

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

                    return nuevaHamburguesa; // Devolver la hamburguesa si se creó correctamente

                } else {
                    // Mostrar un mensaje de error al usuario indicando el ingrediente o extra que falta
                    JOptionPane.showMessageDialog(null,
                            "No hay suficiente " + productoFaltante + " para crear esta hamburguesa.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return null;
                }

            } catch (SQLException e) {
                System.err.println("Error al guardar la hamburguesa en la base de datos: " + e.getMessage());
                // Manejar la excepción adecuadamente (mostrar un mensaje al usuario, etc.)
                return null;
            } finally {
                try {
                    // Cerrar la conexión a la base de datos en el bloque final
                    if (conn != null && !conn.cn.isClosed()) {
                        conn.cn.close();
                    }
                } catch (SQLException e) {
                    System.err.println("Error al cerrar la conexión a la base de datos: " + e.getMessage());
                }
            }
        }


        private String validarInventario(List<Ingrediente> ingredientes, List<Extra> extras) throws SQLException {
            for (Ingrediente ingrediente : ingredientes) {
                int cantidadDisponible = obtenerCantidadIngrediente(ingrediente.getNombre());
                if (ingrediente.getCantidad() > cantidadDisponible) {
                    return ingrediente.getNombre();
                }
            }
            for (Extra extra : extras) {
                int cantidadDisponible = obtenerCantidadExtra(extra.getNombre());
                if (extra.getCantidad() > cantidadDisponible) {
                    return extra.getNombre();
                }
            }
            return null;
        }


        private int obtenerCantidadIngrediente(String nombreIngrediente) throws SQLException {
            String sql = "SELECT cantidad FROM Ingrediente WHERE nombre = '" + nombreIngrediente + "'";
            ResultSet rs = conn.stmt.executeQuery(sql);
            if (rs.next()) {
                return rs.getInt("cantidad");
            } else {
                throw new SQLException("Ingrediente no encontrado: " + nombreIngrediente);
            }
        }

        private int obtenerCantidadExtra(String nombreExtra) throws SQLException {
            String sql = "SELECT cantidad FROM Extra WHERE nombre = '" + nombreExtra + "'";
            ResultSet rs = conn.stmt.executeQuery(sql);
            if (rs.next()) {
                return rs.getInt("cantidad");
            } else {
                throw new SQLException("Extra no encontrado: " + nombreExtra);
            }
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
                    // Generar cantidad aleatoria entre 1 y 5
                    int cantidadAleatoria = random.nextInt(5) + 1;
                    ingrediente.setCantidad(cantidadAleatoria); // Cantidad fija de 1 por ingrediente
                    ingredientes.add(ingrediente);
                }
            }

            return ingredientes;
        }


        private String obtenerRutaImagenAleatoria() {
            String[] rutasImagenes = {
                    "/img/Hamburguesa2.png",
                    "/img/Hamburguesa1.png",
                    "/img/Hamburguesa3.png"
            };

            Random random = new Random();
            int indiceAleatorio = random.nextInt(rutasImagenes.length);

            return rutasImagenes[indiceAleatorio];
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
                    int cantidadAleatoria = random.nextInt(5) + 1;
                    extra.setCantidad(cantidadAleatoria); // Cantidad fija de 1 por extra
                    extras.add(extra);
                }
            }

            return extras;
        }
        // Método para actualizar el total en la interfaz
        private void actualizarTotal() {
            // Formatear el total a dos decimales
            String totalFormateado = String.format("$%.2f", this.pedidoActual.calcularTotal());

            // Actualizar el texto del TextField txtTotal con el total formateado
            this.txtTotal.setText(totalFormateado);
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
        void btnPagar_OnClick() {
            if (pedidoActual.calcularTotal() != 0) {

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

                    // Pasar el pedido actual al controlador de la ventana de pago
                    controladorPago.setPedido(this.pedidoActual);

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
                }
            } else {
                JOptionPane.showMessageDialog(null, "Ordena una hamburguesa por lo menos");
            }
        }

        public void agregarHamburguesa(javafx.event.ActionEvent actionEvent) {
            Hamburguesa nuevaHamburguesa = crearNuevaHamburguesa();
            if (nuevaHamburguesa != null) {
                hamburguesas.add(nuevaHamburguesa);
                pedidoActual.agregarHamburguesa(nuevaHamburguesa);

                // Crear un nuevo VBox para la hamburguesa
                VBox hamburguesaVBox = new VBox(10);

                // Agregar la imagen
                ImageView imageView = new ImageView(nuevaHamburguesa.getImagen());
                imageView.setFitWidth(100);
                imageView.setFitHeight(100);
                hamburguesaVBox.getChildren().add(imageView);

                // Agregar la descripción
                Text descripcionText = new Text(nuevaHamburguesa.descripcion());
                hamburguesaVBox.getChildren().add(descripcionText);

                // Agregar un botón para remover la hamburguesa
                Button btnRemoverHamburguesa = new Button("Remover");
                // Llamar al método removerHamburguesaEspecifico
                btnRemoverHamburguesa.setOnAction(e -> removerHamburguesaEspecifico(hamburguesaVBox, nuevaHamburguesa));
                hamburguesaVBox.getChildren().add(btnRemoverHamburguesa);

                // Agregar el VBox al HBox
                hamburguesasHBox.getChildren().add(hamburguesaVBox);

                actualizarTotal();
            } else {
                System.out.println("");// Manejar el caso en que no se pudo crear la hamburguesa
            }
        }
    }
