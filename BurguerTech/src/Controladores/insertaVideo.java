package Controladores;

import conexion.conectarBD;
import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javafx.concurrent.Task; // For background tasks
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;

public class insertaVideo {

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Button btnVideo;
    @FXML
    private MediaView mediaView; // Linked to the MediaView in FXML
    private File videoSeleccionado; // Selected file

    @FXML
    void seleccionarVideo(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar Video");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivos de Video", "*.mp4", "*.avi", "*.mov"));

        videoSeleccionado = fileChooser.showOpenDialog(null);
        if (videoSeleccionado != null) {
            // Create Media and MediaPlayer to preview the selected video
            Media media = new Media(videoSeleccionado.toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            mediaView.setMediaPlayer(mediaPlayer);
            mediaPlayer.play(); // Play the video
        } else {
            mostrarAlertaError("No se seleccionó ningún video.");
        }
    }

    @FXML
    void insertaVideo(ActionEvent event) {
        if (videoSeleccionado != null) {
            // Connect to the database
            Task<Void> task = new Task<>() {
                @Override
                protected Void call() throws Exception {
                    try {
                        conectarBD con = new conectarBD();
                        if (con.cn == null || con.cn.isClosed()) {
                            con.conectarBDOracle();
                        }

                        // SQL statement to insert the video path
                        String sql = "INSERT INTO videos (id_video, nombre_video, video) VALUES (videos_seq.NEXTVAL, ?, ?)";
                        try (PreparedStatement stmt = con.cn.prepareStatement(sql)) {
                            stmt.setString(1, videoSeleccionado.getName());
                            stmt.setString(2, videoSeleccionado.getAbsolutePath()); // Store the path
                            stmt.executeUpdate();

                            // Show the alert on the UI thread
                            javafx.application.Platform.runLater(() -> {
                                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                                alerta.setTitle("Video guardado");
                                alerta.setHeaderText(null);
                                alerta.setContentText("El video se ha guardado correctamente en la base de datos.");
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