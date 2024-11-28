package application;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;

public class MostarController {

    @FXML
    private AnchorPane pane;
    @FXML
    private Button btnAceptar;

    @FXML
    private TextArea txtAreaInfo;
    
    private String des;
    
    

    /**
	 * @return the des
	 */
	public String getDes() {
		return des;
	}


	/**
	 * @param des the des to set
	 */
	public void setDes(String des) {
		txtAreaInfo.setText(des);
		txtAreaInfo.setEditable(false);
	}
	
	
	@FXML
    void btnAceptra_OneCliked(MouseEvent event) {
		try {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("MenuPrincipla.fxml"));
            Parent loginPane = loader.load();
            Stage stage = (Stage) pane.getScene().getWindow();
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
