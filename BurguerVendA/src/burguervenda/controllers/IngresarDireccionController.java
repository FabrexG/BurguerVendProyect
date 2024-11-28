/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package burguervenda.controllers;

import burguervenda.clases.Direccion;
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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class IngresarDireccionController implements Initializable {

    @FXML
    private AnchorPane anchPaneInfo;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button btnBurguerDelivery;

    @FXML
    private Button btnBurguerPick;

    @FXML
    private Button btnContinuar;

    @FXML
    private Button btnSelectUbi;

    @FXML
    private ComboBox<Direccion> cbDirecciones;

    @FXML
    private CheckBox ckbxGuardarDireccion;

    @FXML
    private CheckBox ckbxSinNumero;

    @FXML
    private Label lbDireccion;

    @FXML
    private Label lbInfo;

    @FXML
    private Label lbNombre;

    @FXML
    private TextField tfDireccion;

    @FXML
    private TextField tfIndicaciones;

    @FXML
    private TextField tfNumero;

    @FXML
    private TextField tfPiso;
    
    Direccion seleccion = new Direccion();
    
    ArrayList<Direccion> direcciones = new ArrayList<>(); 

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
    void btnSelectUbiOnAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/burguervenda/vistas/IngresarDireccionMapa.fxml"));
        anchorPane.getChildren().setAll(root);
    }
    
     @FXML
    void completarInfo(ActionEvent event) {
        seleccion = cbDirecciones.getSelectionModel().getSelectedItem();
        if(seleccion != null) {
            tfDireccion.setText(seleccion.getDireccion());
            tfNumero.setText(seleccion.getNumero());
            tfPiso.setText(seleccion.getPiso());
            tfIndicaciones.setText(seleccion.getIndicaciones());
        }
    }

    @FXML
    void ingresarDireccion(ActionEvent event) throws IOException {
        if(tfDireccion.getText().isEmpty() || tfPiso.getText().isEmpty() || tfIndicaciones.getText().isEmpty()) {
            Alert alerta = new Alert(Alert.AlertType.ERROR,"Favor de rellenar los todos los campos",ButtonType.OK);
            alerta.show();
            return;
        }
        if(ckbxGuardarDireccion.isSelected()) {
            if(cbDirecciones.getItems().contains(seleccion)) {
                Alert alerta = new Alert(Alert.AlertType.ERROR,"Esta direccion ya se encuentra en guardados",ButtonType.OK);
                alerta.show();
                limpiarFormulario();
                return;
            } else {
                seleccion.guardarDireccion(tfDireccion.getText(), tfNumero.getText(), tfPiso.getText(), tfIndicaciones.getText());
                cbDirecciones.getItems().add(new Direccion(tfDireccion.getText(), tfNumero.getText(), tfPiso.getText(), tfIndicaciones.getText()));
            }
            ckbxGuardarDireccion.setSelected(false);
        }
        Parent root = FXMLLoader.load(getClass().getResource("/burguervenda/vistas/MonitorearPedido.fxml"));
        anchorPane.getChildren().setAll(root);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Direccion dir = new Direccion();
        direcciones = dir.getDirecciones();
        cbDirecciones.getItems().addAll(direcciones);
    }    
    
    @FXML
    void sinNumero(ActionEvent event) {
        if(ckbxSinNumero.isSelected()){
            tfNumero.setDisable(true);
        }
        else {
            tfNumero.setDisable(false);
        }
    }
    
    private void limpiarFormulario() {
        tfDireccion.setText("");
        tfNumero.setText("");
        tfIndicaciones.setText("");
        tfPiso.setText("");
        ckbxSinNumero.setSelected(false);
        ckbxGuardarDireccion.setSelected(false);
    }
}
