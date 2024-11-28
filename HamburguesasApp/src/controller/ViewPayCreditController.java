package controller;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class ViewPayCreditController {

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Button btnRegresar;
   
    @FXML
    private Button btnVisa;
    @FXML
    private Button btnMaster;
    


    @FXML
    void goBack(MouseEvent event) throws IOException {
    	Parent pay = FXMLLoader.load(getClass().getResource("/view/Index.fxml"));
		anchorPane.getChildren().setAll(pay);
    }
    
    @FXML
    void payMentCard(MouseEvent event) throws IOException {
    	Parent pay = FXMLLoader.load(getClass().getResource("/view/ViewInfCard.fxml"));
		anchorPane.getChildren().setAll(pay);
    	
    }

    @FXML
    void payMentCard2(MouseEvent event) throws IOException {
    	
    	Parent pay = FXMLLoader.load(getClass().getResource("/view/ViewInfCard.fxml"));
		anchorPane.getChildren().setAll(pay);
    }

    public void deshabilitarBotones() {
        btnVisa.setDisable(true);
        btnMaster.setDisable(true);
        btnRegresar.setDisable(true);
    }
    

    
    
    
    
    
}
