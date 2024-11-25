package controller;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.ColorConstants;

import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;

import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.properties.TextAlignment;
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
import poo.*;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import org.knowm.xchart.PieChart;
import org.knowm.xchart.PieChartBuilder;
import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.BitmapEncoder.BitmapFormat;


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
     * @param rb El total a pagar.
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

        btnPagar.setDisable(true);
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

    private void generarTicketPDF(Pedido pedido) {
        try {
            // Crear un documento PDF
            PdfWriter writer = new PdfWriter("ticket_compra.pdf");
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            // Establecer fuente
            PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA);
            PdfFont boldFont = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);

            // Color de fondo rosa claro
            DeviceRgb colorRosaClaro = new DeviceRgb(255, 228, 225); // Rosa claro

            // Agregar logo
            ImageData logoData = ImageDataFactory.create(getClass().getResource("/img/logo.png"));
            Image logo = new Image(logoData);
            logo.scaleToFit(150, 150); // Ajustar tamaño del logo
            logo.setMarginBottom(20);
            document.add(logo);

            // Añadir contenido al PDF
            Paragraph titulo = new Paragraph("Ticket de Compra")
                    .setFont(boldFont)
                    .setFontSize(24)
                    .setTextAlignment(TextAlignment.CENTER)
                    .setMarginBottom(20)
                    .setBackgroundColor(colorRosaClaro) // Fondo rosa claro
                    .setFontColor(ColorConstants.BLACK);
            document.add(titulo);

            // Número de pedido
            Text numeroPedidoText = new Text("Número de pedido: " + pedido.getNumeroPedido())
                    .setFont(font)
                    .setFontSize(12);
            document.add(new Paragraph(numeroPedidoText));

            // Fecha y hora
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            Text fechaHoraText = new Text("Fecha y hora: " + pedido.getFechaPedido().format(formatter))
                    .setFont(font)
                    .setFontSize(12);
            document.add(new Paragraph(fechaHoraText).setMarginBottom(10));

            // Iterar sobre hamburguesas
            for (Hamburguesa hamburguesa : pedido.getHamburguesas()) {
                DeviceRgb colorHamburguesa = new DeviceRgb(200, 200, 200); // Gris claro
                Text nombreHamburguesa = new Text(hamburguesa.getNombre())
                        .setFont(boldFont)
                        .setFontSize(16)
                        .setBackgroundColor(colorHamburguesa)
                        .setFontColor(ColorConstants.BLACK);
                document.add(new Paragraph(nombreHamburguesa).setMarginBottom(5));

                // Ingredientes
                document.add(new Paragraph("Ingredientes:").setFont(font).setFontSize(12));
                for (Ingrediente ingrediente : hamburguesa.getIngredientes()) {
                    Text ingredienteText = new Text(ingrediente.getCantidad() + " " + ingrediente.getNombre() + " - $" + String.format("%.2f", ingrediente.getCosto()) + " c/u")
                            .setFont(font)
                            .setFontSize(12);
                    document.add(new Paragraph(ingredienteText));
                }

                // Extras
                document.add(new Paragraph("Extras:").setFont(font).setFontSize(12));
                for (Extra extra : hamburguesa.getExtras()) {
                    Text extraText = new Text(extra.getCantidad() + " " + extra.getNombre() + " - $" + String.format("%.2f", extra.getCosto()) + " c/u")
                            .setFont(font)
                            .setFontSize(12);
                    document.add(new Paragraph(extraText));
                }

                Text costoHamburguesa = new Text("Costo: $" + String.format("%.2f", hamburguesa.calcularCosto()))
                        .setFont(boldFont)
                        .setFontSize(12);
                document.add(new Paragraph(costoHamburguesa).setMarginBottom(10));
            }

            // Agregar gráfica de conteo de ingredientes y extras
            String graphPath = generarGraficaIngredientesExtras(pedido);
            ImageData graphData = ImageDataFactory.create(graphPath);
            Image graphImage = new Image(graphData);
            graphImage.scaleToFit(400, 300);
            document.add(graphImage);

            double total = pedido.calcularTotal();
            Text totalText = new Text("Total a pagar: $" + String.format("%.2f", total) + " MXN")
                    .setFont(boldFont)
                    .setFontSize(18)
                    .setFontColor(ColorConstants.WHITE)
                    .setBackgroundColor(new DeviceRgb(34, 139, 34)); // Verde oscuro
            document.add(new Paragraph(totalText).setMarginTop(10).setTextAlignment(TextAlignment.CENTER));

            document.close();
            Desktop.getDesktop().open(new File("ticket_compra.pdf"));

        } catch (IOException e) {
            System.err.println("Error al generar o abrir el ticket en PDF: " + e.getMessage());
        }
    }

    // Método para generar gráfica de ingredientes y extras
    private String generarGraficaIngredientesExtras(Pedido pedido) {
        Map<String, Integer> conteoIngredientes = new HashMap<>();
        Map<String, Integer> conteoExtras = new HashMap<>();

        for (Hamburguesa hamburguesa : pedido.getHamburguesas()) {
            for (Ingrediente ingrediente : hamburguesa.getIngredientes()) {
                conteoIngredientes.merge(ingrediente.getNombre(), ingrediente.getCantidad(), Integer::sum);
            }
            for (Extra extra : hamburguesa.getExtras()) {
                conteoExtras.merge(extra.getNombre(), extra.getCantidad(), Integer::sum);
            }
        }

        PieChart chart = new PieChartBuilder().width(800).height(600).title("Conteo Ingredientes y Extras").build();

        conteoIngredientes.forEach((nombre, cantidad) -> chart.addSeries(nombre + " (Ingrediente)", cantidad));
        conteoExtras.forEach((nombre, cantidad) -> chart.addSeries(nombre + " (Extra)", cantidad));

        String graphPath = "grafica_ticket.png";
        try {
            BitmapEncoder.saveBitmap(chart, graphPath, BitmapFormat.PNG);
        } catch (IOException e) {
            System.err.println("Error al generar gráfica: " + e.getMessage());
        }

        return graphPath;
    }

    // Método para convertir número a letras
    private String convertirNumeroALetras(double numero) {
        if (numero == 0) {
            return "Cero pesos MXN";
        } else if (numero < 0) {
            return "Menos " + convertirNumeroALetras(-numero);
        }

        String[] unidades = {"", "Un", "Dos", "Tres", "Cuatro", "Cinco", "Seis", "Siete", "Ocho", "Nueve"};
        String[] especiales = {"Diez", "Once", "Doce", "Trece", "Catorce", "Quince", "Dieciséis", "Diecisiete", "Dieciocho", "Diecinueve"};
        String[] decenas
 = {"", "Diez", "Veinte", "Treinta", "Cuarenta", "Cincuenta", "Sesenta", "Setenta", "Ochenta", "Noventa"};
        String[] centenas = {"", "Cien", "Doscientos", "Trescientos", "Cuatrocientos", "Quinientos", "Seiscientos", "Setecientos", "Ochocientos", "Novecientos"};


        int parteEntera = (int) numero;
        int parteDecimal = (int) Math.round((numero - parteEntera) * 100);

        String resultado = "";

        if (parteEntera >= 100) {
            resultado += centenas[parteEntera / 100] + " ";
            parteEntera %= 100;
        }
        if (parteEntera >= 20) {
            resultado += decenas[parteEntera / 10]
            + " ";
            parteEntera %= 10;
        }
        if (parteEntera >= 10 && parteEntera <= 19) {
            resultado += especiales[parteEntera - 10] + " ";
        } else if (parteEntera >= 1 && parteEntera <= 9) {
            resultado += unidades[parteEntera] + " ";
        }

        if (parteEntera > 1) {
            resultado += "Pesos ";
        } else {
            resultado += "Peso ";
        }

        if (parteDecimal > 0) {
            // Eliminar la recursividad y convertir solo las unidades
            String decimalEnLetras = unidades[parteDecimal % 10];
            if (parteDecimal >= 10 && parteDecimal <= 19) {
                decimalEnLetras = especiales[parteDecimal - 10];
            } else if (parteDecimal >= 20) {
                decimalEnLetras = decenas[parteDecimal / 10] + (parteDecimal % 10 != 0 ? " y " + unidades[parteDecimal % 10] : "");
            }
            resultado += "con " + decimalEnLetras + " centavos MXN";
        } else {
            resultado += "00/100 MXN";
        }

        return resultado;
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