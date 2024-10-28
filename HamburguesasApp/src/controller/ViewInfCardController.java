package controller;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class ViewInfCardController {
	
	
	@FXML
    private TextField txtFecha;

    @FXML
    private TextField txtNumeroTarjeta;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtTitularTarjeta;
	
	@FXML
	private AnchorPane anchorPane;

	 @FXML
	    private Button btnRegrsar;
	 
	 @FXML
	    void goBack(MouseEvent event) throws IOException {
		 
		 Parent pay = FXMLLoader.load(getClass().getResource("/view/ViewPayCredit.fxml"));
			anchorPane.getChildren().setAll(pay);

	    }
	 
	   @FXML
	    void Pagar(MouseEvent event) {
	   
	   
	   
	   }

	
	
}
