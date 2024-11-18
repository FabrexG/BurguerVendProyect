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
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class IngresarDireccionMapaController implements Initializable {
    
    @FXML
    private AnchorPane anchorPane;
    
    @FXML
    private AnchorPane anchPaneInfo;

    @FXML
    private Button btnBurguerDelivery;

    @FXML
    private Button btnBurguerPick;

    @FXML
    private Button btnContinuar;

    @FXML
    private CheckBox cbSinNumero;

    @FXML
    private Label lbDireccion;

    @FXML
    private Label lbInfo;

    @FXML
    private Label lbNombre;

    @FXML
    private TextField tfIndicaciones;

    @FXML
    private TextField tfNumero;

    @FXML
    private TextField tfPuerta;

    @FXML
    private WebView wb_mapa;

    @FXML
    void btnBurguerDeliveryOnAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/burguervenda/vistas/SeleccionarUbicacion.fxml"));
        anchorPane.getChildren().setAll(root);
    }

    @FXML
    void btnBurguerPickOnAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/burguervenda/vistas/SeleccionarRestaurante.fxml"));
        anchorPane.getChildren().setAll(root);
    }

    @FXML
    void ingresarDireccion(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/burguervenda/vistas/MonitorearPedido.fxml"));
        anchorPane.getChildren().setAll(root);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        WebEngine webEngine = wb_mapa.getEngine();
        File file = new File("src/burguervenda/elementosweb/MapaRestaurantes.html");
        webEngine.load(file.toURI().toString());
    }    
    
}
