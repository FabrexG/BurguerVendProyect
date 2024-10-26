package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * Controlador para la ventana de PagarPedido.
 */
public class PagarPedidoController {

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
    public void setTotal(double total) {
        this.totalAPagar = total;
        this.txtTotal.setText(String.format("$%.2f", total));
    }

    /**
     * Maneja el evento de clic en el botón "Pagar".
     * Muestra un mensaje de confirmación.
     *
     * @param event El evento de acción.
     */
    @FXML
    void btnPagar(ActionEvent event) {
        // Mostrar un mensaje de confirmación
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Pago Exitoso");
        alert.setHeaderText(null);
        alert.setContentText("¡Gracias por su compra! Su pedido ha sido pagado.");
        alert.showAndWait();
    }
}