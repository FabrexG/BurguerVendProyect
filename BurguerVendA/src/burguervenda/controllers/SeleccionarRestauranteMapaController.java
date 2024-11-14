/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package burguervenda.controllers;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

/**
 * FXML Controller class
 *
 * @author asus
 */


public class SeleccionarRestauranteMapaController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private AnchorPane anchPaneInfo;

    @FXML
    private Button btnBurguerDelivery;

    @FXML
    private Button btnBurguerPick;

    @FXML
    private Button btnSelectUbi;

    @FXML
    private ComboBox<?> cbEligeRestaurante;

    @FXML
    private WebView wb_mapa;

    @FXML
    void btnBurguerDeliveryOnAction(ActionEvent event) {

    }

    @FXML
    void btnBurguerPickOnAction(ActionEvent event) {

    }

    @FXML
    void btnSelectUbiOnAction(ActionEvent event) {

    }

    @FXML
    void mostrarInfo(ActionEvent event) {

    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        WebEngine webEngine = wb_mapa.getEngine();
        
        File file = new File("src/burguervenda/mapas/MapaRestaurantes.html");
        webEngine.load(file.toURI().toString());
        
    }
    
    
}
