package application;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import conexion.ConectarBD;

public class PedirComboController {
	ConectarBD con = new ConectarBD();

    @FXML
    private Label lblCombo1;

    @FXML
    private Label lblCombo2;

    @FXML
    private Label lblCombo3;

    @FXML
    private Label lblCombo4;

    @FXML
    private Label lblCombo5;

    @FXML
    private Label lblPrecioCombo1;

    @FXML
    private Label lblPrecioCombo2;

    @FXML
    private Label lblPrecioCombo3;

    @FXML
    private Label lblPrecioCombo4;

    @FXML
    private Label lblPrecioCombo5;

    @FXML
    private AnchorPane principal;

    @FXML
    private VBox vBoxCombo1;

    @FXML
    private VBox vBoxCombo2;

    @FXML
    private VBox vBoxCombo3;

    @FXML
    private VBox vBoxCombo4;

    @FXML
    private VBox vBoxCombo5;

    @FXML
    private VBox vBoxHambuerguesaSola;
    
 // Variables para almacenar precios
    private int precio1;
    private int precio2;
    private int precio3;
    private int precio4;
    private int precio5;
    
    //Extrae los valores de las etiquetas del precio una vez que carga la interfaz 
    @FXML
    public void initialize() {
        // Inicializar precios al cargar la interfaz
        precio1 = parsePrecio(lblPrecioCombo1.getText());
        precio2 = parsePrecio(lblPrecioCombo2.getText());
        precio3 = parsePrecio(lblPrecioCombo3.getText());
        precio4 = parsePrecio(lblPrecioCombo4.getText());
        precio5 = parsePrecio(lblPrecioCombo5.getText());
    }

    //Aplica un parceo a los precio de cada label eliminado simblos y convirtiendolos en enteros
    private int parsePrecio(String precioTexto) {
        // Elimina el símbolo $ y convierte a entero
        if (precioTexto != null) {
            try {
                return Integer.parseInt(precioTexto.replace("$", "").trim());
            } catch (NumberFormatException e) {
                System.err.println("Error Al Usar EL  Parseo Para Calcular El Precio: " + precioTexto);
            }
        }
        return 0; // Devuelve 0 si hay un error
    }
    private int insertarCombo(String nombre, int precioBase, int precioFinal) {
        String consulta = "INSERT INTO Combo (id_Combo, nombre, precio_base, precio_final) VALUES (seq_id_Combo.NEXTVAL, ?, ?, ?)";
         int id = -1;

        try {
            con.conectarBDOracle();
            PreparedStatement stmt = con.cn.prepareStatement(consulta, new String[] {"id_Combo"});
            stmt.setString(1, nombre);
            stmt.setInt(2, precioBase);
            stmt.setInt(3, precioFinal);
            
            stmt.executeUpdate();
            System.out.println("Combo Insertado Correctamente.");

            // Obtener el ID generado
            con.rs = stmt.getGeneratedKeys();
            if (con.rs.next()) {
                id = con.rs.getInt(1);
                System.out.println("Este es el ID generado: " +id);
            }
        } catch (SQLException e) {
            System.err.println("Error Al Insertar El Combo: " + e.getMessage());
        }
        return id;

        
}
    


