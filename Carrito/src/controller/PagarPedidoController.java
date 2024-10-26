package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controlador para la ventana de PagarPedido.
 */
public class PagarPedidoController implements Initializable {

    @FXML
    private Button btnCarrito;
    @FXML
    private TextField txtTotal;
    @FXML
    private Button btnPagar;

    private double totalAPagar;

    /**
     * Establece el total a pagar y actualiza el TextField txtTotal.
     *
     * @param total El total a pagar.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Configurar los controles
        txtTotal.setEditable(false);

    }
    public void setTotal(double total) {
        this.totalAPagar = total; // Guardar el total en el atributo
        this.txtTotal.setText(String.format("$%.2f", this.totalAPagar));
    }

    /**
     * Maneja el evento de clic en el botón "Pagar".
     * Muestra un mensaje de confirmación.
     *
     * @param event El evento de acción.
     */
    @FXML
    void btnPagar(ActionEvent event) {
            // ... (código para procesar el pago) ...

            // Restablecer el total a cero
            setTotal(0);

            // Deshabilitar el botón del carrito
            btnCarrito.setDisable(true);

            // Mostrar un mensaje de confirmación
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Pago Exitoso");
        alert.setHeaderText(null);
        alert.setContentText("¡Gracias por su compra! Su pedido ha sido pagado.");
        alert.showAndWait();
    }

    /**
     * Maneja el evento de clic en el botón "Carrito".
     * Carga y muestra la ventana "Carrito".
     *
     * @param event El evento de acción.
     */
    @FXML
    void btnCarrito_Regresa(ActionEvent event) {
        try {
            // Obtener la ventana actual
            Stage stageActual = (Stage) btnCarrito.getScene().getWindow();

            // Cargar la ventana Carrito
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/Carrito.fxml"));
            Parent root = loader.load();

            // Crear la escena y mostrarla
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Carrito");
            stage.setResizable(false);
            stage.show();

            // Cerrar la ventana actual
            stageActual.close();

        } catch (IOException e) {
            System.err.println("Error al cargar la ventana Carrito: " + e.getMessage());
            e.printStackTrace();
        }
    }
}