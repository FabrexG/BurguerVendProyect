package Controladores;

import conexion.conectarBD;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

public class InsertaVideo {

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Button btnVideo;
    private File videoSeleccionado;

    @FXML
    void seleccionarVideo(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar Video");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivos de Video", "*.mp4", "*.avi", "*.mov"));

        videoSeleccionado = fileChooser.showOpenDialog(null);
        if (videoSeleccionado != null) {
            try {
                // Abre el video en el reproductor de video predeterminado del dispositivo
                Desktop.getDesktop().open(videoSeleccionado);
            } catch (IOException ex) {
                mostrarAlertaError("Error al abrir el video: " + ex.getMessage());
            }
        } else {
            mostrarAlertaError("No se seleccionó ningún video.");
        }
    }

    @FXML
    void insertaVideo(ActionEvent event) {
        if (videoSeleccionado != null) {
            Task<Void> task = new Task<>() {
                @Override
                protected Void call() throws Exception {
                    try {
                        conectarBD con = new conectarBD();
                        if (con.cn == null || con.cn.isClosed()) {
                            con.conectarBDOracle();
                        }

                        // Obtener la hora actual del sistema Java
                        Timestamp fechaHoraActual = new Timestamp(System.currentTimeMillis());

                        // Consulta SQL para insertar el NOMBRE del video en la base de datos
                        String sql = "INSERT INTO videos (id_video, nombre_video, fecha_hora) VALUES (videos_seq.NEXTVAL, ?, ?)";
                        try (PreparedStatement stmt = con.cn.prepareStatement(sql)) {
                            stmt.setString(1, videoSeleccionado.getName());
                            stmt.setTimestamp(2, fechaHoraActual); // Enviar la hora como parámetro
                            stmt.executeUpdate();

                            con.cn.commit(); // Confirmar la transacción si no se usa autocommit

                            javafx.application.Platform.runLater(() -> {
                                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                                alerta.setTitle("Video guardado");
                                alerta.setHeaderText(null);
                                alerta.setContentText("El NOMBRE del video se ha guardado correctamente en la base de datos con fecha y hora.");
                                alerta.showAndWait();
                            });
                        } catch (SQLException ex) {
                            javafx.application.Platform.runLater(() -> mostrarAlertaError("Error al insertar el video en la base de datos: " + ex.getMessage()));
                        }
                    } catch (SQLException ex) {
                        javafx.application.Platform.runLater(() -> mostrarAlertaError("Error al conectar a la base de datos: " + ex.getMessage()));
                    }
                    return null;
                }
            };

            Thread thread = new Thread(task);
            thread.start();
        } else {
            mostrarAlertaError("Debes seleccionar un video antes de subirlo.");
        }
    }

    private void mostrarAlertaError(String mensaje) {
        Alert alertaError = new Alert(Alert.AlertType.ERROR);
        alertaError.setTitle("Error");
        alertaError.setHeaderText(null);
        alertaError.setContentText(mensaje);
        alertaError.showAndWait();
    }

    @FXML
    void regresar(MouseEvent event) throws IOException {
        Parent pay = FXMLLoader.load(getClass().getResource("/burguertech/VQuejas.fxml"));
        anchorPane.getChildren().setAll(pay);
    }
}