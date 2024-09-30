/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package burguervenda.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class AdminLoginController {
    @FXML
    private Button btnLogin;

    @FXML
    private PasswordField tfPassword;

    @FXML
    private TextField tfUsername;
    
    String user="admin";
    String pass="admin1234";
    
    @FXML
    void btnLoginOnAction(ActionEvent event) throws IOException {
        if(tfUsername.getText().equals(user) && tfPassword.getText().equals(pass))
        {
            Stage stage=new Stage();
            AnchorPane root=FXMLLoader.load(getClass().getResource("/burguervenda/vistas/InventarioLogin.fxml"));
            Scene scene=new Scene(root);
            stage.setTitle("Inventario");
            stage.setScene(scene);
            stage.show();
            tfUsername.setText("");
            tfPassword.setText("");
        }
        else 
        {
            Alert alerta=new Alert(AlertType.ERROR,"Credenciales invalidas, intente de nuevo",ButtonType.YES);
            alerta.setTitle("Erro de inicio de sesion");
            alerta.show();
        }
    }
    
}
