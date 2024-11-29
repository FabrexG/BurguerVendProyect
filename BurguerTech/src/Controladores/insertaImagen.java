package Controladores;

import conexion.conectarBD;
import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

public class insertaImagen {

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Button btnImagen;
    @FXML
    private ImageView idImagen;

    @FXML
    private void seleccionarImagen(ActionEvent event) {
        // Crea un FileChooser para seleccionar la imagen.
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar Imagen");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Archivos de Imagen", "*.png", "*.jpg", "*.jpeg", "*.gif")
        );

        // Muestra el diálogo y guarda el archivo seleccionado.
        File archivoImagen = fileChooser.showOpenDialog(btnImagen.getScene().getWindow());

        if (archivoImagen != null) {
            try {
                // Muestra la imagen en el ImageView.
                Image imagen = new Image(archivoImagen.toURI().toString());
                idImagen.setImage(imagen);

                // Crea una conexión a la base de datos.
                conectarBD con = new conectarBD();
                if (con.cn == null || con.cn.isClosed()) {
                    con.conectarBDOracle();
                }

                // Prepara la sentencia SQL para insertar la imagen.
                String sql = "INSERT INTO imagenes (id_imagen, nombre_imagen, imagen) VALUES (imagenes_seq.NEXTVAL, ?, ?)";
                try (PreparedStatement stmt = con.cn.prepareStatement(sql)) {
                    stmt.setString(1, archivoImagen.getName());
                    stmt.setString(2, archivoImagen.getAbsolutePath());
                    stmt.executeUpdate();

                    // Muestra una alerta informando que la imagen se guardó.
                    Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                    alerta.setTitle("Imagen guardada");
                    alerta.setHeaderText(null);
                    alerta.setContentText("La imagen se ha guardado correctamente en la base de datos.");
                    alerta.showAndWait();
                } catch (SQLException ex) {
                    mostrarAlertaError("No se pudo guardar la imagen: " + ex.getMessage());
                }
            } catch (SQLException ex) {
                mostrarAlertaError("Error al conectar a la base de datos: " + ex.getMessage());
            }
        }
    }

    @FXML
    void regresar(MouseEvent event) throws IOException {
        Parent pay = FXMLLoader.load(getClass().getResource("/burguertech/VQuejas.fxml"));
        anchorPane.getChildren().setAll(pay);
    }

    private void mostrarAlertaError(String mensaje) {
        Alert alertaError = new Alert(Alert.AlertType.ERROR);
        alertaError.setTitle("Error");
        alertaError.setHeaderText(null);
        alertaError.setContentText(mensaje);
        alertaError.showAndWait();
    }
}