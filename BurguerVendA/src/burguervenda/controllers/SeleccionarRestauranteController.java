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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
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
    private CheckBox ckbxFavoritos;

    @FXML
    private Label lbDireccion;

    @FXML
    private Label lbInfo;

    @FXML
    private Label lbNombre;

    @FXML
    private ComboBox<Restaurante> cbEligeRestaurante;

    @FXML
    private ComboBox<Restaurante> cbFavoritos;
    
    ArrayList<Restaurante> restList = new ArrayList<>();
    ArrayList<Restaurante> restFavs = new ArrayList<>();
    Restaurante seleccion;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Restaurante res = new Restaurante();
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
        seleccion=cbEligeRestaurante.getSelectionModel().getSelectedItem();
        if(seleccion!=null){
            lbNombre.setText(seleccion.getNombre());
            lbDireccion.setText(seleccion.getDireccion());
            lbInfo.setText(seleccion.getInfoApertura());
            cbFavoritos.setDisable(true);
        }
    }
    
    @FXML
    void mostrarInfo2(ActionEvent event) throws IOException {
        seleccion=cbFavoritos.getSelectionModel().getSelectedItem();
        if(seleccion!=null){
            lbNombre.setText(seleccion.getNombre());
            lbDireccion.setText(seleccion.getDireccion());
            lbInfo.setText(seleccion.getInfoApertura());
            cbEligeRestaurante.setDisable(true);
        }
    }
    
    @FXML
    void seleccionarRestaurante(ActionEvent event) throws IOException {
        if(ckbxFavoritos.isSelected()){
            restFavs.add(seleccion);
            if(cbFavoritos.getItems().contains(seleccion)){
                Alert alerta = new Alert(AlertType.ERROR,"Este restaurante ya se encuentra en favoritos",ButtonType.OK);
                alerta.show();
            }
            else{
               cbFavoritos.getItems().add(seleccion); 
            }
            ckbxFavoritos.setSelected(false);
        }
        if(seleccion == null){
            Alert alerta = new Alert(AlertType.ERROR,"Ingrese un restaurante",ButtonType.YES);
            alerta.setTitle("Error al escoger restaurante");
            alerta.show();
            return;
        }
        cbEligeRestaurante.setDisable(false);
        cbFavoritos.setDisable(false);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/burguervenda/vistas/RecogerPedidoProceso.fxml"));
        Parent root = loader.load();
        RecogerPedidoProcesoController controller = loader.getController();
        controller.setResElegido(seleccion);
        anchorPane.getChildren().setAll(root);
    }
}
