package Controladores;

import com.lowagie.text.Document;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

public class insertaImagen {

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Button btnImagen;

    @FXML
    private void seleccionarImagen(ActionEvent event) {
        
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar Imagen");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Archivos de Imagen", "*.png", "*.jpg", "*.jpeg", "*.gif")
        );

        File archivoImagen = fileChooser.showOpenDialog(btnImagen.getScene().getWindow());

        if (archivoImagen != null) {
            try {
                // Guardar el PDF en la carpeta de documentos del usuario
                String userDir = System.getProperty("user.home");
                String pdfPath = Paths.get(userDir, "Documents", "imagen.pdf").toString();

                // Crear el documento PDF
                Document documento = new Document();
                PdfWriter.getInstance(documento, new FileOutputStream(pdfPath));
                documento.open();

                // Agregar la imagen al PDF y ajustar su tamaño
                Image imagen = Image.getInstance(archivoImagen.getAbsolutePath());
                
                // Escalar la imagen a un tamaño mediano (por ejemplo, la mitad del ancho y alto)
                imagen.scaleToFit(300, 300); // Ajusta el tamaño a 300x300 píxeles
                
                // Centrar la imagen en el documento PDF
                imagen.setAlignment(Image.ALIGN_CENTER);
                
                documento.add(imagen);

                // Agregar un párrafo con la ruta de la imagen
                documento.add(new Paragraph("Ruta de la imagen: " + archivoImagen.getAbsolutePath()));

                // Cerrar el documento
                documento.close();

                // Mostrar alerta de éxito
                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                alerta.setTitle("Queja/Sugerencia");
                alerta.setHeaderText(null);
                alerta.setContentText("La imagen se ha insertado con exito! "
                                    + "\n\nGracias por tu Queja/Sugerencia nos ayudas a mejorar :)");
                alerta.showAndWait();
            } catch (Exception ex) {
                Alert alertaError = new Alert(Alert.AlertType.ERROR);
                alertaError.setTitle("Error");
                alertaError.setHeaderText(null);
                alertaError.setContentText("No se pudo guardar la imagen en PDF: " + ex.getMessage());
                alertaError.showAndWait();
            }
        }
    }

    @FXML
    void regresar(MouseEvent event) throws IOException {
        Parent pay = FXMLLoader.load(getClass().getResource("/burguertech/VQuejas.fxml"));
        anchorPane.getChildren().setAll(pay);
    }
}
