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
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Brian Miguel Escalona Maldonado
 */
public class RecogerPedidoProcesoController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private AnchorPane anchorPane;
    
    @FXML
    private Button btnAyuda;

    @FXML
    private Button btnIrRestaurante;

    @FXML
    private Button btnVerDetalle;
    
    Restaurante resElegido;

    public Restaurante getResElegido() {
        return resElegido;
    }

    public void setResElegido(Restaurante resElegido) {
        this.resElegido = resElegido;
    }

    @FXML
    void abrirChat(ActionEvent event) {

    }

    @FXML
    void irRetirar(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/burguervenda/vistas/DondeRetirar.fxml"));
        Parent root = loader.load();
        DondeRetirarController controller = loader.getController();
        controller.setResElegido(resElegido);
        controller.loadRestaurante();
        anchorPane.getChildren().setAll(root);
    }

    @FXML
    void monitorearPedido(ActionEvent event) {

    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    
}
