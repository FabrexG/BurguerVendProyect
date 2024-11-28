package application;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

public class ElegirGraficaController{
	
	@FXML
    private Button btnVolver;

    @FXML
    private HBox hbCombo;

    @FXML
    private HBox hbHamburguesa;

    @FXML
    private SplitPane spPrincipal;

    @FXML
    void hbCombo_OnClick(MouseEvent event) {
    	try {
			AnchorPane panel = FXMLLoader.load(this.getClass().getResource("GraficasCombo.fxml"));
			AnchorPane panelDer = (AnchorPane)spPrincipal.getItems().get(1);
			panelDer.getChildren().remove(0);
			panelDer.getChildren().add(panel);
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }

    @FXML
    void hbHamburguesa_OnClick(MouseEvent event) {
    	try {
			AnchorPane panel = FXMLLoader.load(this.getClass().getResource("GraficasHamburguesa.fxml"));
			AnchorPane panelDer = (AnchorPane)spPrincipal.getItems().get(1);
			panelDer.getChildren().remove(0);
			panelDer.getChildren().add(panel);
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


    }
    @FXML
    void btnVover_OneClick(MouseEvent event) {
    	try {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("MenuPrincipla.fxml"));
            Parent loginPane = loader.load();
            Stage stage = (Stage) spPrincipal.getScene().getWindow();
            stage.setScene(new Scene(loginPane));
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Devuelta");
            alert.setContentText("Regresaste Al Menu Principal\n");
            alert.showAndWait();
          } catch (IOException e) {
            e.printStackTrace();
        }
    	


    }

}