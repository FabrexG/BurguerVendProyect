/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package burguervenda.controllers;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
/**
 *
 * @author asus
 */
public class BienvenidaController {
    @FXML
     private Button btnClick;

     @FXML
     private Button btnInfo;

     @FXML
     void btnClickOnAction(ActionEvent event) throws IOException {
         Stage stage=new Stage();
         AnchorPane root =(AnchorPane) FXMLLoader.load(getClass().getResource("/burguervenda/vistas/AdminLogin.fxml"));
         Scene scene=new Scene(root);
         stage.setTitle("Login");
         stage.setScene(scene);
         stage.show();
     }

     @FXML
     void btnInfoOnAction(ActionEvent event) throws IOException {
         Stage stage = new Stage();
         AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("/burguervenda/vistas/AppInformacion.fxml"));
         Scene scene = new Scene(root);
         stage.setTitle("Acerca de...");
         stage.setScene(scene);
         stage.show();
     }    
}
