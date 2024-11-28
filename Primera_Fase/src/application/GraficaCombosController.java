package application;

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
import javafx.scene.image.WritableImage;

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
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

import conexion.ConectarBD;

public class GraficaCombosController{
	@FXML
    private Button btnGuardar;

    @FXML
    private StackedBarChart<String, Number> Grafica;

    @FXML
    private TextArea txtAreaInfo;
    
    ObservableList<Combo> listaCombo = FXCollections.observableArrayList();
    ConectarBD con = new ConectarBD();
    
    int combo1;
    int combo2;
    int combo3;
    int combo4;
    int combo5;
    
    
    public void initialize() {
   	 // Leer los datos de la base de datos
       leerDatosBaseDeDatos();
       txtAreaInfo.setEditable(false);
       // Configurar el eje X e Y
       CategoryAxis xAxis = new CategoryAxis();
       xAxis.setLabel("Combos");

       NumberAxis yAxis = new NumberAxis();
       yAxis.setLabel("Ventas");

       // Configurar el título del gráfico
       Grafica.setTitle("Ventas Totales De Combos");
       
      

       // Crear series de datos
       XYChart.Series<String, Number> series1 = new XYChart.Series<>();
       series1.setName("Combo Whoper Doble con Queso");
       series1.getData().add(new XYChart.Data<>("A", combo1));

       XYChart.Series<String, Number> series2 = new XYChart.Series<>();
       series2.setName("Combo Stacker Chipoootle Res");
       series2.getData().add(new XYChart.Data<>("B", combo2));

       XYChart.Series<String, Number> series3 = new XYChart.Series<>();
       series3.setName("Combo Whoper Angry Doble");
       series3.getData().add(new XYChart.Data<>("C", combo3));

       XYChart.Series<String, Number> series4 = new XYChart.Series<>();
       series4.setName("Combo Long Rodeo");
       series4.getData().add(new XYChart.Data<>("D", combo4));

       XYChart.Series<String, Number> series5 = new XYChart.Series<>();
       series5.setName("Combo Spicy Ranch Crispy King");
       series5.getData().add(new XYChart.Data<>("E", combo5));

      
       // Agregar las series al gráfico
       Grafica.getData().addAll(series1, series2, series3, series4, series5);
   }
    
    public void leerDatosBaseDeDatos() {
   	 Map<String, Integer> contadorCombo = new HashMap<>();

        
   	String consultaSQL = "SELECT C.nombre, COUNT(P.id_Combo) AS cantidad "
            + "FROM Combo C "
            + "LEFT JOIN PedidosC P ON C.id_Combo = P.id_Combo "
            + "GROUP BY C.nombre"; 
   	
   	
   	
       try {
         con.conectarBDOracle();
       	 PreparedStatement stmt = con.cn.prepareStatement(consultaSQL);
       	 con.rs = stmt.executeQuery();
       	

           StringBuilder contenido = new StringBuilder();

           while (con.rs.next()) {
               String nombreCombo = con.rs.getString("nombre");
               int cantidad = con.rs.getInt("cantidad");
               contadorCombo.put(nombreCombo, cantidad);

               // Construir el contenido para el TextArea
               contenido.append("Combo: ")
                        .append(nombreCombo)
                        .append(" - Veces ordenada: ")
                        .append(cantidad)
                        .append("\n");
           }

        // Asignar valores a variables específicas
           combo1 = contadorCombo.getOrDefault(" Combo Whopper Doble con Queso", 0);
           combo2 = contadorCombo.getOrDefault("Combo Stacker Chipoootle Res", 0);
           combo3 = contadorCombo.getOrDefault("Combo Whopper Angry Doble", 0);
           combo4 = contadorCombo.getOrDefault("Combo Long Rodeo", 0);
           combo5 = contadorCombo.getOrDefault("   Combo Spicy Ranch Crispy King", 0);
           
           

           // Depuración
           System.out.println("Datos obtenidos: " + contadorCombo);
           

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
            // Capturar la grafica como una imagen
            WritableImage snapshot = Grafica.snapshot(new SnapshotParameters(), null);

            // Guardar la imagen en un archivo temporal
            File tempFile = new File("graficaCombos.png");
            ImageIO.write(SwingFXUtils.fromFXImage(snapshot, null), "png", tempFile);

            // Crear un documento PDF
            PdfWriter.getInstance(document, new FileOutputStream("graficaCombos.pdf"));
            document.open();
            //Agrega el titulo al pdf
            Paragraph titulo = new Paragraph("Graficas de ventas de Combos", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18));
            titulo.setAlignment(Paragraph.ALIGN_CENTER);
            Paragraph titulo2 = new Paragraph("********** Lista de Combos Pedidos **********",FontFactory.getFont(FontFactory.TIMES,14));
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
    void btnGuardar_OneClick(MouseEvent event) {
    	guardarGraficaEnPDF();
    	

    }


}
