package Controladores;

import conexion.conectarBD;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

public class insertaVideo {

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Button btnVideo;

    @FXML
    void insertaVideo(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar Video");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivos de Video", "*.mp4", "*.avi", "*.mov"));

        File videoSeleccionado = fileChooser.showOpenDialog(null);
        if (videoSeleccionado != null) {
            try {
                conectarBD con = new conectarBD();
                if (con.cn == null || con.cn.isClosed()) {
                    con.conectarBDOracle();
                }
                String sql = "INSERT INTO videos (id_video, nombre_video, video) VALUES (videos_seq.NEXTVAL, ?, ?)";
                try (PreparedStatement stmt = con.cn.prepareStatement(sql)) {
                    stmt.setString(1, videoSeleccionado.getName());
                    stmt.setString(2, videoSeleccionado.getAbsolutePath()); // Guardar la ruta del archivo
                    stmt.executeUpdate();
                    try (FileInputStream fis = new FileInputStream(videoSeleccionado)) {
                            stmt.setBinaryStream(2, fis, (int) videoSeleccionado.length());
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(insertaVideo.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    stmt.executeUpdate();

                    Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                    alerta.setTitle("Video guardado");
                    alerta.setHeaderText(null);
                    alerta.setContentText("El video se ha guardado correctamente en la base de datos.");
                    alerta.showAndWait();
                } catch (SQLException ex) {
                    mostrarAlertaError("No se pudo guardar el video en la base de datos: " + ex.getMessage());
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