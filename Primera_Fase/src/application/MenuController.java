package application;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MenuController{

    @FXML
    private VBox VboxCombo;

    @FXML
    private VBox VboxSola;

    @FXML
    private Button btnSalir;
    @FXML
    private AnchorPane principal;

    @FXML
    void btnSalir_OneClick(MouseEvent event) {
    	 Alert alert = new Alert(AlertType.INFORMATION);
         alert.setTitle("Despedida");
         alert.setContentText("Adios Gracias Por Entrar A La Aplicacion\n");
         alert.showAndWait();
    	System.exit(0);

    }

    @FXML
    void vBoxCombo_OneCliked(MouseEvent event) {
    	try {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("PedirCombo.fxml"));
            Parent loginPane = loader.load();
            Stage stage = (Stage) principal.getScene().getWindow();
            stage.setScene(new Scene(loginPane));
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Éxito");
            alert.setContentText("Escogiste La Opcion Del Combo\n");
            alert.showAndWait();
          } catch (IOException e) {
            e.printStackTrace();
        }
    	
    	

    }

    @FXML
    void vBoxSola_OneClicked(MouseEvent event) {
    	try {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("ElegirHamburguesa.fxml"));
            Parent loginPane = loader.load();
            Stage stage = (Stage) principal.getScene().getWindow();
            stage.setScene(new Scene(loginPane));
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Éxito");
            alert.setContentText("Escogiste La Opcion De Hamburguesa Individual\n");
            alert.showAndWait();
          } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
