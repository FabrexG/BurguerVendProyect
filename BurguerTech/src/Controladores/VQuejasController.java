package Controladores;

import conexion.conectarBD;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
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
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

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

        // Programar la generación del PDF diario a las 11:59 PM
        programarGeneracionPDF();
    }

    @FXML
    private void enviarQuejaSugerencia() {
        conectarBD con = new conectarBD();

        try {
            con.conectarBDOracle();

            String tipo = ide_pregunta.getValue();
            String descripcion = descripcionTextArea.getText();

            // Obtener la fecha y hora actual
            LocalDateTime fechaActual = LocalDateTime.now();
            DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            String fechaFormateada = fechaActual.format(formatoFecha);

            // Escribir en el archivo de texto
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("quejas_sugerencias.txt", true))) {
                writer.write("Fecha: " + fechaFormateada);
                writer.newLine();
                writer.write("Tipo: " + tipo);
                writer.newLine();
                writer.write("Descripción: " + descripcion);
                writer.newLine();
                writer.write("--------------------------------------------------");
                writer.newLine();
            } catch (IOException ex) {
                // Manejar la excepción en caso de error al escribir en el archivo
                mostrarAlertaError("No se pudo escribir en el archivo de texto: " + ex.getMessage());
            }

        } catch (SQLException e) {
            // Mostrar alerta de error
            mostrarAlertaError("Error al enviar la queja/sugerencia.");
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

    // Método para generar el PDF y limpiar el archivo de texto
    public void generarPDFDiario() throws FileNotFoundException {
        try {
            // Obtener la fecha actual para el nombre del archivo
            LocalDateTime fechaActual = LocalDateTime.now();
            DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd_MM_yyyy");
            String fechaParaArchivo = fechaActual.format(formatoFecha);

            // Crear un documento PDF
            Document documento = new Document();
            PdfWriter.getInstance(documento, new FileOutputStream("quejas_sugerencias_" + fechaParaArchivo + ".pdf"));
            documento.open();

            // Leer el contenido del archivo de texto
            try (BufferedReader reader = new BufferedReader(new FileReader("quejas_sugerencias.txt"))) {
                String linea;
                while ((linea = reader.readLine()) != null) {
                    documento.add(new Paragraph(linea));
                }
            } catch (IOException e) {
                // Manejar la excepción en caso de error al leer el archivo
                mostrarAlertaError("No se pudo leer el archivo de texto: " + e.getMessage());
            }

            // Cerrar el documento
            documento.close();

            // Limpiar el archivo de texto
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("quejas_sugerencias.txt"))) {
                writer.write(""); // Sobrescribir el archivo con una cadena vacía
            } catch (IOException e) {
                // Manejar la excepción en caso de error al limpiar el archivo
                mostrarAlertaError("No se pudo limpiar el archivo de texto: " + e.getMessage());
            }

        } catch (DocumentException | FileNotFoundException e) {
            // Manejar la excepción en caso de error al generar el PDF
            mostrarAlertaError("No se pudo generar el PDF: " + e.getMessage());
        }
    }

    // Método para programar la generación del PDF diario
    private void programarGeneracionPDF() {
        Timer temporizador = new Timer();
        TimerTask tarea = new TimerTask() {
            @Override
            public void run() {
                try {
                    generarPDFDiario();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(VQuejasController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };

        // Programar la tarea para que se ejecute a las 11:59 PM
        Calendar calendario = Calendar.getInstance();
        calendario.set(Calendar.HOUR_OF_DAY, 23);
        calendario.set(Calendar.MINUTE, 59);
        calendario.set(Calendar.SECOND, 0);

        temporizador.schedule(tarea, calendario.getTime(), TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS));
    }

    // Método para mostrar alertas de error
    private void mostrarAlertaError(String mensaje) {
        Alert alertaError = new Alert(Alert.AlertType.ERROR);
        alertaError.setTitle("Error");
        alertaError.setHeaderText(null);
        alertaError.setContentText(mensaje);
        alertaError.showAndWait();
    }
}