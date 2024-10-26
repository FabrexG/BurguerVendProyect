package Controladores;

import java.io.File;
import java.io.IOException;
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
            // Aquí puedes agregar el código para procesar la imagen seleccionada
            // ...
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Información");
            alerta.setHeaderText(null);
            alerta.setContentText("Imagen seleccionada: " + archivoImagen.getAbsolutePath());
            alerta.showAndWait();
        }
    }

    @FXML
    void regresar(MouseEvent event) throws IOException {
        Parent pay = FXMLLoader.load(getClass().getResource("/burguertech/VQuejas.fxml"));
        anchorPane.getChildren().setAll(pay);
    }
}