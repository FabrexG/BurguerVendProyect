/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package burguervenda.controllers;

import burguervenda.clases.Restaurante;
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

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class SeleccionarRestauranteController implements Initializable {
    
    @FXML
    private AnchorPane anchPaneInfo;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button btnBurguerDelivery;

    @FXML
    private Button btnBurguerPick;

    @FXML
    private Button btnSelectFav;

    @FXML
    private Button btnSelectUbi;

    @FXML
    private ComboBox<Restaurante> cbEligeRestaurante;

    @FXML
    private ComboBox<Restaurante> cbFavoritos;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Restaurante res = new Restaurante();
        ArrayList<Restaurante> restList = new ArrayList<>();
        ArrayList<Restaurante> restFavs = new ArrayList<>();
        restList=res.getRestaurantes();
        cbEligeRestaurante.getItems().addAll(restList);
        cbFavoritos.getItems().addAll(restFavs);
    }   
    
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
        //TODO
    }
    
    @FXML
    void mostrarInfo(ActionEvent event) throws IOException {
        Restaurante seleccion=cbEligeRestaurante.getSelectionModel().getSelectedItem();
        if(seleccion!=null){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/burguervenda/vistas/MostrarRestauranteInfo.fxml"));
            Parent root = loader.load();
            MostrarRestauranteInfoController controller = loader.getController();
            controller.loadInfo(seleccion);
            anchPaneInfo.getChildren().setAll(root);
        }
    }
}