	@FXML
    void vBoxCombo1_OnClick(MouseEvent event) {
    	
     try {
    	    String etiqueta = lblCombo1.getText();
    	    String ingredientes = ("Dos carnes de res a la parrilla, jitomates, lechuga, mayonesa, pepinillos, cebolla, pan suave con ajonjolí");
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("PersonalizarCombo.fxml"));
            Parent loginPane = loader.load();
            Stage stage = (Stage) principal.getScene().getWindow();
            stage.setScene(new Scene(loginPane));
            int idCombo = insertarCombo(etiqueta, precio1, precio1);
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Éxito");
            alert.setContentText("Has Escogido El: \n"+etiqueta);
            alert.showAndWait();
            PersonalizarComboController controller = loader.getController();
            controller.setPrecioCombo(precio1); // Usa precio1 aquí
            controller.setNombre(etiqueta);
            controller.setIngredientes(ingredientes);
            controller.setIdCombo(idCombo);
          } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void vBoxCombo2_OnClick(MouseEvent event) {
    	try {
    		String etiqueta = lblCombo2.getText();
    		String ingredientes =("Carne a la parrilla,Aros de cebolla,Cebolla crujiente,Queso suizo,Salsa de queso chipotle,Salsa stacker,Tocino crujiente");
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("PersonalizarCombo.fxml"));
            Parent loginPane = loader.load();
            Stage stage = (Stage) principal.getScene().getWindow();
            stage.setScene(new Scene(loginPane));
            int idCombo = insertarCombo(etiqueta, precio2, precio2);
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Éxito");
            alert.setContentText("Has Escogido El: \n"+etiqueta);
            alert.showAndWait();
            PersonalizarComboController controller = loader.getController();
            controller.setPrecioCombo(precio2); // Usa precio2 aquí
            controller.setNombre(etiqueta);
            controller.setIngredientes(ingredientes);
            controller.setIdCombo(idCombo);
          } catch (IOException e) {
            e.printStackTrace();
        }

    }

    

    @FXML
    void vBoxCombo3_OnClick(MouseEvent event) {
    	try {
    		String etiqueta = lblCombo3.getText();
    		String ingredientes=("Carne de res asada a la parrilla,Tocino de corte grueso,Queso americano,Lechuga iceberg,Tomates,Pétalos de cebolla,Jalapeños,Mayonesa cremosa,Salsa picante Angry");
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("PersonalizarCombo.fxml"));
            Parent loginPane = loader.load();
            Stage stage = (Stage) principal.getScene().getWindow();
            stage.setScene(new Scene(loginPane));
            int idCombo = insertarCombo(etiqueta, precio3, precio3);
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Éxito");
            alert.setContentText("Has Escogido El: \n"+etiqueta);
            alert.showAndWait();
            PersonalizarComboController controller = loader.getController();
            controller.setPrecioCombo(precio3); // Usa precio3 aquí
            controller.setNombre(etiqueta);
            controller.setIngredientes(ingredientes);
            controller.setIdCombo(idCombo);
          } catch (IOException e) {
            e.printStackTrace();
        }

    

    }

    @FXML
    void vBoxCombo4_OnClick(MouseEvent event) {
    	try {
    		String etiqueta = lblCombo4.getText();
    		String ingredientes = ("Dos carnes de res a la parrilla, Dos rebanadas de queso americano, Aros de cebolla crujientes, Salsa BBQ, Pan suave alargado");
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("PersonalizarCombo.fxml"));
            Parent loginPane = loader.load();
            Stage stage = (Stage) principal.getScene().getWindow();
            stage.setScene(new Scene(loginPane));
            int idCombo = insertarCombo(etiqueta, precio4, precio4);
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Éxito");
            alert.setContentText("Has Escogido El: \n"+etiqueta);
            alert.showAndWait();
            PersonalizarComboController controller = loader.getController();
            controller.setPrecioCombo(precio4); // Usa precio1 aquí
            controller.setNombre(etiqueta);
            controller.setIngredientes(ingredientes);
            controller.setIdCombo(idCombo);
          } catch (IOException e) {
            e.printStackTrace();
        }

    }

    

    @FXML
    void vBoxCombo5_OnClick(MouseEvent event) {
    	try {
    		String etiqueta = lblCombo5.getText();
    		String ingredientes = ("Pechuga de pollo crujiente,Queso suizo,Pepinillos,Aderezo ranch,Salsa buffalo,Salsa de queso pepper jack,Lechuga,Tiras de tocino");
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("PersonalizarCombo.fxml"));
            Parent loginPane = loader.load();
            Stage stage = (Stage) principal.getScene().getWindow();
            stage.setScene(new Scene(loginPane));
            int idCombo = insertarCombo(etiqueta, precio5, precio5);
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Éxito");
            alert.setContentText("Has Escogido El: \n"+etiqueta);
            alert.showAndWait();
            PersonalizarComboController controller = loader.getController();
            controller.setPrecioCombo(precio5); // Usa precio1 aquí
            controller.setNombre(etiqueta);
            controller.setIngredientes(ingredientes);
            controller.setIdCombo(idCombo);
          } catch (IOException e) {
            e.printStackTrace();
        }

    }
    

    @FXML
    void vBoxHambuerguesaSola_OnClick(MouseEvent event) {

    	try {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("MenuPrincipla.fxml"));
            Parent loginPane = loader.load();
            Stage stage = (Stage) principal.getScene().getWindow();
            stage.setScene(new Scene(loginPane));
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Devuelta");
            alert.setContentText("Volveras Al Menu Principal\n");
            alert.showAndWait();
          } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
