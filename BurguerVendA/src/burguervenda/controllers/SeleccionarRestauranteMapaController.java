/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package burguervenda.controllers;

import burguervenda.clases.Restaurante;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
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
    private AnchorPane anchorPane;
    @FXML
    private AnchorPane anchPaneInfo;

    @FXML
    private Button btnBurguerDelivery;

    @FXML
    private Button btnBurguerPick;

    @FXML
    private Button btnSelectUbi;

    @FXML
    private ComboBox<Restaurante> cbEligeRestaurante;

    @FXML
    private WebView wb_mapa;
    
    ArrayList<Restaurante> restList = new ArrayList<>();
    ArrayList<Restaurante> restFavs = new ArrayList<>();
    Restaurante seleccion;

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
    void btnSelectUbiOnAction(ActionEvent event) {

    }

    @FXML
    void mostrarInfo(ActionEvent event) {

    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Restaurante res = new Restaurante();
        restList=res.getRestaurantes();
        restFavs=res.getRestaurantesFavoritos();
        cbEligeRestaurante.getItems().addAll(restList);
        WebEngine webEngine = wb_mapa.getEngine();
        File file = new File("src/burguervenda/elementosweb/MapaRestaurantes.html");
        webEngine.load(file.toURI().toString());
        
    }
    
    
}
