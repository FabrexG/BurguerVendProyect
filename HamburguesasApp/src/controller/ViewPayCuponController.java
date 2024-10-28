package controller;

import java.io.IOException;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class ViewPayCuponController {
	@FXML
	private Label cambiar;

	@FXML
	private TextField txtCupon;

	@FXML
	private AnchorPane anchorPane;

	@FXML
	void goBack(MouseEvent event) throws IOException {
		Parent pay = FXMLLoader.load(getClass().getResource("/view/Index.fxml"));
		anchorPane.getChildren().setAll(pay);
	}

	@FXML
	void changeCupon(MouseEvent event) throws IOException {

		
		 
		 
	}
/*
	private void showAlert(String title, String message) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}*/

}
