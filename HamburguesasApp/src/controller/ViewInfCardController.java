package controller;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

import conexion.ConectarOracle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class ViewInfCardController {

	
	@FXML
    private TextField txtFecha;

    @FXML
    private TextField txtNumeroTarjeta;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtTitularTarjeta;
	
	@FXML
	private AnchorPane anchorPane;
	
   
	 @FXML
	    private Button btnRegrsar;
	 
	 
	 
	 @FXML
	    void goBack(MouseEvent event) throws IOException {
		 
		 Parent pay = FXMLLoader.load(getClass().getResource("/view/ViewPayCredit.fxml"));
			anchorPane.getChildren().setAll(pay);

	    }
	 
	   @FXML
	    void Pagar(MouseEvent event) {
		   String numeroTarjeta = txtNumeroTarjeta.getText();
	        String fechaExpiracion = txtFecha.getText();
	        String password = txtPassword.getText();
	        
	        // Validar la tarjeta
	        if (validarTarjeta(numeroTarjeta, fechaExpiracion, password)) {
	            mostrarAlertaPagoExitoso();  // Mostrar alerta de pago exitoso
	        } else {
	             mostrarAlertaPagoRechazado();// Mostrar alerta de pago rechazado
	        }
	    }

	    // Método para validar la tarjeta en la base de datos
	    private boolean validarTarjeta(String numeroTarjeta, String fechaExpiracion, String password) {
	        String query = "SELECT * FROM tarjetas WHERE numero_tarjeta = ? AND fecha_expiracion = ? AND password = ?";

	        try (Connection conn = new ConectarOracle().getConection();
	             PreparedStatement stmt = conn.prepareStatement(query)) {
	            
	            stmt.setString(1, numeroTarjeta);
	            stmt.setString(2, fechaExpiracion);
	            stmt.setString(3, password);
	            
	            try (ResultSet rs = stmt.executeQuery()) {
	                return rs.next();  // Si encuentra resultados, los datos son válidos
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;  // En caso de error, consideramos la tarjeta como no válida
	        }
	    }

	    // Método para mostrar alerta de pago exitoso
	    private void mostrarAlertaPagoExitoso() {
	        Alert alerta = new Alert(AlertType.INFORMATION);
	        alerta.setTitle("Pago Exitoso");
	        alerta.setHeaderText(null);
	        alerta.setContentText("El pago se ha realizado con éxito.");
	        alerta.showAndWait();
	        generarPDF();
	       
	       
	    }
	    
	    private void generarPDF() {
	    	 try {
	    	        // Crear un nuevo documento PDF
	    	        Document document = new Document();
	    	        String archivoPDF = "Factura_Pago_Exitoso_" + System.currentTimeMillis() + ".pdf";
	    	        PdfWriter.getInstance(document, new FileOutputStream(archivoPDF));
	    	        
	    	        // Abrir el documento
	    	        document.open();
	    	        
	    	        // Agregar una imagen (ajustar la ruta según el archivo)
	    	        String logoPath = "C:\\Users\\Jonha\\OneDrive\\Desktop\\ASEGURAMIENTO\\logo.jpg";  // Asegúrate de poner la ruta correcta
	    	        Image logo = Image.getInstance(logoPath);
	    	        logo.setAlignment(Element.ALIGN_CENTER);
	    	        logo.scaleToFit(100, 100);  // Ajusta el tamaño de la imagen
	    	        document.add(logo);
	    	        
	    	        // Agregar título centrado
	    	        Paragraph title = new Paragraph("Factura de Pago Exitoso", new Font(Font.HELVETICA, 16, Font.BOLD));
	    	        title.setAlignment(Element.ALIGN_CENTER);
	    	        document.add(title);
	    	        
	    	        // Separador
	    	        Paragraph separator = new Paragraph("===================================");
	    	        separator.setAlignment(Element.ALIGN_CENTER);
	    	        document.add(separator);

	    	        // Fecha y Hora
	    	        Paragraph fecha = new Paragraph("Fecha: " + java.time.LocalDate.now().toString());
	    	        fecha.setAlignment(Element.ALIGN_CENTER);
	    	        document.add(fecha);
	    	        
	    	        Paragraph hora = new Paragraph("Hora: " + java.time.LocalTime.now().toString());
	    	        hora.setAlignment(Element.ALIGN_CENTER);
	    	        document.add(hora);
	    	        
	    	        // Datos del titular
	    	        Paragraph titular = new Paragraph("Titular: " + txtTitularTarjeta.getText());
	    	        titular.setAlignment(Element.ALIGN_CENTER);
	    	        document.add(titular);
	    	        
	    	        // Dirección y Teléfono
	    	        Paragraph direccion = new Paragraph("Dirección: Calle Ficticia 123, Ciudad, País");
	    	        direccion.setAlignment(Element.ALIGN_CENTER);
	    	        document.add(direccion);
	    	        
	    	        Paragraph telefono = new Paragraph("Teléfono:56-20-07-73-58");
	    	        telefono.setAlignment(Element.ALIGN_CENTER);
	    	        document.add(telefono);
	    	        
	    	        Paragraph celular = new Paragraph("Celular: 01800-021344-11025");
	    	        celular.setAlignment(Element.ALIGN_CENTER);
	    	        document.add(celular);
	    	        
	    	        // Separador
	    	        Paragraph separator2 = new Paragraph("===================================");
	    	        separator2.setAlignment(Element.ALIGN_CENTER);
	    	        document.add(separator2);

	    	        // Mensaje final
	    	        Paragraph mensaje = new Paragraph("\n\nFavor de pasar por sus productos.");
	    	        mensaje.setAlignment(Element.ALIGN_CENTER);
	    	        document.add(mensaje);
	    	        
	    	        Paragraph gracias = new Paragraph("Gracias por su compra.", new Font(Font.HELVETICA, 12, Font.ITALIC));
	    	        gracias.setAlignment(Element.ALIGN_CENTER);
	    	        document.add(gracias);

	    	        // Cerrar el documento PDF
	    	        document.close();

	    	        // Abrir el archivo PDF automáticamente
	    	        File pdfFile = new File(archivoPDF);
	    	        if (pdfFile.exists()) {
	    	            Desktop.getDesktop().open(pdfFile);
	    	        }

	    	        // Redirigir a otra vista después de generar el PDF
	    	        Parent playView = FXMLLoader.load(getClass().getResource("/view/ViewPayCredit.fxml"));
	    	        anchorPane.getChildren().setAll(playView);

	    	    } catch (Exception e) {
	    	        e.printStackTrace();
	    	        // En caso de error al generar el PDF
	    	        Alert errorAlert = new Alert(AlertType.ERROR);
	    	        errorAlert.setTitle("Error al Generar PDF");
	    	        errorAlert.setHeaderText("Hubo un problema generando el archivo PDF.");
	    	        errorAlert.setContentText("Intente nuevamente más tarde.");
	    	        errorAlert.showAndWait();
	    	    }
	    }
	    private void mostrarAlertaPagoRechazado() {
	        Alert alerta = new Alert(AlertType.ERROR);
	        alerta.setTitle("Pago Rechazado");
	        alerta.setHeaderText("Datos incorrectos");
	        alerta.setContentText("La tarjeta o los datos ingresados son incorrectos. Por favor, revise e intente nuevamente.");
	        alerta.showAndWait();
	    }
	    
	  
	    
}
