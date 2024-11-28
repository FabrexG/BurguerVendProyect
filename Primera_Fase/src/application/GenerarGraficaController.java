package application;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import com.itextpdf.text.Document;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;



import conexion.ConectarBD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.SnapshotParameters;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextArea;
//import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.control.Button;

import javafx.scene.input.MouseEvent;

public class GenerarGraficaController {
	
   
	@FXML
    private Button btnInfo;
	@FXML
    private TextArea txtAreaInfo;
    @FXML
    private StackedBarChart<String, Number> Grafica;

    ObservableList<Hamburguesa> listaHamburguesa = FXCollections.observableArrayList();
    ConectarBD con = new ConectarBD();

    int whopperConQueso;
    int whopperBBQTocino;
    int tresQuesosYTocinoCrispy;
    int classicCrispyKing;
    int kingDePolloGuacamole;
    int whopperSensilla;

    public void initialize() {
    	 // Leer los datos de la base de datos
        leerDatosBaseDeDatos();
        txtAreaInfo.setEditable(false);
        // Configurar el eje X e Y
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Hamburguesas");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Ventas");

        // Configurar el título del gráfico
        Grafica.setTitle("Ventas Totales De Hamburguesas");
        
       

        // Crear series de datos
        XYChart.Series<String, Number> series1 = new XYChart.Series<>();
        series1.setName("Whopper con queso");
        series1.getData().add(new XYChart.Data<>("A", whopperConQueso));

        XYChart.Series<String, Number> series2 = new XYChart.Series<>();
        series2.setName("Whopper BBQ Tocino");
        series2.getData().add(new XYChart.Data<>("B", whopperBBQTocino));

        XYChart.Series<String, Number> series3 = new XYChart.Series<>();
        series3.setName("3 Quesos y Tocino Crispy");
        series3.getData().add(new XYChart.Data<>("C", tresQuesosYTocinoCrispy));

        XYChart.Series<String, Number> series4 = new XYChart.Series<>();
        series4.setName("Classic Crispy King");
        series4.getData().add(new XYChart.Data<>("D", classicCrispyKing));

        XYChart.Series<String, Number> series5 = new XYChart.Series<>();
        series5.setName("King de Pollo Guacamole");
        series5.getData().add(new XYChart.Data<>("E", kingDePolloGuacamole));

        XYChart.Series<String, Number> series6 = new XYChart.Series<>();
        series6.setName("Whopper Sensilla");
        series6.getData().add(new XYChart.Data<>("F", whopperSensilla));

        // Agregar las series al gráfico
        Grafica.getData().addAll(series1, series2, series3, series4, series5, series6);
    }

    public void leerDatosBaseDeDatos() {
    	 Map<String, Integer> contadorHamburguesas = new HashMap<>();

         
    	 String consultaSQL = "SELECT H.nombre, COUNT(P.id_Hamburguesa) AS cantidad "
                 + "FROM Hamburguesa H "
                 + "LEFT JOIN PedidosH P ON H.id_Hamburguesa = P.id_Hamburguesa "
                 + "GROUP BY H.nombre"; 
    	
    	
    	
        try {
             con.conectarBDOracle();
        	 PreparedStatement stmt = con.cn.prepareStatement(consultaSQL);
        	 con.rs = stmt.executeQuery();
        	

            StringBuilder contenido = new StringBuilder();

            while (con.rs.next()) {
                String nombreHamburguesa = con.rs.getString("nombre");
                int cantidad = con.rs.getInt("cantidad");
                contadorHamburguesas.put(nombreHamburguesa, cantidad);

                // Construir el contenido para el TextArea
                contenido.append("Hamburguesa: ")
                         .append(nombreHamburguesa)
                         .append(" - Veces ordenada: ")
                         .append(cantidad)
                         .append("\n");
            }

            // Asignar valores a variables específicas
            whopperConQueso = contadorHamburguesas.getOrDefault("Whopper con queso",0);
            whopperBBQTocino = contadorHamburguesas.getOrDefault("Whopper BBQ Tocino",0);
            tresQuesosYTocinoCrispy = contadorHamburguesas.getOrDefault("3 Quesos y Tocino Crispy King",0);
            classicCrispyKing = contadorHamburguesas.getOrDefault("Classic Crispy King",0);
            kingDePolloGuacamole = contadorHamburguesas.getOrDefault("King de Pollo Guacamole",0);
            whopperSensilla = contadorHamburguesas.getOrDefault("Whopper Sensilla",0);

            // Mostrar en el TextArea
            txtAreaInfo.setText(contenido.toString());

        } catch (Exception e) {
            System.err.println("Error al conectar o consultar la base de datos: " + e.getMessage());
        }
    }
 
    // Método para guardar la gráfica en PDF
    public void guardarGraficaEnPDF() {
        Document document = new Document();
        try {
            // Capturar la gráfica como una imagen
            WritableImage snapshot = Grafica.snapshot(new SnapshotParameters(), null);

            // Guardar la imagen en un archivo temporal
            File tempFile = new File("grafica.png");
            ImageIO.write(SwingFXUtils.fromFXImage(snapshot, null), "png", tempFile);

            // Crear un documento PDF
            PdfWriter.getInstance(document, new FileOutputStream("grafica.pdf"));
            document.open();
            //Agrega el titulo al pdf
            Paragraph titulo = new Paragraph("Graficas de ventas de Hamburguesas", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18));
            titulo.setAlignment(Paragraph.ALIGN_CENTER);
            Paragraph titulo2 = new Paragraph("********** Lista Pedidos de Hamburguesas **********",FontFactory.getFont(FontFactory.TIMES,14));
            titulo2.setAlignment(Paragraph.ALIGN_CENTER);
            Paragraph fin = new Paragraph("*******************************************",FontFactory.getFont(FontFactory.TIMES,14));
            fin.setAlignment(Paragraph.ALIGN_CENTER);
            // Agregar el contenido del TextArea al PDF
            String textoArea = txtAreaInfo.getText();
            Paragraph texto = new Paragraph(textoArea);
            texto.setAlignment(Paragraph.ALIGN_CENTER);
            // Cargar la imagen temporal en el PDF
            Image chartImage = Image.getInstance(tempFile.getAbsolutePath());
            chartImage.scaleToFit(500, 400); // Escalar la imagen
            document.add(titulo);
            document.add(new Paragraph("\n\n\n"));
            document.add(chartImage);
            document.add(new Paragraph("\n\n\n"));
            document.add(titulo2);
            document.add(new Paragraph("\n"));
            document.add(texto);
            document.add(new Paragraph("\n"));
            document.add(fin);

            document.close();
            tempFile.delete(); // Eliminar archivo temporal
            System.out.println("Gráfica guardada en PDF exitosamente.");
        } catch (Exception e) {
            System.err.println("Error al guardar la gráfica en PDF: " + e.getMessage());
        }
    }
    @FXML
    void btnGuardar_oneClicked(MouseEvent event) {
    	guardarGraficaEnPDF();

    }
}