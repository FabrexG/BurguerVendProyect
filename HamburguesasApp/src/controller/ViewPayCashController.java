package controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class ViewPayCashController {
	
    @FXML
    private Pane anchorPane;

    @FXML
    private Button btnRegresar;

    @FXML
    void goBack(MouseEvent event) throws IOException {
    	Parent pay = FXMLLoader.load(getClass().getResource("/view/Index.fxml"));
		anchorPane.getChildren().setAll(pay);
    	

    }

}
