package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextArea;

public class GenerarGraficaController {

	 @FXML
	 private TextArea txtAreaInfo;
    @FXML
    private StackedBarChart<String, Number> Grafica;
    ObservableList<Hamburguesa> listaHamburguesa = FXCollections.observableArrayList();
    
    int whopperConQueso;
    int whopperBBQTocino;
    int tresQuesosYTocinoCrispy;
    int classicCrispyKing;
    int kingDePolloGuacamole;
    int whopperSensilla;
    
   
    
    public void initialize() {
        // Configurar el eje X e Y
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Hamburguesas");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Ventas");

        // Configurar el título del gráfico
        Grafica.setTitle("Ventas Totales De Hamburguesas");
        leerArchivo("hamburguesas.txt");
        leerArchivo2("hamburguesas.txt");
        txtAreaInfo.setEditable(false);

        
        
        
          // Crear series de datos
        XYChart.Series<String, Number> series1 = new XYChart.Series<>();
        series1.setName("Whopper con queso");
        series1.getData().add(new XYChart.Data<>("A",whopperConQueso));
        
        XYChart.Series<String, Number> series2 = new XYChart.Series<>();
        series2.setName("Whopper BBQ Tocino"); 
        series2.getData().add(new XYChart.Data<>("B",whopperBBQTocino));
        
        XYChart.Series<String, Number> series3 = new XYChart.Series<>();
        series3.setName("3 Quesos y Tocino Crispy");
        series3.getData().add(new XYChart.Data<>("C",tresQuesosYTocinoCrispy));
        
        XYChart.Series<String, Number> series4 = new  XYChart.Series<>();
        series4.setName("Classic Crispy King");
        series4.getData().add(new XYChart.Data<>("D",classicCrispyKing));
        
        XYChart.Series<String, Number> series5 = new XYChart.Series<>();
        series5.setName("King de Pollo Guacamole");
        series5.getData().add(new XYChart.Data<>("E",kingDePolloGuacamole));
        
        XYChart.Series<String, Number> series6 = new XYChart.Series<>();
        series6.setName("Whopper Sensilla");
        series6.getData().add(new XYChart.Data<>("F",whopperSensilla));

        
        
        // Agregar las series al gráfico
        Grafica.getData().addAll(series1,series2,series3,series4,series5,series6);
    }
    public void leerArchivo(String nombreArchivo) {
        StringBuilder contenido = new StringBuilder();  // Acumulador para el texto del archivo
        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                contenido.append(linea).append("\n"); // Añade cada línea al StringBuilder
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
        txtAreaInfo.setText(contenido.toString());  // Muestra el contenido en el TextArea
    }
    
    public void leerArchivo2(String nombreArchivo) {
        Map<String, Integer> contadorHamburguesas = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                // Contar el número de pedidos de cada hamburguesa
                if (linea.startsWith("Nombre: ")) {
                    String nombreHamburguesa = linea.substring(8).trim();
                    contadorHamburguesas.put(nombreHamburguesa, contadorHamburguesas.getOrDefault(nombreHamburguesa, 0) + 1);
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }

        // Asignar valores a variables específicas
         whopperConQueso = contadorHamburguesas.getOrDefault("Whopper con queso", 0);
         whopperBBQTocino = contadorHamburguesas.getOrDefault("Whopper BBQ Tocino", 0);
         tresQuesosYTocinoCrispy = contadorHamburguesas.getOrDefault("3 Quesos y Tocino Crispy King", 0);
         classicCrispyKing = contadorHamburguesas.getOrDefault("Classic Crispy King", 0);
         kingDePolloGuacamole = contadorHamburguesas.getOrDefault("King de Pollo Guacamole", 0);
         whopperSensilla = contadorHamburguesas.getOrDefault("Whopper Sensilla", 0);
        
         StringBuilder contenido = new StringBuilder();  // Acumulador para el texto del archivo
        // Imprimir en consola el número de veces que se ha pedido cada hamburguesa
        for (Map.Entry<String, Integer> entry : contadorHamburguesas.entrySet()) {
            System.out.println("Hamburguesa: " + entry.getKey() + " - Veces ordenada: " + entry.getValue());
            
        }
    }
    
  
    
}
