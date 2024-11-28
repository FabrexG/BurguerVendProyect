package application;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;

public class InfoController {

    @FXML
    private Button btnAceptar;

    @FXML
    private AnchorPane pane;
    @FXML
    private TextArea txtAreaInfo;
    
    private String desc;
    

    /**
	 * @return the desc
	 */
	public String getDesc() {
		return desc;
	}


	/**
	 * @param desc the desc to set
	 */
	public void setDesc(String desc) {
		txtAreaInfo.setText(desc);
		txtAreaInfo.setEditable(false);
	}


	@FXML
    void btnAceptar_OneClicked(MouseEvent event) {
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

