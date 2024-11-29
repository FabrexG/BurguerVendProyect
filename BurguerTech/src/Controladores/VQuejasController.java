package Controladores;

import conexion.conectarBD;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import java.sql.Timestamp;


import java.io.*;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

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

        // Limpiar el área de descripción al seleccionar una opción en el ComboBox
        ide_pregunta.setOnAction(event -> {
            descripcionTextArea.clear();
        });

        // Programar la generación del PDF diario
        programarGeneracionPDF();
    }

    @FXML
    private void enviarQuejaSugerencia() {
    conectarBD con = new conectarBD();

    try {
        if (con.cn == null || con.cn.isClosed()) {
            con.conectarBDOracle();
        }

        // Obtener los datos ingresados por el usuario
        String tipo = ide_pregunta.getValue();
        String descripcion = descripcionTextArea.getText();

        // Validar entrada del usuario
        if (tipo == null || tipo.equals("Elige") || descripcion.trim().isEmpty()) {
            mostrarAlertaError("Debes seleccionar un tipo y escribir una descripción.");
            return;
        }


        // Usar la fecha y hora actual del dispositivo
        Timestamp fechaActual = new Timestamp(System.currentTimeMillis());

        // Guardar en la base de datos
        // SQL con solo las columnas necesarias (FECHA, TIPO, DESCRIPCION)
        // SQL con la secuencia para generar automáticamente el ID
        String sql = "INSERT INTO quejas_sugerencias (id, fecha, tipo, descripcion) VALUES (quejas_sugerencias_seq.NEXTVAL, ?, ?, ?)";
        try (PreparedStatement stmt = con.cn.prepareStatement(sql)) {
            stmt.setTimestamp(1, fechaActual);  // Usar Timestamp directamente
            stmt.setString(2, tipo);
            stmt.setString(3, descripcion);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            mostrarAlertaError("No se pudo guardar en la base de datos: " + ex.getMessage());
            return;
        }



        // Guardar en un archivo de texto
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("quejas_sugerencias.txt", true))) {
            writer.write("Fecha: " + fechaActual.toString());
            writer.newLine();
            writer.write("Tipo: " + tipo);
            writer.newLine();
            writer.write("Descripción: " + descripcion);
            writer.newLine();
            writer.write("--------------------------------------------------");
            writer.newLine();
        } catch (IOException ex) {
            mostrarAlertaError("No se pudo escribir en el archivo de texto: " + ex.getMessage());
        }

        // Mostrar confirmación
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Envío exitoso");
        alerta.setHeaderText(null);
        alerta.setContentText("Tu queja/sugerencia ha sido registrada con éxito.");
        alerta.showAndWait();

        // Limpiar los campos
        ide_pregunta.setValue("Elige");
        descripcionTextArea.clear();

    } catch (SQLException e) {
        mostrarAlertaError("Error al conectar a la base de datos: " + e.getMessage());
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

    public void generarPDFDiario() throws FileNotFoundException {
        try {
            // Obtener la fecha actual para el nombre del archivo PDF
            LocalDateTime fechaActual = LocalDateTime.now();
            DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd_MM_yyyy");
            String fechaParaArchivo = fechaActual.format(formatoFecha);

            // Crear el documento PDF
            Document documento = new Document();
            PdfWriter.getInstance(documento, new FileOutputStream("quejas_sugerencias_" + fechaParaArchivo + ".pdf"));
            documento.open();

            // Leer el contenido del archivo de texto y agregarlo al PDF
            try (BufferedReader reader = new BufferedReader(new FileReader("quejas_sugerencias.txt"))) {
                String linea;
                while ((linea = reader.readLine()) != null) {
                    documento.add(new Paragraph(linea));
                }
            } catch (IOException e) {
                mostrarAlertaError("No se pudo leer el archivo de texto: " + e.getMessage());
            }

            // Cerrar el documento PDF
            documento.close();

            // Limpiar el archivo de texto para el siguiente día
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("quejas_sugerencias.txt"))) {
                writer.write("");
            } catch (IOException e) {
                mostrarAlertaError("No se pudo limpiar el archivo de texto: " + e.getMessage());
            }

        } catch (DocumentException e) {
            mostrarAlertaError("No se pudo generar el PDF: " + e.getMessage());
        }
    }

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

        // Configurar la hora de ejecución para las 09:59 PM
        Calendar calendario = Calendar.getInstance();
        calendario.set(Calendar.HOUR_OF_DAY, 12);
        calendario.set(Calendar.MINUTE, 40);
        calendario.set(Calendar.SECOND, 0);

        // Programar la tarea para que se ejecute cada día a la misma hora
        temporizador.schedule(tarea, calendario.getTime(), TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS));
    }

    private void mostrarAlertaError(String mensaje) {
        Alert alertaError = new Alert(Alert.AlertType.ERROR);
        alertaError.setTitle("Error");
        alertaError.setHeaderText(null);
        alertaError.setContentText(mensaje);
        alertaError.showAndWait();
    }

    @FXML
    private void salirDelPrograma(ActionEvent event) {
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle("Confirmación");
        alerta.setHeaderText(null);
        alerta.setContentText("¿Estás seguro de que quieres salir?");
        Optional<ButtonType> resultado = alerta.showAndWait();
        if (resultado.get() == ButtonType.OK) {
            System.exit(0);
        }
    }
}
