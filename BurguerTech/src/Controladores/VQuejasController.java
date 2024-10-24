package Controladores;

import conexion.conectarBD;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

public class VQuejasController implements Initializable {

    @FXML
    private ComboBox<String> ide_pregunta;
    @FXML
    private TextArea descripcionTextArea; 
    
    @FXML
    private StackPane stackPane;
    
    @FXML
    private Button btnVideo;
 
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ide_pregunta.getItems().add("Elige");
        ide_pregunta.getItems().addAll("Queja", "Sugerencia");
        ide_pregunta.setValue("Elige");

        ide_pregunta.setOnMouseClicked(event -> {
            ide_pregunta.show();
        });

        ide_pregunta.setOnAction(event -> {
            descripcionTextArea.clear();
        });
    }

    @FXML
    private void enviarQuejaSugerencia() throws DocumentException {
        conectarBD con = new conectarBD();

        try {
            con.conectarBDOracle();

            String tipo = ide_pregunta.getValue();
            String descripcion = descripcionTextArea.getText();

            // Mostrar la información en la consola
            System.out.println("Tipo: " + tipo);
            System.out.println("Descripcion: " + descripcion);

            // Crear un documento PDF
            Document documento = new Document();
            try {
                PdfWriter.getInstance(documento, new FileOutputStream("queja_sugerencia.pdf", true));
            } catch (FileNotFoundException ex) {
                //Logger.getLogger(VQuejasController.class.getName()).log(Level.SEVERE, null, ex);
            }
            documento.open();

            // Agregar un título al PDF
            documento.add(new Paragraph("Quejas y Sugerencias"));
            documento.add(new Paragraph(" "));

            // Agregar la información al documento
            documento.add(new Paragraph("Tipo: " + tipo));
            documento.add(new Paragraph("Descripcion: " + descripcion));

            // Cerrar el documento
            documento.close();

            // Abrir el PDF automáticamente
            if (Desktop.isDesktopSupported()) {
                try {
                    File myFile = new File("queja_sugerencia.pdf");
                    Desktop.getDesktop().open(myFile);
                } catch (IOException ex) {
                    // Manejar la excepción, por ejemplo, mostrar un mensaje de error
                    Alert alertaError = new Alert(Alert.AlertType.ERROR);
                    alertaError.setTitle("Error");
                    alertaError.setHeaderText(null);
                    alertaError.setContentText("No se pudo abrir el archivo PDF: " + ex.getMessage());
                    alertaError.showAndWait();
                }
            }
        } catch (SQLException e) {
            // Mostrar alerta de error
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error");
            alerta.setHeaderText(null);
            alerta.setContentText("Error al enviar la queja/sugerencia.");
            alerta.showAndWait();
        }
    }
    @FXML
    void InsertIm(MouseEvent event) throws IOException {
        Parent pay = FXMLLoader.load(getClass().getResource("/burguertech/VImagen.fxml"));
	stackPane.getChildren().setAll(pay);

    }
    
   @FXML
    void insertVideo(MouseEvent event) throws IOException {
        Parent pay = FXMLLoader.load(getClass().getResource("/burguertech/VVideo.fxml"));
	stackPane.getChildren().setAll(pay);
    }
}