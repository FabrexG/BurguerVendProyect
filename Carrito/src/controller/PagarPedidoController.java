package controller;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
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
import poo.DatosPedido;
import poo.Hamburguesa;
import poo.Pedido;

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
    private Pedido pedidoActual; // Agregar atributo para el pedido

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

    // Método para recibir el pedido actual
    public void setPedido(Pedido pedido) {
        this.pedidoActual = pedido;
    }

    /**
     * Maneja el evento de clic en el botón "Pagar".
     * Genera el ticket en PDF y muestra un mensaje de confirmación.
     *
     * @param event El evento de acción.
     */
    @FXML
    void btnPagar(ActionEvent event) {
        // Generar el ticket en PDF
        generarTicketPDF(this.pedidoActual);

        //código para procesar el pago

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

    // Método auxiliar para generar el ticket en PDF
    private void generarTicketPDF(Pedido pedido) {
        try {
            // Crear un documento PDF
            PdfWriter writer = new PdfWriter("ticket_compra.pdf");
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            // Añadir contenido al PDF
            document.add(new Paragraph("Ticket de Compra"));
            document.add(new Paragraph("Número de pedido: " + pedido.getNumeroPedido()));
            document.add(new Paragraph("Fecha: " + pedido.getFechaPedido()));

            for (Hamburguesa hamburguesa : pedido.getHamburguesas()) {
                document.add(new Paragraph(hamburguesa.getNombre()));
                // ... (añadir ingredientes y extras si es necesario)
                document.add(new Paragraph("Costo: $" + hamburguesa.calcularCosto()));
            }

            document.add(new Paragraph("Total a pagar: $" + String.format("%.2f", pedido.calcularTotal())));

            // Cerrar el documento PDF
            document.close();

        } catch (IOException e) {
            System.err.println("Error al generar el ticket en PDF: " + e.getMessage());
        }
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
            Stage stageActual = (Stage) this.btnCarrito.getScene().getWindow();

            // Obtener la instancia del controlador CarritoController desde DatosPedido
            CarritoController controladorCarrito = DatosPedido.controladorCarrito;

            // Obtener la escena del controlador CarritoController
            Scene scene = controladorCarrito.getScene(); // Llamar al método getScene()

            // Crear un nuevo Stage y mostrar la ventana (reutilizando la escena del controlador)
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Carrito");
            stage.setResizable(false);
            stage.show();

            // Cerrar la ventana actual
            stageActual.close();

        } catch (Exception e) {
            System.err.println("Error al cargar la ventana Carrito: " + e.getMessage());
            e.printStackTrace();
        }
    }
}