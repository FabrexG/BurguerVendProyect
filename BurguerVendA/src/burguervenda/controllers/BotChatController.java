/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package burguervenda.controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class BotChatController implements Initializable {

    @FXML
    private AnchorPane anchorPane;
    
    @FXML
    private Button btnSalir;
    
    @FXML
    private Label lbRestaurante;

    @FXML
    private WebView wbChatBot;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        WebEngine webEngine = wbChatBot.getEngine();
        File file = new File("src/burguervenda/elementosweb/BotChat.html");
        webEngine.load(file.toURI().toString());
    }
    
    @FXML
    void salirChat(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/burguervenda/vistas/MonitorearPedido.fxml"));
        anchorPane.getChildren().setAll(root);
    }
    
}
