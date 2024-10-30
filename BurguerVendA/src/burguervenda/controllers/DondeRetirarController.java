/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package burguervenda.controllers;

import burguervenda.clases.Restaurante;
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

/**
 * FXML Controller class
 *
 * @author Brian Miguel Escalona Maldonado
 */
public class DondeRetirarController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button btnAutoVend;

    @FXML
    private Button btnMostrador;

    @FXML
    private Button btnRegresar;

    @FXML
    private Label lbRestaurante;
    
    Restaurante resElegido;

    public Restaurante getResElegido() {
        return resElegido;
    }

    public void setResElegido(Restaurante resElegido) {
        this.resElegido = resElegido;
    }
    
    public void loadRestaurante()
    {
        lbRestaurante.setText(resElegido.getNombre());
    }

    @FXML
    void regresarVentanaAnterior(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/burguervenda/vistas/RecogerPedidoProceso.fxml"));
        Parent root = loader.load();
        RecogerPedidoProcesoController controller = loader.getController();
        controller.setResElegido(resElegido);
        anchorPane.getChildren().setAll(root);
    }

    @FXML
    void retirarPedidoAuto(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/burguervenda/vistas/RetirarPedido.fxml"));
        Parent root = loader.load();
        RetirarPedidoController controller = loader.getController();
        controller.cargarInfo("AutoVend");
        anchorPane.getChildren().setAll(root);
    }

    @FXML
    void retirarPedidoMostrador(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/burguervenda/vistas/RetirarPedido.fxml"));
        Parent root = loader.load();
        RetirarPedidoController controller = loader.getController();
        controller.cargarInfo("AutoPick");
        anchorPane.getChildren().setAll(root);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
