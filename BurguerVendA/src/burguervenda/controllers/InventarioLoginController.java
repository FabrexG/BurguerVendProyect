/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package burguervenda.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class InventarioLoginController implements Initializable {

	/**
	 * Initializes the controller class.
	 */

	@FXML
	private AnchorPane anchorPane;
	 

	private Stage stage;

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
	}

	@FXML
	void goToPay(MouseEvent event) throws IOException {

		Parent pay = FXMLLoader.load(getClass().getResource("/burguervenda/vistas/FormaPago.fxml"));
		anchorPane.getChildren().setAll(pay);
		
		
		
	}

	

}
